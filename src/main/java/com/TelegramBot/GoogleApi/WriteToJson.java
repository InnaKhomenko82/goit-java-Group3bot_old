package com.TelegramBot.GoogleApi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteToJson extends GoogleApiToJson {
    public static void main(String[] args) throws IOException {
      Writer1ToArray(rows);
      Writer2ToString(values2);
      Writer3ToObject(values);


    }
    public static void Writer1ToArray(Object rows) throws IOException {
        // System.out.println(rows);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(rows);  //rows1
        //System.out.println(json);
        File file = new File("src/main/resources/GoogleToJsonArray.json");
        FileWriter writer = new FileWriter(file);
        writer.write(String.valueOf(json));
        writer.flush();
        writer.close();
    }
    public static void Writer2ToString(Object values2) throws IOException {
        // System.out.println(rows);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json2 = gson.toJson(values2);
        //System.out.println(json);
        File file2 = new File("src/main/resources/GoogleToJsonToString.json");
        FileWriter writer2 = new FileWriter(file2);
        writer2.write(String.valueOf(json2));
        writer2.flush();
        writer2.close();
    }
    public static void Writer3ToObject(Object values) throws IOException {
        // System.out.println(rows);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json3 = gson.toJson(values);
        //System.out.println(json);
        File file3 = new File("src/main/resources/GoogleToJsonToObject.json");
        FileWriter writer3 = new FileWriter(file3);
        writer3.write(String.valueOf(json3));
        writer3.flush();
        writer3.close();
    }
}
