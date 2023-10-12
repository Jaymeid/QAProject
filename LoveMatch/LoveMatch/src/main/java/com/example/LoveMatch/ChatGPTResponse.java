package com.example.LoveMatch;

public class ChatGPTResponse {
    String name1;
    String name2;

    public ChatGPTResponse(String firstName, String secondName){
        name1 = firstName;
        name2 = secondName;
    }

    public void MakeRequest(){
        String url = "https://api.openai.com/v1/chat/completions";
        String apiKey = "";
        String model = "gpt-3.5-turbo";
    }

}
