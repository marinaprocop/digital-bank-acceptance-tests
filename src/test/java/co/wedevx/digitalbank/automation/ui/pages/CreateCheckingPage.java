package co.wedevx.digitalbank.automation.ui.pages;

import co.wedevx.digitalbank.automation.ui.models.NewCheckingAccountInfo;
import co.wedevx.digitalbank.automation.ui.utils.ConfigReader;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static co.wedevx.digitalbank.automation.ui.utils.Driver.getDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateCheckingPage extends BaseMenuPage{

    public CreateCheckingPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id="Standard Checking")
    private WebElement standardCheckingAccountRadioButton;

    @FindBy(id="Interest Checking")
    private WebElement interestCheckingAccountRadioButton;

    @FindBy(id="Individual")
    private WebElement individualOwnershipRadioButton;

    @FindBy(id="Joint")
    private WebElement jointOwnershipRadioButton;

    @FindBy(id="name")
    private WebElement accountNameTxt;

    @FindBy(id="openingBalance")
    private WebElement openingBalanceTxt;

    @FindBy(id="newCheckingSubmit")
    private WebElement submitButton;


public void createNewChecking(List<NewCheckingAccountInfo> newCheckingAccountInfoList){
    NewCheckingAccountInfo testDataForOneCheckingAccount = newCheckingAccountInfoList.get(0);

    checkingMenuButton.click();
    newCheckingButton.click();

    assertEquals(ConfigReader.getPropertiesValue("digitalbank.createnewcheckingurl"), getDriver().getCurrentUrl(), "Button didn't take to " + ConfigReader.getPropertiesValue("digitalbank.createnewcheckingurl"));

    //the user selects the account type
    if(testDataForOneCheckingAccount.getCheckingAccountType().equalsIgnoreCase("Standard Checking")){
        standardCheckingAccountRadioButton.click();
    }else if(testDataForOneCheckingAccount.getCheckingAccountType().equalsIgnoreCase("Interest Checking")){
        interestCheckingAccountRadioButton.click();
    }else{
        throw new NoSuchElementException("Invalid checking account type option. Only supports Standard Checking and Interest Checking.");
    }

    //the user selects ownership
    if(testDataForOneCheckingAccount.getAccountOwnership().equalsIgnoreCase("Individual")){
        individualOwnershipRadioButton.click();
    }else if(testDataForOneCheckingAccount.getAccountOwnership().equalsIgnoreCase("Joint")){
        jointOwnershipRadioButton.click();
    }else{
        throw new NoSuchElementException("Invalid ownership type option. Only supports Individual and Joint ownership.");
    }
    //the user names the account
    accountNameTxt.sendKeys(testDataForOneCheckingAccount.getAccountName());
    //the user makes the initial deposit
    openingBalanceTxt.sendKeys(String.valueOf(testDataForOneCheckingAccount.getInitialDepositAmount()));
    //the user clicks on submit button
    submitButton.click();
}

}
