package com.edu.educational.repository.impl;

import java.util.List;

import com.edu.educational.model.Course;
import com.edu.educational.repository.CourseWasNotFoundException;

public interface CourseRepositoryInterface {
	
	void saveCourse(Course course) throws CourseWasNotFoundException;
	List<Course> getAllCourses() throws CourseWasNotFoundException;
	List<Course> loadCoursesFromFile() throws CourseWasNotFoundException;
	void obfuscateStudentInCourse(String courseName, String studentEmail);
}
