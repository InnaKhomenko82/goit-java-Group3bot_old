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

public class GoogleApiToJson {
    private static final String CREDENTIALS_FILE = "/credential.json";
    private static final String PROPERTIES_FILE = "/application.properties";
    private static final JacksonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static Properties appProp;
    private static Sheets service;
    private static ValueRange response5;

    static List<Object> values2 = new ArrayList<>();
    static List<Object> rows = new ArrayList<>();
    static List<Object> responseValues = new ArrayList<>();
    static List<Object> row = new ArrayList<>();


    static String blockName;
    static String blockName1;


    static List<String> blocksNames = new ArrayList<>();
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

        getBlock(sheets, spreadSheetId);
        getLessons();
    }

    private static void getLessons() throws IOException {
        List<List<Object>> response5Values = response5.getValues();
        int count = 1;
        for (int i = 3; i < response5Values.size(); i = i + 2) {
            responseValues.add(response5Values.get(i));
            blockName1 = (String) response5Values.get(0).get(0);
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
        System.out.println(row);

        WriteToJson.Writer2ToString(row);
        WriteToJson.Writer1ToArray(responseValues);
        WriteToJson.Writer3ToObject(values2);
    }

    private static void getBlock(List<Sheet> sheets, String spreadSheetId) throws IOException {
        for (Sheet sheet : sheets) {
            blockName = sheet
                    .getProperties()
                    .getTitle();
            blocksNames.add(blockName);// blockName - назание Гугл-Листа
            response5 = service
                    .spreadsheets()
                    .values()
                    .get(spreadSheetId, blocksNames.get(0)) //blocksNames.get(0)-номер лисат//!A1:A-название блока обучения
                    .execute();
            values2.add(response5);
        }
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

    static Properties getProperties() throws IOException {
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
