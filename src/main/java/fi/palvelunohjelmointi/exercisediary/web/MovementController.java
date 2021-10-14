package fi.palvelunohjelmointi.exercisediary.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.palvelunohjelmointi.exercisediary.domain.MovementRepository;

@Controller
public class MovementController {
	
	@Autowired
	private MovementRepository movementRepository;
	
	@RequestMapping(value = "/movementlist", method = RequestMethod.GET)
    public String exerciseList(Model model) {	
        model.addAttribute("movements", movementRepository.findAll());
  
        return "movementlist";
    }

}
