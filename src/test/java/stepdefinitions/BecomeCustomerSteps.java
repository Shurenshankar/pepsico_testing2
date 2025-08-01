package stepdefinitions;

import io.cucumber.java.en.*;
import io.cucumber.datatable.DataTable;
import org.testng.Assert;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import pages.BecomeCustomerPage;
import utilities.DriverFactory;

public class BecomeCustomerSteps {
    private WebDriver driver;
    private BecomeCustomerPage page;

    public BecomeCustomerSteps() {
        this.driver = DriverFactory.getDriver();
        this.page = new BecomeCustomerPage(driver);
    }

    @Given("I open the lead generation page")
    public void i_open_the_lead_generation_page() {
        try {
            driver.get("https://www.pepsicopartners.com/pepsico/en/USD/partner/leadgeneration");
            page.waitForFormToLoad();
        } catch (Exception e) {
            Assert.fail("Failed to open lead generation page: " + e.getMessage());
        }
        sleep();
    }

    @When("I select business type {string}")
    public void i_select_business_type(String type) {
        try {
            page.selectBusinessType(type);
        } catch (Exception e) {
            Assert.fail("Failed to select business type: " + e.getMessage());
        }
        sleep();
    }

    @When("I enter the following lead details")
    public void i_enter_the_following_lead_details(DataTable dataTable) {
        try {
            Map<String, String> data = dataTable.asMap(String.class, String.class);
            if (data.containsKey("contactName")) page.enterContactName(data.get("contactName"));
            if (data.containsKey("businessName")) page.enterBusinessName(data.get("businessName"));
            if (data.containsKey("businessAdd")) page.enterBusinessAddress(data.get("businessAdd"));
            if (data.containsKey("country")) page.enterCountry(data.get("country"));
            if (data.containsKey("city")) page.enterCity(data.get("city"));
            if (data.containsKey("state")) page.enterState(data.get("state"));
            if (data.containsKey("zip")) page.enterZip(data.get("zip"));
            if (data.containsKey("phone")) page.enterPhone(data.get("phone"));
            if (data.containsKey("email")) page.enterEmail(data.get("email"));
        } catch (Exception e) {
            Assert.fail("Failed to enter lead details: " + e.getMessage());
        }
        sleep();
    }

    @When("I enter contact name {string}")
    public void i_enter_contact_name(String name) {
        try {
            page.enterContactName(name);
        } catch (Exception e) {
            Assert.fail("Failed to enter contact name: " + e.getMessage());
        }
        sleep();
    }

    @When("I enter business name {string}")
    public void i_enter_business_name(String bName) {
        try {
            page.enterBusinessName(bName);
        } catch (Exception e) {
            Assert.fail("Failed to enter business name: " + e.getMessage());
        }
        sleep();
    }

    @When("I enter business address {string}")
    public void i_enter_business_address(String bAddress) {
        try {
            page.enterBusinessAddress(bAddress);
        } catch (Exception e) {
            Assert.fail("Failed to enter business address: " + e.getMessage());
        }
        sleep();
    }

    @When("I enter country {string}")
    public void i_enter_country(String country) {
        try {
            page.enterCountry(country);
        } catch (Exception e) {
            Assert.fail("Failed to enter country: " + e.getMessage());
        }
        sleep();
    }

    @When("I enter city {string}")
    public void i_enter_city(String city) {
        try {
            page.enterCity(city);
        } catch (Exception e) {
            Assert.fail("Failed to enter city: " + e.getMessage());
        }
        sleep();
    }

    @When("I enter state {string}")
    public void i_enter_state(String state) {
        try {
            page.enterState(state);
        } catch (Exception e) {
            Assert.fail("Failed to enter state: " + e.getMessage());
        }
        sleep();
    }

    @When("I enter zip {string}")
    public void i_enter_zip(String zip) {
        try {
            page.enterZip(zip);
        } catch (Exception e) {
            Assert.fail("Failed to enter zip: " + e.getMessage());
        }
        sleep();
    }

    @When("I enter the phone {string}")
    public void i_enter_phone(String phone) {
        try {
            page.enterPhone(phone);
        } catch (Exception e) {
            Assert.fail("Failed to enter phone: " + e.getMessage());
        }
        sleep();
    }

    @When("I enter the email {string}")
    public void i_enter_email(String email) {
        try {
            page.enterEmail(email);
        } catch (Exception e) {
            Assert.fail("Failed to enter email: " + e.getMessage());
        }
        sleep();
    }

    @When("I enter the comments {string}")
    public void i_enter_comments(String comments) {
        try {
            page.enterComments(comments);
        } catch (Exception e) {
            Assert.fail("Failed to enter comments: " + e.getMessage());
        }
        sleep();
    }

    @When("I select product interest {string}")
    public void i_select_product_interest(String interest) {
        try {
            selectLOB(interest);
        } catch (Exception e) {
            Assert.fail("Failed to select product interest: " + e.getMessage());
        }
        sleep();
    }

    @When("I select {string} as a line of business")
    public void i_select_line_of_business(String lob) {
        try {
            selectLOB(lob);
        } catch (Exception e) {
            Assert.fail("Failed to select line of business: " + e.getMessage());
        }
        sleep();
    }

    private void selectLOB(String lob) {
        String key = lob.trim().toLowerCase();
        switch (key) {
            case "pepsi beverages":
                page.selectPepsiBeverages(); break;
            case "frito lay":
            case "frito-lay":
                page.selectFritoLay(); break;
            case "tropicana":
                page.selectTropicana(); break;
            case "quaker":
                page.selectQuaker(); break;
            case "gatorade":
                page.selectGatorade(); break;
            default:
                throw new IllegalArgumentException("Unsupported product interest/LOB: " + lob);
        }
    }

    @When("I submit the form")
    public void i_submit_the_form() {
        try {
            page.clickSubmit();
        } catch (Exception e) {
            Assert.fail("Failed to submit form: " + e.getMessage());
        }
        sleep();
    }

    @Then("the form should be submitted successfully")
    public void the_form_should_be_submitted_successfully() {
        try {
            Assert.assertFalse(page.hasValidationErrors(), "Form should submit successfully.");
        } catch (Exception e) {
            Assert.fail("Form submission verification failed: " + e.getMessage());
        }
        sleep();
    }

    private void sleep() {
        try {
            Thread.sleep(1000); // adjust delay as needed
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
