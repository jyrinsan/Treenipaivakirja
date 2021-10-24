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
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserRepositoryTest {

	@Autowired
	UserRepository userRepository;
	
	@Test
	public void findAllShouldReturnSize() {
		List<User> users = (List<User>) userRepository.findAll();
		
		Assertions.assertThat(users).hasSize(2);
	}
	
	@Test
	public void findByUsernameShouldReturnRole() {
		User user = userRepository.findByUsername("admin");
		
		Assertions.assertThat(user.getRole()).isEqualTo("ADMIN");
	}

}
