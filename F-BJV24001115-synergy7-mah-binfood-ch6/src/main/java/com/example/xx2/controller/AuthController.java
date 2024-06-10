package com.example.xx2.controller;

import com.example.xx2.model.ERole;
import com.example.xx2.model.Role;
import com.example.xx2.model.Users;
import com.example.xx2.model.dto.*;
import com.example.xx2.repository.RoleRepository;
import com.example.xx2.repository.UserRepository;
import com.example.xx2.security.jwt.JwtUtils;
import com.example.xx2.security.service.UserDetailsImpl;
import com.example.xx2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<Map<String, Object>> auth(@RequestBody LoginRequestDto loginRequestDto){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getUsername(),
                        loginRequestDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        Map<String, Object> data = new HashMap<>();
        JwtResponse jwtResponse = new JwtResponse(jwt, userDetails.getUsername(), roles);
        data.put("jwt", jwtResponse);
        response.put("data", data);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("signup")
    public ResponseEntity<Map<String, Object>> register(@RequestBody SignUpRequestDto signUpRequestDto){
        if (userService.existsByUsername(signUpRequestDto.getUsername())) {
            Map<String, Object> response = new HashMap<>();
            response.put("status", "error");
            response.put("message", "Username is already taken");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (userService.existsByEmail(signUpRequestDto.getEmail())) {
            Map<String, Object> response = new HashMap<>();
            response.put("status", "error");
            response.put("message", "Email is already in use");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Users user = new Users(signUpRequestDto.getUsername(),
                signUpRequestDto.getEmail(),
                passwordEncoder.encode(signUpRequestDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(ERole.ROLE_USER);
        roles.add(userRole);
        user.setRoles(roles);

        userRepository.save(user);

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "User registered successfully");

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/oauth2/success")
    public ResponseEntity<Map<String, Object>> googleLoginSuccess(Authentication authentication){
        OidcUser oidcUser = (OidcUser) authentication.getPrincipal();
        Collection<GrantedAuthority> authorities = new ArrayList<>(oidcUser.getAuthorities());
        authorities.add(new SimpleGrantedAuthority("ROLE_USER")); //Add your custom authority here TODO: fix it. Get it from DB

        UserDetailsImpl modifiedUserDetails = UserDetailsImpl.build(oidcUser);
        OidcUser modifiedOidcUser = new DefaultOidcUser(authorities, oidcUser.getIdToken(), oidcUser.getUserInfo());

        //Create a new Authentication object with the mofidied Principal
        Authentication modifiedAuthentication = new UsernamePasswordAuthenticationToken(
                modifiedOidcUser,
                oidcUser.getIdToken(),
                authorities
        );

        //Generate token using the modified authentication
        String jwt = jwtUtils.generateToken(modifiedAuthentication);

        //Extract User details from the modified authentication
        List<String> roles = modifiedAuthentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        //Prepare response
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        Map<String, Object> data = new HashMap<>();
        JwtResponse jwtResponse = new JwtResponse(jwt, modifiedUserDetails.getUsername(), roles);
        data.put("jwt", jwtResponse);
        response.put("data", data);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
