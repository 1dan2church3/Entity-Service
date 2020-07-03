package com.dannytech.Insure.pojo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Zip {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long zip_id;
	private String name;
	@ManyToOne
	@JoinColumn(name = "state_id")
	private State state;
	@OneToMany(mappedBy = "zip")
	private List<Address> address;
	@OneToMany(mappedBy = "zip")
	private List<City> city;
}
