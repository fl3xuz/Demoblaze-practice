package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.CartPage;
import pages.MainPage;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test03 extends BaseTest {
    @Test
    @DisplayName("3: Проверка перехода в корзину через меню")
    public void testCartNavigation() {
        MainPage.init().goToCart();
        CartPage.init().waitForElement("#tbodyid");
        CartPage cartPage = CartPage.init();
        assertTrue(cartPage.isCartTableVisible(), "Таблица корзины не отображается");
        assertTrue(cartPage.areHeadersVisible(), "Колонки таблицы (Pic, Title, Price) не найдены");
        assertTrue(cartPage.isPlaceOrderButtonVisible(), "Кнопка 'Place Order' отсутствует");
    }
}
