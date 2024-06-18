package com.winter24.helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

/**
 * Класс DropdownHelper содержит методы для работы с выпадающими списками (dropdowns).
 */
public class DropdownHelper {

    private WebDriver driver;

    /**
     * Конструктор класса DropdownHelper.
     *
     * @param driver Веб-драйвер, используемый для взаимодействия с браузером.
     */
    public DropdownHelper(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Конструктор по умолчанию.
     */
    public DropdownHelper() {
    }

    /**
     * Метод для выбора элемента в выпадающем списке по видимому тексту.
     *
     * @param element Элемент выпадающего списка.
     * @param value   Текст, который необходимо выбрать.
     * @return Текущий экземпляр DropdownHelper для цепочки вызовов.
     */
    public DropdownHelper selectByVisibleText(WebElement element, String value) {
        Select select = new Select(element);
        select.selectByVisibleText(value);
        return this;
    }

    /**
     * Метод для выбора элемента в выпадающем списке по значению.
     *
     * @param element Элемент выпадающего списка.
     * @param value   Значение, которое необходимо выбрать.
     * @return Текущий экземпляр DropdownHelper для цепочки вызовов.
     */
    public DropdownHelper selectByValueText(WebElement element, String value) {
        Select select = new Select(element);
        select.selectByValue(value);
        return this;
    }

    /**
     * Метод для выбора элемента в выпадающем списке по индексу.
     *
     * @param element Элемент выпадающего списка.
     * @param index   Индекс элемента, который необходимо выбрать.
     * @return Текущий экземпляр DropdownHelper для цепочки вызовов.
     */
    public DropdownHelper selectByIndex(WebElement element, int index) {
        Select select = new Select(element);
        select.selectByIndex(index);
        return this;
    }

    /**
     * Метод для вывода всех элементов выпадающего списка с использованием цикла.
     *
     * @param element Элемент выпадающего списка.
     * @return Текущий экземпляр DropdownHelper для цепочки вызовов.
     */
    public DropdownHelper getOptions(WebElement element) {
        Select select = new Select(element);
        List<WebElement> listOfOptions = select.getOptions();
        for (WebElement item : listOfOptions) {
            System.out.println(item.getText());
        }
        return this;
    }

    /**
     * Метод для вывода всех элементов выпадающего списка с использованием Stream API.
     *
     * @param element Элемент выпадающего списка.
     * @return Текущий экземпляр DropdownHelper для цепочки вызовов.
     */
    public DropdownHelper getOptions2(WebElement element) {
        Select select = new Select(element);
        select.getOptions().stream()
                .map(WebElement::getText)
                .forEach(System.out::println);
        return this;
    }

    /**
     * Метод для вывода всех выбранных элементов выпадающего списка с использованием цикла.
     *
     * @param element Элемент выпадающего списка.
     * @return Текущий экземпляр DropdownHelper для цепочки вызовов.
     */
    public DropdownHelper getAllSelectedOptions(WebElement element) {
        Select select = new Select(element);
        List<WebElement> listOfAllSelectedOptions = select.getAllSelectedOptions();
        for (WebElement item : listOfAllSelectedOptions) {
            System.out.println(item.getText());
        }
        return this;
    }
}