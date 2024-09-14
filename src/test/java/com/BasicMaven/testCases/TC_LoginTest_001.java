package com.BasicMaven.testCases;

import com.BasicMaven.pgaeObjects.LoginPage;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC_LoginTest_001 extends CommonClass
{
    @Test
    public void loginTest() throws IOException {
        //driver.get(baseURL);
        logger.info("URL is opened");

        LoginPage lp = new LoginPage(driver);
        lp.setUserName(username);
        logger.info("Entered username");
        lp.setPassword(password);
        logger.warn("Entered password");

        lp.clickSubmit();

        if(driver.getTitle().equals("GTPL Bank Manager HomePage12"))
        {
            Assert.assertTrue(true);
            logger.info("Login test passed");
        }
        else
        {
            //captureScreen(driver,"loginTest");
            Assert.assertTrue(false);
            logger.info("Login test failed");
        }
        lp.clickLogout();
        logger.warn("Logout from user session");


    }
}
