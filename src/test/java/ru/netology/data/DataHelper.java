package ru.netology.data;

import lombok.Value;

public class DataHelper {

    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    @Value
    public static class FirstCard {
        private String firstCardNumber;
    }

    @Value
    public static class SecondCard {
        private String secondCardNumber;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static FirstCard getFirstCardInfo() {
        return new FirstCard("5559 0000 0000 0001");
    }

    public static SecondCard getSecondCardInfo() {
        return new SecondCard("5559 0000 0000 0002");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }
}