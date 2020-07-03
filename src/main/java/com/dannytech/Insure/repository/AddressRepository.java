package com.dannytech.Insure.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.dannytech.Insure.pojo.Address;

@RepositoryRestResource
public interface AddressRepository extends PagingAndSortingRepository<Address, Long> {

}
