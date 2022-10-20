package practiceTest;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class googleTest {
    SoftAssert soft= new SoftAssert();
    @Parameters("browser")
    @BeforeMethod
    public void setUp(String browser) {
        Configuration.browser=browser;
        Configuration.headless=true;
    }
    @Test
    public void googleSearchTest() {
        open("https://www.google.com/");
        WebDriverRunner.getWebDriver().manage().window().maximize();
        WebDriverRunner.getWebDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        $(By.name("q")).shouldHave(visible).setValue("Tesla");
        $(By.name(("btnK"))).click();
        $(By.id("logo")).shouldHave(appear);
        String header = $(By.xpath("//h3[contains(text(), 'Tesla: Electric Cars')]")).getText();
        System.out.println(header);
        soft.assertTrue( WebDriverRunner.source().contains("Tesla: Electric Cars, Solar & Clean Energy"));
        WebDriverRunner.closeWindow();

    }
}
