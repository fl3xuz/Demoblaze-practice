package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.CartPage;
import pages.MainPage;
import pages.ProductPage;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static utils.Constants.*;

public class Test06 extends BaseTest {
    @Test
    @DisplayName("6: Проверка удаления товара из корзины")
    public void testDeleteProductFromCart() {
        MainPage.init().selectCategory(PH);
        MainPage.init().openProduct(S6);
        ProductPage.init().clickAddToCart();
        ProductPage.init().acceptAlert();
        MainPage.init().goToCart();
        CartPage.init().deleteProduct(S6);
        com.codeborne.selenide.Selenide.sleep(1000);
        assertTrue(CartPage.init().isProductAbsent(S6),
                "Товар 'Samsung galaxy s6' всё еще отображается в корзине после удаления");
    }
}
