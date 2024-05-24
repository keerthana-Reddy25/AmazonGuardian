package org.example.page_objects;

import org.apache.hc.core5.util.Asserts;
import org.example.AbstractComponents.AbstractComponent;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends AbstractComponent {
    WebDriver driver;
    public CartPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(id = "nav-cart-count-container")
    WebElement cart_icon;
    By cart_icon_wait = By.id("nav-cart-count-container");
    @FindBy(id = "sc-subtotal-label-buybox")
    WebElement cart_count_text;
    @FindBy(xpath = "//input[@name='proceedToRetailCheckout']")
    WebElement proceed_to_checkout_button;
    @FindBy(xpath = "//span[@class='a-button-inner']")
    WebElement checkout_button;
    @FindBy(xpath = "//li[@class='displayAddressLI displayAddressFullName']")
    WebElement username_element;
    @FindBy(xpath = "//input[@aria-labelledby='submitOrderButtonId-announce']")
    WebElement submit_button;
    @FindBy(className = "a-alert-heading")
    WebElement confirmation_text_element;

    public OrderCancellationPage submit_order(int products_count, String expected_username, String expected_confirmation_text){

        AbstractComponent.element_tobe_clickable(cart_icon_wait);

        cart_icon.click();
        String cart_count = cart_count_text.getText();
        if(cart_count.contains(String.valueOf(products_count))){
                proceed_to_checkout_button.click();
        }
        try{
            checkout_button.click();
        }
        catch(StaleElementReferenceException e){
           // driver.navigate().refresh();
            wait_for_element_to_be_appear(checkout_button);
            checkout_button.click();
        }

        //username check

        String actual_username = username_element.getText();
        if(actual_username.equals(expected_username)){
            submit_button.click();
        }
        //confirmation message check
        String actual_confirmation_text = confirmation_text_element.getText();
        Asserts.check(true,actual_confirmation_text,expected_confirmation_text);
        OrderCancellationPage orderCancellationPage = new OrderCancellationPage(driver);
        return orderCancellationPage;
    }


}
