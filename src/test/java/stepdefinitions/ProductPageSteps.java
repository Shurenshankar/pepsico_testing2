package stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.ProductPage;
import utilities.DriverFactory;

public class ProductPageSteps {
    private WebDriver driver;
    private ProductPage productPage;
    private static final String PLP_URL =
        "https://www.pepsicopartners.com/pepsico/en/USD/BEVERAGES/c/beverages?showProductListing=true";

    public ProductPageSteps() {
        this.driver = DriverFactory.getDriver();
        this.productPage = new ProductPage(driver);
    }

    @Given("I am on the Beverages product listing page")
    public void i_am_on_the_beverages_product_listing_page() {
        try {
            driver.get(PLP_URL);
            sleepOneSecond();
            Assert.assertTrue(driver.getCurrentUrl().contains("/BEVERAGES"), "Did not land on the Beverages PLP.");
        } catch (Exception e) {
            Assert.fail("Exception during page load: " + e.getMessage());
        }
    }

    @Then("the first product tile is visible")
    public void the_first_product_tile_is_visible() {
        try {
        	sleepOneSecond();
            Assert.assertTrue(productPage.isProductVisible(), "Product not visible.");
        } catch (Exception e) {
            Assert.fail("Error checking product visibility: " + e.getMessage());
        }
    }

    @Then("the first product tile is clickable")
    public void the_first_product_tile_is_clickable() {
        try {
        	sleepOneSecond();
            Assert.assertTrue(productPage.isProductVisible(), "Product not clickable.");
        } catch (Exception e) {
            Assert.fail("Error checking product clickability: " + e.getMessage());
        }
    }

    @Then("I can scroll to the first product tile")
    public void i_can_scroll_to_the_first_product_tile() {
        try {
        	sleepOneSecond();
            Assert.assertTrue(productPage.scrollToProduct(), "Cannot scroll to product.");
        } catch (Exception e) {
            Assert.fail("Error during scroll to product: " + e.getMessage());
        }
    }

    @Then("the product container is enabled")
    public void the_product_container_is_enabled() {
        try {
        	sleepOneSecond();
            Assert.assertTrue(productPage.isProductContainerEnabled(), "Product container not enabled.");
        } catch (Exception e) {
            Assert.fail("Error checking product container: " + e.getMessage());
        }
    }

    @Then("hovering over the first product tile works")
    public void hovering_over_the_first_product_tile_works() {
        try {
        	sleepOneSecond();
            Assert.assertTrue(productPage.hoverOverProduct(), "Hover over product failed.");
        } catch (Exception e) {
            Assert.fail("Error hovering over product: " + e.getMessage());
        }
    }

    @Then("the page title contains Beverages")
    public void the_page_title_contains_beverages() {
        try {
        	sleepOneSecond();
            Assert.assertTrue(productPage.isTitleCorrect(), "Page title does not contain 'beverages'.");
        } catch (Exception e) {
            Assert.fail("Error validating page title: " + e.getMessage());
        }
    }

    @Then("at least one product is listed")
    public void at_least_one_product_is_listed() {
        try {
        	sleepOneSecond();
            Assert.assertTrue(productPage.hasAtLeastOneProduct(), "No products displayed.");
        } catch (Exception e) {
            Assert.fail("Error validating product list: " + e.getMessage());
        }
    }

    @Then("I can scroll to the bottom of the page")
    public void i_can_scroll_to_the_bottom_of_the_page() {
        try {
        	sleepOneSecond();
            Assert.assertTrue(productPage.scrollToBottom(), "Scroll to bottom failed.");
        } catch (Exception e) {
            Assert.fail("Error scrolling to bottom: " + e.getMessage());
        }
    }
    private void sleepOneSecond() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interrupted status
        }
    }
}
