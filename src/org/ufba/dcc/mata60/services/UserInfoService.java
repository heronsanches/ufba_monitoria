package org.ufba.dcc.mata60.services;

import org.ufba.dcc.mata60.entity.User;

public interface UserInfoService {

	/** find user by account **/
	public User findUser(String account);
	
	/** update user **/
	public User updateUser(User user);
}
