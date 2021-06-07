package com.TelegramBot.GoogleApiLessonsBlocksGson;


import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.Sheet;

import java.io.IOException;
import java.util.List;

public class TelegramBlockWithLessons extends TelegramGoogleApi {

    public static void getAll(List<Sheet> sheets, String spreadSheetId, Sheets service) throws IOException {
        for (int j = 0; j < sheets.size(); j++) {
            if (numberBlock == 0) {
                numberBlock = j +1;
            }
            Sheet sheet = sheets.get(numberBlock-1);
            blockName1 = sheet
                    .getProperties()
                    .getTitle();
            blocksNames.add(blockName1);// blockName - назание Гугл-Листа
            //blocksNames.get(0)-номер листа//!A1:A-название блока обучения
            response5 = service
                    .spreadsheets()
                    .values()
                    .get(spreadSheetId, blocksNames.get(numberBlock-1)) //blocksNames.get(0)-номер лисат//!A1:A-название блока обучения
                    .execute();
            values2.add(response5);


            List<List<Object>> response5Values = response5.getValues();
            int count = 1;
            lessons.add("\n" + blockName1 + "\n");
            ask.add("\n" + blockName1 + "\n");
            question.add("\n" + blockName1 + "\n");
            videoURL.add("\n" + blockName1 + "\n");
            numberBlock++;
            for (int i = 3; i < response5Values.size(); i = i + 2) {
                responseValues.add(response5Values.get(i));
                blockName = (String) response5Values.get(0).get(0);
                String a = (String) response5Values.get(i).get(0);
                String b = (String) response5Values.get(i).get(1);
                String c = (String) response5Values.get(i).get(2);
                question.add("\nВопрос №" + a);
                ask.add("\nОтвет №" + count + ". " + b);
                videoURL.add("\nСсылка на видео к уроку №" + count + ". " + c);
                lessons.add(" \n Урок №" + count + "\nВопрос \n" + a + "\nОтвет :\n"  + " " + b + "\nСсылка на видео к уроку "  + "\n " + c+"\n");
                count++;
                row.add("\n" + a + "\n" + b + "\n" + c);
                rows.add(row);
                // allBloc.add(" \n Урок №"+count+" \nВопрос№"+a+" \nОтвет: "+b+" \nСсылка на видео: "+c);
                // String allR = "\nВопрос: " + r + " \nОтвет: " + r1 + " \nВидео: " + r2 + "\n";
                //String all2 = " Вопрос: " + r + " Ответ: " + r1 + " Видео: " + r2;
            }

//                  break;

        }
    }

}