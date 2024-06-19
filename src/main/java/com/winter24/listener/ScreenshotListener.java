package com.winter24.listener;

import com.winter24.drivers.DriverManager;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ScreenshotListener implements ITestListener {


    WebDriver driver = DriverManager.getDriver();

    /**
     * Этот метод вызывается каждый раз, когда тест завершается неудачно.
     * Если экземпляр WebDriver не равен null, то выполняется захват скриншота.
     *
     * @param result Результат теста, который завершился неудачно.
     */
    @Override
    public void onTestFailure(ITestResult result) {
        if (driver != null) {
            saveScreenshotPNG();
        }
    }

    /**
     * Захватывает скриншот в формате PNG и прикрепляет его к отчету тестирования.
     *
     * @return Массив байтов, представляющий скриншот в формате PNG.
     */
    @Attachment(value = "Скриншот страницы", type = "image/png")
    public byte[] saveScreenshotPNG() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    /**
     * Этот метод вызывается перед началом любого теста.
     *
     * @param context Контекст теста.
     */
    @Override
    public void onStart(ITestContext context) {

    }

    /**
     * Этот метод вызывается перед началом каждого теста.
     *
     * @param result Результат теста, который собирается начаться.
     */
    @Override
    public void onTestStart(ITestResult result) {

    }

    /**
     * Этот метод вызывается каждый раз, когда тест завершается успешно.
     *
     * @param result Результат теста, который завершился успешно.
     */
    @Override
    public void onTestSuccess(ITestResult result) {

    }

    /**
     * Этот метод вызывается после завершения всех тестов.
     *
     * @param context Контекст теста.
     */
    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
    }

    /**
     * Этот метод вызывается каждый раз, когда тест пропускается.
     *
     * @param result Результат теста, который был пропущен.
     */
    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }
}
