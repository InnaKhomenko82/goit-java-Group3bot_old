package com.TelegramBot.GoogleApi.Lessons.Blocks.Gson;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.Sheet;

import java.io.IOException;
import java.util.List;

public class TelegramStudyBlocks extends TelegramGoogleApi {

    public static void getBlocks(List<Sheet> sheets, String spreadSheetId, Sheets service,int numberBlock) throws IOException {
        for (int j = 0; j < sheets.size(); j++) {
            if (numberBlock == 0) {
                numberBlock = j+1;
            }
            Sheet sheet = sheets.get(numberBlock-1);
            TelegramGoogleApi.blockName = sheet
                    .getProperties()
                    .getTitle();
            TelegramGoogleApi.blocksNames.add(TelegramGoogleApi.blockName);// blockName - назание Гугл-Листа
            //blocksNames.get(0)-номер листа//!A1:A-название блока обучения
            TelegramGoogleApi.response5 = service
                    .spreadsheets()
                    .values()
                    .get(spreadSheetId, TelegramGoogleApi.blocksNames.get(numberBlock-1)) //blocksNames.get(0)-номер лисат//!A1:A-название блока обучения
                    .execute();
            TelegramGoogleApi.values2.add(TelegramGoogleApi.response5);

        }
//        System.out.println(TelegramGoogleApi.blocksNames);
//        System.out.println(TelegramGoogleApi.response5.getValues());
    }
}
