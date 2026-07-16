package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.MainPage;
import pages.ProductPage;
import pages.CartPage;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static utils.Constants.*;

public class Test05 extends BaseTest {

    @Test
    @DisplayName("5: Проверка добавления нескольких товаров в корзину")
    public void testMultipleProductsInCart() {
        MainPage.init().selectCategory(PH);
        MainPage.init().openProduct(S6);
        ProductPage.init().clickAddToCart();
        ProductPage.init().acceptAlert();
        MainPage.init().clickHome();
        MainPage.init().selectCategory(PH);
        MainPage.init().openProduct(NL);
        ProductPage.init().clickAddToCart();
        ProductPage.init().acceptAlert();
        MainPage.init().goToCart();
        com.codeborne.selenide.Selenide.sleep(2500);
        assertTrue(CartPage.init().isProductPresent(S6),
                "Samsung Galaxy S6 отсутствует в корзине");
        assertTrue(CartPage.init().isProductPresent(NL),
                "Nokia Lumia 1520 отсутствует в корзине");
    }
}
