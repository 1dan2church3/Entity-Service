package com.dannytech.Insure.pages;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import com.dannytech.Insure.pojo.Address;
import com.dannytech.Insure.pojo.Insurance;
import com.dannytech.Insure.pojo.Zip;
import com.dannytech.Insure.repository.AddressRepository;
import com.dannytech.Insure.repository.InsuranceRepository;
import com.dannytech.Insure.repository.ZipRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
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
	public InsurancePage(InsuranceRepository insuranceRepo, AddressRepository addressRepo, ZipRepository zipRepo) {

		FormLayout form = new FormLayout();
		form.setWidth("300px");
		insurance.setAddress(new Address());

		TextField name = new TextField("Name");
		binder.forField(name).asRequired("Name is required.").bind(Insurance::getName, Insurance::setName);

		TextField street = new TextField("Street Address");
		binder.bind(street, "address.streetAddress");

		TextField state = new TextField("State");
		state.setReadOnly(true);

		TextField city = new TextField("City");
		city.setReadOnly(true);

		ComboBox<Zip> zip = new ComboBox<Zip>();
		binder.forField(zip).bind("address.zip");
		zip.setPlaceholder("Please Select...");
		zip.setLabel("Zip");
		List<Zip> zips = new ArrayList<Zip>();
		zipRepo.findAll(Sort.by("code")).forEach(zips::add);
		zip.setItems(zips);
		zip.setItemLabelGenerator(event -> event.getCode());
		zip.addValueChangeListener(e -> {

			state.setValue(zipRepo.findByCode(e.getValue().getCode()).getState());
			city.setValue(zipRepo.findByCode(e.getValue().getCode()).getCity());
		});

		Button save = new Button("Add Insurance");
		save.addClickListener(e -> {
			try {
				binder.writeBean(insurance);
				insuranceRepo.save(insurance);
				new Notification("Insurance added!").open();
			} catch (ValidationException e1) {
				new Notification("An error occurred.").open();
				e1.printStackTrace();
			}
		});

		form.add(name, street, zip, city, state, save);

		Grid<Insurance> grid = new Grid<>(Insurance.class);
		grid.removeColumnByKey("id");
		grid.setColumns("name", "address.streetAddress");
		List<Insurance> list = new ArrayList<Insurance>();
		insuranceRepo.findAll().forEach(list::add);
		grid.setItems(list);
		add(form, grid);
	}
}
