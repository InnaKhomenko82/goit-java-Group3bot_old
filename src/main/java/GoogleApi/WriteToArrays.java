package GoogleApi;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.Sheet;
import com.google.api.services.sheets.v4.model.ValueRange;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WriteToArrays extends TelegramGoogleApi {
    public static ValueRange response;
    public static List<Object> responseValues = new ArrayList<>();  //все подряд

    public static List<Object> clearLessons = new ArrayList<>();
    public static List<Object> clearArray = new ArrayList<>();

    static String blockName;
    public static List<String> blocksNames = new ArrayList<>(); //массив с названиями Гугл-листов
    public static List<Object> lessons = new ArrayList<>();     // массив всех уроков,всех листов
    public static List<Object> questions = new ArrayList<>();   // массив всех вопросов,всех листов
    public static List<Object> asks = new ArrayList<>();        // массив всех ответов,всех листов
    public static List<Object> videoURLs = new ArrayList<>();   // массив всех ссылок на видео,всех листов

    public static void getAll(List<Sheet> sheets, String spreadSheetId, Sheets service) throws IOException {
        for (int j = 0; j < sheets.size(); j++) {
                Sheet sheet = sheets.get(j);
                blockName = sheet
                        .getProperties()
                        .getTitle();
                blocksNames.add(blockName);  // blockName - назание Гугл-Листа
                response = service
                        .spreadsheets()
                        .values()
                        .get(spreadSheetId, blocksNames.get(j)) //blocksNames.get(0)-номер лисат//!A1:A-название блока обучения
                        .execute();
                responseValues.add(response);


                List<List<Object>> responseValues = response.getValues();
                int count = 1;
                lessons.add("\n" + blockName + "\n");
                asks.add("\n" + blockName + "\n");
                questions.add("\n" + blockName + "\n");
                videoURLs.add("\n" + blockName + "\n");

                for (int i = 3; i < responseValues.size(); i = i + 2) {

                    responseValues.add(responseValues.get(j));
                    blockName = (String) responseValues.get(0).get(0);
                    String a = (String) responseValues.get(j).get(0);
                    String b = (String) responseValues.get(j).get(1);
                    String c = (String) responseValues.get(j).get(2);
                    questions.add("\nВопрос №" + a);
                    asks.add("\nОтвет №" + count + ". " + b);
                    videoURLs.add("\nСсылка на видео к уроку №" + count + ". " + c);
                    lessons.add(" \n Урок №" + count + "\nВопрос \n" + a + "\nОтвет :\n"  + " " + b + "\nСсылка на видео к уроку №" + count + ". " + c+"\n");
//                    clearArray.add("\n" + a + "\n" + b + "\n" + c);
//                    clearLessons.add("\n" + a + "\n" + b + "\n" + c);
                    count++;
                }
                break;
        }
        System.out.println(asks);
    }
}
