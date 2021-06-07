package com.TelegramBot.GoogleApi.Lessons.Blocks.Gson;

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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class TelegramGoogleApi {
    private static final String CREDENTIALS_FILE = "/credential.json";
    private static final String PROPERTIES_FILE = "/applic.properties";
    private static final JacksonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static Properties appProp;
    public static Sheets service;
    public static ValueRange response5;
    public static int numberBlock;
    public static int numberLesson;

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
    public static String spreadSheetId;
    public static Spreadsheet spreadsheetMetadata;
    static List<Sheet> sheets = new ArrayList<>();

    public static void mainGoogle() throws GeneralSecurityException, IOException {
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        spreadSheetId = getProperties().getProperty("spreadsheet_id");
        service = new Sheets.Builder(httpTransport, JSON_FACTORY, getCredentials())
                .setApplicationName("Google Api")
                .build();
        spreadsheetMetadata = service.spreadsheets().get(spreadSheetId).execute();
        List<Sheet> sheets = spreadsheetMetadata.getSheets();


        //TelegramStudyBlocks.getBlocks(sheets, spreadSheetId, service,numberBlock);
        // TelegramLessons.getLessons(response5, responseValues);
        TelegramBlockWithLessons.getAll(sheets, spreadSheetId, service);

        WriteToJson.Writer2ToString(row);
        WriteToJson.Writer1ToArray(responseValues);
        WriteToJson.Writer3ToObject(values2);

    }

    private static HttpRequestInitializer getCredentials() throws IOException {
        InputStream in = TelegramGoogleApi.class.getResourceAsStream(CREDENTIALS_FILE);
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
        InputStream in = TelegramGoogleApi.class.getResourceAsStream(PROPERTIES_FILE);
        if (in == null) {
            throw new FileNotFoundException("No FileAgain" + PROPERTIES_FILE);
        }
        appProp = new Properties();
        appProp.load(in);
        return appProp;
    }

    public static String oneLesson(int numberLesson, int numberBlock) throws GeneralSecurityException, IOException {
        TelegramGoogleApi.numberBlock = numberBlock - 1;
        TelegramGoogleApi.numberLesson = numberLesson;
        mainGoogle();
        // System.out.println(lessons.get(TelegramGoogleApi.numberLesson));
        return (String) lessons.get(TelegramGoogleApi.numberLesson);
    }

    public static void getBlock(int numberBlock) throws IOException, GeneralSecurityException {
        TelegramGoogleApi.numberBlock = numberBlock - 1;
        System.out.println(blocksNames.get(TelegramGoogleApi.numberBlock));
        System.out.println(values2.get(TelegramGoogleApi.numberBlock));
        // System.out.println(TelegramStudyBlocks.response5.get(TelegramStudyBlocks.blocksNames.get(TelegramGoogleApi.numberBlock)));
        //System.out.println(TelegramStudyBlocks.getBlocks(sheets, spreadSheetId, service,TelegramGoogleApi.numberBlock)););
        // TelegramStudyBlocks.getBlocks(sheets, spreadSheetId, service,TelegramGoogleApi.numberBlock);

    }
}
