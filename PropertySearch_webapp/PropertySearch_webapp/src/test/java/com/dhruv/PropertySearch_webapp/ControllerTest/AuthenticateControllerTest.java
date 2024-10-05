package com.dhruv.PropertySearch_webapp.ControllerTest;

import com.dhruv.PropertySearchLogin_webapp.entity.JwtRequest;
import com.dhruv.PropertySearchLogin_webapp.entity.JwtResponse;
import com.dhruv.PropertySearchLogin_webapp.entity.User;
import com.dhruv.PropertySearchLogin_webapp.PropertySearchWebappApplication;
import com.dhruv.PropertySearchLogin_webapp.Service.UserDetailsServiceImpl;
import com.dhruv.PropertySearchLogin_webapp.config.JwtUtils;
import com.dhruv.PropertySearchLogin_webapp.controller.AuthenticateController;
import com.dhruv.PropertySearchLogin_webapp.helper_exception.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = PropertySearchWebappApplication.class )
@RunWith(SpringRunner.class)
@TestPropertySource("/application.properties")
//@SpringBootTest
public class AuthenticateControllerTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private JwtUtils jwtUtils;

    @InjectMocks
    private AuthenticateController controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGenerateToken_SuccessfulAuthentication() throws Exception {
        
        JwtRequest jwtRequest = new JwtRequest();
        jwtRequest.setPassword("cts123");
        jwtRequest.setUsername("Wade");
        String token = "generated_token";

        when(userDetailsService.loadUserByUsername(jwtRequest.getUsername())).thenReturn(mock(UserDetails.class));
        when(jwtUtils.generateToken(any(UserDetails.class))).thenReturn(token);

        
        ResponseEntity<?> response = controller.generateToken(jwtRequest);

       
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());

        JwtResponse responseBody = (JwtResponse) response.getBody();
        assertNotNull(responseBody);
        assertEquals(token, responseBody.getToken());

        verify(authenticationManager, times(1)).authenticate(any());
        verify(userDetailsService, times(1)).loadUserByUsername(jwtRequest.getUsername());
        verify(jwtUtils, times(1)).generateToken(any(UserDetails.class));
    }

    @Test
    public void testGenerateToken_UserNotFoundException() throws Exception {
        
        JwtRequest jwtRequest = new JwtRequest();
        jwtRequest.setPassword("ct123");
        jwtRequest.setUsername("Wader");
       
       // String token = null;
        
        when(userDetailsService.loadUserByUsername(jwtRequest.getUsername())).thenThrow(new RuntimeException("usernotfound exception"));
        assertThrows(Exception.class, () ->controller.generateToken(jwtRequest));
       
        verify(authenticationManager).authenticate(any());
        verify(userDetailsService).loadUserByUsername(jwtRequest.getUsername());
        verify(jwtUtils, never()).generateToken(any(UserDetails.class));
    }

@Test
    public void testGetCurrentUser() {
        
        Principal principal = mock(Principal.class);
        User userDetails = mock(User.class);

        when(principal.getName()).thenReturn("username");
        when(userDetailsService.loadUserByUsername(principal.getName())).thenReturn(userDetails);

        
        User result = controller.getCurrentUser(principal);

        
        assertNotNull(result);
        assertEquals(userDetails, result);

        verify(userDetailsService, times(1)).loadUserByUsername(principal.getName());
    }

    
}