package stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.ContactUsPage;
import pages.HomePage;
import utilities.DriverFactory;

import java.util.List;

public class ContactUsSteps extends DriverFactory {

    private WebDriver driver = DriverFactory.getDriver();
    private ContactUsPage contactUs;
    private HomePage homePage;

    @Given("I am on the PepsiCo Partners Contact Us page")
    public void i_am_on_the_pepsico_partners_contact_us_page() {
        try {
            homePage = new HomePage(driver);
            homePage.openContactUsDirectly();
            contactUs = new ContactUsPage(driver);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Failed to navigate to Contact Us page");
        }
        sleepOneSecond();
    }

    @When("I check the Subject dropdown options")
    public void i_check_the_subject_dropdown_options() {
        try {
            // Intentionally left blank (handled in next step)
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Error during checking Subject dropdown");
        }
        sleepOneSecond();
    }

    @Then("the dropdown should contain the following options:")
    public void the_dropdown_should_contain_the_following_options(io.cucumber.datatable.DataTable dataTable) {
        try {
            List<String> expectedOptions = dataTable.asList();
            List<String> actualOptions = contactUs.getSubjectOptions();
            Assert.assertEquals(actualOptions, expectedOptions, "Subject dropdown options do not match");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Dropdown validation failed");
        }
        sleepOneSecond();
    }

    @When("I select {string} from the Subject dropdown")
    public void i_select_from_the_subject_dropdown(String subject) {
        try {
            contactUs.selectSubject(subject);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Failed to select subject: " + subject);
        }
        sleepOneSecond();
    }

    @When("I enter name {string}")
    public void i_enter_name(String name) {
        try {
            contactUs.enterName(name);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Failed to enter name");
        }
        sleepOneSecond();
    }

    @When("I enter address {string}")
    public void i_enter_address(String address) {
        try {
            contactUs.enterAddress(address);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Failed to enter address");
        }
        sleepOneSecond();
    }

    @When("I enter phone {string}")
    public void i_enter_phone(String phone) {
        try {
            contactUs.enterPhone(phone);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Failed to enter phone");
        }
        sleepOneSecond();
    }

    @When("I enter email {string}")
    public void i_enter_email(String email) {
        try {
            contactUs.enterEmail(email);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Failed to enter email");
        }
        sleepOneSecond();
    }

    @When("I enter comments {string}")
    public void i_enter_comments(String comments) {
        try {
            contactUs.enterComments(comments);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Failed to enter comments");
        }
        sleepOneSecond();
    }

    @When("I click the Submit button")
    public void i_click_the_submit_button() {
        try {
            contactUs.clickSubmit();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Submit button click failed");
        }
        sleepOneSecond();
    }

    @Then("I should see a confirmation message or successful submission")
    public void i_should_see_confirmation_message() {
        try {
            Assert.assertFalse(contactUs.isValidationErrorShown(), "Validation errors are shown after submission");
            System.out.println("Form submitted successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Submission confirmation check failed");
        }
        sleepOneSecond();
    }

    private void sleepOneSecond() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interrupt status
        }
    }
}
