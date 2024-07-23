package pages.selenide;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.textCaseSensitive;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class OpenMainPage extends BasePage{

    @Step("Получаем курс валюты {nameMoney} для типа операции {typeOperation}")
    private float getCourse(String nameMoney, String typeOperation) {
        //sleep(10000);
        $x("//section[not(@style='display: none;')]//table//span[text()='"+nameMoney+"' and @class='currency__text']").shouldBe(visible);
        ElementsCollection trHeaders = $$(By.xpath("//section[not(@style='display: none;')]//div[@class='card-rates__exchange']//thead//th"));
        ElementsCollection trRows = $$(By.xpath("//section[not(@style='display: none;')]//div[@class='card-rates__exchange']//tbody/tr"));
        for (int i=0;i<trHeaders.size();++i){
            if(trHeaders.get(i).getText().contains(typeOperation))
                return Float.parseFloat(
                        trRows.find(textCaseSensitive(nameMoney))
                                .$$x("./td")
                                .get(i)
                                .getText()
                                .replace(",",".")
                );
        }
        Assertions.fail("Ну удалось найти курс валюты "+nameMoney+" для операции "+typeOperation);
        return 0;
    }

    @Step("Проверяем что курс покупки меньше курса продажи для валюты {nameMoney}")
    public OpenMainPage checkBuySell(String nameMoney){
        Assertions.assertTrue(
                getCourse(nameMoney, "Банк покупает")
                        <
                        getCourse(nameMoney,"Банк продаёт"),
                "Курс покупки "+nameMoney+" не меньше курса продажи"
        );
        return this;
    }

    @Step("Переходим на главную страницу открытия")
    public OpenMainPage scrollSpan(String text){
        $x("//span[text()='"+text+"']").scrollTo();
        return this;
    }

    @Step("Переходим на главную страницу открытия")
    public OpenMainPage goBlock(String text){
        $x("//a[text()='"+text+"']").click();
        return this;
    }

}
