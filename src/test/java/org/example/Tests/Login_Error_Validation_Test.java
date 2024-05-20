package org.example.Tests;

import org.example.TestComponents.BaseTest;
import org.example.page_objects.Login_page;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class Login_Error_Validation_Test extends BaseTest {

    @Test
    public void login_error_validations() throws IOException {
        Login_page loginPage = launch_application();
        String username = "keerthanareddy2506@gmail.com";
        String password = "KeerthiReddy@2";
        loginPage.login_application(username, password);
        Assert.assertEquals(loginPage.getErrorMessage(),"Your password is incorrect");

    }
    @Test
    public void UI_checks(){


    }
}
