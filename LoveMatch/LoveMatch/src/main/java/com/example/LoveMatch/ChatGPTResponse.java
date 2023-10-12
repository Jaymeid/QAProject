package com.example.LoveMatch;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
public class ChatGPTResponse {
    String name1;
    String name2;
    String prompt;

    String[] descriptors;
    public ChatGPTResponse(String firstName, String secondName){
        name1 = firstName;
        name2 = secondName;

        descriptors = new String[]{"funny", "endearing", "serious", "sad"};
    }

    public String MakeRequest(){
        String url = "https://api.openai.com/v1/chat/completions";
        String apiKey = "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";
        String model = "gpt-3.5-turbo";

        try {
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + apiKey);
            connection.setRequestProperty("Content-Type", "application/json");

            // The request body
            String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + prompt + "\"}]}";
            connection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(body);
            writer.flush();
            writer.close();

            // Response from ChatGPT
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;

            StringBuffer response = new StringBuffer();

            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();

            // calls the method to extract the message.
            return extractMessageFromJSONResponse(response.toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String extractMessageFromJSONResponse(String response) {
        int start = response.indexOf("content")+ 11;

        int end = response.indexOf("\"", start);

        return response.substring(start, end);

    }

    private String getRandomDescriptor(){
        Random rand = new Random();
        int randomIndex = rand.nextInt(0, descriptors.length);

        return descriptors[randomIndex];
    }

    private String formatRequest(){
        //return "Write a " + getRandomDescriptor() + " paragraph about how " + name1 + " and " + name2 + " love each other" +
        return "";
    }

}
