package com.Varun.Files.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Varun.Files.models.Vendor;

@Repository
public interface SpringReadFilesRepository extends CrudRepository<Vendor,Integer> {

}
