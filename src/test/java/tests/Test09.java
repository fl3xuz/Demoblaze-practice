package tests;

import org.junit.jupiter.api.*;
import pages.CartPage;
import pages.MainPage;
import pages.ProductPage;
import static utils.Constants.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class Test09 extends BaseTest {
    @Test @Order(1)
    @DisplayName("Тест 9.1: Проверка пустой корзины")
    public void TestEmptyCart() {
        MainPage.init().goToCart();
        assertTrue(CartPage.init().isCartEmpty(),
                "Корзина пустая");
    }
    @Test @Order(2)
    @DisplayName("Тест 9.2: Проверка добавления товара в пустую корзину")
    public void TestProductEmptyCart() {
        MainPage.init().selectCategory(PH);
        MainPage.init().openProduct(S6);
        ProductPage.init().clickAddToCart();
        ProductPage.init().acceptAlert();
        MainPage.init().goToCart();
        com.codeborne.selenide.Selenide.sleep(2500);
        assertTrue(CartPage.init().isProductPresent(S6),
        "Корзина пустая");
    }
}
