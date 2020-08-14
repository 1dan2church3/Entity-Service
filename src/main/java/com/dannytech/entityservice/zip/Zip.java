package com.dannytech.entityservice.zip;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Zip {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long zip_id;
	private String code;
	private String state;
	private String city;
}
