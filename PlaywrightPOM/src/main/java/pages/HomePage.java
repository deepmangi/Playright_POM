package pages;

import com.microsoft.playwright.Page;

import factory.PlaywrightFactory;

public class HomePage extends PlaywrightFactory{

	private Page page;

	// 1. String Locators - OR
	private String search = "input[name='search']";
	private String searchIcon = "div#search button";
	private String searchPageHeader = "div#content h1";
	private String loginLink = "a:text('Login')";
	private String myAccountLink = "a[title='My Account']";
	

	// 2. page constructor:
	public HomePage(Page page) {
		this.page = page;
	}

	// 3. page actions/methods:
	public String getHomePageTitle() {
		String title =  page.title();
		print("page title: " + title);
		return title;
	}

	public String getHomePageURL() {
		String url =  page.url();
		print("page url : " + url);
		return url;
	}

	public String doSearch(String productName) {
		type(search, productName);
		click(searchIcon);
		String header =  page.textContent(searchPageHeader);
		print("search header: " + header);
		return header;
	}
	
	public LoginPage navigateToLoginPage() {
		click(myAccountLink);
		click(loginLink);
		return new LoginPage(page);
	}
	
}
