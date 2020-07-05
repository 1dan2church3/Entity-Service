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
public class Zip {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long zip_id;
	private String code;
	private String state;
	private String city;
	@OneToMany(mappedBy = "zip")
	private List<Address> address;
}
