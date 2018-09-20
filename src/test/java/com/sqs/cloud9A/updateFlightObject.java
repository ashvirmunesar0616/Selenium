package com.sqs.cloud9A;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class updateFlightObject {

    WebDriver driver;

    //private String flightIDLocator;

    private By seatLocator =  By.id("seat");
    private  By updateButtonLocator = By.xpath("/html/body/div/div/div[2]/form/button");


    private By bodyTextLocator = By.tagName("body");

    private String cloud9UpdateFlightHeader = "Edit Flight";
    private String updateSuccessful = "Flight successfully ";


    public updateFlightObject(WebDriver driver) {

        this.driver = driver;

    }

    public void clickUpdate(WebDriver driver){

        driver.findElement(updateButtonLocator).click();
    }

    public void assertUpdateHeader(){
        String bodyText = driver.findElement(bodyTextLocator).getText();
        Assert.assertTrue("Text not found!", bodyText.contains(cloud9UpdateFlightHeader));
    }

    public void assertUpdateSuccessful(){
        String bodyText = driver.findElement(bodyTextLocator).getText();
        Assert.assertTrue("Text not found!", bodyText.contains(updateSuccessful));
    }


    public void populateUpdate(String seat) throws InterruptedException {

        //driver.get("http://10.9.10.39:81/sqlite/Main/editflight.php?FlightID=244");

        assertUpdateHeader();
        driver.findElement(seatLocator).clear();
        driver.findElement(seatLocator).sendKeys(seat);
        Thread.sleep(2000);
        clickUpdate(driver);
        assertUpdateSuccessful();
        Thread.sleep(1000);




    }
}
