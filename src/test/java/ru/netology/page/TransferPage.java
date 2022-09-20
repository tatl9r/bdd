package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class TransferPage {

    private SelenideElement sum = $("[data-test-id='amount'] input");
    private SelenideElement from = $("[data-test-id='from'] input");

    private SelenideElement transfer = $("[data-test-id='action-transfer']");


    public DashboardPage firstCardReplenishment(DataHelper.SecondCard secondCard, String amount) {
        sum.setValue(amount);
        from.setValue(secondCard.getSecondCardNumber());
        transfer.click();
        return new DashboardPage();
    }

    public DashboardPage secondCardReplenishment(DataHelper.FirstCard firstCard, String amount) {
        sum.setValue(amount);
        from.setValue(firstCard.getFirstCardNumber());
        transfer.click();
        return new DashboardPage();
    }
}
