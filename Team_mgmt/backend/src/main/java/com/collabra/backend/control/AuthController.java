package com.collabra.backend.control;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.collabra.backend.enti.Role;
import com.collabra.backend.enti.RoleName;
import com.collabra.backend.enti.User;
import com.collabra.backend.message.request.LoginForm;
import com.collabra.backend.message.request.SignUpForm;
import com.collabra.backend.message.response.JwtResponse;
import com.collabra.backend.repo.RoleRepository;
import com.collabra.backend.repo.UserRepository;
import com.collabra.backend.security.jwt.JwtProvider;
import com.collabra.backend.service.MailService;
import com.collabra.backend.util.Client;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {

  final String clientUrl = Client.clientUrl;
  
  @Autowired
  MailService s;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtProvider jwtProvider;

	@PostMapping("/signin")
	@CrossOrigin(origins = clientUrl)
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtProvider.generateJwtToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		long id = userRepository.getIdByUsername(userDetails.getUsername());
		JwtResponse jwtRes = new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities(), id);
		return ResponseEntity.ok(jwtRes);
	}

	@PostMapping("/signup")
	@CrossOrigin(origins = clientUrl)
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {

		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
		}

		User user = new User(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));

		Set<Role> roles = new HashSet<>();

		if( signUpRequest.getUser().equalsIgnoreCase("U")) {
		  Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
	        .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
	        roles.add(userRole);
	    
		} else if( signUpRequest.getUser().equalsIgnoreCase("A")) {
		  Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
          .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
      roles.add(adminRole);
		}

		user.setRoles(roles);
		userRepository.save(user);
		return new ResponseEntity<>(true, HttpStatus.OK);
	}
}