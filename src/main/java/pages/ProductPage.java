package pages;
import static com.codeborne.selenide.Selenide.*;
import static utils.LoggerUtil.log;

public class ProductPage extends BasePage {
    public void addToCart() {
        log.info("Нажимаю кнопку 'Add to cart'");
        $(".btn-success").click();
        confirm(); // Подтверждаем всплывающее окно браузера
    }
}