package com.khmersolution.moduler.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Created by DANG DIM
 * Date     : 4/11/2018, 11:49 AM
 * Email    : d.dim@gl-f.com
 */

public class AppConfigFile extends Properties {

    private static final long serialVersionUID = 4817939764467381363L;
    private static String configFile = "app-config.properties";
    private static AppConfigFile instance;

    private AppConfigFile() {
    }

    public static void initFile(String configFile) {
        configFile = configFile;
        getInstance();
    }

    public static AppConfigFile getInstance() {
        if (instance == null) {
            Class var0 = AppConfigFile.class;
            synchronized(AppConfigFile.class) {
                if (instance == null) {
                    instance = new AppConfigFile();
                    instance.loadConfigFile();
                }
            }
        }

        return instance;
    }

    public String getValue(String code) {
        return this.getProperty(code);
    }

    private void loadConfigFile() {

        InputStream in = ClassLoader.getSystemResourceAsStream(configFile);
        if (in == null) {
            in = ClassLoader.getSystemResourceAsStream("/" + configFile);
        }

        if (in == null) {
            in = ClassLoader.getSystemClassLoader().getResourceAsStream("/" + configFile);
        }

        if (in == null) {
            in = AppConfigFile.class.getClassLoader().getResourceAsStream("/" + configFile);
        }

        if (in == null) {
            String errMsg = "Can't find " + configFile + " in the classpath, check your Configuration";
            throw new IllegalStateException("Can't find [" + configFile + "]");
        } else {
            String key;
            try {
                instance.load(in);
            } catch (IOException var6) {
                key = "Error while loading [" + configFile + "]";
                throw new IllegalStateException("Can't load [" + configFile + "]");
            }

            try {
                in.close();
            } catch (IOException var5) {
            }

            Enumeration eKeys = instance.keys();

            while(eKeys.hasMoreElements()) {
                key = (String)eKeys.nextElement();
                String value = (String)instance.get(key);
            }

        }
    }

}
