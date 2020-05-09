package operation;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.TouchScreen;

//import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
//import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
//import org.openqa.selenium.support.ui.Select;

//import junit.framework.Assert;
public class Keywords {

	WebDriver driver;

	public Keywords(WebDriver driver) {
		this.driver = driver;
	}

	public void perform(Properties p, String operation, String objectName, String objectType, String value)
			throws Exception {
		System.out.println("");
		if (operation.equals("CLICK")) {
			try {
				Thread.sleep(5000);
				driver.findElement(this.getObject(p, objectName, objectType)).click();
			} catch (Exception e) {
				System.out.println(e);
			}
		} else if (operation.equals("ENTERDATA")) {
			try {
				driver.findElement(this.getObject(p, objectName, objectType)).sendKeys(value);
			} catch (Exception e) {
				System.out.println(e);
			}
		} else if (operation.equals("CLOSEAPPLICATION")) {
			try {
				driver.quit();
			} catch (Exception e) {
				System.out.println(e);
			}

		} else if (operation.equals("ID")) {
			try {
				driver.findElement(this.getObject(p, objectName, objectType)).click();
			} catch (Exception e) {
				System.out.println(e);
			}

		} else if (operation.equals("ASSERT")) {
			try {
				String tv_name=driver.findElement(this.getObject(p, objectName, objectType)).getText();
				if (tv_name.equals("TCL 65-inch 4K Ultra HD HDR Roku Smart TV - 65S425")) {
					System.out.println("TEST CASE PASS");
				} else {
					System.out.println("TEST CASE FAIL");
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		} else if (operation.equals("IMPLICITWAIT")) {
			try {
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			} catch (Exception e) {
				System.out.println(e);
			}
		} else if (operation.equals("WAIT")) {
			try {
				Thread.sleep(5000);
				// driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			} catch (Exception e) {
				System.out.println(e);
			}
		} else if (operation.equals("SCROLL")) {
			try {
				Dimension dim = driver.manage().window().getSize();
				int x = dim.getWidth() / 2;
				int starty = (int) (dim.getHeight() * 0.8);
				int endy = (int) (dim.getHeight() * 0.2);

				/*
				 * TouchAction th=new TouchAction(driver); th.press(PointOption.point(x,
				 * starty)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
				 * .moveTo(PointOption.point(x,endy)).release().perform();
				 */

			} catch (Exception e) {
				System.out.println(e);
			}

		} else if (operation.equals("GETTEXT")) {
			try {

				System.out.println(driver.findElement(this.getObject(p, objectName, objectType)).getText());
			} catch (Exception e) {
				System.out.println(e);
			}
		} else {
			System.out.println("Keyword not fount");
		}

	}

	/**
	 * Find element BY using object type and value
	 * 
	 * @param p
	 * @param objectName
	 * @param objectType
	 * @return
	 * @throws Exception
	 */
	private By getObject(Properties p, String objectName, String objectType) throws Exception {
		// Find by id
		if (objectType.equalsIgnoreCase("ID")) {

			return By.xpath(p.getProperty(objectName));
		}
		// Find by xpath
		if (objectType.equalsIgnoreCase("XPATH")) {

			return By.xpath(p.getProperty(objectName));
		}
		// find by class
		else if (objectType.equalsIgnoreCase("CLASSNAME")) {

			return By.className(p.getProperty(objectName));

		}
		// find by name
		else if (objectType.equalsIgnoreCase("NAME")) {

			return By.name(p.getProperty(objectName));

		}
		// Find by css
		else if (objectType.equalsIgnoreCase("CSS")) {

			return By.cssSelector(p.getProperty(objectName));

		}
		// find by link
		else if (objectType.equalsIgnoreCase("LINK")) {
			System.out.println("in the link");
			return By.linkText(p.getProperty(objectName));

		}
		// find by partial link
		else if (objectType.equalsIgnoreCase("PARTIALLINK")) {

			return By.partialLinkText(p.getProperty(objectName));

		} else {
			throw new Exception("Wrong object type");
		}
	}
}
