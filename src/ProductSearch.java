import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ProductSearch {
    public static void main(String[] args){
        // variables
        String baseUrl = "https://spree-vapasi-prod.herokuapp.com/";
        String chromeDriverPath = System.getProperty("user.dir")+"/"+"drivers/chromedriver";
        String searchBarSelector = "//*[@id=\"keywords\"]";
        String productName = "Baseball Jersey";
        String searchButtonSelector = "#search-bar > form > input.btn.btn-success";
        String productSearchResults = "#products > div .product-body > a > span";

        // WebDriver initialization
        WebDriver driver;

        // Setup chrome driver
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        driver = new ChromeDriver();

        //implicit timeout
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        // Launch URL
        driver.get(baseUrl);

        // enter product name in search bar
        WebElement searchBar = driver.findElement(By.xpath(searchBarSelector));
        searchBar.clear();
        searchBar.sendKeys(productName);
        // Click on Search button
        driver.findElement(By.cssSelector(searchButtonSelector)).click();

        // Get search results and print the product names
        List<WebElement> searchResults = driver.findElements(By.cssSelector(productSearchResults));

        for(WebElement searchResult : searchResults){
            if(searchResult.getText().contains(productName)){
                System.out.println(searchResult.getText()+" is found in the search results");
            }else{
                System.out.println(productName + " is NOT found Search results");
            }
        }

        // Quit page
        driver.quit();
    }
}
