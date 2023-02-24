package Steps.RSAcademy;

import Pages.RSAcademy.RSAcademyAutomationPractisePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class RSAcademyAutomationPractiseSteps {
    WebDriver driver;
    WebDriverWait wait;
    public RSAcademyAutomationPractiseSteps(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void clickOnCheckboxByIndex(int checkBoxIndex){
        RSAcademyAutomationPractisePage rsAcademyAutomationPractisePage = new RSAcademyAutomationPractisePage();
        wait.until(ExpectedConditions.visibilityOfElementLocated(rsAcademyAutomationPractisePage.alertButtonLocator));
        driver.findElements(rsAcademyAutomationPractisePage.checkboxLocator).get(checkBoxIndex).click();
    }

    public String getLabelOfSelectedCheckbox(){
        RSAcademyAutomationPractisePage rsAcademyAutomationPractisePage = new RSAcademyAutomationPractisePage();
        wait.until(ExpectedConditions.visibilityOfElementLocated(rsAcademyAutomationPractisePage.alertButtonLocator));
        List<WebElement> checkboxes = driver.findElements(rsAcademyAutomationPractisePage.checkboxLocator);
        List<WebElement> labels = driver.findElements(rsAcademyAutomationPractisePage.checkboxLabelLocator);
        String selectedCheckboxLabel = "";
        for (int i = 0; i < checkboxes.size(); i++) {
            if (checkboxes.get(i).isSelected()){
                selectedCheckboxLabel = labels.get(i).getText().trim();
                break;
            }
        }
        return selectedCheckboxLabel;
    }

    public void selectDropdownOptionByVisibleText(String text){
        RSAcademyAutomationPractisePage rsAcademyAutomationPractisePage = new RSAcademyAutomationPractisePage();
        wait.until(ExpectedConditions.visibilityOfElementLocated(rsAcademyAutomationPractisePage.alertButtonLocator));
        Select dropdownSelector = new Select(driver.findElement(rsAcademyAutomationPractisePage.dropdownLocator));
        dropdownSelector.selectByValue(text.toLowerCase());
    }

    public void fillAlertEditBoxWithText(String text){
        RSAcademyAutomationPractisePage rsAcademyAutomationPractisePage = new RSAcademyAutomationPractisePage();
        wait.until(ExpectedConditions.visibilityOfElementLocated(rsAcademyAutomationPractisePage.alertButtonLocator));
        driver.findElement(rsAcademyAutomationPractisePage.editBoxForAlertLocator).sendKeys(text);
    }

    public void clickOnAlertButton(){
        RSAcademyAutomationPractisePage rsAcademyAutomationPractisePage = new RSAcademyAutomationPractisePage();
        wait.until(ExpectedConditions.visibilityOfElementLocated(rsAcademyAutomationPractisePage.alertButtonLocator));
        driver.findElement(rsAcademyAutomationPractisePage.alertButtonLocator).click();
    }

    public void verifyAlertMessageContainsText(String text){
        wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertTrue(driver.switchTo().alert().getText().contains(text));
    }

    public void confirmAlert(){
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }
}
