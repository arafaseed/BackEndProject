package com.example.easy_business_springboot.Controller;

import com.example.easy_business_springboot.Model.License;
import com.example.easy_business_springboot.Model.Payment;
import com.example.easy_business_springboot.Model.User;
import com.example.easy_business_springboot.Repository.LicenseRepo;
import com.example.easy_business_springboot.Repository.PaymentRepo;
import com.example.easy_business_springboot.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping("/api/payment")
//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin("*")
public class PaymentAPI {
    @Autowired
    public PaymentRepo paymentRepo;

    @Autowired
    public LicenseRepo licenseRepo;
    @Autowired
    public UserRepo userRepo;

    @PostMapping("/addPayment")
    public ResponseEntity<?> addPayment(@RequestBody Payment payment) {
        try {
            // Generate license number based on payment_id
            Random random = new Random();
            String licenseNumber = String.format("%013d", random.nextInt());
            payment.setLicense_number(licenseNumber);

            Payment payment1 = paymentRepo.save(payment);
            return new ResponseEntity<>(payment1, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getallPayment")
    public ResponseEntity<?> getPayment() {
        try {
            List<Payment> paymentList = paymentRepo.findAll();
            if (paymentList.isEmpty()) {
                return new ResponseEntity<>("No Payment Found", HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(paymentList, HttpStatus.OK);
            }
        } catch (Exception exception) {
            return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
        }
    }



    @GetMapping("/byId/{payment_id}")
    public ResponseEntity<?> getByID(@PathVariable Long payment_id) {
        try {
            Optional<Payment> optionalPayment = paymentRepo.findById(payment_id);

            if (optionalPayment.isPresent()) {
                return new ResponseEntity<>(optionalPayment, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No Payment Found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception exception) {
            return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
        }
    }



    @DeleteMapping("/delete/{payment_id}")
    public ResponseEntity<?> deletePayment(@PathVariable Long payment_id){
        try {
            paymentRepo.deleteById(payment_id);
            return new ResponseEntity<>("Pyment Deleted Successfull",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("something went wrong please wait",HttpStatus.BAD_REQUEST);
        }
    }



    @PutMapping("/updateStatus/{payment_id}")
    public ResponseEntity<Payment> updatePaymentStatus(@PathVariable Long payment_id, @RequestBody Payment payment) {
        Payment existingPayment = paymentRepo.findById(payment_id).orElseThrow();
        existingPayment.setStatus(payment.getStatus());
        Payment updatedPayment = paymentRepo.save(existingPayment);
        return ResponseEntity.ok(updatedPayment);
    }

}
