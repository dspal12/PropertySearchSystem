package com.dhruv.PropertySearchLogin_webapp.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhruv.PropertySearchLogin_webapp.Service.UserService;
import com.dhruv.PropertySearchLogin_webapp.entity.Role;
import com.dhruv.PropertySearchLogin_webapp.entity.User;
import com.dhruv.PropertySearchLogin_webapp.entity.UserRoles;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

	@Autowired
	private UserService userservice;
	
	@PostMapping("/")
	public ResponseEntity<?> createUser(@RequestBody User user) throws Exception
	{Set<UserRoles> roles = new HashSet<>();
	  
	 Role role = new Role();
	 role.setRoleId(101);
	 role.setRoleName("USER");
	 
	 UserRoles userRole = new UserRoles();
	 userRole.setUser(user);
	 userRole.setRole(role);
	 
	 roles.add(userRole);
	 
	 return ResponseEntity.ok(this.userservice.createuser(user, roles)); 
	}
	
	@GetMapping("/{username}")
	public User getUser(@PathVariable("username") String username)
	{
		return this.userservice.getUser(username);}
	
	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable("userId") long userId)
	{   this.userservice.deleteUser(userId);}
}
