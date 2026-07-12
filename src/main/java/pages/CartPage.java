package pages;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.*;
import static utils.LoggerUtil.log;
import static com.codeborne.selenide.Condition.*;

public class CartPage extends BasePage {
    public void placeOrder() {
        log.info("Нажимаю кнопку Place Order");
        $(".btn-success").click(); // Кнопка Place Order
    }

    public void fillCheckoutForm(String name, String country, String city, String card, String month, String year) {
        log.info("Заполняю форму заказа");
        $("#name").shouldBe(visible).setValue(name);
        $("#country").setValue(country);
        $("#city").setValue(city);
        $("#card").setValue(card);
        $("#month").setValue(month);
        $("#year").setValue(year);
        // Кликаем кнопку Purchase
        $("#orderModal .btn-primary").click();
    }

    public void closeCheckoutForm() {
        log.info("Закрываю форму заказа через крестик");
        // Кликаем по крестику в заголовке модального окна
        $("#orderModal .close").click();

        com.codeborne.selenide.Selenide.sleep(500);
    }

    public boolean isProductPresent(String name) {
        log.info("Ожидаю появления товара в корзине: " + name);
        try {
            $x("//td[text()='" + name + "']").shouldBe(visible);
            return true;
        } catch (Exception e) {
            log.warning("Товар не найден: " + name);
            return false;
        }
    }

    public void waitForModalToClose() {
        log.info("Ожидаю закрытия модального окна");
        $("#orderModal").shouldNotBe(visible);
    }

    public void deleteProduct(String name) {
        log.info("Удаляю товар: " + name);
        $x("//td[text()='" + name + "']/..//a").click();
        sleep(1000);
    }
}