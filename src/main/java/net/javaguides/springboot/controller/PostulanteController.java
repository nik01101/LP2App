package net.javaguides.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.javaguides.springboot.model.Postulante;
import net.javaguides.springboot.service.PostulanteService;

@Controller
public class PostulanteController {

	@Autowired
	private PostulanteService postulanteService;
	
	// display list of employees
	@GetMapping("/")
	public String viewHomePage(Model model) {
		return findPaginated(1, "nombres", "asc", model);		
	}
	
	@GetMapping("/mostrarPostulanteForm")
	public String mostrarPostulanteForm(Model model) {
		// create model attribute to bind form data
		Postulante postulante = new Postulante();
		model.addAttribute("postulante", postulante);
		return "postular_form";
	}
	
	@PostMapping("/savePostulante")
	public String savePostulante(@ModelAttribute("postulante") Postulante postulante) {
		// save employee to database
		postulanteService.savePostulante(postulante);
		return "redirect:/";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {
		
		// get employee from the service
		Postulante postulante = postulanteService.getPostulanteById(id);
		
		// set employee as a model attribute to pre-populate the form
		model.addAttribute("postulante", postulante);
		return "editar_form";
	}
	
	@GetMapping("/deletePostulante/{id}")
	public String deleteEmployee(@PathVariable (value = "id") long id) {
		
		// call delete employee method 
		this.postulanteService.deletePostulanteById(id);
		return "redirect:/";
	}
	
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<Postulante> page = postulanteService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Postulante> listPostulantes = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listEmployees", listPostulantes);
		return "index";
	}
}
