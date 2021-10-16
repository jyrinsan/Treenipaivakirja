package fi.palvelunohjelmointi.workoutdiary;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.palvelunohjelmointi.workoutdiary.domain.Workout;
import fi.palvelunohjelmointi.workoutdiary.domain.WorkoutRepository;
import fi.palvelunohjelmointi.workoutdiary.domain.Entry;
import fi.palvelunohjelmointi.workoutdiary.domain.EntryRepository;
import fi.palvelunohjelmointi.workoutdiary.domain.Movement;
import fi.palvelunohjelmointi.workoutdiary.domain.MovementRepository;
import fi.palvelunohjelmointi.workoutdiary.domain.User;
import fi.palvelunohjelmointi.workoutdiary.domain.UserRepository;

@SpringBootApplication
public class WorkoutApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkoutApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner workoutDiary(EntryRepository eRepository, WorkoutRepository wRepository, MovementRepository mRepository, UserRepository uRepository) {
		return (args) -> {	
			
			// Create users: admin/admin user/user
			System.out.println("save a couple of users");
			User user1 = new User("user",
			"$2a$10$hfSLCP0welzxwZ/corOqA.wd3AIXUBeafL4L.T/gMliCbRlFbhdce", "USER");
			User user2 = new User("admin",
			"$2a$10$rTiMhH7akolTCeEhuinTp.vcvqo9chv0hDQa665bMaTOlSOTAUQ1C", "ADMIN");
			uRepository.save(user1);
			uRepository.save(user2);
			
			System.out.println("save a couple of movements");
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
			
			System.out.println("save a couple of workouts");
			Workout workout1 = new Workout("Treeni1", moveList1);
			Workout workout2 = new Workout("Treeni2", moveList2);
			wRepository.save(workout1);
			wRepository.save(workout2);
			
			System.out.println("save a couple of entries");
			eRepository.save(new Entry(LocalDate.now(), "User treenaa",user1, workout1));
			eRepository.save(new Entry(LocalDate.now(), "Jotain", user1, workout2));
			eRepository.save(new Entry(LocalDate.now(), "Admin treenaa", user2, workout1));
			eRepository.save(new Entry(LocalDate.now(), "Jotain2s", user2, workout2));
			
			System.out.println("fetch all movements");
			for (Movement movement : mRepository.findAll()) {
				System.out.println(movement.toString());
			}
			
			
			System.out.println("fetch all workouts");
			for (Workout workout : wRepository.findAll()) {
				System.out.println(workout.toString());
			}
			
			System.out.println("fetch all entries");
			for (Entry entry : eRepository.findAll()) {
				System.out.println(entry.toString());
			}


		};
	}


}
