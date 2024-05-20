package org.example.AbstractComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
}
