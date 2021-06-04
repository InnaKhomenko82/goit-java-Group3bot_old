package com.TelegramBot;

import org.telegram.telegrambots.meta.api.objects.Update;

public class MessageBot extends RegistrationUser{
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