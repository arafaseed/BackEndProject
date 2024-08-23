package com.example.easy_business_springboot.Controller;

import com.example.easy_business_springboot.Model.Customer;
import com.example.easy_business_springboot.Model.License;
import com.example.easy_business_springboot.Model.User;
import com.example.easy_business_springboot.Repository.LicenseRepo;
import com.example.easy_business_springboot.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/licence")
//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin("*")
public class LicenseAPI {
    @Autowired
    public LicenseRepo licenseRepo;


    //API YA APPLICATION YA LICENSE
       @PostMapping("/addLicense")
    public ResponseEntity<?> addLicense(@RequestBody License license){
        try {

            License license1 = licenseRepo.save(license);
            return new ResponseEntity<>(license1, HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
        }
    }

    //API YA STATUS YA LICENSE EITHER PENDING, ACCEPTED OR CANCEL KWA AJILI YA STAFF
    @PatchMapping("{licence_id}/status")
    public ResponseEntity<License> updateStatus(@PathVariable long licence_id){
        License license = licenseRepo.findById(licence_id).orElseThrow();
        if (license.getStatus().equals("Pending")){
            license.setStatus("Accepted");
        }else {
            license.setStatus("Cancel");
        }
        licenseRepo.save(license);
        return ResponseEntity.ok(license);
    }


    //API YA STATUS YA LICENSE EITHER PENDING, ACCEPTED OR CANCEL KWA AJILI YA CUSTOMER
    @PatchMapping("/customer/{licence_id}/status")
    public ResponseEntity<License> updatesStatus(@PathVariable long licence_id){
        License license = licenseRepo.findById(licence_id).orElseThrow();
        if (license.getStatus().equals("Pending")){

            license.setStatus("Cancel");
        }
        licenseRepo.save(license);
        return ResponseEntity.ok(license);
    }

    //API YA KUUPDATE LICENSE KUBADILI MIAKA NA TAREHE
    @PatchMapping("/updateDatesAndAmount/{licence_id}")
    public ResponseEntity<?> updateDatesAndAmount(@PathVariable Long licence_id, @RequestBody License license) {
        try {
            License existingLicense = licenseRepo.findById(licence_id).orElseThrow();
            existingLicense.setCreated_date(LocalDate.now().toString());
            existingLicense.setEndDate(license.getEndDate());
            existingLicense.setAmount(license.getAmount());
            existingLicense.setNumber_ofYear(license.getNumber_ofYear());
            License updatedLicense = licenseRepo.save(existingLicense);
            return new ResponseEntity<>(updatedLicense, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
        }
    }


//API YA KUPATA LESENI ZOTE
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


    //API YA KUPATA LESENI KWA KUTUMIA ID
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

//    @PutMapping("update/{licence_id}")
//    public ResponseEntity<?> updateLicense(@RequestBody License license,@PathVariable Long licence_id){
//
//        try {
//            if (licenseRepo.findById(licence_id).isPresent()){
//                license.setLicence_id(licence_id);
//                License license1 = licenseRepo.save(license);
//                return new ResponseEntity<>(license1,HttpStatus.OK);
//            }else {
//                return new ResponseEntity<>("No Customer found",HttpStatus.NOT_FOUND);
//            }
//        }catch (Exception exception){
//            return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
//        }
//    }

    //API YA KUFUTA LESENI
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
