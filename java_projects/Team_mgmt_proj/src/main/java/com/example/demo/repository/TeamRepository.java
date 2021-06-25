package com.example.demo.repository;

import java.util.List;

import com.example.demo.model.Team;

public interface TeamRepository {

	List<Team> findAll();

}
