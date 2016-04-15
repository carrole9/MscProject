package com.ILoveLAMP.Selenium.Test;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class NetworkEngineerTest {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "http://localhost:8080/maven_Project/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testNetworkEngineer() throws Exception {
		driver.get(baseUrl);
		driver.findElement(By.id("inputEmail")).clear();
		driver.findElement(By.id("inputEmail")).sendKeys("Colm");
		driver.findElement(By.id("inputPassword")).clear();
		driver.findElement(By.id("inputPassword")).sendKeys("1234");
		driver.findElement(By.id("login")).click();
		assertEquals("Welcome back Colm", closeAlertAndGetItsText());

		// check the failures and total duration query
		driver.findElement(By.id("semenu7")).click();
		driver.findElement(By.id("semenu7")).click();
		driver.findElement(
				By.xpath(".//*[@id='NoOfFailureAndTotalDurationStart']"))
				.sendKeys("11/01/2013 17:10");
		driver.findElement(
				By.xpath(".//*[@id='NoOfFailureAndTotalDurationEnd']"))
				.sendKeys("11/01/2013 17:11");

		long startTime = System.currentTimeMillis();
		driver.findElement(
				By.xpath(".//*[@id='submitNoOfFailureAndTotalDuration']"))
				.click();
		boolean status = driver
				.findElement(
						By.xpath(".//*[@id='myNoOfFailureAndTotalDurationTable']/tbody/tr[1]/td[1]"))
				.isDisplayed();
		boolean withIN2Second = false;
		if (status) {
			long stopTime = System.currentTimeMillis();
			long elapsedTime = stopTime - startTime;
			System.out
					.println(elapsedTime
							+ " ms was consumed for failures and total duration for each imsi in certain period (Stroy 9).");
			System.out
					.println(elapsedTime
							/ 1000.000
							+ " s was consumed for failures and total duration for each imsi in certain period (Stroy 9).");
			if (elapsedTime < 2000) {
				withIN2Second = true;
			}
		}
		assertTrue(status);
		assertTrue(withIN2Second);

		// ////////////////////////////////////////////////////////////////////////////////////

		// check the Top 10 imsis query
		driver.findElement(By.id("semenu8")).click();
		driver.findElement(By.xpath(".//*[@id='TopTenStart']")).sendKeys(
				"11/01/2013 17:10");
		driver.findElement(By.xpath(".//*[@id='TopTenEnd']")).sendKeys(
				"11/01/2013 17:11");

		startTime = System.currentTimeMillis();
		driver.findElement(By.xpath(".//*[@id='submitTopTen']")).click();
		status = driver.findElement(
				By.xpath(".//*[@id='myIMSICountTable']/tbody/tr[1]/td[1]"))
				.isDisplayed();
		withIN2Second = false;
		if (status) {
			long stopTime = System.currentTimeMillis();
			long elapsedTime = stopTime - startTime;
			System.out
					.println(elapsedTime
							+ " ms was consumed for top 10 imsis for a period (Stroy 12).");
			System.out
					.println(elapsedTime
							/ 1000.000
							+ " s was consumed for top 10 imsis for a period (Stroy 12).");
			if (elapsedTime < 2000) {
				withIN2Second = true;
			}
		}
		assertTrue(status);
		assertTrue(withIN2Second);

		// ////////////////////////////////////////////////////////////////////////////////////

		// check Event Id and Cause Code Combo query
		driver.findElement(By.id("semenu9")).click();
		driver.findElement(By.xpath(".//*[@id='NetworkManufacturer']"))
				.sendKeys("Mitsubishi");
		driver.findElement(By.xpath(".//*[@id='NetworkModel']")).sendKeys(
				"G410");

		startTime = System.currentTimeMillis();
		driver.findElement(By.xpath(".//*[@id='submitModel']")).click();
		status = driver.findElement(
				By.xpath(".//*[@id='myModelTable']/tbody/tr[1]/td[1]"))
				.isDisplayed();
		withIN2Second = false;
		if (status) {
			long stopTime = System.currentTimeMillis();
			long elapsedTime = stopTime - startTime;
			System.out
					.println(elapsedTime
							+ " ms was consumed for EventId&CauseCodeCombo for given model&manufacturer (Stroy 10).");
			System.out
					.println(elapsedTime
							/ 1000.000
							+ " s was consumed for EventId&CauseCodeCombo for given model&manufacturer (Stroy 10).");
			if (elapsedTime < 2000) {
				withIN2Second = true;
			}
		}
		assertTrue(status);
		assertTrue(withIN2Second);

		// ////////////////////////////////////////////////////////////////////////////////////

		// check the Top 10 Combo query
		driver.findElement(By.id("semenu10")).click();
		driver.findElement(By.xpath(".//*[@id='StartMarketTime']")).sendKeys(
				"11/01/2013 17:10");
		driver.findElement(By.xpath(".//*[@id='EndMarketTime']")).sendKeys(
				"11/01/2013 17:11");

		startTime = System.currentTimeMillis();
		driver.findElement(By.xpath(".//*[@id='submitMarket']")).click();
		status = driver.findElement(
				By.xpath(".//*[@id='myMarketTable']/tbody/tr[1]/td[1]"))
				.isDisplayed();
		withIN2Second = false;
		if (status) {
			long stopTime = System.currentTimeMillis();
			long elapsedTime = stopTime - startTime;
			System.out
					.println(elapsedTime
							+ " ms was consumed for top 10 Combo for a period (Stroy 11).");
			System.out
					.println(elapsedTime
							/ 1000.000
							+ " s was consumed for top 10 Combo for a period (Stroy 11).");
			if (elapsedTime < 2000) {
				withIN2Second = true;
			}
		}
		assertTrue(status);
		assertTrue(withIN2Second);

		// ////////////////////////////////////////////////////////////////////////////////////

		// check list all imsis query
		driver.findElement(By.id("semenu1")).click();
		driver.findElement(By.xpath(".//*[@id='mySTime']")).sendKeys(
				"11/01/2013 17:10");
		driver.findElement(By.xpath(".//*[@id='myETime']")).sendKeys(
				"11/01/2013 17:11");

		startTime = System.currentTimeMillis();
		driver.findElement(By.xpath(".//*[@id='suppSub1']")).click();
		status = driver.findElement(
				By.xpath(".//*[@id='myIMSI']/tbody/tr[1]/td[1]")).isDisplayed();
		withIN2Second = false;
		if (status) {
			long stopTime = System.currentTimeMillis();
			long elapsedTime = stopTime - startTime;
			System.out
					.println(elapsedTime
							+ " ms was consumed for list all imsis for a period (Stroy 7).");
			System.out
					.println(elapsedTime
							/ 1000.000
							+ " s was consumed for list all imsis for a period (Stroy 7).");
			if (elapsedTime < 2000) {
				withIN2Second = true;
			}
		}
		assertTrue(status);
		assertTrue(withIN2Second);

		// ////////////////////////////////////////////////////////////////////////////////////

		// check No. of failure for given manufacturer+model+peiod query
		driver.findElement(By.id("semenu2")).click();
		driver.findElement(By.xpath(".//*[@id='myManufacturer']")).sendKeys(
				"Mitsubishi");
		driver.findElement(By.xpath(".//*[@id='myPhoneModel']")).sendKeys(
				"G410");
		driver.findElement(By.xpath(".//*[@id='FstartTime']")).sendKeys(
				"11/01/2013 17:10");
		driver.findElement(By.xpath(".//*[@id='FendTime']")).sendKeys(
				"20/02/2013 17:11");

		startTime = System.currentTimeMillis();
		driver.findElement(By.xpath(".//*[@id='suppSub2']")).click();
		status = driver.findElement(
				By.xpath(".//*[@id='myFailureCount']/tbody/tr[2]/td"))
				.isDisplayed();
		withIN2Second = false;
		if (status) {
			long stopTime = System.currentTimeMillis();
			long elapsedTime = stopTime - startTime;
			System.out
					.println(elapsedTime
							+ " ms was consumed for No. of failure for given manufacturer+model+peiod query (Stroy 8).");
			System.out
					.println(elapsedTime
							/ 1000.000
							+ " s was consumed for No. of failure for given manufacturer+model+peiod query (Stroy 8).");
			if (elapsedTime < 2000) {
				withIN2Second = true;
			}
		}
		assertTrue(status);
		assertTrue(withIN2Second);

		// ////////////////////////////////////////////////////////////////////////////////////

		// check for a given failure Cause Class, display the IMSI's that were
		// affected query
		driver.findElement(By.id("semenu3")).click();
		driver.findElement(By.xpath(".//*[@id='myFailure']")).sendKeys(
				"MO DATA");

		startTime = System.currentTimeMillis();
		driver.findElement(By.xpath(".//*[@id='suppSub3']")).click();
		status = driver.findElement(
				By.xpath(".//*[@id='myIMSIDisplay']/tbody/tr[1]/td[1]"))
				.isDisplayed();
		withIN2Second = false;
		if (status) {
			long stopTime = System.currentTimeMillis();
			long elapsedTime = stopTime - startTime;
			System.out
					.println(elapsedTime
							+ " ms was consumed for a given failure Cause Class, display the IMSI's that were affected query (Stroy 14).");
			System.out
					.println(elapsedTime
							/ 1000.000
							+ " s was consumed for a given failure Cause Class, display the IMSI's that were affected query (Stroy 14).");
			if (elapsedTime < 2000) {
				withIN2Second = true;
			}
		}
		assertTrue(status);
		assertTrue(withIN2Second);

		// ////////////////////////////////////////////////////////////////////////////////////

		// Find Event ID and Cause Code for failures affecting an IMSI
		driver.findElement(By.id("semenu4")).click();
		driver.findElement(By.xpath(".//*[@id='myIMSI1']")).sendKeys(
				"191911000456426");

		startTime = System.currentTimeMillis();
		driver.findElement(By.xpath(".//*[@id='submitIMSI']")).click();
		status = driver.findElement(
				By.xpath(".//*[@id='myEventID']/tbody/tr/td[1]")).isDisplayed();
		withIN2Second = false;
		if (status) {
			long stopTime = System.currentTimeMillis();
			long elapsedTime = stopTime - startTime;
			System.out
					.println(elapsedTime
							+ " ms was consumed for Find Event ID and Cause Code for failures affecting an IMSI (Stroy 4).");
			System.out
					.println(elapsedTime
							/ 1000.000
							+ " s was consumed for Find Event ID and Cause Code for failures affecting an IMSI (Stroy 4).");
			if (elapsedTime < 2000) {
				withIN2Second = true;
			}
		}
		assertTrue(status);
		assertTrue(withIN2Second);

		// ////////////////////////////////////////////////////////////////////////////////////

		// Count for a given IMSI the number of failures during a given time period
		driver.findElement(By.id("semenu5")).click();
		driver.findElement(By.xpath(".//*[@id='FmyIMSI']")).sendKeys(
				"191911000456426");
		driver.findElement(By.xpath(".//*[@id='x']")).sendKeys(
				"11/01/2013 17:10");
		driver.findElement(By.xpath(".//*[@id='y']")).sendKeys(
				"11/03/2013 17:11");

		startTime = System.currentTimeMillis();
		driver.findElement(By.xpath(".//*[@id='submitFailureCount']")).click();
		status = driver.findElement(
				By.xpath(".//*[@id='myFailureCount1']/tbody/tr[2]/td")).isDisplayed();
		withIN2Second = false;
		if (status) {
			long stopTime = System.currentTimeMillis();
			long elapsedTime = stopTime - startTime;
			System.out
					.println(elapsedTime
							+ " ms was consumed for Counting for a given IMSI the number of failures during a given time period (Stroy 5).");
			System.out
					.println(elapsedTime
							/ 1000.000
							+ " s was consumed for Counting for a given IMSI the number of failures during a given time period (Stroy 5).");
			if (elapsedTime < 2000) {
				withIN2Second = true;
			}
		}
		assertTrue(status);
		assertTrue(withIN2Second);
		
		// ////////////////////////////////////////////////////////////////////////////////////

		// Unique Cause Codes associated with their call failures affecting an IMSI
		driver.findElement(By.id("semenu6")).click();
		driver.findElement(By.xpath(".//*[@id='myIMSICauseCode']")).sendKeys(
				"191911000456426");

		startTime = System.currentTimeMillis();
		driver.findElement(By.xpath(".//*[@id='submitCauseCode']")).click();
		status = driver.findElement(
				By.xpath(".//*[@id='myCauseCode']/tbody/tr/td[1]")).isDisplayed();
		withIN2Second = false;
		if (status) {
			long stopTime = System.currentTimeMillis();
			long elapsedTime = stopTime - startTime;
			System.out
					.println(elapsedTime
							+ " ms was consumed for Find Unique Cause Codes associated with their call failures affecting an IMSI (Stroy 6).");
			System.out
					.println(elapsedTime
							/ 1000.000
							+ " s was consumed for Find Unique Cause Codes associated with their call failures affecting an IMSI (Stroy 6).");
			if (elapsedTime < 2000) {
				withIN2Second = true;
			}
		}
		assertTrue(status);
		assertTrue(withIN2Second);

	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
