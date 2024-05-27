@tag
  Feature: Submit Order

    @OrderSubmission
    Scenario Outline: Positive test of submitting an order
      Given Logged in with userEmail <email> and password <password>
      When I want to add the products <products> to the cart
      And I want to checkout the products <products> and check username "Keerthana chilakala" , "Order placed, thanks!" message is displayed upon placing the order
      Then I want to cancel the placed order and "This order has been cancelled." message is displayed upon cancellation

      Examples:
      |email                          |password              |products|
      |keerthanareddy2506@gmail.com   |KeerthiReddy@25       |["snacks under 5 dollars", "lip balm"]        |
