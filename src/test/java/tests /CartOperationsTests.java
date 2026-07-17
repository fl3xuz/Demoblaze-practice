package tests;

import org.junit.jupiter.api.*;
import pages.CartPage;
import pages.MainPage;
import pages.ProductPage;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static utils.Constants.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CartOperationsTests extends BaseTest {
  @Test @Order(1)
    @DisplayName("3: Проверка перехода в корзину через меню")
    public void Test03() {
        MainPage.init().goToCart();
        CartPage.init().waitForElement(TBODY_ID);
        CartPage cartPage = CartPage.init();
        assertTrue(cartPage.isCartTableVisible(), "Таблица корзины не отображается");
        assertTrue(cartPage.areHeadersVisible(), "Колонки таблицы (Pic, Title, Price) не найдены");
        assertTrue(cartPage.isPlaceOrderButtonVisible(), "Кнопка 'Place Order' отсутствует");
    }

    @Test @Order(2)
    @DisplayName("4.1: Проверка сообщения после добавления товара")
    public void Test04_1() {
        MainPage.init().selectCategory(PH);
        MainPage.init().openProduct(S6);
        ProductPage.init().prepareAlertIntercept();
        ProductPage.init().clickAddToCart();
        com.codeborne.selenide.Selenide.sleep(1000);
        String actualText = ProductPage.init().getCapturedAlertText();
        assertTrue(actualText.contains(MSG_PRODUCT_ADDED),
                "Ожидалось сообщение 'Product added', но получили: " + actualText);
    }

    @Test @Order(3)
    @DisplayName("4.2: Отображение добавленного товара в корзине")
    public void Test04_2() {
        MainPage.init().selectCategory(PH);
        MainPage.init().openProduct(S6);
        ProductPage.init().clickAddToCart();
        ProductPage.init().acceptAlert();
        MainPage.init().goToCart();
        com.codeborne.selenide.Selenide.sleep(2500);
        assertTrue(CartPage.init().isProductPresent(S6),
                "Товар 'Samsung galaxy s6' отсутствует в таблице корзины");
    }
  
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
