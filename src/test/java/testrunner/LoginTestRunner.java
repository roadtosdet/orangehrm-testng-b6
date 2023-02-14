package testrunner;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;
import setup.Setup;
import utils.Utils;

import java.io.IOException;

public class LoginTestRunner extends Setup {
    LoginPage loginPage;

    @Test(priority = 1,description = "User can do login successfully")
    public void doLogin() throws IOException, ParseException {
        loginPage=new LoginPage(driver);
        JSONObject userObject= Utils.loadJSONFile("./src/test/resources/User.json");
        String username= (String) userObject.get("username");
        String password= (String) userObject.get("password");
        driver.get("https://opensource-demo.orangehrmlive.com/");
        loginPage.doLogin(username, password);

        WebElement headerText=driver.findElement(By.tagName("h6"));
        String header_actual= headerText.getText();
        String header_expected="Dashboard";
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(header_actual,header_expected);
        WebElement profileImageElement=driver.findElement(By.className("oxd-userdropdown-img"));
        softAssert.assertTrue(profileImageElement.isDisplayed());
        softAssert.assertAll();
    }

}
