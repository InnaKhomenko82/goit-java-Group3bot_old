package com.GoIt.TelegramBot.GoogleApi.Lessons.Blocks.Gson;

import com.google.api.services.sheets.v4.model.ValueRange;

import java.util.List;

public class TelegramLessons extends TelegramGoogleApi {

    public static void getLessons(ValueRange response5, List<Object> responseValues) {
        List<List<Object>> response5Values = response5.getValues();
        int count = 1;
        numberLesson=numberLesson-1;
        for (int i = 3; i < response5Values.size(); i = i + 2) {
            responseValues.add(response5Values.get(i));
            blockName = (String) response5Values.get(0).get(0);
            String a = (String) response5Values.get(i).get(0);
            String b = (String) response5Values.get(i).get(1);
            String c = (String) response5Values.get(i).get(2);
            question.add("\nВопрос №" + a);
            ask.add("\nОтвет №" + count + ". " + b);
            videoURL.add("\nСсылка на видео к уроку №" + count + ". " + c);
            lessons.add(" \n Урок №" + count + "\nВопрос №" + a + "\nОтвет №" + count + ". " + b + "\nСсылка на видео к уроку №" + count + ". " + c);
            count++;
            row.add("\n" + a + "\n" + b + "\n" + c);
            rows.add(row);
            // allBloc.add(" \n Урок №"+count+" \nВопрос№"+a+" \nОтвет: "+b+" \nСсылка на видео: "+c);
            // String allR = "\nВопрос: " + r + " \nОтвет: " + r1 + " \nВидео: " + r2 + "\n";
            //String all2 = " Вопрос: " + r + " Ответ: " + r1 + " Видео: " + r2;
        }
        // System.out.println(row);
    }
}
