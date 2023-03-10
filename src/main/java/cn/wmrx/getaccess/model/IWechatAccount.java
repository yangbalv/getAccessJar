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
 *	创建时间: 2015年8月31日 - 上午11:09:16
 */
package cn.wmrx.getaccess.model;

/**
 * <p>
 * @TODO 请“JJRQ” 尽快添加代码注释!（中文表达，简要说明）
 * </p>
 *
 * @author JJRQ
 *
 * @version 1.0.0
 *
 * @since 1.0.0
 *
 */
public interface IWechatAccount {
	

	public String getGenerateNo();

	public void setGenerateNo(String generateNo);

	public String getAppId();
	
	public void setAppId(String appId);

	public String getAppSecret();

	public void setAppSecret(String appSecret);

	public String getToken();

	public void setToken(String token);

	public String getEncodingAesKey();

	public void setEncodingAesKey(String encodingAesKey);

	public String getEncodingAesMode();

	public void setEncodingAesMode(String encodingAesMode);

}
