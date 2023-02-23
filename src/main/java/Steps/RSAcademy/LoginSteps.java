package Steps.RSAcademy;

import Pages.RSAcademy.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class LoginSteps {
    WebDriver driver;
    WebDriverWait wait;

    public LoginSteps(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void performLoginWithFollowingData(String username, String password, boolean isAdmin, String role, boolean agreeWithTerms) {
        LoginPage loginPage = new LoginPage();
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginPage.usernameLocator));
        driver.findElement(loginPage.usernameLocator).sendKeys(username);
        driver.findElement(loginPage.passwordLocator).sendKeys(password);
        driver.findElement(loginPage.getUserOrAdminSwitchRadioButtonLocator(isAdmin)).click();
        if (!isAdmin) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(loginPage.alertConfirmButtonLocator));
            driver.findElement(loginPage.alertConfirmButtonLocator).click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(loginPage.alertConfirmButtonLocator));
        }
        Select roleSelector = new Select(driver.findElement(loginPage.roleSelectorLocator));
        roleSelector.selectByVisibleText(role);
        if (agreeWithTerms)
            driver.findElement(loginPage.termsCheckboxLocator).click();
        driver.findElement(loginPage.signInButtonLocator).click();
    }

    public void openResumeAssistanceLink() {
        LoginPage loginPage = new LoginPage();
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginPage.usernameLocator));
        driver.findElement(loginPage.resumeAssistanceButtonLocator).click();
    }

    public void shouldSeeSingInButton() {
        LoginPage loginPage = new LoginPage();
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginPage.usernameLocator));
        Assert.assertTrue(driver.findElement(loginPage.signInButtonLocator).isDisplayed());
    }
}
