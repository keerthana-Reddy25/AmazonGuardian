package org.example.TestComponents;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.example.page_objects.Login_page;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {

    public  WebDriver driver;
    public Login_page login_page;

    @BeforeMethod(alwaysRun = true)
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

    @AfterMethod(alwaysRun = true)
    public void tear_down(){
        driver.quit();
    }
    public List<HashMap<String, Object>> getJsonDataToMap(String filePath) throws IOException {
        String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, Object>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, Object>>>() {
        });
        return data;
    }


        public String getScreenshot(String testCaseName,WebDriver driver) throws IOException{
        String path = System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
        TakesScreenshot screenshot = (TakesScreenshot)driver;
        File sourceFile  = screenshot.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(path);
        FileUtils.copyFile(sourceFile,destinationFile);
        return path;


    }
}
