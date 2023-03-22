package com.myfirstautotest.com;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class GoogleHomePageTest {
    public static void main(String[] args) {
        // Set system property for ChromeDriver
        System.setProperty("webdriver.chrome.driver", "/opt/homebrew/bin/chromedriver");

        // Create a new instance of ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Navigate to the Google home page
        driver.get("https://www.google.com");
        
        GoogleHomePageTest ghpt = new GoogleHomePageTest();
        
        // Get the value of the "lang" attribute of the HTML element
        String locale = driver.findElement(By.tagName("html")).getAttribute("lang");
        
        // Print the locale to the console
        System.out.println("Google homepage locale: " + locale);
       
       
        ghpt.checkPageTitle(driver);
        ghpt.checkSearchBox(driver);
        ghpt.checkTextAlignmentforDiffernetLocale(driver);
       
        /*
        try {
			ghpt.checkpagedeviceChecker();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       */

        // Close the browser
        driver.quit();
    }
    
    public void checkPageTitle(WebDriver driver)
    {
    	 // Verify the page title is correct
        String expectedTitle = "Google";
        String actualTitle = driver.getTitle();
        if (actualTitle.equals(expectedTitle)) {
            System.out.println("Page title is correct: " + actualTitle);
        } else {
            System.out.println("Page title is incorrect: " + actualTitle);
        } 
    	
    }
    
    public void checkSearchBox(WebDriver driver)
    {
    	 // Verify the page title is correct
        String expectedTitle = "Google";
        String actualTitle = driver.getTitle();
        // Verify the search box is present
        WebElement searchBox = driver.findElement(By.name("q"));
        if (searchBox.isDisplayed()) {
            System.out.println("Search box is present");
        } else {
            System.out.println("Search box is not present");
        }

        // Enter a search query and submit the search
        searchBox.sendKeys("Sahaj Sharma");
        searchBox.submit();

        // Wait for the search results to load
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Verify the search results page title contains the search query
        expectedTitle = "Sahaj Sharma - Google Search";
        actualTitle = driver.getTitle();
        if (actualTitle.contains(expectedTitle)) {
            System.out.println("Search results page title is correct: " + actualTitle);
        } else {
            System.out.println("Search results page title is incorrect: " + actualTitle);
        }
    	
    }
    
    public void checkTextAlignmentforDiffernetLocale(WebDriver driver)
    {
    	// Find the element containing the text to check for alignment
    	By textElementLocator = By.xpath("//*[contains(text(),'google')]");

        // Get the initial text alignment value
        String initialTextAlign = driver.findElement(textElementLocator).getCssValue("text-align");

        // Change the locale to another desired locale
        driver.get("https://www.google.com/?hl=fr");

        // Wait for the page to reload
        try {
            Thread.sleep(5000); // Wait for 5 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Find the element again
        By newTextElementLocator = By.xpath("//*[contains(text(),'google')]");  
        
        // Get the new text alignment value
        String newTextAlign = driver.findElement(newTextElementLocator).getCssValue("text-align");

        // Compare the two values
        if (initialTextAlign.equals(newTextAlign)) {
            System.out.println("Text alignment is still the same");
        } else {
            System.out.println("Text alignment has changed");
        }
    }
    
    public void checkpagedeviceChecker() throws MalformedURLException
    {
    	// Define the desired capabilities for the device to test on
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone 12 Pro");
        capabilities.setCapability("browserName", "Chrome");

        // Create a RemoteWebDriver instance for the device to test on
        WebDriver driver = new RemoteWebDriver(new URL("http://192.168.68.105:4444/wd/hub"), capabilities);

        // Navigate to the Google homepage
        driver.get("https://www.google.com");

        // Get the value of the "lang" attribute of the HTML element
        String locale = driver.findElement(By.tagName("html")).getAttribute("lang");

        // Print the locale to the console
        System.out.println("Google homepage locale: " + locale);
    }
    
}