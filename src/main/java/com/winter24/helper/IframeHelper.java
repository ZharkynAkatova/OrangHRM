package com.winter24.helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Класс IframeHelper содержит методы для работы с фреймами (iframes).
 */
public class IframeHelper {

    private static WebDriver driver;

    /**
     * Конструктор класса IframeHelper.
     *
     * @param driver Веб-драйвер, используемый для взаимодействия с браузером.
     */
    public IframeHelper(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Метод для переключения на родительский фрейм.
     */
    public void switchToParentFrame() {
        driver.switchTo().parentFrame();
    }

    /**
     * Метод для переключения на фрейм по его имени или ID.
     *
     * @param nameOrID Имя или ID фрейма, к которому необходимо переключиться.
     */
    public void switchToFrame(String nameOrID) {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(nameOrID));
    }
}