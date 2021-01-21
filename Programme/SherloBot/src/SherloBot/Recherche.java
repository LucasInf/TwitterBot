package SherloBot;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import Robot.ConnectionTest;

public class Recherche {

	private WebDriver web;
	public Recherche(WebDriver w) {
		web=w;
	}
	
	public WebElement findCss(String s) {
		return web.findElement(By.cssSelector("#react-root "+ s));
	}
	
	public List<WebElement> findName(String s){
		List<WebElement> ele=new ArrayList<WebElement>();
		ele.add((WebElement) new WebDriverWait(web,3).until(driver -> web.findElements(By.name("session["+s+"]"))));
		return ele;
	}
}