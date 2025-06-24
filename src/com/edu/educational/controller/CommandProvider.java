package com.edu.educational.controller;

import java.util.HashMap;
import java.util.Map;
import com.edu.educational.controller.impl.*;
import com.edu.educational.service.CourseService;

public class CommandProvider {
	
	private final Map<CommandName, Command> repository = new HashMap<>();
	
	CommandProvider(CourseService courseService) {
		this.repository.put(CommandName.CREATE_COURSE, new CreateCourseCommand(courseService));
		this.repository.put(CommandName.OBFUSCATE_DATA_FOR_PERSON, new ObfuscateDataForPerson(courseService));
		this.repository.put(CommandName.SHOW_ALL_COURSES, new ShowAllCoursesCommand(courseService));
		this.repository.put(CommandName.WRONG_REQUEST, new NoSuchCommand());
	}
	
	public Command getCommand(String name) {
		
		CommandName commandName;
		Command command;
		
		try {
			commandName = CommandName.valueOf(name.toUpperCase());
			command = repository.get(commandName);
		} catch(IllegalArgumentException | NullPointerException e) {
			command = repository.get(CommandName.WRONG_REQUEST);
		}
		
		return command;
	}
	

}
