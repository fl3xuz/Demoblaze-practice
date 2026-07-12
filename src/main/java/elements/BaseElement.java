package elements;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;

public class BaseElement {
    protected final SelenideElement baseElement; // Cсылка на конкретный элемент на странице

    // приём элемента, который мы нашли на странице
    protected BaseElement(SelenideElement element) {
        this.baseElement = element;
    }

    // Метод из презентации: поиск элемента по XPath
    protected SelenideElement $x(String xpath) {
        return com.codeborne.selenide.Selenide.$x(xpath);
    }
}