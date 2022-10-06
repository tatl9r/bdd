package ru.netology.test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class InternetBankTest {
    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void replenishmentOfTheFirstCard() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");

        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var card = DataHelper.getSecondCardInfo(authInfo);
        int currentBalanceFirstCard = dashboardPage.getCardBalance(0);
        int currentBalanceSecondCard = dashboardPage.getCardBalance(1);
        var transferPage = dashboardPage.transferFirstCardBalance();
        transferPage.cardReplenishment(DataHelper.getFirstCardInfo(authInfo).getCardNumber(), 1000);

        int amount = 1000;
        int expected = currentBalanceFirstCard + amount;
        int actual = dashboardPage.getCardBalance(0);
        Assertions.assertEquals(expected, actual);
        int expected2 = currentBalanceSecondCard - amount;
        int actual2 = dashboardPage.getCardBalance(1);
        Assertions.assertEquals(expected2, actual2);
    }


    @Test
    void replenishmentOfTheSecondCard() {

       Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");

        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var card = DataHelper.getFirstCardInfo(authInfo);
        int currentBalanceFirstCard = dashboardPage.getCardBalance(0);
        int currentBalanceSecondCard = dashboardPage.getCardBalance(1);
        var transferPage = dashboardPage.transferSecondCardBalance();
        transferPage.cardReplenishment(DataHelper.getSecondCardInfo(authInfo).getCardNumber(), 5000);

        int amount = 5000;
        int expected = currentBalanceFirstCard - amount;
        int actual = dashboardPage.getCardBalance(0);
        Assertions.assertEquals(expected, actual);
        int expected2 = currentBalanceSecondCard + amount;
        int actual2 = dashboardPage.getCardBalance(1);
        Assertions.assertEquals(expected2, actual2);
    }


    @Test
    void replenishmentOverLimit() {

        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");

        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var card = DataHelper.getSecondCardInfo(authInfo);
        var transferPage = dashboardPage.transferFirstCardBalance();
        transferPage.cardReplenishment(DataHelper.getFirstCardInfo(authInfo).getCardNumber(), 500000);
        transferPage.errorMessage();

    }

}
