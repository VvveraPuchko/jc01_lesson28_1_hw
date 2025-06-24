package com.edu.educational.controller.impl;


import com.edu.educational.controller.Command;
import com.edu.educational.service.CourseService;

public class ObfuscateDataForPerson implements Command {
	
private final CourseService courseService;
	
	public ObfuscateDataForPerson(CourseService courseService) {
		this.courseService = courseService;
	}
	
	@Override
	public String execute(String request) {
		String[] lines = request.split("\n");
		
		String courseName = lines[1];
		String email = lines[2];
		
		try { 
			courseService.obfuscateData(courseName, email);
			return "Data were obfuscated";
		} catch (Exception e) {
			System.err.println("Obfuscation failed: course not found — " + e.getMessage());
			return "Error while obfuscating data";
		}
		
		
	}
 

}
