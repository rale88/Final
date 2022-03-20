package toolsQA;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class IFrameTests {
@Test
    public void IFrameTests (){
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://demoqa.com/frames");
        WebElement textOutSideIFrame = driver.findElement(By.xpath("//*[@id='framesWrapper']"));
        String textOutSide = textOutSideIFrame.getText();
        System.out.println(textOutSide);

        WebElement iFrame = driver.findElement(By.id("frame1"));
        driver.switchTo().frame(iFrame);

        WebElement sampleHeadingInFrame = driver.findElement(By.id("sampleHeading"));
    System.out.println(sampleHeadingInFrame.getText());

    driver.switchTo().defaultContent();



//        driver.quit();








    }

}
