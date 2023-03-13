package cn.wmrx.getaccess.start;

import cn.wmrx.getaccess.dao.IAccessTokenDAO;
import cn.wmrx.getaccess.dao.IWechatAccountDao;
import cn.wmrx.getaccess.exception.WechatException;
import cn.wmrx.getaccess.model.AccessToken;
import cn.wmrx.getaccess.model.IWechatAccount;
import cn.wmrx.getaccess.model.MpAccessToken;
import cn.wmrx.getaccess.util.HttpsUtil;
import cn.wmrx.getaccess.util.JsonMapper;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.Properties;

public class StartGetAccessToken {
    private final static Logger logger = LoggerFactory.getLogger(StartGetAccessToken.class);

    public static void main(String[] args) throws Exception {
        String fileName = "src/main/resources/db.properties";
        InputStream propertiesInputStream = new FileInputStream(fileName);
        System.out.println("配置文件读取成功");
        BufferedReader bf = new BufferedReader(new InputStreamReader(propertiesInputStream));
        Properties properties = new Properties();
        properties.load(bf);
        System.out.println("配置文件加载成功");

        String resource = properties.getProperty("configuration");
        System.out.println("数据库配置文件名称：" + resource);
        String generateNo = properties.getProperty("generateNo");
        System.out.println("generateNo is：" + generateNo);
        String wechatApiUrl = properties.getProperty("wechatApiUrl");
        System.out.println("wechatApiUrl is：" + wechatApiUrl);

        // 定义配置文件，相对路径，文件直接放在resources目录下

        // 读取文件字节流
        InputStream inputStream = Resources.getResourceAsStream(resource);

        // mybatis 读取字节流，利用XMLConfigBuilder类解析文件
        // 将xml文件解析成一个 org.apache.ibatis.session.Configuration 对象
        // 然后将 Configuration 对象交给 SqlSessionFactory 接口实现类 DefaultSqlSessionFactory 管理
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // openSession 有多个重载方法， 比较重要几个是
        // 1 是否默认提交 SqlSession openSession(boolean autoCommit)
        // 2 设置事务级别 SqlSession openSession(TransactionIsolationLevel level)
        // 3 执行器类型   SqlSession openSession(ExecutorType execType)
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // mybatis 内部其实已经解析好了 mapper 和 mapping 对应关系，放在一个map中，这里可以直接获取
        // 如果看源码可以发现userMapper 其实是一个代理类MapperProxy，
        // 通过 sqlSession、mapperInterface、mechodCache三个参数构造的
        // MapperProxyFactory 类中 newInstance(MapperProxy<T> mapperProxy)方法


        IAccessTokenDAO accessTokenDAO = sqlSession.getMapper(IAccessTokenDAO.class);
//        获取accessToken并加锁
        AccessToken accessTokenForUpdate = accessTokenDAO.selectByIdForUpdate(generateNo);
        System.out.println("当前数据库的值是:" + JSONObject.toJSONString(accessTokenForUpdate));
        System.out.println("当前数据库的值对应状态：" + checkEffect(accessTokenForUpdate));

//        获取wechatAccountDao
        IWechatAccountDao wechatAccountDao = sqlSession.getMapper(IWechatAccountDao.class);
        IWechatAccount wechatAccount = wechatAccountDao.getConfigureByGenerateNo(generateNo);
        System.out.println("wechatAccount is: " + JSONObject.toJSONString(wechatAccount));

        MpAccessToken mpAccessToken = get(wechatAccount.getAppId(), wechatAccount.getAppSecret(), wechatApiUrl);
        System.out.println("获取微信mpAccessToken结果" + JSONObject.toJSONString(mpAccessToken));

//        拿到获取的值
        AccessToken accessToken = null;
        if (0 == mpAccessToken.getErrcode()) {
            System.out.println("获取结果成功");
            System.out.println("结果：" + JSONObject.toJSONString(mpAccessToken));
            accessToken = new AccessToken();
            accessToken.setGenerateNo(generateNo);
            accessToken.setAccessToken(mpAccessToken.getAccess_token());
            accessToken.setExpiresIn(mpAccessToken.getExpires_in());
            accessToken.setFreshTime(System.currentTimeMillis());
            accessTokenDAO.update(accessToken);
        }


        sqlSession.commit();
        sqlSession.close();
    }


    public static MpAccessToken get(String appid, String secret, String wechatApiUrl) throws WechatException {


        String url = wechatApiUrl + "/cgi-bin/token?"
                + "grant_type=client_credential"
                + "&appid=" + appid
                + "&secret=" + secret;

        MpAccessToken accessToken = null;

        try {
            String respJson = HttpsUtil.httpsRequest(url, HttpsUtil.TYPE_GET, null);

            accessToken = JsonMapper.getInstance().readValue(respJson, MpAccessToken.class);

        } catch (GeneralSecurityException e) {
            logger.error("调用微信api获取access_token失败！HTTPs请求证书不可用，异常信息:\n", e);
            throw new WechatException(WechatException.HTTPS_CERTIFICATE_ERROR);
        } catch (IOException e) {
            logger.error("调用微信api获取access_token失败！HTTPs连接出现异常，异常信息:\n", e);
            throw new WechatException(WechatException.HTTPS_CERTIFICATE_ERROR);
        }

        return accessToken;
    }

    private static boolean checkEffect(AccessToken accessToken) {

        int expiresIn = accessToken.getExpiresIn();
        long freshTime = accessToken.getFreshTime();
        long currentTimeMillis = System.currentTimeMillis();
        long remainTime = expiresIn - (currentTimeMillis - freshTime) / 1000;

        System.out.println("当前系统时间：[" + currentTimeMillis + "]ms,刷新access_token时的时间：[" + freshTime + "]ms,有效时间还剩[" + remainTime + "]s");

        return (remainTime - 15 * 60) > 0;
    }

}
