package com.TelegramBot;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.Sheet;

import java.io.IOException;
import java.util.List;

public class Blocks extends GoogleApiToJson{

    public static void getBlocks(List<Sheet> sheets, String spreadSheetId, Sheets service,int numberBlock) throws IOException {
        for (int j = 0; j < sheets.size(); j++) {
            if (numberBlock == 0) {
                numberBlock = j+1;
            }
            Sheet sheet = sheets.get(numberBlock-1);
            GoogleApiToJson.blockName = sheet
                    .getProperties()
                    .getTitle();
            GoogleApiToJson.blocksNames.add(GoogleApiToJson.blockName);// blockName - назание Гугл-Листа
            //blocksNames.get(0)-номер листа//!A1:A-название блока обучения
            GoogleApiToJson.response5 = service
                    .spreadsheets()
                    .values()
                    .get(spreadSheetId, GoogleApiToJson.blocksNames.get(numberBlock-1)) //blocksNames.get(0)-номер лисат//!A1:A-название блока обучения
                    .execute();
            GoogleApiToJson.values2.add(GoogleApiToJson.response5);

        }
//        System.out.println(GoogleApiToJson.blocksNames);
//        System.out.println(GoogleApiToJson.response5.getValues());
    }
}
