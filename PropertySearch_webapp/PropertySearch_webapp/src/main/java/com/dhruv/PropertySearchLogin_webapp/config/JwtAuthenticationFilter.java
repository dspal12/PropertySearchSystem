package com.dhruv.PropertySearchLogin_webapp.config;


import com.dhruv.PropertySearchLogin_webapp.Service.UserDetailsServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;

//import org.apache.catalina.security.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.dhruv.PropertySearchLogin_webapp.config.JwtUtils;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private JwtUtils jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        final String requestTokenHeader = httpServletRequest.getHeader("Authorization");
        System.out.println(requestTokenHeader);
        String username = null;
        String jwtToken = null;

        if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")){
            jwtToken = requestTokenHeader.substring(7);
            try {
                username = this.jwtUtil.extractUsername(jwtToken);
            }catch (ExpiredJwtException e){
                e.printStackTrace();
                System.out.println("Jwt token has expired");
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("Error");
            }
        }else{
            System.out.println("Invalid token, not starts with String bearer");
        }

        //validate token
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            final UserDetails userDetails = this.userDetailsServiceImpl.loadUserByUsername(username);
            if(this.jwtUtil.validateToken(jwtToken, userDetails)){
                //token is valid
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }else{
            System.out.println("Token is not valid");
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

}
