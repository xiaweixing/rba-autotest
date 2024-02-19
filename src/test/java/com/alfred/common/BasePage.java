package com.alfred.common;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePage {

    private WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        //PageFactory.initElements(driver, this);
    }

    public boolean isElementVisible(WebElement element, int timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(timeOut));
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public void highlightElement(WebElement element){
        if (this.driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) this.driver).executeScript("arguments[0].style.border='5px solid yellow'", element);
            try {
                Thread.sleep(2000);
            } catch(InterruptedException e) {
                e.printStackTrace(System.out);
            }
        }
    }

    public void unHighlightElement(WebElement element){
        if (this.driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) this.driver).executeScript("arguments[0].style.border=''", element);
        }
    }
}
