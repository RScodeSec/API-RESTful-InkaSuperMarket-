package com.rs.market.web.controller;

import com.rs.market.domain.dto.AutenticationRequest;
import com.rs.market.domain.dto.AutenticationResponse;
import com.rs.market.domain.service.MarketUserDetail;

import com.rs.market.web.security.JWTUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {


    private AuthenticationManager authenticationManager;
    private MarketUserDetail marketUserDetail;
    private JWTUtil jwtUtil;


    public AuthController(AuthenticationManager authenticationManager,
                          MarketUserDetail marketUserDetail,
                          JWTUtil jwtUtil) {
        this.authenticationManager =authenticationManager;
        this.marketUserDetail = marketUserDetail;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/authenticate")
    @ApiOperation("Authenticate by username and password")
    public ResponseEntity<AutenticationResponse> createJWT(@RequestBody AutenticationRequest req){

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
            UserDetails userDetails = marketUserDetail.loadUserByUsername(req.getUsername());


            String jwt = jwtUtil.generateToken(userDetails);

            return new ResponseEntity<>(new AutenticationResponse(jwt),HttpStatus.OK);

        }catch (BadCredentialsException e){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }
}
