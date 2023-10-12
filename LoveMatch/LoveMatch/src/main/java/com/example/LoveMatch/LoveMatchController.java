package com.example.LoveMatch;

import org.springframework.stereotype.Controller;

import java.util.Random;
import java.util.Scanner;

@Controller
public class LoveMatchController {
    private int loveMatchPercentage;

    public LoveMatchController(){
        Random random = new Random();
        loveMatchPercentage = random.nextInt(101);
    }

    public void generateLoveMatch() {
        //MVP example
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the LoveMatch Generator");
        System.out.println("Enter First Name");
        String name1 = scanner.nextLine();

        System.out.println("Enter Second Name");
        String name2 = scanner.nextLine();

        Random random = new Random();
        loveMatchPercentage = random.nextInt(101);

        System.out.println("The match between " + name1 + " and " + name2 + " is " + loveMatchPercentage + "%");
    }

    public int getLoveMatchPercentage() {
        return loveMatchPercentage;
    }
}
