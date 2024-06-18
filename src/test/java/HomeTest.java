import com.winter24.enums.Endpoints;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.qameta.allure.testng.Tag;
import org.testng.annotations.Test;

public class HomeTest extends BaseTest {

    @Test
    public void HomeTest1 () {
        orangeHRMPages.getHomePage().switchToTab(Endpoints.TIME);
        orangeHRMPages.getAdminPage().movingToElementByDropdownList1("Timesheets", "My Timesheets");
    }

    @Test (description ="", groups = {"UI", "2", "OrangHRM"})
    @Tag("UI")
    @Story("")
    @Step
    public void HomeTest2 () {
        orangeHRMPages.getHomePage().switchToTab(Endpoints.ADMIN);
        orangeHRMPages.getAdminPage().movingToElementByDropdownList2("Configuration", "Localization");
    }

    @Test
    public void HomeTest3 () {
        orangeHRMPages.getHomePage().switchToTab(Endpoints.ADMIN);
        orangeHRMPages.getAdminPage().movingToElementByDropdownList2("Nationalities");
    }



}