package co.wedevx.digitalbank.automation.ui.pages;


import co.wedevx.digitalbank.automation.ui.models.NewSavingsAccountInfo;
import co.wedevx.digitalbank.automation.ui.utils.ConfigReader;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static co.wedevx.digitalbank.automation.ui.utils.Driver.getDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateSavingsPage extends BaseMenuPage{

    public CreateSavingsPage(WebDriver driver){
        super(driver);
    }

    @FindBy(id="Savings")
    private WebElement savingsAccountTypeRadioButton;

    @FindBy(id="Money Market")
    private WebElement moneyMarketAccountTypeRadioButton;

    @FindBy(id="Individual")
    private WebElement individualOwnershipRadioButton;

    @FindBy(id="Joint")
    private WebElement jointOwnershipRadioButton;

    @FindBy(id="name")
    private WebElement accountNameTxt;

    @FindBy(id="openingBalance")
    private WebElement openingBalanceTxt;

    @FindBy(id="newSavingsSubmit")
    private WebElement submitButton;

    public void createNewSavings(List<NewSavingsAccountInfo> newSavingsAccountInfos){
        NewSavingsAccountInfo testDataForOneSavingsAccount = newSavingsAccountInfos.get(0);

        savingsButton.click();
        newSavingsButton.click();

        assertEquals(ConfigReader.getPropertiesValue("digitalbank.createnewsavingsurl"), getDriver().getCurrentUrl(), "Button didn't take to " + ConfigReader.getPropertiesValue("digitalbank.createnewsavingsurl"));

        //the user selects the account type
        if(testDataForOneSavingsAccount.getSavingsAccountType().equalsIgnoreCase("Savings")){
            savingsAccountTypeRadioButton.click();
        }else if(testDataForOneSavingsAccount.getSavingsAccountType().equalsIgnoreCase("Money Market")){
            moneyMarketAccountTypeRadioButton.click();
        }else{
            throw new NoSuchElementException("Invalid checking account type option. Only supports Standard Checking and Interest Checking.");
        }

        //the user selects ownership
        if(testDataForOneSavingsAccount.getAccountOwnership().equalsIgnoreCase("Individual")){
            individualOwnershipRadioButton.click();
        }else if(testDataForOneSavingsAccount.getAccountOwnership().equalsIgnoreCase("Joint")){
            jointOwnershipRadioButton.click();
        }else{
            throw new NoSuchElementException("Invalid ownership type option. Only supports Individual and Joint ownership.");
        }

        //the user names the account
        accountNameTxt.sendKeys(testDataForOneSavingsAccount.getAccountName());

        //the user makes the initial deposit
        openingBalanceTxt.sendKeys(String.valueOf(testDataForOneSavingsAccount.getInitialDepositAmount()));

        //the user clicks on submit button
        submitButton.click();
    }


}
