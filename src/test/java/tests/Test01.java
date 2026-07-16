package tests;

import org.junit.jupiter.api.*;
import pages.MainPage;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static utils.Constants.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Test01 extends BaseTest {

    @Test @Order(1)
    @DisplayName("1.1: Переход по категории Phones")
    public void testPhonesCategory() {
        MainPage.init().selectCategory(PH);
        assertTrue(MainPage.init().isProductVisible(S6),
                "В списке товаров категории Phones не найден товар 'Samsung galaxy s6'");
    }

    @Test @Order(2)
    @DisplayName("1.2: Переход по категории Laptops")
    public void testLaptopsCategory() {
        MainPage.init().selectCategory(LAP);
        assertTrue(MainPage.init().isProductVisible(I5),
                "В списке товаров категории Laptops не найден товар 'Sony vaio i5'");
    }
}