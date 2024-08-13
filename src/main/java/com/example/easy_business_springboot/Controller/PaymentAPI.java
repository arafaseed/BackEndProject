package com.example.easy_business_springboot.Controller;

import com.example.easy_business_springboot.Model.License;
import com.example.easy_business_springboot.Model.Payment;
import com.example.easy_business_springboot.Repository.LicenseRepo;
import com.example.easy_business_springboot.Repository.PaymentRepo;
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


    //API FOR Update Payment status

    @PutMapping("/updatePaymentStatus/{paymentId}")
    public ResponseEntity<String> updatePaymentStatus(@PathVariable("paymentId") Long payment_id,@RequestBody Payment payment) {

        Optional<Payment> paymentOptional = paymentRepo.findById(payment_id);

        if (paymentOptional.isPresent()) {
            Payment payment1 = paymentOptional.get();
            payment1.setStatus(payment.getStatus());
            paymentRepo.save(payment);
            return ResponseEntity.ok("Payment status updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Payment not found");
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

    @PatchMapping("/update/{payment_id}")
    public ResponseEntity<String> updatePaymentStatus(@PathVariable Long payment_id) {
        Payment payment = paymentRepo.findById(payment_id).orElseThrow(); // Retrieve the payment entity
        payment.setStatus("Renew"); // Update the status field
        paymentRepo.save(payment); // Save the changes
        return ResponseEntity.ok("Payment status updated successfully");
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


//    @PostMapping("/addPayments")
//    public ResponseEntity<Payment> pay(@RequestBody Payment payment) {
//        // Generate control number based on license ID
//        payment.setControl_number("CN-" + System.currentTimeMillis());
//
//        // Save payment
//        Payment savedPayment = paymentRepo.save(payment);
//        return ResponseEntity.ok(savedPayment);
//
//}

//    @GetMapping("/control_number/{licence_id}")
//    public ResponseEntity<String>  generate(@PathVariable Long licence_id){
//        License license = licenseRepo.findById(licence_id).orElseThrow();
//        Payment payment = new Payment();
//        payment.setLicense(license);
//        payment.setAmount(0L);
//
//        paymentRepo.save(payment);
//        return  new ResponseEntity<>(payment.getControl_number(),HttpStatus.OK);
//
//    }

//    @GetMapping("/control_number/{licence_id}/{years}")
//    public ResponseEntity<String> generate(@PathVariable Long licence_id, @PathVariable int years){
//        License license = licenseRepo.findById(licence_id).orElseThrow();
//        Long year = Long.valueOf(years);
//        Payment payment = paymentRepo.findByLicenseAndYears(license, year);
//        Payment endDates = paymentRepo.getEndLastByLicenceId(licence_id);
//
//
//        if(payment == null) {
//            payment = new Payment();
//            payment.setLicense(license);
//            payment.setAmount(0L);
//            payment.setYears(year);
//
//            LocalDate currentDate = LocalDate.now();
//            LocalDate endDate = currentDate.plusYears(years);
//
//            payment.setStartDate(currentDate);
//            payment.setEndDate(endDate);
//        } else {
//            if(endDates.compareTo(LocalDate.now()) < 0) {
//                payment.setStartDate(LocalDate.now());
//                payment.setEndDate(LocalDate.now().plusYears(years));
//            }
//        }
//
//        paymentRepo.save(payment);
//        return new ResponseEntity<>(payment.getControl_number(), HttpStatus.OK);
//    }
//
//    @GetMapping("/control_number/{licence_id}/{years}")
//    public ResponseEntity<String> generate(@PathVariable Long licence_id, @PathVariable int years){
//        License license = licenseRepo.findById(licence_id).orElseThrow();
//        Payment payment = paymentRepo.findByLicenseAndYears(license, (long) years);
//        Payment endDates = paymentRepo.getEndLastByLicenceId(licence_id);
//
//        if(payment == null) {
//            payment = new Payment();
//            payment.setLicense(license);
//            payment.setAmount(0L);
//            payment.setYears((long) years);
//
//            LocalDate currentDate = LocalDate.now();
//            LocalDate endDate = currentDate.plusYears(years);
//
//            payment.setStartDate(currentDate);
//            payment.setEndDate(endDate);
//        } else {
//            if (endDates.compareTo(LocalDate.now())) {
//                payment.setStartDate(LocalDate.now());
//                payment.setEndDate(LocalDate.now().plusYears(years));
//            }
//        }
//
//        payment.setControl_number("CN-" + System.currentTimeMillis());
//        paymentRepo.save(payment);
//        return new ResponseEntity<>(payment.getControl_number(), HttpStatus.OK);
//    }




}
