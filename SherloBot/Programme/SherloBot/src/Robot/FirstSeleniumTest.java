package Robot;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FirstSeleniumTest {

	WebDriver driver;

	public synchronized void launchBrowser() {
		File chemin = new File(new File("").getAbsolutePath()).getParentFile();

		System.setProperty("webdriver.chrome.driver", chemin +"\\Driver\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.get("https://twitter.com/tmvli");


	}

	public static void main(String[] args) throws InterruptedException {

		FirstSeleniumTest obj = new FirstSeleniumTest();
		obj.launchBrowser();

		obj.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		WebElement test = new WebDriverWait(obj.driver,3).until(driver -> obj.driver
				.findElement(By.cssSelector("#react-root .css-901oao.css-bfa6kz.r-111h2gw.r-18u37iz.r-1qd0xha.r-a023e6.r-16dba41.r-ad9z0x.r-bcqeeo.r-qvutc0")));
		String identifiant = test.getText();
		System.out.println(identifiant);

		String test2=obj.driver.findElement(By.cssSelector("#react-root .css-1dbjc4n.r-1adg3ll.r-15d164r" + "")).getText();
		System.out.println(test2);

		String test3=obj.driver.findElement(By.cssSelector("#react-root .css-901oao.css-16my406.r-111h2gw.r-4qtqp9.r-1qd0xha.r-ad9z0x.r-zso239.r-bcqeeo.r-qvutc0"+ "")).getText();
		System.out.println(test3);

		//liste les dernier tweet
		List<WebElement> test4 =obj.driver.findElements(By.cssSelector("#react-root .css-1dbjc4n.r-1loqt21.r-18u37iz.r-1ny4l3l.r-1udh08x.r-1yt7n81.r-ry3cjt.r-o7ynqc.r-6416eg"));
		for (int i=0;i<test4.size();i++) {
			obj.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			System.out.println(i + "-- " + test4.get(i).getText());
			
			/*
			test4.get(i).click();			
			System.out.println(obj.driver.getCurrentUrl());			
			obj.driver.navigate().back();
			*/
			
			System.out.println("-----------------");
			
		}
		/*
		String test4=obj.driver.findElement(By.cssSelector("#react-root .css-1dbjc4n.r-1loqt21.r-18u37iz.r-1ny4l3l.r-1udh08x.r-1yt7n81.r-ry3cjt.r-o7ynqc.r-6416eg"+ "")).getText();
		System.out.println(test4);
		 */

		//retrouve et affiche la banniere
		WebElement test5=obj.driver.findElement(By.tagName("img"));
		System.out.println(test5.getAttribute("src"));
		//obj.driver.get(test5.getAttribute("src"));

		

	}




}