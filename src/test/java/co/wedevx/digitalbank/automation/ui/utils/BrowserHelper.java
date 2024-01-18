package co.wedevx.digitalbank.automation.ui.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BrowserHelper {
    //wait until the element is visible
    public static WebElement waitForTheVisibilityOfElement(WebDriver driver, WebElement element, int timeToWaitInSec){
        WebDriverWait wait = new WebDriverWait(driver, timeToWaitInSec);
        return  wait.until(ExpectedConditions.visibilityOf(element));
    }
    //wait until the element is clickable and click on it
    public static WebElement waitUntilElementClickableAndClick(WebDriver driver, WebElement element, int timeToWaitInSec){
        WebDriverWait wait = new WebDriverWait(driver, timeToWaitInSec);
        WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(element));
        clickableElement.click();

        return clickableElement;
    }

    public WebElement fluentWaitForElementPresence(WebDriver driver, WebElement element, int maxWaitTimeInSec) {
        try {
            Wait<WebDriver> wait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(maxWaitTimeInSec))
                    .pollingEvery(Duration.ofSeconds(1))
                    .ignoring(org.openqa.selenium.NoSuchElementException.class);

            return wait.until(ExpectedConditions.presenceOfElementLocated((By) element));
        } catch (Exception e) {
            // Handle exceptions gracefully and provide clear error messages
            throw new RuntimeException("Element not found within the specified time: " + e.getMessage());
        }
    }

    public void scrollIntoView(WebDriver driver, WebElement element) {
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(element);
            actions.perform();
        } catch (Exception e) {
            throw new RuntimeException("Error while scrolling element into view: " + e.getMessage());
        }
    }

    public void fillTextInput(WebDriver driver, WebElement textInput, String value) {
        try {
            scrollIntoView(driver, textInput);

            if (textInput.isDisplayed() && textInput.isEnabled()) {
                // Clear any existing value and fill the input with the provided value
                textInput.clear();
                textInput.sendKeys(value);
            } else {
                throw new RuntimeException("Text input element is not interactable");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error while filling text input: " + e.getMessage());
        }
    }
}
