@End2End
Feature: Update User

  Scenario: Update User Scenario
    Given set the url for update
    And set the expected data for update
    When send the patch request for update
    Then do assertion for update