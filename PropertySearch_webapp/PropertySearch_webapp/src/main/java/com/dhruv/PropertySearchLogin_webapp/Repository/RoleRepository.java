package com.dhruv.PropertySearchLogin_webapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dhruv.PropertySearchLogin_webapp.entity.Role;
import com.dhruv.PropertySearchLogin_webapp.entity.User;

public interface RoleRepository extends JpaRepository<Role, Long>{

}
