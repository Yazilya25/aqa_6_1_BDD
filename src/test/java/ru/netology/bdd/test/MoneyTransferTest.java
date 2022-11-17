package ru.netology.bdd.test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.bdd.data.DataHelper;
import ru.netology.bdd.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class MoneyTransferTest {

    @BeforeEach
    void setup() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
    }

    @Test
    void shouldTransferMoneyFromSecondToFirst() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCode(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        int balanceFirstBeforeTransfer = dashboardPage.getCardBalance(0);
        int balanceSecondBeforeTransfer = dashboardPage.getCardBalance(1);
        var moneyTransferPage = dashboardPage.cardRefill(0);
        int amount = 1000;
        moneyTransferPage.moneyTransfer(amount, DataHelper.getSecondCardInfo().getCardNumber());
        Assertions.assertEquals(balanceFirstBeforeTransfer + amount, dashboardPage.getCardBalance(0));
        Assertions.assertEquals(balanceSecondBeforeTransfer - amount, dashboardPage.getCardBalance(1));

    }

    @Test
    void shouldTransferMoneyFromSecondToFirst1() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCode(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        int balanceFirstBeforeTransfer = dashboardPage.getCardBalance(0);
        int balanceSecondBeforeTransfer = dashboardPage.getCardBalance(1);
        var moneyTransferPage = dashboardPage.cardRefill(0);
        int amount = 1;
        moneyTransferPage.moneyTransfer(amount, DataHelper.getSecondCardInfo().getCardNumber());
        Assertions.assertEquals(balanceFirstBeforeTransfer + amount, dashboardPage.getCardBalance(0));
        Assertions.assertEquals(balanceSecondBeforeTransfer - amount, dashboardPage.getCardBalance(1));

    }

    @Test
    void shouldTransferMoneyFromFirstToSecond() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCode(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        int balanceFirstBeforeTransfer = dashboardPage.getCardBalance(0);
        int balanceSecondBeforeTransfer = dashboardPage.getCardBalance(1);
        var moneyTransferPage = dashboardPage.cardRefill(1);
        int amount = 5000;
        moneyTransferPage.moneyTransfer(amount, DataHelper.getFirstCardInfo().getCardNumber());
        Assertions.assertEquals(balanceFirstBeforeTransfer - amount, dashboardPage.getCardBalance(0));
        Assertions.assertEquals(balanceSecondBeforeTransfer + amount, dashboardPage.getCardBalance(1));

    }

    @Test
    void shouldTransferMoneyFromFirstToSecond1() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCode(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        int balanceFirstBeforeTransfer = dashboardPage.getCardBalance(0);
        int balanceSecondBeforeTransfer = dashboardPage.getCardBalance(1);
        var moneyTransferPage = dashboardPage.cardRefill(1);
        int amount = 1;
        moneyTransferPage.moneyTransfer(amount, DataHelper.getFirstCardInfo().getCardNumber());
        Assertions.assertEquals(balanceFirstBeforeTransfer - amount, dashboardPage.getCardBalance(0));
        Assertions.assertEquals(balanceSecondBeforeTransfer + amount, dashboardPage.getCardBalance(1));

    }

    @Test
    void shouldTransferMoneyFromFirstToFirst() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCode(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        int balanceFirstBeforeTransfer = dashboardPage.getCardBalance(0);
        int balanceSecondBeforeTransfer = dashboardPage.getCardBalance(1);
        var moneyTransferPage = dashboardPage.cardRefill(0);
        int amount = 999;
        moneyTransferPage.moneyTransfer(amount, DataHelper.getFirstCardInfo().getCardNumber());
        Assertions.assertEquals(balanceFirstBeforeTransfer, dashboardPage.getCardBalance(0));
        Assertions.assertEquals(balanceSecondBeforeTransfer, dashboardPage.getCardBalance(1));
    }

    @Test
    void shouldNotTransferMoneyFromSecondToSecond() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCode(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        int balanceFirstBeforeTransfer = dashboardPage.getCardBalance(0);
        int balanceSecondBeforeTransfer = dashboardPage.getCardBalance(1);
        var moneyTransferPage = dashboardPage.cardRefill(1);
        int amount = 999;
        moneyTransferPage.moneyTransfer(amount, DataHelper.getSecondCardInfo().getCardNumber());
        Assertions.assertEquals(balanceFirstBeforeTransfer, dashboardPage.getCardBalance(0));
        Assertions.assertEquals(balanceSecondBeforeTransfer, dashboardPage.getCardBalance(1));
    }

    @Test
    void shouldNotTransferMoneyFromSecondToFirstOverLimit() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCode(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        int balanceFirstBeforeTransfer = dashboardPage.getCardBalance(0);
        int balanceSecondBeforeTransfer = dashboardPage.getCardBalance(1);
        var moneyTransferPage = dashboardPage.cardRefill(0);
        moneyTransferPage.moneyTransfer(balanceSecondBeforeTransfer + 10000, DataHelper.getSecondCardInfo().getCardNumber());
        moneyTransferPage.shouldErrorNotification();
        Assertions.assertEquals(balanceFirstBeforeTransfer, dashboardPage.getCardBalance(0));
        Assertions.assertEquals(balanceSecondBeforeTransfer, dashboardPage.getCardBalance(1));

    }

}