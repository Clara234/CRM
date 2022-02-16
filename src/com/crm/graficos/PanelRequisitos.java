package com.crm.graficos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class PanelRequisitos extends JPanel {

	JCheckBox
	// checbox de trabajadores
	foto_dni, foto_3nomina, copia_contrato, foto_movimientosbanco, vidalaboral, foto_recibosHipo,
			foto_reciboContribucion, foto_irpf,
			// trabajadores cuenta ajena
			dni2, nomina2, copia_contrato2, movimiento_bancario2, vidalaboral2, ultimosrecibos2,
			// autonomos para nostros
			dni_auto, ultima_irpf_auto, alta_iae_auto, ultimos_movimientos_autonomo, ultimos_recibos_auto,
			ultimo_recibo_auto, foto_pagos_auto, foto_ejercicio_auto,
			// autonomos para banco
			dni_auto2, ultima_irpf_auto2, alta_iae_auto2, ultimos_movimientos_autonomo2, ultimos_recibos_auto2,
			ultimo_recibo_auto2;

	public PanelRequisitos(int ancho, int alto) {
		setLayout(new BorderLayout());
		add(setPanelMayor(alto, ancho, setPanel1(alto, ancho), setPanel2(alto, ancho)), BorderLayout.NORTH);
		add(setPanelSur(alto, ancho, setPanelEspacio1(alto, ancho), setPanelDatos(alto, ancho), setPanelEspacio2(alto, ancho)),BorderLayout.SOUTH);

	}

	public JPanel setPanelMayor(int alto, int ancho, JPanel p1, JPanel p2) {
		JPanel panelMayor = new JPanel();
		panelMayor.getBorder();
		panelMayor.setLayout(new BorderLayout());
		panelMayor.setPreferredSize(new Dimension((int) (ancho * 0.6), (int) (alto * 1.2)));
		panelMayor.add(p1, BorderLayout.WEST);
		panelMayor.add(p2, BorderLayout.EAST);
		return panelMayor;

	}

	public JPanel setPanel1(int alto, int ancho) {
		JPanel panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

		 //panel1.setBorder(BorderFactory.createLoweredBevelBorder());
		JLabel titulo = new JLabel("TRABAJADORES POR CUENTA AJENA(para nosotros)");
		Font f = new Font("Italic", Font.ITALIC, 12);
		titulo.setFont(f);

		foto_dni = new JCheckBox("Fotocopia DNI/NIE");
		foto_dni.setForeground(Color.BLACK);

		foto_3nomina = new JCheckBox("Fotocopia de las tres ultimas nominas");
		foto_3nomina.setForeground(Color.BLACK);

		copia_contrato = new JCheckBox("Copia del contrato laboral (si <3)");
		copia_contrato.setForeground(Color.BLACK);

		foto_movimientosbanco = new JCheckBox("Fotocopia de los  ultimos movimientos bancarios(3 meses)");
		foto_movimientosbanco.setForeground(Color.BLACK);

		vidalaboral = new JCheckBox("Vida Laboral");
		vidalaboral.setForeground(Color.BLACK);

		foto_recibosHipo = new JCheckBox("Fotocopia de los ultimos recibos de hipoteca/alquiler/prestamos");
		foto_recibosHipo.setForeground(Color.BLACK);
		foto_reciboContribucion = new JCheckBox("Fotocopia del ultimo recibo de contribucion");
		foto_reciboContribucion.setForeground(Color.BLACK);
		foto_irpf = new JCheckBox("Fotocopia ultima declaracion irpf");
		foto_irpf.setForeground(Color.BLACK);

		JLabel titulo2 = new JLabel("TRABAJADORES POR CUENTA AJENA(para el banco)");
		titulo2.setFont(f);

		dni2 = new JCheckBox("Fotocopia DNI/NIE");
		dni2.setForeground(Color.BLACK);

		nomina2 = new JCheckBox("Fotocopia de las tres ultimas nominas");
		nomina2.setForeground(Color.BLACK);

		copia_contrato2 = new JCheckBox("Copia del contrato laboral (si <3)");
		copia_contrato2.setForeground(Color.BLACK);

		movimiento_bancario2 = new JCheckBox("Fotocopia de los  ultimos movimientos bancarios(3 meses)");
		movimiento_bancario2.setForeground(Color.BLACK);

		vidalaboral2 = new JCheckBox("Vida Laboral");
		vidalaboral2.setForeground(Color.BLACK);

		ultimosrecibos2 = new JCheckBox("Fotocopia de los ultimos recibos de hipoteca/alquiler/prestamos");
		ultimosrecibos2.setForeground(Color.BLACK);

		panel1.add(titulo);
		panel1.add(Box.createRigidArea(new Dimension(0, 10)));
		panel1.add(foto_dni);
		panel1.add(Box.createRigidArea(new Dimension(0, 10)));
		panel1.add(foto_3nomina);
		panel1.add(Box.createRigidArea(new Dimension(0, 10)));
		panel1.add(copia_contrato);
		panel1.add(Box.createRigidArea(new Dimension(0, 10)));
		panel1.add(foto_movimientosbanco);
		panel1.add(Box.createRigidArea(new Dimension(0, 10)));
		panel1.add(vidalaboral);
		panel1.add(Box.createRigidArea(new Dimension(0, 10)));
		panel1.add(foto_recibosHipo);
		panel1.add(Box.createRigidArea(new Dimension(0, 10)));
		panel1.add(foto_reciboContribucion);
		panel1.add(Box.createRigidArea(new Dimension(0, 10)));
		panel1.add(foto_irpf);
		panel1.add(Box.createRigidArea(new Dimension(0, 10)));
		panel1.add(titulo2);
		panel1.add(Box.createRigidArea(new Dimension(0, 10)));
		panel1.add(dni2);
		panel1.add(Box.createRigidArea(new Dimension(0, 10)));
		panel1.add(nomina2);
		panel1.add(Box.createRigidArea(new Dimension(0, 10)));
		panel1.add(copia_contrato2);
		panel1.add(Box.createRigidArea(new Dimension(0, 10)));
		panel1.add(movimiento_bancario2);
		panel1.add(Box.createRigidArea(new Dimension(0, 10)));
		panel1.add(vidalaboral2);
		panel1.add(Box.createRigidArea(new Dimension(0, 10)));
		panel1.add(ultimosrecibos2);

		panel1.setPreferredSize(new Dimension((int) (ancho * 0.7), (int) (alto * 2.2)));

		return panel1;
	}

	public JPanel setPanel2(int alto, int ancho) {
		JPanel panel2 = new JPanel();
		panel2.setLayout(new BorderLayout());
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
       //panel2.setBorder(BorderFactory.createLoweredBevelBorder());
		JLabel titulo3 = new JLabel("TRABAJADORES AUTONOMOS(para nosotros)");
		Font f = new Font("Italic", Font.ITALIC, 12);
		titulo3.setFont(f);
		dni_auto = new JCheckBox("Fotocopia DNI/NIE");
		dni_auto.setForeground(Color.BLACK);

		ultima_irpf_auto = new JCheckBox("Fotocopia de las tres ultimas nominas");
		ultima_irpf_auto.setForeground(Color.BLACK);

		alta_iae_auto = new JCheckBox("Copia del contrato laboral (si <3)");
		alta_iae_auto.setForeground(Color.BLACK);

		ultimos_movimientos_autonomo = new JCheckBox("Fotocopia de los  ultimos movimientos bancarios(3 meses)");
		ultimos_movimientos_autonomo.setForeground(Color.BLACK);

		ultimos_recibos_auto = new JCheckBox("Vida Laboral");
		ultimos_recibos_auto.setForeground(Color.BLACK);

		ultimo_recibo_auto = new JCheckBox("Fotocopia de los ultimos recibos de hipoteca/alquiler/prestamos");
		ultimo_recibo_auto.setForeground(Color.BLACK);
		foto_pagos_auto = new JCheckBox("Fotocopia del ultimo recibo de contribucion");
		foto_pagos_auto.setForeground(Color.BLACK);
		foto_ejercicio_auto = new JCheckBox("Fotocopia ultima declaracion irpf");
		foto_ejercicio_auto.setForeground(Color.BLACK);

		JLabel titulo4 = new JLabel("TRABAJADORES AUTONOMOS(para el banco)");
		titulo4.setFont(f);

		dni_auto2 = new JCheckBox("Fotocopia DNI/NIE");
		dni_auto2.setForeground(Color.BLACK);

		ultima_irpf_auto2 = new JCheckBox("Fotocopia de las tres ultimas nominas");
		ultima_irpf_auto2.setForeground(Color.BLACK);

		alta_iae_auto2 = new JCheckBox("Copia del contrato laboral (si <3)");
		alta_iae_auto2.setForeground(Color.BLACK);

		ultimos_movimientos_autonomo2 = new JCheckBox("Fotocopia de los  ultimos movimientos bancarios(3 meses)");
		ultimos_movimientos_autonomo2.setForeground(Color.BLACK);

		ultimos_recibos_auto2 = new JCheckBox("Vida Laboral");
		ultimos_recibos_auto2.setForeground(Color.BLACK);

		ultimo_recibo_auto2 = new JCheckBox("Fotocopia de los ultimos recibos de hipoteca/alquiler/prestamos");
		ultimo_recibo_auto2.setForeground(Color.BLACK);

		panel2.add(titulo3);
		panel2.add(Box.createRigidArea(new Dimension(0, 10)));
		panel2.add(dni_auto);
		panel2.add(Box.createRigidArea(new Dimension(0, 10)));
		panel2.add(ultima_irpf_auto);
		panel2.add(Box.createRigidArea(new Dimension(0, 10)));
		panel2.add(alta_iae_auto);
		panel2.add(Box.createRigidArea(new Dimension(0, 10)));
		panel2.add(ultimos_movimientos_autonomo);
		panel2.add(Box.createRigidArea(new Dimension(0, 10)));
		panel2.add(ultimos_recibos_auto);
		panel2.add(Box.createRigidArea(new Dimension(0, 10)));
		panel2.add(ultimo_recibo_auto);
		panel2.add(Box.createRigidArea(new Dimension(0, 10)));
		panel2.add(foto_pagos_auto);
		panel2.add(Box.createRigidArea(new Dimension(0, 10)));
		panel2.add(foto_ejercicio_auto);
		panel2.add(Box.createRigidArea(new Dimension(0, 10)));
		panel2.add(titulo4);
		panel2.add(dni_auto2);
		panel2.add(Box.createRigidArea(new Dimension(0, 10)));
		panel2.add(ultima_irpf_auto2);
		panel2.add(Box.createRigidArea(new Dimension(0, 10)));
		panel2.add(alta_iae_auto2);
		panel2.add(Box.createRigidArea(new Dimension(0, 10)));
		panel2.add(ultimos_movimientos_autonomo2);
		panel2.add(Box.createRigidArea(new Dimension(0, 10)));
		panel2.add(ultimos_recibos_auto2);
		panel2.add(Box.createRigidArea(new Dimension(0, 10)));
		panel2.add(ultimo_recibo_auto2);
		panel2.setPreferredSize(new Dimension((int) (ancho * 0.7), (int) (alto * 2.2)));
		return panel2;
	}

	public JPanel setPanelSur(int alto, int ancho, JPanel p1, JPanel p2,JPanel p3) {
		JPanel panelSur = new JPanel();
		panelSur.getBorder();
		panelSur.setLayout(new BorderLayout());
		panelSur.setPreferredSize(new Dimension((int) (ancho * 0.6), (int) (alto * 0.2)));
		panelSur.add(p1, BorderLayout.EAST);
		panelSur.add(p2, BorderLayout.CENTER);
		panelSur.add(p3, BorderLayout.WEST);
		return panelSur;
		
		
	}
	
	public JPanel setPanelEspacio1(int ancho, int alto) {
		JPanel espacio1 = new JPanel();
	
		espacio1.setPreferredSize(new Dimension((int) (ancho * 0.6), (int) (alto * 0.8)));
		return espacio1;
	}
	
	public JPanel setPanelDatos(int ancho, int alto) {
		JPanel panelDatos = new JPanel();
		panelDatos.setBorder(BorderFactory.createLoweredBevelBorder());
		panelDatos.setLayout(new BorderLayout());
		panelDatos.setLayout(new BoxLayout(panelDatos, BoxLayout.X_AXIS));

		JTextArea dx = new JTextArea("Comentarios");
		Font f = new Font("Times New Roman",Font.ITALIC,12);
		dx.setFont(f);
		dx.setMaximumSize(new Dimension (250,20));
		JScrollPane hoka = new JScrollPane(dx);
		hoka.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
          hoka.setMaximumSize(new Dimension(250,40));
   

		JButton ihj = new JButton("Registrar");
		ihj.setForeground(Color.CYAN);
        ihj.setMaximumSize(new Dimension(100,30));
		JLabel busca = new JLabel("DNI NIE");
		busca.setSize(250, 20);
		JButton busqueda = new JButton("BUSCAR");
		
		busqueda.setMaximumSize(new Dimension(100,30));
		busqueda.setForeground(Color.CYAN);

		
		
		panelDatos.add(ihj);
		panelDatos.add(Box.createRigidArea(new Dimension(0, 10)));
		panelDatos.add(hoka);
		panelDatos.add(Box.createRigidArea(new Dimension(0, 10)));
		panelDatos.add(busca);
		panelDatos.add(busqueda);
		panelDatos.setLocation(700, 400);
		panelDatos.setPreferredSize(new Dimension((int) (ancho * 0.7), (int) (alto * 0.4)));

		return panelDatos;
	}
	
	
	public JPanel setPanelEspacio2(int ancho, int alto) {
		JPanel espacio2 = new JPanel();
		
		espacio2.setPreferredSize(new Dimension((int) (ancho * 0.4), (int) (alto * 0.5)));
	
		return espacio2;
	}

}
