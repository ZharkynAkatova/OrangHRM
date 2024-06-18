package com.winter24.helper;

import com.winter24.drivers.DriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Класс AlertHelper содержит методы для работы с всплывающими окнами (Alert) в браузере.
 */
public class AlertHelper {

    private WebDriver driver = DriverManager.getDriver();

    /**
     * Конструктор класса AlertHelper.
     *
     * @param driver Веб-драйвер, используемый для взаимодействия с браузером.
     */
    public AlertHelper(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Метод для получения объекта Alert, после ожидания его появления.
     *
     * @return Объект Alert.
     */
    public Alert getAlert() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.alertIsPresent());
        return driver.switchTo().alert();
    }

    /**
     * Метод для принятия (accept) всплывающего окна Alert.
     */
    public void acceptAlert() {
        getAlert().accept();
    }

    /**
     * Метод для отклонения (dismiss) всплывающего окна Alert.
     */
    public void dismissAlert() {
        getAlert().dismiss();
    }

    /**
     * Метод для проверки наличия всплывающего окна Alert.
     *
     * @return true, если Alert присутствует, иначе false.
     */
    public boolean isAlertPresent() {
        try {
            driver.switchTo().alert().accept();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    /**
     * Метод для принятия (accept) всплывающего окна Alert, если оно присутствует.
     */
    public void acceptAlertIfPresented() {
        if (!isAlertPresent())
            return;
        acceptAlert();
    }

    /**
     * Метод для отклонения (dismiss) всплывающего окна Alert, если оно присутствует.
     */
    public void dismissAlertIfPresent() {
        if (!isAlertPresent())
            return;
        dismissAlert();
    }

    /**
     * Метод для ввода текста в всплывающее окно Prompt и его принятия (accept).
     *
     * @param txt Текст для ввода в Prompt.
     */
    public void acceptPromt(String txt) {
        if (!isAlertPresent())
            return;
        Alert alert = getAlert();
        alert.sendKeys(txt);
        alert.accept();
    }

    /**
     * Метод для ввода текста в всплывающее окно Prompt и его отклонения (dismiss).
     *
     * @param txt Текст для ввода в Prompt.
     */
    public void dismissPromt(String txt) {
        if (!isAlertPresent())
            return;
        Alert alert = getAlert();
        alert.sendKeys(txt);
        alert.dismiss();
    }
}