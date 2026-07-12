package elements;

import com.codeborne.selenide.SelenideElement;

public class Input extends BaseElement {

    // приём найденного элемента и передача его "родителю" (BaseElement)
    public Input(SelenideElement element) {
        super(element);
    }

    // заполнение поля текстом
    public void fill(String value) {
        baseElement.setValue(value);
    }
}