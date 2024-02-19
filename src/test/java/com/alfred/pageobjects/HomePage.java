package com.alfred.pageobjects;

import com.alfred.common.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

    private WebDriver driver;

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
        if (!h2NewsAndAnnouncements.getText().equals(textNewsAndAnnouncements)) {
            throw new IllegalStateException("ERROR - This is not Home Page!");
        }
    }

    @FindBy(how = How.CLASS_NAME, using = "homepage-heading-2")
    private WebElement h2NewsAndAnnouncements;
    private final String textNewsAndAnnouncements = "News & Announcements";

    @FindBy(how = How.CLASS_NAME, using = "mission-text-main")
    private WebElement pMissonText;
    private final String textMissionText = "We are Australia’s central bank and serve the people of Australia.";
    public boolean isMissionTextCorrect(){
        return pMissonText.getText().contains(textMissionText);
    }

    @FindBy(how = How.LINK_TEXT, using = "Payments & Infrastructure")
    private WebElement linkPaymentsAndInfrastructure;
    public PaymentsAndInfrastructurePage ClickPaymentsAndInfrastructure(){
        highlightElement(linkPaymentsAndInfrastructure);
        linkPaymentsAndInfrastructure.click();
        return new PaymentsAndInfrastructurePage(this.driver);
    }
}
