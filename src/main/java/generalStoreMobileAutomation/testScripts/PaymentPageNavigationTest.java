package generalStoreMobileAutomation.testScripts;

import generalStoreMobileAutomation.reUsables.ActionReUsables;
import generalStoreMobileAutomation.suiteFile.GeneralStoreBase;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PaymentPageNavigationTest extends GeneralStoreBase {
    static Logger logger = Logger.getLogger(PaymentPageNavigationTest.class);

    @Test
    public void testPaymentPageNavigation() {
        logger.info("Tapping on product updates checkbox");
        ActionReUsables.tap(driver, ActionReUsables.findElement(driver, "CLASS_NAME", locatorsPropertyObject.getProperty("checkBox")));
        logger.info("Long pressing to open Terms & Conditions");
        ActionReUsables.longPress(driver, ActionReUsables.findElement(driver, "XPATH", locatorsPropertyObject.getProperty("termsAndConditions")), 2);
        logger.info("Closing Terms & Conditions");
        ActionReUsables.click(ActionReUsables.findElement(driver, "ID", locatorsPropertyObject.getProperty("close")));
        logger.info("Clicking on Proceed button");
        ActionReUsables.click(ActionReUsables.findElement(driver, "ID", locatorsPropertyObject.getProperty("proceed")));
        logger.info("Verifying the page is rendered in web using context handles.");
        Assert.assertFalse(ActionReUsables.isSetEmpty(ActionReUsables.getContextHandles(driver)), "Not reached payment page");
    }
}
