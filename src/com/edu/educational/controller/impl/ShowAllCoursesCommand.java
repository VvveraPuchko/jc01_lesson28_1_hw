package com.edu.educational.controller.impl;

import java.util.List;

import com.edu.educational.controller.Command;
import com.edu.educational.model.Course;
import com.edu.educational.repository.CourseWasNotFoundException;
import com.edu.educational.service.CourseService;

public class ShowAllCoursesCommand implements Command {
	
private final CourseService courseService;
	
	public ShowAllCoursesCommand(CourseService courseService) {
		this.courseService = courseService;
	}
	
	@Override
	public String execute(String request) {
		List<Course> existingCourses;
		
    	try {
    		existingCourses = courseService.getAllCourses();
    		for (Course course: existingCourses) {
        		System.out.println(course);
        	}
    		return "Courses are on the console";
    	} catch (CourseWasNotFoundException e) {
    		System.err.println("Obfuscation failed: course not found — " + e.getMessage());
    		return "Error while showing courses info";
    	}
    	
		
	}
}
