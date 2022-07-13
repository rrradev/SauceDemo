package com.saucelabs.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigHelper {

    private static final class InstanceHolder {
        static final ConfigHelper instance = new ConfigHelper();
    }

    private static final String PROPS_PATH = "config.properties";
    private static Properties properties;

    private ConfigHelper() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(PROPS_PATH));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("config.properties not found at " + PROPS_PATH);
        }
    }

    public static ConfigHelper getInstance() {
        return InstanceHolder.instance;
    }

    public String getUrl() {
        String url = properties.getProperty("url");
        if (url != null) return url;
        else throw new RuntimeException("url not specified in the config.properties file");
    }

    public String getUsername() {
        String url = properties.getProperty("username");
        if (url != null) return url;
        else throw new RuntimeException("username not specified in the config.properties file");
    }

    public String getPassword() {
        String url = properties.getProperty("password");
        if (url != null) return url;
        else throw new RuntimeException("password not specified in the config.properties file");
    }
}
