package tests;

import org.testng.annotations.Test;

import base.BaseTest;
import constants.AppConstants;

public class LoginPageTest extends BaseTest{
	
	@Test(priority = 1)
	public void loginPageNavigationTest() {
		loginPage = homePage.navigateToLoginPage();
		String actLoginPageTitle = loginPage.getLoginPageTitle();
		
		loginPage.print("page act title: " + actLoginPageTitle);
		
		loginPage.isEqual(actLoginPageTitle, AppConstants.LOGIN_PAGE_TITLE);
	}

	@Test(priority = 2)
	public void forgotPwdLinkExistTest() {
		loginPage.isTrue(loginPage.isForgotPwdLinkExist());
	}

	@Test(priority = 3)
	public void appLoginTest() {
		loginPage.isTrue(loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim()));
	}
}
