package org.example.stepDefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.TestComponents.BaseTest;
import org.example.page_objects.Login_page;
import org.testng.Assert;

import java.io.IOException;

public class LoginErrorValidationStepDefinitions extends BaseTest {
    public Login_page loginPage;

    @Given("I landed on Amazon Login Page")
    public void i_landed_on_Amazon_Login_Page() throws IOException {
        loginPage = setup();
    }
    @When("^Logged in with username (.+) and password (.+)$")
    public void logged_in_with_username_and_password(String email, String password) throws Throwable {
        loginPage.login_application(email, password);

    }
    @Then("{string} message is displayed")
    public void login_error_validation_message_is_displayed(String errorMessage){
        Assert.assertEquals(loginPage.getErrorMessage(),errorMessage);
        tear_down();
    }
}
