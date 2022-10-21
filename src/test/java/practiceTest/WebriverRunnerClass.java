package practiceTest;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class WebriverRunnerClass {
    SoftAssert soft=new SoftAssert();
   @Parameters("browser")
    @BeforeMethod
    public void setUp(String browser) {
        Configuration.browser=browser;
        Configuration.headless=false;
    }

    @Test
    public void myTest() {
        open("https://opensource-demo.orangehrmlive.com/");  // parent window
        WebDriverRunner.getWebDriver().manage().window().maximize();
        WebDriverRunner.getWebDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        System.out.println(WebDriverRunner.isChrome());
        WebDriverRunner.clearBrowserCache();

        System.out.println(WebDriverRunner.url());
        soft.assertTrue(WebDriverRunner.url().contains("orangehrmlive"));
        System.out.println(title());

        soft.assertEquals(title(), "OrangeHRM");
        soft.assertTrue(title().contains("HRM"));
        System.out.println(WebDriverRunner.supportsJavascript());

        System.out.println(WebDriverRunner.source());
        soft.assertTrue(WebDriverRunner.source().contains("orangehrmlive"));

        WebDriverRunner.getAndCheckWebDriver();
        closeWebDriver();

    }
}
