package com.edu.educational.main;

import com.edu.educational.controller.CourseController;
import com.edu.educational.repository.CourseWasNotFoundException;
import com.edu.educational.repository.FileCourseRepository;
import com.edu.educational.service.CourseService;

public class Main {

	public static void main(String[] args) throws CourseWasNotFoundException{
		
		CourseController controller = new CourseController(new CourseService(new FileCourseRepository()));
		
		String request = "CREATE_COURSE\nMathemathic\n3\n1\nGanna\nganna.kovalenko@example.com\nSE-21\n4.2\n2\nInes\nines.schmidt@example.com\nSoftware Architecture\n3\nKristina\nkristi.maier@example.com\nStudent Services";
		
		String response = controller.doAction(request);
		
		System.out.println(response);
		
		request = "OBFUSCATE_DATA_FOR_PERSON\nMathemathic\nganna.kovalenko@example.com";
		
		response = controller.doAction(request);
		
		System.out.println(response);
		
		request = "SHOW_ALL_COURSES\n";
		
		response = controller.doAction(request);
		
		System.out.println(response);
		
	}
}
