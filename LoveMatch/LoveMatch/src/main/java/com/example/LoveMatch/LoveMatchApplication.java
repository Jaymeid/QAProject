package com.example.LoveMatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.LoveMatch.ChatGPTResponse;

import java.util.Random;
import java.util.Scanner;

@SpringBootApplication
public class LoveMatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoveMatchApplication.class, args);
	}{
		ChatGPTResponse chatGPTResponse = new ChatGPTResponse("Ammarah", "Chris Evans", "Pisces", "Gemini");
		System.out.println(chatGPTResponse.formatRequest());

		System.out.println(chatGPTResponse.MakeRequest());
	}

}
