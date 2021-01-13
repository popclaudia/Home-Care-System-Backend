package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.UserDTO;
import ro.tuc.ds2020.dtos.UserPrivateDTO;
import ro.tuc.ds2020.login_request.JwtRequest;
import ro.tuc.ds2020.security_config.JwtTokenUtil;
import ro.tuc.ds2020.services.UserService;


import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Objects;

@RestController
@CrossOrigin
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping()
    public ResponseEntity<Integer> insertUser(@Valid @RequestBody UserDTO userDTO) {
        Integer userID = userService.insert(userDTO);
        return new ResponseEntity<>(userID, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserPrivateDTO> getUser(@PathVariable("id") Integer userId) {
        UserPrivateDTO dto = userService.findUserById(userId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    //login
    @PostMapping(value = "/login")
    public ResponseEntity<UserPrivateDTO> getUserN(@RequestBody JwtRequest authenticationRequest) {
        String user = authenticationRequest.getUsername();
        String password = authenticationRequest.getPassword();
        System.out.println(user + " " + password);
        Objects.requireNonNull(user);
        Objects.requireNonNull(password);
        System.out.println(2);
//        try {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user, password));
//        }
//        catch(Exception e){
//            e.printStackTrace();
//        }
        System.out.println(3);
        UserPrivateDTO dto = userService.findUserByUsername(user, password);
        System.out.println(4);
        final String token = jwtTokenUtil.generateToken(dto);
        System.out.println(5);
        dto.setToken(token);
        System.out.println(token);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }



}
