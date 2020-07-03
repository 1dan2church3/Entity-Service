package com.dannytech.Insure.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long address_id;
	private String streetAddress;
	@ManyToOne
	@JoinColumn(name = "city_id")
	private City city;
	@ManyToOne
	@JoinColumn(name = "state_id")
	private State state;
	@ManyToOne
	@JoinColumn(name = "zip_id")
	private Zip zip;
}
