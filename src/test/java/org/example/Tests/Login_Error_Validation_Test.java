package org.example.Tests;

import org.example.TestComponents.BaseTest;
import org.example.TestComponents.Retry;
import org.example.page_objects.Login_page;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class Login_Error_Validation_Test extends BaseTest {


    @Test(dataProvider = "getData")
    public void login_error_validations(HashMap<String,String> dataset) throws IOException {
        String username = dataset.get("username");
        String password = dataset.get("password");
        String expected_error_msg = dataset.get("expectedErrorMessage");
        login_page.login_application(username, password);
        Assert.assertEquals(login_page.getErrorMessage(),"Your password is incorrect");

    }
    @Test(dataProvider = "getData",retryAnalyzer = Retry.class)
    public void UI_checks(HashMap<String,String> dataset) throws IOException {

        //check whether the text "Create your Amazon account" visible on GUI
        String expected_create_amazon_account_btn_text = login_page.get_create_amazon_account_button_text();
        String actual_create_amazon_account_btn_text = dataset.get("expectedAmazonCreateAccountBtnText");
        Assert.assertEquals(expected_create_amazon_account_btn_text,actual_create_amazon_account_btn_text);
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String, Object>> datasets = getJsonDataToMap(System.getProperty("user.dir") + "//src//test//java//org//example//Data//login_error_validation_test_data.json");
        return datasets.stream().map(data -> new Object[]{data}).toArray(Object[][]::new);

    }

}
