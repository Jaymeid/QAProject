package com.example.LoveMatch;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Random;
import java.util.Scanner;

@Controller
public class LoveMatchController {
    private int loveMatchPercentage;


    public LoveMatchController(){
        Random random = new Random();
        loveMatchPercentage = random.nextInt(101);
    }

    @GetMapping
    public  Integer generateLoveMatch() {
        Random random = new Random();
        loveMatchPercentage = random.nextInt(101);
        return loveMatchPercentage;
    }


}
