package com.BasicMaven.utilities;

import com.BasicMaven.testCases.CommonClass;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ListenerManager implements ITestListener
{
    public ExtentSparkReporter sparkReporter;  // Ui of the Report
    public ExtentReports extent; // Poupulate info report of the report
    public ExtentTest test; // Creating test Case Entries in the report and update status of the Test Method
    String repName;

    @Override
    public void onStart(ITestContext context)
    {
       // ITestListener.super.onStart(context);
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
        repName="Test-Report-"+timeStamp+".html";

        sparkReporter=new ExtentSparkReporter(System.getProperty("user.dir")+ "/Report/"+ repName);//specify location of the report
        sparkReporter.config().setDocumentTitle("Basic maven Automation Report"); //Title of the Report
        sparkReporter.config().setReportName("Functional Testing");
        //sparkReporter.config().setReportName("Praveen");
        sparkReporter.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        extent.setSystemInfo("Host name","localhost");
        extent.setSystemInfo("Application","Basic Maven");
        extent.setSystemInfo("Environemnt","QA");
        extent.setSystemInfo("user",System.getProperty("user.name"));

        String os = context.getCurrentXmlTest().getParameter("os");
        extent.setSystemInfo("Operating System",os);

        String browser = context.getCurrentXmlTest().getParameter("browser");
        extent.setSystemInfo("browser",browser);

        List<String> includegroups = context.getCurrentXmlTest().getIncludedGroups();
        if(!includegroups.isEmpty())
        {
            extent.setSystemInfo("Groups", includegroups.toString());
        }


    }

    @Override
    public void onTestStart(ITestResult result) {
       // ITestListener.super.onTestStart(result);
        test = extent.createTest(result.getTestClass().getName()); // Create a new Entry in the report
       // test.assignCategory(result.getMethod().getGroups()); //to display group report
        test.log(Status.PASS,result.getName() + " Execution started"); // update the status as Started Execution
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        //ITestListener.super.onTestSuccess(result);
        test = extent.createTest(result.getName()); // Create a new Entry in the report
        test.assignCategory(result.getMethod().getGroups()); //to display group report
        test.log(Status.PASS,result.getName() + " got sucesufully executed"); // update the status as pass
    }

    @Override
    public void onTestFailure(ITestResult result) {
        //ITestListener.super.onTestFailure(result);
        test = extent.createTest(result.getClass().getName()); // Create a new Entry in the report
        test.assignCategory(result.getMethod().getGroups()); //to display group report
        test.log(Status.FAIL,result.getTestName() + " got failed" ); // update the status as Fail
        test.log(Status.INFO,result.getThrowable().getMessage());

        try
        {
            String impath = new CommonClass().captureScreen(result.getName());
            test.addScreenCaptureFromPath(impath);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        //ITestListener.super.onTestSkipped(result);
        test = extent.createTest(result.getTestClass().getName()); // Create a new Entry in the report
        test.assignCategory(result.getMethod().getGroups()); //to display group report
        test.log(Status.SKIP,result.getTestName() + " got Skipped" ); // update the status as Skip
        test.log(Status.INFO,result.getThrowable().getMessage()); // update the status as Skip
    }

    @Override
    public void onFinish(ITestContext context) {
        //ITestListener.super.onFinish(context);
        extent.flush();

        String apthofExtentReport = System.getProperty("user.dir") + "/Report/"+ repName;
        File extentRport = new File(apthofExtentReport);
        try
        {
            Desktop.getDesktop().browse(extentRport.toURI());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
