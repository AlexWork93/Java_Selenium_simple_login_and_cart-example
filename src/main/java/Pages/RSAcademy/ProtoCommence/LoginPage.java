package Pages.RSAcademy.ProtoCommence;


import org.openqa.selenium.By;

public class LoginPage {

    public By usernameLocator = By.cssSelector("#username");
    public By passwordLocator = By.cssSelector("#password");
    public By roleSelectorLocator = By.cssSelector("select.form-control");
    public By termsCheckboxLocator = By.cssSelector("#terms");
    public By signInButtonLocator = By.cssSelector("#signInBtn");
    public By alertConfirmButtonLocator = By.cssSelector("#okayBtn");
    public By resumeAssistanceButtonLocator = By.cssSelector(".blinkingText");

    public By getUserOrAdminSwitchRadioButtonLocator(boolean isAdmin) {
        if (isAdmin)
            return By.xpath("//span[contains(text(),'Admin')]/../input");
        else
            return By.xpath("//span[contains(text(),'User')]/../input");
    }
}
