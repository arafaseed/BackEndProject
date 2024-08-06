package com.example.easy_business_springboot.Controller;

import com.example.easy_business_springboot.Model.Customer;
import com.example.easy_business_springboot.Model.User;
import com.example.easy_business_springboot.Repository.CustomerRepo;
import com.example.easy_business_springboot.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserAPI {
    @Autowired
    public UserRepo userRepo;

    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@RequestBody User user){
        try {

            User user1 = userRepo.save(user);
            return new ResponseEntity<>(user1, HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/getallUser")
    public ResponseEntity<?> getUser(){
        try {
            List<User> users = userRepo.findAll();
            if (users.isEmpty()){
                return new ResponseEntity<>("No User Found",HttpStatus.NOT_FOUND);
            }else{
                return new ResponseEntity<>(users,HttpStatus.OK);
            }
        }catch (Exception exception){
            return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/byIdUser/{userID}")
    public ResponseEntity<?> getByID(@PathVariable Long userID ){
        try {
            Optional<User> optionalUser = userRepo.findById(userID);

            if (optionalUser.isPresent()){
                return new ResponseEntity<>(optionalUser,HttpStatus.OK);
            }else {
                return new ResponseEntity<>("No User Found",HttpStatus.NOT_FOUND);
            }
        }catch (Exception exception){
            return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("updateUser/{userID}")
    public ResponseEntity<?> updateUser(@RequestBody User user,@PathVariable Long userID){

        try {
            if (userRepo.findById(userID).isPresent()){
                user.setUserID(userID);
                User user1 = userRepo.save(user);
                return new ResponseEntity<>(user1,HttpStatus.OK);
            }else {
                return new ResponseEntity<>("No User found",HttpStatus.NOT_FOUND);
            }
        }catch (Exception exception){
            return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/delete/{userID}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userID){
        try {
            userRepo.deleteById(userID);
            return new ResponseEntity<>("User Deleted Successfull",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("something went wrong please wait",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username){
        try {
            Optional<User> userOptional = userRepo.findByUsername(username);
            if (userOptional.isPresent()){
                return new ResponseEntity<>(userOptional,HttpStatus.OK);
            }else {
                return new ResponseEntity<>("User  Not Found",HttpStatus.NOT_FOUND);
            }
        }catch (Exception exception){
            return new ResponseEntity<>("No connection",HttpStatus.BAD_REQUEST);
        }
    }

}
