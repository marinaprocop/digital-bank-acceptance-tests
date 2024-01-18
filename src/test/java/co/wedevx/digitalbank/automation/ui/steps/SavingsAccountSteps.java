package co.wedevx.digitalbank.automation.ui.steps;

import co.wedevx.digitalbank.automation.ui.models.AccountCard;
import co.wedevx.digitalbank.automation.ui.models.NewSavingsAccountInfo;
import co.wedevx.digitalbank.automation.ui.models.Transaction;
import co.wedevx.digitalbank.automation.ui.pages.CreateSavingsPage;
import co.wedevx.digitalbank.automation.ui.pages.LogInPage;
import co.wedevx.digitalbank.automation.ui.pages.ViewSavingsAccountPage;
import co.wedevx.digitalbank.automation.ui.utils.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SavingsAccountSteps {
    WebDriver driver = Driver.getDriver();
    private LogInPage logInPage = new LogInPage(driver);

    private CreateSavingsPage createSavingsPage = new CreateSavingsPage(driver);

    private ViewSavingsAccountPage viewSavingsAccountPage = new ViewSavingsAccountPage(driver);

    @Given("the user logged in with the following credentials {string} {string}")
    public void the_user_logged_in_with_the_following_credentials(String username, String password) {
        logInPage.logIn(username, password);
    }

    @When("the user creates new savings account with data")
    public void the_user_creates_new_savings_account_with_data(List<NewSavingsAccountInfo> newSavingsAccountInfos) {
        createSavingsPage.createNewSavings(newSavingsAccountInfos);
    }

    @Then("the user should see the following green {string} message")
    public void the_user_should_see_the_following_green_message(String expectedConfirmationMessage) {
        assertEquals(expectedConfirmationMessage, viewSavingsAccountPage.getActualConfirmationMessage());
    }

    @Then("the user should see the newly added savings account card")
    public void the_user_should_see_the_newly_added_savings_account_card(List<AccountCard> accountCardList) {
        Map<String, String> actualResultMap = viewSavingsAccountPage.getNewlyAddedSavingsAccountInfoMap();
        AccountCard expectedResult = accountCardList.get(0);

        assertEquals(expectedResult.getAccountName(), actualResultMap.get("actualAccountName"));
        //assertEquals("Account: "+expectedResult.getAccountType(), actualResultMap.get("actualAccountType"));
        assertEquals("Ownership: "+expectedResult.getOwnership(), actualResultMap.get("actualOwnership"));
        //assertEquals("Account Number: "+expectedResult.getAccountNumber(), actualResultMap.get("actualAccountNumber"));
        assertEquals("Interest Rate: "+expectedResult.getInterestRate(), actualResultMap.get("actualInterestRate"));
        String expectedBalance= String.format("%.2f", expectedResult.getBalance());
        assertEquals("Balance: $"+expectedBalance, actualResultMap.get("actualBalance"));


    }

    @Then("the user should see the following transactions displayed")
    public void the_user_should_see_the_following_transactions_displayed(List<Transaction> transactionsList) {
        Map<String, String> actualResultMap = viewSavingsAccountPage.getNewlyAddedSavingsAccountTransactionInfoMap();
        Transaction transactionList = transactionsList.get(0);
        assertEquals(transactionList.getCategory(), actualResultMap.get("actualCategoryResult"), "Category mismatch");
        //assertEquals(transactionList.getDescription(), actualDescription, "Description mismatch");
        assertEquals(transactionList.getAmount(), Double.parseDouble(actualResultMap.get("actualAmount")), "Amount mismatch");
        assertEquals(transactionList.getBalance(), Double.parseDouble(actualResultMap.get("actualBalance")), "Balance mismatch");
    }
    }

