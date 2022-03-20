import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class ShopingTest {

    /**
     *
     */

    @Test
    public void buyBackPackTest () {
//        ChromeDriver driver = new ChromeDriver();
//        driver.manage().window().maximize();

        //chromedriver copiramo putanju u metodu ispod
        System.setProperty("webdriver.chrome.driver","C:\\Users\\rkrivokapic\\IdeaProjects\\MojaPrvaProba\\chromedriver.exe");

        //Predefinisana metoda za Chrome driver opcije
        ChromeOptions options = new ChromeOptions();
        options.addArguments(new String[]{"--start-maximized"});
        options.addArguments(new String[]{"--ignore-certificate-errors"});
        options.addArguments(new String[]{"--disable-popup-blocking"});
        options.addArguments(new String[]{"--incognito"});
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        ChromeDriver driver = new ChromeDriver(options);



        //Ovo je za add to cart click na add i asertacija za ADD
        LoginTest.login(driver, LoginTest.USERNAME, LoginTest.PASSWORD);
        WebElement backpackAddToCardButton = driver.findElement(By.xpath("//button[@data-test='add-to-cart-sauce-labs-backpack']"));
        String backPackAddTOCardButtonText = backpackAddToCardButton.getText();
        assert  backPackAddTOCardButtonText.equals("ADD TO CART");
        backpackAddToCardButton.click();

        //Ovo je za remove button asertacija prema REMOVE button-u
        WebElement backPackRemoveButton = driver.findElement(By.xpath("//button[@data-test='remove-sauce-labs-backpack']"));
        assert backPackRemoveButton.isDisplayed(): "Remove button is not shown";
        String backPackRemoveButonText = backPackRemoveButton.getText();
        assert  backPackRemoveButonText.equals("REMOVE");


        //Ovo je asertacija za sliku korpe kada da li se pojavio br 1 nakon klika na ADD TO CART
        WebElement shopingCartBadgeIcon = driver.findElement(By.className("shopping_cart_link"));
        assert shopingCartBadgeIcon.isDisplayed() : "Erroe. Shoping cart icon with number is NOT shown";
        String numberInShopingCartBadge = shopingCartBadgeIcon.getText();
        int number = Integer.valueOf(numberInShopingCartBadge);
        assert number==1 : "Error. Wrong number. Expected: 1, Actual " + numberInShopingCartBadge;

        WebElement shopingCartIcon = driver.findElement(By.xpath("//a[@class = 'shopping_cart_link']"));
        shopingCartIcon.click();

        //asertacija prema URL-u na koji smo prebaceni. Contains dozvoljava da ubacimo deo linka
        String currentUrl = driver.getCurrentUrl();
        assert currentUrl.contains("cart") : "Error. Expected to be on Cart Page. Actual:" + currentUrl;

        //checkout click i asertacija
        WebElement checkoutButton = driver.findElement(By.id("checkout"));
        checkoutButton.click();

        currentUrl = driver.getCurrentUrl();
        assert currentUrl.contains("checkout-step-one"): "Error. Expected to be on checkout Step 1 page. Actual:" + currentUrl;

        //checkout first name
        WebElement firstName = driver.findElement(By.id("first-name"));
        firstName.sendKeys("Pera");

        //checkout Last name
        WebElement lastName = driver.findElement(By.id("last-name"));
        lastName.sendKeys("Peric");

        //checkout ZIP postal code
        WebElement zipPostal = driver.findElement(By.id("postal-code"));
        zipPostal.sendKeys("11080");

        //checkout continue button
        WebElement continueButton = driver.findElement(By.id("continue"));
        continueButton.click();

        //asertacija za Checkout step 2
        currentUrl = driver.getCurrentUrl();
        assert currentUrl.contains("checkout-step-two"): "Error. Expected to be on checkout Step 1 page. Actual:" + currentUrl;

        //klik na finish i asertacija preko URL-a
        WebElement finishButton = driver.findElement(By.xpath("//button[text() = 'Finish']"));
        finishButton.click();

        currentUrl = driver.getCurrentUrl();
        assert currentUrl.contains("checkout-complete"): "Error. Expected to be on checkout Step 1 page. Actual:" + currentUrl;

        driver.quit();





    }

}
