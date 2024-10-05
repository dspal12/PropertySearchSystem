package com.dhruv.PropertySearchLogin_webapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dhruv.PropertySearchLogin_webapp.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByUsername(String username);

}
