package generalStoreMobileAutomation.suiteFile;

import generalStoreMobileAutomation.reUsables.ActionReUsables;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class GeneralStoreBase {
    static Logger logger = Logger.getLogger(GeneralStoreBase.class);
    public static AppiumDriverLocalService service;
    public static AndroidDriver<AndroidElement> driver;
    public String locatorsFilePath;
    public String testDataFilePath;
    public static Properties locatorsPropertyObject;
    public static Properties testDataPropertyObject;

    @BeforeSuite
    public void setCapabilities() {
        configureLog4j();
        logger.info("Starting Emulator");
        startEmulator();
        logger.info("Starting Appium Server");
        startAppiumServer();
        logger.info("Getting locators.properties filepath");
        locatorsFilePath = filesPath() + "locators.properties";
        logger.info("Getting testData.properties filepath");
        testDataFilePath = filesPath() + "testData.properties";
        logger.info("Creating properties object for locators file");
        locatorsPropertyObject = getPropertyObject(locatorsFilePath);
        logger.info("Creating properties object for testData file");
        testDataPropertyObject = getPropertyObject(testDataFilePath);
        logger.info("Setting desired capabilities..");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        logger.info("Device name: " + testDataPropertyObject.getProperty("deviceName"));
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, testDataPropertyObject.getProperty("deviceName"));
        logger.info("App filePath: " + testDataPropertyObject.getProperty("appPath"));
        capabilities.setCapability(MobileCapabilityType.APP, testDataPropertyObject.getProperty("appPath"));
        logger.info("Automation Name: " + testDataPropertyObject.getProperty("automationName"));
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, testDataPropertyObject.getProperty("automationName"));
        logger.info("New Command TimeOut: " + testDataPropertyObject.getProperty("newCommandTimeOutTime"));
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, testDataPropertyObject.getProperty("newCommandTimeOutTime"));
        try {
            logger.info("Creating Android Driver object");
            logger.info("Port Url: " + testDataPropertyObject.getProperty("portUrl"));
            driver = new AndroidDriver<>(new URL(testDataPropertyObject.getProperty("portUrl")), capabilities);
            logger.info("Setting implicit wait");
            ActionReUsables.waitImplicitly(driver, 10);
        } catch (MalformedURLException exception) {
            logger.info("Check the passed url is legal and its following proper protocol.");
            System.out.println("Check the passed url is legal and its following proper protocol.");
        }
    }

    @AfterSuite
    public void tearDown() {
        logger.info("Closing the app");
        driver.closeApp();
        logger.info("Stopping Appium Server");
        stopAppiumServer();
    }

    public String filesPath() {
        String userWorkingDirectory = System.getProperty("user.dir");
        String pathSeparator = System.getProperty("file.separator");
        return userWorkingDirectory + pathSeparator + "src" + pathSeparator + "main" +
                pathSeparator + "java" + pathSeparator + "generalStoreMobileAutomation" + pathSeparator + "files" + pathSeparator;
    }

    public String log4jFilesPath() {
        String userWorkingDirectory = System.getProperty("user.dir");
        String pathSeparator = System.getProperty("file.separator");
        return userWorkingDirectory + pathSeparator + "src" + pathSeparator + "main" +
                pathSeparator + "java" + pathSeparator + "generalStoreMobileAutomation" + pathSeparator + "files" + pathSeparator + "log4j.properties";
    }

    public String batFilePath() {
        String userWorkingDirectory = System.getProperty("user.dir");
        String pathSeparator = System.getProperty("file.separator");
        return userWorkingDirectory + pathSeparator + "src" + pathSeparator + "main" +
                pathSeparator + "java" + pathSeparator + "generalStoreMobileAutomation" + pathSeparator + "files" + pathSeparator + "startEmulator.bat";
    }

    public Properties getPropertyObject(String filePath) {
        Properties property = new Properties();
        try {
            FileInputStream file = new FileInputStream(filePath);
            property.load(file);
        } catch (FileNotFoundException exception) {
            System.out.println("The specified file is not present in the given path.");
        } catch (IOException exception) {
            System.out.println("Check the file in the specified path.");
        }
        return property;
    }

    public void startAppiumServer() {
        service = AppiumDriverLocalService.buildDefaultService();
        service.start();
    }

    public void stopAppiumServer() {
        service.stop();
    }

    public void startEmulator() {
        try {
            Runtime.getRuntime().exec(batFilePath());
            Thread.sleep(35000);
        } catch (IOException | InterruptedException exception) {
            logger.info("Check the file in the specified path");
        }
    }

    public void configureLog4j() {
        PropertyConfigurator.configure(log4jFilesPath());
    }
}
