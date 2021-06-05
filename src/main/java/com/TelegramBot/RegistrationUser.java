package com.TelegramBot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class RegistrationUser extends TelegramLongPollingBot {
    TrainingUser trainingUser = new TrainingUser();
    KeyBoards keyBoard = new KeyBoards();
    Map<Long, TelegramUser> userMap = new HashMap<>();


    protected void sendMessageWithMarkup(long chatId, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(Long.toString(chatId));
        sendMessage.setText(text);
        sendMessage.setReplyMarkup(keyBoard.keyBoardsAfterChoiceTraining());
        try {
            sendApiMethod(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    protected void sendMessage(long chatId, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(Long.toString(chatId));
        sendMessage.setText(text);
        keyBoard.keyboardForRegistration(sendMessage);
        try {
            sendApiMethod(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void message(long chatId, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(Long.toString(chatId));
        sendMessage.setText(text);
        try {
            sendApiMethod(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void keyboardMessage(long chatId, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(Long.toString(chatId));
        sendMessage.setText(text);
        keyBoard.keyBoardsForChoosingTraining(sendMessage);
        try {
            sendApiMethod(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void startMessage(Update update) {
        Long chatId = update.getMessage().getChatId();
        sendMessage(chatId, "Привет!\n" +
                "\n" +
                "Я Telegram бот, и я помогу подготовиться к собеседованию по JavaScrip.");
        sendMessage(chatId, "Вижу, ты новенький. Для начала нажми на кнопку Зарегистрироваться, чтобы я мог сохранять твой прогресс.\n" +
                "\n" +
                "Если не видишь кнопки - нажми на (::), чтобы открыть клавиатуру");
    }

    protected void userRegistration(Update update) {
        Long chatId = update.getMessage().getChatId();
        String returnMessage = update.getMessage().getText();
        switch (returnMessage) {
            case "/start":
                if (!userMap.containsKey(chatId)) {
                    startMessage(update);
                } else {
                    message(chatId, "Вы уже зарегистрированы, ваш логин " + userMap.get(chatId).getEmail());
                }
                break;
            case "Зарегистрироваться":
                message(chatId, "Пожалуйста, введите ваш email.");
                break;
            case "<--- JavaScript React --->":
                trainingUser.userTraining(update);

        }
        if (getValidEmail(update)) {
            getTelegramUser(update);
            message(chatId, "Благодарим за регистрацию.");
            keyboardMessage(chatId, "Выберите, что вас интересует.");

        }
    }

    private void getTelegramUser(Update update) {
        String message = update.getMessage().getText();
        Long chatId = update.getMessage().getChatId();
        TelegramUser telegramUser = new TelegramUser();
        telegramUser.setEmail(message);
        userMap.put(chatId, telegramUser);
    }

    private boolean getValidEmail(Update update) {
        String text = update.getMessage().getText();
        return text.matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,4}$");
    }
     class TrainingUser{

         protected void userTraining(Update update) {
             Long chatId = update.getMessage().getChatId();
             String returnMessage = update.getMessage().getText();
             switch (returnMessage) {
                 case "<--- HTML/CSS --->":
                     break;
                 case "<--- JavaScript React --->":
                     sendMessageWithMarkup(chatId,"Вы выбрали " + returnMessage);

             }
         }
    }
}