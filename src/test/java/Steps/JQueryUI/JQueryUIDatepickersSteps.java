package Steps.JQueryUI;

import Pages.JQueryUI.JQueryUIDatepickersPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class JQueryUIDatepickersSteps {
    WebDriver driver;
    WebDriverWait wait;

    public JQueryUIDatepickersSteps(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void switchToDatepickerIframe() {
        JQueryUIDatepickersPage jQueryUIDatepickersPage = new JQueryUIDatepickersPage();
        wait.until(ExpectedConditions.visibilityOfElementLocated(jQueryUIDatepickersPage.frameWithDatepickerLocator));
        driver.switchTo().frame(driver.findElement(jQueryUIDatepickersPage.frameWithDatepickerLocator));
    }

    public void selectDateInDatepickerByMountAndDay(String month, String day) {
        JQueryUIDatepickersPage jQueryUIDatepickersPage = new JQueryUIDatepickersPage();
        wait.until(ExpectedConditions.visibilityOfElementLocated(jQueryUIDatepickersPage.datepickerInputFieldLocator));
        driver.findElement(jQueryUIDatepickersPage.datepickerInputFieldLocator).click();
        while (!driver.findElement(jQueryUIDatepickersPage.datepickerMonthLabelLocator).getText().equalsIgnoreCase(month)) {
            if (getNumberOfMonthByName(driver.findElement(jQueryUIDatepickersPage.datepickerMonthLabelLocator).getText()) < getNumberOfMonthByName(month))
                driver.findElement(jQueryUIDatepickersPage.datepickerNextButtonLocator).click();
            else
                driver.findElement(jQueryUIDatepickersPage.datepickerPreviousButtonLocator).click();
        }

        List<WebElement> days = driver.findElements(jQueryUIDatepickersPage.datepickerDayLocator);
        for (WebElement dayEl : days) {
            if (dayEl.getText().equalsIgnoreCase(day)) {
                dayEl.click();
                break;
            }
        }
    }

    public String[] selectDateInDatepickerByAmountOfDays(int amountOfDays) throws InterruptedException {
        //switching out of scope or current year is not supported
        JQueryUIDatepickersPage jQueryUIDatepickersPage = new JQueryUIDatepickersPage();
        wait.until(ExpectedConditions.visibilityOfElementLocated(jQueryUIDatepickersPage.datepickerInputFieldLocator));
        driver.findElement(jQueryUIDatepickersPage.datepickerInputFieldLocator).click();

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDataMonth = new SimpleDateFormat("MM");
        SimpleDateFormat simpleDataDay = new SimpleDateFormat("dd");
        int currentMonth = Integer.parseInt(simpleDataMonth.format(calendar.getTime()));
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + amountOfDays);
        int searchedMonth = Integer.parseInt(simpleDataMonth.format(calendar.getTime()));
        int searchedDay = Integer.parseInt(simpleDataDay.format(calendar.getTime()));
        int month = currentMonth - searchedMonth;
        if (amountOfDays > 0) {
            if (Math.abs(month) > 0) {
                for (int i = 0; i < Math.abs(month); i++)
                    driver.findElement(jQueryUIDatepickersPage.datepickerNextButtonLocator).click();
            }
        } else if (Math.abs(month) > 0) {
            for (int i = 0; i < Math.abs(month); i++)
                driver.findElement(jQueryUIDatepickersPage.datepickerPreviousButtonLocator).click();
        }
        driver.findElements(jQueryUIDatepickersPage.datepickerDayLocator).get(Math.abs(searchedDay - 1)).click();
        String[] result = {new SimpleDateFormat("MMMM").format(calendar.getTime()), new SimpleDateFormat("d").format(calendar.getTime())};
        return result;
    }

    public void verifyDayIsSelectedInDatepicker(String month, String day) throws InterruptedException {
        JQueryUIDatepickersPage jQueryUIDatepickersPage = new JQueryUIDatepickersPage();
        wait.until(ExpectedConditions.visibilityOfElementLocated(jQueryUIDatepickersPage.datepickerInputFieldLocator));
        driver.findElement(jQueryUIDatepickersPage.datepickerInputFieldLocator).click();
        Thread.sleep(200);
        Assert.assertEquals(driver.findElement(jQueryUIDatepickersPage.datepickerMonthLabelLocator).getText().toLowerCase(), month.toLowerCase());
        Assert.assertEquals(driver.findElement(jQueryUIDatepickersPage.datepickerSelectedDayLocator).getText(), day);
    }

    private int getNumberOfMonthByName(String monthName) {
        DateFormatSymbols dateFormatSymbols = new DateFormatSymbols();
        String[] month = dateFormatSymbols.getMonths();
        int monthIndex = 0;

        for (int i = 1; i < month.length; i++) {
            if (month[i].equalsIgnoreCase(monthName)) {
                monthIndex = i;
                break;
            }
        }
        return monthIndex;
    }
}
