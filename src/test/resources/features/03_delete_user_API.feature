@End2End
Feature: Delete User

  Scenario: Delete User Scenario
    Given set the url for delete
    When send the delete request for delete
    Then do assertion for delete