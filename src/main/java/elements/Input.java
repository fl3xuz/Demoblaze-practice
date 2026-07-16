package elements;

import com.codeborne.selenide.SelenideElement;

/**
 * Класс, представляющий поле ввода текста на веб-странице.
 * Наследуется от BaseElement и расширяет его функциональность методами для работы с вводом данных.
 */
public class Input extends BaseElement {
    public Input(SelenideElement element) {
        super(element);
    }

    public void fill(String value) {
        logAction("Ввод текста '" + value + "'");
        baseElement.setValue(value);
    }
}