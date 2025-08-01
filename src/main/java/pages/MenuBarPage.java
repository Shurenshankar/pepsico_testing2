package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class MenuBarPage {
    WebDriver driver;

    public MenuBarPage(WebDriver driver) {
        this.driver = driver;
    }
    // Locators for each menu item
    private By productsMenu = By.xpath("//*[@id=\"mobileMainMenu\"]/div/div/div/ul[1]/li/ul/li[1]/div/div[1]/a");
    private By brandsMenu = By.xpath("//*[@id=\"mobileMainMenu\"]/div/div/div/ul[1]/li/ul/li[2]/div/div[1]/a");
    private By equipmentMenu = By.xpath("//*[@id=\"mobileMainMenu\"]/div/div/div/ul[1]/li/ul/li[3]/div/div/a");
    private By trendsMenu = By.xpath("//*[@id=\"mobileMainMenu\"]/div/div/div/ul[1]/li/ul/li[4]/div/div[1]/a");
    private By resourcesMenu = By.xpath("//*[@id=\"mobileMainMenu\"]/div/div/div/ul[1]/li/ul/li[5]/div/div[1]/a");
    private By serviceMenu = By.xpath("//*[@id=\"mobileMainMenu\"]/div/div/div/ul[1]/li/ul/li[6]/div/div[1]/a");
    private By marketingHubMenu = By.xpath("//*[@id=\"mobileMainMenu\"]/div/div/div/ul[1]/li/ul/li[7]/div/div/a");

    // Actions
    public boolean isMenuDisplayed(By menuLocator) {
        return driver.findElement(menuLocator).isDisplayed();
    }

    public void clickMenu(By menuLocator) {
        driver.findElement(menuLocator).click();
    }

    public By[] getAllMenus() {
        return new By[]{productsMenu, brandsMenu, trendsMenu, resourcesMenu, serviceMenu};
    }

    public String getMenuText(By locator) {
        return driver.findElement(locator).getText();
    }
}