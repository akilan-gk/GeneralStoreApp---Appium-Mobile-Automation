package generalStoreMobileAutomation.testScripts;

import generalStoreMobileAutomation.reUsables.ActionReUsables;
import generalStoreMobileAutomation.suiteFile.GeneralStoreBase;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

public class LoginTest extends GeneralStoreBase {
    static Logger logger = Logger.getLogger(LoginTest.class);

    @Test
    public void testLogin() {
        logger.info("Entering Name");
        ActionReUsables.sendText(ActionReUsables.findElement(driver, "ID", locatorsPropertyObject.getProperty("name")), testDataPropertyObject.getProperty("nameText"));
        logger.info("Hiding Keyboard");
        ActionReUsables.hideKeyBoard(driver);
        logger.info("Clicking textbox");
        ActionReUsables.click(ActionReUsables.findElement(driver, "XPATH", locatorsPropertyObject.getProperty("femaleCheckBox")));
        logger.info("Clicking country dropdown");
        ActionReUsables.click(ActionReUsables.findElement(driver, "ID", locatorsPropertyObject.getProperty("countryDropDown")));
        logger.info("Scrolling to the country name");
        ActionReUsables.scrollToText(driver, testDataPropertyObject.getProperty("countryName"));
        logger.info("Clicking on the country name");
        ActionReUsables.click(ActionReUsables.findElement(driver, "XPATH", locatorsPropertyObject.getProperty("countryLocator")));
        logger.info("Clicking on Let's shop");
        ActionReUsables.click(ActionReUsables.findElement(driver, "ID", locatorsPropertyObject.getProperty("letsShop")));
    }
}
