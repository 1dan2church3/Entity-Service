package com.dannytech.Insure.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.dannytech.Insure.pojo.Insurance;

@RepositoryRestResource
public interface InsuranceRepository extends PagingAndSortingRepository<Insurance, Long> {

}
