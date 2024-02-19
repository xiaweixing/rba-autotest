package com.alfred.pageobjects;

import com.alfred.common.BasePage;
import com.alfred.common.Hooks;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class PaymentsSystemPage extends BasePage {

    private WebDriver driver;

    public PaymentsSystemPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
        if (!pageTitle.getText().equals(textPageTitle)) {
            throw new IllegalStateException("ERROR - This is not Payments System Page!");
        }
    }

    @FindBy(how = How.CLASS_NAME, using = "page-title")
    private WebElement pageTitle;
    private final String textPageTitle = "Payments System";

    @FindBy(how = How.LINK_TEXT, using = "Non-cash Payments")
    private WebElement linkNonCashPayments;
    public void ClickNonCashPayments() {
        highlightElement(linkNonCashPayments);
        linkNonCashPayments.click();
        unHighlightElement(linkNonCashPayments);
    }

    @FindBy(how = How.ID, using = "non_cash_payments")
    private WebElement h2NonCashPayments;

    private final String urlPaymentsSystem = Hooks.getBaseUrlWeb() + "payments-and-infrastructure/payments-system.html";
    private final String urlNonCashPayments = Hooks.getBaseUrlWeb() + "payments-and-infrastructure/payments-system.html#non_cash_payments";

    public boolean isUrlCorrect(){
        return this.driver.getCurrentUrl().equals(urlPaymentsSystem);
    }

    public boolean NonCashPaymentsVisible() {
        return isElementVisible(h2NonCashPayments, 30) && this.driver.getCurrentUrl().equals(urlNonCashPayments);
    }
}
