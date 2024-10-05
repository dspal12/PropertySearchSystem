package com.dhruv.PropertySearchLogin_webapp.Service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dhruv.PropertySearchLogin_webapp.Repository.RoleRepository;
import com.dhruv.PropertySearchLogin_webapp.Repository.UserRepository;
import com.dhruv.PropertySearchLogin_webapp.entity.User;
import com.dhruv.PropertySearchLogin_webapp.entity.UserRoles;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Lazy
    @Autowired	
	private UserRepository userRepo; 
    @Lazy
    @Autowired
    private RoleRepository roleRepo;
    
    private UserService userservice;
    
	public UserServiceImpl(@Lazy UserService userservice) {
		this.userservice = userservice;
	}
	//creating user
    @Override
	public User createuser(User user, Set<UserRoles> userRoles) throws Exception {
	
    	User user1 = this.userRepo.findByUsername(user.getUsername());
    	
    	if(user1!=null) 
    	{
    		System.out.println("User already exists");
    		throw new Exception("user already present");
    	}
    	else
    	{
    		for(UserRoles ur: userRoles)
    		{roleRepo.save(ur.getRole());}
    		
    		user.getUserRoles().addAll(userRoles);
    		user1 = this.userRepo.save(user);
    			
    	}
    	
    	return user1;
	}
	@Override
	public User getUser(String username) {
		System.out.println("Getting data from DB : " + username);
		return this.userRepo.findByUsername(username);
	}
	@Override
	public void deleteUser(long userId) {
		this.userRepo.deleteById(userId);
		
	}

}
