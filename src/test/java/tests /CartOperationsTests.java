package tests;

import org.junit.jupiter.api.*;
import pages.CartPage;
import pages.MainPage;
import pages.ProductPage;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static utils.Constants.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CartOperationsTests extends BaseTest {
  
    @Test @Order(4)
    @DisplayName("5: Проверка добавления нескольких товаров в корзину")
    public void Test05() {
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

    @Test @Order(5)
    @DisplayName("6: Проверка удаления товара из корзины")
    public void Test06() {
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

    @Test @Order(6)
    @DisplayName("Тест 9.1: Проверка пустой корзины")
    public void Test09_1() {
        MainPage.init().goToCart();
        assertTrue(CartPage.init().isCartEmpty(),
                "Корзина пустая");
    }
    @Test @Order(7)
    @DisplayName("Тест 9.2: Проверка добавления товара в пустую корзину")
    public void Test09_2() {
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
