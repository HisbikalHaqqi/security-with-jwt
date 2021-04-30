/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.security.securitywithjwt;

import io.security.securitywithjwt.services.MyUserDetailService;
import io.security.securitywithjwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author HISBIKAL
 */
@RestController
public class HelloResources {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private MyUserDetailService userDetailService;
    
    @Autowired
    private JwtUtil jwtTokenUtil;
    
    @RequestMapping("/hello")
        public String hello(){
            return "hello world";
    }
        
   @RequestMapping(value = "/authenticate",method = RequestMethod.POST)
   public ResponseEntity<?> createAuthentication(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
       try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));   
       }
       catch(BadCredentialsException ex){
           throw new Exception("Incorrect username or Password");
       }
       final UserDetails userDetails = userDetailService.loadUserByUsername(authenticationRequest.getUsername());
       final String jwt = jwtTokenUtil.generateToken(userDetails);
       
       return ResponseEntity.ok(new AuthenticationResponse(jwt));
   }
}
