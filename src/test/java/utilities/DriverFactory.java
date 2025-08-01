package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.HashMap;
import java.util.Map;

public class DriverFactory {
    private static WebDriver driver;
    public static WebDriver getDriver() {
        if (driver == null) {
            String browser = System.getProperty("browser", "chrome");
            switch (browser.toLowerCase()) {
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                case "edge":
                    driver = new EdgeDriver();
                    break;
                case "chrome":
                default:
                    ChromeOptions options = new ChromeOptions();
                    // 🔒 Disable save password and autofill prompts
                    Map<String, Object> prefs = new HashMap<>();
                    prefs.put("credentials_enable_service", false);
                    prefs.put("profile.password_manager_enabled", false);
                    prefs.put("autofill.profile_enabled", false);
                    prefs.put("profile.autofill_profile_enabled", false);
                    options.setExperimentalOption("prefs", prefs);
                    options.addArguments("--disable-save-password-bubble");
                    options.addArguments("--disable-autofill-keyboard-accessory-view");
                    driver = new ChromeDriver(options);
                    break;
            }
            driver.manage().window().maximize();
            driver.get("https://www.pepsicopartners.com/");
        }
        return driver;
    }
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
