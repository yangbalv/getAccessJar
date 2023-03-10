/*
 * ================================================================
 * Copyright 2008-2014 Everelegance.
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Everelegance Corp. Ltd, ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Everelegance.
 *
 * ================================================================
 *  创建人: JJRQ
 *	创建时间: 2015年8月29日 - 下午6:44:04
 */
package cn.wmrx.getaccess.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.UUID;

/**
 * <p>
 * Https 工具类
 * </p>
 *
 * @author JJRQ
 * @version 1.0.0
 * @since 1.0.0
 */
public class HttpsUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(HttpsUtil.class);

	public static final String TYPE_GET = "GET";

	public static final String TYPE_POST = "POST";

	private static final int CONNECT_TIMEOUT = 5000;
	private static final int READ_TIMEOUT = 8000;

	public static String httpsRequest(String requestUrl, String requestMethod, String outputStr)
			throws GeneralSecurityException, IOException {

		HttpsURLConnection conn = null;
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;

		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new HttpsTrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			conn = (HttpsURLConnection) url.openConnection();
			conn.setSSLSocketFactory(ssf);
			conn.setConnectTimeout(CONNECT_TIMEOUT);
			conn.setReadTimeout(READ_TIMEOUT);

			conn.setDoOutput(true); // 使用 URL 连接进行输出
			conn.setDoInput(true); // 使用 URL 连接进行输入
			conn.setUseCaches(false); // 不使用缓存
			// 设置请求方式（GET/POST）
			conn.setRequestMethod(requestMethod);

			// 当outputStr不为null时向输出流写数据
			if (null != outputStr) {
				OutputStream outputStream = conn.getOutputStream();
				// 注意编码格式
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 从输入流读取返回内容
			inputStream = conn.getInputStream();
			inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
			bufferedReader = new BufferedReader(inputStreamReader);
			StringBuffer buffer = new StringBuffer();
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			LOGGER.info("http[{}]返回结果为：{}", requestMethod, buffer.toString());
			return buffer.toString();
		} finally {
			// 释放资源
			if (null != bufferedReader) {
				bufferedReader.close();
			}
			if (null != inputStreamReader) {
				inputStreamReader.close();
			}
			if (null != inputStream) {
				inputStream.close();
			}
			if (null != conn) {
				conn.disconnect();
			}
		}
	}

	/**
	 * 上传文件只须关注请求头和请求体
	 * 请求头：
	 * 包含了一系列参数，如 Content-Length、Content-Type、Cookie等，但大多数并不是必需的。
	 * 对于文件上传请求，最重要的是 Content-Type，它用于指定HTTP请求的内容类型，如下：
	 * Content-Type:multipart/form-data;
	 * boundary=----WeKitFormBoundaryLhuhxmsef74yiAUO
	 * 上传文件Content-Type必须为multipart/form-data。
	 * boundary是数据分隔符，是随机生成的，如果同时上传多个文件，在请求体中通过boundary分隔每个文件的内容。
	 * 请求体：
	 * 请求体中用到了请求头中定义的boundary，如下：
	 * ----WeKitFormBoundaryLhuhxmsef74yiAUO（数据分隔符）
	 * Content-Disposition: form-data; name="media";
	 * filename="filename.jpg"（与请求表单对应）
	 * Content-Type: image/jpeg（文件的MIME类型）
	 * 这一行用来存放文件内容...（文件内容）
	 * 空行（必须存在）
	 * ----WeKitFormBoundaryLhuhxmsef74yiAUO（数据分隔符）
	 * 通过代码上传文件和使用网页表单上传文件的原理一样，也需要构造相同格式的请求头和请求体，在发送到服务器
	 *
	 * @throws IOException
	 */
	public static String uploadMedia(String apiUrl, String fileType, byte[] fileDatas)
			throws GeneralSecurityException, IOException {

		HttpsURLConnection connection = null;
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;

		try {
			TrustManager[] tm = { new HttpsTrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new SecureRandom());

			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(apiUrl);
			connection = (HttpsURLConnection) url.openConnection();

			String boundary = "------WebKitFormBoundarypbXnApx026IOV4ox";

			connection.setSSLSocketFactory(ssf);
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod(TYPE_POST);
			connection.setRequestProperty("Content-Length", String.valueOf(fileDatas.length));
			connection.setRequestProperty("Content-Type",
					"multipart/form-data; boundary=" + boundary);

			OutputStream outputStream = connection.getOutputStream();

			String contentType = HttpsUtil.getContentType(fileType);

			String uuid = UUID.randomUUID().toString().replace("-", "");

			outputStream.write(("--" + boundary + "\r\n").getBytes());
			outputStream.write(String
					.format("Content-Disposition: form-data; name=\"%s\"; filename=\"%s\"\r\n",
							uuid, uuid + "." + fileType)
					.getBytes());
			outputStream.write(String.format("Content-Type: %s\r\n\r\n", contentType).getBytes());

			BufferedInputStream bis = new BufferedInputStream(new ByteArrayInputStream(fileDatas));
			byte[] buf = new byte[8096];
			int size = 0;
			while ((size = bis.read(buf)) != -1) {
				outputStream.write(buf, 0, size);
			}

			outputStream.write(("\r\n--" + boundary + "--\r\n").getBytes());

			outputStream.close();
			bis.close();

			inputStream = connection.getInputStream();
			inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
			bufferedReader = new BufferedReader(inputStreamReader);
			StringBuffer buffer = new StringBuffer();
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			return buffer.toString();
		} finally {
			if (null != bufferedReader)
				bufferedReader.close();
			if (null != inputStreamReader)
				inputStreamReader.close();
			if (null != inputStream)
				inputStream.close();
			if (null != connection)
				connection.disconnect();
		}
	}

	private static String getContentType(String fileType) {
		String contentType = "";
		if ("jpg".equalsIgnoreCase(fileType)) {
			contentType = "image/jpeg";
		} else if ("png".equalsIgnoreCase(fileType)) {
			contentType = "image/png";
		}
		return contentType;
	}



}
