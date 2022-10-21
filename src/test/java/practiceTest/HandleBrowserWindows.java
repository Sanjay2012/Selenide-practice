package practiceTest;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class HandleBrowserWindows {
    SoftAssert soft = new SoftAssert();

    @Parameters("browser")
    @BeforeMethod
    public void setUp(String browser) {
        Configuration.browser=browser;
        Configuration.headless=false;
        Configuration.baseUrl = "https://demoqa.com";

    }

    @Test
    public void switchTo_NewTab() {
        open("/browser-windows");
        WebDriverRunner.getWebDriver().manage().window().maximize();
        WebDriverRunner.getWebDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        $(By.id("tabButton")).shouldHave(text("New Tab")).shouldBe(visible).click();
        switchTo().window(1);
        String header = $(By.id("sampleHeading")).getText();
        soft.assertEquals(header, "This is a sample page");
        soft.assertAll();
        closeWindow();
    }
    @AfterMethod
    public void tearDown() {
        closeWebDriver();

    }
}
