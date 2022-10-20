package practiceTest;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class JavaScriptMethods {

    SoftAssert soft=new SoftAssert();
    @Parameters("browser")
    @BeforeMethod
    public void setUp(String browser) {
        Configuration.browser=browser;
        Configuration.headless=true;
    }

    @Test
    public void jsTimeWait() {
        open("http://demo.guru99.com/V4/");
        WebDriverRunner.getWebDriver().manage().window().maximize();
        WebDriverRunner.getWebDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        long start_time = System.currentTimeMillis();
        executeAsyncJavaScript("window.setTimeout(arguments[arguments.length - 1], 5000);");
        System.out.println("Passed time: " + (System.currentTimeMillis() - start_time));

    }

    @Test
    public void JsScrolltToButtonAndClick() {
        Configuration.baseUrl = "https://www.lambdatest.com";
        open("/selenium-playground/");
        WebDriverRunner.getWebDriver().manage().window().maximize();
        WebDriverRunner.getWebDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        $(By.linkText("Input Form Submit")).shouldBe(visible).shouldHave(text("Input Form Submit")).click();
        SelenideElement submitButton = $(By.cssSelector("button[type='submit']"));
        executeJavaScript("window.scrollBy(0,-400)", "");
        executeJavaScript("arguments[0].click();", submitButton);

        boolean actualError = $(By.xpath("//input[@id='name']"))
                .getAttribute("validationMessage") != null;

        System.out.println(actualError);
        soft.assertTrue(actualError);
    }

    @AfterMethod
    public void tearDown() {
        closeWebDriver();
    }
}
