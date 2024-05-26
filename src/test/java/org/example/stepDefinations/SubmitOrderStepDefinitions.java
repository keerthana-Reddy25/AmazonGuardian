package org.example.stepDefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.TestComponents.BaseTest;
import org.example.page_objects.CartPage;
import org.example.page_objects.Login_page;
import org.example.page_objects.OrderCancellationPage;
import org.example.page_objects.ProductsPage;

public class SubmitOrderStepDefinitions extends BaseTest {

    public Login_page loginPage;
    public ProductsPage productsPage;
    public CartPage cartPage;
    public OrderCancellationPage orderCancellationPage;

    @Given("^Logged in with userEmail (.+) and password (.+)$")
    public void logged_in_with_user_and_password(String email, String password) throws Throwable {
        loginPage = setup();
        productsPage = loginPage.login_application(email, password);
    }

    @When("^I want to add the products \\[(.+)\\] to the cart$")
    public void i_want_to_add_the_products_to_the_cart(String products) throws Throwable {
        String[] productArray = products.split(",\\s*");
        cartPage = productsPage.search_and_add_products_to_cart(productArray);
    }

    @When("^I want to checkout the products \\[(.+)\\] and check username \"([^\"]*)\" , \"([^\"]*)\" message is displayed upon placing the order$")
    public void checkout_and_submit_the_order(String products, String username, String message) throws Throwable {
        String[] productArray = products.split(",\\s*");
        orderCancellationPage = cartPage.submit_order(productArray.length, username, message);
    }

    @Then("^I want to cancel the placed order and \"([^\"]*)\" message is displayed upon cancellation$")
    public void cancel_the_order_and_message_is_displayed(String message) throws Throwable {
        orderCancellationPage.cancel_orders(message);
        tear_down();
    }
}
