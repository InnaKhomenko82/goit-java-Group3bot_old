package com.TelegramBot.Bot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class KeyBoards {
    //Метод для кнопки Зарегистрировавться
    protected void keyboardForRegistration(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setSelective(true);

        KeyboardRow keyboardButtons = new KeyboardRow();
        keyboardButtons.add("⚠️ Зарегистрироваться");

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        keyboardRows.add(keyboardButtons);

        replyKeyboardMarkup.setKeyboard(keyboardRows);
    }
    // Метод для выбора кнопок, что изучать
    protected void keyBoardsForChoosingTraining(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        // Предлагаю клавиатуру немного подправить, чтобы каждую клавишу было хорошо видно и удобнее)) P.S.Женя
        // (Предложение хорошее и оно принято:))
        // Ниже код с кнопками, можно добавить кнопки как в 1 строку так и с новой строки


        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton("✅ HTML/CSS"));
        keyboardFirstRow.add("✅ JavaScript");
        keyboardFirstRow.add("✅ React");
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        keyboardSecondRow.add(new KeyboardButton("⚙️ Настройки"));
        keyboardSecondRow.add("⚙️ Настроить напоминание");

        KeyboardRow keyboardThreeRow = new KeyboardRow();
        keyboardThreeRow.add(new KeyboardButton("\uD83C\uDF24 Просмотреть погоду, всегда есть о чем поговорить:)"));

        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        keyboard.add(keyboardThreeRow);

        replyKeyboardMarkup.setKeyboard(keyboard);
    }
    // Метод для кнопок, после того как выбрали изучение JavaScript.
    protected void keyBoardForTraining(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        replyKeyboardMarkup.setResizeKeyboard(true);

        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton("\uD83D\uDD19 Перейти к предыдущему вопросу"));
        keyboardFirstRow.add("Выбрать следующий вопрос \uD83D\uDD1C");

        KeyboardRow keyboardSecondRow = new KeyboardRow();
        keyboardSecondRow.add(new KeyboardButton("\uD83C\uDFE1 Перейти в главное меню"));
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);

        replyKeyboardMarkup.setKeyboard(keyboard);
    }
    // Метод для кнопок, после того как выбрали изучение JavaScript и после того как выбрали следующий вопрос номер 2.

    //Метод для кнопок, после того как выбрали изучение JavaScript. Не смог закончить метод. Надо думать как связать со всем остальным. P.s Андрей.
//    protected ReplyKeyboard keyBoardsAfterChoiceTraining() {
//        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
//        inlineKeyboardMarkup.setKeyboard(Collections.singletonList(Arrays.asList(
//                InlineKeyboardButton.builder().text("Перейти в главное меню").callbackData("Перейти в главное меню").build(),
//                InlineKeyboardButton.builder().text("Выбрать следующий вопрос").callbackData("Выбрать следующий вопрос").build()
//        )));
//
//        return inlineKeyboardMarkup;
//    }

}
