package SherloBot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;




public class TestFollowes {

	private Compte c1,c2;
	private ListeFollowes l;
	@Before
	public void initialiserListeFollowes() throws SQLException {
		Compte.createTable();
		ListeFollowes.createTable();
		c1=new Compte("1");
		c2=new Compte("2");
		c1.save();
		c2.save();
		l=new ListeFollowes(c1, c2);
		l.save();
	}

	@After
	public void SupprimerListeFollowes() throws SQLException {
		ListeFollowes.deleteTable();
		Compte.deleteTable();
	}

	@Test
	public void TestFindByNumFollowesFonctionnel() throws SQLException {
		ListeFollowes l2=ListeFollowes.findByNumFollowes(1);
		assertEquals("pas le bon numéro d'identifiant pour le compte",l2.getIdentifiant(),"1");
		assertEquals("pas le bon numéro d'identifiant pour le followes",l2.getFollowes(),"2");
	}
	@Test
	public void TestFindByNumFollowesFaux() throws SQLException {
		ListeFollowes l2=ListeFollowes.findByNumFollowes(2);
		assertNull(l2);
	}

	@Test
	public void TestFindByIdentifiantFonctionnel() throws SQLException {
		ListeFollowes l2=ListeFollowes.findByIdentifiant("1");
		assertEquals("pas le bon numéro d'identifiant pour le followes",l2.getFollowes(),"2");
	}

	@Test
	public void TestFindByIdentifiantFaux() throws SQLException {
		ListeFollowes l2=ListeFollowes.findByIdentifiant("2");
		assertNull(l2);	
	}
	
	@Test
	public void TestFindByFollowesFonctionnel() throws SQLException {
		ListeFollowes l2=ListeFollowes.findByFollowes("2");
		assertEquals("pas le bon numéro d'identifiant pour le compte",l2.getIdentifiant(),"1");
	}
	
	@Test
	public void TestFindByFollowesFaux() throws SQLException {
		ListeFollowes l2=ListeFollowes.findByFollowes("1");
		assertNull(l2);	
	}
}
