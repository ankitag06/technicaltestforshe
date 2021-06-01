package com.wifispark.config;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class Config {

    
    public static final Properties properties = new Properties();    

    static
    {

        try
        {
            File configFile = new File("config.properties");
            
            FileInputStream inputStream = new FileInputStream(configFile); 

            Config.properties.load(inputStream);

        }
        
        catch(Exception e)
        {
            System.out.println("Problem in reading config file : " +e.getMessage());
        }
    
    
    }
    
    public static String getUrl() throws Exception {
        String url = Config.properties.getProperty("url").toString();
        if (url != null)
            return url;
        else
            throw new Exception("Application url is not specified in config.properties file");

    }
    
    public static String getUsername() throws Exception
    {
    	String username = Config.properties.getProperty("username").toString();
        if (username != null)
            return username;
        else
            throw new Exception("username is not specified in config.properties file");


    }
    
    public static String getPassword() throws Exception
    {
    	String password = Config.properties.getProperty("password").toString();
        if (password != null)
            return password;
        else
            throw new Exception("password is not specified in config.properties file");


    }  
       
    
}
