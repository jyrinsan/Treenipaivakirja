package fi.palvelinohjelmointi.workoutdiary;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.palvelinohjelmointi.workoutdiary.web.EntryController;
import fi.palvelinohjelmointi.workoutdiary.web.MovementController;
import fi.palvelinohjelmointi.workoutdiary.web.WorkoutController;

@RunWith(SpringRunner.class)
@SpringBootTest
class WorkoutApplicationTests {
	
	@Autowired
	private EntryController entryController;
	
	@Autowired
	private WorkoutController workoutController;
	
	@Autowired
	private MovementController movementController;
	
	@Test
	void contextLoads() {
		assertNotNull(entryController);
		assertNotNull(workoutController);
		assertNotNull(movementController);
	}

}
