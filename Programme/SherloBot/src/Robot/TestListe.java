package Robot;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import SherloBot.TConnexion;

public class TestListe {
	
	TConnexion con;

	public TestListe() {

	}

	public void connexion() {
		try{
			con = TConnexion.getTConnection();

		}catch (Exception e) {
			System.out.println("ERREUR, essaie d'une nouvelle connexion");
			System.out.println(e);
			con = TConnexion.getTConnection();		
		}
		System.out.println("Connexion reussis");
	}

	public void AnalyseCompte(String compte) throws SQLException {

		this.connexion();
		
		con.driver.get("https://twitter.com/ElNa32534207");
		
		con.driver.get(con.driver.getCurrentUrl()+"/following");
		
		List<WebElement> test4 =con.driver.findElements(By.cssSelector("#react-root div.css-901oao.r-jwli3a.r-1qd0xha.r-a023e6.r-16dba41.r-ad9z0x.r-bcqeeo.r-glunga.r-1jeg54m.r-qvutc0"));
		for (int i=0;i<test4.size();i++) {
			
			test4 =con.driver.findElements(By.cssSelector("#react-root div.css-901oao.r-jwli3a.r-1qd0xha.r-a023e6.r-16dba41.r-ad9z0x.r-bcqeeo.r-glunga.r-1jeg54m.r-qvutc0"));
			con.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			System.out.println(i + "-- " + test4.get(i).getText());
			
			test4.get(i).click();
			
			List<WebElement> id = con.driver.findElements(By.cssSelector("#react-root .css-901oao.css-bfa6kz.r-111h2gw.r-18u37iz.r-1qd0xha.r-a023e6.r-16dba41.r-ad9z0x.r-bcqeeo.r-qvutc0"));
			System.out.println(id.get(1).getText());
			
			con.driver.navigate().back();

			System.out.println("-----------------");

		}
		
	}
	
	


public static void main(String[] args) throws InterruptedException, SQLException {
	
	TestListe t = new TestListe();
	t.AnalyseCompte("test");
}
}
