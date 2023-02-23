package Features;

import Steps.HerokuApp.NestedFramesSteps;
import Steps.RSAcademy.CartSteps;
import Steps.RSAcademy.LoginSteps;
import Steps.RSAcademy.RSAcademyDocumentRequestSteps;
import Steps.RSAcademy.ShopSteps;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.manage().window().setSize(new Dimension(1920, 1080));

        driver.get("https://rahulshettyacademy.com/loginpagePractise/");

        LoginSteps loginSteps = new LoginSteps(driver, wait);
        ShopSteps shopSteps = new ShopSteps(driver, wait);
        CartSteps cartSteps = new CartSteps(driver, wait);
        RSAcademyDocumentRequestSteps rsAcademyDocumentRequestSteps = new RSAcademyDocumentRequestSteps(driver, wait);
        NestedFramesSteps nestedFramesSteps = new NestedFramesSteps(driver, wait);

        //Test 1 User should be able to add all items available on the page to a cart
        driver.get("https://rahulshettyacademy.com/loginpagePractise/");
        loginSteps.performLoginWithFollowingData("rahulshettyacademy", "learning", false, "Consultant", true);
        shopSteps.addAllDisplayedItemsToCart();
        shopSteps.clickOnCheckoutButton();
        cartSteps.shouldSeeAmountOfItemsCart(4);

        //Test 2 User should be able to open academy page from login page
        driver.get("https://rahulshettyacademy.com/loginpagePractise/");
        loginSteps.shouldSeeSingInButton();
        loginSteps.openResumeAssistanceLink();
        Set<String> tabs = driver.getWindowHandles();
        Iterator<String> iterator = tabs.iterator();
        String loginPageIterator = iterator.next();
        String resumeAssistancePageIterator = iterator.next();
        driver.switchTo().window(resumeAssistancePageIterator);
        rsAcademyDocumentRequestSteps.shouldSeeDocumentsRequiredBanner();
        driver.close();
        driver.switchTo().window(loginPageIterator);
        loginSteps.shouldSeeSingInButton();

        //Test 3 User should be able to switch between iframes and see content on every iframe
        driver.get("https://the-internet.herokuapp.com/nested_frames");
        nestedFramesSteps.switchToIframeByName("left");
        nestedFramesSteps.shouldSeeTextContent("left");
        nestedFramesSteps.switchToIframeByName("middle");
        nestedFramesSteps.shouldSeeTextContent("middle");
        nestedFramesSteps.switchToIframeByName("right");
        nestedFramesSteps.shouldSeeTextContent("right");


        driver.quit();

    }
}
