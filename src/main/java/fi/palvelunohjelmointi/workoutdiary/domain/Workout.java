package fi.palvelunohjelmointi.workoutdiary.domain;

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
import javax.persistence.OneToMany;

@Entity
public class Workout {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id; 
	
	private String name;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="workout")
	private List<Entry> entries;
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "Workout_Movement",
	    joinColumns = {
	        @JoinColumn(
	            name = "workout_id",
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

	public Workout() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Workout(String name, List<Movement> movements) {
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

	public List<Entry> getEntries() {
		return entries;
	}

	public void setEntries(List<Entry> entries) {
		this.entries = entries;
	}

	@Override
	public String toString() {
		return "Workout [id=" + id + ", name=" + name + ", movements=" + movements + "]";
	}

}
