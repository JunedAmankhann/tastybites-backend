package com.tastybites.controller;
import com.tastybites.entity.User;
import com.tastybites.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @PostMapping("/register")
    public User registerUser(@RequestBody User user){
        return userRepository.save(user);
    }
    @PostMapping("/login")
    public Object loginUser(@RequestBody User user){
        User existingUser=userRepository.findByEmail(user.getEmail()).orElse(null);
        if(existingUser == null){
            return "User Not Found";
        }
        if(!existingUser.getPassword().equals(user.getPassword())){
            return "Invalid Password";
        }
        return existingUser;
    }
}
