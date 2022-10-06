package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {

    private SelenideElement sum = $("[data-test-id='amount'] .input__control");
    private SelenideElement heading = $("//h1[contains(text(), 'Пополнение карты')]");
    private SelenideElement from = $("[data-test-id='from'] input");
    private SelenideElement transfer = $("[data-test-id='action-transfer']");
    private static SelenideElement errorMessage = $(".notification__title");

    public TransferPage() {
        heading.shouldBe(Condition.visible);
    }

    public DashboardPage cardReplenishment(String Card, int amount) {
        sum.setValue(String.valueOf(amount));
        from.setValue(Card);
        transfer.click();
        return new DashboardPage();

    }

    public static void errorMessage(){
        errorMessage.shouldHave(exactText("Ошибка"))
                .shouldBe(Condition.visible);
    }
}

