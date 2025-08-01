Feature: Lead generation submission

  Scenario: Submit with valid details
    Given I open the lead generation page
    When I enter contact name "E23423mily Stone"
    And I enter business name "The Urban Fork"
    And I enter business address "321 Culinary Blvd"
    And I enter country "USA"
    And I enter city "New York"
    And I enter state "NY"
    And I enter zip "10001"
    And I enter the phone "2125957890"
    And I enter the email "es23mily.stone@urbanfork.com"
    And I enter the comments "This is a product"
    And I select product interest "Pepsi Beverages"
    And I submit the form
