package SherloBot;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import mvc.Modele;

public class test {

	public static void main(String args[]) throws SQLException{

		ListeFollow.deleteTable();
		Compte.deleteTable();
		
		Compte.createTable();
		ListeFollow.createTable();
		
		TConnexion con = TConnexion.getTConnection();
		
		try {
			Thread.sleep(1000, 0);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		con.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement recherche = con.driver.findElement(By.cssSelector("input.r-30o5oe"));
		con.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		recherche.sendKeys("@ElNa32534207"+ Keys.ENTER);
		
		try {
			Thread.sleep(1000, 0);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		//accede a sa page
		List<WebElement> listeCompte = con.driver.findElements(By.cssSelector("div.css-901oao.css-bfa6kz.r-jwli3a.r-1qd0xha.r-a023e6.r-b88u0q.r-ad9z0x.r-bcqeeo"));
		System.out.println(listeCompte.get(1).getText());
		listeCompte.get(1).click();	
		
		ArrayList<String> lF = new ArrayList<String>();

		con.driver.get(con.driver.getCurrentUrl()+"/followers");

		List<WebElement> lFolwer =con.driver.findElements(By.cssSelector("#react-root div.css-901oao.r-jwli3a.r-1qd0xha.r-a023e6.r-16dba41.r-ad9z0x.r-bcqeeo.r-glunga.r-1jeg54m.r-qvutc0"));
		lFolwer.get(0).click();

	}

}
