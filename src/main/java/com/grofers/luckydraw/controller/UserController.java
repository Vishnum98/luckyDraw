package com.grofers.luckydraw.controller;

import com.grofers.luckydraw.model.User;
import com.grofers.luckydraw.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @PostMapping("/createUser")
    public ResponseEntity<User> createUser(@RequestParam String name, @RequestParam String add) {

        User user = new User(name, add);

        user = userRepo.save(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     *  Get's all users Based on ID & NAME
     * @param id userID
     * @param name user Name
     * @return List of Users satisfying the conditions
     */
    @GetMapping("/get")
    public ResponseEntity<List<User>> getAllUser(@RequestParam(required = false) Integer id,@RequestParam(required = false) String name) {

        List<User> users = new ArrayList<>();
        if(id != null && name != null) {
            users = userRepo.findByIdAndName(id,name);
        }else if(id != null){
            Optional<User> user = userRepo.findById(id);
            if(user.isPresent())
                users = List.of(user.get());
        }else if(name != null){
            users = userRepo.findByName(name);
        }else
            users=userRepo.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
