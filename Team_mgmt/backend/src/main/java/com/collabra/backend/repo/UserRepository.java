package com.collabra.backend.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.collabra.backend.enti.Program;
import com.collabra.backend.enti.User;
import com.collabra.backend.resourc.UserResource;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    
    Boolean existsByUsername(String username);
    
    Boolean existsByEmail(String email);
    
    @Query("SELECT u.id FROM User u WHERE u.username=:username")
    long getIdByUsername(@ Param("username") String username);

    @Query("SELECT u.program FROM User u WHERE u.id=:id")
    List<Program> findProgramById(long id);
    
    public static final String FIND_USER = "SELECT id, name, username, email, password FROM users";

    @Query(value = FIND_USER, nativeQuery = true)
    public List<UserResource> findAllUsers();
}