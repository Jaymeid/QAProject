package com.example.LoveMatch;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.Random;
import java.util.Scanner;

@Controller
public class LoveMatchController {

    public int generateLoveMatch(){
        Random random = new Random();
        int loveMatchPercentage = random.nextInt(101);
        return loveMatchPercentage;
    }

    @PostMapping("/calculateLoveMatch")
    public String calculateLoveMatch(Model model,
                                     @RequestParam String name1,
                                     @RequestParam String name2) {
        // Calculate love match percentage (for demonstration purposes, you can implement your own logic)
        Random random = new Random();
        int loveMatchPercentage = random.nextInt(101);

        // Store the love match percentage in a model attribute
        model.addAttribute("loveMatchPercentage", loveMatchPercentage);
        return "result";
    }




}
