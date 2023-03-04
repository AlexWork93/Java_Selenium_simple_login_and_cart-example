package Pages.JQueryUI;


import org.openqa.selenium.By;

public class JQueryUIDatepickersPage {

    public By frameWithDatepickerLocator = By.cssSelector("iframe.demo-frame");
    public By datepickerInputFieldLocator = By.cssSelector("#datepicker");
    public By datepickerMonthLabelLocator = By.cssSelector("span.ui-datepicker-month");
    public By datepickerNextButtonLocator = By.cssSelector("a.ui-datepicker-next");
    public By datepickerPreviousButtonLocator = By.cssSelector("a.ui-datepicker-prev");
    public By datepickerDayLocator = By.cssSelector("td[data-handler='selectDay'] a");
    public By datepickerSelectedDayLocator = By.cssSelector("td[data-handler='selectDay'] a.ui-state-active");


}
