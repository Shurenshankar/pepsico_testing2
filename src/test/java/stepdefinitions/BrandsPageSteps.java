package stepdefinitions;

import static org.junit.Assert.*;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.*;
import pages.BrandsPage;
import utilities.DriverFactory;

public class BrandsPageSteps {

    private WebDriver driver = DriverFactory.getDriver();
    private BrandsPage brandsPage;

    @Given("I navigate to the Starbucks brands page")
    public void i_navigate_to_the_starbucks_brands_page_through_the_homepage() {
        try {
            brandsPage = new BrandsPage(driver);
            sleepOneSecond();

            brandsPage.clickBrandsButton();
            sleepOneSecond();

            String originalTab = driver.getWindowHandle();
            for (String handle : driver.getWindowHandles()) {
                if (!handle.equals(originalTab)) {
                    driver.switchTo().window(handle);
                    break;
                }
            }

            brandsPage.clickStarbucksBrandTile();
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception in navigating to Starbucks brands page: " + e.getMessage());
        }
        sleepOneSecond();
    }

    @Then("the Starbucks banner should be visible")
    public void the_starbucks_banner_should_be_visible() {
        try {
            assertTrue("Starbucks banner is not visible", brandsPage.isBannerVisible());
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception checking banner visibility: " + e.getMessage());
        }
        sleepOneSecond();
    }

    @When("I click the 'View All Products' CTA")
    public void i_click_the_view_all_products_cta() {
        try {
            brandsPage.clickViewAllProducts();
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception clicking 'View All Products' CTA: " + e.getMessage());
        }
        sleepOneSecond();
    }

    @Then("the URL should contain {string}")
    public void the_url_should_contain(String expectedText) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains(expectedText));
            assertTrue("URL does not contain expected text: " + expectedText,
                driver.getCurrentUrl().contains(expectedText));
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception verifying URL contains text: " + e.getMessage());
        }
        sleepOneSecond();
    }

    @When("I navigate to the Starbucks brands page with parameters")
    public void i_navigate_to_the_starbucks_brands_page_with_parameters() {
        try {
            driver.get("https://www.pepsicopartners.com/pepsico/en/USD/PEPSICO-BRANDS/STARBUCKS%C2%AE/c/brand_starbucks?source=brand_pepsicobrands&root=beverages&selectedBrand=brand_starbucks");
            brandsPage = new BrandsPage(driver);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception navigating to Starbucks brand page with parameters: " + e.getMessage());
        }
        sleepOneSecond();
    }

    @When("I click on the first sub-brand tile")
    public void i_click_on_the_first_sub_brand_tile() {
        try {
            brandsPage.clickSubBrandByIndex(0);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception clicking sub-brand tile: " + e.getMessage());
        }
        sleepOneSecond();
    }

    @When("I click on the 'Learn More' link in the Tips section")
    public void i_click_on_the_learn_more_link_in_the_tips_section() {
        try {
            brandsPage.clickTipsLearnMore();
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception clicking 'Learn More' link: " + e.getMessage());
        }
        sleepOneSecond();
    }

    @Then("the URL should change and not contain {string}")
    public void the_url_should_change_and_not_contain(String unexpectedText) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.not(ExpectedConditions.urlContains(unexpectedText)));
            assertFalse("URL still contains unexpected text: " + unexpectedText,
                driver.getCurrentUrl().contains(unexpectedText));
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception verifying URL change: " + e.getMessage());
        }
        sleepOneSecond();
    }

    @Then("all images on the brands page should be loaded")
    public void all_images_on_the_brands_page_should_be_loaded() {
        try {
            assertTrue("Some images failed to load", brandsPage.allImagesLoaded());
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception checking image load status: " + e.getMessage());
        }
        sleepOneSecond();
    }

    @Then("there should be at least one CTA present")
    public void there_should_be_at_least_one_cta_present() {
        try {
            assertTrue("No CTA links or buttons found", brandsPage.getCtaCount() > 0);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception checking CTA presence: " + e.getMessage());
        }
        sleepOneSecond();
    }

    @Then("the page title should not contain {string} or {string}")
    public void the_page_title_should_not_contain_or(String text1, String text2) {
        try {
            String title = driver.getTitle().toLowerCase();
            assertFalse("Page title contains error text",
                title.contains(text1.toLowerCase()) || title.contains(text2.toLowerCase()));
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception verifying page title: " + e.getMessage());
        }
        sleepOneSecond();
    }

    @Then("the footer links should be visible")
    public void the_footer_links_should_be_visible() {
        try {
            assertTrue("Footer links not present", brandsPage.isFooterVisible());
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception checking footer visibility: " + e.getMessage());
        }
        sleepOneSecond();
    }

    // Sleep helper
    private void sleepOneSecond() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
