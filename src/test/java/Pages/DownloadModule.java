package Pages;

import java.io.IOException;

import org.openqa.selenium.By;

import Utills.AppUtills;

public class DownloadModule extends AppUtills {

	private static final By DownloadTab = By.xpath("//a[text()='Download']");
	private static final By DownloadBuilder = By.xpath("//h1[text()='Download Builder']");
	private static final By AllJqueryUiDownloadPageLink = By.xpath("//a[text()='All jQuery UI Downloads']");
	private static final By AllJqueryUiDownloadPage = By.xpath("//h1[text()='All jQuery UI Downloads']");

	public void validateDownloadPage() throws IOException {
	try {
		clickElement(DownloadTab);
		userShouldseeElement(DownloadBuilder);
	} catch (Exception e) {
		getScreenShotForFailed("validateDownloadPage");
	}
	}

	public void validateAllJqueryUiDownloadPage() throws IOException {
		try {
			clickElement(DownloadTab);
			userShouldseeElement(DownloadBuilder);
			clickElement(AllJqueryUiDownloadPageLink);
			userShouldseeElement(AllJqueryUiDownloadPage);
		} catch (Exception e) {
			getScreenShotForFailed("validateAllJqueryUiDownloadPage");
		}
	}

}
