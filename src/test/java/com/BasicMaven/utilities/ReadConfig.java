package com.BasicMaven.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig
{
    Properties pro;
    File src = new File("./Configuration/config.properties");


        FileInputStream fis;

    {
        try {
            fis = new FileInputStream(src);
            pro = new Properties();
            pro.load(fis);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }






    public String getApplicationURL()
    {
        String url=pro.getProperty("baseURL");
        return url;
    }

    public String getUsername()
    {
        String username=pro.getProperty("username");
        return username;
    }

    public String getPassword()
    {
        String password=pro.getProperty("password");
        return password;
    }

    public String getChromePath()
    {
        String chromepath=pro.getProperty("chromepath");
        return chromepath;
    }

    public String getFirefoxPath()
    {
        String firefoxpath=pro.getProperty("firefoxpath");
        return firefoxpath;
    }

    public String getEdgePath()
    {
        String firefoxpath=pro.getProperty("edgepath");
        return firefoxpath;
    }
}
