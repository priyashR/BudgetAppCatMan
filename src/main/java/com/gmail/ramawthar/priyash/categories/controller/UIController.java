package com.gmail.ramawthar.priyash.categories.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gmail.ramawthar.priyash.categories.model.Categories;
import com.gmail.ramawthar.priyash.categories.services.CategoriesService;

@Controller
public class UIController {
	@Autowired
	CategoriesService categoriesService;
	
 	  @RequestMapping(value = "/processCSVFile", method = RequestMethod.POST)
	  //public ResponseEntity<Object> processFile(@Valid @RequestBody String input) {
	  	public ResponseEntity<Object> handleFileUpload(@RequestParam("file") MultipartFile file,
				RedirectAttributes redirectAttributes) {
		  System.out.println(categoriesService.processCSVFile(file));
		  redirectAttributes.addFlashAttribute("message",
					"You successfully uploaded " + file.getOriginalFilename() + "!");
			//logger.info("Controller call to fetchPath");
	    return new ResponseEntity<>(HttpStatus.OK);
	  }
 	   
 	 @GetMapping("/")
 	public String index(Model model) {
 		model.addAttribute("unCatCategories", categoriesService.getAllUncategorised());
 	    return "maintenance";
 	}

}
