//package com.GoIt.TelegramBot;
//
//import org.telegram.telegrambots.bots.TelegramLongPollingBot;
//import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
//import org.telegram.telegrambots.meta.api.objects.Update;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
//import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public abstract class RegistrationUser extends TelegramLongPollingBot {
//    Map<Long, TelegramUser> userMap = new HashMap<>();
//
//    private void sendMessage(long chatId, String text) {
//        SendMessage sendMessage = new SendMessage();
//        sendMessage.setChatId(Long.toString(chatId));
//        sendMessage.setText(text);
//        firstKeyboardsMarkup(sendMessage);
//        try {
//            sendApiMethod(sendMessage);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void message(long chatId, String text) {
//        SendMessage sendMessage = new SendMessage();
//        sendMessage.setChatId(Long.toString(chatId));
//        sendMessage.setText(text);
//        try {
//            sendApiMethod(sendMessage);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void keyboardMessage(long chatId, String text) {
//        SendMessage sendMessage = new SendMessage();
//        sendMessage.setChatId(Long.toString(chatId));
//        sendMessage.setText(text);
//        keyboardsMarkup(sendMessage);
//        try {
//            sendApiMethod(sendMessage);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void startMessage(Update update) {
//        Long chatId = update.getMessage().getChatId();
//        sendMessage(chatId, "Привет!\n" +
//                "\n" +
//                "Я Telegram бот, и я помогу подготовиться к собеседованию по JavaScrip");
//        sendMessage(chatId, "Вижу, ты новенький. Для начала нажми на кнопку Зарегистрироваться, чтобы я мог сохранять твой прогресс.\n" +
//                "\n" +
//                "Если не видишь кнопки - нажми на (::), чтобы открыть клавиатуру");
//    }
//
//    protected void userRegistration(Update update) {
//        Long chatId = update.getMessage().getChatId();
//        String returnMessage = update.getMessage().getText();
//        switch (returnMessage) {
//            case "/start":
//                if (!userMap.containsKey(chatId)) {
//                    startMessage(update);
//                } else {
//                    message(chatId, "Вы уже зарегистрированы, ваш логин " + userMap.get(chatId).getEmail());
//                }
//                break;
//            case "Зарегистрироваться":
//                message(chatId, "Пожалуйста, введите ваш email.");
//                break;
//        }
//        if (getValidEmail(update)) {
//            getTelegramUser(update);
//            message(chatId, "Благодарим за регистрацию.");
//            keyboardMessage(chatId, "Выберите, что вас интересует.");
//
//        }
//    }
//
//    private void firstKeyboardsMarkup(SendMessage sendMessage) {
//        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
//
//        sendMessage.setReplyMarkup(replyKeyboardMarkup);
//        replyKeyboardMarkup.setOneTimeKeyboard(true);
//        replyKeyboardMarkup.setResizeKeyboard(true);
//        replyKeyboardMarkup.setSelective(true);
//
//        KeyboardRow keyboardButtons = new KeyboardRow();
//        keyboardButtons.add("Зарегистрироваться");
//
//        List<KeyboardRow> keyboardRows = new ArrayList<>();
//        keyboardRows.add(keyboardButtons);
//
//        replyKeyboardMarkup.setKeyboard(keyboardRows);
//    }
//
//    private void getTelegramUser(Update update) {
//        String message = update.getMessage().getText();
//        Long chatId = update.getMessage().getChatId();
//        TelegramUser telegramUser = new TelegramUser();
//        telegramUser.setEmail(message);
//        userMap.put(chatId, telegramUser);
//    }
//
//    private boolean getValidEmail(Update update) {
//        String text = update.getMessage().getText();
//        return text.matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,4}$");
//
//
//    }
//
//    private void keyboardsMarkup(SendMessage sendMessage) {
//        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
//
//        sendMessage.setReplyMarkup(replyKeyboardMarkup);
//        replyKeyboardMarkup.setOneTimeKeyboard(false);
//        replyKeyboardMarkup.setResizeKeyboard(false);
//        replyKeyboardMarkup.setSelective(true);
//
//        KeyboardRow keyboardButtons = new KeyboardRow();
//        keyboardButtons.add("Вопросы/Ответы\n-текстовый вариант");
//        keyboardButtons.add("Вопросы/Ответы\n-видео вариант");
//        keyboardButtons.add("Настроить напоминание");
//        keyboardButtons.add("Просмотреть погоду,\nвсегда есть о чем поговорить:)");
//
//        List<KeyboardRow> keyboardRows = new ArrayList<>();
//        keyboardRows.add(keyboardButtons);
//
//        replyKeyboardMarkup.setKeyboard(keyboardRows);
//    }
//}