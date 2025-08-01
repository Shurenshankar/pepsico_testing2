Feature: Menu bar navigation

  As a user
  I want to view and interact with all the menu items in the navigation bar
  So that I can access all sections of the site

  Scenario: Verify all menu items are visible and clickable
    Given I am on the homepage menubar
    When I check all menu items in the menu bar
    Then all menu items should be visible and clickable
