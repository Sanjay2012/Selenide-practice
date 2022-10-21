package practiceTest;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class NavigationTest {
    SoftAssert soft=new SoftAssert();
    @Parameters("browser")
    @BeforeMethod
    public void setUp(String browser) {
        Configuration.browser=browser;
        Configuration.headless=false;

    }

    @Test
    public void navigateTest() throws InterruptedException {
        open("https://www.google.com/");
        WebDriverRunner.getWebDriver().manage().window().maximize();
        WebDriverRunner.getWebDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        System.out.println(title());
        String googlePageTitle = title();
        soft.assertEquals(googlePageTitle, "Google");
        Thread.sleep(2000);
        open("https://www.facebook.com/");
        System.out.println(title());
        String facebookPageTitle = title();
        soft.assertEquals(facebookPageTitle, "Facebook – log in or sign up");
        Thread.sleep(2000);
        back();
        System.out.println(title());
        String googlePageTitle1 = title();
        soft.assertEquals(googlePageTitle1, "Google");
        Thread.sleep(2000);
        forward();
        System.out.println(title());
        String facebookPageTitle1 = title();
        soft.assertEquals(facebookPageTitle1, "Facebook – log in or sign up");
        Thread.sleep(2000);
        back();
        System.out.println(title());
        String googlePageTitle2 = title();
        soft.assertEquals(googlePageTitle2, "Google");
        Thread.sleep(2000);
        refresh();



    }

    @AfterMethod
    public void tearDown() {
        closeWindow();

    }

    @AfterClass
    public void afterClass() {
        closeWebDriver();

    }
}
