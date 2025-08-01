package stepdefinitions;

import io.cucumber.java.AfterAll;
import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import pages.HomePage;

import java.time.Duration;
import java.util.List;

import static org.testng.Assert.*;

public class HomePageSteps {
    private static WebDriver driver;
    private static WebDriverWait wait;

    @Given("I open the PepsiCo Partners homepage")
    public void i_open_the_homepage() {
        try {
            if (driver == null) {
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            }
            driver.get("https://www.pepsicopartners.com/pepsico/en/USD/");
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Error opening homepage: " + e.getMessage());
            fail("Homepage did not load.");
        }
        sleepOneSecond();
    }

    @Then("the search input is visible")
    public void the_search_input_is_visible() {
        try {
            WebElement icon = wait.until(ExpectedConditions.elementToBeClickable(HomePage.SEARCH_ICON));
            icon.click();
            Thread.sleep(1000);

            WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(HomePage.SEARCH_INPUT));
            assertTrue(input.isDisplayed(), "Search input is not visible.");
        } catch (Exception e) {
            System.out.println("Search input not visible: " + e.getMessage());
            fail("Search input visibility check failed.");
        }
        sleepOneSecond();
    }

    @When("I type {string} into the search input")
    public void i_type_into_the_search_input(String text) {
        try {
            WebElement input = wait.until(ExpectedConditions.elementToBeClickable(HomePage.SEARCH_INPUT));
            input.clear();
            input.sendKeys(text);
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Typing into search input failed: " + e.getMessage());
            fail("Search input typing failed.");
        }
        sleepOneSecond();
    }

    @Then("search suggestions are displayed")
    public void search_suggestions_are_displayed() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(10));
            List<WebElement> suggestions = shortWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(HomePage.SUGGESTION_LIST_ITEMS));
            assertFalse(suggestions.isEmpty(), "Suggestions not shown for the search text.");
        } catch (Exception e) {
            System.out.println("Search suggestions error: " + e.getMessage());
            fail("Search suggestions not displayed.");
        }
        sleepOneSecond();
    }

    @Then("the page ready state should be {string}")
    public void the_page_ready_state_should_be(String expectedState) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            String readyState = (String) js.executeScript("return document.readyState");
            assertEquals(readyState, expectedState, "Homepage did not load completely.");
        } catch (Exception e) {
            System.out.println("Page ready state error: " + e.getMessage());
            fail("Page ready state validation failed.");
        }
        sleepOneSecond();
    }

    @Then("the current URL contains {string}")
    public void the_current_url_contains(String expectedPart) {
        try {
            String currentURL = driver.getCurrentUrl();
            assertTrue(currentURL.contains(expectedPart), "Incorrect URL loaded. Actual: " + currentURL);
        } catch (Exception e) {
            System.out.println("URL check failed: " + e.getMessage());
            fail("URL validation failed.");
        }
        sleepOneSecond();
    }

    @Then("the \"Become a Customer\" button is clickable")
    public void the_become_a_customer_button_is_clickable() {
        try {
            WebElement el = wait.until(ExpectedConditions.elementToBeClickable(HomePage.BECOME_CUSTOMER_BTN));
            assertTrue(el.isDisplayed(), "'Become a Customer' button is not clickable.");
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Become a Customer button error: " + e.getMessage());
            fail("Become a Customer button not clickable.");
        }
        sleepOneSecond();
    }

    @Then("the PepsiCo logo is clickable")
    public void the_pepsico_logo_is_clickable() {
        try {
            WebElement logo = wait.until(ExpectedConditions.elementToBeClickable(HomePage.PEPSI_LOGO));
            assertTrue(logo.isDisplayed(), "PepsiCo logo is not clickable.");
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("PepsiCo logo click error: " + e.getMessage());
            fail("PepsiCo logo not clickable.");
        }
        sleepOneSecond();
    }

    @Then("the Login\\/Register link is clickable")
    public void the_login_register_link_is_clickable() {
        try {
            WebElement link = wait.until(ExpectedConditions.elementToBeClickable(HomePage.LOGIN_REGISTER_LINK));
            assertTrue(link.isDisplayed(), "Login/Register link is not clickable.");
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Login/Register link error: " + e.getMessage());
            fail("Login/Register link not clickable.");
        }
        sleepOneSecond();
    }

    @AfterAll
    public static void teardown() {
        try {
            if (driver != null) {
                driver.quit();
                driver = null;
            }
        } catch (Exception e) {
            System.out.println("Teardown error: " + e.getMessage());
        }
    }

    private void sleepOneSecond() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
