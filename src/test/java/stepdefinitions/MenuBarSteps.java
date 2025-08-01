package stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.MenuBarPage;
import pages.ProductPage;
import utilities.DriverFactory;

public class MenuBarSteps extends DriverFactory {
    private WebDriver driver;
    private MenuBarPage menuBarPage;
    
    public MenuBarSteps() {
        this.driver = DriverFactory.getDriver();
        this.menuBarPage = new MenuBarPage(driver);
    }

    @Given("I am on the homepage menubar")
    public void i_am_on_the_homepage() {
        try {
            driver.get("https://www.pepsicopartners.com/");
            menuBarPage = new MenuBarPage(driver);
        } catch (Exception e) {
            System.out.println("Error navigating to homepage: " + e.getMessage());
            Assert.fail("Homepage navigation failed.");
        }
        sleepOneSecond();
    }

    @When("I check all menu items in the menu bar")
    public void i_check_all_menu_items_in_the_menu_bar() {
        try {
            for (By menu : menuBarPage.getAllMenus()) {
                Thread.sleep(1000);
                Assert.assertTrue(menuBarPage.isMenuDisplayed(menu), "Menu not visible: " + menu);
                menuBarPage.clickMenu(menu);
                System.out.println("Clicked menu: " + menuBarPage.getMenuText(menu));
            }
        } catch (Exception e) {
            System.out.println("Error verifying menu bar items: " + e.getMessage());
            Assert.fail("Menu bar check failed.");
        }
        sleepOneSecond();
    }

    @Then("all menu items should be visible and clickable")
    public void all_menu_items_should_be_visible_and_clickable() {
        try {
            // Already validated in the previous step
            System.out.println("All menu items verified.");
        } catch (Exception e) {
            System.out.println("Menu verification error: " + e.getMessage());
            Assert.fail("Final menu validation step failed.");
        }
        sleepOneSecond();
    }

    private void sleepOneSecond() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interrupted status
        }
    }
}
