package com.protean.legislativetracker.zidane.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesFile {

    private static Logger log = LoggerFactory.getLogger(PropertiesFile.class);

    @Value("${legiscan.url}")
    private String url;

    @Value("${legiscan.key}")
    private String key;

    public PropertiesFile() {
        if (url == null || key == null) loadProperties();
    }

    private void loadProperties() {
        Properties properties = new Properties();
        InputStream input = null;

        try {
            input = getClass().getClassLoader().getResourceAsStream("application-test.properties");
            properties.load(input);
            this.key = properties.getProperty("legiscan.key");
            this.url = properties.getProperty("legiscan.url");
        } catch (IOException e) {
            log.debug(e.getMessage());
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getUrl() {
        return url;
    }

    public String getKey() {
        return key;
    }
}
