package org.example.Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.TestComponents.BaseTest;
import org.example.page_objects.CartPage;
import org.example.page_objects.Login_page;
import org.example.page_objects.OrderCancellationPage;
import org.example.page_objects.ProductsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

public class SubmitOrderTest extends BaseTest {


    @Test
            public void submit_order() throws IOException {

        //Amazon Login open  -Login Page -1
//        Login_page login_page = launch_application();
        String username = "keerthanareddy2506@gmail.com";
        String password = "KeerthiReddy@25";
        ProductsPage products_page = login_page.login_application(username, password);
        //Amazon login closed


        //Products Page Start
        String[] products = {"snacks under 5 dollars", "lip balm", "french vanilla", "yupik", "shampoo"};
        CartPage cartPage = products_page.search_and_add_products_to_cart(products);

        //Products Page End

        //Cart Page Start
        String expected_username = "Keerthana chilakala";
        String expected_confirmation_text = "Order placed, thanks!";
        OrderCancellationPage orderCancellationPage = cartPage.submit_order(products.length, expected_username, expected_confirmation_text);

        //Cart Page End

        //Orders cancellation Page Start

        String expected_cancel_msg = "This order has been cancelled.";
        orderCancellationPage.cancel_orders(expected_cancel_msg);
        //Orders cancellation Page End
    }










    }
