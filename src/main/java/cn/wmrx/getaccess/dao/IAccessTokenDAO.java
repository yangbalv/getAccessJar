
package cn.wmrx.getaccess.dao;

import cn.wmrx.getaccess.model.AccessToken;


public interface IAccessTokenDAO {

     int update(AccessToken accessToken);

     int insert(AccessToken accessToken);

     AccessToken selectById(String generateNo);

     AccessToken selectByIdForUpdate(String generateNo);


}
