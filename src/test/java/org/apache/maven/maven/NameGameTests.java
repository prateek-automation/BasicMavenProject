package org.apache.maven.maven;

import org.apache.maven.nameGame.NameGamePage;
import org.apache.maven.nameGame.Setup;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class NameGameTests extends Setup {

	static NameGamePage home = new NameGamePage();

	@Test(priority = 1, enabled = true)
	public void verifyNamAndPhotosChangeForCorrectAnswerTest() throws Exception {
		launchChrome();
		home.validateNameAndPhotoChange();
		driver.quit();
	}
	
	@Test(priority = 2, enabled = true)
	public void validateStreakCounterIncrementsOnCorrectSelection() throws Exception {
		launchChrome();
		home.validateStreakCounterIncrementsOnCorrectSelection();
		driver.quit();
	}


	@Test(priority = 3, enabled = true)
	public void verifyNameGameAutomationScenarios() throws Exception {
		launchChrome();
		home.ShowPictures(driver);
		driver.quit();
	}
}
