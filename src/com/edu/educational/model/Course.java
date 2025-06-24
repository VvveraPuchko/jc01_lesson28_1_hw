package com.edu.educational.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Course {
    private final String name;
    private final List<Person> participants = new ArrayList<>();
    private final List<Person> staff = new ArrayList<>();

    public Course(String name) {
        this.name = name;
    }

    public String getName() { return name; }

	public boolean addParticipant(Person person) {
		if (person.getClass() == Administrator.class || person.getClass() == Teacher.class) {
			for (Person staffPerson : staff) {
				if (staffPerson.getEmail().equals(person.getEmail()))
					return false;
			}
			return staff.add(person);
		} else {
			for (Person p : participants) {
				if (p.getEmail().equals(person.getEmail()))
					return false;
			}
			return participants.add(person);
		}
		
		
	}

    public void conductLesson() {
        for (Person p : participants) {
            p.performRole();
        }
        for (Person p : staff) {
            p.performRole();
        }
    }

    public double calculateAverageGrade() {
        int count = 0;
        double sum = 0.0;
        for (Person p : participants) {
            if (p instanceof Student s) {
                sum += s.getAverageGrade();
                count++;
            }
        }
        return count > 0 ? sum / count : 0.0;
    }

    public List<Person> getParticipants() {
        return participants;
    }
    
    public List<Person> getStaff() {
        return staff;
    }

	@Override
	public int hashCode() {
		return Objects.hash(name, participants, staff);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		return Objects.equals(name, other.name) && Objects.equals(participants, other.participants)
				&& Objects.equals(staff, other.staff);
	}

	@Override
	public String toString() {
		return "Course [name=" + name + ", participants=" + participants + ", staff=" + staff + "]";
	}

	
	
 
}

