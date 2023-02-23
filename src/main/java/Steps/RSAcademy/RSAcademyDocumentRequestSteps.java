package Steps.RSAcademy;

import Pages.RSAcademy.RSAcademyDocumentRequestPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class RSAcademyDocumentRequestSteps {
    WebDriver driver;
    WebDriverWait wait;
    public RSAcademyDocumentRequestSteps(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void shouldSeeDocumentsRequiredBanner(){
        RSAcademyDocumentRequestPage rsAcademyDocumentRequestPage = new RSAcademyDocumentRequestPage();
        wait.until(ExpectedConditions.visibilityOfElementLocated(rsAcademyDocumentRequestPage.joinNowButton));
        Assert.assertTrue(driver.findElement(rsAcademyDocumentRequestPage.documentsRequiredBanner).isDisplayed());
    }
}
