package Robot;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import SherloBot.Recherche;

public class ConnectionTest {

	WebDriver driver;

	public void launchBrowser() {
		File chemin = new File(new File("").getAbsolutePath()).getParentFile();

		System.setProperty("webdriver.chrome.driver", chemin +"\\Driver\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.get("https://twitter.com");
		driver.manage().window().maximize(); 


	}


	public static void main(String[] args) throws InterruptedException {

		ConnectionTest obj = new ConnectionTest();
		attente a = new attente();
		obj.launchBrowser();
		//.css-4rbku5.css-18t94o4
		obj.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		List<WebElement> Connect= obj.driver.findElements(By.cssSelector("#react-root .css-4rbku5.css-18t94o4.css-1dbjc4n.r-1niwhzg"));
		Connect.get(0).click();
		
		List<WebElement> User =obj.driver.findElements(By.name("session[username_or_email]"));
		List<WebElement> MDP =obj.driver.findElements(By.name("session[password]"));

		Recherche r=new Recherche(obj.driver);
		
		User.get(0).sendKeys("@BotSherlo");
		MDP.get(0).sendKeys("NqlBot400"+ Keys.ENTER);

		User =r.findName("username_or_email");
		MDP =obj.driver.findElements(By.name("session[password]"));

		User.get(0).sendKeys("sherlobot4@gmail.com");
		MDP.get(0).sendKeys("NqlBot400"+ Keys.ENTER);
		/*
		 * Ancienne version
		List<WebElement> Login =obj.driver.findElements(By.cssSelector("#react-root input.r-30o5oe")); // chemin actuelle de lemplacement login : input.r-30o5oe.r-1niwhzg.r-17gur6a.r-1yadl64.r-deolkf.r-homxoj.r-poiln3.r-7cikom.r-1ny4l3l.r-1inuy60.r-ou255f.r-vmopo1.r-1w50u8q.r-1dz5y72.r-fdjqy7.r-13qz1uu

		Login.get(0).sendKeys("@BotSherlo");
		Login.get(1).sendKeys("NqlBot400"+ Keys.ENTER);
		 */

		
		WebElement Tweet =r.findCss(".public-DraftStyleDefault-block.public-DraftStyleDefault-ltr");
		System.out.print(Tweet);
		Tweet.sendKeys("8ème test \nSalut Nael @BoussettaNael ");

		WebElement AppuieTweet =a.recherche(obj.driver, ".css-18t94o4.css-1dbjc4n.r-urgr8i.r-42olwf.r-sdzlij.r-1phboty.r-rs99b7.r-1w2pmg.r-1n0xq6e.r-1vuscfd.r-1dhvaqw.r-1ny4l3l.r-1fneopy.r-o7ynqc.r-6416eg.r-lrvibr");
		AppuieTweet.click();
		 


	}
}
