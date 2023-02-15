package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Utils;

import java.util.List;

public class PIMPage {
    @FindBy(className = "oxd-button--secondary")
    List<WebElement> button;
    @FindBy(css = "[type=submit]")
    WebElement btnSubmit;
    @FindBy(className = "employee-image")
    WebElement imgProfilePic;
    @FindBy(css = "[name=firstName]")
    WebElement txtFirstName;
    @FindBy(css = "[name=lastName]")
    WebElement txtLastName;
    @FindBy(className = "oxd-switch-input")
    WebElement btnToggle;
    @FindBy(tagName = "input")
    List<WebElement> txtInput;

    public PIMPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }
    public void createEmployee(String firstName, String lastName, String username, String password) throws InterruptedException {
        button.get(1).click();
        //Utils.waitForElement(driver,imgProfilePic,50);
        txtFirstName.sendKeys(firstName);
        txtLastName.sendKeys(lastName);
        //Utils.waitForElement(driver,btnToggle,50);
        Thread.sleep(3000);
        btnToggle.click();
        txtInput.get(7).sendKeys(username); //input username
        txtInput.get(10).sendKeys(password); //input password
        txtInput.get(11).sendKeys(password); //confirm password
        btnSubmit.click();

    }
}
