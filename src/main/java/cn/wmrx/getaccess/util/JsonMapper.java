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
 *	创建时间: 2015年8月31日 - 上午9:12:06
 */
package cn.wmrx.getaccess.util;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * <p>
 * @TODO 请“JJRQ” 尽快添加代码注释!（中文表达，简要说明）
 * </p>
 *
 * @author JJRQ
 *
 * @version 1.0.0
 *
 * @since 1.0.0 （ @TODO 新增自版本号，请修改！）
 *
 */
public class JsonMapper {

	private static Logger logger = LoggerFactory.getLogger(JsonMapper.class);

	private static final ObjectMapper jsonMapper = new ObjectMapper();

	public static ObjectMapper getInstance() {

		return jsonMapper;
	}

	@SuppressWarnings("unchecked")
	public static <T> List<T> toList(String jsonStr, Class<T> t) {

		JavaType javaType = getInstance().getTypeFactory().constructParametricType(List.class, t);
		try {
			return (List<T>) getInstance().readValue(jsonStr, javaType);
		} catch (Exception e) {
			logger.error("输入的json字符串[{}]格式错误", jsonStr);
			e.printStackTrace();
			return null;
		}
	}
}
