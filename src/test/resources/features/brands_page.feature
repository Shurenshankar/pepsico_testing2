Feature: PepsiCo Brands Page Tests

  Background:
    Given I navigate to the Starbucks brands page

  Scenario: Verify full Starbucks brands page functionality

    Then the Starbucks banner should be visible

    When I click the 'View All Products' CTA
    Then the URL should contain "showProductListing=true"

    When I navigate to the Starbucks brands page with parameters
    And I click on the first sub-brand tile
    Then the URL should contain "FRAPPUCCINO"

    When I click on the 'Learn More' link in the Tips section
    Then the URL should change and not contain "brand_starbucks"

    Then all images on the brands page should be loaded

    Then there should be at least one CTA present
    And the page title should not contain "not found" or "404"

    Then the footer links should be visible
