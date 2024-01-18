Feature: Creating a new checking account
  Scenario: Create a standard individual checking account
    Given the user logged in as "milavolo@gmail.com" "MilaVolo1995"
    When the user creates new checking account with the following data
      | checkingAccountType | accountOwnership | accountName          | initialDepositAmount |
      | Standard Checking  | Individual       | Mila Volo Checking 2 | 100000.00            |
    Then the user should see the green "Successfully created new Standard Checking account named Mila Volo Checking 2" message
    And the user should see the newly added account card
      |accountName         |accountType      |ownership |accountNumber|interestRate|balance  |
      |Mila Volo Checking 2|Standard Checking|Individual|486133589    |0.0%        |100000.00|
    And the user should see the following transactions
      |date            |category|description              |amount    |balance   |
      |2023-12-19 04:47|Income  |845324678 (DPT) - Deposit|100000.00 |100000.00 |