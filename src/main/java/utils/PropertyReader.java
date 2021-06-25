package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
    private static final String FILE_PATH = "credentials/mobile_cloud.properties";

    public static Properties getApiProperties(){
        FileInputStream fileInputStream;
        Properties properties = new Properties();
        try {
            fileInputStream = new FileInputStream(FILE_PATH);
            properties.load(fileInputStream);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return properties;
    }
}
