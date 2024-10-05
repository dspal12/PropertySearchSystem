package com.dhruv.PropertySearchLogin_webapp.entity.propertymodel;



import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.LinkedHashSet;
//import java.util.List;
import java.util.Set;


@Entity
@Table(name="property_owner")
public class Owner {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private String ownername;
    private String owneremail;
    private long contactno;
    
	@ManyToOne(fetch=FetchType.EAGER)
	private Property property;

	public Owner() {
	
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOwnername() {
		return ownername;
	}

	public void setOwnername(String ownername) {
		this.ownername = ownername;
	}

	public String getOwneremail() {
		return owneremail;
	}

	public void setOwneremail(String owneremail) {
		this.owneremail = owneremail;
	}

	public long getContactno() {
		return contactno;
	}

	public void setContactno(long contactno) {
		this.contactno = contactno;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	@Override
	public String toString() {
		return "Owner [id=" + id + ", ownername=" + ownername + ", owneremail=" + owneremail + ", contactno="
				+ contactno + ", property=" + property + "]";
	}
    
    
	
	
    
}
