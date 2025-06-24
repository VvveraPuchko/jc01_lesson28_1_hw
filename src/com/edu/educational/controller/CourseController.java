package com.edu.educational.controller;

import java.util.List;

import com.edu.educational.model.Course;
import com.edu.educational.model.Person;
import com.edu.educational.repository.CourseWasNotFoundException;
import com.edu.educational.service.CourseService;

public class CourseController {
	private final char paramDelimeter = '\n';
	
    private final CourseService courseService;
	
	private final CommandProvider provider;
	
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
        provider = new CommandProvider(courseService);
    }
    
    public String doAction(String request) {
    	String commandName;
    	Command command;
    	
    	commandName = request.substring(0, request.indexOf(paramDelimeter));
    	command = provider.getCommand(commandName);
    	
    	String response = command.execute(request);
    	
    	return response;
    }

}