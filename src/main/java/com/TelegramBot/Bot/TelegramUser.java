package com.TelegramBot.Bot;


import lombok.Data;

@Data

public class TelegramUser {
    private String chatId;
    private String name;
    private String lastname;
    private String email;
}