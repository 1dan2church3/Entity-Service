package com.dannytech.Insure.pojo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class State {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long state_id;
	private String name;
	@OneToMany(mappedBy = "state")
	private List<Zip> zips;
	@OneToMany(mappedBy = "state")
	private List<City> cities;
	@OneToMany(mappedBy = "state")
	private List<Address> addresses;
}
