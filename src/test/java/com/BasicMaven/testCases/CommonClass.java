package com.BasicMaven.testCases;

import com.BasicMaven.utilities.ReadConfig;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CommonClass
{
    ReadConfig readconfig=new ReadConfig();
    public String baseURL= readconfig.getApplicationURL();
    public String username= readconfig.getUsername();
    public String password= readconfig.getPassword();
    public static WebDriver driver;
    public Logger logger;

    @Parameters("browser")
    @BeforeClass
    public void setup(String br)
    {
        logger = Logger.getLogger("BasicMaven");
        PropertyConfigurator.configure("Log4j.properties");

      //  System.setProperty("webdriver.chrome.driver",readconfig.getChromePath());
       // driver=new ChromeDriver();
       //System.setProperty("webdriver.gecko.driver","/Users/praveenbr/IdeaProjects/BasicsMaven/Drivers/geckodriver");
       //driver = new FirefoxDriver();
        /*logger = Logger.getLogger("ebanking");
        PropertyConfigurator.configure("Log4j.properties");
        if(br.equals("chrome"))
        {
            System.setProperty("webdriver.chrome.driver",readconfig.getChromePath());
            driver=new ChromeDriver();
            driver.manage().deleteAllCookies();

        }
        else if(br.equals("firefox"))
        {
            System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"./Drivers/geckodriver");
            driver = new FirefoxDriver();
            driver.manage().deleteAllCookies();
        }

         */
        switch (br.toLowerCase())
        {
            case "chrome" :
                System.setProperty("webdriver.chrome.driver",readconfig.getChromePath());
                driver=new ChromeDriver();
                driver.manage().deleteAllCookies();
                logger.info("Opened Chrome driver");
                break;
            case "firefox" :
                System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"./Drivers/geckodriver");
                driver = new FirefoxDriver();
                driver.manage().deleteAllCookies();
                logger.info("Opened Fire fox driver");
                break;
            case "edge" :
                System.setProperty("webdriver.edge.driver",readconfig.getEdgePath());
                driver = new EdgeDriver();
                driver.manage().deleteAllCookies();
                logger.info("Opened edge driver");
                break;
            default :
                System.out.println("Invalid driver");
                logger.warn("Opened edge driver");
        }


        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(baseURL);
        driver.manage().window().maximize();


    }

    public String captureScreen(String tname) throws IOException
    {
        String NowStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String targetFilePath = System.getProperty("user.dir")+ "/Screenshots/" + tname + "_" + NowStamp +".png";
        File target = new File(targetFilePath);

        source.renameTo(target);
        System.out.println("Screenshot taken");
        return targetFilePath;

    }

    public String randomestring()
    {
        String generatedstring= RandomStringUtils.randomAlphabetic(8);
        return(generatedstring);
    }

    public static String randomeNum() {
        String generatedString2 = RandomStringUtils.randomNumeric(4);
        return (generatedString2);
    }

    @AfterClass
    public void tearDown()
    {
        driver.quit();
    }


}
