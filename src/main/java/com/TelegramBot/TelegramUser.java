package com.TelegramBot;
import lombok.Data;


@Data
public class TelegramUser {
    private String name;
    private String surName;
    private int age;
    private int id;
    private String email;
}