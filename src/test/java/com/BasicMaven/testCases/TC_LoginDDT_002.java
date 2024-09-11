package com.BasicMaven.testCases;

import com.BasicMaven.pgaeObjects.LoginPage;
import com.BasicMaven.utilities.XLUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC_LoginDDT_002 extends CommonClass
{
    @Test(dataProvider="LoginData")
    public void loginDDT(String user,String pwd) throws Exception
    {
        LoginPage lp=new LoginPage(driver);
        lp.setUserName(user);
        logger.info("user name provided");
        lp.setPassword(pwd);
        logger.warn("password provided");
        lp.clickSubmit();

        //JavascriptExecutor js = (JavascriptExecutor) driver;
       // js.executeScript("window.scrollBy(0,1000)");

        driver.switchTo().defaultContent();
        Thread.sleep(3000);


        if(isAlertPresent()== true)
        {
            driver.switchTo().alert().accept();//close alert
            driver.switchTo().defaultContent();
            //captureScreen(driver,"TC_LoginDDT_002");
            Assert.assertTrue(false);
            logger.warn("Login failed");
        }
        else
        {
            Assert.assertTrue(true);
            logger.info("Login passed");
            lp.clickLogout();
            Thread.sleep(3000);

            driver.switchTo().alert().accept();//close logout alert
            driver.switchTo().defaultContent();

        }


    }


    public boolean isAlertPresent() //user defined method created to check alert is presetn or not
    {
        try
        {
            driver.switchTo().alert();
            return true;
        }
        catch(NoAlertPresentException e)
        {
            return false;
        }

    }


    @DataProvider(name="LoginData")
    String [][] getData() throws IOException
    {
        String path="/Users/praveenbr/IdeaProjects/BasicsMaven/src/test/java/com/BasicMaven/testData/TestOne.xlsx";

        int rownum= XLUtils.getRowCount(path, "Sheet1");
        int colcount=XLUtils.getCellCount(path,"Sheet1",1);

        String logindata[][]=new String[rownum][colcount];

        for(int i=1;i<=rownum;i++)
        {
            for(int j=0;j<colcount;j++)
            {
                logindata[i-1][j]=XLUtils.getCellData(path,"Sheet1", i,j);//1 0
            }

        }
        return logindata;
    }
}
