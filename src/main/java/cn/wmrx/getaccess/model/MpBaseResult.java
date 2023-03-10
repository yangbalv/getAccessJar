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
 *	创建时间: 2015年9月15日 - 下午2:55:06
 */
package cn.wmrx.getaccess.model;

import java.io.Serializable;

/**
 * <p>
 * 微信公众平台 API调用结果基类
 * </p>
 *
 * @author JJRQ
 *
 * @version 1.0.0
 *
 * @since 1.0.0
 *
 */
public class MpBaseResult implements Serializable {
	
	private static final long serialVersionUID = -9222780587538094114L;

	public static final int SYSTEM_ERROR_CODE = 61450;
	
	protected int errcode;
	
	protected String errmsg;
	
	public MpBaseResult() {
		
	}
	
	public MpBaseResult(int errcode, String errmsg) {
		this.errcode = errcode;
		this.errmsg = errmsg;
	}

	/**
	 * @return the errcode
	 */
	public int getErrcode() {
		return errcode;
	}

	/**
	 * @param errcode the errcode to set
	 */
	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}

	/**
	 * @return the errmsg
	 */
	public String getErrmsg() {
		return errmsg;
	}

	/**
	 * @param errmsg the errmsg to set
	 */
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	
}
