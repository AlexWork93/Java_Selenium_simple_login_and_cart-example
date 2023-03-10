package Features;

import Steps.HerokuApp.NestedFramesSteps;
import Steps.JQueryUI.JQueryUIDatepickersSteps;
import Steps.RSAcademy.ProtoCommerce.CartSteps;
import Steps.RSAcademy.ProtoCommerce.LoginSteps;
import Steps.RSAcademy.ProtoCommerce.ShopSteps;
import Steps.RSAcademy.RSAcademyAutomationPractiseSteps;
import Steps.RSAcademy.RSAcademyDocumentRequestSteps;
import Steps.RSAcademy.RSAcademyGreenKartTopDealsSteps;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.*;

public class TestNGClass {
    ChromeOptions options;
    WebDriver driver;
    WebDriverWait wait;
    LoginSteps loginSteps;
    ShopSteps shopSteps;
    CartSteps cartSteps;
    RSAcademyDocumentRequestSteps rsAcademyDocumentRequestSteps;
    RSAcademyAutomationPractiseSteps rsAcademyAutomationPractiseSteps;
    NestedFramesSteps nestedFramesSteps;
    JQueryUIDatepickersSteps jQueryUIDatepickersSteps;
    RSAcademyGreenKartTopDealsSteps rsAcademyGreenKartTopDealsSteps;

    @BeforeClass(groups = {"Smoke"})
    public void BeforeClass() {
        System.out.println("Before class");
        options = new ChromeOptions();
        //Accept insecure http and ssl certificates
        options.setAcceptInsecureCerts(true);
        //blocks all pop-up windows
        options.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking"));
        //Set download directory
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("download.default_directory", "project_downloads/");
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.manage().window().setSize(new Dimension(1920, 1080));

        loginSteps = new LoginSteps(driver, wait);
        shopSteps = new ShopSteps(driver, wait);
        cartSteps = new CartSteps(driver, wait);
        rsAcademyDocumentRequestSteps = new RSAcademyDocumentRequestSteps(driver, wait);
        rsAcademyAutomationPractiseSteps = new RSAcademyAutomationPractiseSteps(driver, wait);
        nestedFramesSteps = new NestedFramesSteps(driver, wait);
        jQueryUIDatepickersSteps = new JQueryUIDatepickersSteps(driver, wait);
        rsAcademyGreenKartTopDealsSteps = new RSAcademyGreenKartTopDealsSteps(driver, wait);
    }

    @Parameters({"URLLoginPagePractice"})
    @Test(groups = {"Smoke"})
    public void test01_userShouldBeAbleToAddAllAvailableItemsFromThePageToCart(String URL) {
        System.out.println("Test 1 User should be able to add all items available on the page to a cart");
        driver.get(URL);
        loginSteps.performLoginWithFollowingData("rahulshettyacademy", "learning", false, "Consultant", true);
        shopSteps.addAllDisplayedItemsToCart();
        shopSteps.clickOnCheckoutButton();
        cartSteps.shouldSeeAmountOfItemsCart(4);
    }

    @Parameters({"URLLoginPagePractice"})
    @Test(groups = {"Smoke"})
    public void test02_userShouldBeAbleToOpenAnAcademyPageFromLoginPageInNewTab(String URL) {
        System.out.println("Test 2 User should be able to open academy page from login page");
        driver.get(URL);
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
    }

    @Test
    public void test03_userShouldBeAbleToSwitchBetweenFramesAndSeeCorrectInformation() {
        System.out.println("Test 3 User should be able to switch between iframes and see content on every iframe");
        driver.get("https://the-internet.herokuapp.com/nested_frames");
        nestedFramesSteps.switchToIframeByName("left");
        nestedFramesSteps.shouldSeeTextContent("left");
        nestedFramesSteps.switchToIframeByName("middle");
        nestedFramesSteps.shouldSeeTextContent("middle");
        nestedFramesSteps.switchToIframeByName("right");
        nestedFramesSteps.shouldSeeTextContent("right");
    }

    @Test
    public void test04_assignment() {
        //this test is an assignment task from course
        System.out.println("Test 4 User should be able to perform following actions");
        //Select any checkbox
        //Grab the label of the selected checkbox
        //Select an option in dropdown. Here options to select should become from the step 2
        //Enter step 2 grabbed label text in edit box
        //Click alert and verify if text grabbed from the step 2 is present in the Pop-Up message

        driver.get("https://www.rahulshettyacademy.com/AutomationPractice/");
        rsAcademyAutomationPractiseSteps.clickOnCheckboxByIndex(2);
        String labelOfSelectedCheckbox = rsAcademyAutomationPractiseSteps.getLabelOfSelectedCheckbox();
        rsAcademyAutomationPractiseSteps.selectDropdownOptionByVisibleText(labelOfSelectedCheckbox);
        rsAcademyAutomationPractiseSteps.fillAlertEditBoxWithText(labelOfSelectedCheckbox);
        rsAcademyAutomationPractiseSteps.clickOnAlertButton();
        rsAcademyAutomationPractiseSteps.verifyAlertMessageContainsText(labelOfSelectedCheckbox);
        rsAcademyAutomationPractiseSteps.confirmAlert();
    }

    @Test
    public void test05_userShouldBeAbleToInteractWithDifferentTypesOfDatePickers() throws InterruptedException {
        System.out.println("Test 5 User should be able to interact with different types of date pickers");
        driver.get("https://jqueryui.com/datepicker/");
        jQueryUIDatepickersSteps.switchToDatepickerIframe();
        jQueryUIDatepickersSteps.selectDateInDatepickerByMountAndDay("january", "2");
        jQueryUIDatepickersSteps.verifyDayIsSelectedInDatepicker("january", "2");
        jQueryUIDatepickersSteps.selectDateInDatepickerByMountAndDay("may", "10");
        jQueryUIDatepickersSteps.verifyDayIsSelectedInDatepicker("may", "10");
        //this page refresh returns datepicker to initial state
        driver.navigate().refresh();
        jQueryUIDatepickersSteps.switchToDatepickerIframe();
        String[] datapickerResult = jQueryUIDatepickersSteps.selectDateInDatepickerByAmountOfDays(5);
        jQueryUIDatepickersSteps.verifyDayIsSelectedInDatepicker(datapickerResult[0], datapickerResult[1]);
        //this page refresh returns datepicker to initial state
        driver.navigate().refresh();
        jQueryUIDatepickersSteps.switchToDatepickerIframe();
        datapickerResult = jQueryUIDatepickersSteps.selectDateInDatepickerByAmountOfDays(-5);
        jQueryUIDatepickersSteps.verifyDayIsSelectedInDatepicker(datapickerResult[0], datapickerResult[1]);
    }

    @Test
    public void test06_userShouldBeAbleToTakeAScreenShot() throws IOException {
        System.out.println("Test 6 User should be able to take a screenshot");
        driver.get("https://www.rahulshettyacademy.com/AutomationPractice/");
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src, new File("project_downloads/screen.png"));
    }

    @Test
    public void test07_userShouldBeAbleToClickOnEveryPageIbFooterWithoutErrors() {
        System.out.println("Test 7");
        //This scenario should be failed because list of links contains broken link
        driver.get("https://www.rahulshettyacademy.com/AutomationPractice/");
        rsAcademyAutomationPractiseSteps.scrollToFooterUsingJavaScript();
        rsAcademyAutomationPractiseSteps.verifyAvailabilityOfAllFooterURL();
    }

    @Test
    public void test08_userShouldBeAbleToVerifyTableDataAndUsePagination() {
        System.out.println("Test 8 User should be able to verify table data and use pagination");
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
        rsAcademyGreenKartTopDealsSteps.verifyValueByItemName("Banana", "Price", 87);
        rsAcademyGreenKartTopDealsSteps.verifyValueByItemName("Strawberry", "Discount price", 15);
        rsAcademyGreenKartTopDealsSteps.verifyAmountItemsIsDisplayed(5);
        rsAcademyGreenKartTopDealsSteps.setAmountOfDisplayedItems(10);
        rsAcademyGreenKartTopDealsSteps.verifyAmountItemsIsDisplayed(10);
        rsAcademyGreenKartTopDealsSteps.setAmountOfDisplayedItems(20);
        rsAcademyGreenKartTopDealsSteps.verifyAmountItemsIsDisplayed(19);
        rsAcademyGreenKartTopDealsSteps.setFilterValue("Banana");
        rsAcademyGreenKartTopDealsSteps.verifyAmountItemsIsDisplayed(1);
        rsAcademyGreenKartTopDealsSteps.setFilterValue(" ");
        rsAcademyGreenKartTopDealsSteps.verifyAmountItemsIsDisplayed(19);
    }

    @AfterTest(groups = {"Smoke"})
    public void afterTest() {
        driver.quit();
    }

    @DataProvider
    public Object[][] getData() {
        Object [][] data = new Object[2][2];
        data[0][0] = "rahulshettyacademy";
        data[0][1] = "learning";
        data[1][0] = "wronglogin";
        data[1][1] = "wrongpass";
        return data;
    }
}
