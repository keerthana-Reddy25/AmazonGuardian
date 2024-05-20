package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.page_objects.CartPage;
import org.example.page_objects.Login_page;
import org.example.page_objects.OrderCancellationPage;
import org.example.page_objects.ProductsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class SubmitOrderTest {
    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(90));
        driver.get("https://www.amazon.com");
        //Amazon Login open  -Login Page -1
        Login_page login_page = new Login_page(driver);
        login_page.login_application("keerthanareddy2506@gmail.com", "KeerthiReddy@25");
        //Amazon login closed


        //Products Page Start
        String[] products = {"snacks under 5 dollars", "lip balm", "french vanilla","yupik","shampoo"};
        ProductsPage products_page = new ProductsPage(driver);
        products_page.search_and_add_products_to_cart(products);

        //Products Page End

        //Cart Page Start
        String expected_username = "Keerthana chilakala";
        String expected_confirmation_text = "Order placed, thanks!";
        CartPage cartPage = new CartPage(driver);
        cartPage.submit_order(products.length,expected_username,expected_confirmation_text);

        //Cart Page End

        //Orders cancellation Page Start
        OrderCancellationPage orderCancellationPage = new OrderCancellationPage(driver);
        String expected_cancel_msg = "This order has been cancelled.";
        orderCancellationPage.cancel_orders(expected_cancel_msg);
        //Orders cancellation Page End










    }
}