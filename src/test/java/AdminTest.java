import com.winter24.enums.Endpoints;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(com.winter24.listener.ScreenshotListener.class)

public class AdminTest extends BaseTest{

    @Test
    public void test1() {
        orangeHRMPages.getHomePage().switchToTab(Endpoints.ADMIN);
    }

    @Test
    public void test2() throws Exception {
        orangeHRMPages.getHomePage().switchToTab(Endpoints.ADMIN);
        orangeHRMPages.getAdminPage().movingToElementByDropdownList2("User Management", "Users");

//        orangeHRMPages.getAdminPage().searchSystemUser(null, null, "test user");
//        Thread.sleep(2000);
//        orangeHRMPages.getAdminPage().printListEmployees();
//
//        orangeHRMPages.getAdminPage().searchSystemUser("FMLName");
//        Thread.sleep(2000);
//        orangeHRMPages.getAdminPage().printListEmployees();
//
//        orangeHRMPages.getAdminPage().searchSystemUser("Admin", "Admin");
//        Thread.sleep(2000);
//        orangeHRMPages.getAdminPage().printListEmployees();

        orangeHRMPages.getAdminPage().searchSystemUser("FMLName1", "ESS", "FName LName");
        Thread.sleep(2000);
        orangeHRMPages.getAdminPage().printListEmployees();

//        orangeHRMPages.getAdminPage().searchSystemUser(null, null, null, "Enabled");
//        Thread.sleep(2000);
//        orangeHRMPages.getAdminPage().printListEmployees();

    }

}
