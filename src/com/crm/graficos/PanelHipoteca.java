package com.crm.graficos;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class PanelHipoteca  extends JPanel{
	JTable tabla;
	DefaultTableModel dtm;
	
	public PanelHipoteca(int ancho, int alto) {
		//disposicion de los objetos
		setLayout(new BorderLayout());
		add(setPanelizq(alto, ancho), BorderLayout.WEST);
		add(setPanelcentro(alto, ancho), BorderLayout.CENTER);
		add(setPaneldch(alto, ancho), BorderLayout.EAST);
		add(setTabla(alto, ancho), BorderLayout.SOUTH);
		
	}
	
	public  JPanel setPanelizq(int alto, int ancho) {
		JPanel panelIzq = new JPanel();
		panelIzq.setLocation(250, 100);
		dtm = new DefaultTableModel();
		dtm.addColumn("Caracteristicas");
		return panelIzq;
		
		 
		
	}
	
	public JPanel setPanelcentro(int alto, int ancho) {
		JPanel panelCentro = new JPanel();
		panelCentro.setLocation(50, 50);
		dtm = new DefaultTableModel();
		dtm.addColumn("DX");
		return panelCentro;
		
	}
	
	public JPanel setPaneldch(int alto, int ancho) {
		JPanel panelDch = new JPanel();
		panelDch.setLocation(100, 250);
		dtm = new DefaultTableModel();
		dtm.addColumn("PM");
		return panelDch;
		
	}
	
	public JPanel setTabla(int alto, int ancho) {
		JPanel tabla = new JPanel();
		JTable table = new JTable();
		table.setLocation(250, 250);
		dtm = new DefaultTableModel();
		dtm.addColumn("ef");
		return tabla;
	}


}
