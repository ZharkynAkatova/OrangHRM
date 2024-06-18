package com.winter24.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.time.Duration;

import static com.winter24.utils.ConfigReader.getValue;

public class EdgeWebDriver {

    /**
     * Загружает и настраивает экземпляр EdgeDriver.
     *
     * Настройка включает следующие параметры:
     * - "--disable-extensions" для отключения расширений.
     * - "--window-size=1920,1080" для установки размера окна.
     * - "--headless" для безголового режима (если параметр "headless" установлен в true).
     *
     * Возвращает настроенный экземпляр WebDriver.
     *
     * @return Экземпляр WebDriver с настроенным EdgeDriver.
     */
    public static WebDriver loadEdgeDriver (){
        WebDriverManager.edgedriver().setup();

        EdgeOptions options = new EdgeOptions();
        options.addArguments("--disable-extensions");
        options.addArguments("--window-size-1920,1080");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);

        if(Boolean.parseBoolean(getValue("headless"))) {
            options.addArguments("--headless");
        }
        WebDriver driver = new EdgeDriver(options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();
        return driver;
    }
}