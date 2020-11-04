package com.Varun.Files.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.Varun.Files.models.Vendor;

public interface SpringReadFileService {

	List<Vendor> findAll();

	boolean saveDataFromUploadfile(MultipartFile file);

}
