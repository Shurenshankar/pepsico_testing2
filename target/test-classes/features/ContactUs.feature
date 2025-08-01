Feature: Contact Us Form on PepsiCo Partners Contact Page

  Scenario: Fill and submit the Contact Us form with valid data
    Given I am on the PepsiCo Partners Contact Us page
    When I check the Subject dropdown options
    Then the dropdown should contain the following options:
      | Select a Subject                          |
      | Become a PepsiCo Partner                  |
      | Become a Service Advantage Customer       |
    When I select "Become a PepsiCo Partner" from the Subject dropdown
    And I enter name "Ashok"
    And I enter address "123 Main St, Chennai"
    And I enter phone "9876540210"
    And I enter email "ashok76@example.com"
    And I enter comments "This is a test comment."
    And I click the Submit button
    Then I should see a confirmation message or successful submission
