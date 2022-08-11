
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class CreditCardFormTestSelenium {
    private WebDriver driver;

    @BeforeAll
    static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:9999/");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void shouldSuccessfullySubmitTheApplication() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Антонов-Пряников Антон");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79998887766");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click(); // поиск по селектору css
        driver.findElement(By.className("button__text")).click();
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText().trim();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldFindImputErrorFieldName() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Anton");
        driver.findElement(By.className("button__text")).click();
        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=name].input_invalid .input__sub")).getText().trim();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldFindImputErrorFieldPhone() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Антон");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("89998887766");
        driver.findElement(By.className("button__text")).click();
        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=phone].input_invalid .input__sub")).getText().trim();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldNotBeSentWithoutCheckbox() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Антонов Антон");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79998887766");
        driver.findElement(By.className("button__text")).click();
        String expected = "true";
        String actual = String.valueOf(driver.findElement(By.cssSelector("[data-test-id=agreement].input_invalid")).isDisplayed());
        Assertions.assertEquals(expected, actual);
    }
    @Test
    void shouldShowErrorIfEmptyInputName() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79998887766");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button__text")).click();
        String expected = "Поле обязательно для заполнения";
        String actual = driver.findElement(By.cssSelector("[data-test-id=name].input_invalid .input__sub")).getText().trim();
        Assertions.assertEquals(expected, actual);
    }
    @Test
    void shouldShowErrorIfEmptyInputPhone() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Антон");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button__text")).click();
        String expected = "Поле обязательно для заполнения";
        String actual = driver.findElement(By.cssSelector("[data-test-id=phone].input_invalid .input__sub")).getText().trim();
        Assertions.assertEquals(expected, actual);
    }
}
