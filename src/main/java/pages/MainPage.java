package pages;
import static com.codeborne.selenide.Selenide.*;
import static utils.LoggerUtil.log;

public class MainPage extends BasePage {
    public void open() {
        log.info("Открываю сайт https://www.demoblaze.com/");
        com.codeborne.selenide.Selenide.open("https://www.demoblaze.com/");
    }

    public void selectCategory(String category) {
        log.info("Выбираю категорию: " + category);
        $x("//a[text()='" + category + "']").click();
    }

    public void openProduct(String productName) {
        log.info("Открываю товар: " + productName);
        $x("//a[text()='" + productName + "']").click();
    }

    public void goToCart() {
        log.info("Перехожу в корзину");
        $("#cartur").click();
    }
}