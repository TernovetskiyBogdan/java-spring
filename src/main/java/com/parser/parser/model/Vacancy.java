package com.parser.parser.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

@Data
@AllArgsConstructor
public class Vacancy {
    private String name;
    private String date;
    private String location;
    private String company;

    private String reference;
   
    public Vacancy(String name, String date, String location, String company, String reference) {
    	this.name = name; 
    	this.date = date;
    	this.location = location;
    	this.company = company;
    	this.reference = reference;
    }
    
    public String getName() {
    	return name;
    }
    
    public String getDate() {
    	return date;
    }
    
    public String getLocation() {
    	return location;
    }
    
    public String getCompany() {
    	return company;
    }
    
    public String getReference() {
    	return reference;
    }
}
