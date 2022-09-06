package com.project.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.dao.UserDao;
import com.project.dto.UserRequest;
import com.project.entity.User;
import com.project.exception.UserNotFoundException;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class UserServiceImpl implements UserService {
	
    @Autowired
    UserDao dao;

    @Value("${admin.username}")
    private String adminUsername;

    @Override
    public ResponseEntity<User> addUser(UserRequest req) {

        log.info("inserting the user {}", req);
        User user = new User();
        BeanUtils.copyProperties(req, user);
        User response = dao.save(user);
        log.info("Response {}", response);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<List<User>> getAllUsers() {

        log.info("admin username : "+ adminUsername);
        List<User> users = dao.findAll();
        log.info("List of Users: ", users);
        return ResponseEntity.ok(users);
    }

    @Override
    public ResponseEntity<User> getUserById(int id) {

        log.info("Request {}", id);
        User user = new User();
        try {
            user = dao.findById(id).get();
            log.info("Response {}", id);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            log.error("User Not Found for id : " , e);
            throw new UserNotFoundException("User Not Found");
        }
    }

    @Override
    public ResponseEntity<User> updateUser(int id, UserRequest req) {

        log.info("Updating the user {}", id, req);
        try {
            User user = dao.findById(id).get();
            BeanUtils.copyProperties(req, user);
            user = dao.save(user);
            log.info("Response {}",user);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            log.error("User Not Found for id : " + id);
            throw new UserNotFoundException("User not Found");
        }

    }

    @Override
    public ResponseEntity<String> deleteUser(int id) {

        log.info("Deleting the user {}",id);
        try{
            dao.findById(id).get();
            dao.deleteById(id);
            log.info("Deleted successfully");
            return ResponseEntity.ok("Deleted");
        }catch (Exception e){
            log.error("User Not Found for id : " + id);
            throw new UserNotFoundException("User not Found");
        }

    }

}
