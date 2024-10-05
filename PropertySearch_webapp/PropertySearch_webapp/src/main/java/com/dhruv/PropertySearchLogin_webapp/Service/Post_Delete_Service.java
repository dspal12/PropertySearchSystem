package com.dhruv.PropertySearchLogin_webapp.Service;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;

import com.dhruv.PropertySearchLogin_webapp.entity.propertymodel.Property;
import com.dhruv.PropertySearchLogin_webapp.entity.propertymodel.Owner;
import com.dhruv.PropertySearchLogin_webapp.entity.propertymodel.Type;
public interface Post_Delete_Service {

	// List<Property> searchpropertybyCity(String city);

	// for owner
	public Owner addOwner(Owner owner);

	public Owner findById(int theId);

	public Owner updateOwner(Owner owner);

	public void deleteOwner(int theId);

	public Set<Owner> getOwners();

	public Set<Owner> getOwnerOfProperty(Property property);
	// Property
	public Property addProperty(Property property);

	public Property updateProperty(Property property);

	public Set<Property> getProperties();

	public Property getProperty(int propId);

	public void deleteProperty(int propId);
	
	//Type
    public Type addType(Type type);

    public Type updateType(Type type);

    public Set<Type> getTypes();

    public Type getType(int typeId);

    //public Set<Type> getTypesOfProperty(Property property);

	public void deleteType(int typeId);
	
	
//List<Owner> searchpropertybyCityOwner(String city);
	
	List<Property> searchpropertybyQuery(String query);
	
	 Page<Property> findPropertiesWithPagination(int offset,int pageSize);
	
	



}
