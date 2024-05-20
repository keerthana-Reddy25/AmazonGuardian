package org.example.Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.page_objects.Login_page;
import org.example.page_objects.ProductsPage;
import org.openqa.selenium.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class StandAloneTest {
    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(90));
        driver.get("https://www.amazon.com");
        //Amazon Login open  -Login Page -1
        Login_page login_page = new Login_page(driver);


        driver.findElement(By.id("nav-link-accountList-nav-line-1")).click();
        driver.findElement(By.id("ap_email")).sendKeys("keerthanareddy2506@gmail.com");
        driver.findElement(By.id("continue")).click();
        driver.findElement(By.id("ap_password")).sendKeys("KeerthiReddy@25");
        driver.findElement(By.id("signInSubmit")).click();
        //Amazon login closed


        //searching products and add them to cart begin -Products Page -2

        String[] products = {"snacks under 5 dollars", "lip balm", "french vanilla","yupik","shampoo"};

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.search_and_add_products_to_cart(products);

        //searching products and add them to cart end


        //cart page start   -Cart Page -3
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(60));
        WebElement cartIcon = wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-cart-count-container")));
        cartIcon.click();
        String text = driver.findElement(By.id("sc-subtotal-label-buybox")).getText();
        if(text.contains(String.valueOf(products.length))){
            driver.findElement(By.xpath("//input[@name='proceedToRetailCheckout']")).click();

        }
        driver.findElement(By.xpath("//span[@class='a-button-inner']")).click();

        //username check
        String actual_username = driver.findElement(By.xpath("//li[@class='displayAddressLI displayAddressFullName']")).getText();
        String expected_username = "Keerthana chilakala";
        Assert.assertEquals(actual_username,expected_username);

        driver.findElement(By.xpath("//input[@aria-labelledby='submitOrderButtonId-announce']")).click();

        //cart page end

        //Order placed Confirmation Start  -OrdersPage -4

        String actual_confirmation_text = driver.findElement(By.className("a-alert-heading")).getText();
        String expected_confirmation_text = "Order placed, thanks!";
        Assert.assertEquals(actual_confirmation_text,expected_confirmation_text);
        //Order placed Confirmation End

        //order cancellation Start -OrdersCancelPage -5

        driver.findElement(By.id("nav-orders")).click();

        driver.findElement(By.linkText("View or edit order")).click();

        driver.findElement(By.linkText("Cancel items")).click();

        //checkbox is checked or not
        List<WebElement> checkboxes = driver.findElements(By.xpath("//input[contains(@name, 'cq.cancelItem')]"));

        for(WebElement checkbox : checkboxes){
            if(!checkbox.isSelected()){
                checkbox.click();
            }

        }
        //checkbox end

        WebElement static_drop_down = driver.findElement(By.name("cancel.reason"));

        Select dropdown = new Select(static_drop_down);
        dropdown.selectByIndex(3);
        driver.findElement(By.name("cq.submit")).click();

        //cancellation confirmation message
        String actual_cancel_msg = driver.findElement(By.className("a-alert-heading")).getText();
        String expected_cancel_msg = "This order has been cancelled.";
        Assert.assertEquals(actual_cancel_msg,expected_cancel_msg);

        //order cancellation End





    }
}