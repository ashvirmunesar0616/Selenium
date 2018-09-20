package com.sqs.cloud9A;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class bookFlightObject {

    WebDriver driver;

    private By originLocator = By.name("Origin");
    private By destinationLocator = By.name("Destination");
    private By seatLocator = By.name("seat");
    private By classLocator = By.name("Flightclass");
    private By bookButtonLocator = By.xpath("/html/body/div/div/div[2]/form/button");
    private By bodyTextLocator = By.tagName("body");

    private String cloud9BookFlightHeader = "Book Flight";
    private String bookingSuccessful = "Flight";

    public bookFlightObject(WebDriver driver) {

        this.driver = driver;

    }

    public void clickBook(WebDriver driver){

        //System.out.println("Click Register Button");
        driver.findElement(bookButtonLocator).click();
    }


    public void assertBookingHeader(){
        String bodyText = driver.findElement(bodyTextLocator).getText();
        Assert.assertTrue("Text not found!", bodyText.contains(cloud9BookFlightHeader));
    }


    public void assertBookingSuccessful(){
        String bodyText = driver.findElement(bodyTextLocator).getText();
        Assert.assertTrue("Text not found!", bodyText.contains(bookingSuccessful));
    }

    public void populateLogin(String origin, String destination, String seat, String Flightclass) throws InterruptedException {

        assertBookingHeader();

        driver.findElement(originLocator).sendKeys(origin);
        driver.findElement(destinationLocator).sendKeys(destination);
        driver.findElement(seatLocator).sendKeys(seat);
        driver.findElement(classLocator).sendKeys(Flightclass);
        Thread.sleep(2000);
        clickBook(driver);

        assertBookingSuccessful();
        Thread.sleep(1000);



    }


}
