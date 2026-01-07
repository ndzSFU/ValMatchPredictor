package com.valmatchpredictor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableScheduling
@SpringBootApplication
@RestController
public class ValMatchPredictorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ValMatchPredictorApplication.class, args);
    }

//    @GetMapping
//    public String helloWorld(){
//        return "Hello World VLR";
//    }

}
