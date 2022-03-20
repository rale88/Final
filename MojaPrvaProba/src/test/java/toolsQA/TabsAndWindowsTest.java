package toolsQA;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class TabsAndWindowsTest {

    @Test
    public void switchingToandFromTabsTests(){

        ChromeDriver driver = new ChromeDriver();
        driver.get("https://demoqa.com/browser-windows");
        WebElement tabButton = driver.findElement(By.id("tabButton"));
        tabButton.click();
        ArrayList <String> tabs = new ArrayList (driver.getWindowHandles());

        driver.switchTo().window(tabs.get(1));
        WebElement texInAnotherTab = driver.findElement(By.id("sampleHeading"));
        System.out.println(texInAnotherTab.getText());
        driver.close();

        driver.switchTo().window(tabs.get(0));

        driver.quit();


    }


}
