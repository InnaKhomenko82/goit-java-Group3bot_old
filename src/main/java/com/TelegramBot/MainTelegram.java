package com.TelegramBot;

import com.TelegramBot.GoogleApi.Lessons.Blocks.Gson.TelegramGoogleApi;
import com.TelegramBot.GoogleApi.Lessons.Blocks.Gson.WriteToJson;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainTelegram {
    public static void main(String[] args) throws GeneralSecurityException, IOException, TelegramApiException {
        //Метод парсит гуглТаблицу и записывает в АрейЛисты
        TelegramGoogleApi.mainGoogle();

        //Метод записывает информацию из Гугл таблицы в json.формат ToString
        WriteToJson.Writer2ToString(TelegramGoogleApi.row);

        //Метод записывает информацию из Гугл таблицы в json.формат ToArray
        WriteToJson.Writer1ToArray(TelegramGoogleApi.responseValues);

        //Метод записывает информацию из Гугл таблицы в json.формат ToObject
        WriteToJson.Writer3ToObject(TelegramGoogleApi.values2);

        //Метод запуска ТелеграмБота
       // TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
       // telegramBotsApi.registerBot(new MessageBot());


    }
}
