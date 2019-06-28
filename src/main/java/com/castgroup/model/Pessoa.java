package com.castgroup.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Pessoa {
	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	private String street;
	private String neighborhood;
	private String city;
	private String state;
	private String cellphone;
	private String phone;
	
}
