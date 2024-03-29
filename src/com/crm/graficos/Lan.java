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
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
//import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatDraculaIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatSolarizedLightContrastIJTheme;

public class Lan {
	// CRM DE SERVICIOS FINANCIEROS CON DIRECTORIO DE CLIENTES AL QUE LES DAS
	// SERVICIOS


	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel(new FlatSolarizedLightContrastIJTheme());
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setMarco();

		System.out.println("Bienvenido");

	}

	public static void setMarco() {
		JFrame marco = new JFrame("CRM");
		// top,left
		// marco.setLocation(320,320);
		marco.setLocation(0, 0);

	/*	dim =marco.getToolkit().getScreenSize();
		marco.setSize(dim);
		marco.setUndecorated(true);
		marco.setVisible(true);*/
	

		JOptionPane.showConfirmDialog(marco, "Bienvenido", null, JOptionPane.CLOSED_OPTION);

		Toolkit.getDefaultToolkit().beep();

		// es abstracta, toolkit no puede crear new, no es instanciable
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image imagen = tk.getImage("hipoteca.png");
		marco.setIconImage(imagen);
		marco.setMinimumSize(new Dimension(1200,900));

		Toolkit.getDefaultToolkit().beep();
		// almacenar las dimensiones de la pantalla del usuario
		Dimension dim = tk.getDefaultToolkit().getScreenSize();
		int anchoM = (int) (dim.width /2), altoM = (int) (dim.height /2);
		int anchoM1 = (int) (dim.width /2), altoM1 = (int) (dim.height /2);
		int anchoM2 = (int) (dim.width /2), altoM2 = (int) (dim.height /2);
		int anchoM3 = (int)(dim.width/2), altoM3 = (int)(dim.height/2);
		int anchoM4 = (int)(dim.width/2), altoM4 = (int)(dim.height/2);

		// crear pestaņas dentro del panel:
		JTabbedPane pestanha = new JTabbedPane();
		
		pestanha.setForeground(Color.gray);
		pestanha.add("Directorio cliente", new PanelCliente(anchoM, altoM));
		pestanha.add("Hipotecas", new PanelHipoteca(anchoM1, altoM1));
		pestanha.add("Requisitos", new PanelRequisitos(anchoM2, altoM2));
		pestanha.add("Encargos", new PanelEncargos(anchoM3,altoM3));
		pestanha.add("Usuarios", new PanelUsuario(anchoM4,altoM4));
		
		

		// Aqui es donde se visualiza, aņadiendolo al marco

		marco.getContentPane().add(pestanha);
		// imprimir por consola la resolucion de la pantalla
		System.out.println(dim.width + "px x " + dim.height + "px ");
		// especificamos al programa que finalice cuando el marco se cierre
		marco.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		// Se pon visible al final
		marco.setVisible(true);

	}
}
