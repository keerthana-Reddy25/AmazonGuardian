package org.example.page_objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.example.AbstractComponents.AbstractComponent.wait_for_element_to_be_appear;

public class Login_page {
    WebDriver driver;
    public Login_page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }



    @FindBy(id = "nav-link-accountList-nav-line-1")
    WebElement account_signIn_link;
    @FindBy(id = "ap_email")
    WebElement user_signIn_email;
    @FindBy(id = "continue")
    WebElement continue_signIn;
    @FindBy(id = "ap_password")
    WebElement password_signIn;
    @FindBy(id = "signInSubmit")
    WebElement signIn_button;

    @FindBy(className = "a-list-item")
    WebElement error_message_element;


    public ProductsPage login_application(String user_email, String password){
        account_signIn_link.click();
        user_signIn_email.sendKeys(user_email);
        continue_signIn.click();
        password_signIn.sendKeys(password);
        signIn_button.click();
        ProductsPage productsPage = new ProductsPage(driver);
        return productsPage;
    }
    public void go_to_application(){
        driver.get("https://www.amazon.com");
    }

    public String getErrorMessage(){
        wait_for_element_to_be_appear(error_message_element);
        String error_msg = error_message_element.getText();
        return error_msg;
    }






}
