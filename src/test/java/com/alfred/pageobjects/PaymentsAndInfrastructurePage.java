package com.alfred.pageobjects;

import com.alfred.common.BasePage;
import com.alfred.common.Hooks;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class PaymentsAndInfrastructurePage extends BasePage {

    private WebDriver driver;

    public PaymentsAndInfrastructurePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
        if (!pageTitle.getText().equals(textPageTitle)) {
            throw new IllegalStateException("ERROR - This is not Payments & Infrastructure Page!");
        }
    }

    @FindBy(how = How.CLASS_NAME, using = "page-title")
    private WebElement pageTitle;
    private final String textPageTitle = "Payments & Infrastructure";

    @FindBy(how = How.LINK_TEXT, using = "Payments System")
    private WebElement linkPaymentsSystem;
    private final String urlPaymentsAndInfrastructure = Hooks.getBaseUrlWeb() + "payments-and-infrastructure/";

    public boolean isUrlCorrect(){
        return this.driver.getCurrentUrl().equals(urlPaymentsAndInfrastructure);
    }

    public PaymentsSystemPage ClickPaymentsSystem() {
        highlightElement(linkPaymentsSystem);
        linkPaymentsSystem.click();
        return new PaymentsSystemPage(this.driver);
    }
}
