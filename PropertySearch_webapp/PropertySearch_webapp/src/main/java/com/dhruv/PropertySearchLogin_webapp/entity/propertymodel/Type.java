package com.dhruv.PropertySearchLogin_webapp.entity.propertymodel;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Type {
    
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int tid;
	private String type_name;
	private int bedrooms;
	private String furnished;
	private Boolean tax_paid;
    //to avoid cyclic dependency between two tables, we use json ignore
    @OneToMany(mappedBy="type" , cascade= CascadeType.ALL)
    @JsonIgnore
    private Set<Property> properties = new LinkedHashSet<>();
	public Type() {
		
	}
	
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	public int getBedrooms() {
		return bedrooms;
	}
	public void setBedrooms(int bedrooms) {
		this.bedrooms = bedrooms;
	}
	public String getFurnished() {
		return furnished;
	}
	public void setFurnished(String furnished) {
		this.furnished = furnished;
	}
	public Boolean getTax_paid() {
		return tax_paid;
	}
	public void setTax_paid(Boolean tax_paid) {
		this.tax_paid = tax_paid;
	}
	public Set<Property> getProperties() {
		return properties;
	}
	public void setProperties(Set<Property> properties) {
		this.properties = properties;
	}
	@Override
	public String toString() {
		return "Type [tid=" + tid + ", type_name=" + type_name + ", bedrooms=" + bedrooms + ", furnished=" + furnished
				+ ", tax_paid=" + tax_paid + ", properties=" + properties + "]";
	}
	
	
}
