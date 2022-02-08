package com.crm.graficos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class PanelRequisitos extends JPanel {

	JCheckBox foto_dni, foto_3nomina, copia_contrato, foto_movimientosbanco, vidalaboral, foto_recibosHipo,
			foto_reciboContribucion, foto_irpf;

	public PanelRequisitos(int ancho, int alto) {
		setLayout(new BorderLayout());
		add(setPanelMayor(alto, ancho, setPanel1(alto, ancho), setPanel2(alto, ancho)), BorderLayout.NORTH);
		add(setPanelSur(alto, ancho), BorderLayout.SOUTH);

	}

	public JPanel setPanelMayor(int alto, int ancho, JPanel p1, JPanel p2) {
		JPanel panelMayor = new JPanel();
		panelMayor.getBorder();
		panelMayor.setLayout(new BorderLayout());
		panelMayor.setPreferredSize(new Dimension((int) (ancho * 0.8), (int) (alto * 2.5)));
		panelMayor.add(p1, BorderLayout.WEST);
		panelMayor.add(p2, BorderLayout.EAST);
		return panelMayor;

	}

	public JPanel setPanel1(int alto, int ancho) {
		JPanel panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

		// panel1.setBorder(BorderFactory.createLoweredBevelBorder());
		JLabel titulo = new JLabel("TRABAJADORES POR CUENTA AJENA");
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
		panel1.setPreferredSize(new Dimension((int) (ancho * 0.8), (int) (alto * 2.2)));

		return panel1;
	}

	public JPanel setPanel2(int alto, int ancho) {
		JPanel panel2 = new JPanel();
		panel2.setLayout(new BorderLayout());

		return panel2;
	}

	public JPanel setPanelSur(int alto, int ancho) {
		JPanel panelSur = new JPanel();
		panelSur.setLayout(new BorderLayout());

		JLabel hoka = new JLabel("Sin c");
		hoka.setForeground(Color.BLACK);
		JTextArea dx = new JTextArea();

		panelSur.add(hoka);
		panelSur.add(dx);
		panelSur.setPreferredSize(new Dimension((int) (ancho * 0.01), (int) (alto * 1.2)));

		return panelSur;

	}

}
