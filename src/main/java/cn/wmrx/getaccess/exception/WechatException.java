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
 *	创建时间: 2015年9月16日 - 下午11:46:37
 */
package cn.wmrx.getaccess.exception;

/**
 * <p>
 * 
 * @TODO 请“JJRQ” 尽快添加代码注释!（中文表达，简要说明）
 *       </p>
 *
 * @author JJRQ
 *
 * @version 1.0.0
 *
 * @since 1.0.0
 *
 */
public class WechatException extends Exception {

	private static final long serialVersionUID = -8900399916693262815L;

	public static final String ACCOUNT_NOT_EXIST = "not exist the generateNo's wechat account";

	public static final String HTTPS_CERTIFICATE_ERROR = "https certificate error";

	public static final String HTTPS_IO_ERROR = "https io error";

	public static final String JSON_PROCESS_RETURN_DATA_ERROR = "process wechat api return data error";

	public static final String JSON_PROCESS_OUT_DATA_ERROR = "process out data error";

	public WechatException(Exception ex) {
		super(ex);
	}

	public WechatException(String message) {
		super(message);
	}

}
