package org.example.Tests;

import org.example.TestComponents.BaseTest;
import org.example.page_objects.Login_page;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class Login_Error_Validation_Test extends BaseTest {


    @Test
    public void login_error_validations() throws IOException {
        String username = "keerthanareddy2506@gmail.com";
        String password = "KeerthiReddy@2";
        login_page.login_application(username, password);
        Assert.assertEquals(login_page.getErrorMessage(),"Your password is incorrect");

    }
    @Test
    public void UI_checks() throws IOException {

        //check whether the text "Create your Amazon account" visible on GUI
        String expected_create_amazon_account_btn_text = login_page.get_create_amazon_account_button_text();
        String actual_create_amazon_account_btn_text = "Create your Amazon account";
        Assert.assertEquals(expected_create_amazon_account_btn_text,actual_create_amazon_account_btn_text);
    }

}
