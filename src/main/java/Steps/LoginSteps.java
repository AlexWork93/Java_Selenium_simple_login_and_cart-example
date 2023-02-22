package Steps;

import Pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginSteps {
    WebDriver driver;
    WebDriverWait wait;
    public LoginSteps(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void performLoginWithFollowingData(String username, String password, boolean isAdmin, String role, boolean agreeWithTerms, boolean login){
        LoginPage loginPage = new LoginPage();
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginPage.usernameLocator));
        driver.findElement(loginPage.usernameLocator).sendKeys(username);
        driver.findElement(loginPage.passwordLocator).sendKeys(password);
        driver.findElement(loginPage.getUserOrAdminSwitchRadioButtonLocator(isAdmin)).click();
        Select roleSelector = new Select(driver.findElement(loginPage.roleSelectorLocator));
        roleSelector.selectByVisibleText(role);
        if (agreeWithTerms)
            driver.findElement(loginPage.termsCheckboxLocator).click();
        driver.findElement(loginPage.signInButtonLocator).click();
    }
}
