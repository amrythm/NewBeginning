package org.pharmEasy.controller;


import org.pharmEasy.constants.LoginMappings;
import org.pharmEasy.authentication.dto.JwtResponse;
import org.pharmEasy.authentication.dto.LoginRequest;
import org.pharmEasy.service.MyUserDetailService;
import org.pharmEasy.authentication.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(LoginMappings.LOGIN)
@CrossOrigin
public class LoginController {

        @Autowired
        private AuthenticationManager authenticationManager;

        @Autowired
        private JwtTokenUtil jwtTokenUtil;

        @Autowired
        private MyUserDetailService userDetailsService;

        @PostMapping
        public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws Exception {

                authenticate(loginRequest.getUsername(), loginRequest.getPassword());

                final UserDetails userDetails = userDetailsService
                        .loadUserByUsername(loginRequest.getUsername());

                final String token = jwtTokenUtil.generateToken(userDetails);

                return ResponseEntity.ok(new JwtResponse(token));
        }

        private void authenticate(String username, String password) throws Exception {
                try {
                        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
                } catch (DisabledException e) {
                        throw new Exception("USER_DISABLED", e);
                } catch (BadCredentialsException e) {
                        throw new Exception("INVALID_CREDENTIALS", e);
                }
        }
}
