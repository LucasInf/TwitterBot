package SherloBot;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Compte {

	private int numCompte = -1;
	private String identifiant=null;
	private String pseudo;
	private String biographie;
	private String banniere;
	private String photoProfil;
	private String lieu;
	private String dateCreation;
	private String analyser="non";

	public Compte(String id, String p, String bi, String b, String ph, String l, String d) throws SQLException {
		this.identifiant=id;
		this.pseudo=p;
		this.biographie=bi;
		this.banniere=b;
		this.photoProfil=ph;
		this.lieu=l;
		this.dateCreation=d;

		Connection c = DBConnection.getDBConnection();

		String SQLPrep = "SELECT numCompte,analyser FROM Compte where identifiant=? and dateCreation=?;";
		PreparedStatement prep1 = c.prepareStatement(SQLPrep);
		prep1.setString(1, id);
		prep1.setString(2, d);
		prep1.execute();
		ResultSet rs = prep1.getResultSet();

		while (rs.next()) {
			numCompte = rs.getInt("numCompte");
		}
	}

	public Compte(String id) throws SQLException {
		this.identifiant=id;

		Connection c = DBConnection.getDBConnection();

		String SQLPrep = "SELECT numCompte,analyser FROM Compte where identifiant=?;";
		PreparedStatement prep1 = c.prepareStatement(SQLPrep);
		prep1.setString(1, id);
		prep1.execute();
		ResultSet rs = prep1.getResultSet();

		while (rs.next()) {
			numCompte = rs.getInt("numCompte");
			analyser = rs.getString("analyser");
		}
	}
	
	public Compte(String id, String analyse) throws SQLException {
		this.identifiant=id;
		this.analyser=analyse;

		Connection c = DBConnection.getDBConnection();

		String SQLPrep = "SELECT numCompte FROM Compte where identifiant=?;";
		PreparedStatement prep1 = c.prepareStatement(SQLPrep);
		prep1.setString(1, id);
		prep1.execute();
		ResultSet rs = prep1.getResultSet();

		while (rs.next()) {
			numCompte = rs.getInt("numCompte");
		}
	}

	public static Compte findByIdentifiant(String id) throws SQLException {
		Compte p = null;
		Connection c = DBConnection.getDBConnection();
		String FI="SELECT * FROM COMPTE WHERE identifiant = ?";
		PreparedStatement getS = c.prepareStatement(FI);
		getS.setString(1, id);
		ResultSet rs = getS.executeQuery();
		while (rs.next()) {
			String analyser = rs.getString("analyser");
			p = new Compte(id, analyser);
		}
		return p;

	}

	public static ArrayList<Compte> findByPseudo(String pseudo) throws SQLException {
		ArrayList<Compte> ps = new ArrayList<Compte>();
		Connection c = DBConnection.getDBConnection();
		String FA="SELECT * FROM SERIE WHERE pseudo = ?";
		PreparedStatement getS = c.prepareStatement(FA);
		getS.setString(1, pseudo);
		ResultSet rs = getS.executeQuery();
		while (rs.next()) {
			String id = rs.getString("identifiant");
			String biographie = rs.getString("biographie");
			String banniere = rs.getString("banniere");
			String photoProfil = rs.getString("photoProfil");
			String lieu = rs.getString("lieu");
			String dateCreation = rs.getString("dateCreation");
			Compte p = new Compte(id, pseudo, biographie, banniere, photoProfil, lieu, dateCreation);
			ps.add(p);
		}
		return ps;

	}

	public static ArrayList<Compte> findByBiographie(String biographie) throws SQLException {
		ArrayList<Compte> ps = new ArrayList<Compte>();
		Connection c = DBConnection.getDBConnection();
		String FA="SELECT * FROM SERIE WHERE biographie = ?";
		PreparedStatement getS = c.prepareStatement(FA);
		getS.setString(1, biographie);
		ResultSet rs = getS.executeQuery();
		while (rs.next()) {
			String id = rs.getString("identifiant");
			String pseudo = rs.getString("pseudo");
			String banniere = rs.getString("banniere");
			String photoProfil = rs.getString("photoProfil");
			String lieu = rs.getString("lieu");
			String dateCreation = rs.getString("dateCreation");
			Compte p = new Compte(id, pseudo, biographie, banniere, photoProfil, lieu, dateCreation);
			ps.add(p);
		}
		return ps;

	}

	public static Compte findByBanniere(String banniere) throws SQLException {
		Compte p = new Compte("", "", "", "", "", "", "");
		Connection c = DBConnection.getDBConnection();
		String FI="SELECT * FROM COMPTE WHERE banniere = ?";
		PreparedStatement getS = c.prepareStatement(FI);
		getS.setString(1, banniere);
		ResultSet rs = getS.executeQuery();
		while (rs.next()) {
			String id = rs.getString("identifiant");
			String pseudo = rs.getString("pseudo");
			String biographie = rs.getString("biographie");
			String photoProfil = rs.getString("photoProfil");
			String lieu = rs.getString("lieu");
			String dateCreation = rs.getString("dateCreation");
			p = new Compte(id, pseudo, biographie, banniere, photoProfil, lieu, dateCreation);
		}
		return p;

	}

	public static Compte findByPhotoProfil(String photoProfil) throws SQLException {
		Compte p = new Compte("", "", "", "", "", "", "");
		Connection c = DBConnection.getDBConnection();
		String FI="SELECT * FROM COMPTE WHERE photoProfil = ?";
		PreparedStatement getS = c.prepareStatement(FI);
		getS.setString(1, photoProfil);
		ResultSet rs = getS.executeQuery();
		while (rs.next()) {
			String id = rs.getString("identifiant");
			String pseudo = rs.getString("pseudo");
			String biographie = rs.getString("biographie");
			String banniere = rs.getString("banniere");
			String lieu = rs.getString("lieu");
			String dateCreation = rs.getString("dateCreation");
			p = new Compte(id, pseudo, biographie, banniere, photoProfil, lieu, dateCreation);
		}
		return p;

	}

	public static ArrayList<Compte> findByLieu(String lieu) throws SQLException {
		ArrayList<Compte> ps = new ArrayList<Compte>();
		Connection c = DBConnection.getDBConnection();
		String FA="SELECT * FROM SERIE WHERE lieu = ?";
		PreparedStatement getS = c.prepareStatement(FA);
		getS.setString(1, lieu);
		ResultSet rs = getS.executeQuery();
		while (rs.next()) {
			String id = rs.getString("identifiant");
			String pseudo = rs.getString("pseudo");
			String biographie = rs.getString("biographie");
			String banniere = rs.getString("banniere");
			String photoProfil = rs.getString("photoProfil");
			String dateCreation = rs.getString("dateCreation");
			Compte p = new Compte(id, pseudo, biographie, banniere, photoProfil, lieu, dateCreation);
			ps.add(p);
		}
		return ps;

	}

	public static ArrayList<Compte> findByDateCreation(String dateCreation) throws SQLException {
		ArrayList<Compte> ps = new ArrayList<Compte>();
		Connection c = DBConnection.getDBConnection();
		String FA="SELECT * FROM SERIE WHERE dateCreation = ?";
		PreparedStatement getS = c.prepareStatement(FA);
		getS.setString(1, dateCreation);
		ResultSet rs = getS.executeQuery();
		while (rs.next()) {
			String id = rs.getString("identifiant");
			String pseudo = rs.getString("pseudo");
			String biographie = rs.getString("biographie");
			String banniere = rs.getString("banniere");
			String photoProfil = rs.getString("photoProfil");
			String lieu = rs.getString("lieu");
			Compte p = new Compte(id, pseudo, biographie, banniere, photoProfil, lieu, dateCreation);
			ps.add(p);
		}
		return ps;

	}

	public static void createTable() throws SQLException {
		Connection c = DBConnection.getDBConnection();


		String createString = "CREATE TABLE IF NOT EXISTS Compte (" + "numCompte integer AUTO_INCREMENT, "+
				"identifiant varchar(50)," + 
				"pseudo varchar(50)," + 
				"biographie varchar(50)," + 
				"banniere varchar(50)," + "photoProfil varchar(50)," + "lieu varchar(50)," + "dateCreation varchar(50)," +"analyser varchar(50),"+ "PRIMARY KEY(numCompte))";
		Statement stmt = c.createStatement();
		stmt.executeUpdate(createString);
		createString = "ALTER TABLE COMPTE\n"+ 
				"ADD KEY IF NOT EXISTS FKCLnf (identifiant)";
		stmt = c.createStatement();
		stmt.executeUpdate(createString);
		System.out.println("Table Compte crée\n");		
	}

	public static void deleteTable() throws SQLException {
		Connection c = DBConnection.getDBConnection();
		String drop = "DROP TABLE IF EXISTS Compte";
		Statement stmt = c.createStatement();
		stmt.executeUpdate(drop);
		System.out.println("Supprime table Compte");

	}

	public void delete() throws SQLException {
		Connection c = DBConnection.getDBConnection();
		PreparedStatement prep = c.prepareStatement("DELETE FROM Compte WHERE identifiant=?");
		prep.setString(1, this.identifiant);
		prep.execute();
		System.out.println("Suppression dans Compte");
		this.numCompte=-1;

	}

	public void save() throws SQLException {
		if (this.numCompte==-1){
			this.saveNew();
		}else {
			this.update();
		}
	}

	/*@Override
	private void saveNew() {
		Connection c = DBConnection.getDBConnection();
		String SQLPrep = "INSERT INTO Compte (identifiant,pseudo,biographie,banniere,photoProfil,lieu,dateCreation) VALUES (?,?,?,?,?,?,?);";
		PreparedStatement prep = c.prepareStatement(SQLPrep, Statement.RETURN_GENERATED_KEYS);			
		prep.setInt(1, this.prix);
		prep.setString(2, this.type);
		prep.executeUpdate();
		System.out.println("ajout Compte");

		// recuperation de la derniere ligne ajoutee (auto increment)
		// recupere le nouvel id
		int autoInc = -1;
		ResultSet rs = prep.getGeneratedKeys();
		if (rs.next()) {
			autoInc = rs.getInt(1);
		}
		System.out.print("  ->  numCompte utilise lors de l'ajout : ");
		System.out.println(autoInc);
		this.numCompte=autoInc;
		System.out.println();

	}*/
	private void saveNew() throws SQLException {
		Connection c = DBConnection.getDBConnection();
		String SQLPrep = "INSERT INTO Compte (identifiant,analyser) VALUES (?,?);";
		PreparedStatement prep = c.prepareStatement(SQLPrep, Statement.RETURN_GENERATED_KEYS);			
		prep.setString(1, this.identifiant);
		prep.setString(2, this.analyser);
		prep.executeUpdate();
		System.out.println("ajout compte");

		int autoInc = -1;
		ResultSet rs = prep.getGeneratedKeys();
		if (rs.next()) {
			autoInc = rs.getInt(1);
		}
		System.out.print("  ->  numCompte utilise lors de l'ajout : ");
		System.out.println(autoInc);
		this.numCompte=autoInc;
		System.out.println();

	}

	private void update() throws SQLException {

		Connection c = DBConnection.getDBConnection();
		
		String SQLprep = "update Compte set identifiant=? where numCompte=?;";
		PreparedStatement prep = c.prepareStatement(SQLprep);
		prep.setString(1, this.identifiant);
		prep.setInt(2, this.numCompte);
		prep.execute();
		
		SQLprep = "update Compte set analyser=? where numCompte=?;";
		prep = c.prepareStatement(SQLprep);
		prep.setString(1, this.analyser);
		prep.setInt(2, this.numCompte);
		prep.execute();
		
		System.out.println("modification Compte");
		System.out.println();

	}

	@Override
	public String toString() {
		return "Compte [numCompte=" + numCompte + ", identifiant=" + identifiant + ", pseudo=" + pseudo
				+ ", biographie=" + biographie + ", banniere=" + banniere + ", photoProfil=" + photoProfil + ", lieu="
				+ lieu + ", dateCreation=" + dateCreation + "]";
	}

	public int getNumCompte() {
		return numCompte;
	}

	public void setNumCompte(int numCompte) {
		this.numCompte = numCompte;
	}

	public String getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getBiographie() {
		return biographie;
	}

	public void setBiographie(String biographie) {
		this.biographie = biographie;
	}

	public String getBanniere() {
		return banniere;
	}

	public void setBanniere(String banniere) {
		this.banniere = banniere;
	}

	public String getPhotoProfil() {
		return photoProfil;
	}

	public void setPhotoProfil(String photoProfil) {
		this.photoProfil = photoProfil;
	}

	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	public String getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getAnalyser() {
		return analyser;
	}

	public void setAnalyser(String analyser) {
		this.analyser = analyser;
	}



}
