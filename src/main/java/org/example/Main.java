package org.example;

public class Main {
    public static void main(String[] args)
    {
        System.out.println("Hello world!");
        /*
             User ID :	mngr588700
             Password :	udYdYde
            /Users/praveenbr/IdeaProjects/BasicsMaven/src/test/java/com/BasicMaven/testCases/TC_LoginTest_001.java
            Install the latest LTS version: brew install jenkins-lts
            Start the Jenkins service: brew services start jenkins-lts
            Restart the Jenkins service: brew services restart jenkins-lts
            Update the Jenkins version: brew upgrade jenkins-lts
            cd /Users/praveenbr/IdeaProjects/BasicsMaven/
            bash run.command
            http://localhost:8080/
         */
        fibonacciSeries(10);
        factorial(6);
    }

    public static void fibonacciSeries(int p)
    {
       int n1 = 0, n2 = 1, n3 ,count = p;
       System.out.println(n1 + " " + n2 );
       for(int i = 2; i < count;++i)
       {
           n3 = n1+n2;
           System.out.print(" " +n3);
           n1=n2;
           n2=n3;


       }
    }
    public static void factorial(int y)
    {
        int fact= 1, n = y;
        for(int i=1;i<n;i++)
        {
            fact = fact*i;
        }
        System.out.println("factorial of number "+n + " is "+fact);
    }
}