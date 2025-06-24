package com.edu.educational.controller.impl;

import com.edu.educational.controller.Command;

public class NoSuchCommand implements Command {
	
	@Override
	public String execute(String request) {
		return "There is no such command, please try again";
	}
 

}