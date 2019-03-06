package com.interswitchgroup.internship.samples.transaction.service.api.models;

public class Agent extends BaseEntity {
	//private Integer id;
	private String firstname;
	private String lastname;
	private int category;
	private String address;
	//private String dateRegistered;
	private String code;
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
//	public String getDateRegistered() {
//		return dateRegistered;
//	}
//	public void setDateRegistered(String dateRegistered) {
//		this.dateRegistered = dateRegistered;
//	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}
