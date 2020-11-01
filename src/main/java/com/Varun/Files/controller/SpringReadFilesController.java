package com.Varun.Files.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Varun.Files.models.Vendor;
import com.Varun.Files.service.SpringReadFileService;



@Controller
public class SpringReadFilesController {

	@Autowired
	private SpringReadFileService springReadFileService;
	
	@GetMapping(value = "/")
	public String home(Model model) {
		model.addAttribute("vendor",new Vendor());
		List<Vendor> vendors=springReadFileService.findAll();
		model.addAttribute("vendors", vendors);
		
		return "vendors";
	}
	@PostMapping(value="/fileupload")
	public String uploadFiles(@ModelAttribute Vendor vendor, RedirectAttributes redirectAttributes) {
		boolean isFlag= (springReadFileService).saveDataFromUploadfile(vendor.getFile());
		if(isFlag) {
			redirectAttributes.addFlashAttribute("successmessage","File Upload Successfully");
		}else {
			redirectAttributes.addFlashAttribute("error","File is not upload successfully");
		}
		
		
		
		return "redirect:/";
		
	}
}
