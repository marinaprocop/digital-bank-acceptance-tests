package co.wedevx.digitalbank.automation.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewSavingsAccountPage extends BaseMenuPage{

    public ViewSavingsAccountPage(WebDriver driver){
        super(driver);
    }

//new-account-msg
    @FindBy(id="new-account-msg")
    private WebElement newAccountConfirmationAlert;

    @FindBy(xpath="//div[@id='firstRow']/div")
    private List<WebElement> allFirstRowDivs;

    @FindBy(xpath="//table[@id='transactionTable']/tbody/tr")
    private WebElement firstRowOfTransactions;

    public Map<String, String> getNewlyAddedSavingsAccountTransactionInfoMap(){
        List<WebElement> firstRowColumns = firstRowOfTransactions.findElements(By.xpath("td"));

        Map<String, String> actualResultMap = new HashMap<>();
        actualResultMap.put("actualCategoryResult", firstRowColumns.get(1).getText());
        actualResultMap.put("actualDescription", firstRowColumns.get(2).getText());
        actualResultMap.put("actualAmount", firstRowColumns.get(3).getText().substring(1));
        actualResultMap.put("actualBalance", firstRowColumns.get(4).getText().substring(1));

        return actualResultMap;

    }

    public Map<String, String> getNewlyAddedSavingsAccountInfoMap(){
        WebElement lastAccountCard = allFirstRowDivs.get(allFirstRowDivs.size()-1);
        String actualResult = lastAccountCard.getText();

        Map<String, String > actualResultMap = new HashMap<>();
        actualResultMap.put("actualAccountName", actualResult.substring(0, actualResult.indexOf("\n")).trim());
        actualResultMap.put("actualAccountType", actualResult.substring(actualResult.indexOf("Account"), actualResult.indexOf("Ownership")).trim());
        actualResultMap.put("actualOwnership", actualResult.substring(actualResult.indexOf("Ownership"), actualResult.indexOf("Account Number:")).trim());
        actualResultMap.put("actualAccountNumber", actualResult.substring(actualResult.indexOf("Account Number:"), actualResult.indexOf("Interest Rate")).trim());
        actualResultMap.put("actualInterestRate", actualResult.substring(actualResult.indexOf("Interest Rate:"), actualResult.indexOf("Balance:")).trim());
        actualResultMap.put("actualBalance", actualResult.substring(actualResult.indexOf("Balance:")).trim());
        System.out.println("Actual Result String: " + actualResult);
        System.out.println("Actual Account Type: " + actualResultMap.get("actualAccountType"));
        return actualResultMap;
    }


    public String getActualConfirmationMessage(){
        return newAccountConfirmationAlert.getText();
    }

}
