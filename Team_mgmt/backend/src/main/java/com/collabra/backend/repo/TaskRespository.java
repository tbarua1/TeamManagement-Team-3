package com.collabra.backend.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.collabra.backend.enti.Task;

@Repository
public interface TaskRespository extends JpaRepository<Task, Long> {
  List<Task> findByProgram(long program);
}
