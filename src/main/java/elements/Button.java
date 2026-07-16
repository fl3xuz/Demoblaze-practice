package elements;

import com.codeborne.selenide.SelenideElement;

/**
 * Класс-компонент для кнопок.
 * Наследуется от BaseElement и расширяет его функциональность методами для работы с кнопками.
 */
public class Button extends BaseElement {
    public Button(SelenideElement element) {
        super(element);
    }

    public void click() {
        logAction("Клик");
        baseElement.click();
    }
}