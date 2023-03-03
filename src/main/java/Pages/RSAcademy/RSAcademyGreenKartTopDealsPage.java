package Pages.RSAcademy;


import org.openqa.selenium.By;

public class RSAcademyGreenKartTopDealsPage {
    public By firstPageButton = By.cssSelector("a[aria-label='First']");
    public By nextPageButton = By.cssSelector("a[aria-label='Next']");
    public By tableHeader = By.cssSelector("th[role='columnheader']");
    public By tableRow = By.cssSelector("tbody tr");
    public By tableCell = By.tagName("td");
    public By amountItemsInTableSelector = By.cssSelector("div select#page-menu");
    public By searchFieldInput = By.cssSelector("div input#search-field");


}
