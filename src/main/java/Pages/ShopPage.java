package Pages;


import org.openqa.selenium.By;

public class ShopPage {
    public By addToCartButtonLocator = By.cssSelector(".card-footer button.btn");
    public By checkoutButtonLocator = By.cssSelector("#navbarResponsive a.btn-primary");


    public By getItemCardLocator(String itemName) {
        return By.xpath("//a[text()='" + itemName + "']/ancestor::app-card");
    }

}
