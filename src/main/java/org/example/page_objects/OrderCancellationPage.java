package org.example.page_objects;

import org.apache.hc.core5.util.Asserts;
import org.example.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class OrderCancellationPage extends AbstractComponent {
    WebDriver driver;
    public OrderCancellationPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "nav-orders")
    WebElement orders_link_element;
    @FindBy(linkText = "View or edit order")
    WebElement view_or_edit_button_element;
    @FindBy(linkText = "Cancel items")
    WebElement cancel_item_button_element;
    @FindBy(xpath = "//input[contains(@name, 'cq.cancelItem')]")
    List<WebElement> checkboxes;
    @FindBy(name = "cancel.reason")
    WebElement static_drop_down;
    @FindBy(name = "cq.submit")
    WebElement cancel_order_button_element;
    @FindBy(className = "a-alert-heading")
    WebElement cancel_msg_element;

    public void cancel_orders(String expected_cancel_msg){
        orders_link_element.click();
        view_or_edit_button_element.click();
        cancel_item_button_element.click();
        for(WebElement checkbox : checkboxes){
            if(!checkbox.isSelected()){
                checkbox.click();
            }

        }

        Select dropdown = new Select(static_drop_down);
        dropdown.selectByIndex(3);
        cancel_order_button_element.click();

        String actual_cancel_msg = cancel_msg_element.getText();
        Asserts.check(true,actual_cancel_msg,expected_cancel_msg);
    }




}
