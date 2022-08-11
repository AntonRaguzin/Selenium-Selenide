
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CreditCardFormTestSelenide {
    @Test
    void shouldSuccessfullySubmitTheApplication() {
        Configuration.headless = true;
        open("http://localhost:9999/");
        SelenideElement form = $("[id=root]");
        form.$("[data-test-id=name] input").setValue("Антонов-Пирогов Антон");
        form.$("[data-test-id=phone] input").setValue("+79998887766");
        form.$("[data-test-id=agreement]").click();
        form.$(By.className("button__text")).click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }
    @Test
    void shouldFindImputErrorFieldName() {
        Configuration.headless = true;
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Anton");
        $("[data-test-id=phone] input").setValue("+79998887766");
        $("[data-test-id=agreement]").click();
        $(By.className("button__text")).click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldFindImputErrorFieldPhone() {
        Configuration.headless = true;
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Антон");
        $("[data-test-id=phone] input").setValue("89998887766");
        $("[data-test-id=agreement]").click();
        $("button[role=button]").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
        }
    @Test
    void shouldNotBeSentWithoutCheckbox() {
        Configuration.headless = true;
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Антонов Антон");
        $("[data-test-id=phone] input").setValue("+79998887766");
        $("button[role=button]").click();
        $("[data-test-id=agreement].input_invalid").shouldBe(visible);
        }
    @Test
    void shouldShowErrorIfEmptyInputName() {
        Configuration.headless = true;
        open("http://localhost:9999/");
        $("[data-test-id=name] input").sendKeys("");
        $("[data-test-id=phone] input").sendKeys("+79998887766");
        $("[data-test-id=agreement]").click();
        $("button[role=button]").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }
    @Test
    void shouldShowErrorIfEmptyInputPhone() {
        Configuration.headless = true;
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Антон");
        $("[data-test-id=phone] input").sendKeys("");
        $("[data-test-id=agreement]").click();
        $("button[role=button]").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }
}
