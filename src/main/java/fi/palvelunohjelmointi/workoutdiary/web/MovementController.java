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
import fi.palvelunohjelmointi.workoutdiary.domain.Movement;
import fi.palvelunohjelmointi.workoutdiary.domain.MovementRepository;

@Controller
public class MovementController {
	
	@Autowired
	private MovementRepository movementRepository;
	
	@RequestMapping(value = "/movementlist", method = RequestMethod.GET)
    public String movementList(Model model) {	
        model.addAttribute("movements", movementRepository.findAll());
  
        return "movementlist";
    }
	
	@PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/addMovement")
    public String addMovement(Model model){
    	model.addAttribute("movement", new Movement());
        return "addmovement";
    }   
    
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("saveMovement")
	public String saveMovement(Movement movement) {
		movementRepository.save(movement);
		return "redirect:movementlist";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/editMovement/{id}", method = RequestMethod.GET)
    public String editMovement(@PathVariable("id") Long movementId, Model model) {
    	Movement movement= movementRepository.findById(movementId).get();
    	model.addAttribute("movement", movement);
        return "editmovement";
    } 

}
