package ru.example.rest.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.example.rest.entity.User;
import ru.example.rest.exception.ResourceNotFoundException;
import ru.example.rest.service.UserService;

import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserController {

   private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser() {
        List<User> users = userService.findALL();
            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user) {
            userService.save(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable long id){
            User user = userService.findById(id);
            if(user == null){
                throw new ResourceNotFoundException("Not found User with id = " + id);
            }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody User user) {
        User userUpdate = userService.findById(id);
            if(userUpdate == null){
                throw new ResourceNotFoundException("Not found User with id = " + id);
            }
            userUpdate.setFirstname(user.getFirstname());
            userUpdate.setLastname(user.getLastname());
            userUpdate.setData(user.getData());
            userUpdate.setParty(user.getParty());

            userService.update(userUpdate);
            return new ResponseEntity<>(userUpdate, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable long id) {
            userService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
