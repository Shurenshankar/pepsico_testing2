package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class EquipmentCarouselPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Example footer labels you might expect (update as needed)
    private final String[] footerLabels = {
        "CONTACT US",
        "PRIVACY POLICY",
        "TERMS OF SERVICE",
        "COPYRIGHT"
    };

    public EquipmentCarouselPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // =================== Locators as nested static class ===================
    public static class EquipmentCarouselLocators {

        // =================== Carousel Locators ===================
        public static final By carouselContainer = By.xpath("//div[contains(@class,'carousel__component--carousel')]");
        public static final By nextArrow = By.xpath("//div[contains(@class,'owl-next')]");
        public static final By prevArrow = By.xpath("//div[contains(@class,'owl-prev')]");
        public static final By equipmentName = By.xpath("//div[contains(@class,'owl-item') and contains(@class,'active')]//div[contains(@class,'carousel-banner-title')]");
        public static final By equipmentDescription = By.xpath("//div[contains(@class,'owl-item') and contains(@class,'active')]//div[contains(@class,'carousel-banner-desc')]");
        public static final By equipmentImage = By.xpath("//div[contains(@class,'owl-item') and contains(@class,'active')]//img[contains(@class,'js-responsive-image')]");
        public static final By equipmentCTA = By.xpath("//div[contains(@class,'owl-item') and contains(@class,'active')]//a[contains(@class,'carousel-banner-link')]");
        public static final By videoButton = By.xpath("//div[contains(@class,'owl-item') and contains(@class,'active')]//a[contains(@class,'carousel-banner-play')]");
        public static final By indicators = By.xpath("//div[contains(@class,'owl-dots')]//button");
        public static final By activeIndicator = By.xpath("//div[contains(@class,'owl-dots')]//button[contains(@class,'active')]");

        // =================== Contact Button ===================
        public static final By contactUsButton = By.cssSelector(".js-redirect-equipment-contact");

        // =================== Footer Locators ===================
        public static final By footerContainer = By.tagName("footer");

        // Carousel sections by heading
        public static final By serviceAdvantageSection = By.xpath("//h2[normalize-space()='Service Advantage']/ancestor::div[contains(@class,'row')]");
        public static final By fountainSection = By.xpath("//h2[normalize-space()='Fountain']/ancestor::div[contains(@class,'row')]");
        public static final By coolersCarousel = By.xpath("//h2[normalize-space()='Coolers']/ancestor::div[contains(@class,'row')]");
        public static final By vendorsCarousel = By.xpath("//h2[normalize-space()='Vendors']/ancestor::div[contains(@class,'row')]");
        public static final By urnsCarousel = By.xpath("//h2[normalize-space()='Urns']/ancestor::div[contains(@class,'row')]");
        public static final By quikPickCarousel = By.xpath("//h2[contains(normalize-space(),'Quik Pick')]/ancestor::div[contains(@class,'row')]");

    }

    // =================== Carousel Methods ===================
    public boolean isCarouselVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(EquipmentCarouselLocators.carouselContainer)).isDisplayed();
    }

    public boolean isNavigationArrowsVisible() {
        return driver.findElement(EquipmentCarouselLocators.nextArrow).isDisplayed() &&
               driver.findElement(EquipmentCarouselLocators.prevArrow).isDisplayed();
    }

    public String getActiveEquipmentName() {
        return driver.findElement(EquipmentCarouselLocators.equipmentName).getText();
    }

    public String getActiveEquipmentDescription() {
        return driver.findElement(EquipmentCarouselLocators.equipmentDescription).getText();
    }

    public String getActiveImageSrc() {
        return driver.findElement(EquipmentCarouselLocators.equipmentImage).getAttribute("src");
    }

    public void clickNextArrow() {
        wait.until(ExpectedConditions.elementToBeClickable(EquipmentCarouselLocators.nextArrow)).click();
    }

    public void clickPrevArrow() {
        wait.until(ExpectedConditions.elementToBeClickable(EquipmentCarouselLocators.prevArrow)).click();
    }

    public void clickEquipmentCTA() {
        wait.until(ExpectedConditions.elementToBeClickable(EquipmentCarouselLocators.equipmentCTA)).click();
    }

    public boolean isImageChanged(String oldSrc) {
        wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(EquipmentCarouselLocators.equipmentImage, "src", oldSrc)));
        return !getActiveImageSrc().equals(oldSrc);
    }

    public boolean isCurrentUrlChanged(String oldUrl) {
        return !driver.getCurrentUrl().equals(oldUrl);
    }

    public boolean isCTABtnVisibleAndClickable() {
        WebElement cta = wait.until(ExpectedConditions.visibilityOfElementLocated(EquipmentCarouselLocators.equipmentCTA));
        return cta.isDisplayed() && cta.isEnabled();
    }

    public boolean isVideoButtonPresent() {
        return !driver.findElements(EquipmentCarouselLocators.videoButton).isEmpty();
    }

    public void clickVideoButton() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(EquipmentCarouselLocators.videoButton));
        btn.click();
    }

    public int getTotalIndicators() {
        return driver.findElements(EquipmentCarouselLocators.indicators).size();
    }

    public WebElement getActiveIndicator() {
        return driver.findElement(EquipmentCarouselLocators.activeIndicator);
    }

    public void clickIndicatorByIndex(int index) {
        List<WebElement> dots = driver.findElements(EquipmentCarouselLocators.indicators);
        if (index >= 0 && index < dots.size()) {
            wait.until(ExpectedConditions.elementToBeClickable(dots.get(index))).click();
        }
    }

    public String getActiveIndicatorClass() {
        return getActiveIndicator().getAttribute("class");
    }

    public WebElement getActiveSlideCTAButton() {
        By ctaSelector = By.cssSelector(".owl-item.active a.carousel-banner-link");
        return new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.elementToBeClickable(ctaSelector));
    }

    // =================== Contact Button Methods ===================
    public WebElement getContactUsButton() {
        return wait.until(ExpectedConditions.elementToBeClickable(EquipmentCarouselLocators.contactUsButton));
    }

    public boolean isContactUsButtonVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(EquipmentCarouselLocators.contactUsButton)).isDisplayed();
    }

    public boolean isContactUsButtonClickable() {
        return wait.until(ExpectedConditions.elementToBeClickable(EquipmentCarouselLocators.contactUsButton)).isEnabled();
    }

    // ========== Footer Methods ==========
    public WebElement getFooterContainer() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(EquipmentCarouselLocators.footerContainer));
    }

    public boolean isFooterVisible() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
        return getFooterContainer().isDisplayed();
    }

    public boolean isFooterLabelPresent(String label) {
        return getFooterContainer().getText().toUpperCase().contains(label.toUpperCase());
    }

    public String[] getExpectedFooterLabels() {
        return footerLabels;
    }

    public boolean isServiceAdvantageCarouselVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(EquipmentCarouselLocators.serviceAdvantageSection)).isDisplayed();
    }

    public boolean isFountainCarouselVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(EquipmentCarouselLocators.fountainSection)).isDisplayed();
    }

    public boolean isCoolersCarouselVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(EquipmentCarouselLocators.coolersCarousel)).isDisplayed();
    }

    public boolean isVendorsCarouselVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(EquipmentCarouselLocators.vendorsCarousel)).isDisplayed();
    }

    public boolean isUrnsCarouselVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(EquipmentCarouselLocators.urnsCarousel)).isDisplayed();
    }

    public boolean isQuikPickCarouselVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(EquipmentCarouselLocators.quikPickCarousel)).isDisplayed();
    }
}
