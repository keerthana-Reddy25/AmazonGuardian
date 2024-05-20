package org.example.page_objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductsPage {
    WebDriver driver;
    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "twotabsearchtextbox")
    WebElement searchbox;
    @FindBy(id = "nav-search-submit-button")
    WebElement search_button;
    @FindBy(xpath = "//div[@class='a-section aok-relative s-image-square-aspect']")
    List<WebElement> search_results_products;
    @FindBy(id ="add-to-cart-button")
    WebElement add_to_cart_button;

    public CartPage search_and_add_products_to_cart(String[] products){
        for(String product : products){
            searchbox.sendKeys(product);
            search_button.click();
            if(!search_results_products.isEmpty()){
                WebElement first_product = search_results_products.get(0);
                first_product.click();
            }
            add_to_cart_button.click();
        }
        CartPage cartPage = new CartPage(driver);
        return cartPage;
    }

}
