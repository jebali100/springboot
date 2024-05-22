package com.projet.demo.controllers;


import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projet.demo.dto.AuthResponseDTO;
import com.projet.demo.dto.LoginDto;
import com.projet.demo.dto.RegisterDto;
import com.projet.demo.entities.Member;
import com.projet.demo.entities.Salle;
import com.projet.demo.repositories.MemberRepository;
import com.projet.demo.repositories.SalleRepository;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.projet.demo.security.JWTGenerator;
import com.projet.demo.services.MemberServiceImpl;


@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("/api/auth")
public class MemberController {
	
	@Autowired
	MemberServiceImpl Memberserv;
	private final AuthenticationManager authenticationManager;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final SalleRepository salleRepository;
    private final JWTGenerator jwtGenerator;
    
    @Autowired
    public MemberController(AuthenticationManager authenticationManager, MemberRepository memberRepository,
                            PasswordEncoder passwordEncoder, SalleRepository salleRepository, JWTGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.salleRepository = salleRepository;
        this.jwtGenerator = jwtGenerator;
    }

	
	
	
	
	    @PostMapping("/register-member/{idsalle}")
	    public ResponseEntity<String> registerMember(@PathVariable("idsalle") long idsalle, @RequestBody RegisterDto registerDto) {
	        if (memberRepository.existsByUsername(registerDto.getUsername())) {
	            return new ResponseEntity<>("Nom d'utilisateur déjà utilisé!", HttpStatus.BAD_REQUEST);
	        }

	        if (memberRepository.existsByEmail(registerDto.getEmail())) {
	            return new ResponseEntity<>("L'e-mail est déjà enregistré!", HttpStatus.BAD_REQUEST);
	        }

	        Member member = new Member();
	        member.setFirstname(registerDto.getFirstname());
	        member.setLastname(registerDto.getLastname());
	        member.setUsername(registerDto.getUsername());
	        member.setEmail(registerDto.getEmail());
	        member.setPassword(passwordEncoder.encode(registerDto.getPassword()));
	        member.setEtat(0);

	        // Trouver la salle par ID
	        Optional<Salle> salleOptional = salleRepository.findById(idsalle);
	        if (salleOptional.isEmpty()) {
	            return new ResponseEntity<>("Salle non trouvée!", HttpStatus.BAD_REQUEST);
	        }

	        // Associer la salle trouvée au membre
	        Salle salle = salleOptional.get();
	        member.setSalle(salle);

	        memberRepository.save(member);

	        return new ResponseEntity<>("Membre enregistré avec succès!", HttpStatus.OK);
	    }
	
	
	    @PostMapping("/login-member")
	    public ResponseEntity<?> loginMember(@RequestBody LoginDto loginDto) {
	        try {
	            Authentication authentication = authenticationManager.authenticate(
	                    new UsernamePasswordAuthenticationToken(
	                            loginDto.getUsername(),
	                            loginDto.getPassword()
	                    )
	            );

	            SecurityContextHolder.getContext().setAuthentication(authentication);
	            String token = jwtGenerator.generateToken(authentication);

	            // Récupérer le membre authentifié
	            Member member = memberRepository.findByUsername(loginDto.getUsername())
	                                            .orElseThrow(() -> new RuntimeException("Member not found"));

	            // Inclure l'ID de la salle dans la réponse
	            long idsalle = member.getSalle().getIdsalle();

	            return new ResponseEntity<>(new AuthResponseDTO(token, idsalle), HttpStatus.OK);

	        } catch (AuthenticationException e) {
	            // Authentication failed, return an error message
	            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
	        }
	    }


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	        @GetMapping("/abonnes")
	        public Set<Member> getMembresAbonnesUniques() {
	            return Memberserv.getMembresAbonnesUniques();
	        }
	        
	        @GetMapping("/profile")
	        public ResponseEntity<Member> getLoggedInMember(@RequestParam("username") String username) {
	            Optional<Member> optionalMember = Memberserv.getLoggedInMember(username);
	            if (optionalMember.isPresent()) {
	                return ResponseEntity.ok(optionalMember.get());
	            } else {
	                return ResponseEntity.notFound().build();
	            }
	        }
	
	
	 
	
}
