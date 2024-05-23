package org.example.TestComponents;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.page_objects.Login_page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {

    public  WebDriver driver;
    public Login_page login_page;

    @BeforeMethod
    public Login_page setup() throws IOException {
        driver = initializeDriver();
        login_page = new Login_page(driver);
        launch_application();
        return login_page;

    }
    public WebDriver initializeDriver() throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//org//example//Resources//GlobalData.properties");
        properties.load(fileInputStream);
        String browser_name = properties.getProperty("browser");

        if(browser_name.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();


        } else if (browser_name.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();

        } else if (browser_name.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();

        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(90));
        return driver;

    }
    public void launch_application() {
        login_page.go_to_application();
    }

    @AfterMethod
    public void tear_down(){
        driver.quit();
    }
}
