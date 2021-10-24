package fi.palvelinohjelmointi.workoutdiary.domain;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
//@AutoConfigureTestDatabase(replace = NONE)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class WorkoutRepositoryTest {

	@Autowired
	WorkoutRepository workoutRepository;
	
	@Autowired
	MovementRepository movementRepository;
	
	@Test
	public void findAllShouldReturnSize() {
		List<Workout> workouts = (List<Workout>) workoutRepository.findAll();
		
		Assertions.assertThat(workouts).hasSize(3);
	}

	@Test
	public void findAllShouldReturnName() {
		List<Workout> workouts = (List<Workout>) workoutRepository.findAll();
		Assertions.assertThat(workouts.get(0).getName()).isEqualTo("juoksutreeni");
	}
	
	@Test
	public void insertNewWorkout() {
		Workout workout = new Workout("uusitreeni",(List<Movement>)movementRepository.findAll());
		workoutRepository.save(workout);
		List<Workout> workouts = (List<Workout>) workoutRepository.findAll();
		Assertions.assertThat(workouts.get(3).getName()).isEqualTo("uusitreeni");
	}

}
