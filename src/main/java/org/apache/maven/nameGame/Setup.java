package org.apache.maven.nameGame;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class Setup {

	public static ChromeDriver driver = new ChromeDriver();

	public static void launchChrome() throws Exception {
		driver.manage().window().maximize();
		driver.get("http://www.ericrochester.com/name-game/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public static void customWait(int i){
		
		driver.manage().timeouts().implicitlyWait(i,TimeUnit.SECONDS);
	}
}