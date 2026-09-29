package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    public void validLogin(){
        LoginPage loginPage=new LoginPage(driver);
        loginPage.enterUserName("admin@yourstore.com");
        loginPage.enterPassWord("admin");
        loginPage.clickLogin();
        Assert.assertEquals(driver.getTitle(),"Just a moment...");

    }

}
