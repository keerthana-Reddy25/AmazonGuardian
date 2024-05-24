@tag
Feature:Error validation

  @ErrorValidation
  Scenario Outline: Negative test of user login with invalid credentials
    Given I landed on E-commerce Page
    When Logged in with username <email> and password <password>
    Then "Incorrect email or password." message is displayed

    Examples:
      |email                  |password              |
      |keerthanareddy2506@gmail.com    |KeerthiReddy@25  |