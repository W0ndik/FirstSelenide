package pages.selenide;

import io.qameta.allure.Step;

public class WikiMainPage extends BasePage{
    @Step("Ищу слово {query}")
    public WikiMainPage searchByWiki(String query){
        //todo
        return this;
    }
}
