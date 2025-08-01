@ui @homepage @fullCheck
Feature: PepsiCo Partners Homepage checks

  Background:
    Given I open the PepsiCo Partners homepage

  Scenario: Full homepage validation with redirect checks
    Then the search input is visible
    When I type "Pepsi" into the search input
    Then search suggestions are displayed
    Then the page ready state should be "complete"
    Then the current URL contains "pepsicopartners.com"
    Then the "Become a Customer" button is clickable
    Then the PepsiCo logo is clickable
    Then the Login/Register link is clickable
    
