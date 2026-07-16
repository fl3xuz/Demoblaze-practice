package pages;

import elements.Button;
import com.codeborne.selenide.Condition;
import static com.codeborne.selenide.Selenide.*;
import static utils.Constants.*;

/**
 * Класс, представляющий главную страницу сайта Demoblaze.
 * Отвечает за навигацию по категориям товаров и открытие карточек продуктов.
 */
public class MainPage extends BasePage {

    public static MainPage init() { return page(MainPage.class); }

    public void selectCategory(String category) {
        new Button($x("//a[text()='" + category + "']")).click();
    }

    public void openProduct(String productName) {
        new Button($x("//a[text()='" + productName + "']")).click();
    }

    public void goToCart() {
        new Button($(CART_MENU_LINK)).click();
    }

    public boolean isProductVisible(String productName) {
        try {
            $x("//a[text()='" + productName + "']").shouldBe(com.codeborne.selenide.Condition.visible);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isTitleCorrect(String expectedText) {
        return $(H2_TITLE).has(Condition.text(expectedText));
    }

    public void clickHome() { new Button($(HOME_LINK)).click(); }
}