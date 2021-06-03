package com.GoIt.TelegramBot;

import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.*;

public class TextMessageBot extends TelegramLongPollingBot {
    Map<Long, TelegramUser> telegramUserMap = new HashMap<>();
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
        if (update.hasMessage() && update.getMessage().hasText()) {
            Long chatId = update.getMessage().getChatId();
            String message = update.getMessage().getText();
            if ("/start".equals(message)) {
                if (!telegramUserMap.containsKey(chatId)) {
                    getStartMessage(update);
                } else {
                    sendMessage(chatId, "Вы уже зарегестрированы " + telegramUserMap.get(chatId).getEmail());
                }
                return;
            }
            if (getValidEmail(update)) {
                getTelegramUser(update);
                return;
            }
            getInformation(update);
        }

    }
    private void getTelegramUser(Update update) {
        String message = update.getMessage().getText();
        Long chatId = update.getMessage().getChatId();
        TelegramUser telegramUser = new TelegramUser();
        telegramUser.setEmail(message);
        telegramUserMap.put(chatId, telegramUser);
        getEmail(update);
    }

    private boolean getValidEmail(Update update) {
        String message = update.getMessage().getText();
        return message.matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,4}$");
    }
    private void getQuestionsAndAnswerFile(Update update) {
        Long chatId = update.getMessage().getChatId();
        sendMessage(chatId, "https://docs.google.com/spreadsheets/d/1JAV0NxS2ZNH6IzrFLJumwTdU6uA_Fs37cX6Nu31W9fY/edit#gid=0");
    }

    private void getStartMessage(Update update) {
        Long chatId = update.getMessage().getChatId();
        sendMessage(chatId, "Тебя приветствует JavaScriptBot.\nЧтобы пользоваться полноценно этим суперПупер ботом, тебе нужна регистрация, иначе иди и смотри мультики.");
    }

    private void getEmail(Update update) {
        Long chatId = update.getMessage().getChatId();
        String text = update.getMessage().getText();
        sendMsgAfterRegLogin(chatId, "Ваша электронная почта: " + text);
    }

    private synchronized void sendMsgAfterRegLogin(long chatId, String text) {
        SendMessage sendMessageRequest = new SendMessage();
        sendMessageRequest.setChatId(Long.toString(chatId));
        sendMessageRequest.setText(text);
        getReplayKeyBoardMarkup(sendMessageRequest);
        try {
            sendApiMethod(sendMessageRequest);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private synchronized void sendMessage(long chatId, String text) {
        SendMessage sendMessageRequest = new SendMessage();
        sendMessageRequest.setChatId(Long.toString(chatId));
        sendMessageRequest.setText(text);
        try {
            sendApiMethod(sendMessageRequest);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void getReplayKeyBoardMarkup(SendMessage sendMessage) {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();

        sendMessage.setReplyMarkup(keyboardMarkup);
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);

        KeyboardRow keyboardButtons = new KeyboardRow();
        keyboardButtons.add("/Вопросы и ответы по JavaScript");


        List<KeyboardRow> keyboardRows = new ArrayList<>();
        keyboardRows.add(keyboardButtons);
        keyboardMarkup.setKeyboard(keyboardRows);
    }

    private void getInformation(Update update) throws IOException {
        switch (update.getMessage().getText()) {
            case "/Вопросы и ответы по JavaScript":
                getQuestionsAndAnswerFile(update);
                break;

        }
    }
}
