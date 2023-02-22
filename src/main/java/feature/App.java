package feature;

import Steps.CartSteps;
import Steps.LoginSteps;
import Steps.ShopSteps;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.get("https://rahulshettyacademy.com/loginpagePractise/");

        LoginSteps loginSteps = new LoginSteps(driver, wait);
        ShopSteps shopSteps = new ShopSteps(driver, wait);
        CartSteps cartSteps = new CartSteps(driver, wait);

        loginSteps.performLoginWithFollowingData("rahulshettyacademy", "learning", true, "Student", true, true);
        shopSteps.addAllDisplayedItemsToCart();
        shopSteps.clickOnCheckoutButton();
    }
}
