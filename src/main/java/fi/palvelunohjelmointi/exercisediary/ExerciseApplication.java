package fi.palvelunohjelmointi.exercisediary;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.palvelunohjelmointi.exercisediary.domain.Exercise;
import fi.palvelunohjelmointi.exercisediary.domain.ExerciseRepository;
import fi.palvelunohjelmointi.exercisediary.domain.Movement;
import fi.palvelunohjelmointi.exercisediary.domain.MovementRepository;
import fi.palvelunohjelmointi.exercisediary.domain.User;
import fi.palvelunohjelmointi.exercisediary.domain.UserRepository;

@SpringBootApplication
public class ExerciseApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExerciseApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner exerciseDiary(ExerciseRepository eRepository, MovementRepository mRepository, UserRepository uRepository) {
		return (args) -> {			
			System.out.println("save a couple of exercices");
			Movement move1 = mRepository.save(new Movement("maastaveto",10));
			Movement move2 = mRepository.save(new Movement("rinnalleveto",10));
			List<Movement> moveList1 = new ArrayList<Movement> ();
			moveList1.add(move1);
			moveList1.add(move2);
			Movement move3 = mRepository.save(new Movement("burbee",20));
			Movement move4 = mRepository.save(new Movement("hölkkä",100));
			List<Movement> moveList2 = new ArrayList<Movement> ();
			moveList2.add(move3);
			moveList2.add(move4);
			eRepository.save(new Exercise("Treeni1", moveList1));
			eRepository.save(new Exercise("Treeni2", moveList2));
			
			System.out.println("fetch all movements");
			for (Movement movement : mRepository.findAll()) {
				System.out.println(movement.toString());
			}
			
			
			System.out.println("fetch all exercises");
			for (Exercise exercise : eRepository.findAll()) {
				System.out.println(exercise.toString());
			}
			
			
			// Create users: admin/admin user/user
			User user1 = new User("user",
			"$2a$10$hfSLCP0welzxwZ/corOqA.wd3AIXUBeafL4L.T/gMliCbRlFbhdce", "USER");
			User user2 = new User("admin",
			"$2a$10$rTiMhH7akolTCeEhuinTp.vcvqo9chv0hDQa665bMaTOlSOTAUQ1C", "ADMIN");
			uRepository.save(user1);
			uRepository.save(user2);

		};
	}


}
