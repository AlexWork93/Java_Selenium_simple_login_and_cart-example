package Steps.RSAcademy.ProtoCommerce;

import Pages.RSAcademy.ProtoCommence.CartPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CartSteps {
    WebDriver driver;
    WebDriverWait wait;
    public CartSteps(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void shouldSeeAmountOfItemsCart(int expectedAmount){
        CartPage cartPage = new CartPage();
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartPage.checkoutButtonLocator));
        Assert.assertEquals(driver.findElements(cartPage.itemsItCartLocator).size(), expectedAmount);
    }
}
