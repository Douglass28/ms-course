package com.dsevoluction.hrpayroll.services;

import com.dsevoluction.hrpayroll.entities.Payment;
import com.dsevoluction.hrpayroll.entities.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class PayrollService {

    @Value("${hr-worker.host}")
    private String workerHost;
    @Autowired
    private RestTemplate restTemplate;

    public Payment getPayment(Integer workerId, Integer days ){
        Map<String, String> uriVaribles = new HashMap<>();
        uriVaribles.put("id", ""+ workerId);
        Worker worker = restTemplate.getForObject(workerHost + "/workers/{id} ", Worker.class, uriVaribles);
        return new Payment(worker.getName(), worker.getDailyIncome(), days);

    }
}
