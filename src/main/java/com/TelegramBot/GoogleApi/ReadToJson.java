package com.TelegramBot.GoogleApi;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadToJson extends GoogleApiToJson {
    public static void main(String[] args) throws FileNotFoundException {
        Read1();
    }

    public static void Read1() throws FileNotFoundException {
        List<Lessons> lesson = new ArrayList<>();
        Scanner sc = new Scanner("/resources/GoogleApiToJson.json");
        String question = sc.nextLine();
        String ask = sc.nextLine();
        String video = sc.nextLine();
//        if (sc.hasNext()){
//
//        }

        //String json = "";
//        FileReader reader = new FileReader("src/main/resources/GoogleApiToJson3.json");
//        Gson g = new Gson();
//Lessons lessons = g.fromJson(reader,Lessons.class);
//lesson.add(lessons);
//        System.out.println(lesson);
// Кастим obj в JSONObject
       // Scanner sc = new Scanner(reader);
        //String nameAge = sc.nextLine();
        //while (sc.hasNext()) {
          //  Lessons lessons = new Lessons(sc.next(), sc.next(), sc.next());
          //  Lessons lesson = g.fromJson(lessons, Lessons.class);

         //   lesson.add(lessons);
//        }
//        System.out.println(lesson);
    }
}
