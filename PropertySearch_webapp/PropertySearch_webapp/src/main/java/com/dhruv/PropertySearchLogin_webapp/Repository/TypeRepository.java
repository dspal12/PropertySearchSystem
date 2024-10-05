package com.dhruv.PropertySearchLogin_webapp.Repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dhruv.PropertySearchLogin_webapp.entity.propertymodel.Type;

public interface TypeRepository extends JpaRepository<Type, Integer>{
	  //Set<Type> findByProperty(Property property);
}
