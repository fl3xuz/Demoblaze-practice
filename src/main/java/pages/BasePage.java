package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class BasePage {
    protected SelenideElement baseType;

    // Обновление страницы
    public void refresh() {
        com.codeborne.selenide.Selenide.refresh();
    }
}