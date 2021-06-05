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
    static List<List<Object>> values = new ArrayList<>();

    static String blockName;
    static List<Object> question = new ArrayList<>();
    static List<Object> ask = new ArrayList<>();
    static List<Object> videoURL = new ArrayList<>();
    static List<Object> lessons = new ArrayList<>();


    public static void main(String[] args) throws GeneralSecurityException, IOException {
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        final String spreadSheetId = getProperties().getProperty("spreadsheet_id");


        Sheets service = new Sheets.Builder(httpTransport, JSON_FACTORY, getCredentials())
                .setApplicationName("Google ApiTwo")
                .build();


        Spreadsheet spreadsheetMetadata = service.spreadsheets().get(spreadSheetId).execute();

        List<Sheet> sheets = spreadsheetMetadata.getSheets();


        // sheets.forEach(sheet -> ((SheetProperties) (sheet.get("properties"))).get("title"));
        if (sheets != null) {
            String title = sheets.get(0)
                    .getProperties()
                    .getTitle();
            //  System.out.println(title); // nameDocList - JavaScrip
        }


// Список Вопрос/Ответ/Ссылка
        ValueRange response4 = service
                .spreadsheets()
                .values()
                .get(spreadSheetId, "JavaScrip!A1:C")////!A1:A-название блока обучения
                .execute();

        List<List<Object>> lesson = response4.getValues();
        for (int i = 3; i < lessons.size()-1; i = i + 2) {
            lessons.add("Урок №" + lesson.get(i));
            //lesson.add("Вопрос: " + lessons.get(i));
        }
        System.out.println(lessons);

// Список Вопрос/Ответ/Ссылка

// Список ссылок на видео
        ValueRange response3 = service
                .spreadsheets()
                .values()
                .get(spreadSheetId, "JavaScrip!C1:C")////!A1:A-название блока обучения
                .execute();

        List<List<Object>> video = response3.getValues();
        for (int i = 3; i < video.size(); i = i + 2) {
            videoURL.add("Ссылка на видео: " + video.get(i).get(0));
            //videoURL.add("Ссылка на видео: " + video.get(i));
        }
       // System.out.println(videoURL);

// Список ссылок на видео

// Список ответов
        ValueRange response2 = service
                .spreadsheets()
                .values()
                .get(spreadSheetId, "JavaScrip!B1:B")////!A1:A-название блока обучения
                .execute();

        List<List<Object>> asks = response2.getValues();
        for (int i = 3; i < asks.size(); i = i + 2) {
            ask.add("Ответ: " + asks.get(i).get(0));
             //ask.add("Ответ: " + asks.get(i));
        }
       // System.out.println(ask);

// Список ответов

// Список вопросов и название Блока
        ValueRange response1 = service
                .spreadsheets()
                .values()
                .get(spreadSheetId, "JavaScrip!A1:A")////!A1:A-название блока обучения
                .execute();

        List<List<Object>> questions = response1.getValues();
        blockName = (String) questions.get(0).get(0);
        for (int i = 3; i < questions.size(); i = i + 2) {
            question.add("Вопрос №" + questions.get(i).get(0));
           // question.add("Вопрос: " + questions.get(i));
        }
        // System.out.println(question);
// Список вопросов и название Блока

        ValueRange response = service
                .spreadsheets()
                .values()
                .get(spreadSheetId, "JavaScrip!A4:C")////!A1:A-название блока обучения
                .execute();

        List<List<Object>> values = response.getValues();
        // List<List<Object>> values = new LinkedList<>();
        if (values == null || values.isEmpty()) {
            System.out.println("No data found");
            return;
        }

        for (List row : values) {
            if (row.isEmpty()) {
                continue;
            }
            //String vi = "Video: ";

            String r = (String) row.get(0);
            String r1 = (String) row.get(1);
            String r2 = (String) row.get(2);
            // String allR = "\nВопрос: " + r + " \nОтвет: " + r1 + " \nВидео: " + r2 + "\n";
            String all2 = " Вопрос: " + r + " Ответ: " + r1 + " Видео: " + r2;
            rows.add(row);
            values2.add(all2);

            //System.out.printf("Вопрос: %s \n Ответ: %s \n Видео: %s \n\n", row.get(0), row.get(1), row.get(2));
        }
        // System.out.println(range);
        WriteToJson.Writer1ToArray(rows);
        WriteToJson.Writer2ToString(values2);
        WriteToJson.Writer3ToObject(values);
        //ReadToJson.Read1();
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
