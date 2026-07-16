package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

/**
 * Базовый класс для всех страниц веб-приложения.
 * Содержит общие методы для управления состоянием страницы,
 * доступные на всех экранных формах.
 */
public class BasePage {
    protected SelenideElement baseType;

    public void waitForElement(String selector) {
        $(selector).shouldBe(Condition.visible);
    }

}