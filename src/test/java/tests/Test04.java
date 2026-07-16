package tests;

import org.junit.jupiter.api.*;
import pages.CartPage;
import pages.MainPage;
import pages.ProductPage;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static utils.Constants.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Test04 extends BaseTest {
    @Test @Order(1)
    @DisplayName("4.1: Проверка сообщения после добавления товара")
    public void testAddToCartMessage() {
        MainPage.init().selectCategory("Phones");
        MainPage.init().openProduct(S6);
        ProductPage.init().prepareAlertIntercept();
        ProductPage.init().clickAddToCart();
        com.codeborne.selenide.Selenide.sleep(1000);
        String actualText = ProductPage.init().getCapturedAlertText();
        assertTrue(actualText.contains("Product added"),
                "Ожидалось сообщение 'Product added', но получили: " + actualText);
    }

    @Test @Order(2)
    @DisplayName("4.2: Отображение добавленного товара в корзине")
    public void testProductDisplayedInCart() {
        MainPage.init().selectCategory(PH);
        MainPage.init().openProduct(S6);
        ProductPage.init().clickAddToCart();
        ProductPage.init().acceptAlert();
        MainPage.init().goToCart();
        com.codeborne.selenide.Selenide.sleep(2500);
        assertTrue(CartPage.init().isProductPresent(S6),
                "Товар 'Samsung galaxy s6' отсутствует в таблице корзины");
    }

}