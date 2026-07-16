package pages;

import elements.Button;
import elements.Input;
import com.codeborne.selenide.Condition;
import static com.codeborne.selenide.Selenide.*;
import static utils.Constants.*;

/**
 * Класс, представляющий страницу корзины (Cart).
 * Отвечает за проверку наличия товаров в корзине, удаление товаров из нее,
 * а также за процесс оформления заказа (заполнение и отправка формы).
 */
public class CartPage extends BasePage {

    private final Button placeOrderBtn = new Button($(BTN_PLACE_ORDER));
    private final Button purchaseBtn = new Button($(BTN_PURCHASE));
    private final Button closeBtn = new Button($(BTN_CLOSE));
    private final Input nameInput = new Input($(INPUT_NAME));
    private final Input countryInput = new Input($(INPUT_COUNTRY));
    private final Input cityInput = new Input($(INPUT_CITY));
    private final Input cardInput = new Input($(INPUT_CARD));
    private final Input monthInput = new Input($(INPUT_MONTH));
    private final Input yearInput = new Input($(INPUT_YEAR));

    public static CartPage init() { return page(CartPage.class); }
    public void clickPlaceOrder() { placeOrderBtn.click(); }
    public void clickPurchase() { purchaseBtn.click(); }
    public void closeCheckoutForm() { closeBtn.click(); }
    public void fillname(String name) { nameInput.fill(name); }
    public void fillCountry(String country) { countryInput.fill(country); }
    public void fillCity(String city) { cityInput.fill(city); }
    public void fillCard(String card) { cardInput.fill(card); }
    public void fillMonth(String month) { monthInput.fill(month); }
    public void fillYear(String year) { yearInput.fill(year); }
    public void deleteProduct(String name) {
        $x("//td[text()='" + name + "']/..//a").click();
    }

    public boolean isProductAbsent(String name) {
        return $x("//td[text()='" + name + "']").shouldNot(Condition.exist).is(Condition.not(Condition.visible));
    }
    public boolean isProductPresent(String name) {
        return $x("//td[text()='" + name + "']").is(Condition.visible);
    }
    public boolean isCartEmpty() {
        return $(TBODY_ID).has(Condition.empty);
    }
    public boolean isPurchaseSuccessVisible() {
        return $(SWEET_ALERT).shouldBe(com.codeborne.selenide.Condition.visible).isDisplayed();
    }

    public boolean isCartTableVisible() {
        return $(TBODY_ID).is(Condition.visible);
    }

    public boolean isPlaceOrderButtonVisible() {
        return new Button($(BTN_PLACE_ORDER)).isDisplayed();
    }

    public boolean areHeadersVisible() {
        return $(TABLE).shouldBe(Condition.visible).has(Condition.text(PIC)) &&
                $(TABLE).has(Condition.text(TITLE)) &&
                $(TABLE).has(Condition.text(COST));
    }

    public boolean isPlaceOrderModalVisible() {
        return $(MODAL_ORDER).shouldBe(com.codeborne.selenide.Condition.visible).isDisplayed();
    }

}