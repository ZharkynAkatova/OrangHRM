package com.winter24.pages;

import com.winter24.drivers.DriverManager;
import com.winter24.entities.Employee;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class AdminPage extends BasePage{

    @FindBy (xpath = "//label[text()='User Role']/../following-sibling::div/div/div")
    private WebElement userRoleSelect;

    @FindBy (xpath = "(//div[@class='oxd-select-wrapper'])[2]")
    private WebElement statusSelect;

    @FindBy (xpath = "//input[contains(@class, 'oxd-input--active') and not(@placeholder='Search')]")
    private WebElement inputUserName;

    @FindBy (xpath = "//input[(@placeholder='Type for hints...')]")
    private WebElement inputEmployeeName;


    /**
     * Ищет системного пользователя по заданным параметрам.
     *
     * @param inputText массив строк, содержащий параметры поиска:
     *                  [0] - имя пользователя
     *                  [1] - роль пользователя
     *                  [2] - имя сотрудника
     *                  [3] - статус
     * @throws Exception в случае возникновения ошибки
     */
    public void searchSystemUser(String... inputText) throws Exception {

        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(15));

        // Сбрасываем фильтры поиска
        webElementActions.click(resetBtn);

        // Проверяем, что первый параметр (имя пользователя) не равен null
        if (inputText.length > 0 && inputText[0] != null) {
            webElementActions.sendKeys(inputUserName, inputText[0]);
        }

        // Проверяем, что второй параметр (роль пользователя) не равен null
        if (inputText.length > 1 && inputText[1] != null) {
            webElementActions.click(wait.until(ExpectedConditions.elementToBeClickable(userRoleSelect)))
                    .click(takeElementFromList(dropdownSelectItems, inputText[1]));
        }

        // Проверяем, что третий параметр (имя сотрудника) не равен null
        if (inputText.length > 2 && inputText[2] != null) {
            webElementActions.clearAndsendKeysWithARROW_DOWNandEnter(inputEmployeeName, inputText[2]);
        }

        // Проверяем, что четвертый параметр (статус) не равен null
        if (inputText.length > 3 && inputText[3] != null) {
            webElementActions.click(wait.until(ExpectedConditions.elementToBeClickable(statusSelect)))
                    .click(takeElementFromList(dropdownSelectItems, inputText[3]));
        }

        // Независимо от количества параметров, всегда кликаем на кнопку поиска
        webElementActions.click(searchBtn);
    }

    /**
     * Получает список сотрудников из таблицы.
     *
     * @return список объектов Employee
     * @throws Exception в случае возникновения ошибки
     */
    public ArrayList<Employee> getEmployeesFromTable() {
        List<WebElement> rows = DriverManager.getDriver().findElements(By.cssSelector(".oxd-table-card .oxd-table-row"));

        ArrayList<Employee> employees = new ArrayList<>();

        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.cssSelector(".oxd-table-cell"));
            String userName = cells.get(1).getText();
            String userRole = cells.get(2).getText();
            String employeeName = cells.get(3).getText();
            String status = cells.get(4).getText();

            if (userName.isEmpty() || userRole.isEmpty() || employeeName.isEmpty() || status.isEmpty()) {
                continue;
            }

            employees.add(new Employee(userName, userRole, employeeName, status));
        }
        return employees;
    }

    // Выводим результаты поиска
    public void printListEmployees() throws Exception {
        if (getEmployeesFromTable().isEmpty()) {
            System.out.println("Поиск не дал результата");
        } else {
            System.out.println("Результаты поиска:");
            int i = 1;
            for (Employee employee : getEmployeesFromTable()) {
                System.out.println(i + ". " + employee.toString());
                i++;
            }
        }
        System.out.print("\n");
    }
}
