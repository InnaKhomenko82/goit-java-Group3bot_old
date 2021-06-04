package com.GoIt.TelegramBot;

import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.common.collect.Lists;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;

// https://docs.google.com/spreadsheets/d/1JAV0NxS2ZNH6IzrFLJumwTdU6uA_Fs37cX6Nu31W9fY/edit#gid=0
//   1JAV0NxS2ZNH6IzrFLJumwTdU6uA_Fs37cX6Nu31W9fY
public class Remind implements Runnable {
private static final String CREDENTIALS_FILE = "/credential.json";

    @Override
    public void run() {

    }
    private static HttpRequestInitializer getCredentials() throws IOException {
        InputStream in = Remind.class.getResourceAsStream(CREDENTIALS_FILE);
        if (in == null){
            throw new FileNotFoundException("No File"+CREDENTIALS_FILE);
        }
        GoogleCredentials credentials = GoogleCredentials.fromStream(in)
                .createScoped(Lists.newArrayList(Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY)));
        return new HttpCredentialsAdapter(credentials);
    }
}
