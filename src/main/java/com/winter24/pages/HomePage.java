package com.winter24.pages;

import com.winter24.drivers.DriverManager;
import com.winter24.enums.Endpoints;
import com.winter24.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Класс HomePage представляет главную страницу приложения и содержит методы для взаимодействия с элементами этой страницы.
 */
public class HomePage extends BasePage {

    @FindBy(xpath = "//input[@name='username']")
    private WebElement userNameInput;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginBtn;

    /**
     * Приватный метод для выбора пункта бокового меню по заданному эндпоинту.
     *
     * @param endpoints Эндпоинт, который нужно выбрать в боковом меню.
     */
    private void chooseSidebarMenu(Endpoints endpoints) {
        WebElement element = DriverManager.getDriver().
                findElement(By.xpath("//ul[@class='oxd-main-menu']/li/a/span[text()='" + endpoints.getEndpoint() +"']"));
        element.click();
    }

    /**
     * Метод для открытия соответствующего эндпоинта.
     *
     * @param endpoint Эндпоинт, на который нужно переключиться.
     */
    public void switchToTab(Endpoints endpoint) {
        browserHelper.open(ConfigReader.getValue("baseURL"));
        doLogin();
        WebElement webElement = DriverManager.getDriver()
                .findElement(By.xpath("//span[text() = '" + endpoint.getEndpoint() + "']"));
        webElementActions.click(webElement);
    }

    /**
     * Метод для выполнения входа в систему.
     * Использует данные для входа, которые загружаются из конфигурационного файла.
     */
    public void doLogin() {
        webElementActions.sendKeys(userNameInput, ConfigReader.getValue("login"))
                .sendKeys(passwordInput, ConfigReader.getValue("password"))
                .click(loginBtn);
    }
}