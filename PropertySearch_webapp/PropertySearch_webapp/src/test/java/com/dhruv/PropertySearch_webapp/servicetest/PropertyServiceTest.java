package com.dhruv.PropertySearch_webapp.servicetest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.dhruv.PropertySearchLogin_webapp.Repository.OwnerRepository;
import com.dhruv.PropertySearchLogin_webapp.Repository.PropertyRepository;
import com.dhruv.PropertySearchLogin_webapp.Repository.TypeRepository;
import com.dhruv.PropertySearchLogin_webapp.Service.Post_Delete_ServiceImpl;
import com.dhruv.PropertySearchLogin_webapp.entity.propertymodel.Owner;
import com.dhruv.PropertySearchLogin_webapp.entity.propertymodel.Property;
import com.dhruv.PropertySearchLogin_webapp.entity.propertymodel.Type;

public class PropertyServiceTest {

	@Mock
	private OwnerRepository ownerRepo;
	
	@Mock
	private PropertyRepository propertyRepo;
	
	@Mock
	private TypeRepository typeRepo;
	
	@InjectMocks
	private Post_Delete_ServiceImpl service;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	@DisplayName("finding owner service functionality")
	public void OwnerFind_test() 
	{
		int ownerId = 12;
		Owner owner = new Owner();
		when(ownerRepo.findById(ownerId)).thenReturn(Optional.of(owner));
		
		Owner actual_result = service.findById(ownerId);
		
		assertEquals(owner, actual_result);
		verify(ownerRepo).findById(ownerId);
	}
	
	@Test
	public void AddOwner_test() 
	{
	
		Owner owner = new Owner();
		owner.setOwnername("Bharat");
		owner.setContactno(9872133445L);
		owner.setOwneremail("bharat15@gmail.com");
		when(ownerRepo.save(owner)).thenReturn(owner);
		
		Owner actual_result = service.addOwner(owner);
		
		assertEquals(owner, actual_result);
		verify(ownerRepo).save(owner);
	}
	
	@Test
	public void AddProperty_test()
	{
		Property prop = new Property();
		prop.setPropertyAddress("Kalkaji Mandir");
		prop.setArea(800);
		prop.setCity("Delhi");
		prop.setPrice(6000000);
		prop.setType(new Type());
		when(propertyRepo.save(prop)).thenReturn(prop);
		
		Property actual_result = service.addProperty(prop);
		assertEquals(prop,actual_result);
		verify(propertyRepo).save(prop);
	}
	
	@Test
	public void GetOwnerOfProperty_test()
	{
		
		Owner owner = new Owner();
		owner.setOwnername("Bharat");
		owner.setContactno(9872133445L);
		owner.setOwneremail("bharat15@gmail.com");
		
		Set<Owner> owners = new HashSet<>();
		owners.add(owner);
		Property prop = new Property();
		prop.setPropertyAddress("Kalkaji Mandir");
		prop.setArea(800);
		prop.setCity("Delhi");
		prop.setPrice(6000000);
		prop.setType(new Type());
		prop.setOwner(owners);
		
		when(ownerRepo.findByProperty(prop)).thenReturn(owners);
		when(ownerRepo.save(owner)).thenReturn(owner);
		when(propertyRepo.save(prop)).thenReturn(prop);
	
		Set<Owner> actual_result = service.getOwnerOfProperty(prop);
		assertEquals(owners,actual_result);
		verify(ownerRepo).findByProperty(prop);
		
	}
	
    @Test
    public void testUpdateProperty() {
        Property property = new Property();
        when(propertyRepo.save(property)).thenReturn(property);
        assertEquals(property, service.updateProperty(property));
    }
}
