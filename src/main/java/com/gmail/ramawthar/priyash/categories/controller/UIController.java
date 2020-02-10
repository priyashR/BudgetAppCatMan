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
	  	public String handleFileUpload(@RequestParam("file") MultipartFile file,
				RedirectAttributes redirectAttributes,
				Model model) {
		  System.out.println(categoriesService.processCSVFile(file,"N"));
		  redirectAttributes.addFlashAttribute("message",
					"You successfully uploaded " + file.getOriginalFilename() + "!");
			//logger.info("Controller call to fetchPath");
	 		setUpDate(model);
		    return "maintenance";
 	  }
 	  
 	  @RequestMapping(value = "/refreshAndProcessCSVFile", method = RequestMethod.POST)
	  //public ResponseEntity<Object> processFile(@Valid @RequestBody String input) {
	  	public String refreshAndHandleFileUpload(@RequestParam("file") MultipartFile file,
				RedirectAttributes redirectAttributes,
				Model model) {
 		  System.out.println("DELETING ALL CATERGORIES HERE!!!!");
		  System.out.println(categoriesService.processCSVFile(file,"Y"));
		  redirectAttributes.addFlashAttribute("message",
					"You successfully uploaded " + file.getOriginalFilename() + "!");
			//logger.info("Controller call to fetchPath");
	 		setUpDate(model);
		    return "maintenance";
 	  } 
 	  
 	  @RequestMapping(value = "/allocateParent", method = RequestMethod.POST) 
	  public String allocateParent(@RequestParam("category") String file,
			  									   @RequestParam("description") String description,
			  									   @RequestParam("parent") String parent,
				RedirectAttributes redirectAttributes,
				Model model) {
		  System.out.println("allocating parent "+parent);
		  redirectAttributes.addFlashAttribute("done!");
			//logger.info("Controller call to fetchPath");
	 		setUpDate(model);
	    return "maintenance";
	  }
 	   
 	 @GetMapping("/")
 	public String index(Model model) {
 		setUpDate(model);
 	    return "maintenance";
 	}
 	private void setUpDate(Model model){
 		List<Categories> uncat = categoriesService.getAllUncategorised();
 		for (Categories cat : uncat){
 			cat.setParent(cat.getParent()+" : "+categoriesService.getPath(cat.getCategory(),"X"));
 		}
 		model.addAttribute("unCatCategories", uncat);
 		

 		List<Categories> categories = categoriesService.getAllCategories();
 		for (Categories cat : categories){
 			cat.setParent(cat.getParent()+" : "+categoriesService.getPath(cat.getCategory(),"X"));
 		}
 		model.addAttribute("categories", categories);
 		
 	}

}
