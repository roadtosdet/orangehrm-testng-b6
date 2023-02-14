package testrunner;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import setup.Setup;
import utils.Utils;

import java.io.IOException;


public class PIMTestRunner extends Setup {
    DashboardPage dashboardPage;
    LoginPage loginPage;
    @BeforeTest
    public void doLogin() throws IOException, ParseException {
        loginPage=new LoginPage(driver);
        JSONObject userObject= Utils.loadJSONFile("./src/test/resources/User.json");
        String username= (String) userObject.get("username");
        String password= (String) userObject.get("password");
        driver.get("https://opensource-demo.orangehrmlive.com/");
        loginPage.doLogin(username, password);
    }
    @Test(priority = 2, description = "User can view exisitng employee list")
    public void searchEmployeeInfo() throws InterruptedException {
        dashboardPage=new DashboardPage(driver);
        Thread.sleep(3000);
        dashboardPage.menus.get(1).click();
        Thread.sleep(3000);
        String isUserFound= driver.findElements(By.className("oxd-text--span")).get(11).getText();
        Assert.assertTrue(isUserFound.contains("Records Found"));

    }
    @Test(priority = 3, description = "User can search employee by employee status")
    public void selectEmployeeStatus() throws InterruptedException {
        dashboardPage=new DashboardPage(driver);
        dashboardPage.dropdowns.get(0).click();
        dashboardPage.dropdowns.get(0).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(500);
        dashboardPage.dropdowns.get(0).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(500);
        dashboardPage.dropdowns.get(0).sendKeys(Keys.ENTER);
        driver.findElement(By.cssSelector("[type=submit]")).click();
        Utils.doScroll(driver,200);

    }
}
