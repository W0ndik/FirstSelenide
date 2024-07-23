package pages.selenide;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class GoogleMainPage extends BasePage{

    @Step("Ищу слово {query}")
    public GoogleSearchPage search(String query){
        $(By.name("q")).setValue(query).pressEnter();
        return page(GoogleSearchPage.class);
    }

}
