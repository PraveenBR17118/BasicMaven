package com.BasicMaven.testCases;



import com.BasicMaven.pgaeObjects.AddCustomerPage;
import com.BasicMaven.pgaeObjects.LoginPage;
import java.io.IOException;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_AddCustomerTest_003 extends  CommonClass
{
    public WebDriver ldriver;


    @Test
    public void addNewCustomer() throws InterruptedException, IOException
    {
        LoginPage lp=new LoginPage(driver);
        lp.setUserName(username);
        logger.info("User name is provided");
        lp.setPassword(password);
        logger.info("Passsword is provided");
        lp.clickSubmit();

        Thread.sleep(3000);

        AddCustomerPage addcust=new AddCustomerPage(driver);

        addcust.clickAddNewCustomer();

        logger.info("providing customer details....");


        addcust.custName("Praven");
        addcust.custgender("male");
        addcust.custdob("10","15","1985");
        Thread.sleep(3000);
        addcust.custaddress("INDIA");
        addcust.custcity("HYD");
        addcust.custstate("AP");
        addcust.custpinno("5000074");
        addcust.custtelephoneno("987890091");

        String email=randomestring()+"@gmail.com";
        addcust.custemailid(email);
        //addcust.custpassword("abcdef");
        addcust.custsubmit();

        Thread.sleep(3000);

        logger.info("validation started....");

        boolean res=driver.getPageSource().contains("Access denied for user");

        String s1= driver.getPageSource();
        System.out.println(s1);

        if(res==true)
        {
            Assert.assertTrue(true);
            logger.info("test case passed....");

        }
        else
        {
            logger.info("test case failed....");
           // captureScreen(driver,"addNewCustomer");
            Assert.assertTrue(false);
        }

    }


}
