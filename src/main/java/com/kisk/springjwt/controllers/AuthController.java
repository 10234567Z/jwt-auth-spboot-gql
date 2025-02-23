package com.kisk.springjwt.controllers;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.kisk.springjwt.models.ERole;
import com.kisk.springjwt.models.Role;
import com.kisk.springjwt.models.User;
import com.kisk.springjwt.payload.response.JwtResponse;
import com.kisk.springjwt.payload.response.MessageResponse;
import com.kisk.springjwt.repository.RoleRepository;
import com.kisk.springjwt.repository.UserRepository;
import com.kisk.springjwt.security.jwt.JwtUtils;
import com.kisk.springjwt.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @MutationMapping
    public JwtResponse loginWithUsername(@Argument String username, @Argument String password) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        System.out.println(userDetails);
        List<Role> roles = userDetails.getAuthorities().stream()
                .map(authority -> roleRepository.findByName(
                                Objects.equals(authority.getAuthority(), "ROLE_ADMIN")
                                        ? ERole.ROLE_ADMIN :
                                        Objects.equals(authority.getAuthority(), "ROLE_MOD") ? ERole.ROLE_MODERATOR :
                                                ERole.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."))
                )
                .toList();



        User userToReturn = new User(userDetails.getEmail(), userDetails.getPassword(), userDetails.getUsername());
        userToReturn.setId(userDetails.getId());
        userToReturn.setRoles(new ArrayList<>(roles));
        return new JwtResponse(jwt, userToReturn);
    }


    @MutationMapping
    public MessageResponse signUp(@Argument String username, @Argument String password, @Argument String email, @Argument List<String> roles) {
        if (userRepository.existsByUsername(username)) {
            return new MessageResponse("Error: Username is already taken!");
        }

        if (userRepository.existsByEmail(email)){
            return new MessageResponse("Error: Email is already in use!");
        }

        User user = new User(username,  email, encoder.encode(password));

        List<Role> passedRoles = new ArrayList<>();
        roles.forEach(role -> {
            if (Objects.equals(role, "admin")) {
                Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                passedRoles.add(adminRole);
            } else if (Objects.equals(role, "mod")) {
                Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                passedRoles.add(modRole);
            } else {
                Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                passedRoles.add(userRole);
            }
        });

        user.setRoles(passedRoles);
        userRepository.save(user);

        return new MessageResponse("User registered successfully!");
    }
}
