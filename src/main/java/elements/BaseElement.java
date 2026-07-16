package elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import utils.LoggerUtil;

/**
 * Базовый класс для всех UI-элементов страницы.
 * Предоставляет общие методы для работы с SelenideElement
 * и служит родительским классом для всех специфических элементов (кнопки, поля ввода и т.д.).
 */
public class BaseElement {
    protected final SelenideElement baseElement;

    protected BaseElement(SelenideElement element) {
        this.baseElement = element;
    }

    protected void logAction(String action) {
        LoggerUtil.log.info("Действие: " + action + " на элементе: " + baseElement.getSearchCriteria());
    }

    public boolean isDisplayed() {
        return baseElement.is(Condition.visible);
    }
}