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
public class CloseBrowser {
    SoftAssert soft=new SoftAssert();
    @Parameters("browser")
    @BeforeMethod
    public void setUp(String browser) {
        Configuration.browser=browser;
        Configuration.headless=true;
    }

    @Test
    public void closeBrowserTest() throws InterruptedException {
        open("https://opensource-demo.orangehrmlive.com/");  // parent window
        WebDriverRunner.getWebDriver().manage().window().maximize();
        WebDriverRunner.getWebDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        System.out.println(title());
        soft.assertEquals(title(), "OrangeHRM");

        $(By.xpath("//a[@href=\"https://www.facebook.com/OrangeHRM/\"]")).shouldBe(visible).click(); // child window 1
        switchTo().window(1);
        System.out.println(title());
        soft.assertEquals(title(), "OrangeHRM - World's Most Popular Opensource HRIS | Facebook");

        Thread.sleep(2000);
        closeWindow(); // it will close the current opened child window==> driver.close();

        switchTo().window(0);
        System.out.println(title());
        soft.assertEquals(title(), "OrangeHRM");
        closeWebDriver(); // close main driver ==> driver.quit();
    }
}
