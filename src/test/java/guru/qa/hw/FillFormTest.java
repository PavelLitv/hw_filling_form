package guru.qa.hw;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class FillFormTest {
    @Test
    void fillingFormTest() {
        Configuration.browserSize = ("1920x1080");
        open("https://demoqa.com/automation-practice-form");
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");

        $("#firstName").sendKeys("Pavel");
        $("#lastName").sendKeys("Li");
        $("#userEmail").sendKeys("pavel_li@gmail.com");
        $x("//input[@value='Male']/..").click();
        $("#userNumber").sendKeys("7909808707");

        $("#dateOfBirthInput").click();
        $x("//select[@class='react-datepicker__month-select']").selectOption("March");
        $x("//select[@class='react-datepicker__year-select']").selectOption("1980");
        $x("//div[@class='react-datepicker__day react-datepicker__day--006']").click();

        $("#subjectsInput").setValue("Arts").pressEnter();
        $x("//label[text()='Sports']").click();
        $("#uploadPicture").uploadFile(new File("src/test/resources/photo.jpg"));
        $("#currentAddress").sendKeys("luxury village somewhere in Siberia");

        $x("//div[text()='Select State']").click();
        $x("//div[text()='Haryana']").click();
        $x("//div[text()='Select City']").click();
        $x("//div[text()='Karnal']").click();

        $("#submit").click();

        //asserts
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $x("//td[text()='Student Name']/following::td").shouldHave(text("Pavel Li"));
        $x("//td[text()='Student Email']/following::td").shouldHave(text("pavel_li@gmail.com"));
        $x("//td[text()='Gender']/following::td").shouldHave(text("Male"));
        $x("//td[text()='Mobile']/following::td").shouldHave(text("7909808707"));
        $x("//td[text()='Date of Birth']/following::td").shouldHave(text("06 March,1980"));
        $x("//td[text()='Subjects']/following::td").shouldHave(text("Arts"));
        $x("//td[text()='Hobbies']/following::td").shouldHave(text("Sports"));
        $x("//td[text()='Picture']/following::td").shouldHave(text("photo.jpg"));
        $x("//td[text()='Address']/following::td").shouldHave(text("luxury village somewhere in Siberia"));
        $x("//td[text()='State and City']/following::td").shouldHave(text("Haryana Karnal"));
    }
}