/**
 * 
 */
package cn.wmrx.getaccess.model;

import org.esbuilder.business.model.IBaseModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * <p>
 * 微信公众账号 model
 * </p>
 *
 * @author JJRQ
 * @version 1.0.0
 * @since 1.0.0
 */
public class WechatAccount implements IBaseModel<String>, IWechatAccount {

	private static final long serialVersionUID = 4681592968939566640L;

	private String generateNo;

	private String wechatNo;

	private String wechatName;

	private String accountType;

	private String introduction;

	private String appId;

	private String appSecret;

	private String token;

	private String encodingAesMode;

	private String encodingAesKey;

	private String serverUrl;

	private byte[] qrcodeImage;

	private byte[] headerImage;

	private String createUser;

	private Date createTime;

	private String lastUpdateUser;

	private Date lastUpdateTime;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	private Date updateTime;

	/**
	 * @return the generateNo
	 */
	public String getGenerateNo() {
		return generateNo;
	}

	/**
	 * @param generateNo
	 *            the generateNo to set
	 */
	public void setGenerateNo(String generateNo) {
		this.generateNo = generateNo;
	}

	/**
	 * @return the wechatNo
	 */
	public String getWechatNo() {
		return wechatNo;
	}

	/**
	 * @param wechatNo
	 *            the wechatNo to set
	 */
	public void setWechatNo(String wechatNo) {
		this.wechatNo = wechatNo;
	}

	/**
	 * @return the wechatName
	 */
	public String getWechatName() {
		return wechatName;
	}

	/**
	 * @param wechatName
	 *            the wechatName to set
	 */
	public void setWechatName(String wechatName) {
		this.wechatName = wechatName;
	}

	/**
	 * @return the accountType
	 */
	public String getAccountType() {
		return accountType;
	}

	/**
	 * @param accountType
	 *            the accountType to set
	 */
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	/**
	 * @return the introduction
	 */
	public String getIntroduction() {
		return introduction;
	}

	/**
	 * @param introduction
	 *            the introduction to set
	 */
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	/**
	 * @return the appId
	 */
	public String getAppId() {
		return appId;
	}

	/**
	 * @param appId
	 *            the appId to set
	 */
	public void setAppId(String appId) {
		this.appId = appId;
	}

	/**
	 * @return the appSecret
	 */
	public String getAppSecret() {
		return appSecret;
	}

	/**
	 * @param appSecret
	 *            the appSecret to set
	 */
	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token
	 *            the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return the encodingAesMode
	 */
	public String getEncodingAesMode() {
		return encodingAesMode;
	}

	/**
	 * @param encodingAesMode
	 *            the encodingAesMode to set
	 */
	public void setEncodingAesMode(String encodingAesMode) {
		this.encodingAesMode = encodingAesMode;
	}

	/**
	 * @return the encodingAesKey
	 */
	public String getEncodingAesKey() {
		return encodingAesKey;
	}

	/**
	 * @param encodingAesKey
	 *            the encodingAesKey to set
	 */
	public void setEncodingAesKey(String encodingAesKey) {
		this.encodingAesKey = encodingAesKey;
	}

	/**
	 * @return the serverUrl
	 */
	public String getServerUrl() {
		return serverUrl;
	}

	/**
	 * @param serverUrl
	 *            the serverUrl to set
	 */
	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}

	/**
	 * @return the qrcodeImage
	 */
	public byte[] getQrcodeImage() {
		return qrcodeImage;
	}

	/**
	 * @param qrcodeImage
	 *            the qrcodeImage to set
	 */
	public void setQrcodeImage(byte[] qrcodeImage) {
		this.qrcodeImage = qrcodeImage;
	}

	/**
	 * @return the headerImage
	 */
	public byte[] getHeaderImage() {
		return headerImage;
	}

	/**
	 * @param headerImage
	 *            the headerImage to set
	 */
	public void setHeaderImage(byte[] headerImage) {
		this.headerImage = headerImage;
	}

	/**
	 * @return the createUser
	 */
	public String getCreateUser() {
		return createUser;
	}

	/**
	 * @param createUser
	 *            the createUser to set
	 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the lastUpdateUser
	 */
	public String getLastUpdateUser() {
		return lastUpdateUser;
	}

	/**
	 * @param lastUpdateUser
	 *            the lastUpdateUser to set
	 */
	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}

	/**
	 * @return the lastUpdateTime
	 */
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	/**
	 * @param lastUpdateTime
	 *            the lastUpdateTime to set
	 */
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	@Override
	public String getId() {
		return generateNo;
	}

	@Override
	public void setId(String id) {
		this.generateNo = id;
	}

	/**
	 * @return the updateTime
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime
	 *            the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
