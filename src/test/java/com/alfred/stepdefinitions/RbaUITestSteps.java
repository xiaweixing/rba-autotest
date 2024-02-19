package com.alfred.stepdefinitions;

import com.alfred.common.Hooks;
import com.alfred.pageobjects.PaymentsSystemPage;
import com.alfred.pageobjects.HomePage;
import com.alfred.pageobjects.PaymentsAndInfrastructurePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class RbaUITestSteps {

    private static WebDriver driver;
    private HomePage homePage = null;
    private PaymentsAndInfrastructurePage paymentsAndInfrastructurePage = null;
    private PaymentsSystemPage paymentsSystemPage = null;

    @Given("user goes to RBA website")
    public void userGoesToRBAWebsite() {
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/drivers/chromedriver");
        driver = new ChromeDriver();
        Hooks.setDriver(driver);
        driver.manage().window().maximize();
        driver.get(Hooks.getBaseUrlWeb());
        homePage = new HomePage(driver);
        assertTrue(homePage.isMissionTextCorrect());
    }

    @And("click link Payments and Infrastructure")
    public void clickLinkPaymentsAndInfrastructure() {
        paymentsAndInfrastructurePage = homePage.ClickPaymentsAndInfrastructure();
    }

    @Then("user should see Payments and Infrastructure page")
    public void userShouldSeePaymentsAndInfrastructurePage() {
        assertTrue(paymentsAndInfrastructurePage.isUrlCorrect());
    }

    @And("click link Payments System from left menu")
    public void clickLinkPaymentsSystemFromLeftMenu() {
        paymentsSystemPage = paymentsAndInfrastructurePage.ClickPaymentsSystem();
    }

    @Then("user should see Payments System page")
    public void userShouldSeePaymentsSystemPage() {
        assertTrue(paymentsSystemPage.isUrlCorrect());
    }

    @And("click link Non-cash Payments from On This page list at the right side of the page")
    public void clickLinkNonCashPaymentsFromOnThisPageListAtTheRightSideOfThePage() {
        paymentsSystemPage.ClickNonCashPayments();
    }

    @Then("user should be taken to Non-cash Payments section")
    public void userShouldBeTakenToNonCashPaymentsSection() {
        assertTrue(paymentsSystemPage.NonCashPaymentsVisible());
        //fail();
    }

}
