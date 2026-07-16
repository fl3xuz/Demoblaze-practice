package pages;

import elements.Button;
import elements.Input;
import com.codeborne.selenide.Condition;
import static com.codeborne.selenide.Selenide.*;

/**
 * Класс, представляющий страницу корзины (Cart).
 * Отвечает за проверку наличия товаров в корзине, удаление товаров из нее,
 * а также за процесс оформления заказа (заполнение и отправка формы).
 */
public class CartPage extends BasePage {

    private final Button placeOrderBtn = new Button($(".btn-success"));
    private final Button purchaseBtn = new Button($("#orderModal .btn-primary"));
    private final Button closeBtn = new Button($("#orderModal .close"));
    private final Input nameInput = new Input($("#name"));
    private final Input countryInput = new Input($("#country"));
    private final Input cityInput = new Input($("#city"));
    private final Input cardInput = new Input($("#card"));
    private final Input monthInput = new Input($("#month"));
    private final Input yearInput = new Input($("#year"));

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
        return $("#tbodyid").has(Condition.empty);
    }
    public boolean isPurchaseSuccessVisible() {
        return $(".sweet-alert").shouldBe(com.codeborne.selenide.Condition.visible).isDisplayed();
    }

    public boolean isCartTableVisible() {
        return $("#tbodyid").is(Condition.visible);
    }

    public boolean isPlaceOrderButtonVisible() {
        return new Button($(".btn-success")).isDisplayed();
    }

    public boolean areHeadersVisible() {
        return $(".table").shouldBe(Condition.visible).has(Condition.text("Pic")) &&
                $(".table").has(Condition.text("Title")) &&
                $(".table").has(Condition.text("Price"));
    }

    public boolean isPlaceOrderModalVisible() {
        return $("#orderModal").shouldBe(com.codeborne.selenide.Condition.visible).isDisplayed();
    }

}