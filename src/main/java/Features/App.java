package Features;

import Steps.HerokuApp.NestedFramesSteps;
import Steps.JQueryUI.JQueryUIDatepickersSteps;
import Steps.RSAcademy.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws InterruptedException, IOException {
        ChromeOptions options = new ChromeOptions();
        //Accept insecure http and ssl certificates
        options.setAcceptInsecureCerts(true);
        //blocks all pop-up windows
        options.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking"));
        //Set download directory
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("download.default_directory", "project_downloads/");
        options.setExperimentalOption("prefs", prefs);

        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.manage().window().setSize(new Dimension(1920, 1080));

        driver.get("https://rahulshettyacademy.com/loginpagePractise/");

        LoginSteps loginSteps = new LoginSteps(driver, wait);
        ShopSteps shopSteps = new ShopSteps(driver, wait);
        CartSteps cartSteps = new CartSteps(driver, wait);
        RSAcademyDocumentRequestSteps rsAcademyDocumentRequestSteps = new RSAcademyDocumentRequestSteps(driver, wait);
        RSAcademyAutomationPractiseSteps rsAcademyAutomationPractiseSteps = new RSAcademyAutomationPractiseSteps(driver, wait);
        NestedFramesSteps nestedFramesSteps = new NestedFramesSteps(driver, wait);
        JQueryUIDatepickersSteps jQueryUIDatepickersSteps = new JQueryUIDatepickersSteps(driver, wait);


//        //Test 1 User should be able to add all items available on the page to a cart
//        driver.get("https://rahulshettyacademy.com/loginpagePractise/");
//        loginSteps.performLoginWithFollowingData("rahulshettyacademy", "learning", false, "Consultant", true);
//        shopSteps.addAllDisplayedItemsToCart();
//        shopSteps.clickOnCheckoutButton();
//        cartSteps.shouldSeeAmountOfItemsCart(4);
//
//        //Test 2 User should be able to open academy page from login page
//        driver.get("https://rahulshettyacademy.com/loginpagePractise/");
//        loginSteps.shouldSeeSingInButton();
//        loginSteps.openResumeAssistanceLink();
//        Set<String> tabs = driver.getWindowHandles();
//        Iterator<String> iterator = tabs.iterator();
//        String loginPageIterator = iterator.next();
//        String resumeAssistancePageIterator = iterator.next();
//        driver.switchTo().window(resumeAssistancePageIterator);
//        rsAcademyDocumentRequestSteps.shouldSeeDocumentsRequiredBanner();
//        driver.close();
//        driver.switchTo().window(loginPageIterator);
//        loginSteps.shouldSeeSingInButton();
//
//        //Test 3 User should be able to switch between iframes and see content on every iframe
//        driver.get("https://the-internet.herokuapp.com/nested_frames");
//        nestedFramesSteps.switchToIframeByName("left");
//        nestedFramesSteps.shouldSeeTextContent("left");
//        nestedFramesSteps.switchToIframeByName("middle");
//        nestedFramesSteps.shouldSeeTextContent("middle");
//        nestedFramesSteps.switchToIframeByName("right");
//        nestedFramesSteps.shouldSeeTextContent("right");
//
//        //Test 4 User should be able to perform following actions
//        // Select any checkbox
//        // Grab the label of the selected checkbox
//        // Select an option in dropdown. Here options to select should become from the step 2
//        // Enter step 2 grabbed label text in edit box
//        // Click alert and verify if text grabbed from the step 2 is present in the Pop-Up message
//
//        driver.get("https://www.rahulshettyacademy.com/AutomationPractice/");
//        rsAcademyAutomationPractiseSteps.clickOnCheckboxByIndex(2);
//        String labelOfSelectedCheckbox = rsAcademyAutomationPractiseSteps.getLabelOfSelectedCheckbox();
//        rsAcademyAutomationPractiseSteps.selectDropdownOptionByVisibleText(labelOfSelectedCheckbox);
//        rsAcademyAutomationPractiseSteps.fillAlertEditBoxWithText(labelOfSelectedCheckbox);
//        rsAcademyAutomationPractiseSteps.clickOnAlertButton();
//        rsAcademyAutomationPractiseSteps.verifyAlertMessageContainsText(labelOfSelectedCheckbox);
//        rsAcademyAutomationPractiseSteps.confirmAlert();
//
//        //Test 5 User should be able to interact with different types of date pickers
//        driver.get("https://jqueryui.com/datepicker/");
//        jQueryUIDatepickersSteps.switchToDatepickerIframe();
//        jQueryUIDatepickersSteps.selectDateInDatepickerByMountAndDay("january", "2");
//        jQueryUIDatepickersSteps.verifyDayIsSelectedInDatepicker("january", "2");
//        jQueryUIDatepickersSteps.selectDateInDatepickerByMountAndDay( "may", "10");
//        jQueryUIDatepickersSteps.verifyDayIsSelectedInDatepicker("may", "10");
//        driver.navigate().refresh();
//        jQueryUIDatepickersSteps.switchToDatepickerIframe();
//        String[] datapickerResult = jQueryUIDatepickersSteps.selectDateInDatepickerByAmountOfDays(5);
//        jQueryUIDatepickersSteps.verifyDayIsSelectedInDatepicker( datapickerResult[0], datapickerResult[1]);
//        driver.navigate().refresh();
//        jQueryUIDatepickersSteps.switchToDatepickerIframe();
//        datapickerResult = jQueryUIDatepickersSteps.selectDateInDatepickerByAmountOfDays(-5);
//        jQueryUIDatepickersSteps.verifyDayIsSelectedInDatepicker( datapickerResult[0], datapickerResult[1]);
//
//        //Test 6 User should be able to take a screenshot

//        File src =  ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//        FileUtils.copyFile(src, new File("project_downloads/screen.png"));

          //Test 7
        driver.get("https://www.rahulshettyacademy.com/AutomationPractice/");
        rsAcademyAutomationPractiseSteps.scrollToFooterUsingJavaScript();
        rsAcademyAutomationPractiseSteps.verifyAvailabilityOfAllFooterURL();





        driver.quit();

    }
}
