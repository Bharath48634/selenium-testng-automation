package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;
    private By userNametextBox=By.id("Email");
    private By passwordTextBox=By.id("Password");
    private By loginButton=By.xpath("//*[@id=\"main\"]/div/section/div/div[2]/div[1]/div/form/div[3]/button");

    public LoginPage(WebDriver d){
        this.driver=d;
    }

    public void enterUserName(String username){
        driver.findElement(userNametextBox).clear();
         driver.findElement(userNametextBox).sendKeys(username);
    }
    public void enterPassWord(String password){
        driver.findElement(passwordTextBox).clear();
        driver.findElement(passwordTextBox).sendKeys(password);
    }
    public void clickLogin(){
        driver.findElement(loginButton).click();
    }
}
