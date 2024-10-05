package com.dhruv.PropertySearchLogin_webapp.entity;


import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;



@Entity
@Table(name="Property_User")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userid;

    @Column(length = 255)
    private String username;

    @Column(name="email", length = 255)
    private String email;

    @Column(name="password", length = 255)
    private String password;
    
    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER,mappedBy="user")
    @JsonIgnore
    private Set<UserRoles> userRoles = new HashSet<>();

	public Set<UserRoles> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(java.util.Set<UserRoles> userRoles) {
		this.userRoles = userRoles;
	}

	public User() {
	}

	public User(int userid, String username, String email, String password) {
		super();
		this.userid = userid;
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [userid=" + userid + ", username=" + username + ", email=" + email + ", password=" + password
				+ "]";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	     Set<Authority> set = new HashSet<>();
		this.userRoles.forEach(userRole->{
			set.add(new Authority(userRole.getRole().getRoleName()));
		});
		
		return set;
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {

		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
	
		return true;
	}

	@Override
	public boolean isEnabled() {

		return true;
	}

	

}

//create getters and setters