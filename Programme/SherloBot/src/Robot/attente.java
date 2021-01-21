package Robot;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class attente {
	
	public WebElement recherche(WebDriver d, String s) {
		return new WebDriverWait(d,3).until(driver -> d.findElement(By.cssSelector("#react-root "+ s)));
	}

}
