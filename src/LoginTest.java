import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class LoginTest {

    public static void main(String[] args){
        // variables
        String baseUrl = "https://spree-vapasi-prod.herokuapp.com/";
        String chromeDriverPath = System.getProperty("user.dir")+"/"+"drivers/chromedriver";
        String loginId="vapasi@thoughtworks.com";
        String password = "test123";
        String loginLinkSelector = "#link-to-login > a";
        String emailInput = "#spree_user_email";
        String passwordInput = "#spree_user_password";
        String loginButton = "#new_spree_user > p:nth-child(4) > input";
        String loginMessageText = "Logged in successfully";
        String loginMessageSelector = "#content > div.alert.alert-success";
        String signoutLinkSelector = "//*[@id=\"nav-bar\"]/li[2]/a";
        String signoutMessageLocator = "#content > div.alert.alert-notice";
        String signoutMessageText = "Signed out successfully.";

        // WebDriver initialization
        WebDriver driver;

        // Setup chrome driver
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        driver = new ChromeDriver();
        //implicit timeout
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        driver.manage().window().maximize();

        // Launch URL
        driver.get(baseUrl);

        // Click on Login Link
        WebElement loginLink = driver.findElement(By.cssSelector(loginLinkSelector));
        loginLink.click();

        // provide Email
        WebElement emailField = driver.findElement(By.cssSelector(emailInput));
        emailField.clear();
        emailField.sendKeys(loginId);

        // provide Password
        WebElement passwordField = driver.findElement(By.cssSelector(passwordInput));
        passwordField.clear();
        passwordField.sendKeys(password);

        // Click on Login Button
        driver.findElement(By.cssSelector(loginButton)).click();


        // verify if Login is successful
        WebElement signoutLink = driver.findElement(By.xpath(signoutLinkSelector));
        // wait for the Login alert message to be displayed
        WebElement loginMessage = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(loginMessageSelector)));

          if(loginMessage.getText().contains(loginMessageText)){
              System.out.println("Login Successful");
          }else{
              System.out.println("Login failed");
          }

        // Logout and wait for the logout message to be displayed
        signoutLink.click();
        WebElement signoutMessage = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(signoutMessageLocator)));

        if(signoutMessage.getText().contains(signoutMessageText)){
            System.out.println("Signout successful");
        }else {
            System.out.println("Signout failed");
        }

        // close the page
        driver.quit();

    }

}
