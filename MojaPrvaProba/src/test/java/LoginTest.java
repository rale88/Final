import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class LoginTest {


    public static final String LOGIN_PAGE = "https://www.saucedemo.com/";
    public static final String USERNAME = "standard_user";
    public static final String PASSWORD = "secret_sauce";
    public static final String INVENTORY_PAGE = "https://www.saucedemo.com/inventory.html";
    public static final String PRODUCT_TITLE = "PRODUCTS";
    public static final String INVALID_USERNAME = "P";
    public static final String INVALID_PASSWORD = "L";
    public static final String ERROR_MASSAGE = "Epic sadface: Username and password do not match any user in this service";


    /**
     * Login with valid credentials
     * Steps:
     * 1. Start driver
     * 2. Enter valid username
     * 3. Enter valid password
     * 4. Click on the Login button
     *
     * Expected results:
     * 1. Verify that actual page is INVENTORY PAGE
     * 2. Verify that Products title is shown
     */


    @Test
    public static void prviTest() {

    ChromeDriver driver = new ChromeDriver();
    login(driver,USERNAME, PASSWORD);
    sleep();
//
////    driver.get(LOGIN_PAGE);
////
////    WebElement userName = driver.findElement(By.id("user-name"));
////    userName.click();
////    userName.sendKeys(USERNAME);
////
////    WebElement password = driver.findElement(By.id("password"));
////    password.click();
////    password.sendKeys(PASSWORD);
////
////    WebElement loginButton = driver.findElement(By.id("login-button"));
////    loginButton.click();
//
    String inventoryPageUrl = driver.getCurrentUrl();
    assert inventoryPageUrl.equals(INVENTORY_PAGE) : "Wrong. Expected to be on the page:" + INVENTORY_PAGE + "Actual URL" + inventoryPageUrl;
    sleep();
    WebElement productsTitle = driver.findElement(By.className("title"));
    String actualTitle = productsTitle.getText();
    assert actualTitle.equals(PRODUCT_TITLE) : "Wrong. Expected to be: " + PRODUCT_TITLE + "actual:" + actualTitle;
    sleep();
    }

    @Test
    public void testSaInvalidUserValidPass() {

        ChromeDriver driver = new ChromeDriver();
        login(driver, INVALID_USERNAME, PASSWORD);
        sleep();
        String actualUrl = driver.getCurrentUrl();
        assert !actualUrl.equals(INVENTORY_PAGE): "Error. Expected to be on:" + INVENTORY_PAGE + "Actual" + actualUrl;
        sleep();
        WebElement errorMassageButton = driver.findElement(By.className("error-button"));
        assert errorMassageButton.isDisplayed(): "Error massage should be shown but it is not";
        sleep();
        WebElement errorMassageText = driver.findElement(By.xpath("//h3[@data-test='error']"));
        String actualErrorText = errorMassageText.getText();
        assert actualErrorText.equals(ERROR_MASSAGE): "Wrong. Expected to be shown" + ERROR_MASSAGE + "Actual" + actualErrorText;
        sleep();


    }


    public static void login(ChromeDriver driver, String user, String pass) {

        driver.get(LOGIN_PAGE);
        sleep();
        stampaj("unesi username");
        WebElement userName = driver.findElement(By.id("user-name"));
        userName.click();
        userName.sendKeys(user);
        sleep();
        stampaj("unesi password");
        WebElement password = driver.findElement(By.id("password"));
        password.click();
        password.sendKeys(pass);
        sleep();
        stampaj("click login button");
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();
        sleep();
    }

    /**
     * Login and Logout test
     * Steps:
     * 1. Login with valid credentials
     * 2. Click on  the Hamburger menu
     * 3. Click on the logout link
     *
     * Expected resluts:
     * 1. Verify that user is logged in
     * 2. Verify that user is logged out
     */


    @Test
    public void loginAndLogoutTest() {



        ChromeDriver driver = new ChromeDriver();
        login(driver, USERNAME, PASSWORD);

        stampaj("verify that user is on INV PAGE");
        String actualUrl = driver.getCurrentUrl();
        assert actualUrl.equals(INVENTORY_PAGE): "Error. Expected to be on:" + INVENTORY_PAGE + "Actual" + actualUrl;

        stampaj("click on the Hamburger menu");
        WebElement hamburgerMenu = driver.findElement(By.id("react-burger-menu-btn"));
        hamburgerMenu.click();

        WebElement logoutLink = driver.findElement(By.id("logout_sidebar_link"));
        WebDriverWait wait = new WebDriverWait(driver,5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("react-burger-cross-btn")));

        stampaj("click logout");
        logoutLink.click();

        stampaj("verify that you are loged out");
        actualUrl = driver.getCurrentUrl();
        assert actualUrl.equals(LOGIN_PAGE): "Error. Exp to be on: " + LOGIN_PAGE + "Actual on:" + actualUrl;
        driver.quit();

    }

    public static void stampaj(String x){
        System.out.println(x);
    }



      public static void sleep() {
        try {
            Thread.sleep(2000);
        }
        catch (Exception e){}



      }
}