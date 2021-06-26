package com.collabra.backend.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.collabra.backend.enti.Program;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Long> {

  List<Program> findByAdmin(long admin);
}