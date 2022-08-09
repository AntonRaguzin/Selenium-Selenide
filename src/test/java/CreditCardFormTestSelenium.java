

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class CreditCardFormTestSelenium {
    private WebDriver driver;

    @BeforeAll
    static void setupAll() {
        System.setProperty("webdriver.chrome.driver", "./driver/win/chromedriver.exe");
    }

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
    }

    @BeforeEach
    void headless() {
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void shouldSuccessfullySubmitTheApplication() throws InterruptedException {
        driver.get("http://localhost:9999/");
//        Thread.sleep(10000); Задержка в мс
//        List<WebElement> inputs = driver.findElements(By.tagName("input")); Поиск по селекторам не стабилет т.к. могуд добавляться новые элементы и тест упадет
//        inputs.get(0).sendKeys("Антонов Антон");
//        inputs.get(1).sendKeys("+79998887766");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Антонов Антон");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79998887766");
//        driver.findElement(By.className("checkbox__box")).click(); поиск элементов по имени класса
//        driver.findElement(By.className("button__text")).click();
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click(); // поиск по селектору css
        driver.findElement(By.className("button__text")).click();
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
//        String actual = driver.findElement(By.className("Success_successBlock__2L3Cw")).getText().trim(); // поиск по тексту не надежно (текст может меняться и тест упадет)
        String actual = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText().trim();
        Assertions.assertEquals(expected, actual);
    }

}
