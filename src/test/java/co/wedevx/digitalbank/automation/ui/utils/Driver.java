package co.wedevx.digitalbank.automation.ui.utils;

import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Driver {
    private static WebDriver driver;
    private Driver(){

    }

    public static WebDriver getDriver()  {
        if(driver == null){

            String browser = ConfigReader.getPropertiesValue("digitalbank.browser");

            switch (browser.toLowerCase()){
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver =new ChromeDriver();
                    break;
                case "safari":
                    WebDriverManager.safaridriver().setup();
                    driver = new SafariDriver();
                    break;
                case "ie":
                    WebDriverManager.iedriver().setup();
                    driver = new InternetExplorerDriver();
                    break;
                case "headless":
                    ChromeDriverManager.getInstance(DriverManagerType.CHROME).setup();
                    ChromeOptions options = new ChromeOptions();

                    options.addArguments("--window-size=1920,1080");
                    options.addArguments("disable-extensions");
                    options.setExperimentalOption("useAutomationExtension", false);
                    options.addArguments("--proxy-server='direct://'");
                    options.addArguments("--proxy-bypass-list=*");
                    options.addArguments("--start-maximized");
                    options.addArguments("--headless");

                    driver = new ChromeDriver(options);
                    break;
                case "saucelabs":
                    String platform = ConfigReader.getPropertiesValue("digitalbank.saucelabs.platform");
                    String browserSauceLabs = ConfigReader.getPropertiesValue("digitalBank.saucelabs.browser");
                    String browserVersion= ConfigReader.getPropertiesValue("digitalbank.saucelab.browser.version");
                    driver = loadSauceLabs(platform, browserSauceLabs, browserVersion);
                    break;
                default:
                   // WebDriverManager.firefoxdriver().setup();
                   // driver = new FirefoxDriver();
                    break;
            }
        }
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

    public static void takeScreenShot(Scenario scenario){
        if(scenario.isFailed()){
            //taking a screenshot
            final byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
            //adding the screenshot to the report
            scenario.attach(screenshot, "image/png", "screenshot");
        }
    }
    public static void closeDriver(){
      if(driver != null){
            driver.quit();
            driver = null;
        }
    }

    private static WebDriver loadSauceLabs(String platform, String browserType, String browserVersion) {
        String username = ConfigReader.getPropertiesValue("digitalbank.saucelabs.username");
        String accessKey = "76d93024-0f6d-40b1-83e9-7c8635de4b3e";
        String host = ConfigReader.getPropertiesValue("digitalbank.saucelabs.host");
        String url = "https://"+ username+ ":" +accessKey+ "@" + host;

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", platform);
        capabilities.setCapability("browserName", browserType);
        capabilities.setCapability("browserVersion", browserVersion);

        WebDriver driver = null;
        try {
            driver = new RemoteWebDriver(new URL(url), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return  driver;
    }
}
