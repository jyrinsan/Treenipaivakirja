package fi.palvelunohjelmointi.exercisediary.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.palvelunohjelmointi.exercisediary.domain.Exercise;
import fi.palvelunohjelmointi.exercisediary.domain.ExerciseRepository;
import fi.palvelunohjelmointi.exercisediary.domain.MovementRepository;
import s2021.bookstoreFinal.domain.Book;

@Controller
public class ExerciceController {
	
	@Autowired
	private ExerciseRepository exerciseRepository;
	
	@Autowired
	private MovementRepository movementRepository;
	
	@RequestMapping(value = "/exerciselist", method = RequestMethod.GET)
    public String exerciseList(Model model) {	
		System.out.println(exerciseRepository.findAll());
        model.addAttribute("exercises", exerciseRepository.findAll());
  
        return "exerciselist";
    }
	
    @RequestMapping(value = "/add")
    public String addBook(Model model){
    	model.addAttribute("exercise", new Exercise());
    	model.addAttribute("movements", movementRepository.findAll());
        return "addexercise";
    }   
    
	@PostMapping("save")
	public String saveBook(Exercise exercise) {
		System.out.println("Saving exercise: "+exercise);
		exerciseRepository.save(exercise);
		return "redirect:exerciselist";
	}

}
