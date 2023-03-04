package Steps.RSAcademy;

import Pages.RSAcademy.RSAcademyGreenKartTopDealsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RSAcademyGreenKartTopDealsSteps {
    WebDriver driver;
    WebDriverWait wait;
    public RSAcademyGreenKartTopDealsSteps(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }


    public void verifyValueByItemName(String itemName, String expectedValueName, int expectedValue){
        RSAcademyGreenKartTopDealsPage rsAcademyGreenKartTopDealsPage = new RSAcademyGreenKartTopDealsPage();
        wait.until(ExpectedConditions.visibilityOfElementLocated(rsAcademyGreenKartTopDealsPage.tableHeader));
        if (driver.findElement(rsAcademyGreenKartTopDealsPage.firstPageButton).getAttribute("aria-disabled").equalsIgnoreCase("false"))
            driver.findElement(rsAcademyGreenKartTopDealsPage.firstPageButton).click();
        int actualValue = -1;
        while (actualValue == -1) {
            List<WebElement> tableRows = driver.findElements(rsAcademyGreenKartTopDealsPage.tableRow);
            List<WebElement> asd = tableRows.stream()
                    .filter(el -> el.findElements(rsAcademyGreenKartTopDealsPage.tableCell).get(0).getText().equalsIgnoreCase(itemName))
                    .collect(Collectors.toList());
            if (asd.size() > 0) {
                actualValue = Integer.parseInt(asd.get(0).findElements(rsAcademyGreenKartTopDealsPage.tableCell).get(getColumnIndexByHeader(expectedValueName)).getText());
            }
            if (driver.findElement(rsAcademyGreenKartTopDealsPage.nextPageButton).getAttribute("aria-disabled").equalsIgnoreCase("true"))
                break;
            driver.findElement(rsAcademyGreenKartTopDealsPage.nextPageButton).click();
        }
        Assert.assertEquals(actualValue, expectedValue);
    }

    private int getColumnIndexByHeader(String header){
        RSAcademyGreenKartTopDealsPage rsAcademyGreenKartTopDealsPage = new RSAcademyGreenKartTopDealsPage();
        wait.until(ExpectedConditions.visibilityOfElementLocated(rsAcademyGreenKartTopDealsPage.tableHeader));
        List<WebElement> headers = driver.findElements(rsAcademyGreenKartTopDealsPage.tableHeader);
        return IntStream.range(0, headers.size())
                .filter(i -> headers.get(i).getText().toLowerCase().contains(header.toLowerCase()))
                .findFirst()
                .orElse(-1);
    }

    public void verifyAmountItemsIsDisplayed(int expectedValue){
        RSAcademyGreenKartTopDealsPage rsAcademyGreenKartTopDealsPage = new RSAcademyGreenKartTopDealsPage();
        wait.until(ExpectedConditions.visibilityOfElementLocated(rsAcademyGreenKartTopDealsPage.tableHeader));
        if (driver.findElement(rsAcademyGreenKartTopDealsPage.firstPageButton).getAttribute("aria-disabled").equalsIgnoreCase("false"))
            driver.findElement(rsAcademyGreenKartTopDealsPage.firstPageButton).click();
        int actualValue = driver.findElements(rsAcademyGreenKartTopDealsPage.tableRow).size();
        Assert.assertEquals(actualValue, expectedValue);
    }

    public void setAmountOfDisplayedItems(int amount){
        RSAcademyGreenKartTopDealsPage rsAcademyGreenKartTopDealsPage = new RSAcademyGreenKartTopDealsPage();
        wait.until(ExpectedConditions.visibilityOfElementLocated(rsAcademyGreenKartTopDealsPage.tableHeader));
        Select amountItemsSelector = new Select(driver.findElement(rsAcademyGreenKartTopDealsPage.amountItemsInTableSelector));
        amountItemsSelector.selectByValue(String.valueOf(amount));
    }

    public void setFilterValue(String itemName){
        RSAcademyGreenKartTopDealsPage rsAcademyGreenKartTopDealsPage = new RSAcademyGreenKartTopDealsPage();
        wait.until(ExpectedConditions.visibilityOfElementLocated(rsAcademyGreenKartTopDealsPage.tableHeader));
        driver.findElement(rsAcademyGreenKartTopDealsPage.searchFieldInput).clear();
        driver.findElement(rsAcademyGreenKartTopDealsPage.searchFieldInput).sendKeys(itemName);
    }
}
