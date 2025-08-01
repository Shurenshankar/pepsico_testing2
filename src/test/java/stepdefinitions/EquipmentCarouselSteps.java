package stepdefinitions;

import pages.EquipmentCarouselPage;
import utilities.DriverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import io.cucumber.java.en.*;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class EquipmentCarouselSteps extends DriverFactory {

    private WebDriverWait wait;
    private String slideNameBefore;
    private String slideImageSrcBefore;
    private WebDriver driver = DriverFactory.getDriver();

    public EquipmentCarouselSteps() {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private void sleepOneSecond() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Given("I am on the Equipment page")
    public void i_am_on_the_equipment_page() {
        try {
            driver.get("https://www.pepsicopartners.com/pepsico/en/USD/equipment");
            wait.until(ExpectedConditions.urlContains("/equipment"));
        } catch (Exception e) {
            Assert.fail("Failed to navigate to Equipment page: " + e.getMessage());
        }
        sleepOneSecond();
    }

    @When("I get the current slide name and image")
    public void i_get_the_current_slide_name_and_image() {
        try {
            slideNameBefore = driver.findElement(EquipmentCarouselPage.EquipmentCarouselLocators.equipmentName).getText();
            slideImageSrcBefore = driver.findElement(EquipmentCarouselPage.EquipmentCarouselLocators.equipmentImage).getAttribute("src");
        } catch (Exception e) {
            Assert.fail("Failed to get slide name and image: " + e.getMessage());
        }
        sleepOneSecond();
    }

    @When("I click the next arrow")
    public void i_click_the_next_arrow() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(EquipmentCarouselPage.EquipmentCarouselLocators.nextArrow)).click();
            wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElementLocated(
                EquipmentCarouselPage.EquipmentCarouselLocators.equipmentName, slideNameBefore)));
        } catch (Exception e) {
            Assert.fail("Next arrow click failed: " + e.getMessage());
        }
        sleepOneSecond();
    }

    @When("I click the previous arrow")
    public void i_click_the_previous_arrow() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(EquipmentCarouselPage.EquipmentCarouselLocators.prevArrow)).click();
            wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElementLocated(
                EquipmentCarouselPage.EquipmentCarouselLocators.equipmentName, slideNameBefore)));
        } catch (Exception e) {
            Assert.fail("Previous arrow click failed: " + e.getMessage());
        }
        sleepOneSecond();
    }

    @Then("the slide should change")
    public void the_slide_should_change() {
        try {
            String slideNameAfter = driver.findElement(EquipmentCarouselPage.EquipmentCarouselLocators.equipmentName).getText();
            Assert.assertNotEquals(slideNameBefore, slideNameAfter, "Slide name did not change");
        } catch (Exception e) {
            Assert.fail("Slide change verification failed: " + e.getMessage());
        }
        sleepOneSecond();
    }

    @Then("the image should update")
    public void the_image_should_update() {
        try {
            wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(
                EquipmentCarouselPage.EquipmentCarouselLocators.equipmentImage, "src", slideImageSrcBefore)));
            String slideImageSrcAfter = driver.findElement(EquipmentCarouselPage.EquipmentCarouselLocators.equipmentImage).getAttribute("src");
            Assert.assertNotEquals(slideImageSrcBefore, slideImageSrcAfter, "Image src did not change");
        } catch (Exception e) {
            Assert.fail("Image update verification failed: " + e.getMessage());
        }
        sleepOneSecond();
    }

    @When("I check if video button is present and click it if so")
    public void i_check_if_video_button_is_present_and_click_it_if_so() {
        try {
            List<WebElement> videos = driver.findElements(EquipmentCarouselPage.EquipmentCarouselLocators.videoButton);
            if (!videos.isEmpty()) {
                wait.until(ExpectedConditions.elementToBeClickable(EquipmentCarouselPage.EquipmentCarouselLocators.videoButton)).click();
            }
        } catch (Exception e) {
            Assert.fail("Video button click failed: " + e.getMessage());
        }
        sleepOneSecond();
    }

    @Then("a video window should open")
    public void a_video_window_should_open() {
        try {
            Set<String> windows = driver.getWindowHandles();
            Assert.assertTrue(windows.size() > 1, "No new window opened after clicking video button");
        } catch (Exception e) {
            Assert.fail("Video window validation failed: " + e.getMessage());
        }
        sleepOneSecond();
    }

    @When("I wait for 10 seconds")
    public void i_wait_for_10_seconds() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        sleepOneSecond();
    }

    @Then("the carousel should auto-rotate")
    public void the_carousel_should_auto_rotate() {
        try {
            String slideNameAfter = driver.findElement(EquipmentCarouselPage.EquipmentCarouselLocators.equipmentName).getText();
            Assert.assertNotEquals(slideNameBefore, slideNameAfter, "Carousel did not auto-rotate");
        } catch (Exception e) {
            Assert.fail("Auto-rotation check failed: " + e.getMessage());
        }
        sleepOneSecond();
    }

    @When("I click the second indicator")
    public void i_click_the_second_indicator() {
        try {
            List<WebElement> dots = driver.findElements(EquipmentCarouselPage.EquipmentCarouselLocators.indicators);
            if (dots.size() > 1) {
                WebElement activeDot = driver.findElement(EquipmentCarouselPage.EquipmentCarouselLocators.activeIndicator);
                String classBefore = activeDot.getAttribute("class");
                dots.get(1).click();
                wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(activeDot, "class", classBefore)));
            }
        } catch (Exception e) {
            Assert.fail("Indicator click failed: " + e.getMessage());
        }
        sleepOneSecond();
    }

    @Then("the indicator should update")
    public void the_indicator_should_update() {
        try {
            WebElement activeDot = driver.findElement(EquipmentCarouselPage.EquipmentCarouselLocators.activeIndicator);
            Assert.assertTrue(activeDot.isDisplayed(), "Active indicator is not displayed");
        } catch (Exception e) {
            Assert.fail("Indicator update check failed: " + e.getMessage());
        }
        sleepOneSecond();
    }

    @Then("the 'CONTACT US TO LEARN MORE' button should be visible and clickable")
    public void the_contact_us_button_should_be_visible_and_clickable() {
        try {
            WebElement contactBtn = wait.until(ExpectedConditions.elementToBeClickable(
                EquipmentCarouselPage.EquipmentCarouselLocators.contactUsButton));
            Assert.assertTrue(contactBtn.isDisplayed(), "Contact Us button is not visible");
            Assert.assertTrue(contactBtn.isEnabled(), "Contact Us button is not clickable");
        } catch (Exception e) {
            Assert.fail("Contact Us button visibility/clickability check failed: " + e.getMessage());
        }
        sleepOneSecond();
    }

    @When("I click the 'CONTACT US TO LEARN MORE' button")
    public void i_click_the_contact_us_button() {
        try {
            WebElement contactBtn = wait.until(ExpectedConditions.elementToBeClickable(
                EquipmentCarouselPage.EquipmentCarouselLocators.contactUsButton));
            contactBtn.click();
        } catch (Exception e) {
            Assert.fail("Contact Us button click failed: " + e.getMessage());
        }
        sleepOneSecond();
    }

    @Then("I should be redirected to the contact page")
    public void i_should_be_redirected_to_the_contact_page() {
        try {
            wait.until(ExpectedConditions.urlContains("/contact"));
            String url = driver.getCurrentUrl();
            Assert.assertTrue(url.contains("/contact"), "Did not navigate to contact page");
        } catch (Exception e) {
            Assert.fail("Redirection to contact page failed: " + e.getMessage());
        }
        sleepOneSecond();
    }

    @When("I navigate back to the Equipment page")
    public void i_navigate_back_to_the_equipment_page() {
        try {
            driver.navigate().back();
            wait.until(ExpectedConditions.urlContains("/equipment"));
        } catch (Exception e) {
            Assert.fail("Navigation back to equipment page failed: " + e.getMessage());
        }
        sleepOneSecond();
    }

    @Then("I should be on the Equipment page")
    public void i_should_be_on_the_equipment_page() {
        try {
            String url = driver.getCurrentUrl();
            Assert.assertTrue(url.contains("/equipment"), "Did not navigate back to Equipment page");
        } catch (Exception e) {
            Assert.fail("Equipment page verification failed: " + e.getMessage());
        }
        sleepOneSecond();
    }

    @Then("the Service Advantage carousel should be visible")
    public void the_service_advantage_carousel_should_be_visible() {
        try {
            WebElement serviceAdvantageSection = wait.until(ExpectedConditions.visibilityOfElementLocated(
                EquipmentCarouselPage.EquipmentCarouselLocators.serviceAdvantageSection));
            Assert.assertTrue(serviceAdvantageSection.isDisplayed(), "Service Advantage carousel not visible");
        } catch (Exception e) {
            Assert.fail("Service Advantage visibility check failed: " + e.getMessage());
        }
        sleepOneSecond();
    }
}
