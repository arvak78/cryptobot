package com.commons.utils;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by manel on 20/01/18.
 */
public class Properties {

    public static java.util.Properties getApplicationProperties(String rootPath) {
        try {
            String appConfigPath = rootPath + "application.properties";

            java.util.Properties appProps = new java.util.Properties();
            appProps.load(new FileInputStream(appConfigPath));

            return appProps;
        } catch (IOException e) {
            return null;
        }
    }
}
