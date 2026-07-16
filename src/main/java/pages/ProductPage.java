package pages;

import elements.Button;
import com.codeborne.selenide.Condition;
import static com.codeborne.selenide.Selenide.*;
import static utils.Constants.*;

/**
 * Класс, представляющий страницу отдельного товара на сайте.
 * Отвечает за взаимодействие с карточкой продукта, в частности, за добавление товара в корзину.
 */
public class ProductPage extends BasePage {
    public static ProductPage init() {
        return page(ProductPage.class);
    }

    public void clickAddToCart() {
        new Button($(ADD_TO_CART_BTN)).click();
    }

    public boolean isTitleCorrect(String name) {
        return $(PRODUCT_NAME_SELECTOR).has(Condition.text(name));
    }

    public boolean isPriceCorrect(String price) {
        return $(PRODUCT_PRICE_SELECTOR).shouldBe(Condition.visible).has(Condition.text(price));
    }

    public boolean isDescriptionVisible() {
        return $(PRODUCT_DESC_SELECTOR).shouldBe(Condition.visible).is(Condition.visible);
    }

    public boolean isAddToCartButtonVisible() {
        return new Button($(ADD_TO_CART_BTN)).isDisplayed();
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