package fi.palvelinohjelmointi.workoutdiary.domain;

import java.time.LocalDate;
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
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class EntryRepositoryTest {

	@Autowired
	EntryRepository entryRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	WorkoutRepository workoutRepository;
	
	@Test
	public void findByUserShouldReturnSize() {
		User user = userRepository.findByUsername("user");
		List<Entry> entries = entryRepository.findByUser(user);
		
		Assertions.assertThat(entries).hasSize(2);
	}
	
	@Test
	public void findAllShouldReturnSize() {
		List<Entry> entries = (List<Entry>) entryRepository.findAll();
		
		Assertions.assertThat(entries).hasSize(4);
	}
	
	public void insertNewEntry() {
		User user = userRepository.findByUsername("user");
		Workout workout = workoutRepository.findByName("mavetreeni");
		Entry entry = new Entry(LocalDate.now(), "uusi treenisuoritus", user, workout);
		entryRepository.save(entry);
		List<Entry> entries = (List<Entry>) entryRepository.findAll();
		
		Assertions.assertThat(entries).hasSize(5);
		Assertions.assertThat(entries.get(4).getDescription()).isEqualTo("uusi treenisuoritus");
	}


}
