package com.example.easy_business_springboot.Controller;

import com.example.easy_business_springboot.Model.Customer;
import com.example.easy_business_springboot.Repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customer")
//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin("*")

public class CustomerAPI {
    @Autowired
    public CustomerRepo customerRepo;
    @PostMapping("/addCustomer")
    public ResponseEntity<?> addApplication(@RequestBody Customer customer){
        try {

           Customer customer1 = customerRepo.save(customer);
            return new ResponseEntity<>(customer1, HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/getallCustomer")
    public ResponseEntity<?> getCustomer(){
        try {
            List<Customer> customerList = customerRepo.findAll();
            if (customerList.isEmpty()){
                return new ResponseEntity<>("No Application Found",HttpStatus.NOT_FOUND);
            }else{
                return new ResponseEntity<>(customerList,HttpStatus.OK);
            }
        }catch (Exception exception){
            return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/byId/{userID}")
    public ResponseEntity<?> getByID(@PathVariable Long userID ){
        try {
            Optional<Customer> optionalCustomer = customerRepo.findByUserID(userID);

            if (optionalCustomer.isPresent()){
                return new ResponseEntity<>(optionalCustomer,HttpStatus.OK);
            }else {
                return new ResponseEntity<>("No Customer Found",HttpStatus.NOT_FOUND);
            }
        }catch (Exception exception){
            return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("update/{userID}")
    public ResponseEntity<?> updateCustomer(@RequestBody Customer customer,@PathVariable Long userID){

        try {
            if (customerRepo.findById(userID).isPresent()){
                customer.setUserID(userID);
                Customer customer1 = customerRepo.save(customer);
                return new ResponseEntity<>(customer1,HttpStatus.OK);
            }else {
                return new ResponseEntity<>("No Customer found",HttpStatus.NOT_FOUND);
            }
        }catch (Exception exception){
            return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/delete/{userID}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long userID){
        try {
            customerRepo.deleteById(userID);
            return new ResponseEntity<>("Customer Deleted Successfull",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("something went wrong please wait",HttpStatus.BAD_REQUEST);
        }
    }

}
