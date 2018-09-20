package com.sqs.cloud9A;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.print.attribute.standard.DateTimeAtProcessing;
import java.util.Date;
import java.util.concurrent.TimeUnit;


/**
 * Unit test for simple App.
 */
public class AppTestA
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

    driver.findElement(By.linkText("Register")).click();

    registrationObject registrationPage;
    registrationPage = new registrationObject(driver);

    registrationPage.populateRegistration("Ashvir","Munesar", "AM13@gmail.com","Password123");

}

    @Test
    public void shouldLoginCustomer() throws InterruptedException {

        driver.findElement(By.xpath("/html/body/div/form/button")).click();

        loginObject loginPage;
        loginPage = new loginObject(driver);

        loginPage.populateLogin("am@gmail.com","Password");


    }

    @Test
    public void shouldBookFlight() throws InterruptedException {

        shouldLoginCustomer();
        driver.findElement(By.xpath("/html/body/div/div/div[1]/ul/li[1]/a")).click();

        bookFlightObject bookPage;
        bookPage = new bookFlightObject(driver);

        bookPage.populateLogin("Dubai","London","A30", "First");

        driver.findElement(By.xpath("/html/body/div/div/div[1]/ul/li[2]/a")).click();


    }

    @Test
    public void shouldUpdateFlight() throws InterruptedException {
        //Customer Login
        shouldLoginCustomer();


       //Find
        itineraryObject findObj;
        findObj = new itineraryObject(driver);
        String editUrl = findObj.scanBookingsAndClickUpdate("240", "U");
        System.out.println("This edit string: " + editUrl);

        //Update


        updateFlightObject updatePage;
        updatePage = new updateFlightObject(driver);
        driver.get(editUrl);
        updatePage.populateUpdate("E99");

        driver.findElement(By.xpath("/html/body/div/div/div[2]/center/a")).click();
    }

    @Test
    public void shouldDeleteFlight() throws InterruptedException {
        //Customer Login
        shouldLoginCustomer();

        //Find
        itineraryObject findObj;
        findObj = new itineraryObject(driver);
        String editUrl = findObj.scanBookingsAndClickUpdate("275", "D");
        System.out.println("This edit string: " + editUrl);


        //Delete
        deleteBookingObject deleteRow;
        deleteRow = new deleteBookingObject(driver);
        driver.get(editUrl);
        deleteRow.populateDelete();

        driver.findElement(By.xpath("/html/body/div/div/div[2]/center/a")).click();
    }



    @After
    public void tearDown()
{
    //driver.close();
}

    public static class registrationObject {
        WebDriver driver;

        private By firstNameLocator = By.name("firstname");
        private By lastNameLocator = By.name("lastname");
        private By emailLocator = By.name("email");
        private By passwordLocator = By.name("password");
        private By registerButtonLocator = By.xpath("/html/body/div/form/button");
        private By bodyTextLocator = By.tagName("body");

        private String cloud9RegisterHeader = "Cloud9 - Register";
        private String registrationSuccessful = "Registration Successful";

        public registrationObject(WebDriver driver) {

            this.driver = driver;
        }

        public void clickRegister(WebDriver driver){

            //System.out.println("Click Register Button");
            driver.findElement(registerButtonLocator).click();
        }

        public void assertRegistrationHeader(){
            String bodyText = driver.findElement(bodyTextLocator).getText();
            Assert.assertTrue("Text not found!", bodyText.contains(cloud9RegisterHeader));
        }

        public void assertRegistrationSuccessful(){
            String bodyText = driver.findElement(bodyTextLocator).getText();
            Assert.assertTrue("Text not found!", bodyText.contains(registrationSuccessful));
        }

        public void populateRegistration(String firstName, String lastName, String emailAddress, String password){

            assertRegistrationHeader();

            driver.findElement(firstNameLocator).sendKeys(firstName);
            driver.findElement(lastNameLocator).sendKeys(lastName);
            driver.findElement(emailLocator).sendKeys(emailAddress);
            driver.findElement(passwordLocator).sendKeys(password);
            clickRegister(driver);
            assertRegistrationSuccessful();
        }
    }
}
