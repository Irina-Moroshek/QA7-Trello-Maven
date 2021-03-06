package ru.stqa.selenium.tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.selenium.pages.*;
import ru.stqa.selenium.util.DataProviders;


public class LoginTests extends TestBase {
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;
    HomePageHelper homePage;

    @BeforeMethod
    public void initTests() {
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        boardsPage = PageFactory.initElements(driver, BoardsPageHelper.class);
        homePage = PageFactory.initElements(driver, HomePageHelper.class);
        homePage.waitUntilPageIsLoaded()
                .openLoginPage();
        loginPage.waitUntilPageIsLoaded();

    }

    @Test
    public void loginNegativeLoginEmpty() {
        log4j.startTestCase("loginNegativeLoginEmpty()");
        loginPage.loginNotAttlassian("", PASSWORD)
                .pressLoginButton();
        log4j.info("------Test case was finished---");
        Assert.assertEquals(loginPage.getErrorMessage(), "Missing email",
                "The text of the error message is not correct");
    }

    //Sel-04
    @Test
    public void loginNegativeNonexistentLoginAndPassword() {
        loginPage.loginNotAttlassian("wweq", PASSWORD);
        Assert.assertEquals(loginPage.getErrorMessage(), "There isn't an account for this username",
                "The error message is not 'There isn't an account for this username'");

    }

    //Sel-05
    @Test
    public void loginNegativeNonexistentPassword() {
        loginPage.loginAsAttlassian(LOGIN, "14675878");
        Assert.assertTrue(loginPage.getAttlassianErrorMessage().contains("Incorrect email address and / or password."),
                "The error message is not contains the text 'Incorrect email address and / or password.'");
    }

    //Sel-06
    @Test(dataProviderClass = DataProviders.class, dataProvider = "dataProviderFirst")
    public void loginPositiveTest(String login, String password) {
        loginPage.loginAsAttlassian(login, password);
        boardsPage.waitUntilPageIsLoaded();
        Assert.assertTrue(boardsPage.getBoadsIconName().equals("Boards"), "The text on the button is not 'Board'");
    }

    //Sel-17
    @Test(dataProviderClass = DataProviders.class, dataProvider = "dataProviderLoginIncorrect")
    public void loginNegativeLoginIncorrect(String login, String password, String errorMessage) {
        loginPage.loginNotAttlassian(login, password);
        Assert.assertTrue(loginPage.getErrorMessage().equals(errorMessage), "The error message is not ' " + errorMessage + " ' ");
    }

    //Sel-17 информация из файла
    @Test(dataProviderClass = DataProviders.class, dataProvider = "dataProviderLoginIncorrectFile")
    public void loginNegativeAndPasswordFile(String login, String password, String errorMessage) {
        loginPage.loginNotAttlassian(login, password);
        Assert.assertEquals(loginPage.getErrorMessage(), errorMessage,
                "The error message is not ' " + errorMessage + " ' ");

    }

    //здесь значения пароля формируются автоматически
    @Test(dataProviderClass = DataProviders.class, dataProvider = "dataProviderRandomPassword")
    public void loginNegativeAndPasswordRandom(String login, String password) {
        loginPage.loginNotAttlassian(login, password);
        Assert.assertEquals(loginPage.getErrorMessage(), "There isn't an account for this email",
                "The error message is not ' "+ "There isn't an account for this email ' ");

    }
}