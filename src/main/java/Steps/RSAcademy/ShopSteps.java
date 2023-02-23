package Steps.RSAcademy;

import Pages.RSAcademy.ShopPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class ShopSteps {
    WebDriver driver;
    WebDriverWait wait;
    public ShopSteps(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void addItemToCart(String itemName){
        ShopPage shopPage = new ShopPage();
        wait.until(ExpectedConditions.visibilityOfElementLocated(shopPage.addToCartButtonLocator));
        driver.findElement(shopPage.getItemCardLocator(itemName)).findElement(shopPage.addToCartButtonLocator).click();
    }
    public void addAllDisplayedItemsToCart(){
        ShopPage shopPage = new ShopPage();
        wait.until(ExpectedConditions.visibilityOfElementLocated(shopPage.addToCartButtonLocator));
        ArrayList<WebElement> buttons = new ArrayList<WebElement>(driver.findElements(shopPage.addToCartButtonLocator));
        for (WebElement el: buttons) {
            el.click();
        }
    }
    public void clickOnCheckoutButton(){
        ShopPage shopPage = new ShopPage();
        driver.findElement(shopPage.checkoutButtonLocator).click();
    }
}
