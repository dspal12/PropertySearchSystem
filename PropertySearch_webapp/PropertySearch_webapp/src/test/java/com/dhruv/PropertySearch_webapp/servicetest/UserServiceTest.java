package com.dhruv.PropertySearch_webapp.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashSet;
import java.util.Set;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.dhruv.PropertySearchLogin_webapp.PropertySearchWebappApplication;
import com.dhruv.PropertySearchLogin_webapp.Repository.RoleRepository;
import com.dhruv.PropertySearchLogin_webapp.Repository.UserRepository;
import com.dhruv.PropertySearchLogin_webapp.Service.UserService;
import com.dhruv.PropertySearchLogin_webapp.Service.UserServiceImpl;
import com.dhruv.PropertySearchLogin_webapp.entity.Role;
import com.dhruv.PropertySearchLogin_webapp.entity.User;
import com.dhruv.PropertySearchLogin_webapp.entity.UserRoles;


@SpringBootTest(classes = PropertySearchWebappApplication.class )
@RunWith(SpringRunner.class)
@TestPropertySource("/application.properties")
public class UserServiceTest {

  @Mock
	private UserRepository userRepo;
  
  @Mock
	private RoleRepository roleRepo;
  
  @InjectMocks
	private UserServiceImpl userService;
	
	//@Autowired
	//ApplicationContext context;
  //@SuppressWarnings("deprecation")
@BeforeEach
  public void setup()
 {MockitoAnnotations.openMocks(this);}
  
  
  @Test
	public void createUserServiceTest() throws Exception 
	{
		User user = new User();
		user.setUsername("ankita");
		user.setEmail("anki@gmail.com");
		user.setPassword("cts123");
		Set<UserRoles> rolelist= new HashSet<>();
		Role role= new Role();
		role.setRoleId(101);
		role.setRoleName("USER");
		 
		UserRoles userRole = new UserRoles();
		 userRole.setUser(user);
		rolelist.add(userRole);
		
		when(userRepo.findByUsername(user.getUsername())).thenReturn(null);
		when(roleRepo.save(userRole.getRole())).thenReturn(userRole.getRole());
		when(userRepo.save(user)).thenReturn(user);
		
		User createdUser = userService.createuser(user, rolelist);
		
		assertEquals(user,createdUser);
		verify(userRepo).findByUsername(user.getUsername());
		verify(roleRepo).save(userRole.getRole());
		verify(userRepo).save(user);
		
	}
  
  @Test
  public void UserAlreadyExists_test() 
  {
	  User user = new User();
	  user.setUsername("ExistingUser");
	  Set<UserRoles> userRoles = new HashSet<>();
	  when(userRepo.findByUsername(user.getUsername())).thenReturn(user);
	  
	  assertThrows(Exception.class,() -> userService.createuser(user,userRoles));
	  verify(userRepo).findByUsername(user.getUsername());
	  verify(roleRepo,never()).save(any());
	  verify(userRepo,never()).save(any(User.class));
  }
  
  @Test
  public void GetUser_test()
  {
	  String username="Wolf";
	  User user = new User();
	  user.setUsername(username);
	  
	  when(userRepo.findByUsername(username)).thenReturn(user);
	  User actualresult = userService.getUser(username);
	  assertEquals(user,actualresult);
	  verify(userRepo).findByUsername(username);
  }
  
  @Test
  public void DeleteUser_test()
  {
	  long userID = 66;
	  
	  userService.deleteUser(userID);
	  
	  verify(userRepo).deleteById(userID);
  }
  
}
