package com.dhruv.PropertySearchLogin_webapp.Service;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.dhruv.PropertySearchLogin_webapp.entity.propertymodel.Property;
import com.dhruv.PropertySearchLogin_webapp.entity.propertymodel.Owner;
import com.dhruv.PropertySearchLogin_webapp.entity.propertymodel.Type;
import com.dhruv.PropertySearchLogin_webapp.Repository.*;

@Service
public class Post_Delete_ServiceImpl implements Post_Delete_Service {
   @Autowired
	private OwnerRepository ownerrepo;
   
   @Autowired
    private PropertyRepository proprepo;
   
   @Autowired
   private TypeRepository typerepo;
    
   @Autowired
	public Post_Delete_ServiceImpl(OwnerRepository ownerrepo, PropertyRepository proprepo,TypeRepository typerepo) {
	this.ownerrepo = ownerrepo;
	this.proprepo = proprepo;
	this.typerepo = typerepo;
}
 

	@Override
	public Owner findById(int theId) {
		 return this.ownerrepo.findById(theId).get();
	}


	@Override
	public Owner addOwner(Owner owner) {
		return this.ownerrepo.save(owner);
	}


	@Override
	public Owner updateOwner(Owner owner) {
		return this.ownerrepo.save(owner);
	}


	@Override
	public void deleteOwner(int theId) {
		  Owner owner = new Owner();
	        owner.setId(theId);
	        this.ownerrepo.delete(owner);
	
	}


	@Override
	public Set<Owner> getOwners() {

		return new LinkedHashSet<>(this.ownerrepo.findAll());
	}

// service for property
	
	@Override
	public Property addProperty(Property property) {
		// TODO Auto-generated method stub
		return this.proprepo.save(property);
	}


	@Override
	public Property updateProperty(Property property) {
		// TODO Auto-generated method stub
		return  this.proprepo.save(property);
	}


	@Override
	public Set<Property> getProperties() {
		return new HashSet<>(this.proprepo.findAll());
	}


	@Override
	public Property getProperty(int propId) {
		return this.proprepo.findById(propId).get();
	}


	@Override
	public void deleteProperty(int propId) {
		this.proprepo.deleteById(propId);	
	}


	// service for type of property
	@Override
	public Type addType(Type type) {
		// TODO Auto-generated method stub
		return this.typerepo.save(type);
	}


	@Override
	public Type updateType(Type type) {
		// TODO Auto-generated method stub
		return this.typerepo.save(type);
	}


	@Override
	public Set<Type> getTypes() {
		// TODO Auto-generated method stub
		return new HashSet<>(this.typerepo.findAll());
	}


	@Override
	public Type getType(int typeId) {
		
		return this.typerepo.findById(typeId).get();
	}


	@Override
	public Set<Owner> getOwnerOfProperty(Property property) {
		// TODO Auto-generated method stub
		return this.ownerrepo.findByProperty(property);
	}
	
	
	@Override
    public void deleteType(int typeId) {
        Type type = new Type();
         type.setTid(typeId);
        this.typerepo.delete(type);
    }

	@Override
	public List<Property> searchpropertybyQuery(String query) {
		List<Property> property = proprepo.getPropInformationByQuery(query);
		return property;
	}


	@Override
	public Page<Property> findPropertiesWithPagination(int offset, int pageSize) {
		 Page<Property> properties = proprepo.findAll(PageRequest.of(offset, pageSize));
	     return  properties;
	}


}
