package com.collabra.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.collabra.backend.enti.User;
import com.collabra.backend.repo.ProgramRepository;
import com.collabra.backend.repo.UserRepository;
import com.collabra.backend.resourc.ProgramResource;
import com.collabra.backend.resourc.UserResource;

@Service
public class UserService {
  @Autowired
  private ProgramRepository programRepository;
  @Autowired
  private UserRepository userRepository;
 
  public List<UserResource> getAllUser() {
    return (List<UserResource>) userRepository.findAll()
                                .stream()
                                .map(user -> new UserResource(user))
                                .collect(Collectors.toList());
  }
  
  public List<UserResource> getAllUserByProgram(long programId) {
    List<UserResource> users = programRepository.findById(programId)
                                .get()
                                .getUsers()
                                .stream()
                                .map(user -> new UserResource(user))
                                .collect(Collectors.toList());
    return users;
  }
  
  public List<ProgramResource> getAllProgramByUser(long uid) {
    return userRepository.findProgramById(uid)
                                .stream()
                                .map(program -> new ProgramResource(program))
                                .collect(Collectors.toList());
  }
  
  public User getUser(long id) {
    return userRepository.findById(id).get();
  }
}
