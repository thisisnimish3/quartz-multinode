package com.example.quartz.service;

import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AService {

    public void printTime() {
        System.out.println("Job A2:  Hi there ! " + new Date());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
