@cart
Feature: Cart

  Background:
    Given the user is logged in

#######################################################################
  Scenario: Add to cart
    When Ezekiel adds an item to his cart
    And he navigates to the Cart page
    Then he should see the item in his cart
#######################################################################