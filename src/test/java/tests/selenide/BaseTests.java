package tests.selenide;

import allure.selenide.CustomAllureSelenide;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

public class BaseTests {
    @BeforeAll
    public static void setup(){
        SelenideLogger.addListener("AllureSelenide",new CustomAllureSelenide().screenshots(true).savePageSource(true));
    }

    //@BeforeEach
    public void option(){
        Configuration.timeout = 6000;
        Configuration.browser="chrome";

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-extensions");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        Configuration.browserCapabilities = capabilities;

        System.setProperty("webdriver.chrome.driver",System.getenv("CHROME_DRIVER"));
        WebDriver driver;
        driver = new ChromeDriver(options);
        setWebDriver(driver);
    }

}
