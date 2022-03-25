package generalStoreMobileAutomation.testScripts;

import generalStoreMobileAutomation.reUsables.ActionReUsables;
import generalStoreMobileAutomation.suiteFile.GeneralStoreBase;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PriceValidationTest extends GeneralStoreBase {
    static Logger logger = Logger.getLogger(PriceValidationTest.class);

    @Test
    public void testProductPrice() {
        int size = ActionReUsables.getSize(ActionReUsables.findElements(driver, "ID", locatorsPropertyObject.getProperty("productNames")));
        double sumOfPrice = 0;
        logger.info("Calculating the total price of two chosen products");
        for (int index = 0; index < size; index++) {
            String priceText = ActionReUsables.getText(ActionReUsables.findElements(driver, "ID", locatorsPropertyObject.getProperty("productPrice")).get(index));
            double price = getPrice(priceText);
            sumOfPrice = sumOfPrice + price;
        }
        logger.info("Total price of chosen products: " + sumOfPrice);
        logger.info("Getting the actual total price displayed");
        String totalPriceDisplayed = ActionReUsables.getText(ActionReUsables.findElement(driver, "ID", locatorsPropertyObject.getProperty("totalPriceDisplayed")));
        totalPriceDisplayed = totalPriceDisplayed.substring(1);
        double totalValue = Double.parseDouble(totalPriceDisplayed);
        logger.info("Total price displayed: " + totalValue);
        logger.info("Verifying the actual and expected total price of the chosen products..");
        Assert.assertEquals(sumOfPrice, totalValue, "Price mismatch");
        logger.info("Price Matched");
    }

    public static double getPrice(String value) {
        value = value.substring(1);
        return Double.parseDouble(value);
    }
}
