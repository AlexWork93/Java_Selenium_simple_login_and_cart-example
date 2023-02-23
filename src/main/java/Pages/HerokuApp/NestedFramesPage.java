package Pages.HerokuApp;


import org.openqa.selenium.By;

public class NestedFramesPage {

    public By frameTop = By.cssSelector("frame[name='frame-top']");
    public By textContentForMiddleFrame = By.cssSelector("#content");
    public By textContentForLeftAndRightFrame = By.tagName("body");

    public By getIframeByName(String iframeName){
        return By.cssSelector("frame[name='frame-" + iframeName.toLowerCase() + "']");
    }

}
