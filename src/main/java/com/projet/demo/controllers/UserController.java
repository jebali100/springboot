package com.projet.demo.controllers;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projet.demo.dto.AuthResponseDTO;
import com.projet.demo.dto.LoginDto;
import com.projet.demo.dto.RegisterDto;
import com.projet.demo.entities.Role;
import com.projet.demo.entities.Salle;
import com.projet.demo.entities.User;
import com.projet.demo.repositories.RoleRepository;
import com.projet.demo.repositories.SalleRepository;
import com.projet.demo.repositories.UserRepository;
import com.projet.demo.security.JWTGenerator;
import com.projet.demo.services.UserServiceImpl;


@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/auth")
@RestController
public class UserController {
	
	
	@Autowired
	
	UserServiceImpl UserServ ;
	
	
	  private final AuthenticationManager authenticationManager;
	    private final UserRepository userRepository;
	    private final RoleRepository roleRepository;
	    private final PasswordEncoder passwordEncoder;
	    private final JWTGenerator jwtGenerator;
	    private final SalleRepository salleRepository;

	    @Autowired
	    public UserController(AuthenticationManager authenticationManager, UserRepository userRepository,
	                          RoleRepository roleRepository, PasswordEncoder passwordEncoder, JWTGenerator jwtGenerator,
	                          SalleRepository salleRepository) {
	        this.authenticationManager = authenticationManager;
	        this.userRepository = userRepository;
	        this.roleRepository = roleRepository;
	        this.passwordEncoder = passwordEncoder;
	        this.jwtGenerator = jwtGenerator;
	        this.salleRepository = salleRepository;
	    }

    
	    
	    @PostMapping("login")
	    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
	        try {
	            Authentication authentication = authenticationManager.authenticate(
	                    new UsernamePasswordAuthenticationToken(
	                            loginDto.getUsername(),
	                            loginDto.getPassword()
	                    )
	            );

	            SecurityContextHolder.getContext().setAuthentication(authentication);
	            String token = jwtGenerator.generateToken(authentication);

	            // Récupérer l'utilisateur authentifié
	            User user = userRepository.findByUsername(loginDto.getUsername())
	                                      .orElseThrow(() -> new RuntimeException("User not found"));

	            // Inclure l'ID de la salle dans la réponse
	            long idsalle = user.getSalle().getIdsalle();

	            return new ResponseEntity<>(new AuthResponseDTO(token, idsalle), HttpStatus.OK);

	        } catch (AuthenticationException e) {
	            // Authentication failed, return an error message
	            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
	        }
	    }

	    
	    @PostMapping("register-entraineur/{idsalle}")
	    public ResponseEntity<String> register(@PathVariable("idsalle") long idsalle, @RequestBody RegisterDto registerDto) {
	        // Vérifier si le nom d'utilisateur est déjà pris
	        if (userRepository.existsByUsername(registerDto.getUsername())) {
	            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
	        }

	        // Vérifier si l'email est déjà enregistré
	        if (userRepository.existsByEmail(registerDto.getEmail())) {
	            return new ResponseEntity<>("Email is already registered!", HttpStatus.BAD_REQUEST);
	        }

	        // Créer un nouvel utilisateur avec les informations fournies
	        User user = new User();
	        user.setFirstname(registerDto.getFirstname());
	        user.setLastname(registerDto.getLastname());
	        user.setUsername(registerDto.getUsername());
	        user.setEmail(registerDto.getEmail());
	        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

	        // Trouver la salle par ID
	        Optional<Salle> salleOptional = salleRepository.findById(idsalle);
	        if (salleOptional.isEmpty()) {
	            return new ResponseEntity<>("Salle not found!", HttpStatus.BAD_REQUEST);
	        }

	        // Associer la salle trouvée à l'utilisateur
	        Salle salle = salleOptional.get();
	        user.setSalle(salle);

	        // Rechercher le rôle 'entraineur'
	        Optional<Role> userRoleOptional = roleRepository.findByRolenameString("entraineur");
	        if (userRoleOptional.isEmpty()) {
	            return new ResponseEntity<>("Error during registration. Please try again later.", HttpStatus.INTERNAL_SERVER_ERROR);
	        }

	        // Associer le rôle 'entraineur' à l'utilisateur
	        Role userRole = userRoleOptional.get();
	        user.setRoles(Collections.singletonList(userRole));

	        // Enregistrer l'utilisateur dans la base de données
	        userRepository.save(user);

	        return new ResponseEntity<>("User registered successfully!", HttpStatus.OK);
	    }
	
	    
	    @PostMapping("register-admin/{idsalle}")
	    public ResponseEntity<String> registeradmin(@PathVariable("idsalle") long idsalle, @RequestBody RegisterDto registerDto) {
	        // Vérifier si le nom d'utilisateur est déjà pris
	        if (userRepository.existsByUsername(registerDto.getUsername())) {
	            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
	        }

	        // Vérifier si l'email est déjà enregistré
	        if (userRepository.existsByEmail(registerDto.getEmail())) {
	            return new ResponseEntity<>("Email is already registered!", HttpStatus.BAD_REQUEST);
	        }

	        // Créer un nouvel utilisateur avec les informations fournies
	        User user = new User();
	        user.setFirstname(registerDto.getFirstname());
	        user.setLastname(registerDto.getLastname());
	        user.setUsername(registerDto.getUsername());
	        user.setEmail(registerDto.getEmail());
	        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

	        // Trouver la salle par ID
	        Optional<Salle> salleOptional = salleRepository.findById(idsalle);
	        if (salleOptional.isEmpty()) {
	            return new ResponseEntity<>("Salle not found!", HttpStatus.BAD_REQUEST);
	        }

	        // Associer la salle trouvée à l'utilisateur
	        Salle salle = salleOptional.get();
	        user.setSalle(salle);

	        // Rechercher le rôle 'entraineur'
	        Optional<Role> userRoleOptional = roleRepository.findByRolenameString("admin");
	        if (userRoleOptional.isEmpty()) {
	            return new ResponseEntity<>("Error during registration. Please try again later.", HttpStatus.INTERNAL_SERVER_ERROR);
	        }

	        // Associer le rôle 'entraineur' à l'utilisateur
	        Role userRole = userRoleOptional.get();
	        user.setRoles(Collections.singletonList(userRole));

	        // Enregistrer l'utilisateur dans la base de données
	        userRepository.save(user);

	        return new ResponseEntity<>("User registered successfully!", HttpStatus.OK);
	    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
    
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
    @PutMapping (value = "/updateuser/{id}")
  	public User upadteUser ( @PathVariable Long id , @RequestBody User user) {
  		
      	return UserServ.updateUser(id , user);
  	}
    
    
    
    @DeleteMapping (value = "/deletuser/{id}")
    public void deleteUser(@PathVariable Long id) {
    	 UserServ.deleteUser(id);
    	
    }
    
    
    
    
    @GetMapping (value = "/listusers")
    public List<User> getAllUsers(){
    	
    	return UserServ.getListUsers();
    	
    }
    
    
    
    
    
    
    
    
    
    
    
   
    
    
   
    
    
	
	
}
