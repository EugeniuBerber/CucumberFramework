package com.hrms.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hrms.testbase.PageInitializer;
import com.sun.corba.se.spi.orbutil.fsm.Action;

public class CommonMethods extends PageInitializer {

	/**
	 * Method that sends text to any given element
	 * 
	 * @param element
	 * @param text
	 */
	public static void sendText(WebElement element, String text) {
		element.clear();
		element.sendKeys(text);
	}

	/**
	 * Method return Object of JavaScript Executor type
	 * 
	 * @return js object
	 */
	public static JavascriptExecutor getJSExecutor() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js;
	}

	/**
	 * Method performs click using JavaScript executor
	 * 
	 * @param element
	 */
	public static void jsClick(WebElement element) {
		getJSExecutor().executeScript("arguments[0].click();", element);
	}

	/**
	 * Methods scrolls up using JavaScript executor
	 * 
	 * @param pixel
	 */
	public static void scrollUp(int pixel) {
		getJSExecutor().executeScript("window.scrollBy(0, -" + pixel + ")");
	}

	/**
	 * Methods scrolls down using JavaScript executor
	 * 
	 * @param pixel
	 */
	public static void scrollDown(int pixel) {
		getJSExecutor().executeScript("window.scrollBy(0," + pixel + ")");
	}

	/**
	 * Method return Object of WebDriverWait type
	 * 
	 * @return wait object
	 */
	public static WebDriverWait getWaitObject() {
		return new WebDriverWait(driver, Constants.EXPLICIT_WAIT_TIME);
	}

	/**
	 * Method waits until clickability of given element
	 * 
	 * @param element
	 */
	public static void waitForClickability(WebElement element) {
		getWaitObject().until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * Method waits until visibility of given element
	 * 
	 * @param element
	 */
	public static void waitForVisibility(WebElement element) {
		getWaitObject().until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * Method waits until visibility of given element and then clicking
	 * 
	 * @param element
	 */
	public static void click(WebElement element) {
		waitForClickability(element);
		element.click();
	}

	/**
	 * Method that will take a screenshot and store with name in specified location
	 * with .png extension
	 * 
	 * @param fileName
	 */
	public static byte[] takeScreenshot(String fileName) {

		TakesScreenshot ts = (TakesScreenshot) driver;
		byte[] bytes = ts.getScreenshotAs(OutputType.BYTES);

		File src = ts.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(src, new File(Constants.SCREENSHOT_FILEPATH + fileName + getTimeStamp() + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return bytes;

	}

	/**
	 * This method will generate timeStamp
	 * 
	 * @return timeStamp
	 */
	public static String getTimeStamp() {

		Date date = new Date();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss");

		return sdf.format(date);
	}

	/**
	 * This method will click on check box or radio button by given list of
	 * webElements and the value
	 * 
	 * @param radioOrCheckBoxs
	 * @param value
	 */
	public static void clickRadioOrCheckBox(List<WebElement> radioOrCheckBoxs, String value) {
		String actualValue;
		for (WebElement radioOrCheckBox : radioOrCheckBoxs) {
			actualValue = radioOrCheckBox.getAttribute("value").trim();
			if (radioOrCheckBox.isEnabled() && actualValue.equals(value)) {
				jsClick(radioOrCheckBox);
				break;
			}
		}
	}

	/**
	 * This method will select an option from the dropdown by the given webelement
	 * and visible text value and visible text value
	 * 
	 * @param dd
	 * @param visibleTextOrValue
	 */
	public static void selectDDvalue(WebElement dd, String visibleTextOrValue) {

		try {
			Select select = new Select(dd);
			List<WebElement> options = select.getOptions();
			for (WebElement option : options) {
				if (option.getText().equals(visibleTextOrValue)) {
					select.selectByVisibleText(visibleTextOrValue);
					break;
				}
			}
		} catch (UnexpectedTagNameException e) {
			e.printStackTrace();
		}

	}

	/**
	 * This method will select an option from the dropdown by the given webelement
	 * and index and visible text value
	 * 
	 * @param dd
	 * @param visibleTextOrValue
	 */
	public static void selectDDvalue(WebElement dd, int index) {

		try {
			Select select = new Select(dd);
			List<WebElement> options = select.getOptions();

			int size = options.size();

			if (size > index) {
				select.selectByIndex(index);
			}
		} catch (UnexpectedTagNameException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method will switch to a fraim by the given frame webelement
	 * 
	 * @param iFrame
	 * @param index
	 */
	public static void switchToFraim(WebElement iFrame) {
		try {
			driver.switchTo().frame(iFrame);
		}catch(NoSuchFrameException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * This method will switch to a fraim by the given frame index
	 * 
	 * @param iFrame
	 * @param index
	 */
	public static void switchToFraim(int FrameIndex) {
		try {
			driver.switchTo().frame(FrameIndex);
		}catch(NoSuchFrameException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * This method will switch to a fraim by the given frame nameOrId
	 * 
	 * @param iFrame
	 * @param index
	 */
	public static void switchToFraim(String NameOrId) {
		try {
			driver.switchTo().frame(NameOrId);
		}catch(NoSuchFrameException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * This window will switch to child window
	 */			
	public static void switchToChildWindow() {
		String mainWindow = driver.getWindowHandle();
		Set<String> allWindows = driver.getWindowHandles();
		for (String window : allWindows) {
			if(!window.equals(mainWindow)) {
				driver.switchTo().window(window);
				break;
			}
		}
	}
	
	/**
	 * This method waits until visibility of alert
	 */
	public static void waitForAlertVisibility() {
		getWaitObject().until(ExpectedConditions.alertIsPresent());
	}
	
	/**
	 * This method will switch to Alert
	 * 
	 * @return alert 
	 */
	public static Alert switchToAlert() {
		waitForAlertVisibility();
		Alert alert = driver.switchTo().alert();
		return alert;
	}
	
	/**
	 * This method will accept Alert
	 */
	public static void acceptAlert() {
		switchToAlert().accept();
	}
	
	/**
	 * This method will dismiss Alert
	 */
	public static void dismissAlert() {
		switchToAlert().dismiss();
	}
	
	/**
	 * This method will send text to Alert
	 * 
	 * @param text
	 */
	public static void sendTextToAlert(String text) {
		switchToAlert().sendKeys(text);
	}
	
	/**
	 * This method will return text from Alert
	 * 
	 * @return text
	 */
	public static String getTextFromAlert() {
		String text = switchToAlert().getText();
		return text;
	}
	
	/**
	 * This method will return Action Object
	 * 
	 * @return action object
	 */
	public static Actions getActionObject() {
		Actions action = new Actions(driver);
		return action;
	}
	
	/**
	 * This method will move to any given element using Action class
	 * 
	 * @param element
	 */
	public static void moveToElement(WebElement element) {
		waitForVisibility(element);
		getActionObject().moveToElement(element).perform();
	}
	
	/**
	 * This method will perform double click using Actions
	 * 
	 * @param element
	 */
	public static void doubleClick(WebElement element) {
		waitForClickability(element);
		getActionObject().doubleClick(element).perform();
	}
	
	/**
	 * This method will take draggable webelement and move to droppable webelement
	 * 
	 * @param draggable
	 * @param droppable
	 */
	public static void dragAndDrop(WebElement draggable, WebElement droppable) {
		waitForClickability(draggable);
		waitForClickability(droppable);
		getActionObject().dragAndDrop(draggable, droppable).perform();
	}
	
	/**
	 * This method will perform right click using Actions
	 * 
	 * @param element
	 */
	public static void rightClick(WebElement element) {
		waitForClickability(element);
		getActionObject().contextClick(element).perform();
	}
	
	/**
	 * This method will perform click using Actions
	 * 
	 * @param element
	 */
	public static void actionClick(WebElement element) {
		waitForClickability(element);
		getActionObject().click(element).perform();
	}
	
	/**
	 * This method will read JSON file
	 * 
	 * @param fileName
	 * @return jsonFile
	 */
	static String jsonFile;
	public static String readJson(String fileName) {
		try {
			jsonFile = new String(Files.readAllBytes(Paths.get(fileName)));
		}catch(Exception e) {
			e.printStackTrace();
		}
		return jsonFile;
	}

}