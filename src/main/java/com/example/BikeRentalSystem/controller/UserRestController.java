package com.example.BikeRentalSystem.controller;

import com.example.BikeRentalSystem.entities.Users;
import com.example.BikeRentalSystem.service.SequenceGeneratoryService;
import com.example.BikeRentalSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
public class UserRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private SequenceGeneratoryService sequenceGeneratoryService;

    @GetMapping("/users")
    public ResponseEntity<List<Users>> getAllUsers(){
        List<Users> users=userService.getAllUsers();
        if(users.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else{
            return ResponseEntity.of(Optional.of(users));
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable("id") int id){
        try {
            Users users=this.userService.getUserById(id);
            return ResponseEntity.ok(users);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/users/get-by-email/{email}")
    public ResponseEntity<Users> getUserByEmail(@PathVariable("email") String email){
        try {
            Users users=this.userService.getUserByEmail(email);
            return ResponseEntity.ok(users);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Users> addUser(@RequestBody Users users){
        Users user=this.userService.addUser(users);
        return ResponseEntity.ok(user);
    }
    @PutMapping("/users/{id}")
    public ResponseEntity<Users> updateUser(@RequestBody Users users, @PathVariable("id") int id){
        Users user= this.userService.updateUser(users,id);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Users> deleteUser(@PathVariable("id") int id){
        this.userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
