// ProductPage.java
package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // <-- initialize it here
    }
    private By firstProduct = By.xpath("//*[@id=\"main-content\"]/div[2]/div[2]/div[2]/div/div/div[1]/div[4]/div[1]/div");
    private By productImage = By.xpath("//*[@id=\"main-content\"]/div[2]/div[2]/div[2]/div/div/div[1]/div[4]/div[1]/div/a/img");
    private By productTitle = By.xpath("//*[@id=\"title-102979\"]");
    private By allProducts = By.xpath("//*[@id=\"mobileMainMenu\"]/div/div/div/ul[1]/li/ul/li[1]/div/div[2]/ul/li[1]/div[2]/ul/li[12]/a");
    private By productLink = By.cssSelector("https://www.pepsicopartners.com/pepsico/en/USD/BEVERAGES/Soft-Drinks/Pepsi-Zero-Sugar/p/1-JYAS-3");
    private By filterPanel = By.id("//*[@id=\"main-content\"]/div[2]/div[1]/div/div[2]");

    
    public boolean isProductVisible() {
        try {
            return driver.findElement(firstProduct).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isProductClickable() {
        try {
            WebElement product = driver.findElement(firstProduct);
            product.click();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean scrollToProduct() {
        try {
            WebElement product = driver.findElement(firstProduct);
            new Actions(driver).moveToElement(product).perform();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isImagePresent() {
        try {
            return driver.findElement(productImage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isTitlePresent() {
        try {
            String title = driver.findElement(productTitle).getText();
            return title != null && !title.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isProductContainerEnabled() {
        try {
            return driver.findElement(firstProduct).isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean hoverOverProduct() {
        try {
            WebElement product = driver.findElement(firstProduct);
            new Actions(driver).moveToElement(product).perform();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean openProductDetailPage() {
        try {
            WebElement link = driver.findElement(productLink);
            String oldUrl = driver.getCurrentUrl();
            link.click();
            Thread.sleep(2000);
            return !driver.getCurrentUrl().equals(oldUrl);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isTitleCorrect() {
        return driver.getTitle().toLowerCase().contains("beverages");
    }

    public boolean hasAtLeastOneProduct() {
        try {
            List<WebElement> products = driver.findElements(allProducts);
            return products.size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isProductLinkValid() {
        try {
            WebElement link = driver.findElement(productLink);
            String href = link.getAttribute("href");
            return href != null && href.contains("product") && !href.trim().isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean scrollToBottom() {
        try {
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
            Thread.sleep(2000);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean hasNoBrokenImage() {
        try {
            WebElement img = driver.findElement(productImage);
            Long width = (Long) ((JavascriptExecutor) driver).executeScript("return arguments[0].naturalWidth", img);
            return width != null && width > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isFilterPanelVisible() {
        try {
            return driver.findElement(filterPanel).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}