package com.dhruv.PropertySearch_webapp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.Mockito.when;


import com.dhruv.PropertySearchLogin_webapp.Repository.OwnerRepository;
import com.dhruv.PropertySearchLogin_webapp.Repository.PropertyRepository;
import com.dhruv.PropertySearchLogin_webapp.Repository.RoleRepository;
import com.dhruv.PropertySearchLogin_webapp.Repository.TypeRepository;
import com.dhruv.PropertySearchLogin_webapp.Repository.UserRepository;
//import com.dhruv.PropertySearchLogin_webapp.Service.Post_Delete_Service;
import com.dhruv.PropertySearchLogin_webapp.Service.UserService;
import com.dhruv.PropertySearchLogin_webapp.config.JwtUtils;
import com.dhruv.PropertySearchLogin_webapp.controller.AuthenticateController;
import com.dhruv.PropertySearchLogin_webapp.controller.UserController;
import com.dhruv.PropertySearchLogin_webapp.entity.JwtRequest;
import com.dhruv.PropertySearchLogin_webapp.entity.Role;
import com.dhruv.PropertySearchLogin_webapp.entity.User;
import com.dhruv.PropertySearchLogin_webapp.entity.UserRoles;


@ContextConfiguration
@RunWith(SpringRunner.class)

class PropertySearchWebappApplicationTests {

	
	 //  @Autowired
	  //  private Post_Delete_Service pdservice;
	   
	   @Mock
	    private UserService userService;
	   
	   @Mock
	    private OwnerRepository ownerRepo;
	    
	   @Mock
	    private PropertyRepository propertyRepo;
	    
	    @Mock
	    private TypeRepository typeRepo;
	    
	    @Mock
	    private UserRepository userRepo;
	    
	    @Mock
	AuthenticationManager authenticationManager;

	
		@Mock
		RoleRepository roleRepository;

		@Mock
	    JwtUtils jwtUtils;
		
	@InjectMocks
	AuthenticateController authController;
			@Test
		public void authenticateUserTest() {
			JwtRequest loginRequest= new JwtRequest();
			loginRequest.setUsername("ankita");
			loginRequest.setPassword("123456");
			Authentication authToken = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
			SecurityContextHolder.getContext().setAuthentication(authToken);
		}
			
			
			@Test
			public void testRegisterUserWithDefaultUser() {
				//SignupRequest signUpRequest = new SignupRequest();
				User user = new User();  
				Set<UserRoles> rolelist= new HashSet<UserRoles>();
				Role role= new Role();
				role.setRoleName("USER");
				user.setUsername("ankita");
				user.setPassword("123456");
				user.setEmail("ankita@gmail.com");
				 UserRoles userRole = new UserRoles();
				 userRole.setUser(user);
				rolelist.add(userRole);
				//when(userRepo.findByUsername(user.getUsername())).thenReturn(user);
		       // assertEquals(user,"user created");
				assertNotNull(user.getUsername());
				assertNotNull(user.getPassword());
				assertNotNull(user.getEmail());
				assertNotNull(userRole.getUser());
				assertNotNull(user.getUserRoles());
				assertEquals(user,userRole.getUser());
		}
			
			
	    
	    

}
