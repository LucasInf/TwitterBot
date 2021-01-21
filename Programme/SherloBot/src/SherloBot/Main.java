package SherloBot;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import SherloBot.Compte;

import mvc.Controleur;
import mvc.Modele;
import mvc.Vue;

public class Main {

	public static void main(String args[]) throws SQLException{
		
		ListeFollowes.deleteTable();
		ListeFollow.deleteTable();
		Compte.deleteTable();		

		Compte.createTable();
		ListeFollow.createTable();
		ListeFollowes.createTable();

		Vue v = new Vue();

		Modele modele = new Modele(v);		


		//JPanel Nord
		JPanel panneauDeControle= new JPanel(new GridLayout(1,2));
		panneauDeControle.add(new JLabel("Donner un compte à analyser "+"    ",JLabel.CENTER));

		JTextField txt = new JTextField(10);
		txt.setPreferredSize(new Dimension(200,30));

		Controleur control= new Controleur(modele,txt);

		txt.addActionListener(control);


		panneauDeControle.add(txt);

		//Construction de l'IG dans une JFrame		 
		JFrame frame=new JFrame();
		frame.getContentPane().add(panneauDeControle,BorderLayout.NORTH);
		frame.getContentPane().add(new JScrollPane(v),BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(400,400));
		frame.setVisible(true);	


	}

}
