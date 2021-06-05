package com.TelegramBot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class MessageBot extends RegistrationUser {
    TrainingUser trainingUser = new TrainingUser();

    @Override
    public String getBotUsername() {
        return TelegramConstants.BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return TelegramConstants.BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        userRegistration(update);


    }
}