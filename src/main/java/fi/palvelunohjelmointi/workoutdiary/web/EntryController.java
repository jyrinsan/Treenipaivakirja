package fi.palvelunohjelmointi.workoutdiary.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.palvelunohjelmointi.workoutdiary.domain.Entry;
import fi.palvelunohjelmointi.workoutdiary.domain.EntryRepository;
import fi.palvelunohjelmointi.workoutdiary.domain.User;
import fi.palvelunohjelmointi.workoutdiary.domain.UserRepository;
import fi.palvelunohjelmointi.workoutdiary.domain.WorkoutRepository;

@Controller
public class EntryController {
	
	@Autowired
	private EntryRepository entryRepository;
	
	@Autowired
	private WorkoutRepository workoutRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value = "/entrylist", method = RequestMethod.GET)
    public String entryList(Model model) {	
        model.addAttribute("entries", entryRepository.findAll());
  
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
	public String saveEntry(Entry entry) {
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

}
