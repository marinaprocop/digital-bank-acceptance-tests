package co.wedevx.digitalbank.automation.ui.steps.data_transformer;

import co.wedevx.digitalbank.automation.ui.models.AccountCard;
import co.wedevx.digitalbank.automation.ui.models.NewCheckingAccountInfo;
import co.wedevx.digitalbank.automation.ui.models.NewSavingsAccountInfo;
import co.wedevx.digitalbank.automation.ui.models.Transaction;
import io.cucumber.java.DataTableType;


import java.util.Map;

public class DataTableTransformer {
@DataTableType
    public AccountCard accountCardEntry(Map<String, String> entry) {
    String accountName = entry.get("accountName");
    String accountType = entry.get("accountType");
    String ownership = entry.get("ownership");
    long accountNumber = Long.parseLong(entry.get("accountNumber"));
    String interestRate = entry.get("interestRate");
    double balance = Double.parseDouble(entry.get("balance"));

    return new AccountCard(accountName,accountType,ownership,accountNumber,interestRate, balance);
}

@DataTableType
    public Transaction transactionsEntry(Map<String, String> entry){
    String date = entry.get("date");
    String category = entry.get("category");
    String description = entry.get("description");
    double amount = Double.parseDouble(entry.get("amount"));
    double balance = Double.parseDouble(entry.get("balance"));

    return new Transaction(date, category, description, amount, balance);
}

@DataTableType
    public NewCheckingAccountInfo newCheckingAccountInfoEntry(Map<String, String> entry){
    String checkingAccountType = entry.get("checkingAccountType");
    String accountOwnership = entry.get("accountOwnership");
    String accountName = entry.get("accountName");
    double initialDepositAmount = Double.parseDouble(entry.get("initialDepositAmount"));

    return new NewCheckingAccountInfo(checkingAccountType, accountOwnership, accountName, initialDepositAmount);
}

    @DataTableType
    public NewSavingsAccountInfo newSavingsAccountInfo(Map<String, String> entry){
        String savingsAccountType = entry.get("savingsAccountType");
        String accountOwnership = entry.get("accountOwnership");
        String accountName = entry.get("accountName");
        double initialDepositAmount = Double.parseDouble(entry.get("initialDepositAmount"));

        return new NewSavingsAccountInfo(savingsAccountType, accountOwnership, accountName, initialDepositAmount);
    }
}

