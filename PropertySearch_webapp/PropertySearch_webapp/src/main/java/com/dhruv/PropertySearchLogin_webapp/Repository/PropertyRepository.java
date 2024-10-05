package com.dhruv.PropertySearchLogin_webapp.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.dhruv.PropertySearchLogin_webapp.entity.propertymodel.Property;

public interface PropertyRepository extends JpaRepository<Property,Integer> {
	@Query("FROM Property p JOIN  p.type t where p.city=:c or t.type_name=:c")
	List<Property> getPropInformationByQuery(@Param("c")String query);

	///@Query("SELECT p FROM Property p WHERE p.city=:p AND p.type=:l")
	///List<Property> searchpropertybyCityandType(@Param("p") String city,@Param("l") String type);
	
	
}
