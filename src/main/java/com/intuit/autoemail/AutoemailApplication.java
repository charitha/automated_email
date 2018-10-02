package com.intuit.autoemail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class AutoemailApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutoemailApplication.class, args);
    }

}
