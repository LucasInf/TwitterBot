package mvc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JTextField;


public class Controleur implements ActionListener {
	
	private Modele modele;
	private JTextField j;
	
	public Controleur(Modele m, JTextField j) {

		this.modele=m;
		this.j=j;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String txt=j.getText();
		try {
			modele.modifier(txt);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		j.setText("");
		
	}


}
