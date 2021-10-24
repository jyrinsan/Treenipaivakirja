package fi.palvelinohjelmointi.workoutdiary.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.palvelinohjelmointi.workoutdiary.domain.MovementRepository;
import fi.palvelinohjelmointi.workoutdiary.domain.Workout;
import fi.palvelinohjelmointi.workoutdiary.domain.WorkoutRepository;

@Controller
public class WorkoutController {
	
	@Autowired
	private WorkoutRepository workoutRepository;
	
	@Autowired
	private MovementRepository movementRepository;
	
	@RequestMapping(value = "/workoutlist", method = RequestMethod.GET)
    public String workoutList(Model model) {	
        model.addAttribute("workouts", workoutRepository.findAll());
  
        return "workoutlist";
    }
	
	@PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/addWorkout")
    public String addWorkout(Model model){
    	model.addAttribute("workout", new Workout());
    	model.addAttribute("movements", movementRepository.findAll());
        return "addworkout";
    }   
    
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("saveWorkout")
	public String saveWorkout(@Valid Workout workout, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("movements", movementRepository.findAll());
			return "addWorkout";
		}
		workoutRepository.save(workout);
		return "redirect:workoutlist";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/editWorkout/{id}", method = RequestMethod.GET)
    public String editWorkout(@PathVariable("id") Long workoutId, Model model) {
    	Workout workout= workoutRepository.findById(workoutId).get();
    	model.addAttribute("workout", workout);
    	model.addAttribute("movements", movementRepository.findAll());
        return "editworkout";
    } 
	
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/deleteWorkout/{id}", method = RequestMethod.GET)
    public String deleteWorkout(@PathVariable("id") Long workoutId, Model model) {
    	workoutRepository.deleteById(workoutId);
        return "redirect:../workoutlist";
    }   

}
