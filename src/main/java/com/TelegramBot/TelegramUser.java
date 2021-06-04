package com.TelegramBot;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TelegramUser {
    private String chatId;
    private String name;
    private String lastname;
    private String email;
}
