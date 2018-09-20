package com.sqs.cloud9A;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */

    static WebDriver driver;
    private String registrationSuccessful = "Registration Successful";
    private By bodyTextLocator = By.tagName("Body");
    private String cloud9Registration = "Cloud9 - Registration";


    @Before
    public void testSetup() throws InterruptedException {
        System.out.println("In testSetup ");
        System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        String baseUrl =  "http://10.9.10.39:81/sqlite/Main/login.html";
        String expectedTitle = "Cloud9 Airlines";
        driver.get(baseUrl);
        String actualTitle = driver.getTitle();
        System.out.println("Actual Title is: " + actualTitle);
        Assert.assertEquals(actualTitle, expectedTitle);
        Thread.sleep(2000);
    }

    @BeforeClass
    public static void browserSetup()
    {

    }

    @Test
public void shouldRegisterNewCustomer()
{
    driver.findElement(By.xpath("/html/body/div/form/center/a")).click();
    driver.findElement(By.name("firstname")).sendKeys("Ashvir");
    driver.findElement(By.name("lastname")).sendKeys("Munesar");
    driver.findElement(By.name("email")).sendKeys("AM2@gmail.com");
    driver.findElement(By.name("password")).sendKeys("Password123");
    driver.findElement(By.xpath("/html/body/div/form/button")).click();

    String bodyText = driver.findElement(bodyTextLocator).getText();
    Assert.assertTrue("Text not found!" , bodyText.contains(registrationSuccessful));

}

    @Test
    public void shouldLoginCustomer() throws InterruptedException {

        //driver.findElement(By.xpath("/html/body/center/a")).click();
        driver.findElement(By.name("email")).sendKeys("AM@gmail.com");
        driver.findElement(By.name("password")).sendKeys("Password123");
        driver.findElement(By.xpath("/html/body/div/form/button")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/center[3]/a")).click();
        Thread.sleep(1000);


    }
    @After
    public void tearDown()
{
    //driver.close();
}

}
