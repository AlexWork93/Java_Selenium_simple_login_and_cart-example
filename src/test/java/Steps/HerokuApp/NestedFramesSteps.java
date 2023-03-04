package Steps.HerokuApp;

import Pages.HerokuApp.NestedFramesPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class NestedFramesSteps {
    WebDriver driver;
    WebDriverWait wait;

    public NestedFramesSteps(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void switchToIframeByName(String iframeName) {
        NestedFramesPage nestedFramesPage = new NestedFramesPage();
        driver.switchTo().defaultContent();
        if (iframeName.equalsIgnoreCase("left") || iframeName.equalsIgnoreCase("right") || iframeName.equalsIgnoreCase("middle")) {
            driver.switchTo().frame(driver.findElement(nestedFramesPage.frameTop));
            driver.switchTo().frame(driver.findElement(nestedFramesPage.getIframeByName(iframeName)));
        } else
            driver.switchTo().frame(driver.findElement(nestedFramesPage.getIframeByName(iframeName)));
    }

    public void shouldSeeTextContent(String contentText) {
        NestedFramesPage nestedFramesPage = new NestedFramesPage();
        if (contentText.equalsIgnoreCase("middle")) {
            Assert.assertEquals(driver.findElement(nestedFramesPage.textContentForMiddleFrame).getText(), contentText.toUpperCase());
        }else {
            Assert.assertEquals(driver.findElement(nestedFramesPage.textContentForLeftAndRightFrame).getText(), contentText.toUpperCase());
        }
    }
}
