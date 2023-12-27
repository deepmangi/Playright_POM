package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import constants.AppConstants;

public class HomePageTest extends BaseTest {

	@Test(priority=2)
	public void homePageTitleTest() {
		String actualTitle = homePage.getHomePageTitle();
		
		homePage.isEqual(actualTitle, AppConstants.HOME_PAGE_TITLE);
	}

	@Test(priority=1)
	public void homePageURLTest() {
		String actualURL = homePage.getHomePageURL();
		
		homePage.isEqual(actualURL, prop.getProperty("url"));
	}

	@DataProvider
	public Object[][] getProductsData() {
		return new Object[][] {
				{ "Macbook" }, 
				{ "iMac" }, 
				{ "Samsung" }
		};
	}

	@Test(dataProvider = "getProductsData",priority=3)
	public void searchTest(String productName) throws InterruptedException {
		Thread.sleep(5000);
		String actualSearchHeader = homePage.doSearch(productName);
		homePage.isEqual(actualSearchHeader, "Search - " + productName);
	}

}
