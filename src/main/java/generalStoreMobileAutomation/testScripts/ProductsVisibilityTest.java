package generalStoreMobileAutomation.testScripts;

import generalStoreMobileAutomation.reUsables.ActionReUsables;
import generalStoreMobileAutomation.suiteFile.GeneralStoreBase;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductsVisibilityTest extends GeneralStoreBase {
    static Logger logger = Logger.getLogger(ProductsVisibilityTest.class);

    @Test
    public void testProductVisibility() {
        logger.info("Verifying that the page contains products");
        boolean result = ActionReUsables.isListEmpty(ActionReUsables.findElements(driver, "ID", locatorsPropertyObject.getProperty("productNames")));
        Assert.assertFalse(result, "Product page is empty.");
        logger.info("Page contains products for purchase");
    }
}
