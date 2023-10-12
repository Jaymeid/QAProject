package com.example.LoveMatch;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

@Controller
public class LoveMatchController {


    public String generateDescription(String name1, String name2){
        ChatGPTResponse chatGPTResponse = new ChatGPTResponse(name1, name2, "Leo", "Aries");
        return chatGPTResponse.MakeRequest();
    }

    public int generateLoveMatch(){
        Random random = new Random();
        return random.nextInt(101);
    }


    @PostMapping("/outputLoveMatch")
    public String outputLoveMatch(Model model,
                                     @RequestParam String name1,
                                     @RequestParam String name2) {

        int loveMatchPercentage = generateLoveMatch();
        String loveDescription = generateDescription(name1, name2);

        model.addAttribute("loveMatchPercentage", loveMatchPercentage);
        model.addAttribute("name1", name1);
        model.addAttribute("name2", name2);
        model.addAttribute("loveDescription", loveDescription);

        return "result";
    }




}
