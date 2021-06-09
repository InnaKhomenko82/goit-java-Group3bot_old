package com.TelegramBot.Bot;

import com.TelegramBot.GoogleApi.Lessons.Blocks.Gson.TelegramGoogleApi;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

public abstract class RegistrationUser extends TelegramLongPollingBot {
    KeyBoards keyBoard = new KeyBoards();
    Map<Long, TelegramUser> userMap = new HashMap<>();


    protected void sendMessageWithMarkup(long chatId, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(Long.toString(chatId));
        sendMessage.setText(text);
        keyBoard.keyBoardForTraining(sendMessage);
        try {
            sendApiMethod(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    protected void sendMessageForRegistration(long chatId, String text) {
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

    private void returnMessage(long chatId, String text) {
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

    private void startMessage(Update update) throws InterruptedException {
        Long chatId = update.getMessage().getChatId();
        sendMessageForRegistration(chatId, "Привет!\n" +
                "\n" +
                "Я Telegram бот, и я помогу подготовиться к собеседованию по JavaScrip.");
        Thread.sleep(500);
        sendMessageForRegistration(chatId, "Вижу, ты новенький. Для начала нажми на кнопку Зарегистрироваться, чтобы я мог сохранять твой прогресс.\n" +
                "\n" +
                "Если не видишь кнопки - нажми на (::), чтобы открыть клавиатуру");
    }

    protected void userRegistration(Update update) throws InterruptedException {
        Long chatId = update.getMessage().getChatId();
        String returnMessage = update.getMessage().getText();
        switch (returnMessage) {
            case "/start":
                if (!userMap.containsKey(chatId)) {
                    startMessage(update);
                } else {
                    returnMessage(chatId, "Вы уже зарегистрированы, ваш логин " + userMap.get(chatId).getEmail());
                }
                break;
            case "⚠️ Зарегистрироваться":
                Thread.sleep(300);
                returnMessage(chatId, "Пожалуйста, введите ваш email.");
                break;

        }
        if (getValidEmail(update)) {
            getTelegramUser(update);
            returnMessage(chatId, "Благодарим за регистрацию.");
            Thread.sleep(800);
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

    class TrainingUser {
        // Выбор, что пользователь будет изучать, какой язык
        protected void userChooseTraining(Update update) throws GeneralSecurityException, IOException, InterruptedException {
            Long chatId = update.getMessage().getChatId();
            String returnMessage = update.getMessage().getText();
            switch (returnMessage) {
                case "✅ HTML/CSS":
                    break;
                case "✅ JavaScript":
                    Thread.sleep(500);
                    sendMessageWithMarkup(chatId, TelegramGoogleApi.oneLesson(1, 1));
                    break;
                case "✅ React":
                    //добавить код
                    break;
                case "\uD83D\uDD27 Настройки":
                    //добавить код
                    break;
                case "\uD83D\uDD27 Настроить напоминание":
                    //добавить код
                    break;
                case "\uD83C\uDF24 Просмотреть погоду, всегда есть о чем поговорить:)":
                    //добавить код
                    break;
            }
        }
        //Выбор для пользователя, когда выбрал изучение JavaScript
        protected void userTraining(Update update) throws GeneralSecurityException, IOException {
            Long chatId = update.getMessage().getChatId();
            String returnMessage = update.getMessage().getText();
            switch (returnMessage) {
                case "\uD83C\uDFE1 Перейти в главное меню":
                    keyboardMessage(chatId, "Вы выбрали пунк: \"Главное меню\"");
                    break;
                case "Выбрать следующий вопрос \uD83D\uDD1C":
                    sendMessageWithMarkup(chatId, TelegramGoogleApi.oneLesson(2, 2));
                    break;
                case "\uD83D\uDD19 Перейти к предыдущему вопросу":
                    //Добавить логику
            }
        }
    }
}

