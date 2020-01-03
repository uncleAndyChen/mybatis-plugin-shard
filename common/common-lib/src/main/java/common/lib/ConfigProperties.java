package common.lib;

import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

public class ConfigProperties {
    private static Properties properties = new Properties();

    static {
        try {
            properties = PropertiesLoaderUtils.loadAllProperties("common.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getValue(String key) {
        return properties.getProperty(key, "");
    }
}
