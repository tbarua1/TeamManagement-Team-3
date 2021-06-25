package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.service.TeamService;

@Controller

public class TeamController {
	//display list of team members
	@Autowired
	private TeamService teamService;
	
	
	@GetMapping("/")
	public String viewHomePage(Model model) {
		model.addAttribute("listTeams",teamService.getAllTeams());
		return "index";
		
		
	}
	
	
}
