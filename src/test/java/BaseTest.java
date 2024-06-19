import com.winter24.drivers.DriverManager;
import com.winter24.helper.*;
import com.winter24.pages.*;
import com.winter24.utils.RandomUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

@Listeners(com.winter24.listener.ScreenshotListener.class)

public class BaseTest {

    protected WebDriver driver;

    protected RandomUtils randomUtils;

    protected WebElementActions webElementActions;

    protected AlertHelper alertHelper;

    protected BrowserHelper browserHelper;

    protected OrangeHRMPages orangeHRMPages;

    protected DropdownHelper dropdownHelper;

    protected IframeHelper iframeHelper;


    @BeforeClass(alwaysRun = true)
    public void setUp() {
        driver = DriverManager.getDriver();
        randomUtils = new RandomUtils();
        webElementActions = new WebElementActions();
        alertHelper = new AlertHelper(driver);
        browserHelper = new BrowserHelper(driver);
        orangeHRMPages = new OrangeHRMPages();
        orangeHRMPages.setUp();
        dropdownHelper = new DropdownHelper(driver);
        iframeHelper = new IframeHelper(driver);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown()  {
        DriverManager.closedriver();
    }
}