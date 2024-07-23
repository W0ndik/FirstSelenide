package pages.selenide;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class GoogleSearchPage extends BasePage{

    @Step("Поереходим по имени ссылки {linkName}")
    public <T extends BasePage> T goLinkByName(String linkName, Class<T> typeNextPage){
        $$x("//*[text()='Результаты поиска']/following-sibling::*//a[@target='_blank']/h3")
                .find(text(linkName))
                .click();
        switchTo().window(1);
        return typeNextPage.cast(page(typeNextPage));
    }
}
