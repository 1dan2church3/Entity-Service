package com.dannytech.entityservice.zip;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface ZipRepository extends PagingAndSortingRepository<Zip, Long> {

	Zip findByCode(String code);
}
