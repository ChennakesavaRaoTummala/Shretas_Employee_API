package com.project.controller;

import com.project.dto.UserRequest;
import com.project.entity.User;
import com.project.exception.UserNotFoundException;
import com.project.service.UserService;
import com.project.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class.getName());


    @Autowired
    UserService service;

    /**
     * To add User
     *
     * @param req
     */
    @PostMapping("/users")
    @Operation(summary = "To add User", tags = "Users")
    public ResponseEntity<User> addUser(@RequestBody UserRequest req) {
        User user = new User();
        LOG.info("Request {}",req);
        ResponseEntity<User> response = service.addUser(req);
        LOG.info("Response {}",response);
        return response;
    }

    /**
     * To get all Users
     *
     * @return
     */
    @GetMapping("/users")
    @Operation(summary = "To retrieve all Users", tags = "Users")
    public ResponseEntity<List<User>> getUsers() {
        LOG.info("Retrieving all the users..");
        ResponseEntity<List<User>> response = service.getAllUsers();
        LOG.info("Response {}",response);
        return response;
    }

    /**
     * To get User by Id
     *
     * @param id
     * @return
     */
    @GetMapping("/users/{id}")
    @Operation(summary = "To retrieve User by Id", tags = "Users")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        ResponseEntity<User> response = service.getUserById(id);
        return response;
    }

    /**
     * To update user
     *
     * @param id
     * @param req
     * @return
     */
    @PutMapping("/users/{id}")
    @Operation(summary = "To update User", tags = "Users")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody UserRequest req) {
        LOG.info("Request {}",id,req);
        ResponseEntity<User> response = service.updateUser(id, req);
        LOG.info("Response {}",response);
        return response;
    }

    /**
     * To delete user
     *
     * @param id
     */
    @DeleteMapping("/users/{id}")
    @Operation(summary = "To delete User", tags = "Users")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        LOG.info("Request {}",id);
        ResponseEntity<String> response = service.deleteUser(id);
        LOG.info("Response {}",response);
        return response;
    }
}
