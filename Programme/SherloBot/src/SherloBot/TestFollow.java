package SherloBot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;




public class TestFollow {

	private Compte c1,c2;
	private ListeFollow l;
	@Before
	public void initialiserListeFollow() throws SQLException {
		Compte.createTable();
		c1=new Compte("1");
		c2=new Compte("2");
		c1.save();
		c2.save();
		ListeFollow.createTable();
		l=new ListeFollow(c1, c2);
		l.save();
	}

	@After
	public void SupprimerListeFollow() throws SQLException {
		ListeFollow.deleteTable();
		Compte.deleteTable();
	}

	@Test
	public void TestFindByNumFollowFonctionnel() throws SQLException {
		ListeFollow l2=ListeFollow.findByNumFollow(1);
		assertEquals("pas le bon numéro d'identifiant pour le compte",l2.getIdentifiant(),"1");
		assertEquals("pas le bon numéro d'identifiant pour le follower",l2.getFollow(),"2");
	}
	@Test
	public void TestFindByNumFollowFaux() throws SQLException {
		ListeFollow l2=ListeFollow.findByNumFollow(2);
		assertNull(l2);
	}

	@Test
	public void TestFindByIdentifiantFonctionnel() throws SQLException {
		ListeFollow l2=ListeFollow.findByIdentifiant("1");
		assertEquals("pas le bon numéro d'identifiant pour le follower",l2.getFollow(),"2");
	}

	@Test
	public void TestFindByIdentifiantFaux() throws SQLException {
		ListeFollow l2=ListeFollow.findByIdentifiant("2");
		assertNull(l2);	
	}
	
	@Test
	public void TestFindByFollowFonctionnel() throws SQLException {
		ListeFollow l2=ListeFollow.findByFollow("2");
		assertEquals("pas le bon numéro d'identifiant pour le compte",l2.getIdentifiant(),"1");
	}
	
	@Test
	public void TestFindByFollowFaux() throws SQLException {
		ListeFollow l2=ListeFollow.findByFollow("1");
		assertNull(l2);	
	}
}
