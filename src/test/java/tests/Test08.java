package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.MainPage;
import pages.ProductPage;
import pages.CartPage;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static utils.Constants.*;

public class Test08 extends BaseTest {
    @Test
    @DisplayName("8: Проверка отмены оформления заказа")
    public void testClosePlaceOrder() {
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
