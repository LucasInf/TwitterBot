package SherloBot;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import mvc.Modele;
//"@ElNa32534207"
public class SherloBot {

	TConnexion con;
	Modele modele;
	Boolean anal = false;

	public SherloBot(Modele m) throws SQLException {
		this.modele=m;
	}

	/*
	 * permet d'analyser les comptes
	 */
	public void AnalyseCompte(String compte) throws SQLException {

		//se connecte au compte twitter
		this.connexion();


		//test si le compte a deja ete analysé
		this.TestAnalyse(compte);

		if(anal) {

			//permet d'acceder a la page du compte recherche
			this.Recherche(compte);

			//recupere l'identifiant du compte
			String identifiant = this.RecupererIdentifiant();	

			//recupere la liste des followers
			ArrayList<String> listFollower = this.RecupererListeFollower(compte);
			System.out.println(listFollower);
			
			//recupere la liste des followers
			ArrayList<String> listFollowes = this.RecupererListeFollowes(compte);
			System.out.println(listFollowes);		

			//permet d'indiquer que le compte a été analysé
			Compte c = new Compte(compte,"oui");
			c.save();
			
			modele.ajoutTexte(compte + " analysé");
			
			System.out.println("Analyse "+listFollower);
			
			WebElement home = con.driver.findElement(By.cssSelector("a.css-4rbku5.css-18t94o4.css-1dbjc4n.r-1habvwh.r-1loqt21.r-6koalj.r-eqz5dr.r-16y2uox.r-1ny4l3l.r-1ag2gil.r-13qz1uu"));
			home.click();


			//permet d'analyser les comptes de la liste des followers
			for (int i=0; i<listFollower.size();i++) {
				con.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				System.out.println("Analyse"+listFollower.get(i));

				//appel recursif
				this.AnalyseCompte(listFollower.get(i));
			}

			System.out.println("Analyse "+listFollowes);

			//permet d'analyser les comptes de la liste des followers
			for (int i=0; i<listFollowes.size();i++) {
				con.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				System.out.println("Analyse"+listFollowes.get(i));

				//appel recursif
				this.AnalyseCompte(listFollowes.get(i));
			}
		}
	}

	/*
	 * permet de laisser charger la page pour eviter les erreurs
	 */
	public void chargement() {

		try {
			Thread.sleep(1000, 0);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
	}

	/*
	 * permet au bot de se connecter a son compte
	 */
	public void connexion() throws SQLException {
		try{
			con = TConnexion.getTConnection();

		}catch (Exception e) {
			System.out.println("ERREUR, essaie d'une nouvelle connexion");
			System.out.println(e);
			con = TConnexion.getTConnection();		
		}
		System.out.println("Connexion reussis");
	}	

	/*
	 * permet de rechercher un compte et d'acceder a sa page
	 */
	public void Recherche(String compte) {

		//permet de laisser charger
		this.chargement();

		//recherche le compte
		con.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement recherche = con.driver.findElement(By.cssSelector("input.r-30o5oe"));
		con.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		recherche.sendKeys(compte+ Keys.ENTER);

		//permet de laisser charger
		this.chargement();

		//accede a sa page
		List<WebElement> listeCompte = con.driver.findElements(By.cssSelector("div.css-901oao.css-bfa6kz.r-jwli3a.r-1qd0xha.r-a023e6.r-b88u0q.r-ad9z0x.r-bcqeeo"));
		System.out.println(listeCompte.get(1).getText());
		listeCompte.get(1).click();		
	}

	/*
	 * Permet de recuperer l'identifiant sur la page d'un compte
	 */
	public String RecupererIdentifiant() throws SQLException{

		//permet de laisser charger
		this.chargement();

		String lI = "";

		List<WebElement> id = con.driver.findElements(By.cssSelector("#react-root .css-901oao.css-bfa6kz.r-111h2gw.r-18u37iz.r-1qd0xha.r-a023e6.r-16dba41.r-ad9z0x.r-bcqeeo.r-qvutc0"));
		System.out.println(id.get(1).getText());
		lI = id.get(1).getText();

		System.out.println("-----------------");

		Compte c = new Compte (lI);
		c.save();

		return lI;	
	}

	/**
	 * Permet de recuperer les identifiants des comptes qui follows
	 * @param compte
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<String> RecupererListeFollower(String compte) throws SQLException{

		//permet de laisser charger
		this.chargement();

		ArrayList<String> lF = new ArrayList<String>();

		con.driver.get(con.driver.getCurrentUrl()+"/followers");

		List<WebElement> lFolwer =con.driver.findElements(By.cssSelector("#react-root div.css-901oao.r-jwli3a.r-1qd0xha.r-a023e6.r-16dba41.r-ad9z0x.r-bcqeeo.r-glunga.r-1jeg54m.r-qvutc0"));

		int nb=5;
		if (lFolwer.size()<nb) {
			nb=lFolwer.size();
		}
		for (int i=0;i<nb;i++) {

			//permet de laisser charger
			this.chargement();

			lFolwer =con.driver.findElements(By.cssSelector("#react-root div.css-901oao.r-jwli3a.r-1qd0xha.r-a023e6.r-16dba41.r-ad9z0x.r-bcqeeo.r-glunga.r-1jeg54m.r-qvutc0"));
			con.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

			lFolwer.get(i).click();

			String id = this.RecupererIdentifiant();

			Compte c1 = new Compte(compte);
			c1.save();
			Compte c2 = Compte.findByIdentifiant(id);


			ListeFollow lf = new ListeFollow (c1,c2);
			lf.save();
			lF.add(id);
			modele.ajoutTexte("Ajout dans \"Liste Follower\" : " + id);

			con.driver.navigate().back();

			System.out.println("-----------------");

		}
		con.driver.navigate().back();
		return lF;
	}
	
	public ArrayList<String> RecupererListeFollowes(String compte) throws SQLException{

		//permet de laisser charger
		this.chargement();

		ArrayList<String> lFes = new ArrayList<String>();

		con.driver.get(con.driver.getCurrentUrl()+"/following");

		List<WebElement> lFolwes =con.driver.findElements(By.cssSelector("#react-root div.css-901oao.r-jwli3a.r-1qd0xha.r-a023e6.r-16dba41.r-ad9z0x.r-bcqeeo.r-glunga.r-1jeg54m.r-qvutc0"));

		int nb=5;
		if (lFolwes.size()<nb) {
			nb=lFolwes.size();
		}
		for (int i=0;i<nb;i++) {

			//permet de laisser charger
			this.chargement();

			lFolwes =con.driver.findElements(By.cssSelector("#react-root div.css-901oao.r-jwli3a.r-1qd0xha.r-a023e6.r-16dba41.r-ad9z0x.r-bcqeeo.r-glunga.r-1jeg54m.r-qvutc0"));
			con.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

			lFolwes.get(i).click();

			String id = this.RecupererIdentifiant();

			Compte c1 = new Compte(compte);
			c1.save();
			Compte c2 = Compte.findByIdentifiant(id);


			ListeFollowes lf = new ListeFollowes (c1,c2);
			lf.save();
			lFes.add(id);
			modele.ajoutTexte("Ajout dans \"Liste Followes\" : " + id);

			con.driver.navigate().back();

			System.out.println("-----------------");

		}
		con.driver.navigate().back();
		return lFes;
	}

	/*
	 * Permet de tester si un compte a deja ete ou non analysé
	 */
	public void TestAnalyse(String compte) throws SQLException {

		Compte analy = Compte.findByIdentifiant(compte);

		if(analy == null) {
			this.anal = true;

		}else {
			if(analy.getAnalyser().equals("non")) {
				this.anal=true;
			}else {
				this.anal=false;
				modele.ajoutTexte(compte + "deja analysé");
			}
		}

	}

}
