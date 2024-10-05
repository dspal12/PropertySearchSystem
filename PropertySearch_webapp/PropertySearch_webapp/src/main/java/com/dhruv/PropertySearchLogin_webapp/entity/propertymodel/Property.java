package com.dhruv.PropertySearchLogin_webapp.entity.propertymodel;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;

//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int pid;
    private String propertyAddress;
    private String city;
    private double price;
    private int area;
	@ManyToOne(fetch=FetchType.EAGER)
    private Type type;
    
    @OneToMany(mappedBy="property",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
    @JsonIgnore
    private Set<Owner> owner = new LinkedHashSet<>();
    
    

	public Property() {
		
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getPropertyAddress() {
		return propertyAddress;
	}

	public void setPropertyAddress(String propertyAddress) {
		this.propertyAddress = propertyAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Set<Owner> getOwner() {
		return owner;
	}

	public void setOwner(Set<Owner> owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		return "Property [pid=" + pid + ", propertyAddress=" + propertyAddress + ", city=" + city + ", price=" + price
				+ ", area=" + area + ", type=" + type + ", owner=" + owner + "]";
	}
    
    
	
	
	
    
    
}
