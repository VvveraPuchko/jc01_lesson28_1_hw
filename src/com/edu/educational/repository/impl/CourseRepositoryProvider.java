package com.edu.educational.repository.impl;

import com.edu.educational.repository.CourseWasNotFoundException;
import com.edu.educational.repository.FileCourseRepository;
import com.edu.educational.repository.RepositoryInitializationException;

public class CourseRepositoryProvider {
	

    private static CourseRepositoryProvider instance = new CourseRepositoryProvider();
    
    private final CourseRepositoryInterface courseRepository;
    
    private CourseRepositoryProvider() {
        
    }

    {
        try {
        	courseRepository = new FileCourseRepository();
        } catch (CourseWasNotFoundException e) {
            throw new RepositoryInitializationException("Failed to initialize course repository", e);
        }
    }
	
	public CourseRepositoryInterface getCourseRepository() {
		return courseRepository;
	}
	
	public static CourseRepositoryProvider getInstance() throws CourseWasNotFoundException {
		return instance;
	}
	
}
