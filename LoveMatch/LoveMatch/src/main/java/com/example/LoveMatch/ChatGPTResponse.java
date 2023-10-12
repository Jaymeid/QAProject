package com.example.LoveMatch;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import com.example.LoveMatch.LoveMatchController;
import com.example.LoveMatch.Keys;
public class ChatGPTResponse {
    String name1;
    String name2;
    String prompt;

    String[] descriptors;

    String starSign1;

    String starSign2;



    Map<String, String[]> compatibilityMap = new HashMap<>();


    LoveMatchController loveMatchController;
    public ChatGPTResponse(String firstName, String secondName, String firstStarSign, String secondStarSign){
        name1 = firstName;
        name2 = secondName;
        starSign1 = firstStarSign;
        starSign2 = secondStarSign;

        descriptors = new String[]{"funny", "endearing", "serious", "sad"};

        loveMatchController = new LoveMatchController();

        compatibilityMap.put("Aries", new String[]{"Leo", "Sagittarius"});
        compatibilityMap.put("Taurus", new String[]{"Virgo", "Capricorn"});
        compatibilityMap.put("Gemini", new String[]{"Libra", "Aquarius"});
        compatibilityMap.put("Cancer", new String[]{"Scorpio", "Pisces"});
        compatibilityMap.put("Leo", new String[]{"Aries", "Sagittarius"});
        compatibilityMap.put("Virgo", new String[]{"Taurus", "Capricorn"});
        compatibilityMap.put("Libra", new String[]{"Gemini", "Aquarius"});
        compatibilityMap.put("Scorpio", new String[]{"Cancer", "Pisces"});
        compatibilityMap.put("Sagittarius", new String[]{"Aries", "Leo"});
        compatibilityMap.put("Capricorn", new String[]{"Taurus", "Virgo"});
        compatibilityMap.put("Aquarius", new String[]{"Gemini", "Libra"});
        compatibilityMap.put("Pisces", new String[]{"Cancer", "Scorpio"});
    }

    private boolean checkCompatibility(){
        if (compatibilityMap.containsKey(starSign1)) {
            String[] compatibleSigns = compatibilityMap.get(starSign1);
            if (contains(compatibleSigns, starSign2)) {
                return true;
            }
            return false;
        }

        return false;
    }

    private static boolean contains(String[] arr, String target) {
        for (String element : arr) {
            if (element.equals(target)) {
                return true;
            }
        }
        return false;
    }
    public String MakeRequest(){
        String url = "https://api.openai.com/v1/chat/completions";
        String apiKey = Keys.getOPENAIKEY();
        System.out.println("KEY HERE: " + apiKey);
        String model = "gpt-3.5-turbo";

        try {
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + apiKey);
            connection.setRequestProperty("Content-Type", "application/json");

            // The request body
            String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + formatRequest() + "\"}]}";
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

    public String formatRequest(){
        boolean bisCompatible = checkCompatibility();
        String horoscopeSentence = "";

        if(bisCompatible){
            horoscopeSentence = "Speak about how their horoscopes, " + starSign1 + " and " + starSign2 + ", are compatible";
        }
        else{
            horoscopeSentence = "Speak about how their horoscopes, " + starSign1 + " and " + starSign2 + ", are not compatible";
        }
        return "Write a " + getRandomDescriptor() + " paragraph about how " + name1 + " and " + name2 + " love each other " + loveMatchController.generateLoveMatch() + "%" + horoscopeSentence;
    }

}
