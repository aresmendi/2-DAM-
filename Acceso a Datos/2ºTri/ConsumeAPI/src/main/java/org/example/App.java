package org.example;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class App
{
    public static void main(String[] args)
    {
       try{
           URL url = new URL("https://restcountries.com/v3.1/capital/paris");
           HttpURLConnection con = (HttpURLConnection) url.openConnection();
           con.setRequestMethod("GET");
           int responseCode = con.getResponseCode();
           if (responseCode != 200){
               throw new RuntimeException("We've problems..See the error code: " + responseCode);
           } else {
               StringBuilder info = new StringBuilder();
               try (Scanner scanner = new Scanner(url.openStream())){
                   while (scanner.hasNext()){
                       info.append(scanner.nextLine());
                   }
               }
               System.out.println(info.toString());
           }

       } catch (IOException ex){
           ex.printStackTrace();
       }
    }
}
