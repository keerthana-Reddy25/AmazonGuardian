package org.example.AbstractComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponent {

        static WebDriver driver;
        public AbstractComponent(WebDriver driver) {
            AbstractComponent.driver = driver;
    }

    public static void element_tobe_clickable(By find_by_element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.elementToBeClickable(find_by_element));
    }

    public static void wait_for_element_to_be_appear(WebElement webelement){
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90) );
            wait.until(ExpectedConditions.visibilityOf(webelement));
    }
    public static void javascript_click(WebElement webelement){
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", webelement);
    }

    public static void scroll_to_the_element(WebElement webelement){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", webelement);

    }
}
