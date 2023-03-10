
package cn.wmrx.getaccess.service;

import cn.wmrx.getaccess.exception.WechatException;
import cn.wmrx.getaccess.model.IWechatAccount;

public interface IWechatAccountCfgService {

    public abstract IWechatAccount getAccountByGenerateNo(String generateNo) throws WechatException;

}
