package com.edu.educational.service;

import java.util.List;

import com.edu.educational.repository.*;
import com.edu.educational.repository.impl.CourseRepositoryInterface;
import com.edu.educational.model.Course;
import com.edu.educational.model.Person;

public class CourseService {
    private final CourseRepositoryInterface courseRepository;

    public CourseService(CourseRepositoryInterface courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void createCourse(Course course) throws CourseWasNotFoundException {
        courseRepository.saveCourse(course);
    }

    public boolean enrollPerson(Course course, Person person) {
        return course.addParticipant(person);
    }

    public void conductLesson(Course course) {
        course.conductLesson();
    }

    public List<Person> getParticipants(Course course) {
        return course.getParticipants();
    }
    
    public List<Person> getStaff(Course course) {
        return course.getStaff();
    }

    public double getAverageGrade(Course course) {
        return course.calculateAverageGrade();
    }

    public List<Course> getAllCourses() throws CourseWasNotFoundException {
        return courseRepository.getAllCourses();
    }
    
    public void obfuscateData(String nameOfCourse, String email) {
    	courseRepository.obfuscateStudentInCourse(nameOfCourse, email);
    }
}
