package tests;

import org.junit.jupiter.api.*;
import pages.CartPage;
import pages.MainPage;
import pages.ProductPage;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static utils.Constants.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PurchaseWorkflowTests extends BaseTest {
    @Test @Order(1)
    @DisplayName("7.1: Открытие формы оформления заказа")
    public void Test07_1() {
        MainPage.init().selectCategory(PH);
        MainPage.init().openProduct(S6);
        ProductPage.init().clickAddToCart();
        ProductPage.init().acceptAlert();
        MainPage.init().goToCart();
        com.codeborne.selenide.Selenide.sleep(2500);
        CartPage.init().clickPlaceOrder();
        assertTrue(CartPage.init().isPlaceOrderModalVisible(),
                "Форма оформления заказа 'Place order' не открылась");
    }

    @Test @Order(2)
    @DisplayName("7.2: Оформление заказа с корректными данными")
    public void Test07_2() {
        MainPage.init().selectCategory(PH);
        MainPage.init().openProduct(S6);
        ProductPage.init().clickAddToCart();
        ProductPage.init().acceptAlert();
        MainPage.init().goToCart();
        com.codeborne.selenide.Selenide.sleep(2500);
        CartPage.init().clickPlaceOrder();
        CartPage cartPage = CartPage.init();
        cartPage.fillname(USER_NAME);
        cartPage.fillCountry(USER_COUNTRY);
        cartPage.fillCity(USER_CITY);
        cartPage.fillCard(USER_CARD);
        cartPage.fillMonth(USER_MONTH);
        cartPage.fillYear(USER_YEAR);
        cartPage.clickPurchase();
        assertTrue(cartPage.isPurchaseSuccessVisible(),
                "Сообщение об успешной покупке не появилось");
    }

    @Test @Order(3)
    @DisplayName("8: Проверка отмены оформления заказа")
    public void Test08() {
        MainPage.init().selectCategory(PH);
        MainPage.init().openProduct(S6);
        ProductPage.init().clickAddToCart();
        ProductPage.init().acceptAlert();
        MainPage.init().goToCart();
        CartPage.init().clickPlaceOrder();
        CartPage.init().closeCheckoutForm();
        com.codeborne.selenide.Selenide.sleep(2500);
        assertTrue(CartPage.init().isProductPresent(S6),
                "Форма оформления заказа закрылась с оформленным заказом");
    }
}
