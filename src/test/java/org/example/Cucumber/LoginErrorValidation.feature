@tag
Feature:Error validation

  @ErrorValidation
  Scenario Outline: Negative test of user login with invalid Password
    Given I landed on Amazon Login Page
    When Logged in with username <email> and password <password>
    Then "Your password is incorrect" message is displayed

    Examples:
      |email                  |password              |
      |keerthanareddy2506@gmail.com    |KeerthiReddy@125  |