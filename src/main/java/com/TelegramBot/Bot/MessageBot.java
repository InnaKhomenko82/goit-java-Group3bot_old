package com.TelegramBot.Bot;


import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.api.objects.Update;

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

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        userRegistration(update);
        trainingUser.userChooseTraining(update);
        trainingUser.userTraining(update);


    }
}