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

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class HandlingJSAlert {

    SoftAssert soft = new SoftAssert();

    @Parameters("browser")
    @BeforeMethod
    public void setUp(String browser) {
        Configuration.browser=browser;
        Configuration.headless=true;
        Configuration.baseUrl="https://demoqa.com";

    }

    @Test(priority = 1)
    public void acceptAlertAndGetText() {
        open("/alerts");
        WebDriverRunner.getWebDriver().manage().window().maximize();
        WebDriverRunner.getWebDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        $(By.id("alertButton")).shouldBe(visible).click();
        Alert alt = switchTo().alert();
        String alertText = alt.getText();
        soft.assertEquals(alertText, "You clicked a button");
        alt.accept();

    }

    @Test(priority = 2)
    public void acceptTimerAlertAndGetText() throws InterruptedException {
        open("/alerts");
        WebDriverRunner.getWebDriver().manage().window().maximize();
        WebDriverRunner.getWebDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        $(By.id("timerAlertButton")).shouldBe(visible).click();
        Thread.sleep(6000);
        Alert alt = switchTo().alert();
        String alertText = alt.getText();
        soft.assertEquals(alertText, "This alert appeared after 5 seconds");
        alt.accept();
    }

    @Test(priority = 3)
    public void confirmAlertAndGetText() throws InterruptedException {
        open("/alerts");
        WebDriverRunner.getWebDriver().manage().window().maximize();
        WebDriverRunner.getWebDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        $(By.id("confirmButton")).shouldBe(visible).click();
        Alert alt = switchTo().alert();
        String alertText = alt.getText();
        soft.assertEquals(alertText, "Do you confirm action?");
        alt.accept();
        soft.assertTrue(WebDriverRunner.source().contains("Ok"));
    }

    @Test(priority = 4)
    public void cancelAlertAndGetText() {
        open("/alerts");
        WebDriverRunner.getWebDriver().manage().window().maximize();
        WebDriverRunner.getWebDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        $(By.id("confirmButton")).shouldBe(visible).click();
        Alert alt = switchTo().alert();
        String alertText = alt.getText();
        soft.assertEquals(alertText, "Do you confirm action?");
        alt.dismiss();
        soft.assertTrue(WebDriverRunner.source().contains("Cancel"));
    }

    @Test(priority = 5)
    public void handleProptAlertAndEnterText() {
        open("/alerts");
        WebDriverRunner.getWebDriver().manage().window().maximize();
        WebDriverRunner.getWebDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        $(By.id("promtButton")).shouldBe(visible).click();
        Alert alt = switchTo().alert();
        String alertText = alt.getText();
        soft.assertEquals(alertText, "Please enter your name");
        alt.sendKeys("Sanjay");
        alt.accept();
        soft.assertTrue(WebDriverRunner.source().contains("Sanjay"));
    }


    @AfterMethod
    public void tearDown() {

        closeWindow();
    }
}

