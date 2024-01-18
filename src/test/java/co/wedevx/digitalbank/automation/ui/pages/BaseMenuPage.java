package co.wedevx.digitalbank.automation.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BaseMenuPage extends BasePage {

    public BaseMenuPage(WebDriver driver){
        super(driver);
    }

    @FindBy(id="home-menu-item")
    protected WebElement homeButton;
    @FindBy(id="checking-menu")
    protected WebElement checkingMenuButton;

    @FindBy(id="new-checking-menu-item")
    protected WebElement newCheckingButton;

    @FindBy(id="view-checking-menu-item")
    protected WebElement viewCheckingButton;

    @FindBy(id="savings-menu")
    protected WebElement savingsButton;

    @FindBy(id="view-savings-menu-item")
    protected WebElement viewSavingsButton;

    @FindBy(id="new-savings-menu-item")
    protected WebElement newSavingsButton;

    @FindBy(id="external-accounts-menu")
    protected WebElement externalAccountsButton;

    @FindBy(id="link-external-menu-item")
    protected WebElement linkExternalAccountsButton;

    @FindBy(id="view-external-menu-item")
    protected WebElement viewExternalAccountsButton;

    @FindBy(id="deposit-menu-item")
    protected WebElement depositButton;

    @FindBy(id="withdraw-menu-item")
    protected WebElement withdrawButton;

    @FindBy(id="transfer-menu-item")
    protected WebElement transferButton;

    @FindBy(id="visa-transfer-menu-item")
    protected WebElement visaTransferButton;

    public void goToHomePage(){
        homeButton.click();
    }
}
