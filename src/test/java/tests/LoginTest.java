package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ExcelUtils;
import utils.ExtentReportManager;
import utils.Log;

import java.io.IOException;

public class LoginTest extends BaseTest {

    @DataProvider(name = "LoginData")
    public Object[][] getLoginData() throws IOException {
        String filepath=System.getProperty("user.dir")+"/testData/TestData.xlsx";
        ExcelUtils.loadExcel(filepath,"Sheet1");
        int rowCount=ExcelUtils.getRowCount();
        Object[][] data=new Object[rowCount-1][2];
        for(int i=1;i<rowCount;i++){
            data[i - 1][0] = ExcelUtils.getCellData(i,0);//userName
            data[i - 1][1] = ExcelUtils.getCellData(i,1);//passWord

        }
        ExcelUtils.closeExcel();
        return data;
    }


    @Test(dataProvider = "LoginData")
    public void testValidLogin(String userName,String passWord){
        Log.info("Starting login test...");
        test= ExtentReportManager.createTest("Login Test-"+userName);
        LoginPage loginPage=new LoginPage(driver);
        test.info("Adding credentials");
        loginPage.enterUserName(userName);
        loginPage.enterPassWord( passWord);
        test.info("Clicking on Login button");
        loginPage.clickLogin();
        Assert.assertEquals(driver.getTitle(),"Just a moment...");
        test.pass("Login Successful");
    }

//    @Test
//    public void testInvalidLogin(){
//        Log.info("Starting login test...");
//        test= ExtentReportManager.createTest("Login Test");
//        LoginPage loginPage=new LoginPage(driver);
//        test.info("Adding credentials");
//        loginPage.enterUserName("admin@yourstore.com1234");
//        loginPage.enterPassWord("admin123");
//        test.info("Clicking on Login button");
//        loginPage.clickLogin();
//        Assert.assertEquals(driver.getTitle(),"Just a moment...123");
//        test.pass("Login Successful");
//
//    }

}
