package fi.palvelinohjelmointi.workoutdiary.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fi.palvelinohjelmointi.workoutdiary.domain.Entry;
import fi.palvelinohjelmointi.workoutdiary.domain.EntryRepository;
import fi.palvelinohjelmointi.workoutdiary.domain.Movement;
import fi.palvelinohjelmointi.workoutdiary.domain.MovementRepository;
import fi.palvelinohjelmointi.workoutdiary.domain.Workout;
import fi.palvelinohjelmointi.workoutdiary.domain.WorkoutRepository;

@RestController
public class RestWorkoutController {
	
	@Autowired
	private EntryRepository entryRepository;
	
	@Autowired
	private WorkoutRepository workoutRepository;
	
	@Autowired
	private MovementRepository movementRepository;
	
	@GetMapping(value = "/entries")
    public List<Entry> entryListRest() {	
        return (List<Entry>)entryRepository.findAll();
    }
	
    @GetMapping(value="/entries/{id}")
    public Optional<Entry> findEntryRest(@PathVariable("id") Long entryId) {	
    	return entryRepository.findById(entryId);
    }  
	
	@GetMapping(value = "/workouts")
    public List<Workout> workoutListRest() {	
        return (List<Workout>)workoutRepository.findAll();
    }
	
    @GetMapping(value="/workouts/{id}")
    public Optional<Workout> findWorkoutRest(@PathVariable("id") Long workoutId) {	
    	return workoutRepository.findById(workoutId);
    }  
	
	@GetMapping(value = "/movements")
    public List<Movement> movementListRest() {	
        return (List<Movement>)movementRepository.findAll();
    }
	
    @GetMapping(value="/movements/{id}")
    public Optional<Movement> findMovementRest(@PathVariable("id") Long movementId) {	
    	return movementRepository.findById(movementId);
    }  
	

}
