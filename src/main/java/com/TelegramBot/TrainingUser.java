package com.TelegramBot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TrainingUser {



    protected void userTraining(Update update) {
        Long chatId = update.getMessage().getChatId();
        String returnMessage = update.getMessage().getText();
        switch (returnMessage) {
            case "<--- HTML/CSS --->":
                break;
            case "<--- JavaScript React --->":

        }
    }
}
