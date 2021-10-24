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
public class MovementRepositoryTest {

	@Autowired
	MovementRepository movementRepository;
	
	@Test
	public void findAllShouldReturnSize() {
		List<Movement> movements = (List<Movement>) movementRepository.findAll();
		
		Assertions.assertThat(movements).hasSize(4);
	}

	@Test
	public void findAllShouldReturnName() {
		List<Movement> movements = (List<Movement>) movementRepository.findAll();
		Assertions.assertThat(movements.get(0).getName()).isEqualTo("maastaveto");
	}
	
	@Test
	public void insertNewMovement() {
		Movement movement = new Movement("uusiliike",30);
		movementRepository.save(movement);
		List<Movement> movements = (List<Movement>) movementRepository.findAll();
		Assertions.assertThat(movements.get(4).getName()).isEqualTo("uusiliike");
		
	}
	
	@Test
	public void editMovement() {
		List<Movement> movements = (List<Movement>) movementRepository.findAll();
		Movement movement = movements.get(0);
		movement.setName("muokattu liike");
		movementRepository.save(movement);
		Assertions.assertThat(movements.get(0).getName()).isEqualTo("muokattu liike");
		
	}
	
	@Test
	public void deleteMovement() {
		List<Movement> movements = (List<Movement>) movementRepository.findAll();
		Movement movement = movements.get(0);
		movementRepository.delete(movement);
		Assertions.assertThat(movements).hasSize(4);
		
	}

}
