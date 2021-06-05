package com.TelegramBot.GoogleApi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteToJson extends GoogleApiToJson {
    public static void main(String[] args) throws IOException {
      Writer1(rows);
      Writer2(values2);
      Writer3(values);


    }
    public static void Writer1(Object rows) throws IOException {
        // System.out.println(rows);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(rows);  //rows1
        //System.out.println(json);
        File file = new File("src/main/resources/GoogleApiToJson.json");
        FileWriter writer = new FileWriter(file);
        writer.write(String.valueOf(json));
        writer.flush();
        writer.close();
    }
    public static void Writer2(Object values2) throws IOException {
        // System.out.println(rows);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json2 = gson.toJson(values2);
        //System.out.println(json);
        File file2 = new File("src/main/resources/GoogleApiToJsonVariant2.json");
        FileWriter writer2 = new FileWriter(file2);
        writer2.write(String.valueOf(json2));
        writer2.flush();
        writer2.close();
    }
    public static void Writer3(Object values) throws IOException {
        // System.out.println(rows);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json3 = gson.toJson(values);
        //System.out.println(json);
        File file3 = new File("src/main/resources/GoogleApiToJsonVariant3.json");
        FileWriter writer3 = new FileWriter(file3);
        writer3.write(String.valueOf(json3));
        writer3.flush();
        writer3.close();
    }
}
