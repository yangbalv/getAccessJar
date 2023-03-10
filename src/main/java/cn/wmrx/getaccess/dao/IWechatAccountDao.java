package cn.wmrx.getaccess.dao;

import cn.wmrx.getaccess.model.IWechatAccount;

public interface IWechatAccountDao {

    IWechatAccount getConfigureByGenerateNo(String generate_no);

}
