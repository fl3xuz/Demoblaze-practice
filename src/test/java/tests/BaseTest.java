package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import utils.LoggerUtil;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

/**
 * Базовый класс для всех тестов.
 * Отвечает за жизненный цикл браузера и логирование.
 */
public class BaseTest {

    @BeforeEach
    public void setup(TestInfo testInfo) {
        LoggerUtil.log.info("--- Начинается выполнение теста: " + testInfo.getDisplayName() + " ---");
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10000;
        open("https://www.demoblaze.com/");
    }

    @AfterEach
    public void tearDown(TestInfo testInfo) {
        LoggerUtil.log.info("--- Завершен тест: " + testInfo.getDisplayName() + " ---");
        closeWebDriver();
    }
}