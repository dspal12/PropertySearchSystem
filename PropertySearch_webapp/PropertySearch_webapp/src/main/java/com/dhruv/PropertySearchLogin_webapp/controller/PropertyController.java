package com.dhruv.PropertySearchLogin_webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//import com.dhruv.PropertyOwnerType.dto.OrderRequest;
//import com.dhruv.PropertyOwnerType.dto.OrderResponse;
//import com.dhruv.PropertyOwnerType.dto.OrderResponseByTypeByCity;

import com.dhruv.PropertySearchLogin_webapp.Repository.*;
import com.dhruv.PropertySearchLogin_webapp.Service.*;
import com.dhruv.PropertySearchLogin_webapp.entity.propertymodel.Owner;
import com.dhruv.PropertySearchLogin_webapp.entity.propertymodel.Property;
import com.dhruv.PropertySearchLogin_webapp.entity.propertymodel.Type;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin("*")
public class PropertyController {
    @Autowired
    private OwnerRepository ownerRepo;
    @Autowired
    private PropertyRepository propertyRepo;
    
    @Autowired
    private TypeRepository typeRepo;
    
    @Autowired
    private Post_Delete_Service pdservice;
    
    

    public PropertyController(
			Post_Delete_Service pdservice) {
		this.ownerRepo = ownerRepo;
		this.propertyRepo = propertyRepo;
		this.typeRepo=typeRepo;
		this.pdservice = pdservice;
	}

	//@PostMapping("/postproperty")
  //  public Owner placeOrder(@RequestBody OrderRequest request){
   //    return ownerRepo.save(request.getOwner());
  //  }
    @PostMapping("/type/add")
    public ResponseEntity<Type> addType(@RequestBody Type type) {
        Type type1 = this.pdservice.addType(type);
        return ResponseEntity.ok(type1);
    }
    
    @GetMapping("/type/{typeId}")
    public Type getType(@PathVariable("typeId") int typeId) {
        return this.pdservice.getType(typeId);
    }
    
    @GetMapping("/type")
    public ResponseEntity<?> getTypes() {
        return ResponseEntity.ok(this.pdservice.getTypes());
    }

    //update type
    @PutMapping("/type/update")
    public Type updateType(@RequestBody Type type) {
        return this.pdservice.updateType(type);
    }

    //delete type
    @DeleteMapping("/type/{typeId}")
    public void deleteOwner(@PathVariable("typeId") int typeId) {
        this.pdservice.deleteType(typeId);
    }
    
    //add property service
    @PostMapping("/type/property/add")
    public ResponseEntity<Property> add(@RequestBody Property property) {
        return ResponseEntity.ok(this.pdservice.addProperty(property));
    }

    //update property
    @PutMapping("/type/property/update")
    public ResponseEntity<Property> update(@RequestBody Property property) {
        return ResponseEntity.ok(this.pdservice.updateProperty(property));
    }

    //get properties
    @GetMapping("/type/property")
    public ResponseEntity<?> properties() {
        return ResponseEntity.ok(this.pdservice.getProperties());
    }

    //get single property
    @GetMapping("/type/property/{propId}")
    public Property property(@PathVariable("propId") int propId) {
        return this.pdservice.getProperty(propId);
    }

    //delete the property
    @DeleteMapping("/type/property/{propId}")
    public void deleteProperty(@PathVariable("propId") int propId) {
        this.pdservice.deleteProperty(propId);
    }


    //add the property owner
    @PostMapping("/type/property/owner/add")
    public ResponseEntity<Owner> add(@RequestBody Owner owner) {
        return ResponseEntity.ok(this.pdservice.addOwner(owner));
    }

    //update the property owner
    @PutMapping("/type/property/owner/update")
    public ResponseEntity<Owner> update(@RequestBody Owner owner) {
        return ResponseEntity.ok(this.pdservice.updateOwner(owner));
    }

    //get owner of given property
    @GetMapping("/type/property/owner/{propId}")
    public ResponseEntity<?> getOwnerOfProperty(@PathVariable("propId") int propId) {
//        Quiz quiz = new Quiz();
//        quiz.setqId(qid);
//        Set<Question> questionsOfQuiz = this.service.getQuestionsOfQuiz(quiz);
//        return ResponseEntity.ok(questionsOfQuiz);

        Property property = this.pdservice.getProperty(propId);
        Set<Owner> owners = property.getOwner();
        List<Owner> list = new ArrayList(owners);
        Collections.shuffle(list);
        return ResponseEntity.ok(list);
    }

    //get single owner
    @GetMapping("/type/property-owner/{ownerId}")
    public Owner get(@PathVariable("ownerId") int ownerId) {
        return this.pdservice.findById(ownerId);
    }

    //delete owner
    @DeleteMapping("/type/property/owner/{ownerId}")
    public void deleteType(@PathVariable("ownerId") int ownerId) {
        this.pdservice.deleteOwner(ownerId);
    }

  //  @GetMapping("/type/property/owner/search")
   // public List<Owner> getPropandOwnerinfobyCity(@RequestParam("query")String query)
    //{return pdservice.searchpropertybyCityOwner(query);}

    @GetMapping("/type/property/search2")
    public List<Property> getPropinfoByQuery(@RequestParam("query")String query)
    {return pdservice.searchpropertybyQuery(query);}
    
    @GetMapping("/type/property/pagination")
    private Page<Property> getPropertiesWithPagination(@RequestParam("offset")int offset, @RequestParam("pageSize") int pageSize) {
        Page<Property> propertiesWithPagination = pdservice.findPropertiesWithPagination(offset, pageSize);
        return propertiesWithPagination;
    }

    //@GetMapping("/type/property/owner/search3")
    //public List<Owner> getPropinfoBytwoQuery(@RequestParam("city")String city,@RequestParam("type")String type)
    //{return pdservice.searchpropertybytwoQuery(city, type);}
  
}

