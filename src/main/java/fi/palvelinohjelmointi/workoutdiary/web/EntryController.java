package fi.palvelinohjelmointi.workoutdiary.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.palvelinohjelmointi.workoutdiary.domain.Entry;
import fi.palvelinohjelmointi.workoutdiary.domain.EntryRepository;
import fi.palvelinohjelmointi.workoutdiary.domain.User;
import fi.palvelinohjelmointi.workoutdiary.domain.UserRepository;
import fi.palvelinohjelmointi.workoutdiary.domain.WorkoutRepository;

@Controller
public class EntryController {
	
	@Autowired
	private EntryRepository entryRepository;
	
	@Autowired
	private WorkoutRepository workoutRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value = "/entrylist", method = RequestMethod.GET)
    public String entryList(@AuthenticationPrincipal UserDetails userDetails, Model model) {	
		User user = userRepository.findByUsername(userDetails.getUsername());
        model.addAttribute("entries", entryRepository.findByUser(user));
  
        return "entrylist";
    }
	
    @RequestMapping(value = "/addEntry")
    public String addEntry(@AuthenticationPrincipal UserDetails userDetails, Model model){
    	Entry entry = new Entry();
    	User user = userRepository.findByUsername(userDetails.getUsername());
    	entry.setUser(user);
    	model.addAttribute("entry", entry);
        model.addAttribute("workouts", workoutRepository.findAll());
        return "addentry";
    }   
    
	@PostMapping("saveEntry")
	public String saveEntry(@Valid Entry entry, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("workouts", workoutRepository.findAll());
			return "addEntry";
		}
		entryRepository.save(entry);
		return "redirect:entrylist";
	}
	
    @RequestMapping(value = "/editEntry/{id}", method = RequestMethod.GET)
    public String editEntry(@AuthenticationPrincipal UserDetails userDetails, @PathVariable("id") Long entryId, Model model) {
    	Entry entry = entryRepository.findById(entryId).get();
    	User user = userRepository.findByUsername(userDetails.getUsername());
    	entry.setUser(user);
    	model.addAttribute("entry", entry);
        model.addAttribute("workouts", workoutRepository.findAll());
        return "editentry";
    } 
    
    @RequestMapping(value = "/deleteEntry/{id}", method = RequestMethod.GET)
    public String deleteEntry(@PathVariable("id") Long entryId, Model model) {
    	entryRepository.deleteById(entryId);
        return "redirect:../entrylist";
    }  

}
