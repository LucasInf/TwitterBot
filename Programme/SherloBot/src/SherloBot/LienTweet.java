package SherloBot;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LienTweet{
	
	private String identifiant;
	private int idTweet;	
	private String status;
	
	public LienTweet(String i, int idt, String s) {
		this.identifiant=i;
		this.idTweet=idt;
		this.status=s;
	}
	
	public LienTweet findByIdentifiant(String id) throws SQLException {
		LienTweet l = new LienTweet("",0, "");
        Connection c = DBConnection.getDBConnection();
        String FI="SELECT * FROM LIENTWEET WHERE identifiant = ?";
        PreparedStatement getS = c.prepareStatement(FI);
        getS.setString(1, id);
        ResultSet rs = getS.executeQuery();
        while (rs.next()) {
        	int idTweet = rs.getInt("idTweet");
            String status = rs.getString("status");
            l = new LienTweet(id, idTweet, status);
        }
        return l;
		
	}
	
	public LienTweet findByIdTweet(int idT) throws SQLException {
		LienTweet l = new LienTweet("",0, "");
        Connection c = DBConnection.getDBConnection();
        String FI="SELECT * FROM LIENTWEET WHERE idTweet = ?";
        PreparedStatement getS = c.prepareStatement(FI);
        getS.setInt(1, idT);
        ResultSet rs = getS.executeQuery();
        while (rs.next()) {
        	String identifiant = rs.getString("identifiant");
            String status = rs.getString("status");
            l = new LienTweet(identifiant, idT, status);
        }
        return l;
		
	}
	
	public LienTweet findByFollow(String s) throws SQLException {
		LienTweet l = new LienTweet("",0, "");
        Connection c = DBConnection.getDBConnection();
        String FI="SELECT * FROM LIENTWEET WHERE identifiant = ?";
        PreparedStatement getS = c.prepareStatement(FI);
        getS.setString(1, s);
        ResultSet rs = getS.executeQuery();
        while (rs.next()) {
        	String identifiant = rs.getString("identifiant");
        	int idTweet = rs.getInt("idTweet");
            l = new LienTweet(identifiant, idTweet, s);
        }
        return l;
		
	}

	@Override
	public void createTable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteTable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveNew() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}


}
