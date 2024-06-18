package com.winter24.helper;

import com.winter24.drivers.DriverManager;
import com.winter24.entities.Employee;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Класс WebElementActions содержит методы для выполнения различных действий с веб-элементами.
 */
public class WebElementActions {

    Actions actions = new Actions(DriverManager.getDriver());

    /**
     * Клик по элементу.
     *
     * @param element Веб-элемент для клика.
     * @return Текущий объект WebElementActions для цепочки вызовов.
     */
    @Step("Performing click {1}")
    public WebElementActions click(WebElement element) {
        waitElementToBeVisible(element);
        waitElementToBeClickable(element);
//        scrollToElement(element);
        element.click();
        return this;
    }

    /**
     * Ввод текста в элемент.
     *
     * @param element Веб-элемент для ввода текста.
     * @param txt Текст для ввода.
     * @return Текущий объект WebElementActions для цепочки вызовов.
     */
    public WebElementActions sendKeys(WebElement element, String txt) {
        waitElementToBeVisible(element);
    //    scrollToElement(element);
        highlightElement(element);
        element.sendKeys(txt);
        return this;
    }

    /**
     * Ввод текста в элемент и нажатие Enter.
     *
     * @param element Веб-элемент для ввода текста.
     * @param txt Текст для ввода.
     * @return Текущий объект WebElementActions для цепочки вызовов.
     */
    public WebElementActions sendKeysWithEnter(WebElement element, String txt) {
        waitElementToBeVisible(element);
        scrollToElement(element);
        element.sendKeys(txt);
        element.sendKeys(Keys.ENTER);
        return this;
    }

    /**
     * Очищает существующий текст в указанном WebElement, вводит новый текст, выполняет нажатие стрелки вниз и затем клавишу Enter.
     * Ожидает видимости элемента перед выполнением действий.
     *
     * @param element WebElement для взаимодействия.
     * @param txt     Текстовая строка для ввода в WebElement.
     * @return Текущий экземпляр WebElementActions для цепочки методов.
     * @throws InterruptedException Если поток прерывается во время ожидания.
     */
    public WebElementActions clearAndsendKeysWithARROW_DOWNandEnter(WebElement element, String txt) throws InterruptedException {
        waitElementToBeVisible(element); // Ожидает, пока элемент не станет видимым, перед взаимодействием с ним
        element.sendKeys(Keys.CONTROL + "a"); // Выбирает весь текст в поле ввода
        element.sendKeys(Keys.DELETE);
        element.sendKeys(txt); // Вводит новый текст в поле ввода
        pause(2); // Приостанавливает выполнение на 2 секунды (для демонстрации; рекомендуется использовать явные ожидания)
        element.sendKeys(Keys.ARROW_DOWN, Keys.ENTER); // Отправляет клавишу стрелки вниз, затем клавишу Enter
        return this; // Возвращает текущий экземпляр WebElementActions для цепочки методов
    }

    /**
     * Очистка поля и ввод нового текста.
     *
     * @param element Веб-элемент для ввода текста.
     * @param txt Новый текст для ввода.
     * @return Текущий объект WebElementActions для цепочки вызовов.
     */
    public WebElementActions clearAndSendKeys(WebElement element, String txt) {
        waitElementToBeVisible(element);
//        scrollToElement(element);
        element.clear();
        element.sendKeys(txt);
        return this;
    }

    /**
     * Ожидание, пока элемент станет кликабельным.
     *
     * @param element Веб-элемент для ожидания.
     * @return Текущий объект WebElementActions для цепочки вызовов.
     */
    public WebElementActions waitElementToBeClickable(WebElement element) {
        new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(element));
        return this;
    }

    /**
     * Ожидание, пока элемент станет видимым.
     *
     * @param element Веб-элемент для ожидания.
     * @return Текущий объект WebElementActions для цепочки вызовов.
     */
    public WebElementActions waitElementToBeVisible(WebElement element) {
        new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOf(element));
        return this;
    }

    /**
     * Скроллинг к элементу.
     *
     * @param element Веб-элемент для скроллинга.
     * @return Текущий объект WebElementActions для цепочки вызовов.
     */
    public WebElementActions scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        return this;
    }

    /**
     * Клик по элементу с использованием JavaScript.
     *
     * @param element Веб-элемент для клика.
     * @return Текущий объект WebElementActions для цепочки вызовов.
     */
    public WebElementActions jsClick(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].click();", element);
        return this;
    }

    /**
     * Подсветка элемента.
     *
     * @param element Веб-элемент для подсветки.
     * @return Текущий объект WebElementActions для цепочки вызовов.
     */
    public WebElementActions highlightElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].style.border='2px solid red';" +
                "arguments[0].style.backgroundColor='peachpuff';", element);
        return this;
    }

    /**
     * Ввод текста в элемент с использованием JavaScript.
     *
     * @param element Веб-элемент для ввода текста.
     * @param txt Текст для ввода.
     * @return Текущий объект WebElementActions для цепочки вызовов.
     */
    public WebElementActions jsSendKeys(WebElement element, String txt) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].value=arguments[1];", element, txt);
        return this;
    }

    /**
     * Двойной клик по элементу.
     *
     * @param element Веб-элемент для двойного клика.
     * @return Текущий объект WebElementActions для цепочки вызовов.
     */
    public WebElementActions doubleClick(WebElement element) {
        waitElementToBeVisible(element);
        waitElementToBeClickable(element);
        actions.doubleClick(element).perform();
        return this;
    }

    /**
     * Клик правой кнопкой мыши по элементу.
     *
     * @param element Веб-элемент для клика правой кнопкой мыши.
     * @return Текущий объект WebElementActions для цепочки вызовов.
     */
    public WebElementActions rightClick(WebElement element) {
        waitElementToBeVisible(element);
        waitElementToBeClickable(element);
        scrollToElement(element);
        actions.contextClick(element).perform();
        return this;
    }

    /**
     * Перемещение к элементу.
     *
     * @param element Веб-элемент для перемещения.
     * @return Текущий объект WebElementActions для цепочки вызовов.
     */
    public WebElementActions moveToElement(WebElement element) {
        waitElementToBeVisible(element);
        actions.moveToElement(element).perform();
        return this;
    }

    /**
     * Приостановка выполнения на заданное количество секунд.
     *
     * @param seconds Количество секунд для паузы.
     */
    public void pause(Integer seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public WebElementActions clearElementUsingJS(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].value = '';", element);
        return this;
    }

}