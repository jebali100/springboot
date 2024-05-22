package com.projet.demo.security;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.projet.demo.entities.Member;
import com.projet.demo.entities.Role;
import com.projet.demo.entities.User;
import com.projet.demo.repositories.MemberRepository;
import com.projet.demo.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;
    private MemberRepository memberRepository;

    public CustomUserDetailsService(UserRepository userRepository, MemberRepository memberRepository) {
        this.userRepository = userRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user != null) {
            return buildUserDetails(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
        } else {
            Member member = memberRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
            return buildUserDetails(member.getUsername(), member.getPassword());
        }
    }

    private UserDetails buildUserDetails(String username, String password) {
        return new org.springframework.security.core.userdetails.User(username, password, Collections.emptyList());
    }

    private UserDetails buildUserDetails(String username, String password, Collection<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(username, password, authorities);
    }

    private Collection<GrantedAuthority> mapRolesToAuthorities(List<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRolename().toString())).collect(Collectors.toList());
    }


}
