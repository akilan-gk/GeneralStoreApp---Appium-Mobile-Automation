package generalStoreMobileAutomation.testScripts;

import generalStoreMobileAutomation.reUsables.ActionReUsables;
import generalStoreMobileAutomation.suiteFile.GeneralStoreBase;
import io.appium.java_client.android.AndroidElement;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class AddingProductToCartTest extends GeneralStoreBase {
    static Logger logger = Logger.getLogger(AddingProductToCartTest.class);

    @Test
    public void testAddingProductToCart() {
        String productOne = testDataPropertyObject.getProperty("productOne");
        String productTwo = testDataPropertyObject.getProperty("productTwo");
        logger.info("Searching and adding the products into the cart");
        ActionReUsables.click(ActionReUsables.findProduct(driver, locatorsPropertyObject.getProperty("productOne")));
        ActionReUsables.click(ActionReUsables.findProduct(driver, locatorsPropertyObject.getProperty("productTwo")));
        logger.info("Opening the cart");
        ActionReUsables.click(ActionReUsables.findElement(driver, "ID", locatorsPropertyObject.getProperty("cart")));
        logger.info("Verifying that the added products in the cart are same as the user's choice");
        List<WebElement> productNameElements =waitForElement(locatorsPropertyObject.getProperty("productNames"));
        //List<AndroidElement> productNameElements = ActionReUsables.findElements(driver, "ID", locatorsPropertyObject.getProperty("productNames"));
        for (WebElement product : productNameElements) {
            String name = ActionReUsables.getText((AndroidElement) product);
            boolean result = name.equals(productOne) || name.equals(productTwo);
            Assert.assertTrue(result, "Product Mismatch: " + name);
            logger.info("Products Matched");
        }
    }
    public List<WebElement> waitForElement(String locator){
        WebDriverWait wait = new WebDriverWait(driver,3);
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(locator)));
    }
}

