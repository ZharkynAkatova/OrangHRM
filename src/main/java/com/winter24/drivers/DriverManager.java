package com.winter24.drivers;

import com.winter24.utils.ConfigReader;
import org.openqa.selenium.WebDriver;

public class DriverManager {

    private static WebDriver driver;

    /**
     * Возвращает текущий экземпляр WebDriver. Если драйвер еще не был создан, он создается
     * в зависимости от указанного в конфигурации браузера.
     * @return экземпляр WebDriver.
     */
    public static WebDriver getDriver(){
        if (driver == null) {
            switch ((ConfigReader.getValue("browser").toLowerCase())){
                case "chrome":
                    driver = ChromeWebDriver.loadChromeDriver();
                    break;
                case "firefox":
                    driver = FireFoxWebDriver.loadFireFoxDriver();
                    break;
                case "edge":
                    driver = EdgeWebDriver.loadEdgeDriver();
                    break;
                default:
                    throw new IllegalArgumentException("Указано неверное имя драйвера");
            }
        }
        return driver;
    }

    /**
     * Закрывает и завершает работу текущего экземпляра WebDriver.
     * Если драйвер не был инициализирован, метод ничего не делает.
     */
    public static void closedriver(){
        try {
            if (driver != null) {
                driver.close();
                driver.quit();
                driver = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
