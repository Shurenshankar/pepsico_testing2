Feature: Equipment Carousel functionality on Equipment page

  Background:
    Given I am on the Equipment page

  Scenario: Full Equipment Carousel functionality

    # Check next arrow navigation
    When I get the current slide name and image
    And I click the next arrow
    Then the slide should change
    And the image should update

    # Check previous arrow navigation
    When I get the current slide name and image
    And I click the previous arrow
    Then the slide should change
    And the image should update

    # Check auto-rotation
    When I wait for 10 seconds
    Then the carousel should auto-rotate

    # Check 'Contact Us' button visibility
    Then the 'CONTACT US TO LEARN MORE' button should be visible and clickable

    # Check redirection on CTA click
    When I click the 'CONTACT US TO LEARN MORE' button
    Then I should be redirected to the contact page

    # Navigate back to Equipment page to validate Service Advantage section
    When I navigate back to the Equipment page
    Then I should be on the Equipment page

    # Validate Service Advantage carousel
    Then the Service Advantage carousel should be visible
