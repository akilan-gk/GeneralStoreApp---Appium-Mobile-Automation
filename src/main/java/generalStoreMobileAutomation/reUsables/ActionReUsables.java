package generalStoreMobileAutomation.reUsables;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class ActionReUsables {
    public static AndroidElement findElement(AndroidDriver<AndroidElement> driver, String locatorType, String locatorText) {
        AndroidElement element;
        switch (locatorType) {
            case "ID":
                element = driver.findElementById(locatorText);
                break;
            case "XPATH":
                element = driver.findElementByXPath(locatorText);
                break;
            case "NAME":
                element = driver.findElementByName(locatorText);
                break;
            case "CLASS_NAME":
                element = driver.findElementByClassName(locatorText);
                break;
            case "ANDROID_UI_AUTOMATOR":
                element = driver.findElementByAndroidUIAutomator(locatorText);
                break;
            default:
                element = null;
        }
        return element;
    }

    public static List<AndroidElement> findElements(AndroidDriver<AndroidElement> driver, String locatorType, String locatorText) {
        List<AndroidElement> elements;
        switch (locatorType) {
            case "ID":
                elements = driver.findElementsById(locatorText);
                break;
            case "XPATH":
                elements = driver.findElementsByXPath(locatorText);
                break;
            case "NAME":
                elements = driver.findElementsByName(locatorText);
                break;
            case "CLASS_NAME":
                elements = driver.findElementsByClassName(locatorText);
                break;
            case "ANDROID_UI_AUTOMATOR":
                elements = driver.findElementsByAndroidUIAutomator(locatorText);
                break;
            default:
                elements = null;
        }
        return elements;
    }

    public static boolean click(AndroidElement element) {
        boolean value;
        if (element.isDisplayed()) {
            element.click();
            value = true;
        } else {
            System.out.println("Element is not displayed to click");
            value = false;
        }
        return value;
    }

    public static boolean sendText(AndroidElement element, String text) {
        boolean value;
        if (element.isDisplayed()) {
            element.sendKeys(text);
            value = true;
        } else {
            System.out.println("Element is not displayed to send text");
            value = false;
        }
        return value;
    }

    public static int getSize(List<AndroidElement> list) {
        return list.size();
    }

    public static String getText(AndroidElement element) {
        return element.getText();
    }

    public static Set<String> getContextHandles(AndroidDriver<AndroidElement> driver){
        return driver.getContextHandles();
    }
    public static void longPress(AndroidDriver<AndroidElement> driver, AndroidElement element, int time) {
        TouchAction action = new TouchAction(driver);
        action.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(element)).withDuration(Duration.ofSeconds(time))).release().perform();
    }

    public static void tap(AndroidDriver<AndroidElement> driver, AndroidElement element) {
        TouchAction action = new TouchAction(driver);
        action.tap(TapOptions.tapOptions().withElement(ElementOption.element(element))).perform();
    }

    public static AndroidElement findProduct(AndroidDriver<AndroidElement> driver, String locator) {
        return driver.findElementsByXPath(locator).get(0);
    }

    public static boolean isListEmpty(List<AndroidElement> list) {
        return list.isEmpty();
    }

    public static boolean isSetEmpty(Set<String> set) {
        return set.isEmpty();
    }

    public static void hideKeyBoard(AndroidDriver<AndroidElement> driver) {
        driver.hideKeyboard();
    }

    public static void scrollToText(AndroidDriver<AndroidElement> driver, String text) {
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + text + "\"));");
    }

    public static void scrollToTextWithID(AndroidDriver<AndroidElement> driver, String id, String text) {
        driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"" + id + "\")).scrollIntoView(new UiSelector().textMatches(\"" + text + "\").instance(0))"));
    }

    public static void waitImplicitly(AndroidDriver<AndroidElement> driver, int time) {
        driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
    }
}
