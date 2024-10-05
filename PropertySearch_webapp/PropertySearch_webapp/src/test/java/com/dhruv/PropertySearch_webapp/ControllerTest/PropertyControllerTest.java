package com.dhruv.PropertySearch_webapp.ControllerTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.dhruv.PropertySearchLogin_webapp.Repository.OwnerRepository;
import com.dhruv.PropertySearchLogin_webapp.Repository.PropertyRepository;
import com.dhruv.PropertySearchLogin_webapp.Repository.TypeRepository;
import com.dhruv.PropertySearchLogin_webapp.Service.Post_Delete_Service;
import com.dhruv.PropertySearchLogin_webapp.Service.Post_Delete_ServiceImpl;
import com.dhruv.PropertySearchLogin_webapp.controller.PropertyController;
import com.dhruv.PropertySearchLogin_webapp.entity.propertymodel.Owner;
import com.dhruv.PropertySearchLogin_webapp.entity.propertymodel.Property;
import com.dhruv.PropertySearchLogin_webapp.entity.propertymodel.Type;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class PropertyControllerTest {

    @Mock
    private OwnerRepository ownerRepo;

    @Mock
    private PropertyRepository propertyRepo;

    @Mock
    private TypeRepository typeRepo;

    @Mock
    private Post_Delete_Service pdservice;

    @InjectMocks
    private PropertyController propertyController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddType() {
        
        Type type = new Type();

        when(pdservice.addType(type)).thenReturn(type);

        
        ResponseEntity<Type> response = propertyController.addType(type);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(type, response.getBody());
        verify(pdservice, times(1)).addType(type);
    }

    @Test
    public void testGetType() {
        
        int typeId = 1;
        Type type = new Type();

        when(pdservice.getType(typeId)).thenReturn(type);

        
        Type result = propertyController.getType(typeId);

        
        assertNotNull(result);
        assertEquals(type, result);
        verify(pdservice, times(1)).getType(typeId);
    }

    @Test
    public void testGetTypes() {
        
        Set<Type> types = new HashSet<>();

        when(pdservice.getTypes()).thenReturn(types);

        
        ResponseEntity<?> response = propertyController.getTypes();

        
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(types, response.getBody());
        verify(pdservice, times(1)).getTypes();
    }
    
    @Test
    public void testAddProperty() {
        
        Property prop = new Property();

        when(pdservice.addProperty(prop)).thenReturn(prop);

        
        ResponseEntity<Property> response = propertyController.add(prop);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(prop, response.getBody());
        verify(pdservice, times(1)).addProperty(prop);
    }
    
    @Test
    public void testGetProperty() {
        
        int propId = 11;
        Property prop = new Property();

        when(pdservice.getProperty(propId)).thenReturn(prop);

        
        Property result = propertyController.property(propId);

        
        assertNotNull(result);
        assertEquals(prop, result);
        verify(pdservice, times(1)).getProperty(propId);
    }
    
    @Test
    public void Search_test() 
    {
    	String search = "cityname";
    	List<Property> prop = new ArrayList<>();
    	when(propertyRepo.getPropInformationByQuery(search)).thenReturn(prop);
    	
    	List<Property> result = propertyController.getPropinfoByQuery(search);
    	assertEquals(prop, result);
    	
    	verify(pdservice,times(1)).searchpropertybyQuery(search);
    }
    
    @Test
    public void GetOwnerOfProperty_test() 
    {
    	int propid = 12;
    	Property prop = new Property(); 
    			
    	Owner owner = new Owner();
        Set<Owner> owners = prop.getOwner();
    	
        
    	when(pdservice.getProperty(propid)).thenReturn(prop);
    	when(pdservice.getOwners()).thenReturn(owners);
    
    	owners.add(owner);
     	List<Owner> list = new ArrayList<>();
     	list.addAll(owners);

    	ResponseEntity<?> response = propertyController.getOwnerOfProperty(propid);
    	assertEquals(HttpStatus.OK, response.getStatusCode());
    	assertEquals(list, response.getBody());
  
    }


}


