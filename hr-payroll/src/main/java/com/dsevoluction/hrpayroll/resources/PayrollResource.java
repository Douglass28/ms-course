package com.dsevoluction.hrpayroll.resources;

import com.dsevoluction.hrpayroll.entities.Payment;
import com.dsevoluction.hrpayroll.services.PayrollService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/payments")
public class PayrollResource {

    @Autowired
    private PayrollService service;

    @HystrixCommand(fallbackMethod = "getPaymentAlternative")
    @GetMapping(value = "/{workerId}/days/{days}")
    public ResponseEntity<Payment> getPayment(@PathVariable Integer workerId, @PathVariable Integer days){
        Payment payment = service.getPayment(workerId, days);
        return ResponseEntity.ok(payment);
    }

    public ResponseEntity<Payment> getPaymentAlternative( Integer workerId, Integer days){
        Payment payment = new Payment("Bran", 400.0,  days);
        return ResponseEntity.ok(payment);
    }
}
