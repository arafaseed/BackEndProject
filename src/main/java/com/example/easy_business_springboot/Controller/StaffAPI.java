package com.example.easy_business_springboot.Controller;

import com.example.easy_business_springboot.Model.License;
import com.example.easy_business_springboot.Model.Staff;
import com.example.easy_business_springboot.Repository.StaffRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/staff")
//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin("*")
public class StaffAPI {
    @Autowired
    public StaffRepo staffRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/addStaff")
    public ResponseEntity<?> addLicense(@RequestBody Staff staff){
        try {
            String encryptedPassword = new BCryptPasswordEncoder().encode(staff.getPassword());
            staff.setPassword(encryptedPassword);

            Staff staff1 = staffRepo.save(staff);
            return new ResponseEntity<>(staff1, HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/getallSatff")
    public ResponseEntity<?> getStaff(){
        try {
            List<Staff> staffList = staffRepo.findAll();
            if (staffList.isEmpty()){
                return new ResponseEntity<>("No staff  Found",HttpStatus.NOT_FOUND);
            }else{
                return new ResponseEntity<>(staffList,HttpStatus.OK);
            }
        }catch (Exception exception){
            return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/byId/{UserID}")
    public ResponseEntity<?> getByID(@PathVariable Long UserID ){
        try {
            Optional<Staff> optionalStaff = staffRepo.findByUserID(UserID);

            if (optionalStaff.isPresent()){
                return new ResponseEntity<>(optionalStaff,HttpStatus.OK);
            }else {
                return new ResponseEntity<>("No Staff Found",HttpStatus.NOT_FOUND);
            }
        }catch (Exception exception){
            return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("update/{UserID}")
    public ResponseEntity<?> updateLicense(@RequestBody Staff staff,@PathVariable Long UserID){

        try {
            if (staffRepo.findByUserID(UserID).isPresent()){
                staff.setUserID(UserID);
                Staff staff1 = staffRepo.save(staff);
                return new ResponseEntity<>(staff1,HttpStatus.OK);
            }else {
                return new ResponseEntity<>("No Customer found",HttpStatus.NOT_FOUND);
            }
        }catch (Exception exception){
            return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/delete/{UserID}")
    public ResponseEntity<?> deleteStaff(@PathVariable Long UserID){
        try {
            staffRepo.deleteById(UserID);
            return new ResponseEntity<>("Staff Deleted Successfull",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("something went wrong please wait",HttpStatus.BAD_REQUEST);
        }
    }

}

