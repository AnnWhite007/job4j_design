package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 2. Формат JSON
 * JSON (JavaScript Object Notation) – текстовый формат обмена данными,
 * основан на синтаксисе JavaScript, удобен для написания и чтения человеком.
 * Несмотря на происхождение от JavaScript формат независим от него и может использоваться практически с любым языком программирования,
 * для многих из которых существуют готовые библиотеки для создания и обработки данных в формате JSON.
 * Применение:
 * - наиболее часто применяется для пересылки информации между браузером и сервером (загрузка контента по технологии Ajax) или между веб-сайтами (различные HTTP-соединения).
 * - можно использовать везде, где требуется обмен данных между программами;
 * - хранение локальной информации (например, настроек);
 * - за счёт лаконичности может быть выбран для сериализации сложных структур вместо XML.
 * Примитивные типы данных в JSON:
 * - число (целое или вещественное).
 * - литералы true, false и null.
 * - строка —символы юникода, заключённые в двойные кавычки.
 * Ссылочные типы данных:
 * - Объект - заключается в фигурные скобки ({ и }) и содержит разделенный запятой список пар имя/значение.
 * - Массив - заключается в квадратные скобки ([ и ]) и содержит разделенный запятой список значений.
 *
 * 5. Преобразование JSON в POJO. JsonObject
 * JSON-Java (org.json) легковесная функциональная библиотека для работы с JSON,
 * которая дополнительно умеет преобразовывать JSON в XML, HTTP header, Cookies и др. В отличие от Jackson или Gson,
 * JSON-Java преобразует json-строку не в объект пользовательского класса (способ Data bind),
 * а в объекты своей библиотеки JSONObject, JSONArray (способ Tree Model).
 * Объекты классов Cat, Parents из урока «Формат JSON» являются POJO, но для корректного преобразования в строку
 * с помощью org.json к ним ещё необходимо добавить геттеры.
 */

public class Main {
    public static void main(String[] args) {
        final Cat cat = new Cat("Marysia", 5, new Parents("Vasiliy", "Myrka"), true,
                new String[] {"Tyzic", "Sasha", "Vasilisa"});

        /* Преобразуем объект cat в json-строку. */
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(cat));

        /* Модифицируем json-строку */
        final String catJson =
                "{"
                        + "\"name\":\"Tisha\","
                        + "\"age\":6,"
                        + "\"parents\":"
                        + "{"
                        + "\"father\":\"Fox, \","
                        + "\"mother\":\"Kasia\""
                        + "},"
                        + "\"home\":false,"
                        + "\"kittensName\":"
                        + "[\"Finic\",\"Fes\"]"
                        + "}";
        final Cat catMod = gson.fromJson(catJson, Cat.class);
        System.out.println(catMod);

        /* JSONObject из json-строки строки */
        JSONObject jsonParents = new JSONObject("{\"father\":\"Vasiliy\",\"mother\":\"Myrka\"}");

        /* JSONArray из ArrayList */
        List<String> list = new ArrayList<>();
        list.add("Finic");
        list.add("Kasia");
        JSONArray jsonKittens = new JSONArray(list);

        /* JSONObject напрямую методом put */
        final Cat cat2 = new Cat("Sami", 6, new Parents("Sam", "Pam"), false,
                new String[] {"Honey", "Blum"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", cat2.getName());
        jsonObject.put("age", cat2.getAge());
        jsonObject.put("parents", jsonParents);
        jsonObject.put("kittens", jsonKittens);

        /* Выведем результат в консоль */
        System.out.println(jsonObject.toString());

        /* Преобразуем объект person в json-строку */
        System.out.println(new JSONObject(cat2).toString());
    }
}
