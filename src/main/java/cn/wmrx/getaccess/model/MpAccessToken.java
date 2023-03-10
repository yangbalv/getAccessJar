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
 *	创建时间: 2015年9月15日 - 下午2:59:44
 */
package cn.wmrx.getaccess.model;

/**
 * <p>
 * 微信公众平台 access token 实体
 * </p>
 *
 * @author JJRQ
 *
 * @version 1.0.0
 *
 * @since 1.0.0
 *
 */
public class MpAccessToken extends MpBaseResult {
	
	private static final long serialVersionUID = 2290596389046250453L;

	private String access_token;
	
	private int expires_in;

	/**
	 * @return the access_token
	 */
	public String getAccess_token() {
		return access_token;
	}

	/**
	 * @param access_token the access_token to set
	 */
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	/**
	 * @return the expires_in
	 */
	public int getExpires_in() {
		return expires_in;
	}

	/**
	 * @param expires_in the expires_in to set
	 */
	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}

	

}
