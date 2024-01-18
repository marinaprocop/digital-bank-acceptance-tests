package co.wedevx.digitalbank.automation.ui.steps;

import co.wedevx.digitalbank.automation.ui.models.AccountCard;
import co.wedevx.digitalbank.automation.ui.models.NewCheckingAccountInfo;
import co.wedevx.digitalbank.automation.ui.models.Transaction;
import co.wedevx.digitalbank.automation.ui.pages.CreateCheckingPage;
import co.wedevx.digitalbank.automation.ui.pages.LogInPage;
import co.wedevx.digitalbank.automation.ui.pages.ViewCheckingAccountPage;
import co.wedevx.digitalbank.automation.ui.utils.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckingAccountSteps {
    WebDriver driver = Driver.getDriver();
    private LogInPage logInPage = new LogInPage(driver);
    private CreateCheckingPage checkingPage = new CreateCheckingPage(driver);
    private ViewCheckingAccountPage viewCheckingAccountPage = new ViewCheckingAccountPage(driver);

    @Given("the user logged in as {string} {string}")
    public void the_user_logged_in_as(String username, String password){
        logInPage.logIn(username, password);

    }

    @When("the user creates new checking account with the following data")
    public void the_user_creates_new_checking_account_with_the_following_data(List<NewCheckingAccountInfo> newCheckingAccountInfoList) {
       checkingPage.createNewChecking(newCheckingAccountInfoList);
    }

    @Then("the user should see the green {string} message")
    public void the_user_should_see_the_green_message(String expectedConfMessage) {
        expectedConfMessage = "Confirmation " + expectedConfMessage+ "\n×";
        assertEquals(expectedConfMessage, viewCheckingAccountPage.getActualConfirmationMessage());
    }

    @Then("the user should see the newly added account card")
    public void the_user_should_see_the_newly_added_account_card(List<AccountCard> accountCardList) {
        Map<String, String> actualResultMap = viewCheckingAccountPage.getNewlyAddedCheckingAccountInfoMap();
        AccountCard expectedResult = accountCardList.get(0);

        assertEquals(expectedResult.getAccountName(), actualResultMap.get("actualAccountName"));
        assertEquals("Account: "+expectedResult.getAccountType(), actualResultMap.get("actualAccountType"));
        assertEquals("Ownership: "+expectedResult.getOwnership(), actualResultMap.get("actualOwnership"));
        //assertEquals("Account Number: "+expectedResult.getAccountNumber(), actualResultMap.get("actualAccountNumber"));
        assertEquals("Interest Rate: "+expectedResult.getInterestRate(), actualResultMap.get("actualInterestRate"));
        String expectedBalance= String.format("%.2f", expectedResult.getBalance());
        assertEquals("Balance: $"+expectedBalance, actualResultMap.get("actualBalance"));

    }

    @Then("the user should see the following transactions")
    public void the_user_should_see_the_following_transactions(List<Transaction> transactionsList) {
        Map<String, String> actualResultMap = viewCheckingAccountPage.getNewlyAddedCheckingAccountTransactionInfoMap();
        Transaction transactionList = transactionsList.get(0);
        assertEquals(transactionList.getCategory(), actualResultMap.get("actualCategoryResult"), "Category mismatch");
        //assertEquals(transactionList.getDescription(), actualDescription, "Description mismatch");
        assertEquals(transactionList.getAmount(), Double.parseDouble(actualResultMap.get("actualAmount")), "Amount mismatch");
        assertEquals(transactionList.getBalance(), Double.parseDouble(actualResultMap.get("actualBalance")), "Balance mismatch");
    }

}