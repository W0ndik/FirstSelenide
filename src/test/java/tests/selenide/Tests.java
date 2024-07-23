package tests.selenide;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import pages.selenide.*;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class Tests extends BaseTests {

    @Test
    public void firstSelenide(){
        open("https://www.google.ru/");
        $(By.name("q")).setValue("Открытие").pressEnter();
        //$$x("//*[text()='Результаты поиска']/following-sibling::*//a[@target='_blank']/h3").shouldHave(size(25));
        ElementsCollection resultSearch = $$x("//*[text()='Результаты поиска']/following-sibling::*//a[@target='_blank']/h3");
        resultSearch.find(text("Открытие (банк) - Википедия"));
        SelenideElement elemOtkr = $$x("//*[text()='Результаты поиска']/following-sibling::*//a[@target='_blank']/h3")
                .find(text("Банк Открытие: кредит наличными — под 4% каждому ..."));
        elemOtkr.click();
        switchTo().window(1);
        $x("//span[text()='Вас может заинтересовать']").scrollTo();
        $x("//a[text()='Все курсы']").click();
        System.out.println($x("//section[not(@style='display: none;')]//div[@class='card-rates__exchange']").getText());
    }

    @Feature("Проверка результатов поиска")
    @DisplayName("Проверка результатов поиска c помощью PO")
    @ParameterizedTest(name = "{displayName} {arguments}")
    @CsvSource({"USD"})
    public void otkrSelenideShort(String typeMoney) {
        open("https://www.google.ru/", GoogleMainPage.class)
                .search("Открытие")
                .goLinkByName("Банк Открытие: кредит наличными — под 4% каждому ...", OpenMainPage.class)
                .scrollSpan("Вас может заинтересовать")
                .goBlock("Все курсы")
                .checkBuySell(typeMoney);
    }

    @Feature("Проверка результатов поиска")
    @DisplayName("Проверка результатов поиска c помощью PO")
    @ParameterizedTest(name = "{displayName} {arguments}")
    @CsvSource({"USD"})
    public void otkrSelenideShort3(String typeMoney) {
        open("https://www.google.ru/");
        GoogleMainPage googleMainPage = new GoogleMainPage();
        googleMainPage.search("Открытие");
        GoogleSearchPage googleSearchResult = new GoogleSearchPage();
        googleSearchResult.goLinkByName("Банк Открытие: кредит наличными — под 4% каждому ...", OpenMainPage.class);
        OpenMainPage openMainPage = new OpenMainPage();
        openMainPage.scrollSpan("Вас может заинтересовать");
        openMainPage.goBlock("Все курсы");
        openMainPage.checkBuySell(typeMoney);
    }
}
