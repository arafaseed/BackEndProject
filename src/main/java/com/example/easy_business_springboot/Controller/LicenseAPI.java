package com.example.easy_business_springboot.Controller;

import com.example.easy_business_springboot.Model.Customer;
import com.example.easy_business_springboot.Model.License;
import com.example.easy_business_springboot.Repository.LicenseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/licence")
@CrossOrigin(origins = "http://localhost:3000")
public class LicenseAPI {
    @Autowired
    public LicenseRepo licenseRepo;

    @PostMapping("/addLicense")
    public ResponseEntity<?> addLicense(@RequestBody License license){
        try {

            License license1 = licenseRepo.save(license);
            return new ResponseEntity<>(license1, HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/getallLicense")
    public ResponseEntity<?> getLicense(){
        try {
            List<License> licenseList = licenseRepo.findAll();
            if (licenseList.isEmpty()){
                return new ResponseEntity<>("No License  Found",HttpStatus.NOT_FOUND);
            }else{
                return new ResponseEntity<>(licenseList,HttpStatus.OK);
            }
        }catch (Exception exception){
            return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/byId/{licence_id}")
    public ResponseEntity<?> getByID(@PathVariable Long licence_id ){
        try {
            Optional<License> optionalLicense = licenseRepo.findById(licence_id);

            if (optionalLicense.isPresent()){
                return new ResponseEntity<>(optionalLicense,HttpStatus.OK);
            }else {
                    return new ResponseEntity<>("No License Found",HttpStatus.NOT_FOUND);
            }
        }catch (Exception exception){
            return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("update/{licence_id}")
    public ResponseEntity<?> updateLicense(@RequestBody License license,@PathVariable Long licence_id){

        try {
            if (licenseRepo.findById(licence_id).isPresent()){
                license.setLicence_id(licence_id);
                License license1 = licenseRepo.save(license);
                return new ResponseEntity<>(license1,HttpStatus.OK);
            }else {
                return new ResponseEntity<>("No Customer found",HttpStatus.NOT_FOUND);
            }
        }catch (Exception exception){
            return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/delete/{licence_id}")
    public ResponseEntity<?> deleteLicense(@PathVariable Long licence_id){
        try {
            licenseRepo.deleteById(licence_id);
            return new ResponseEntity<>("License Deleted Successfull",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("something went wrong please wait",HttpStatus.BAD_REQUEST);
        }
    }

}
