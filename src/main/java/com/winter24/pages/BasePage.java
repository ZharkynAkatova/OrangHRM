package com.winter24.pages;

import com.winter24.drivers.DriverManager;
import com.winter24.helper.BrowserHelper;
import com.winter24.helper.DropdownHelper;
import com.winter24.helper.WebElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Базовая страница, содержащая методы для взаимодействия с элементами веб-страницы.
 */
public abstract class BasePage {

    private final String parentXpath = "//nav[@class = 'oxd-topbar-body-nav']/ul/li";

    private final String xPath = parentXpath + "/ul/li";

    @FindBy(xpath = parentXpath)
    public List<WebElement> selectMenu;

    @FindBy(xpath = xPath)
    public List<WebElement> dropdownSubItems;

    @FindBy (xpath = "//div[contains(@class, 'option')]")
    public List<WebElement> dropdownSelectItems;

    @FindBy(xpath = "//button[@type= 'submit']")
    public WebElement searchBtn;

    @FindBy(xpath = "//button[text() = ' Reset ']")
    public WebElement resetBtn;


    public BasePage() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    public WebElementActions webElementActions = new WebElementActions();
    public DropdownHelper dropdownHelper = new DropdownHelper(DriverManager.getDriver());
    public BrowserHelper browserHelper = new BrowserHelper(DriverManager.getDriver());

    /**
     * Находит элемент в списке selectMenu по тексту и возвращает его.
     *
     * @param text Текст элемента, который нужно найти и вернуть.
     * @return WebElement Найденный элемент.
     * @throws NoSuchElementException Если элемент с указанным текстом не найден.
     */
    public WebElement takeElementFromList(List<WebElement> list, String text) {

         for (WebElement item : list) {
            if (item.getText().trim().equals(text)) {
                return item;
            }
        }

        throw new NoSuchElementException("Элемент с указанным текстом не найден: " + text);
    }


    /**
     * Находит родительский элемент по тексту, кликает по нему и затем ищет и кликает по подэлементу в выпадающем списке.
     *
     * @param parentText Текст родительского элемента, по которому нужно кликнуть.
     * @param subItemText Текст подэлемента, по которому нужно кликнуть после раскрытия выпадающего списка.
     * @throws NoSuchElementException Если родительский элемент или подэлемент с указанным текстом не найден.
     */
    public void movingToElementByDropdownList1(String parentText, String subItemText) {
        // Находим и кликаем на элемент из первого уровня
        WebElement parentElement = takeElementFromList(dropdownSubItems, parentText);
        parentElement.click();
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(30));
        // Явное ожидание появления элементов подсписка после клика
        wait.until(ExpectedConditions.visibilityOfAllElements(dropdownSubItems));
        for (WebElement subItem : dropdownSubItems) {
            if (subItem.getText().trim().equals(subItemText)) {
                webElementActions.click(subItem);
                return;
            }
        }
        throw new NoSuchElementException("Подэлемент с указанным текстом не найден: " + subItemText);
    }

    /**
     * Находит и кликает по элементам, заданным в иерархическом порядке.
     * Метод принимает переменное количество текстовых аргументов, которые представляют элементы на различных уровнях иерархии.
     *
     * @param texts Массив текстов, представляющих элементы на различных уровнях иерархии.
     * @throws NoSuchElementException Если элемент с указанным текстом на любом уровне иерархии не найден.
     */
    public void movingToElementByDropdownList2(String... texts) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(30));
        // Начинаем с элемента верхнего уровня
        WebElement currentElement = null;
        for (int i = 0; i < texts.length; i++) {
            String text = texts[i];
            List<WebElement> elements;

            // Определяем соответствующий XPath в зависимости от уровня итерации
            if (i == 0) {
                elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath(parentXpath)));
            } else {
                elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath(xPath)));
            }
            // Поиск элемента с указанным текстом
            boolean found = false;
            for (WebElement element : elements) {
                if (element.getText().trim().equals(text)) {
                    currentElement = element;
                    found = true;
                    break;
                }
            }
            if (!found) {
                throw new NoSuchElementException("Элемент с указанным текстом не найден: " + text);
            }
            // Клик на найденный элемент
            currentElement.click();
        }
    }
}