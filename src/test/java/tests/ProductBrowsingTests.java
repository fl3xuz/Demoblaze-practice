package tests;

import org.junit.jupiter.api.*;
import pages.MainPage;
import pages.ProductPage;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static utils.Constants.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductBrowsingTests extends BaseTest {

    @Test @Order(1)
    @DisplayName("1.1: Переход по категории Phones")
    public void Test01_1() {
        MainPage.init().selectCategory(PH);
        assertTrue(MainPage.init().isProductVisible(S6),
                "В списке товаров категории Phones не найден товар 'Samsung galaxy s6'");
    }

    @Test @Order(2)
    @DisplayName("1.2: Переход по категории Laptops")
    public void Test01_2() {
        MainPage.init().selectCategory(LAP);
        assertTrue(MainPage.init().isProductVisible(I5),
                "В списке товаров категории Laptops не найден товар 'Sony vaio i5'");
    }

    @Test @Order(3)
    @DisplayName("2: Проверка открытия страницы товара")
    public void Test02() {
        MainPage.init().selectCategory(PH);
        MainPage.init().openProduct(S6);
        MainPage.init().waitForElement(PRODUCT_NAME_SELECTOR);
        ProductPage productPage = ProductPage.init();
        assertTrue(productPage.isTitleCorrect(S6), "Название товара неверное");
        assertTrue(productPage.isPriceCorrect(PRICE), "Цена товара не $360");
        assertTrue(productPage.isDescriptionVisible(), "Описание товара не отображается");
        assertTrue(productPage.isAddToCartButtonVisible(), "Кнопка 'Add to cart' отсутствует");
    }
}