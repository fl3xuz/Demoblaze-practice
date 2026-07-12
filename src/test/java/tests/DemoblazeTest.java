package tests;

import org.junit.jupiter.api.*;
import pages.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class DemoblazeTest {

    @Test
    public void test01_PhonesCategory() {
        MainPage mainPage = new MainPage();
        mainPage.open();
        mainPage.selectCategory("Phones");
        $x("//a[text()='Samsung galaxy s6']").shouldBe(visible);
    }

    @Test
    public void test02_LaptopsCategory() {
        MainPage mainPage = new MainPage();
        mainPage.open();
        mainPage.selectCategory("Laptops");
        $x("//a[text()='Sony vaio i5']").shouldBe(visible);
    }

    @Test
    public void test03_OpenProduct() {
        MainPage mainPage = new MainPage();
        mainPage.open();
        mainPage.selectCategory("Phones");
        mainPage.openProduct("Samsung galaxy s6");
        $(".name").shouldHave(text("Samsung galaxy s6"));
    }

    @Test
    public void test04_CartNavigation() {
        MainPage mainPage = new MainPage();
        mainPage.open();
        mainPage.goToCart();
        $("h2").shouldHave(text("Products"));
    }

    @Test
    public void test05_AddToCartMessage() {
        MainPage mainPage = new MainPage();
        ProductPage productPage = new ProductPage();
        mainPage.open();
        mainPage.selectCategory("Phones");
        mainPage.openProduct("Samsung galaxy s6");
        productPage.addToCart();
    }

    @Test
    public void test06_ProductInCart() {
        MainPage mainPage = new MainPage();
        ProductPage productPage = new ProductPage();
        CartPage cartPage = new CartPage();
        mainPage.open();
        mainPage.selectCategory("Phones");
        mainPage.openProduct("Samsung galaxy s6");
        productPage.addToCart();
        mainPage.goToCart();
        assertTrue(cartPage.isProductPresent("Samsung galaxy s6"));
    }

    @Test
    public void test07_MultipleProductsInCart() {
        MainPage mainPage = new MainPage();
        ProductPage productPage = new ProductPage();
        CartPage cartPage = new CartPage();
        mainPage.open();

        mainPage.selectCategory("Phones");
        mainPage.openProduct("Samsung galaxy s6");
        productPage.addToCart();

        mainPage.open();
        mainPage.selectCategory("Phones");
        mainPage.openProduct("Nokia lumia 1520");
        productPage.addToCart();

        mainPage.goToCart();
        cartPage.isProductPresent("Samsung galaxy s6"); // Дополнительное ожидание
        cartPage.isProductPresent("Nokia lumia 1520");
        assertTrue(cartPage.isProductPresent("Samsung galaxy s6"));
        assertTrue(cartPage.isProductPresent("Nokia lumia 1520"));
    }

    @Test
    public void test08_DeleteFromCart() {
        MainPage mainPage = new MainPage();
        ProductPage productPage = new ProductPage();
        CartPage cartPage = new CartPage();
        mainPage.open();
        mainPage.selectCategory("Phones");
        mainPage.openProduct("Samsung galaxy s6");
        productPage.addToCart();
        mainPage.goToCart();
        cartPage.deleteProduct("Samsung galaxy s6");
        $x("//td[text()='Samsung galaxy s6']").shouldNot(exist);
    }

    @Test
    public void test09_OpenCheckout() {
        MainPage mainPage = new MainPage();
        CartPage cartPage = new CartPage();
        mainPage.open();
        mainPage.goToCart();
        cartPage.placeOrder();
        $("#orderModal").shouldBe(visible);
    }

    @Test
    public void test10_PurchaseCorrectData() {
        MainPage mainPage = new MainPage();
        ProductPage productPage = new ProductPage();
        CartPage cartPage = new CartPage();

        mainPage.open();
        mainPage.selectCategory("Phones");
        mainPage.openProduct("Samsung galaxy s6");
        productPage.addToCart();
        mainPage.goToCart();

        cartPage.placeOrder();
        cartPage.fillCheckoutForm("Test User", "Russia", "Saint Petersburg", "1234567890", "06", "2026");

        // Вместо поиска по классу, ищем текст "Thank you"
        $x("//h2[text()='Thank you for your purchase!']").shouldBe(visible);
        $(".confirm").click(); // Нажимаем OK в алерте
    }

    @Test
    public void test11_CancelOrder() {
        MainPage mainPage = new MainPage();
        CartPage cartPage = new CartPage();

        mainPage.open();
        mainPage.goToCart();
        cartPage.placeOrder();

        $("#orderModal").shouldBe(visible);

        cartPage.closeCheckoutForm();

        // Если окно не закрылось, пробуем еще раз
        if ($("#orderModal").isDisplayed()) {
            cartPage.closeCheckoutForm();
        }

        cartPage.waitForModalToClose();
    }

    @Test
    public void test12_EmptyCart() {
        MainPage mainPage = new MainPage();
        mainPage.open();
        mainPage.goToCart();
        $("#tbodyid").shouldBe(empty);
    }
}