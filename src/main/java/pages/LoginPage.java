package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Log;

public class LoginPage {
    private WebDriver driver;
    @FindBy(id="Email")
    WebElement userNametextBox;

    @FindBy(id="Password")
    WebElement passwordTextBox;

    @FindBy(xpath = "//*[@id=\"main\"]/div/section/div/div[2]/div[1]/div/form/div[3]/button")
    WebElement loginButton;

//    private By userNametextBox=By.id("Email");
//    private By passwordTextBox=By.id("Password");
//    private By loginButton=By.xpath("//*[@id=\"main\"]/div/section/div/div[2]/div[1]/div/form/div[3]/button");

    public LoginPage(WebDriver d){
        this.driver=d;
        PageFactory.initElements(driver,this);
    }

    public void enterUserName(String username){
           userNametextBox.clear();
           userNametextBox.sendKeys(username);
//         driver.findElement(userNametextBox).clear();
//         driver.findElement(userNametextBox).sendKeys(username);
    }
    public void enterPassWord(String password){
          passwordTextBox.clear();
          passwordTextBox.sendKeys(password);
//        driver.findElement(passwordTextBox).clear();
//        driver.findElement(passwordTextBox).sendKeys(password);
    }
    public void clickLogin(){
        Log.info("Clicking Login button");
          loginButton.click();
//        driver.findElement(loginButton).click();
    }
}
