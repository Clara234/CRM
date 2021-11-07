package com.crm.graficos;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PanelCliente  extends JPanel{
	DefaultTableModel dtm;
	
	public PanelCliente(int ancho, int alto){
		
		setLayout(new BorderLayout());
	
	
		
		
	}
	
	public JScrollPane setTabla(int ancho, int alto) {
		
		dtm = new DefaultTableModel();
		dtm.addColumn("Datos Personales");
		dtm.addColumn("Datos Profesionales");
		dtm.addColumn("Datos Patrimoniales");
		
		
		JTable tabla = new JTable(dtm);
		JScrollPane sp = new JScrollPane(tabla);
		
		return null;
		
	}
	
	public JPanel setPanelEste(int alto, int ancho, JPanel p1, JPanel p2) {
		JPanel panelEste = new JPanel();
		panelEste.setLayout(new BorderLayout());
		panelEste.setPreferredSize(new Dimension((int)(ancho*0.15), (int)(alto*0.8)));
		panelEste.add(p1, BorderLayout.NORTH);
		panelEste.add(p2, BorderLayout.CENTER);
		return panelEste;
	}

}
