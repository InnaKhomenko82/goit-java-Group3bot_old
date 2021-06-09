package com.TelegramBot;

import com.TelegramBot.Bot.MessageBot;
import com.TelegramBot.GoogleApi.Lessons.Blocks.Gson.TelegramGoogleApi;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class Main {
    public static void main(String[] args) throws TelegramApiException, IOException, GeneralSecurityException {
        TelegramGoogleApi.mainGoogle();


        //Метод запуска ТелеграмБота
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new MessageBot());
    }
}
