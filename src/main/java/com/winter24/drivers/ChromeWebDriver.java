package com.winter24.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

import static com.winter24.utils.ConfigReader.getValue;

public class ChromeWebDriver {

    public static WebDriver loadChromeDriver() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--disable-gpu", "--remote-allow-origins=*", "--window-size=1920,1080");
//        options.addArguments("--no-sandbox");
//        options.addArguments("--disable-dev-shm-usage");
//        options.addArguments("--disable-gpu");
//        options.addArguments("--remote-allow-origins=*");
//        options.addArguments("--window-size=1920,1080");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);

        if (Boolean.parseBoolean(getValue("headless"))) {
            options.addArguments("--headless");
        }
        WebDriver driver = new ChromeDriver(options);
//                WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        return driver;

    }
}
