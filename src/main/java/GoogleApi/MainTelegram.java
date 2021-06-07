package GoogleApi;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class MainTelegram {
    public static void main(String[] args) throws GeneralSecurityException, IOException, TelegramApiException {
        //Метод парсит гуглТаблицу и записывает в АрейЛисты
        TelegramGoogleApi.mainGoogle();

        //Метод записывает информацию из Гугл таблицы в json.формат ToString
    //    WriteToJson.Writer2ToString(TelegramGoogleApi.row);

        //Метод записывает информацию из Гугл таблицы в json.формат ToArray
      //  WriteToJson.Writer1ToArray(TelegramGoogleApi.responseValues);

        //Метод записывает информацию из Гугл таблицы в json.формат ToObject
      //  WriteToJson.Writer3ToObject(TelegramGoogleApi.values2);

        //Метод запуска ТелеграмБота
       // TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
       // telegramBotsApi.registerBot(new MessageBot());


    }
}
