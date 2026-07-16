package tests;

import org.junit.jupiter.api.*;
import pages.CartPage;
import pages.MainPage;
import pages.ProductPage;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static utils.Constants.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Test07 extends BaseTest {
    @Test @Order(1)
    @DisplayName("7.1: Открытие формы оформления заказа")
    public void testOpenPlaceOrderForm() {
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
    public void testPurchaseWithCorrectData() {
        MainPage.init().selectCategory(PH);
        MainPage.init().openProduct(S6);
        ProductPage.init().clickAddToCart();
        ProductPage.init().acceptAlert();
        MainPage.init().goToCart();
        com.codeborne.selenide.Selenide.sleep(2500);
        CartPage.init().clickPlaceOrder();
        CartPage cartPage = CartPage.init();
        cartPage.fillname("Test User");
        cartPage.fillCountry("Russia");
        cartPage.fillCity("Saint Petersburg");
        cartPage.fillCard("1234567890");
        cartPage.fillMonth("06");
        cartPage.fillYear("2026");
        cartPage.clickPurchase();
        assertTrue(cartPage.isPurchaseSuccessVisible(),
                "Сообщение об успешной покупке не появилось");
    }
}
