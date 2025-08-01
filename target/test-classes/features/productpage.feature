@plp @beverages @ui @smoke @visibility @click @usability @scroll @state @hover @title @listing
Feature: Beverages Product Listing Page (PLP)
  As a shopper on PepsiCo Partners
  I want the Beverages PLP to render correctly
  So that I can view and interact with products

  Background:
    Given I am on the Beverages product listing page

  Scenario: Validate Beverages PLP UI and interactions
    Then the first product tile is visible
    And the first product tile is clickable
    And I can scroll to the first product tile
    And the product container is enabled
    And hovering over the first product tile works
    And the page title contains Beverages
    And at least one product is listed
    And I can scroll to the bottom of the page
