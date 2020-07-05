package com.dannytech.Insure.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.dannytech.Insure.pojo.Zip;

@RepositoryRestResource
public interface ZipRepository extends PagingAndSortingRepository<Zip, Long> {

	Zip findByCode(String code);
}
