package com.sqs.cloud9A;


import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class deleteBookingObject {

    WebDriver driver;

    //private  By deleteButtonLocator = By.xpath("/html/body/div/div/div[2]/div/table/tbody/tr[8]/td[1]/a[2]");

    private String cloud9DeleteFlightHeader = "Itinerary";
    private String deleteSuccessful = "Flight successfully deleted.";
    private By bodyTextLocator = By.tagName("body");


    public deleteBookingObject(WebDriver driver) {

        this.driver = driver;

    }


   /* public void clickDelete(WebDriver driver){

        driver.findElement(deleteButtonLocator).click();
    }
*/
    public void assertDeleteHeader(){
        String bodyText = driver.findElement(bodyTextLocator).getText();
        Assert.assertTrue("Text not found!", bodyText.contains(cloud9DeleteFlightHeader));
    }

    public void assertDeleteSuccessful(){
        String bodyText = driver.findElement(bodyTextLocator).getText();
        Assert.assertTrue("Text not found!", bodyText.contains(deleteSuccessful));
    }

    public void populateDelete() throws InterruptedException {


        assertDeleteHeader();
        Thread.sleep(2000);
        //clickDelete(driver);
        assertDeleteSuccessful();
        Thread.sleep(1000);




    }



}
