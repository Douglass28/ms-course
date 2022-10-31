package com.dsevoluction.hrpayroll.services;

import com.dsevoluction.hrpayroll.entities.Payment;
import org.springframework.stereotype.Service;

@Service
public class PayrollService {

    public Payment getPayment(Integer workerId, Integer days ){
        return new Payment("douglas", 50.5, days);

    }
}
