package fi.palvelunohjelmointi.exercisediary.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Exercise {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id; 
	
	private String name;
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "Exercise_Movement",
    joinColumns = {
        @JoinColumn(
            name = "exercise_id",
            referencedColumnName = "id"
        )
    },
    inverseJoinColumns = {
        @JoinColumn(
            name = "movement_id",
            referencedColumnName = "id"
        )
    }
)
	private List<Movement> movements;

	public Exercise() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Exercise(String name, List<Movement> movements) {
		super();
		this.name = name;
		this.movements = movements;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Movement> getMovements() {
		return movements;
	}

	public void setMovements(List<Movement> movements) {
		this.movements = movements;
	}

	@Override
	public String toString() {
		return "Exercise [id=" + id + ", name=" + name + ", movements=" + movements + "]";
	}

	
}
