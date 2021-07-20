package me.ultradev.skyblocksquadbot.config;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class Config {

    public static String getToken() {

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("src/main/resources/config.json")) {

            Object obj = jsonParser.parse(reader);

            return obj.toString().substring(10, obj.toString().length() - 2);

        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return null;
        }

    }

}
