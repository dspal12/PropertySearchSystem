package com.dhruv.PropertySearchLogin_webapp.Service;

import java.util.Set;

import com.dhruv.PropertySearchLogin_webapp.entity.User;
import com.dhruv.PropertySearchLogin_webapp.entity.UserRoles;

public interface UserService {

	public User createuser(User user,Set<UserRoles> userRoles) throws Exception;
	
	public User getUser(String username);
	
	public void deleteUser(long userId);
}
