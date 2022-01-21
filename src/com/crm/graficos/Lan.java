package com.crm.graficos;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

public class Lan {
	// CRM DE SERVICIOS FINANCIEROS CON DIRECTORIO DE CLIENTES AL QUE LES DAS
	// SERVICIOS

	public static void main(String[] args) {
		setMarco();
		// insertar lookandfeel para el estilo del crm

		
		try {
			JFrame.setDefaultLookAndFeelDecorated(true);
			JDialog.setDefaultLookAndFeelDecorated(true);
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void setMarco() {
		JFrame marco = new JFrame("CRM");
		// top,left
		// marco.setLocation(320,320);
		marco.setLocation(0, 0);
		
		marco.getToolkit().getScreenSize();
		marco.setForeground(Color.RED);
	
		JOptionPane.showConfirmDialog(marco, "Bienvenido", null, JOptionPane.CLOSED_OPTION);
		
		Toolkit.getDefaultToolkit().beep();
		
		
		// es abstracta, toolkit no puede crear new, no es instanciable
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image imagen = tk.getImage("hipoteca.png");
		marco.setIconImage(imagen);
		marco.setMinimumSize(new Dimension(1000, 950));
		
		
		Toolkit.getDefaultToolkit().beep();
		// almacenar las dimensiones de la pantalla del usuario
		Dimension dim = tk.getScreenSize();
		int anchoM = (int) (dim.width / 2), altoM = (int) (dim.height / 2);
		int anchoM1 = (int) (dim.width / 2), altoM1 = (int) (dim.height / 2);

		// crear pestañas dentro del panel:
		JTabbedPane pestanha = new JTabbedPane();
		pestanha.setForeground(Color.gray);
		pestanha.add("Directorio cliente", new PanelCliente(anchoM, altoM));// poner tabla cliente
		pestanha.add("Hipotecas", new PanelHipoteca(anchoM1, altoM1));

		// Aqui es donde se visualiza, añadiendolo al marco

		marco.getContentPane().add(pestanha);
		// imprimir por consola la resolucion de la pantalla
		System.out.println(dim.width + "px x " + dim.height + "px");
		// especificamos al programa que finalice cuando el marco se cierre
		marco.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		marco.setMinimumSize(new Dimension(800, 700));

		// Se pon visible al final
		marco.setVisible(true);

	}
}
