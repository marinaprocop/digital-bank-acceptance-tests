package co.wedevx.digitalbank.automation.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LogInPage extends BasePage{
    public LogInPage(WebDriver driver){
        super(driver);
    }

    @FindBy(id="username")
    private WebElement usernameTextBox;

    @FindBy(id="password")
    private WebElement passwordTextBox;

    @FindBy(id="remember-me")
    private WebElement rememberMeCheckBox;

    @FindBy(xpath="//button")
    private  WebElement submitButton;

    @FindBy(xpath="//a[contains(text(), 'Sign Up Here')]")
    private WebElement signUpHereLink;

    public void logIn(String username, String password){
        usernameTextBox.sendKeys(username);
        passwordTextBox.sendKeys(password);
        submitButton.click();
    }

}