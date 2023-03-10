/**
 * 
 */
package cn.wmrx.getaccess.model;

import java.io.Serializable;

/**
 * 
 * <p>
 * 微信公众平台 access_token model
 * </p>
 *
 * @author JJRQ
 *
 * @version 1.0.0
 *
 * @since 1.0.0
 *
 */
public class AccessToken implements Serializable {

	private static final long serialVersionUID = 6156539988164700103L;

	private String generateNo;
	
	private String accessToken;

	private int expiresIn;

	private long freshTime;
	
	public AccessToken() {
		
	}

	public AccessToken(String generateNo) {
		this.generateNo = generateNo;
	}
	/**
	 * @return the generateNo
	 */
	public String getGenerateNo() {
		return generateNo;
	}

	/**
	 * @param generateNo the generateNo to set
	 */
	public void setGenerateNo(String generateNo) {
		this.generateNo = generateNo;
	}

	/**
	 * @return the accessToken
	 */
	public String getAccessToken() {
		return accessToken;
	}

	/**
	 * @param accessToken the accessToken to set
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	/**
	 * @return the expiresIn
	 */
	public int getExpiresIn() {
		return expiresIn;
	}

	/**
	 * @param expiresIn the expiresIn to set
	 */
	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}

	/**
	 * @return the freshTime
	 */
	public long getFreshTime() {
		return freshTime;
	}

	/**
	 * @param freshTime the freshTime to set
	 */
	public void setFreshTime(long freshTime) {
		this.freshTime = freshTime;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
        int result = 1;
        int time = (int)(freshTime ^ (freshTime >>> 32));
        result = prime * result + ((accessToken == null) ? 0 : accessToken.hashCode());
        result = prime * result + time;
        return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		AccessToken other = (AccessToken) obj;
		if(null == accessToken || null == other.accessToken)
			return false;
		if (!accessToken.equals(other.accessToken))
			return false;
		if (freshTime != other.freshTime)
			return false;
		return true;
	}

}
