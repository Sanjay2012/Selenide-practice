package practiceTest;


import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class JSAlert {

    SoftAssert soft = new SoftAssert();

    @Parameters("browser")
    @BeforeMethod
    public void setUp(String browser) {
        Configuration.browser=browser;
        Configuration.headless=false;
        Configuration.baseUrl="https://the-internet.herokuapp.com";

    }


    @Test
    public void acceptJsAlertAndGetText() {
        open("/javascript_alerts");
        WebDriverRunner.getWebDriver().manage().window().maximize();
        WebDriverRunner.getWebDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        $(By.xpath("//button[@onclick=\"jsAlert()\"]")).shouldBe(visible).click();
        Alert alt = switchTo().alert();
        String alertText = alt.getText();
        soft.assertEquals(alertText, "I am a JS Alert");
        alt.accept();

    }

    @Test
    public void confirmJsAlertAndGetText() {
        open("/javascript_alerts");
        WebDriverRunner.getWebDriver().manage().window().maximize();
        WebDriverRunner.getWebDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        $(By.xpath("//button[@onclick=\"jsConfirm()\"]")).shouldBe(visible).click();
        Alert alt = switchTo().alert();
        String alertText = alt.getText();
        soft.assertEquals(alertText, "I am a JS Confirm");
        alt.accept();
        soft.assertTrue(WebDriverRunner.source().contains("Ok"));
    }

    @Test
    public void cancelJsAlertAndGetText() {
        open("/javascript_alerts");
        WebDriverRunner.getWebDriver().manage().window().maximize();
        WebDriverRunner.getWebDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        $(By.xpath("//button[@onclick=\"jsConfirm()\"]")).shouldBe(visible).click();
        Alert alt = switchTo().alert();
        String alertText = alt.getText();
        soft.assertEquals(alertText, "I am a JS Confirm");
        alt.accept();
        soft.assertTrue(WebDriverRunner.source().contains("Cancel"));
    }

    @Test
    public void handleJsProptAlertAndEnterText() {
        open("/javascript_alerts");
        WebDriverRunner.getWebDriver().manage().window().maximize();
        WebDriverRunner.getWebDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        $(By.xpath("//button[@onclick=\"jsPrompt()\"]")).shouldBe(visible).click();
        Alert alt = switchTo().alert();
        String alertText = alt.getText();
        soft.assertEquals(alertText, "I am a JS prompt");
        alt.sendKeys("Sanjay");
        alt.accept();
        soft.assertTrue(WebDriverRunner.source().contains("Sanjay"));
    }

    @Test
    public void handleJsProptAlertAndcancel() {
        open("/javascript_alerts");
        WebDriverRunner.getWebDriver().manage().window().maximize();
        WebDriverRunner.getWebDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        $(By.xpath("//button[@onclick=\"jsPrompt()\"]")).shouldBe(visible).click();
        Alert alt = switchTo().alert();
        String alertText = alt.getText();
        soft.assertEquals(alertText, "I am a JS prompt");
        alt.dismiss();
        soft.assertTrue(WebDriverRunner.source().contains("null"));
    }

    @AfterMethod
    public void tearDown() {
        closeWindow();
    }
}

