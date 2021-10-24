package fi.palvelinohjelmointi.workoutdiary.domain;

import org.springframework.data.repository.CrudRepository;

public interface WorkoutRepository extends CrudRepository<Workout, Long>{
	Workout findByName(String name);

}

