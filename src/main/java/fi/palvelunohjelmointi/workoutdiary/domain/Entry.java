package fi.palvelunohjelmointi.workoutdiary.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Entry {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	
	@NotNull
	@DateTimeFormat(pattern="dd.MM.yyyy")
	private LocalDate date;
	
	@NotBlank
	private String description;
	
	@ManyToOne
	@JoinColumn(name="userid")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="workoutid")
	@NotNull
	private Workout workout;
	
	public Entry() {
		super();
	}
	
	public Entry(LocalDate date, String description, User user, Workout workout) {
		this.date = date;
		this.description = description;
		this.user = user;
		this.workout = workout;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Workout getWorkout() {
		return workout;
	}

	public void setWorkout(Workout workout) {
		this.workout = workout;
	}

	@Override
	public String toString() {
		return "Entry [id=" + id + ", date=" + date + ", description=" + description + ", user=" + user + ", workout="
				+ workout + "]";
	}
	
}
