package fi.palvelunohjelmointi.workoutdiary.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.palvelunohjelmointi.workoutdiary.domain.Workout;
import fi.palvelunohjelmointi.workoutdiary.domain.WorkoutRepository;
import fi.palvelunohjelmointi.workoutdiary.domain.MovementRepository;

@Controller
public class WorkoutController {
	
	@Autowired
	private WorkoutRepository workoutRepository;
	
	@Autowired
	private MovementRepository movementRepository;
	
	@RequestMapping(value = "/workoutlist", method = RequestMethod.GET)
    public String workoutList(Model model) {	
		System.out.println(workoutRepository.findAll());
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
	public String saveWorkout(Workout workout) {
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

}
