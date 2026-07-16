package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.MainPage;
import pages.ProductPage;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static utils.Constants.*;

public class Test02 extends BaseTest {
    @Test
    @DisplayName("2: Проверка открытия страницы товара")
    public void testOpenProductCard() {
        MainPage.init().selectCategory(PH);
        MainPage.init().openProduct(S6);
        MainPage.init().waitForElement(".name");
        ProductPage productPage = ProductPage.init();
        assertTrue(productPage.isTitleCorrect(S6), "Название товара неверное");
        assertTrue(productPage.isPriceCorrect(PRICE), "Цена товара не $360");
        assertTrue(productPage.isDescriptionVisible(), "Описание товара не отображается");
        assertTrue(productPage.isAddToCartButtonVisible(), "Кнопка 'Add to cart' отсутствует");
    }
}