//import com.codeborne.selenide.Selenide;
//import com.codeborne.selenide.SelenideElement;
//import org.junit.jupiter.api.Test;
//import org.openqa.selenium.By;
//import static com.codeborne.selenide.Condition.exactText;
//import static com.codeborne.selenide.Selenide.$;
//import static com.codeborne.selenide.Selenide.open;
//
//public class CreditCardFormTestSelenide {
//    @Test
//    void shouldSuccessfullySubmitTheApplication() {
//        open("http://localhost:9999/");
//        SelenideElement form = $("[id=root]");
//        form.$("[data-test-id=name] input").setValue("Антонов Антон");
//        form.$("[data-test-id=phone] input").setValue("+79998887766");
//        form.$("[data-test-id=agreement]").click();
//        form.$(By.className("button__text")).click();
//        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
//    }
//}
