package me.ultradev.skyblocksquadbot.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public class Config {

    // https://github.com/frankred/json-config-file/blob/master/src/de/roth/json/config/Config.java

    public String TOKEN;

    public Config() {
        TOKEN = "";
    }

    private static Config instance;

    public static Config getInstance() {

        if (instance == null) {
            instance = fromDefaults();
        }

        return instance;

    }

    public static void load(File file) {

        instance = fromFile(file);

        // no config file found
        if (instance == null) {
            instance = fromDefaults();
        }

    }

    public static void load(String file) {
        load(new File(file));
    }

    private static Config fromDefaults() {
        return new Config();
    }

    public void toFile(String file) {
        toFile(new File(file));
    }

    public void toFile(File file) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String jsonConfig = gson.toJson(this);

        FileWriter writer;

        try {

            writer = new FileWriter(file);
            writer.write(jsonConfig);
            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Config fromFile(File configFile) {

        try {

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(configFile)));

            return gson.fromJson(reader, Config.class);

        } catch (FileNotFoundException e) {
            return null;
        }

    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }

}
