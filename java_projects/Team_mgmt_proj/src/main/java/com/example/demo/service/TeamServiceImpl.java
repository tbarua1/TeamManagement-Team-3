package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Team;
import com.example.demo.repository.TeamRepository;
@Service

public class TeamServiceImpl implements TeamService {

	
	@Autowired
	private TeamRepository teamRepository;
	public List<Team> getAllTeam_members() {
		// TODO Auto-generated method stub
		
		
		return teamRepository.findAll();
		
		
		
	}
	@Override
	public List<Team> getAllTeams() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void saveTeam(Team team) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Team getTeamById(long id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void deleteTeamById(long id) {
		// TODO Auto-generated method stub
		
	}
	
}
