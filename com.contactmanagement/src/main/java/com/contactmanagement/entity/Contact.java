package com.contactmanagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="contact_datails")
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int id;
	
	@Column(name="first_name")
	private String firstname;
	
	@Column(name="last_name")
	private String lastname;
	
	@Column(name="mobile_number")
	private long mobileNumber;
	
	@Column(name="email_id")
	private String email;
	
	@Column(name="company_name")
	private String company;
	
	@Column(name="contact_group")
	private String group;
}