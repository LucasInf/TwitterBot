package SherloBot;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Limaga.DBConnection;
import Limaga.Formule;

public class Tweet implements ActiveRecord{

	private int IdTweet;
	private String IdentifiantTweet;
	private String Tweet;
	
	public Tweet(String idT, String t) throws SQLException{
		this.IdentifiantTweet=idT;
		this.Tweet=t;
	}
	
	public Tweet(int id, String idT, String t) throws SQLException{
		this.IdTweet=id;
		this.IdentifiantTweet=idT;
		this.Tweet=t;
	}
	
	
	public Tweet findByIdTweet(int id) {
		Tweet t = null;
		{
			Connection connect = DBConnection.getDBConnection();
			String SQLPrep = "SELECT * FROM Tweet WHERE IdTweet=?;";
			PreparedStatement prep1 = connect.prepareStatement(SQLPrep);
			prep1.setInt(1, id);
			prep1.execute();
			ResultSet rs = prep1.getResultSet();
			// s'il y a un resultat
			while (rs.next()) {
				String IdentifiantTweet = rs.getString("IdentifiantTweet");
				String Tweet = rs.getString("Tweet");

				t = new Tweet(id, IdentifiantTweet, Tweet);
			}
		}
		return t;
	}
	
	public ArrayList<Tweet> findByIdentifiantTweet(String iden) {
		ArrayList<Tweet> l =  new ArrayList<Tweet>();
		{
			Connection connect = DBConnection.getDBConnection();
			String SQLPrep = "SELECT * FROM Tweet WHERE IdTweet=?;";
			PreparedStatement prep1 = connect.prepareStatement(SQLPrep);
			prep1.setString(1, iden);
			prep1.execute();
			ResultSet rs = prep1.getResultSet();
			// s'il y a un resultat
			while (rs.next()) {
				int IdTweet = rs.getInt("IdTweet");
				String Tweet = rs.getString("Tweet");

				Tweet t = new Tweet(IdTweet, iden, Tweet);
				l.add(t);
			}
		}
		return l;
	}
	
	public ArrayList<Tweet> findByTweet(String tw) {
		ArrayList<Tweet> l =  new ArrayList<Tweet>();
		{
			Connection connect = DBConnection.getDBConnection();
			String SQLPrep = "SELECT * FROM Tweet WHERE IdTweet=?;";
			PreparedStatement prep1 = connect.prepareStatement(SQLPrep);
			prep1.setString(1, tw);
			prep1.execute();
			ResultSet rs = prep1.getResultSet();
			// s'il y a un resultat
			while (rs.next()) {
				int IdTweet = rs.getInt("IdTweet");
				String IdentifiantTweet = rs.getString("IdentifiantTweet");

				Tweet t = new Tweet(IdTweet, IdentifiantTweet, tw);
				l.add(t);
			}
		}
		return l;
	}
	
	@Override
	public void createTable() {
		{
			Connection connect = DBConnection.getDBConnection();
			String createString = "CREATE TABLE IF NOT EXISTS Tweet ( " + "IdTweet INTEGER  AUTO_INCREMENT, "
					+ "IdentifiantTweet varchar(50)," + "Tweet varchar(280)," + "PRIMARY KEY (IdTweet))";
			Statement stmt = connect.createStatement();
			stmt.executeUpdate(createString);
			System.out.println("Table Tweet crée\n");
		}
		
	}

	@Override
	public void deleteTable() {
		{
			Connection connect = DBConnection.getDBConnection();
			String drop = "DROP TABLE IF EXISTS Tweet";
			Statement stmt = connect.createStatement();
			stmt.executeUpdate(drop);
			System.out.println("Supprime table Tweet");
		}
		
	}

	@Override
	public void delete() {
		Connection c = DBConnection.getDBConnection();
		PreparedStatement prep = c.prepareStatement("DELETE FROM Tweet WHERE IdTweet =? and IdentifiantTweet =? and Tweet=? ");
		prep.setInt(1, this.idTweet);
		prep.setString(2, this.IdentifiantTweet);
		prep.setString(3, this.Tweet);
		prep.execute();
		System.out.println("Suppression dans Tweet");
		this.idTweet=-1;
		
	}

	@Override
	public void save() {
		if (this.idTweet==-1){
			this.saveNew();
		}else {
			this.update();
		}
		
	}

	@Override
	public void saveNew() {
		{
			Connection connect = DBConnection.getDBConnection();
			String SQLPrep = "INSERT INTO Tweet (IdentifiantTweet,Tweet) VALUES (?,?);";
			PreparedStatement prep = connect.prepareStatement(SQLPrep, Statement.RETURN_GENERATED_KEYS);			
			prep.setString(1, this.IdentifiantTweet);
			prep.setString(2, this.Tweet);
			prep.executeUpdate();
			System.out.println("ajout tweet");

			// recuperation de la derniere ligne ajoutee (auto increment)
			// recupere le nouvel id
			int autoInc = -1;
			ResultSet rs = prep.getGeneratedKeys();
			if (rs.next()) {
				autoInc = rs.getInt(1);
			}
			System.out.print("  ->  idTweet utilise lors de l'ajout : ");
			System.out.println(autoInc);
			this.IdTweet=autoInc;
			System.out.println();
		}
		
	}

	@Override
	public void update() {
		{
			Connection connect = DBConnection.getDBConnection();
			String SQLprep = "update Tweet set IdTweet=? and IdentifiantTweet=? where Tweet=?;";
			PreparedStatement prep = connect.prepareStatement(SQLprep);
			prep.setInt(1, this.IdTweet);
			prep.setString(2, this.IdentifiantTweet);
			prep.setString(3, this.Tweet);
			prep.execute();
			System.out.println("modification Tweet");
			System.out.println();
		}
		
	}

}
