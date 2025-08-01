package pages;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait; 
import java.time.Duration;
import java.util.List;

public class BrandsPage {
 
   private WebDriver driver;
   
   public BrandsPage(WebDriver driver) {
       this.driver = driver;
   }
   // Banner
   private By bannerTitle = By.xpath("//h1[contains(@class, 'page-heading') and contains(text(), 'STARBUCKS')]");
   private By bannerImage = By.xpath("//div[contains(@class, 'banner')]//img[contains(@src, 'Starbucks-Banner-image')]");
   private By breadcrumbs = By.xpath("//div[contains(@class,'breadcrumb')]//a");
   By partnerCTA = By.xpath("//a[contains(text(),'PARTNER WITH PEPSICO TODAY')]");
   private By viewAllProductsCTA = By.cssSelector("a.viewAllLinkBtn");
   By subBrandTiles = By.xpath("(//div[@class='brand-product-item']//a)[1]");
   private By tipsSection = By.xpath("//div[contains(@class,'tips-trends')]");
   private By tipsLearnMoreCTA = By.xpath("//div[contains(@class,'tips-trends')]//a[contains(text(),'Learn More')]");
   private By globalNavBar = By.xpath("//nav[@aria-label='User menu' and contains(@class, 'navigation--middle')]");
   private By brandIntro = By.cssSelector("div.content div.paradiv p.header");
   private By allImages = By.xpath("//img");
   private By ctas = By.xpath("//a[contains(@class,'cta')] | //button");
   private By footer = By.xpath("//footer");
 
   // Methods
   public boolean isBannerVisible() {
       try {
           WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
           WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(bannerTitle));
           WebElement image = wait.until(ExpectedConditions.visibilityOfElementLocated(bannerImage));
           return title.isDisplayed() && image.isDisplayed();
       } catch (TimeoutException e) {
           return false;
       }
   }
 
   public int getBreadcrumbCount() {
       return driver.findElements(breadcrumbs).size();
   }
 
   public void clickPartnerCta() {
       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
       WebElement cta = wait.until(ExpectedConditions.elementToBeClickable(
           By.xpath("//a[contains(text(),'PARTNER WITH PEPSICO TODAY')]")));
       ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cta);
       ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cta); // JS click
   }
 
   public void clickViewAllProducts() {
       WebElement viewAll = driver.findElement(viewAllProductsCTA);
       ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", viewAll);
       new WebDriverWait(driver, Duration.ofSeconds(10))
           .until(ExpectedConditions.elementToBeClickable(viewAll));
       viewAll.click();
   }
 
   public int getSubBrandCount() {
       return driver.findElements(subBrandTiles).size();
   }
 
   public void clickSubBrandByIndex(int index) {
       List<WebElement> subBrands = driver.findElements(subBrandTiles);
       if (subBrands.size() > index) {
           subBrands.get(index).click();
       }
   }
 
   public boolean isTipsSectionVisible() {
       return driver.findElements(tipsSection).size() > 0;
   }
 
   public void clickTipsLearnMore() {
       driver.findElement(tipsLearnMoreCTA).click();
   }

   public boolean isGlobalNavBarVisible() {
       try {
           WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
           WebElement nav = wait.until(ExpectedConditions.visibilityOfElementLocated(globalNavBar));
           return nav.isDisplayed();
       } catch (Exception e) {
           return false;
       }
   }
   public boolean isBrandIntroVisible() {
       return driver.findElements(brandIntro).size() > 0;
   }
 
   public boolean allImagesLoaded() {
       List<WebElement> images = driver.findElements(allImages);
       JavascriptExecutor js = (JavascriptExecutor) driver;
       for (WebElement img : images) {
           boolean loaded = (Boolean) js.executeScript(
               "return arguments[0].complete && arguments[0].naturalWidth > 0", img);
           if (!loaded) return false;
       }
       return true;
   }
 
   public int getCtaCount() {
       return driver.findElements(ctas).size();
   }
 
   public boolean isFooterVisible() {
       return driver.findElements(footer).size() > 0;
   }
   public int getSubBrandTileCount() {
       return driver.findElements(By.xpath("//div[@class='brand-product-item']")).size();
   }

   public void clickBrandsButton() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    WebElement brandsButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"mobileMainMenu\"]/div/div/div/ul[1]/li/ul/li[2]/div/div[1]/a")));
	    brandsButton.click();
	}
   public void clickStarbucksBrandTile() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    // Locate the Starbucks brand link using its href or brand name text
	    WebElement starbucksTile = wait.until(ExpectedConditions.elementToBeClickable(
	        By.xpath("//a[contains(@href, '/brand_starbucks') and .//span[contains(text(), 'STARBUCKS')]]")
	    ));

	    starbucksTile.click();
	}


 
}
