package com.edu.educational.controller.impl;

import com.edu.educational.controller.Command;
import com.edu.educational.service.CourseService;
import com.edu.educational.model.*;

public class CreateCourseCommand implements Command {
	
private final CourseService courseService;
	
	public CreateCourseCommand(CourseService courseService) {
		this.courseService = courseService;
	}
	
	@Override
	public String execute(String request) {
	    String[] lines = request.split("\n");

	    if (lines.length < 3) {
	        return "Invalid request: not enough data.";
	    }

	    String courseName = lines[1];
	    int participantCount = Integer.parseInt(lines[2]);

	    Course course = new Course(courseName);

	    int index = 3;
	    for (int i = 0; i < participantCount; i++) {
	        int role = Integer.parseInt(lines[index++]);
	        String name = lines[index++];
	        String email = lines[index++];

	        Person person = null;

	        switch (role) {
	            case 1 -> { //1 - Student
	                String group = lines[index++];
	                double grade = Double.parseDouble(lines[index++]);
	                person = new Student(name, email, group, grade);
	            }
	            case 2 -> { //2 - Teacher
	                String subject = lines[index++];
	                person = new Teacher(name, email, subject);
	            }
	            case 3 -> { // 3 - Administrator
	                String dept = lines[index++];
	                person = new Administrator(name, email, dept);
	            }
	            default -> {
	                return "Invalid role number: " + role;
	            }
	        }

	        if (person != null) {
	            course.addParticipant(person);
	        }
	    }

	    try {
	        courseService.createCourse(course);
	        return "Course '" + courseName + "' was successfully created with " + participantCount + " participant(s).";
	    } catch (Exception e) {
	    	System.err.println("Course creation failed: " + e.getMessage());
	        return "Error while saving course";
	    }
	}

}
