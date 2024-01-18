package co.wedevx.digitalbank.automation.ui.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

//build a logic that reads the properties file
public class ConfigReader {

    private static Properties properties;

    //static initializer run the block only once for the whole project
    //instance initializer run the block for every object creation from the class
    static{
        //filePath -> the directory of my properties file
        String filePath = "src/test/resources/properties/digitalbank.properties";

        //class that enables to read the files
        FileInputStream input= null;
        try {
            input = new FileInputStream(filePath);
            properties = new Properties();
            properties.load(input);
        } catch (IOException e) {
            System.out.println("File not found");
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static String getPropertiesValue(String key){
        return properties.getProperty(key);
    }
}
