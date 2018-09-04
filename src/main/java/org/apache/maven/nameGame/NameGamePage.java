package org.apache.maven.nameGame;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class NameGamePage extends Setup {

	private WebElement getNameGameTitle() {
		return driver.findElement(By.className("text-muted"));
	}

	static int streakCounter = 0;
	static int counter = 0;
	static int pageCOunter = 0;
	static int correctAns = 0;
	static int tries = 0;
	static List<String> wrongPics = new ArrayList<String>();
	static List<String> correctPics = new ArrayList<String>();
	static List<String> currentPics = new ArrayList<String>();
	static int wrongCounter = 0;
	static int correctCount = 0;

	public static void ShowPictures(WebDriver driver) throws InterruptedException {

		Thread.sleep(10000);

		List<WebElement> images = driver.findElements(By.className("photo"));

		for (WebElement we : images) {
			String subStr = we.getText().substring(2);
			currentPics.add(subStr);
		}

		// String img = driver.findElement(By.className("photo")).getText();
		String exp = "photo correct";
		int size = images.size();
		int i = 0;

		while (size != 0) {
			int pageRepeat = 1;

			tries++;
			images.get(i).click();
			String text = images.get(i).getAttribute("class");
			int k = i + 1;

			if (images.get(i).getAttribute("class").equalsIgnoreCase(exp)) {

				List<WebElement> rightPhoto = driver
						.findElements(By.cssSelector("div.photo:nth-child(" + k + ") > div:nth-child(2)"));
				for (WebElement we : rightPhoto) {
					if (!correctPics.contains(we.getText())) {
						correctPics.add(we.getText());
					}

				}
				System.out.println("correct pic list" + correctPics);

				correctAns++;
				streakCounter = Integer.parseInt(driver.findElement(By.className("streak")).getText());

				counter++;
				pageCOunter++;
				if (counter == 7) {
					System.out.println("the wrong counter is " + wrongCounter + " the correct counter " + correctCount);
					driver.quit();
				}

				incrementPicNumber(images);
				ShowPictures(driver);

			} else {
				List<WebElement> wrongPhoto = driver
						.findElements(By.cssSelector("div.photo:nth-child(" + k + ") > div:nth-child(2)"));
				for (WebElement we : wrongPhoto) {
					if (!wrongPics.contains(we.getText())) {
						wrongPics.add(we.getText());
					}
				}

				System.out.println("wrong pic list" + wrongPics);
			}

			if (pageCOunter >= 1 && pageRepeat == 1) {
				streakCounter = 0;
				pageRepeat = 0;
				int streakCounterAfterIncorrect = Integer
						.parseInt(driver.findElement(By.className("streak")).getText());

				if (streakCounter == streakCounterAfterIncorrect) {
					System.out.println("system working fine");
				}

			}
			if (tries == 10) {
				int countTries = Integer.parseInt(driver.findElement(By.cssSelector(".attempts")).getText());
				int countCorrect = Integer.parseInt(driver.findElement(By.cssSelector(".correct")).getText());
				if (tries == countTries) {
					System.out.println("the number of tries are right");
				}
				if (correctAns == countCorrect) {
					System.out.println("the number of correct are right");
				}
			}
			i++;
			size--;

		}
	}

	public static void incrementPicNumber(List<WebElement> images) {
		System.out.println("length of images" + images.size());
		for (int l = 0; l < wrongPics.size(); l++) {
			if (wrongPics.size() > 0 && currentPics.contains(wrongPics.get(l))) {
				wrongCounter++;

			}

		}
		for (int l = 0; l < correctPics.size(); l++) {
			if (correctPics.size() > 0 && currentPics.contains(correctPics.get(l))) {
				correctCount++;
			}

		}
		currentPics.clear();

	}

	public void validateTitleIsPresent() {
		WebElement title = driver.findElement(By.cssSelector("h1"));
		Assert.assertTrue(title != null);
	}

	public void validateClickingFirstPhotoIncreasesTriesCounter() throws Exception {

		int count = Integer.parseInt(driver.findElement(By.className("attempts")).getText());

		driver.findElement(By.className("photo")).click();

		Thread.sleep(3000);

		int countAfter = Integer.parseInt(driver.findElement(By.className("attempts")).getText());

		Assert.assertTrue(countAfter > count);

	}

	public void validateStreakCounterIncrementsOnCorrectSelection() throws Exception {
		Thread.sleep(4000);
		int streakCounterbefore = Integer.parseInt(driver.findElement(By.className("streak")).getText());
		List<WebElement> images = driver.findElements(By.className("photo"));
		String exp = "photo correct";
		for (int i = 0; i < images.size() - 1; i++) {
			images.get(i).click();
			if (images.get(i).getAttribute("class").equalsIgnoreCase(exp)) {
				Thread.sleep(5000);
				int streakCounterAfter = Integer.parseInt(driver.findElement(By.className("streak")).getText());
				Assert.assertTrue(streakCounterAfter > streakCounterbefore);
			}
		}
	}

	public void validateNameAndPhotoChange() throws InterruptedException {
		String nameOne = driver.findElement(By.cssSelector("#name")).getText();
		List<WebElement> imageList = driver.findElements(By.className("photo"));
		List<String> imageListOne = new ArrayList<String>();
		for (WebElement G : imageList) {
			imageListOne.add(G.getText());
		}

		String exp = "photo correct";
		// List <WebElement> images =
		// driver.findElements(By.className("photo"));
		for (int i = 0; i < imageList.size() - 1; i++) {

			imageList.get(i).click();
			if (imageList.get(i).getAttribute("class").equalsIgnoreCase(exp)) {

				Thread.sleep(5000);
				System.out.println("On new page");
				break;

			}
		}

		String nameTwo = driver.findElement(By.cssSelector("#name")).getText();
		List<WebElement> newImageList = driver.findElements(By.className("photo"));
		List<String> imageListTwo = new ArrayList<String>();
		for (WebElement G : newImageList) {
			imageListTwo.add(G.getText());
		}
		Assert.assertTrue(imageListTwo != imageListOne);
	
	}
}
