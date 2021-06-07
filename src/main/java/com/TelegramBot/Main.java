package com.TelegramBot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class Main {
    public static void main(String[] args) throws GeneralSecurityException, IOException, TelegramApiException {
        // GoogleApiToJson.oneLesson(3,2);
        GoogleApiToJson.mainGoogle();
        //   System.out.println(GoogleApiToJson.blocksNames.get(0));
      //  GoogleApiToJson.getBlock(2);
        //System.out.println(GoogleApiToJson.lessons.get(2));
        // System.out.println(GoogleApiToJson.ask);
        // System.out.println(GoogleApiToJson.lessons);
        // System.out.println(GoogleApiToJson.ask);
        // System.out.println(GoogleApiToJson);
      //  GoogleApiToJson.oneLesson(3,3);
      //  TelegramUser telegramUser = new TelegramUser();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new MessageBot());
       // TrainingUser trainingUser = new TrainingUser();
        //MessageBot messageBot = new MessageBot();


       // RegistrationUser registrationUser = new RegistrationUser();

    }
}
