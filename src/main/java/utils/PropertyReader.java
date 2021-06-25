package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
    private static final String FILE_PATH_MC = "credentials/mobile_cloud.properties";
    private static final String FILE_PATH_SK = "src/test/resources/web_test.properties";

    public static Properties getApiProperties(){
        FileInputStream fileInputStream;
        Properties properties = new Properties();
        try {
            fileInputStream = new FileInputStream(FILE_PATH_MC);
            properties.load(fileInputStream);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return properties;
    }

    public static Properties getTestProperties(){
        FileInputStream fileInputStream;
        Properties properties = new Properties();
        try {
            fileInputStream = new FileInputStream(FILE_PATH_SK);
            properties.load(fileInputStream);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return properties;
    }

}
