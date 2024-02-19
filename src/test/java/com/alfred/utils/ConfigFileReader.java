package com.alfred.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {
    private Properties properties;

    public ConfigFileReader(String configFilePath) {
        File ConfigFile = new File(configFilePath);
        try {
            FileInputStream configFileReader = new FileInputStream(ConfigFile);
            properties = new Properties();
            try {
                properties.load(configFileReader);
                configFileReader.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("properties file not found at config file path " + configFilePath);
        }
    }

    public String getBaseUrl() {
        String webBaseUrl = properties.getProperty("base.url");
        if (webBaseUrl != null)
            return webBaseUrl;
        else
            throw new RuntimeException("base.url not specified in the properties file.");
    }

    public String getEnv() {
        String env = properties.getProperty("env");
        if (env != null)
            return env;
        else
            throw new RuntimeException("env not specified in the properties file.");
    }
}
