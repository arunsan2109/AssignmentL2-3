package com.selenium.AssignmentL2_3;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import io.github.bonigarcia.wdm.WebDriverManager;

/*Assignment 3: Write TestNG method to find the company with maximum current price. 
 * Print the company name and the maximum current price. Hint: Use Customized Xpath*/
public class AssignmentThree {
	// POM for finding the elements by name, id, xpath
	By rows = By.xpath("//table[@class='dataTable']/child::tbody/child::tr");
	By columns = By.xpath("//table[@class='dataTable']/child::thead/child::tr/th");
	By company = By.xpath("//table[@class='dataTable']/child::tbody/child::tr/child::td[1]");
	By currentPrice = By.xpath("//table[@class='dataTable']/child::tbody/child::tr/child::td[4]");

	WebDriver driver;
	String url = "https://demo.guru99.com/test/web-table-element.php";

	@BeforeTest
	public void launchURL() {
		// Step1: Launch URL with Chrome Browser
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get(url);
	}

	@Test
	public void guru99() {
		driver.manage().window().maximize();
		// Step2: Find the total number of rows and the columns in the stock table
		List<WebElement> rowsList = driver.findElements(rows);
		System.out.println("Number of Rows: " + rowsList.size());
		rowsList.clear();
		List<WebElement> colList = driver.findElements(columns);
		System.out.println("Number of Columns: " + colList.size());
		colList.clear();
		List<WebElement> currentPriceList = driver.findElements(currentPrice);
		List<WebElement> companyList = driver.findElements(company);

		float maxCurrentPrice = Float.parseFloat(currentPriceList.get(0).getText());
		int companyIndex = 0;

		// Step3: Traverse the table and find the company with maximum current price
		for (int i = 1; i < currentPriceList.size(); i++) {
			float currentPrice = Float.parseFloat(currentPriceList.get(i).getText());
			if (currentPrice > maxCurrentPrice) {
				maxCurrentPrice = Float.parseFloat(currentPriceList.get(i).getText());
				companyIndex = i;
			}
		}
		// Step4: Print the company name and the maximum current price of the company
		System.out.println(
				"Company: " + companyList.get(companyIndex).getText() + "\nMaximum Current Price: " + maxCurrentPrice);
	}

	@AfterTest
	public void tearDownURL() {
		driver.quit();
	}

}
