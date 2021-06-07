package com.TelegramBot;

import org.telegram.telegrambots.meta.api.objects.Update;

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
