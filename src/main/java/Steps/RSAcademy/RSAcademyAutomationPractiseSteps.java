package Steps.RSAcademy;

import Pages.RSAcademy.RSAcademyAutomationPractisePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

public class RSAcademyAutomationPractiseSteps {
    WebDriver driver;
    WebDriverWait wait;

    public RSAcademyAutomationPractiseSteps(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void clickOnCheckboxByIndex(int checkBoxIndex) {
        RSAcademyAutomationPractisePage rsAcademyAutomationPractisePage = new RSAcademyAutomationPractisePage();
        wait.until(ExpectedConditions.visibilityOfElementLocated(rsAcademyAutomationPractisePage.alertButtonLocator));
        driver.findElements(rsAcademyAutomationPractisePage.checkboxLocator).get(checkBoxIndex).click();
    }

    public String getLabelOfSelectedCheckbox() {
        RSAcademyAutomationPractisePage rsAcademyAutomationPractisePage = new RSAcademyAutomationPractisePage();
        wait.until(ExpectedConditions.visibilityOfElementLocated(rsAcademyAutomationPractisePage.alertButtonLocator));
        List<WebElement> checkboxes = driver.findElements(rsAcademyAutomationPractisePage.checkboxLocator);
        List<WebElement> labels = driver.findElements(rsAcademyAutomationPractisePage.checkboxLabelLocator);
        String selectedCheckboxLabel = "";
        for (int i = 0; i < checkboxes.size(); i++) {
            if (checkboxes.get(i).isSelected()) {
                selectedCheckboxLabel = labels.get(i).getText().trim();
                break;
            }
        }
        return selectedCheckboxLabel;
    }

    public void selectDropdownOptionByVisibleText(String text) {
        RSAcademyAutomationPractisePage rsAcademyAutomationPractisePage = new RSAcademyAutomationPractisePage();
        wait.until(ExpectedConditions.visibilityOfElementLocated(rsAcademyAutomationPractisePage.alertButtonLocator));
        Select dropdownSelector = new Select(driver.findElement(rsAcademyAutomationPractisePage.dropdownLocator));
        dropdownSelector.selectByValue(text.toLowerCase());
    }

    public void fillAlertEditBoxWithText(String text) {
        RSAcademyAutomationPractisePage rsAcademyAutomationPractisePage = new RSAcademyAutomationPractisePage();
        wait.until(ExpectedConditions.visibilityOfElementLocated(rsAcademyAutomationPractisePage.alertButtonLocator));
        driver.findElement(rsAcademyAutomationPractisePage.editBoxForAlertLocator).sendKeys(text);
    }

    public void clickOnAlertButton() {
        RSAcademyAutomationPractisePage rsAcademyAutomationPractisePage = new RSAcademyAutomationPractisePage();
        wait.until(ExpectedConditions.visibilityOfElementLocated(rsAcademyAutomationPractisePage.alertButtonLocator));
        driver.findElement(rsAcademyAutomationPractisePage.alertButtonLocator).click();
    }

    public void verifyAlertMessageContainsText(String text) {
        wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertTrue(driver.switchTo().alert().getText().contains(text));
    }

    public void confirmAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    public void scrollToFooterUsingJavaScript() {
        RSAcademyAutomationPractisePage rsAcademyAutomationPractisePage = new RSAcademyAutomationPractisePage();
        Point footerCoordinates = driver.findElement(rsAcademyAutomationPractisePage.footer).getLocation();
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("window.scrollTo(" + footerCoordinates.x + ", " + footerCoordinates.y + ");");
    }

    public void verifyAvailabilityOfAllFooterURL() {
        RSAcademyAutomationPractisePage rsAcademyAutomationPractisePage = new RSAcademyAutomationPractisePage();
        SoftAssert softAssert = new SoftAssert();
        List<WebElement> footerAnchorTags = driver.findElement(rsAcademyAutomationPractisePage.footer).findElements(By.tagName("a"));
        List<String> footerLinks = footerAnchorTags.stream().map(el -> el.getAttribute("href"))
                .collect(Collectors.toList());
        footerLinks.stream().forEach(el -> {
            checkLinkAndUpdateAssertion(el, softAssert);
        });
        softAssert.assertAll();
    }

    private void checkLinkAndUpdateAssertion(String el, SoftAssert softAssert) {
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(el).openConnection();
            conn.setRequestMethod("HEAD");
            int code = conn.getResponseCode();
            softAssert.assertTrue(code <= 400, el + " link code is BROKEN");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
