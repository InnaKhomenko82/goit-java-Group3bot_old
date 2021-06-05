package com.TelegramBot.GoogleApi;

public class Lessons extends GoogleApiToJson{
    private static String question;
    private static String ask;
    private static String video;

    public static String getQuestion() {
        return question;
    }

    public static void setQuestion(String question) {
        Lessons.question = question;
    }

    public static String getAsk() {
        return ask;
    }

    public static void setAsk(String ask) {
        Lessons.ask = ask;
    }

    public static String getVideo() {
        return video;
    }

    public static void setVideo(String video) {
        Lessons.video = video;
    }

    public Lessons(String question, String ask, String video) {
    }

}
