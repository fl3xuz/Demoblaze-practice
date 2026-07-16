package pages;

import elements.Button;
import com.codeborne.selenide.Condition;
import static com.codeborne.selenide.Selenide.*;

/**
 * Класс, представляющий страницу отдельного товара на сайте.
 * Отвечает за взаимодействие с карточкой продукта, в частности, за добавление товара в корзину.
 */
public class ProductPage extends BasePage {
    public static ProductPage init() {
        return page(ProductPage.class);
    }

    public void clickAddToCart() {
        new Button($(".btn-success")).click();
    }

    public boolean isTitleCorrect(String name) {
        return $(".name").has(Condition.text(name));
    }

    public boolean isPriceCorrect(String price) {
        return $(".price-container").shouldBe(Condition.visible).has(Condition.text(price));
    }

    public boolean isDescriptionVisible() {
        return $("#more-information").shouldBe(Condition.visible).is(Condition.visible);
    }

    public boolean isAddToCartButtonVisible() {
        return new Button($(".btn-success")).isDisplayed();
    }

    public void acceptAlert() {
        switchTo().alert().accept();
    }

    public void prepareAlertIntercept() {
        executeJavaScript(
                "window.lastAlertMsg = '';" +
                        "window.alert = function(msg) { window.lastAlertMsg = msg; };"
        );
    }

    public String getCapturedAlertText() {
        return executeJavaScript("return window.lastAlertMsg;");
    }


}