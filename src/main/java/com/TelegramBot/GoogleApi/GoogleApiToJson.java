package com.TelegramBot.GoogleApi;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.Sheet;
import com.google.api.services.sheets.v4.model.Spreadsheet;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.common.collect.Lists;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.*;

// https://docs.google.com/spreadsheets/d/1JAV0NxS2ZNH6IzrFLJumwTdU6uA_Fs37cX6Nu31W9fY/edit#gid=0
//   1JAV0NxS2ZNH6IzrFLJumwTdU6uA_Fs37cX6Nu31W9fY

//  https://docs.google.com/spreadsheets/d/1z19e59hOvB1UoycPYnAzNEPSIUkSEswdOTY3jF_x5JM/edit#gid=1067596464
//   1z19e59hOvB1UoycPYnAzNEPSIUkSEswdOTY3jF_x5JM
public class GoogleApiToJson {
    private static final String CREDENTIALS_FILE = "/credential.json";
    private static final String PROPERTIES_FILE = "/application.properties";
    private static final JacksonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    private static Properties appProp;
    static List<Object> values2 = new ArrayList<>();
    static List<Object> rows = new ArrayList<>();
    static List<Object> response51Values = new ArrayList<>();
    static List<Object> row = new ArrayList<>();
    static Sheets service;
    static String title;
    static String blockName;
    static List<Object> question = new ArrayList<>();
    static List<Object> ask = new ArrayList<>();
    static List<Object> videoURL = new ArrayList<>();
    static List<Object> lessons = new ArrayList<>();


    public static void main(String[] args) throws GeneralSecurityException, IOException {
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        final String spreadSheetId = getProperties().getProperty("spreadsheet_id");


        service = new Sheets.Builder(httpTransport, JSON_FACTORY, getCredentials())
                .setApplicationName("Google Api")
                .build();

        Spreadsheet spreadsheetMetadata = service.spreadsheets().get(spreadSheetId).execute();

        List<Sheet> sheets = spreadsheetMetadata.getSheets();

        // sheets.forEach(sheet -> ((SheetProperties) (sheet.get("properties"))).get("title"));
        if (sheets != null) {
            title = sheets.get(0)
                    .getProperties()
                    .getTitle();
            //  System.out.println(title); // nameDocList - JavaScrip назание Гугл-Листа слева в углу
        }

        ValueRange response5 = service
                .spreadsheets()
                .values()
                .get(spreadSheetId, "JavaScrip")////!A1:A-название блока обучения
                .execute();
        List<List<Object>> response5Values = response5.getValues();
        int count = 1;

        for (int i = 3; i < response5Values.size(); i = i + 2) {
            blockName = (String) response5Values.get(0).get(0);
            String a = (String) response5Values.get(i).get(0);
            String b = (String) response5Values.get(i).get(1);
            String c = (String) response5Values.get(i).get(2);
            question.add("\nВопрос №" + a);
            ask.add("\nОтвет №" + count + ". " + b);
            videoURL.add("\nСсылка на видео к уроку №" + count + ". " + c);

            // allBloc.add(" \n Урок №"+count+" \nВопрос№"+a+" \nОтвет: "+b+" \nСсылка на видео: "+c);
            lessons.add("\nВопрос №" + a + "\nОтвет №" + count + ". " + b + "\nСсылка на видео к уроку №" + count + ". " + c);
            count++;

            // Json

            row.add(response5Values.get(i));
//            String r = (String) row.get(0);
//            String r1 = (String) row.get(0);
//            String r2 = (String) row.get(0);
            // String allR = "\nВопрос: " + r + " \nОтвет: " + r1 + " \nВидео: " + r2 + "\n";
            //String all2 = " Вопрос: " + r + " Ответ: " + r1 + " Видео: " + r2;
            rows.add(row);
          //  values2.add(all2);
            response51Values.add(response5Values.get(i));

//                WriteToJson.rows;
//                WriteToJson.Writer2ToString(values2);
//                WriteToJson.Writer3ToObject(response5Values);
            // System.out.println(something);


        }
        WriteToJson.main(ask);
        WriteToJson.main(ask);
        WriteToJson.main(rows);
       // System.out.println(ask);
    }


    private static HttpRequestInitializer getCredentials() throws IOException {
        InputStream in = GoogleApiToJson.class.getResourceAsStream(CREDENTIALS_FILE);
        if (in == null) {
            throw new FileNotFoundException("No File" + CREDENTIALS_FILE);
        }
        GoogleCredentials credentials = GoogleCredentials.fromStream(in)
                .createScoped(Lists.newArrayList(Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY)));
        return new HttpCredentialsAdapter(credentials);
    }

    private static Properties getProperties() throws IOException {
        if (appProp != null) {
            return appProp;
        }
        InputStream in = GoogleApiToJson.class.getResourceAsStream(PROPERTIES_FILE);
        if (in == null) {
            throw new FileNotFoundException("No FileAgain" + PROPERTIES_FILE);
        }
        appProp = new Properties();
        appProp.load(in);
        return appProp;
    }
}
