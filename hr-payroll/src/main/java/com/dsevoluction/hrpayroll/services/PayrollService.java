package com.dsevoluction.hrpayroll.services;

import com.dsevoluction.hrpayroll.entities.Payment;
import com.dsevoluction.hrpayroll.entities.Worker;
import com.dsevoluction.hrpayroll.feignclients.WorkerFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class PayrollService {

    @Autowired
    private WorkerFeignClient workerFeignClient;

    public Payment getPayment(Integer workerId, Integer days ){
        Worker worker = workerFeignClient.findById(workerId).getBody();
        return new Payment(worker.getName(), worker.getDailyIncome(), days);
    }
}
