package com.winter24.helper;

import org.openqa.selenium.WebDriver;

import java.util.LinkedList;
import java.util.Set;

/**
 * Класс BrowserHelper содержит методы для управления браузером.
 */
public class BrowserHelper {

    private WebDriver driver;

    /**
     * Конструктор класса BrowserHelper.
     *
     * @param driver Веб-драйвер, используемый для взаимодействия с браузером.
     */
    public BrowserHelper(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Метод для открытия указанного URL.
     *
     * @param url URL-адрес для открытия.
     */
    public void open(String url) {
        driver.navigate().to(url);
    }

    /**
     * Метод для возврата на предыдущую страницу.
     */
    public void goBack() {
        driver.navigate().back();
    }

    /**
     * Метод для перехода на следующую страницу.
     */
    public void goForward() {
        driver.navigate().forward();
    }

    /**
     * Метод для обновления текущей страницы.
     */
    public void refreshThePage() {
        driver.navigate().refresh();
    }

    /**
     * Метод для получения всех дескрипторов окон браузера.
     *
     * @return Множество дескрипторов окон браузера.
     */
    public Set<String> getWindowHandless() {
        return driver.getWindowHandles();
    }

    /**
     * Метод для переключения на окно по его индексу.
     *
     * @param index Индекс окна, на которое нужно переключиться.
     * @throws IllegalArgumentException Если индекс не соответствует ни одному из окон.
     */
    public void switchToWindow(int index) {
        LinkedList<String> windowsId = new LinkedList<>(getWindowHandless());

        for (String window : windowsId) {
            System.out.println(window);
        }

        if (index < 0 || index >= windowsId.size()) {
            throw new IllegalArgumentException("Invalid index: " + index);
        }

        driver.switchTo().window(windowsId.get(index));
    }

    /**
     * Метод для переключения на главное окно (первое открытое окно).
     */
    public void switchToParentWindow() {
        LinkedList<String> windowsId = new LinkedList<>(getWindowHandless());
        driver.switchTo().window(windowsId.get(0));
    }

    /**
     * Метод для переключения на главное окно и закрытия всех дочерних окон.
     */
    public void switchToParentWithChildClose() {
        switchToParentWindow();

        LinkedList<String> windowsId = new LinkedList<>(getWindowHandless());

        for (int i = 1; i < windowsId.size(); i++) {
            driver.switchTo().window(windowsId.get(i));
            driver.close();
        }
        switchToParentWindow();
    }
}