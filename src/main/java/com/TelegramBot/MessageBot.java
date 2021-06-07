package com.TelegramBot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private void keyboardsMarkup(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        replyKeyboardMarkup.setResizeKeyboard(false);
        replyKeyboardMarkup.setSelective(true);

        KeyboardRow keyboardButtons = new KeyboardRow();
        keyboardButtons.add("Вопросы/Ответы\n-текстовый вариант");
        keyboardButtons.add("Вопросы/Ответы\n-видео вариант");
        keyboardButtons.add("Настроить напоминание");
        keyboardButtons.add("Просмотреть погоду,\nвсегда есть о чем поговорить:)");

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        keyboardRows.add(keyboardButtons);

        replyKeyboardMarkup.setKeyboard(keyboardRows);
// Предлагаю клавиатуру немного подправить, чтобы каждую клавишу было хорошо видно и удобнее)) P.S.Женя
        // Ниже код с кнопками, можно добавить кнопки как в 1 строку так и с новой строки


//        List<KeyboardRow> keyboard = new ArrayList<>();
//
//        KeyboardRow keyboardFirstRow = new KeyboardRow();
//        keyboardFirstRow.add(new KeyboardButton("Первая строка"));  // Уроки(Вопрос/Ответ)
//
//        KeyboardRow keyboardSecondRow = new KeyboardRow();
//        keyboardSecondRow.add(new KeyboardButton("Вторая строка"));  // Уроки(Видео)
//
//        KeyboardRow keyboardThreeRow = new KeyboardRow();
//        keyboardThreeRow.add(new KeyboardButton("Третья строка"));   //Настроить напоминание
//
//        keyboard.add(keyboardFirstRow);
//        keyboard.add(keyboardSecondRow);
//        keyboard.add(keyboardThreeRow);
//
//        replyKeyboardMarkup.setKeyboard(keyboard);
    }
}