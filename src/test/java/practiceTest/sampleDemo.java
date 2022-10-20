package practiceTest;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class sampleDemo {
    SoftAssert soft=new SoftAssert();
    @BeforeMethod
    @Parameters("browser")
    public void setup(String browser) {
        Configuration.browser= browser;
        Configuration.headless=true;
        Configuration.baseUrl="https://www.lambdatest.com";

    }
    @Test
    public void enterMessageAndValidate() {
        open("/selenium-playground/");
        WebDriverRunner.getWebDriver().manage().window().maximize();
        WebDriverRunner.getWebDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        String Url = WebDriverRunner.url();
        System.out.println(Url);
        soft.assertEquals(Url, "https://www.lambdatest.com/selenium-playground/");
        String title= title();
        System.out.println(title);
        soft.assertEquals(title, "Selenium Grid Online | Run Selenium Test On Cloud");
        $(By.linkText("Simple Form Demo")).click();
        soft.assertEquals(WebDriverRunner.url(), "https://www.lambdatest.com/selenium-playground/simple-form-demo");
        soft.assertEquals(title(),"Selenium Grid Online | Run Selenium Test On Cloud");
        $(By.xpath("//h1[@class='text-size-48 font-bold text-black text-center leading-none text-shadow md:w-full leading-height-70 mx-auto smtablet:text-size-30 smtablet:leading-height-42']")).shouldHave(text("Simple Form Demo"));
        $(By.id("user-message"))
                .shouldBe(visible).val("Welcome to LambdaTest");

        $(By.id("showInput"))
                .shouldBe(visible)
                .click();

        $(By.id("message"))
                .shouldHave(text("Welcome to LambdaTest"));
        String message = $(By.id("message")).getText();
        soft.assertEquals(message, "Welcome to LambdaTest");
    }

    @Test
    public void checkArithmetic() {
        open("/selenium-playground/");
        WebDriverRunner.getWebDriver().manage().window().maximize();
        WebDriverRunner.getWebDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        String Url = WebDriverRunner.url();
        System.out.println(Url);
        soft.assertEquals(Url, "https://www.lambdatest.com/selenium-playground/");
        String title= title();
        System.out.println(title);
        soft.assertEquals(title, "Selenium Grid Online | Run Selenium Test On Cloud");
        $(By.linkText("Simple Form Demo")).click();
        soft.assertEquals(WebDriverRunner.url(), "https://www.lambdatest.com/selenium-playground/simple-form-demo");
        soft.assertEquals(title(),"Selenium Grid Online | Run Selenium Test On Cloud");
        $(By.id("sum1"))
                .shouldBe(visible)
                .val("10");

        $(By.id("sum2"))
                .shouldBe(visible)
                .val("10");
        $(By.xpath("(//button[@type=\"button\"])[2]"))
                .shouldBe(visible)
                .click();

        $(By.id("addmessage"))
                .shouldHave(text("20"));
        String message = $(By.id("addmessage")).getText();
        soft.assertEquals(message, "20");
        soft.assertAll();
    }

    @Test
    public void singleCheckboxDemo() {
        open("/selenium-playground/");
        WebDriverRunner.getWebDriver().manage().window().maximize();
        WebDriverRunner.getWebDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        $(By.linkText("Checkbox Demo")).shouldBe(visible).click();
        String url = WebDriverRunner.url();
        System.out.println(url);
        soft.assertEquals(url, "https://www.lambdatest.com/selenium-playground/checkbox-demo");
        String pageTitle = title();
        System.out.println(pageTitle);
        soft.assertEquals(pageTitle, "Selenium Grid Online | Run Selenium Test On Cloud");
        $(By.id("isAgeSelected")).shouldBe(visible).click();
        soft.assertTrue($(By.id("isAgeSelected")).isSelected());
        $(By.id("txtAge")).shouldHave(text("Success - Check box is checked"));
        $(By.id("isAgeSelected")).shouldBe(visible).click();
        soft.assertFalse($(By.id("isAgeSelected")).isSelected());
        soft.assertAll();
    }

    @Test
    public void multiCheckboxDemo() {
        open("/selenium-playground/");
        WebDriverRunner.getWebDriver().manage().window().maximize();
        WebDriverRunner.getWebDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        $(By.linkText("Checkbox Demo")).shouldBe(visible).click();
        String url = WebDriverRunner.url();
        System.out.println(url);
        soft.assertEquals(url, "https://www.lambdatest.com/selenium-playground/checkbox-demo");
        String pageTitle = title();
        System.out.println(pageTitle);
        soft.assertEquals(pageTitle, "Selenium Grid Online | Run Selenium Test On Cloud");
        $(By.id("box")).shouldBe(visible).click();
        $(By.id("box")).shouldBe(visible).click();
        soft.assertAll();
    }

    @Test
    public void multiSelectList() {
        open("/selenium-playground/");
        WebDriverRunner.getWebDriver().manage().window().maximize();
        WebDriverRunner.getWebDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        $(By.xpath("//a[normalize-space()='Select Dropdown List']")).shouldBe(visible).click();
        String listType = $(By.xpath("//div[contains(text(), 'Multi Select List ')]")).getText();
        soft.assertEquals(listType, "Multi Select List Demo");
        Select select=new Select($(By.xpath("//select[@name=\"States\"]")));
        select.selectByVisibleText("California");
        $(By.id("printAll")).shouldHave(text("Get All Selected")).click();
        String validateText = $(By.xpath("//p[@class=\"text-size-14 mt-20\"]")).getText();
        soft.assertTrue(WebDriverRunner.source().contains("Options selected are :"));
        soft.assertAll();
    }
}
