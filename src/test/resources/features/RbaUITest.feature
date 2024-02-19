@RbaUITest
Feature: Payments and Infrastructure
  Scenario: Payment and Infrastructure page navigation
    Given user goes to RBA website
    And click link Payments and Infrastructure
    Then user should see Payments and Infrastructure page
    And click link Payments System from left menu
    Then user should see Payments System page
    And click link Non-cash Payments from On This page list at the right side of the page
    Then user should be taken to Non-cash Payments section