package com.dhruv.PropertySearchLogin_webapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dhruv.PropertySearchLogin_webapp.entity.propertymodel.Property;
import com.dhruv.PropertySearchLogin_webapp.entity.propertymodel.Owner;

import java.util.List;
import java.util.Set;

public interface OwnerRepository extends JpaRepository<Owner,Integer> {

	Set<Owner> findByProperty(Property property);

//@Query("SELECT new com.dhruv.PropertySearch_webapp.dto.OrderResponse(o.ownername , o.contactno ,p.propertyAddress) "
		//+ "FROM Owner o JOIN o.properties p WHERE p.type=:l")
   //public List<OrderResponse> getJoinInformation(@Param("l")String type);

//@Query("SELECT new com.dhruv.PropertySearch_webapp.dto.OrderResponse(o.ownername , o.contactno ,p.propertyAddress) "
	//	+ "FROM Owner o JOIN o.properties p WHERE p.city=:c")
  /// public List<OrderResponse> getPropInformation(@Param("c")String city);

//@Query("SELECT new com.dhruv.PropertySearch_webapp.dto.OrderResponseByTypeByCity(o.ownername , o.contactno ,p.propertyAddress,"
	///	+ "p.type,p.area) "
	///	+ "FROM Owner o JOIN o.properties p WHERE p.city=:c and p.type=:t")
   //public List<OrderResponseByTypeByCity> getPropInformationByTypeByCity(@Param("c")String city,@Param("t")String type);
	
	//@Query("FROM Owner o JOIN o.property p WHERE p.city=:c or o.ownername=:c")
	   //public List<Owner> getPropInformationByTypeByCity(@Param("c")String query);
		
	//@Query("FROM Owner o JOIN o.property p JOIN  p.type t where p.city=:c or t.type_name=:c")
	 //  public List<Owner> getPropInformationByQuery(@Param("c")String query);
		
	//@Query("FROM Owner o JOIN o.property p JOIN  p.type t where p.city=:c and t.type_name=:t")
	 //  public List<Owner> getPropInformationByTwoQuery(@Param("c")String city,@Param("t")String type);
}
