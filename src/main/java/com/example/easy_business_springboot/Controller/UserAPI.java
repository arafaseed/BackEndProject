package com.example.easy_business_springboot.Controller;

import com.example.easy_business_springboot.Model.Customer;
import com.example.easy_business_springboot.Model.Staff;
import com.example.easy_business_springboot.Model.User;
import com.example.easy_business_springboot.Repository.CustomerRepo;
import com.example.easy_business_springboot.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/user")
//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin("*")
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

    //API YA KUPATA USER WOTE
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
//API YA KUPATA USER KWA KUTUMIA ID
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
    //DELETE USER
    @DeleteMapping("/delete/{userID}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userID){
        try {
            userRepo.deleteById(userID);
            return new ResponseEntity<>("User Deleted Successfull",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("something went wrong please wait",HttpStatus.BAD_REQUEST);
        }
    }

    //API YA KULOGIN
    @GetMapping("/username/{username}")
    public ResponseEntity<?> login(@PathVariable String username, @RequestParam String password){
        try {
            Optional<User> userOptional = userRepo.findByUsername(username);
            if (userOptional.isPresent()){
                User user = userOptional.get();
                // Compare the encrypted password with the one in the database
                if (new BCryptPasswordEncoder().matches(password, user.getPassword())){
                    return new ResponseEntity<>(user,HttpStatus.OK);
                }else {
                    return new ResponseEntity<>("Incorrect Cridential ",HttpStatus.NOT_FOUND);
                }
            }else {
                return new ResponseEntity<>("Not found",HttpStatus.NOT_FOUND);
            }
        }catch (Exception exception){
            return new ResponseEntity<>("No connection",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getUsername(@PathVariable String username){
        try {
            Optional<User> userOptional = userRepo.findByUsername(username);
            if (userOptional.isPresent()){
                return new ResponseEntity<>(userOptional,HttpStatus.OK);
            }else {
                return new ResponseEntity<>("No user found",HttpStatus.NOT_FOUND);
            }
        }catch (Exception exception){
            return new ResponseEntity<>("Opps",HttpStatus.BAD_REQUEST);
        }
    }

    //

    @PutMapping("update/{UserID}")
    public ResponseEntity<?> updateLicense(@RequestBody User user, @PathVariable Long UserID){

        try {
            if (userRepo.findByUserID(UserID).isPresent()){
                user.setUserID(UserID);
                User user1 = userRepo.save(user);
                return new ResponseEntity<>(user1,HttpStatus.OK);
            }else {
                return new ResponseEntity<>("No user found",HttpStatus.NOT_FOUND);
            }
        }catch (Exception exception){
            return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
        }
    }
//    @PostMapping("/update-credentials/{userID}")
//    public ResponseEntity<?> updateCredentials(@PathVariable Long userID, @RequestBody username username,@RequestBody Password password) {
//        try {
//            Optional<User> optionalUser = userRepo.findById(userID);
//            if (optionalUser.isPresent()) {
//                User user = optionalUser.get();
//                if (credentials.getUsername() != null) {
//                    user.setUsername(credentials.getUsername());
//                }
//                if (credentials.getPassword() != null) {
//                    user.setPassword(credentials.getPassword());
//                }
//                User updatedUser = userRepo.save(user);
//                return new ResponseEntity<>(updatedUser, HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
//            }
//        } catch (Exception e) {
//            return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
//        }
//    }



}
