package com.GoIt.TelegramBot;

import com.GoIt.TelegramBot.GoogleApi.Lessons.Blocks.Gson.TelegramGoogleApi;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class MainTelegram {
    public static void main(String[] args) throws GeneralSecurityException, IOException, TelegramApiException {
        TelegramGoogleApi.mainGoogle();

        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new MessageBot());

    }
}
