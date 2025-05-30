package com.vicheaCoder.food_delivery_api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Welcome {

    @GetMapping("/welcome")
    public String welcome(){
        return "Hello from Vichea";
    }
    @GetMapping("/user")
    public String user(){
        return "welcome to our web service";
    }
    @GetMapping("/restaurant")
    public String restaurant(){
        return "welcome to our restaurant service";
    }
}
