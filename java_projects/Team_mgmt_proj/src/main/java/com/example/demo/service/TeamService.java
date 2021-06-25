package com.example.demo.service;

import com.example.demo.model.Team;

import java.util.*;
public interface TeamService {

	List < Team > getAllTeams();
    void saveTeam(Team team);
    Team getTeamById(long id);
    void deleteTeamById(long id);

}
