package com.sqs.cloud9A;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class loginObject {

    WebDriver driver;


    private By emailLocator = By.name("email");
    private By passwordLocator = By.name("password");
    private By loginButtonLocator = By.xpath("/html/body/div/form/button");
    private By hompageLinkLocator = By.xpath("/html/body/center[3]/a");
    private By bodyTextLocator = By.tagName("body");

    private String cloud9LoginHeader = "Cloud9 - Sign in";
    private String LoginSuccessful = "Welcome";

    public loginObject(WebDriver driver) {

        this.driver = driver;
    }

    public void clickSignIn(WebDriver driver){

        //System.out.println("Click Register Button");
        driver.findElement(loginButtonLocator).click();
    }

    public void clickHomepage(WebDriver driver){

        driver.findElement(hompageLinkLocator).click();

    }

    public void assertLoginHeader(){
        String bodyText = driver.findElement(bodyTextLocator).getText();
        Assert.assertTrue("Text not found!", bodyText.contains(cloud9LoginHeader));
    }


    public void assertLoginSuccessful(){
        String bodyText = driver.findElement(bodyTextLocator).getText();
        Assert.assertTrue("Text not found!", bodyText.contains(LoginSuccessful));
    }


    public void populateLogin(String emailAddress, String password) throws InterruptedException {

        assertLoginHeader();

        driver.findElement(emailLocator).sendKeys(emailAddress);
        driver.findElement(passwordLocator).sendKeys(password);
        clickSignIn(driver);
        Thread.sleep(1000);
        assertLoginSuccessful();
        Thread.sleep(2000);
        clickHomepage(driver);


    }
}
