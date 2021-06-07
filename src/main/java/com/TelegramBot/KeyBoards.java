package com.TelegramBot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class KeyBoards {

    protected void keyboardForRegistration(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setSelective(true);

        KeyboardRow keyboardButtons = new KeyboardRow();
        keyboardButtons.add("Зарегистрироваться");

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        keyboardRows.add(keyboardButtons);

        replyKeyboardMarkup.setKeyboard(keyboardRows);
    }

    protected void keyBoardsForChoosingTraining(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        // Предлагаю клавиатуру немного подправить, чтобы каждую клавишу было хорошо видно и удобнее)) P.S.Женя
        // (Предложение хорошее и оно принято:))
        // Ниже код с кнопками, можно добавить кнопки как в 1 строку так и с новой строки


        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton("<--- HTML/CSS --->"));
        keyboardFirstRow.add("<--- JavaScript React --->");
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        keyboardSecondRow.add(new KeyboardButton("< Настройки >"));
        keyboardSecondRow.add("< Настроить напоминание >");

        KeyboardRow keyboardThreeRow = new KeyboardRow();
        keyboardThreeRow.add(new KeyboardButton("Просмотреть погоду, всегда есть о чем поговорить:)"));

        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        keyboard.add(keyboardThreeRow);

        replyKeyboardMarkup.setKeyboard(keyboard);
    }

    protected ReplyKeyboard keyBoardsAfterChoiceTraining() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(Collections.singletonList(Arrays.asList(
                InlineKeyboardButton.builder().text("Начать с первого вопроса.").callbackData("A").build(),
                InlineKeyboardButton.builder().text("Выбрать вопрос.").callbackData("B").build()
        )));


        return inlineKeyboardMarkup;
    }

}
