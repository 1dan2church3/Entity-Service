package com.dannytech.Insure.pages;

import java.util.ArrayList;
import java.util.List;

import org.geonames.PostalCode;
import org.springframework.beans.factory.annotation.Autowired;

import com.dannytech.Insure.pojo.Address;
import com.dannytech.Insure.pojo.Insurance;
import com.dannytech.Insure.repository.AddressRepository;
import com.dannytech.Insure.repository.InsuranceRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.Route;

@Route("Insurance")
public class InsurancePage extends VerticalLayout {

	private static final long serialVersionUID = 1L;
	Binder<Insurance> binder = new Binder<>(Insurance.class);
	Insurance insurance = new Insurance();

	@Autowired
	public InsurancePage(InsuranceRepository insuranceRepository, AddressRepository addressRepository) {

		FormLayout form = new FormLayout();
		form.setWidth("300px");
		insurance.setAddress(new Address());

		TextField name = new TextField("Name");
		binder.forField(name).asRequired("Name is required.").bind(Insurance::getName, Insurance::setName);

		TextField street = new TextField("Street Address");
		binder.bind(street, "address.streetAddress");

		Select<String> state = new Select<String>("Montana");
		binder.bind(state, "address.state");
		state.setPlaceholder("Please Select...");
		state.setLabel("State");

		Select<String> city = new Select<String>("Helena");
		binder.bind(city, "address.city");
		city.setPlaceholder("Please Select...");
		city.setLabel("City");

		Select<String> zip = new Select<String>();
		binder.bind(zip, "address.zip");
		zip.setPlaceholder("Please Select...");
		zip.setLabel("Zip");
		PostalCode postalCode = new PostalCode();
		zip.setItems(postalCode.getAdminCode1());

		Button save = new Button("Add Insurance");
		save.addClickListener(e -> {
			try {
				binder.writeBean(insurance);
				insuranceRepository.save(insurance);
				new Notification("Insurance added!").open();
			} catch (ValidationException e1) {
				new Notification("An error occurred.").open();
				e1.printStackTrace();
			}
		});

		form.add(name, street, state, city, zip, save);

		Grid<Insurance> grid = new Grid<>(Insurance.class);
		grid.removeColumnByKey("id");
		grid.setColumns("name", "address.streetAddress");
		List<Insurance> list = new ArrayList<Insurance>();
		insuranceRepository.findAll().forEach(list::add);
		grid.setItems(list);
		add(form, grid);
	}
}
