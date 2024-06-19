import com.winter24.enums.Endpoints;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.qameta.allure.testng.Tag;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

//@Listeners(com.winter24.listener.ScreenshotListener.class)

public class HomeTest extends BaseTest {

    @Test (description ="", groups = {"Smoke", "UI", "2", "OrangHRM"})
    @Tag("UI")
    @Story("")
    @Step
    public void HomeTest2 () {
        orangeHRMPages.getHomePage().switchToTab(Endpoints.ADMIN);
        orangeHRMPages.getAdminPage().movingToElementByDropdownList2("Configuration", "Localization");
    }

}
