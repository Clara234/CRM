package com.crm.graficos;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.print.PageFormat;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.print.Printable;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.print.PrintException;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.crm.auxiliares.WordProcessing;
import com.crm.persistencia.ConfigDir;
import com.crm.persistencia.MisConexiones;
import com.crm.pojos.Hipotecado;

public class PanelHipoteca extends JPanel {
	DefaultTableModel dtm;
	JTable tabla;
	MisConexiones c;
	PreparedStatement ps;
	Hipotecado seleccionado;
	ResultSet rs;

	JTextField finalidad, valorAdquisicion, valorImporte, plazo, direccion, cargas, vinculacion, busquedacliente,
			// datos hipotecado
			dninie, apellido1, apellido2, nombre, fechaNacimiento, profesion, domicilio, poblacion, codigoPostal,
			nombreEmpresa, actividad, antiguedad, puesto, direccionEmpresa, contactoEmpresa, ingresosFijos,

			ingresosVariables, gastosAlquiler, gastosHipoteca, otros, valor, cargasVivienda,
			// datos conyuge
			dninie_conyuge, apellido1_conyuge, apellido2_conyuge, nombre_conyuge, fechaNacimiento_conyuge,
			profesion_conyuge, domicilio_conyuge, poblacion_conyuge, codigoPostal_conyuge, nombreEmpresa_conyuge,
			actividad_conyuge, antiguedad_conyuge, puesto_conyuge, direccionEmpresa_conyuge, contactoEmpresa_conyuge,
			ingresosFijos_conyuge,

			ingresosVariables_conyuge, gastosAlquiler_conyuge, gastosHipoteca_conyuge, otros_conyuge, valor_conyuge,
			cargasVivienda_conyuge;

	JButton imprimir, insertar, limpiar, b_dniConyuge,b_buscarCliente;
	JCheckBox // datos hipotecado
	chb_editar, chb_propiedad, chb_escritura, chb_contratoPrivado, chb_otrosGastos, chb_padres, chb_alquiler, chb_fijo,
			chb_temporal, chb_autonomo, chb_otrosCosas,

			// datos conyuge
			chb_propiedad_conyuge, chb_escritura_conyuge, chb_contratoPrivado_conyuge, chb_otrosGastos_conyuge,
			chb_padres_conyuge, chb_alquiler_conyuge, chb_fijo_conyuge, chb_temporal_conyuge, chb_autonomo_conyuge,
			chb_otrosCosas_conyuge;
	JTextArea // datos hipotecado
	otrosBienes, comentarios,
			// datos conyuge
			otrosBienes_conyuge;
	public JComboBox<String> tipo, nueva, estadoCivil, regimenBienes;

	private JTabbedPane datosHipoteca;

	public PanelHipoteca(int alto, int ancho) {

		setLayout(new BorderLayout());
		add(setPanelNorte(alto, ancho, setPanelNorteDatos1(alto, ancho), setPanelNorteDatos2(alto, ancho),
				setPanelNorteDatos3(alto, ancho)), BorderLayout.NORTH);
		// add(setPanelOeste(alto, ancho, setPanelOesteDatos(alto, ancho)),
		// BorderLayout.WEST);

		datosHipoteca = cositas();

		add(datosHipoteca, BorderLayout.CENTER);
		// add(setPanelEste(alto, ancho, setPanelEsteDatos(alto, ancho),
		// setPanelControl(alto, ancho), setPanelEsteDatos2(alto,ancho)));
	}

	public JTabbedPane cositas() {
		JTabbedPane ventanitas = new JTabbedPane();
		ventanitas.add(setPanelEste(1200, 1000, setPanelEsteDatos(500, 500), setPanelControl(500, 500),
				setPanelEsteDatos2(500, 500)), "Datos Hipotecado");
		ventanitas.add(setPanelConyuge(setPanelPersonal(), setPanelProfesional(), setPanelEconomico()),
				"Datos Conyuge");

		ventanitas.setEnabledAt(1, false);

		return ventanitas;

	}

	public JPanel setPanelNorte(int alto, int ancho, JPanel jp1, JPanel jp2, JPanel jp3) {

		JPanel panelNorte = new JPanel();
		panelNorte.setLayout(new BoxLayout(panelNorte, BoxLayout.Y_AXIS));
		//panelNorte.setLayout(new BoxLayout(panelNorte, BoxLayout.X_AXIS));
		panelNorte.setPreferredSize(new Dimension((int) (ancho * 0.1), (int) (alto * 0.2))); // no cambiarr porfavor
		panelNorte.add(jp1);
		panelNorte.add(jp2);
		panelNorte.add(jp3);

		return panelNorte;

	}

	public JPanel setPanelNorteDatos1(int alto, int ancho) {
		JPanel panelPrimero = new JPanel();
		panelPrimero.setLayout(new BoxLayout(panelPrimero, BoxLayout.X_AXIS));
		panelPrimero.setBorder(BorderFactory.createLoweredBevelBorder());

		JLabel l_finalidad = new JLabel("Finalidad: ");
		finalidad = new JTextField();
		finalidad.setForeground(Color.gray);
		Font f = new Font("Serif", Font.BOLD, 12);
		finalidad.setFont(f);
		finalidad.setMaximumSize(new Dimension(250, 20));

		JLabel l_creditoSolicitado = new JLabel("CREDITO SOLICITADO : ");
		l_creditoSolicitado.setForeground(Color.gray);
		Font f1 = new Font("Serif", Font.BOLD, 12);
		l_creditoSolicitado.setFont(f1);

		JLabel l_valorAdquisicion = new JLabel("Valor de  adquisicion: ");
		valorAdquisicion = new JTextField();
		valorAdquisicion.setForeground(Color.gray);
		valorAdquisicion.setFont(f);
		valorAdquisicion.setMaximumSize(new Dimension(250, 20));

		JLabel l_importe = new JLabel("Importe Credito");
		valorImporte = new JTextField();
		valorImporte.setForeground(Color.gray);
		valorImporte.setFont(f);
		valorImporte.setMaximumSize(new Dimension(250, 20));

		JLabel l_plazo = new JLabel("Plazo Total(meses): ");
		plazo = new JTextField();
		plazo.setForeground(Color.gray);
		plazo.setFont(f);
		plazo.setMaximumSize(new Dimension(250, 20));

		panelPrimero.add(l_finalidad);
		panelPrimero.add(finalidad);
		panelPrimero.add(Box.createRigidArea(new Dimension(0, 1)));
		panelPrimero.add(l_creditoSolicitado);
		panelPrimero.add(l_valorAdquisicion);
		panelPrimero.add(valorAdquisicion);
		panelPrimero.add(Box.createRigidArea(new Dimension(0, 10)));
		panelPrimero.add(l_importe);
		panelPrimero.add(valorImporte);
		panelPrimero.add(Box.createRigidArea(new Dimension(0, 10)));
		panelPrimero.add(l_plazo);
		panelPrimero.add(plazo);
		panelPrimero.add(Box.createRigidArea(new Dimension(0, 10)));

		panelPrimero.setPreferredSize(new Dimension((int) (ancho * 0.01), (int) (alto * 0.1)));
		return panelPrimero;

	}

	public JPanel setPanelNorteDatos2(int alto, int ancho) {

		JPanel panelNorteDatos2 = new JPanel();
		panelNorteDatos2.setLayout(new BoxLayout(panelNorteDatos2, BoxLayout.X_AXIS));
		panelNorteDatos2.setBorder(BorderFactory.createLoweredBevelBorder());

		JLabel l_direccionVivienda = new JLabel("Direccion de Vivienda:");
		direccion = new JTextField();
		direccion.setForeground(Color.gray);
		Font f = new Font("Serif", Font.BOLD, 12);
		direccion.setFont(f);
		direccion.setMaximumSize(new Dimension(250, 20));

		JLabel l_tipo = new JLabel("Tipo:  ");
		tipo = new JComboBox();
		tipo.addItem("Libre");
		tipo.addItem("Con cargas");
		tipo.setForeground(Color.BLACK);
		tipo.setFont(f);
		tipo.setMaximumSize(new Dimension(70, 20));

		JLabel l_cargas = new JLabel("Cargas:  ");
		cargas = new JTextField();
		cargas.setForeground(Color.BLACK);
		cargas.setFont(f);
		cargas.setMaximumSize(new Dimension(70, 20));

		JLabel l_nueva = new JLabel("Nueva:  ");
		nueva = new JComboBox();
		nueva.addItem("Si");
		nueva.addItem("No");
		nueva.setForeground(Color.BLACK);
		nueva.setFont(f);
		nueva.setMaximumSize(new Dimension(40, 20));

		JLabel l_estadoCivil = new JLabel("Estado Civil:  ");
		estadoCivil = new JComboBox();
		estadoCivil.addItem("Solter@");
		estadoCivil.addItem("Casad@");
		estadoCivil.addItem("Divorciad@");
		estadoCivil.addItem("Viud@");
		estadoCivil.addItem("Concubinato");

		estadoCivil.setForeground(Color.BLACK);
		estadoCivil.setFont(f);
		estadoCivil.setMaximumSize(new Dimension(65, 20));

		JLabel l_regimenBienes = new JLabel("Regimen de bienes:  ");
		regimenBienes = new JComboBox();
		regimenBienes.addItem("Gananciales");
		regimenBienes.addItem("Separacion de bienes");
		regimenBienes.addItem("Participacion");
		regimenBienes.setForeground(Color.BLACK);
		regimenBienes.setFont(f);
		regimenBienes.setMaximumSize(new Dimension(100, 20));

		panelNorteDatos2.add(l_direccionVivienda);
		panelNorteDatos2.add(direccion);
		panelNorteDatos2.add(Box.createRigidArea(new Dimension(0, 10)));
		panelNorteDatos2.add(l_tipo);
		panelNorteDatos2.add(tipo);
		panelNorteDatos2.add(Box.createRigidArea(new Dimension(0, 10)));
		panelNorteDatos2.add(l_cargas);
		panelNorteDatos2.add(cargas);
		panelNorteDatos2.add(Box.createRigidArea(new Dimension(0, 10)));
		panelNorteDatos2.add(l_nueva);
		panelNorteDatos2.add(nueva);
		panelNorteDatos2.add(Box.createRigidArea(new Dimension(0, 10)));
		panelNorteDatos2.add(l_estadoCivil);
		panelNorteDatos2.add(estadoCivil);
		panelNorteDatos2.add(Box.createRigidArea(new Dimension(0, 10)));
		panelNorteDatos2.add(l_regimenBienes);
		panelNorteDatos2.add(regimenBienes);
		panelNorteDatos2.add(Box.createRigidArea(new Dimension(0, 10)));

		panelNorteDatos2.setPreferredSize(new Dimension((int) (ancho * 0.5), (int) (alto * 0.1)));
		return panelNorteDatos2;
	}

	public JPanel setPanelNorteDatos3(int alto, int ancho) {

		JPanel panelNorteDatos3 = new JPanel();
		panelNorteDatos3.setLayout(new BoxLayout(panelNorteDatos3, BoxLayout.X_AXIS));
		panelNorteDatos3.setBorder(BorderFactory.createLoweredBevelBorder());

		JLabel l_vinculacion = new JLabel("Vinculado a: ");
		vinculacion = new JTextField();
		vinculacion.setForeground(Color.gray);
		Font f = new Font("Serif", Font.BOLD, 12);
		vinculacion.setFont(f);
		vinculacion.setMaximumSize(new Dimension(250, 20));
		b_dniConyuge = new JButton("Vincular");
		b_dniConyuge.setMaximumSize(new Dimension(250, 20));
		b_dniConyuge.addActionListener(new gestorVinculado());
		JLabel l_busquedacliente = new JLabel("Busqueda cliente:");
		busquedacliente = new JTextField();
		busquedacliente.setForeground(Color.gray);
		busquedacliente.setFont(f);
		busquedacliente.setMaximumSize(new Dimension(250, 20));
		b_buscarCliente = new JButton("BUSCAR");
		b_buscarCliente.setMaximumSize(new Dimension(250,20));
		b_buscarCliente.addActionListener(new gestorBuscaCli());

		panelNorteDatos3.add(l_vinculacion);
		panelNorteDatos3.add(vinculacion);
		panelNorteDatos3.add(b_dniConyuge);
		panelNorteDatos3.add(l_busquedacliente);
		panelNorteDatos3.add(busquedacliente);
		panelNorteDatos3.add(b_buscarCliente);
		
		panelNorteDatos3.setPreferredSize(new Dimension((int) (ancho * 0.01), (int) (alto * 0.1)));

		return panelNorteDatos3;

	}

	public JPanel setPanelEste(int alto, int ancho, JPanel jp1, JPanel jp2, JPanel jp3) {
		JPanel panelEste = new JPanel();
		panelEste.setLayout(new BoxLayout(panelEste, BoxLayout.X_AXIS));
		// panelConyuge.setPreferredSize(new Dimension((int) (ancho * 1.2), (int) (alto
		// * 1.2)));
		panelEste.setBackground(Color.red);
		jp1.setPreferredSize(new Dimension(1, 1));
		jp1.setMinimumSize(new Dimension(1, 1));
		jp1.setMaximumSize(new Dimension(2000, 2000));
		jp2.setPreferredSize(new Dimension(1, 1));
		jp2.setMinimumSize(new Dimension(1, 1));
		jp2.setMaximumSize(new Dimension(2000, 2000));
		jp3.setPreferredSize(new Dimension(1, 1));
		jp3.setMinimumSize(new Dimension(1, 1));
		jp3.setMaximumSize(new Dimension(2000, 2000));
		
		panelEste.add(jp1);
		panelEste.add(jp2);
		panelEste.add(jp3);
		

		return panelEste;

	}

//correcto
	public JPanel setPanelEsteDatos(int alto, int ancho) {

		JPanel panelEsteDatos = new JPanel();
		panelEsteDatos.setLayout(new BoxLayout(panelEsteDatos, BoxLayout.Y_AXIS));
		panelEsteDatos.setAlignmentY(CENTER_ALIGNMENT);

		JLabel l_datoseconomicos = new JLabel("Datos Economicos:");
		l_datoseconomicos.setSize(new Dimension(250, 20));
		l_datoseconomicos.setForeground(Color.BLACK);
		Font f1 = new Font("Arial", Font.BOLD, 15);
		l_datoseconomicos.setFont(f1);

		JLabel l_ingresosFijos = new JLabel("Ingresos Fijos(mes):");
		ingresosFijos = new JTextField();
		ingresosFijos.setForeground(Color.gray);
		Font f = new Font("Serif", Font.BOLD, 12);
		ingresosFijos.setFont(f);
		ingresosFijos.setMaximumSize(new Dimension(250, 20));

//		?

//	Pasa saber el texto que ha seleccionado en un ComboBox usar:
//				setString(1,miComboBox.getSelectedItem())

		JLabel l_ingresosVariables = new JLabel("Ingresos Variables(mes):");
		ingresosVariables = new JTextField();
		ingresosVariables.setForeground(Color.gray);
		ingresosVariables.setFont(f);
		ingresosVariables.setMaximumSize(new Dimension(250, 20));

		JLabel l_gastosAlquiler = new JLabel("Gastos Alquiler:");
		gastosAlquiler = new JTextField();
		gastosAlquiler.setForeground(Color.gray);
		gastosAlquiler.setFont(f);
		gastosAlquiler.setMaximumSize(new Dimension(250, 20));

		JLabel l_gastosHipoteca = new JLabel("Gastos Hipoteca:");
		gastosHipoteca = new JTextField();
		gastosHipoteca.setForeground(Color.gray);
		gastosHipoteca.setFont(f);
		gastosHipoteca.setMaximumSize(new Dimension(250, 20));

		JLabel l_otros = new JLabel("Otros:");
		otros = new JTextField();
		otros.setForeground(Color.gray);
		otros.setFont(f);
		otros.setMaximumSize(new Dimension(250, 20));

		JLabel l_valor = new JLabel("Valor:");
		valor = new JTextField();
		valor.setForeground(Color.gray);
		valor.setFont(f);
		valor.setMaximumSize(new Dimension(250, 20));

		JLabel l_cargasVivienda = new JLabel("Cargas vivienda: ");
		cargasVivienda = new JTextField();
		cargasVivienda.setForeground(Color.gray);
		cargasVivienda.setFont(f);
		cargasVivienda.setMaximumSize(new Dimension(250, 20));

		JLabel l_otrosBienes = new JLabel("Otro Bienes: ");
		otrosBienes = new JTextArea();
		otrosBienes.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		otrosBienes.setForeground(Color.gray);
		otrosBienes.setMaximumSize(new Dimension(375, 70));

		chb_propiedad = new JCheckBox("Propiedad");
		chb_propiedad.setForeground(Color.BLACK);
		chb_escritura = new JCheckBox("Escritura");
		chb_escritura.setForeground(Color.BLACK);
		chb_contratoPrivado = new JCheckBox("Contrato Privado");
		chb_contratoPrivado.setForeground(Color.BLACK);
		chb_otrosGastos = new JCheckBox("Otros");
		chb_otrosGastos.setForeground(Color.BLACK);
		chb_padres = new JCheckBox("Padres");
		chb_padres.setForeground(Color.BLACK);
		chb_alquiler = new JCheckBox("Alquiler");
		chb_alquiler.setForeground(Color.BLACK);

		panelEsteDatos.add(l_datoseconomicos);
		panelEsteDatos.add(Box.createRigidArea(new Dimension(0, 10)));
		panelEsteDatos.add(l_ingresosFijos);
		panelEsteDatos.add(ingresosFijos);
		panelEsteDatos.add(Box.createRigidArea(new Dimension(0, 10)));
		panelEsteDatos.add(l_ingresosVariables);
		panelEsteDatos.add(ingresosVariables);
		panelEsteDatos.add(Box.createRigidArea(new Dimension(0, 10)));
		panelEsteDatos.add(l_gastosAlquiler);
		panelEsteDatos.add(gastosAlquiler);
		panelEsteDatos.add(Box.createRigidArea(new Dimension(0, 10)));
		panelEsteDatos.add(l_gastosHipoteca);
		panelEsteDatos.add(gastosHipoteca);
		panelEsteDatos.add(Box.createRigidArea(new Dimension(0, 10)));
		panelEsteDatos.add(l_otros);
		panelEsteDatos.add(otros);
		panelEsteDatos.add(Box.createRigidArea(new Dimension(0, 10)));
		panelEsteDatos.add(l_valor);
		panelEsteDatos.add(valor);
		panelEsteDatos.add(Box.createRigidArea(new Dimension(0, 10)));
		panelEsteDatos.add(l_cargasVivienda);
		panelEsteDatos.add(cargasVivienda);
		panelEsteDatos.add(Box.createRigidArea(new Dimension(0, 10)));
		panelEsteDatos.add(chb_propiedad);
		panelEsteDatos.add(Box.createRigidArea(new Dimension(0, 2)));
		panelEsteDatos.add(chb_escritura);
		panelEsteDatos.add(Box.createRigidArea(new Dimension(0, 2)));
		panelEsteDatos.add(chb_contratoPrivado);
		panelEsteDatos.add(Box.createRigidArea(new Dimension(0, 2)));
		panelEsteDatos.add(chb_otrosGastos);
		panelEsteDatos.add(Box.createRigidArea(new Dimension(0, 2)));
		panelEsteDatos.add(chb_padres);
		panelEsteDatos.add(Box.createRigidArea(new Dimension(0, 2)));
		panelEsteDatos.add(chb_alquiler);
		panelEsteDatos.add(Box.createRigidArea(new Dimension(0, 2)));
		panelEsteDatos.add(l_otrosBienes);
		panelEsteDatos.add(otrosBienes);
		panelEsteDatos.add(Box.createRigidArea(new Dimension(0, 1)));
		
		JPanel auxiliar = new JPanel();
		auxiliar.setLayout(new BoxLayout(auxiliar, BoxLayout.X_AXIS));
		auxiliar.add(new JPanel());
		panelEsteDatos.setMaximumSize(new Dimension(255,2000));
		auxiliar.add(panelEsteDatos, BorderLayout.CENTER);
		auxiliar.add(new JPanel());
		
		return auxiliar;



	}

	public JPanel setPanelControl(int alto, int ancho) {

		JPanel panelControl = new JPanel();
		panelControl.setLayout(new BoxLayout(panelControl, BoxLayout.Y_AXIS));
		panelControl.setAlignmentY(CENTER_ALIGNMENT);

		JLabel l_datosProfesionales = new JLabel("Datos Profesionales");
		l_datosProfesionales.setForeground(Color.BLACK);
		Font f1 = new Font("Arial", Font.BOLD, 15);
		l_datosProfesionales.setFont(f1);

		JLabel l_nombreEmpresa = new JLabel("Nombre de la Empresa:  ");
		nombreEmpresa = new JTextField();
		nombreEmpresa.setForeground(Color.gray);
		Font f = new Font("Serif", Font.BOLD, 12);
		nombreEmpresa.setFont(f);
		nombreEmpresa.setMaximumSize(new Dimension(250, 20));

		JLabel l_actividad = new JLabel("Actividad de la Empresa:  ");
		actividad = new JTextField();
		actividad.setForeground(Color.gray);
		actividad.setFont(f);
		actividad.setMaximumSize(new Dimension(250, 20));

		JLabel l_antiguedad = new JLabel("Antiguedad:  ");
		antiguedad = new JTextField();
		antiguedad.setForeground(Color.gray);
		antiguedad.setFont(f);
		antiguedad.setMaximumSize(new Dimension(250, 20));

		JLabel l_puesto = new JLabel("Puesto de la empresa:  ");
		puesto = new JTextField();
		puesto.setForeground(Color.gray);
		puesto.setFont(f);
		puesto.setMaximumSize(new Dimension(250, 20));

		JLabel l_direccionEmpresa = new JLabel("Direccion de la empresa:  ");
		direccionEmpresa = new JTextField();
		direccionEmpresa.setForeground(Color.gray);
		direccionEmpresa.setFont(f);
		direccionEmpresa.setMaximumSize(new Dimension(250, 20));

		JLabel l_contactoEmpresa = new JLabel("Correo-e de la empresa:  ");
		contactoEmpresa = new JTextField();
		contactoEmpresa.setForeground(Color.gray);
		contactoEmpresa.setFont(f);
		contactoEmpresa.setMaximumSize(new Dimension(250, 20));

		JTable b = new JTable();
		b.setMaximumSize(new Dimension(400, 200));
		b.setLayout(new BoxLayout(b, BoxLayout.Y_AXIS));
		b.setBorder(BorderFactory.createLoweredBevelBorder());
		JTextField control = new JTextField("Panel Control");
		Font f2 = new Font("Arial", Font.BOLD, 20);
		control.setFont(f2);
		control.setMaximumSize(new Dimension(300, 40));
		imprimir = new JButton("IMPRIMIR PETICION");
		imprimir.setForeground(Color.MAGENTA);
		insertar = new JButton("INSERTAR EN BBDD");
		insertar.setForeground(Color.MAGENTA);
		limpiar = new JButton("LIMPIAR");
		limpiar.setForeground(Color.MAGENTA);
		chb_editar = new JCheckBox("Editar Campos");
		chb_editar.setForeground(Color.BLACK);

		b.add(control);
		b.add(Box.createRigidArea(new Dimension(0, 10)));
		b.add(insertar);
		b.add(Box.createRigidArea(new Dimension(0, 10)));
		b.add(imprimir);
		b.add(Box.createRigidArea(new Dimension(0, 10)));
		b.add(limpiar);

		b.add(Box.createRigidArea(new Dimension(0, 10)));
		b.add(chb_editar);

		imprimir.addActionListener(new gestorImprimir());
		insertar.addActionListener(new gestorInsertar());
		limpiar.addActionListener(new gestorLimpiar());
		b.setVisible(true);

		JLabel l_comentarios = new JLabel("Comentarios: ");
		comentarios = new JTextArea();
		comentarios.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		comentarios.setForeground(Color.gray);
		Font f7 = new Font("Italic", Font.ITALIC, 12);
		comentarios.setFont(f7);
		comentarios.setMaximumSize(new Dimension(250, 20));

		panelControl.add(l_datosProfesionales);
		panelControl.add(l_nombreEmpresa);
		panelControl.add(nombreEmpresa);
		panelControl.add(Box.createRigidArea(new Dimension(0, 10)));
		panelControl.add(l_actividad);
		panelControl.add(actividad);
		panelControl.add(Box.createRigidArea(new Dimension(0, 10)));
		panelControl.add(l_antiguedad);
		panelControl.add(antiguedad);
		panelControl.add(Box.createRigidArea(new Dimension(0, 10)));
		panelControl.add(l_puesto);
		panelControl.add(puesto);
		panelControl.add(Box.createRigidArea(new Dimension(0, 10)));
		panelControl.add(l_direccionEmpresa);
		panelControl.add(direccionEmpresa);
		panelControl.add(Box.createRigidArea(new Dimension(0, 10)));
		panelControl.add(l_contactoEmpresa);
		panelControl.add(contactoEmpresa);
		panelControl.add(Box.createRigidArea(new Dimension(0, 10)));
		panelControl.add(b);
		panelControl.add(Box.createRigidArea(new Dimension(0, 10)));
		panelControl.add(l_comentarios);
		panelControl.add(comentarios);
		panelControl.add(Box.createRigidArea(new Dimension(0, 1)));

		panelControl.setVisible(true);
		
		JPanel auxiliar = new JPanel();
		auxiliar.setLayout(new BoxLayout(auxiliar, BoxLayout.X_AXIS));
		auxiliar.add(new JPanel());
		panelControl.setMaximumSize(new Dimension(255,2000));
		auxiliar.add(panelControl, BorderLayout.CENTER);
		auxiliar.add(new JPanel());
		
		return auxiliar;

	
		

	}

	public class gestorImprimir implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			imprimir();

		}
	}

	public class gestorInsertar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			insertarBBDD();
		}

	}

	public class gestorLimpiar implements ActionListener {
		// poner los clear en clearHipoteca y luego clear Conyuge

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			String[] botones = { "A LIMPIAR", "DENEGAR" };

			int resp = JOptionPane.showOptionDialog(null, " Usted eliminara los datos del nuevo hipotecado",
					"¿Esta seguro?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null/* icono */, botones,
					botones[0]);
			if (resp == JOptionPane.YES_OPTION) {
				// limpiar el jtextfield
				finalidad.setText("");
				valorAdquisicion.setText("");
				valorImporte.setText("");
				plazo.setText("");
				direccion.setText("");
				cargas.setText("");
				vinculacion.setText("");
				busquedacliente.setText("");
				dninie.setText("");
				apellido1.setText("");
				apellido2.setText("");
				nombre.setText("");
				fechaNacimiento.setText("");
				profesion.setText("");
				domicilio.setText("");
				poblacion.setText("");
				codigoPostal.setText("");
				nombreEmpresa.setText("");
				actividad.setText("");
				antiguedad.setText("");
				puesto.setText("");
				direccionEmpresa.setText("");
				contactoEmpresa.setText("");
				ingresosFijos.setText("");
				ingresosVariables.setText("");
				gastosAlquiler.setText("");
				gastosHipoteca.setText("");
				otros.setText("");
				valor.setText(""); 
				cargasVivienda.setText("");

				dninie_conyuge.setText("");
				apellido1_conyuge.setText("");
				apellido2_conyuge.setText("");
				nombre_conyuge.setText("");
				fechaNacimiento_conyuge.setText("");
				profesion_conyuge.setText("");
				domicilio_conyuge.setText("");
				poblacion_conyuge.setText("");
				codigoPostal_conyuge.setText("");
				nombreEmpresa_conyuge.setText("");
				actividad_conyuge.setText("");
				antiguedad_conyuge.setText("");
				puesto_conyuge.setText("");
				direccionEmpresa_conyuge.setText("");
				contactoEmpresa_conyuge.setText("");
				ingresosFijos_conyuge.setText("");
				ingresosVariables_conyuge.setText("");
				ingresosFijos_conyuge.setText("");
				gastosHipoteca_conyuge.setText("");
				otros_conyuge.setText("");
				valor_conyuge.setText("");
				cargasVivienda_conyuge.setText("");
				


				// limpiar el checkbbox
				chb_editar.setSelected(false);
				chb_propiedad.setSelected(false);
				chb_escritura.setSelected(false);
				chb_contratoPrivado.setSelected(false);
				chb_otrosGastos.setSelected(false);
				chb_padres.setSelected(false);
				chb_alquiler.setSelected(false);
				chb_fijo.setSelected(false);
				chb_temporal.setSelected(false);
				chb_autonomo.setSelected(false);
				chb_otrosCosas.setSelected(false);
				// limpiar el jcombobox
				tipo.setSelectedIndex(0);
				nueva.setSelectedIndex(0);
				estadoCivil.setSelectedIndex(0);
				regimenBienes.setSelectedIndex(0);
				
				chb_propiedad_conyuge.setSelected(false);
				chb_escritura_conyuge.setSelected(false);
				chb_contratoPrivado_conyuge.setSelected(false);
				chb_otrosGastos_conyuge.setSelected(false);
				chb_padres_conyuge.setSelected(false);
				chb_alquiler_conyuge.setSelected(false);
				chb_fijo_conyuge.setSelected(false);
				chb_temporal_conyuge.setSelected(false);
				chb_autonomo_conyuge.setSelected(false);
				chb_otrosCosas_conyuge.setSelected(false);
			

				// limpiar el jtextAREA

				otrosBienes.setText("");
				otrosBienes_conyuge.setText("");
			}
			if (resp == JOptionPane.NO_OPTION) {
				String box = "0";
			}

		}

	}

	public class gestorVinculado implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				c = new MisConexiones();
				ps = c.getPS(ConfigDir.getInstance().getProperty("vinculado"));
				ps.setString(1, vinculacion.getText());
				
				rs = ps.executeQuery();

				if (rs.next() == false) {
					new JOptionPane().showMessageDialog(null,
							"El usuario con DNI: " + vinculacion.getText() + " no existe");
				} else {
					//consigue que despues de buscar el dni se va y activa el panel conyuge
					datosHipoteca.setEnabledAt(1, true);
					datosHipoteca.setSelectedIndex(1);
					
			
					
					
					//fechaNacimiento.setText(rs.getTimestamp("fecha_alta"));
					nombre_conyuge.setText(rs.getString("nombre"));
					apellido1_conyuge.setText(rs.getString("apellido1"));
					apellido2_conyuge.setText(rs.getString("apellido2"));
					fechaNacimiento_conyuge.setText("" + rs.getTimestamp("fecha_nacimiento"));
					chb_fijo_conyuge.setSelected(rs.getBoolean("fijo"));
					chb_temporal_conyuge.setSelected(rs.getBoolean("temporal"));
					chb_autonomo_conyuge.setSelected(rs.getBoolean("autonomo"));
					chb_otrosCosas_conyuge.setSelected(rs.getBoolean("otros"));
					profesion_conyuge.setText(rs.getString("profesion"));
					domicilio_conyuge.setText(rs.getString("domicilio"));
					poblacion_conyuge.setText(rs.getString("poblacion"));
					codigoPostal_conyuge.setText(rs.getString("codigoPostal"));
					
					//System.out.println(rs.getString("nombre"));
				}

			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e1) {
				System.out.println("Error al vincular");
				e1.printStackTrace();
			}
		}

	}
	
	public class gestorBuscaCli implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try {
				c = new MisConexiones();
				ps = c.getPS(ConfigDir.getInstance().getProperty("vinculado"));
				ps.setString(1, busquedacliente.getText());
				
				rs = ps.executeQuery();

				if (rs.next() == false) {
					new JOptionPane().showMessageDialog(null,
							"El usuario con DNI: " + busquedacliente.getText() + " no existe");
				} else {
					//consigue que despues de buscar el dni se va y activa el panel conyuge
					//datosHipoteca.setEnabledAt(1, true);
					
					
			
					
					
					//fechaNacimiento.setText(rs.getTimestamp("fecha_alta"));
					nombre.setText(rs.getString("nombre"));
					apellido1.setText(rs.getString("apellido1"));
					apellido2.setText(rs.getString("apellido2"));
					fechaNacimiento.setText("" + rs.getTimestamp("fecha_nacimiento"));
					chb_fijo.setSelected(rs.getBoolean("fijo"));
					chb_temporal.setSelected(rs.getBoolean("temporal"));
					chb_autonomo.setSelected(rs.getBoolean("autonomo"));
					chb_otrosCosas.setSelected(rs.getBoolean("otros"));
					profesion.setText(rs.getString("profesion"));
					domicilio.setText(rs.getString("domicilio"));
					poblacion.setText(rs.getString("poblacion"));
					codigoPostal.setText(rs.getString("codigoPostal"));
					
					//System.out.println(rs.getString("nombre"));
				}

			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e1) {
				System.out.println("Error al vincular");
				e1.printStackTrace();
			}
		}

		}
		
	

	
	public JPanel setPanelEsteDatos2(int alto, int ancho) {
		JPanel panelEsteDatos2 = new JPanel();
		panelEsteDatos2.setLayout(new BoxLayout(panelEsteDatos2, BoxLayout.Y_AXIS));
		panelEsteDatos2.setAlignmentY(CENTER_ALIGNMENT);

		JLabel l_datosPersonales = new JLabel("Datos Personales");
		l_datosPersonales.setForeground(Color.BLACK);
		Font f1 = new Font("Arial", Font.BOLD, 15);
		l_datosPersonales.setFont(f1);

		JLabel l_titular = new JLabel("Titular");
		l_titular.setForeground(Color.BLACK);
		JLabel l_ocupacion = new JLabel("Ocupacion");
		l_ocupacion.setForeground(Color.BLACK);

		JLabel l_dninie = new JLabel("Dni|nie");
		dninie = new JTextField();
		dninie.setForeground(Color.gray);
		Font f = new Font("Serif", Font.BOLD, 12);
		dninie.setFont(f);
		dninie.setMaximumSize(new Dimension(250, 20));

		JLabel l_apellido1 = new JLabel("Primer Apellido: ");
		apellido1 = new JTextField();
		apellido1.setForeground(Color.gray);
		apellido1.setFont(f);
		apellido1.setMaximumSize(new Dimension(250, 20));

		JLabel l_apellido2 = new JLabel("Segundo Apellido: ");
		apellido2 = new JTextField();
		apellido2.setForeground(Color.gray);
		apellido2.setFont(f);
		apellido2.setMaximumSize(new Dimension(250, 20));

		JLabel l_nombre = new JLabel("Nombre: ");
		nombre = new JTextField();
		nombre.setForeground(Color.gray);
		nombre.setFont(f);
		nombre.setMaximumSize(new Dimension(250, 20));

		JLabel l_fechanacimiento = new JLabel("Fecha de Nacimiento: ");
		fechaNacimiento = new JTextField();
		fechaNacimiento.setForeground(Color.gray);
		fechaNacimiento.setFont(f);
		fechaNacimiento.setMaximumSize(new Dimension(250, 20));

		chb_fijo = new JCheckBox("Fijo");
		chb_fijo.setForeground(Color.BLACK);
		chb_temporal = new JCheckBox("Temporal");
		chb_temporal.setForeground(Color.BLACK);
		chb_autonomo = new JCheckBox("Autonomo");
		chb_autonomo.setForeground(Color.BLACK);
		chb_otrosCosas = new JCheckBox("Otros");
		chb_otrosCosas.setForeground(Color.BLACK);

		JLabel l_profesion = new JLabel("Profesion: ");
		profesion = new JTextField();
		profesion.setForeground(Color.gray);
		profesion.setFont(f);
		profesion.setMaximumSize(new Dimension(250, 20));

		JLabel l_domicilio = new JLabel("Domicilio: ");
		domicilio = new JTextField();
		domicilio.setForeground(Color.gray);
		domicilio.setFont(f);
		domicilio.setMaximumSize(new Dimension(250, 20));

		JLabel l_poblacion = new JLabel("Poblacion: ");
		poblacion = new JTextField();
		poblacion.setForeground(Color.gray);
		poblacion.setFont(f);
		poblacion.setMaximumSize(new Dimension(250, 20));

		JLabel l_codigopostal = new JLabel("Codigo Postal: ");
		codigoPostal = new JTextField();
		codigoPostal.setForeground(Color.gray);
		codigoPostal.setFont(f);
		codigoPostal.setMaximumSize(new Dimension(250, 20));

		panelEsteDatos2.add(l_datosPersonales);
		panelEsteDatos2.add(Box.createRigidArea(new Dimension(0, 10)));
		panelEsteDatos2.add(l_titular);
		panelEsteDatos2.add(Box.createRigidArea(new Dimension(0, 10)));
		panelEsteDatos2.add(l_dninie);
		panelEsteDatos2.add(dninie);
		panelEsteDatos2.add(Box.createRigidArea(new Dimension(0, 10)));
		panelEsteDatos2.add(l_apellido1);
		panelEsteDatos2.add(apellido1);
		panelEsteDatos2.add(Box.createRigidArea(new Dimension(0, 10)));
		panelEsteDatos2.add(l_apellido2);
		panelEsteDatos2.add(apellido2);
		panelEsteDatos2.add(Box.createRigidArea(new Dimension(0, 10)));
		panelEsteDatos2.add(l_nombre);
		panelEsteDatos2.add(nombre);
		panelEsteDatos2.add(Box.createRigidArea(new Dimension(0, 10)));
		panelEsteDatos2.add(l_fechanacimiento);
		panelEsteDatos2.add(fechaNacimiento);
		panelEsteDatos2.add(Box.createRigidArea(new Dimension(0, 10)));
		panelEsteDatos2.add(l_ocupacion);
		panelEsteDatos2.add(Box.createRigidArea(new Dimension(0, 10)));
		panelEsteDatos2.add(chb_fijo);
		panelEsteDatos2.add(chb_temporal);
		panelEsteDatos2.add(chb_autonomo);
		panelEsteDatos2.add(chb_otrosCosas);
		panelEsteDatos2.add(l_profesion);
		panelEsteDatos2.add(profesion);
		panelEsteDatos2.add(Box.createRigidArea(new Dimension(0, 10)));
		panelEsteDatos2.add(l_domicilio);
		panelEsteDatos2.add(domicilio);
		panelEsteDatos2.add(Box.createRigidArea(new Dimension(0, 10)));
		panelEsteDatos2.add(l_poblacion);
		panelEsteDatos2.add(poblacion);
		panelEsteDatos2.add(Box.createRigidArea(new Dimension(0, 10)));
		panelEsteDatos2.add(l_codigopostal);
		panelEsteDatos2.add(codigoPostal);
		panelEsteDatos2.add(Box.createRigidArea(new Dimension(0, 10)));
		JPanel auxiliar = new JPanel();
		auxiliar.setLayout(new BoxLayout(auxiliar, BoxLayout.X_AXIS));
		auxiliar.add(new JPanel());
		panelEsteDatos2.setMaximumSize(new Dimension(255,2000));
		auxiliar.add(panelEsteDatos2, BorderLayout.CENTER);
		auxiliar.add(new JPanel());
		
		return auxiliar;
		

	}
	/*
	 * public JPanel setPanelOeste(int alto, int ancho, JPanel j1) { JPanel
	 * panelOeste = new JPanel(); panelOeste.setLayout(new BorderLayout());
	 * panelOeste.setPreferredSize(new Dimension((int) (ancho * 0.7), (int) (alto *
	 * 1.2))); panelOeste.add(j1, BorderLayout.WEST); return panelOeste;
	 * 
	 * }
	 */

	/*
	 * public JPanel setPanelOesteDatos(int alto, int ancho) {
	 * 
	 */
	public JPanel setPanelConyuge(JPanel jp1, JPanel jp2, JPanel jp3) {
		JPanel panelConyuge = new JPanel();
		panelConyuge.setLayout(new BoxLayout(panelConyuge, BoxLayout.X_AXIS));
		// panelConyuge.setPreferredSize(new Dimension((int) (ancho * 1.2), (int) (alto
		// * 1.2)));
		panelConyuge.setBackground(Color.red);
		jp1.setPreferredSize(new Dimension(1, 1));
		jp1.setMinimumSize(new Dimension(1, 1));
		jp1.setMaximumSize(new Dimension(2000, 2000));
		jp2.setPreferredSize(new Dimension(1, 1));
		jp2.setMinimumSize(new Dimension(1, 1));
		jp2.setMaximumSize(new Dimension(2000, 2000));
		jp3.setPreferredSize(new Dimension(1, 1));
		jp3.setMinimumSize(new Dimension(1, 1));
		jp3.setMaximumSize(new Dimension(2000, 2000));


		panelConyuge.add(jp1);
		panelConyuge.add(jp2);
		panelConyuge.add(jp3);
		return panelConyuge;
	}

	public JPanel setPanelPersonal() {
		JPanel panelPersonal = new JPanel();

		panelPersonal.setLayout(new BoxLayout(panelPersonal, BoxLayout.Y_AXIS));
		panelPersonal.setAlignmentY(CENTER_ALIGNMENT);

		JLabel l_datosPersonales = new JLabel("Datos Personales");
		l_datosPersonales.setForeground(Color.BLACK);
		Font f1 = new Font("Arial", Font.BOLD, 15);
		l_datosPersonales.setFont(f1);

		JLabel l_titular = new JLabel("Titular");
		l_titular.setForeground(Color.BLACK);
		JLabel l_ocupacion = new JLabel("Ocupacion");
		l_ocupacion.setForeground(Color.BLACK);

		JLabel l_dninie = new JLabel("Dni|nie");
		dninie_conyuge = new JTextField();
		dninie_conyuge.setForeground(Color.gray);
		Font f = new Font("Serif", Font.BOLD, 12);
		dninie_conyuge.setFont(f);
		dninie_conyuge.setMaximumSize(new Dimension(250, 20));

		JLabel l_apellido1 = new JLabel("Primer Apellido: ");
		apellido1_conyuge = new JTextField();
		apellido1_conyuge.setForeground(Color.gray);
		apellido1_conyuge.setFont(f);
		apellido1_conyuge.setMaximumSize(new Dimension(250, 20));

		JLabel l_apellido2 = new JLabel("Segundo Apellido: ");
		apellido2_conyuge = new JTextField();
		apellido2_conyuge.setForeground(Color.gray);
		apellido2_conyuge.setFont(f);
		apellido2_conyuge.setMaximumSize(new Dimension(250, 20));

		JLabel l_nombre = new JLabel("Nombre: ");
		nombre_conyuge = new JTextField();
		nombre_conyuge.setForeground(Color.gray);
		nombre_conyuge.setFont(f);
		nombre_conyuge.setMaximumSize(new Dimension(250, 20));

		JLabel l_fechanacimiento = new JLabel("Fecha de Nacimiento: ");
		fechaNacimiento_conyuge = new JTextField();
		fechaNacimiento_conyuge.setForeground(Color.gray);
		fechaNacimiento_conyuge.setFont(f);
		fechaNacimiento_conyuge.setMaximumSize(new Dimension(250, 20));

		chb_fijo_conyuge = new JCheckBox("Fijo");
		chb_fijo_conyuge.setForeground(Color.BLACK);
		chb_temporal_conyuge = new JCheckBox("Temporal");
		chb_temporal_conyuge.setForeground(Color.BLACK);
		chb_autonomo_conyuge = new JCheckBox("Autonomo");
		chb_autonomo_conyuge.setForeground(Color.BLACK);
		chb_otrosCosas_conyuge = new JCheckBox("Otros");
		chb_otrosCosas_conyuge.setForeground(Color.BLACK);

		JLabel l_profesion = new JLabel("Profesion: ");
		profesion_conyuge = new JTextField();
		profesion_conyuge.setForeground(Color.gray);
		profesion_conyuge.setFont(f);
		profesion_conyuge.setMaximumSize(new Dimension(250, 20));

		JLabel l_domicilio = new JLabel("Domicilio: ");
		domicilio_conyuge = new JTextField();
		domicilio_conyuge.setForeground(Color.gray);
		domicilio_conyuge.setFont(f);
		domicilio_conyuge.setMaximumSize(new Dimension(250, 20));

		JLabel l_poblacion = new JLabel("Poblacion: ");
		poblacion_conyuge = new JTextField();
		poblacion_conyuge.setForeground(Color.gray);
		poblacion_conyuge.setFont(f);
		poblacion_conyuge.setMaximumSize(new Dimension(250, 20));

		JLabel l_codigopostal = new JLabel("Codigo Postal: ");
		codigoPostal_conyuge = new JTextField();
		codigoPostal_conyuge.setForeground(Color.gray);
		codigoPostal_conyuge.setFont(f);
		codigoPostal_conyuge.setMaximumSize(new Dimension(250, 20));

		panelPersonal.add(l_datosPersonales);
		panelPersonal.add(Box.createRigidArea(new Dimension(0, 10)));
		panelPersonal.add(l_titular);
		panelPersonal.add(Box.createRigidArea(new Dimension(0, 10)));
		panelPersonal.add(l_dninie);
		panelPersonal.add(dninie_conyuge);
		panelPersonal.add(Box.createRigidArea(new Dimension(0, 10)));
		panelPersonal.add(l_apellido1);
		panelPersonal.add(apellido1_conyuge);
		panelPersonal.add(Box.createRigidArea(new Dimension(0, 10)));
		panelPersonal.add(l_apellido2);
		panelPersonal.add(apellido2_conyuge);
		panelPersonal.add(Box.createRigidArea(new Dimension(0, 10)));
		panelPersonal.add(l_nombre);
		panelPersonal.add(nombre_conyuge);
		panelPersonal.add(Box.createRigidArea(new Dimension(0, 10)));
		panelPersonal.add(l_fechanacimiento);
		panelPersonal.add(fechaNacimiento_conyuge);
		panelPersonal.add(Box.createRigidArea(new Dimension(0, 10)));
		panelPersonal.add(l_ocupacion);
		panelPersonal.add(Box.createRigidArea(new Dimension(0, 10)));
		panelPersonal.add(chb_fijo_conyuge);
		panelPersonal.add(chb_temporal_conyuge);
		panelPersonal.add(chb_autonomo_conyuge);
		panelPersonal.add(chb_otrosCosas_conyuge);
		panelPersonal.add(l_profesion);
		panelPersonal.add(profesion_conyuge);
		panelPersonal.add(Box.createRigidArea(new Dimension(0, 10)));
		panelPersonal.add(l_domicilio);
		panelPersonal.add(domicilio_conyuge);
		panelPersonal.add(Box.createRigidArea(new Dimension(0, 10)));
		panelPersonal.add(l_poblacion);
		panelPersonal.add(poblacion_conyuge);
		panelPersonal.add(Box.createRigidArea(new Dimension(0, 10)));
		panelPersonal.add(l_codigopostal);
		// panelPersonal.add(new JLabel("adsadasd"));
		panelPersonal.add(codigoPostal_conyuge);
		panelPersonal.add(Box.createRigidArea(new Dimension(0, 10)));

		JPanel auxiliar = new JPanel();
		auxiliar.setLayout(new BoxLayout(auxiliar, BoxLayout.X_AXIS));
		auxiliar.add(new JPanel());
		panelPersonal.setMaximumSize(new Dimension(255,2000));
		auxiliar.add(panelPersonal, BorderLayout.CENTER);
		auxiliar.add(new JPanel());
		
		return auxiliar;
	}

	public JPanel setPanelProfesional() {
		JPanel panelProfesional = new JPanel();
		panelProfesional.setLayout(new BoxLayout(panelProfesional, BoxLayout.Y_AXIS));
		panelProfesional.setAlignmentY(CENTER_ALIGNMENT);
		JLabel l_datosProfesionales = new JLabel("Datos Profesionales");
		l_datosProfesionales.setForeground(Color.BLACK);
		Font f1 = new Font("Arial", Font.BOLD, 15);
		l_datosProfesionales.setFont(f1);

		JLabel l_nombreEmpresa = new JLabel("Nombre de la Empresa:  ");
		nombreEmpresa_conyuge = new JTextField();
		nombreEmpresa_conyuge.setForeground(Color.gray);
		Font f = new Font("Serif", Font.BOLD, 12);
		nombreEmpresa_conyuge.setFont(f);
		nombreEmpresa_conyuge.setMaximumSize(new Dimension(250, 20));
		nombreEmpresa_conyuge.setHorizontalAlignment(getWidth()/2);

		JLabel l_actividad = new JLabel("Actividad de la Empresa:  ");
		actividad_conyuge = new JTextField();
		actividad_conyuge.setForeground(Color.gray);
		actividad_conyuge.setFont(f);
		actividad_conyuge.setMaximumSize(new Dimension(250, 20));

		JLabel l_antiguedad = new JLabel("Antiguedad:  ");
		antiguedad_conyuge = new JTextField();
		antiguedad_conyuge.setForeground(Color.gray);
		antiguedad_conyuge.setFont(f);
		antiguedad_conyuge.setMaximumSize(new Dimension(250, 20));

		JLabel l_puesto = new JLabel("Puesto de la empresa:  ");
		puesto_conyuge = new JTextField();
		puesto_conyuge.setForeground(Color.gray);
		puesto_conyuge.setFont(f);
		puesto_conyuge.setMaximumSize(new Dimension(250, 20));

		JLabel l_direccionEmpresa = new JLabel("Direccion de la empresa:  ");
		direccionEmpresa_conyuge = new JTextField();
		direccionEmpresa_conyuge.setForeground(Color.gray);
		direccionEmpresa_conyuge.setFont(f);
		direccionEmpresa_conyuge.setMaximumSize(new Dimension(250, 20));

		JLabel l_contactoEmpresa = new JLabel("Correo-e de la empresa:  ");
		contactoEmpresa_conyuge = new JTextField();
		contactoEmpresa_conyuge.setForeground(Color.gray);
		contactoEmpresa_conyuge.setFont(f);
		contactoEmpresa_conyuge.setMaximumSize(new Dimension(250, 20));

		panelProfesional.add(l_datosProfesionales);
		panelProfesional.add(Box.createRigidArea(new Dimension(0, 10)));
		panelProfesional.add(l_nombreEmpresa);
		panelProfesional.add(nombreEmpresa_conyuge);
		panelProfesional.add(Box.createRigidArea(new Dimension(0, 10)));
		panelProfesional.add(l_actividad);
		panelProfesional.add(actividad_conyuge);
		panelProfesional.add(Box.createRigidArea(new Dimension(0, 10)));
		panelProfesional.add(l_antiguedad);
		panelProfesional.add(antiguedad_conyuge);
		panelProfesional.add(Box.createRigidArea(new Dimension(0, 10)));
		panelProfesional.add(l_puesto);
		panelProfesional.add(puesto_conyuge);
		panelProfesional.add(Box.createRigidArea(new Dimension(0, 10)));
		panelProfesional.add(l_direccionEmpresa);
		panelProfesional.add(direccionEmpresa_conyuge);
		panelProfesional.add(Box.createRigidArea(new Dimension(0, 10)));
		panelProfesional.add(l_contactoEmpresa);
		panelProfesional.add(contactoEmpresa_conyuge);
		panelProfesional.add(Box.createRigidArea(new Dimension(0, 10)));
		// panelProfesional.setPreferredSize(new Dimension((int) (ancho * 0.4), (int)
		// (alto * 1.2)));

		
		JPanel auxiliar = new JPanel();
		auxiliar.setLayout(new BoxLayout(auxiliar, BoxLayout.X_AXIS));
		auxiliar.add(new JPanel());
		panelProfesional.setMaximumSize(new Dimension(255,2000));
		auxiliar.add(panelProfesional, BorderLayout.CENTER);
		auxiliar.add(new JPanel());
		
		auxiliar.setBorder(BorderFactory.createLoweredBevelBorder());
		
		return auxiliar;
	}

	public JPanel setPanelEconomico() {

		JPanel panelEconomico = new JPanel();
		
		
		// para diferenciar paneles
		// panelEconomico.setPreferredSize(new Dimension((int) (ancho * 0.8), (int)
		// (alto * 1.2)));

		panelEconomico.setLayout(new BoxLayout(panelEconomico, BoxLayout.Y_AXIS));
		JLabel l_datoseconomicos = new JLabel("Datos Economicos:");
		l_datoseconomicos.setSize(new Dimension(250, 20));
		l_datoseconomicos.setForeground(Color.BLACK);
		Font f1 = new Font("Arial", Font.BOLD, 15);
		l_datoseconomicos.setFont(f1);

		JLabel l_ingresosFijos = new JLabel("Ingresos Fijos(mes):");
		ingresosFijos_conyuge = new JTextField();
		ingresosFijos_conyuge.setForeground(Color.gray);
		Font f = new Font("Serif", Font.BOLD, 12);
		ingresosFijos_conyuge.setFont(f);
		ingresosFijos_conyuge.setMaximumSize(new Dimension(250, 20));

		JLabel l_ingresosVariables = new JLabel("Ingresos Variables(mes):");
		ingresosVariables_conyuge = new JTextField();
		ingresosVariables_conyuge.setForeground(Color.gray);
		ingresosVariables_conyuge.setFont(f);
		ingresosVariables_conyuge.setMaximumSize(new Dimension(250, 20));

		JLabel l_gastosAlquiler = new JLabel("Gastos Alquiler:");
		gastosAlquiler_conyuge = new JTextField();
		gastosAlquiler_conyuge.setForeground(Color.gray);
		gastosAlquiler_conyuge.setFont(f);
		gastosAlquiler_conyuge.setMaximumSize(new Dimension(250, 20));

		JLabel l_gastosHipoteca = new JLabel("Gastos Hipoteca:");
		gastosHipoteca_conyuge = new JTextField();
		gastosHipoteca_conyuge.setForeground(Color.gray);
		gastosHipoteca_conyuge.setFont(f);
		gastosHipoteca_conyuge.setMaximumSize(new Dimension(250, 20));

		JLabel l_otros = new JLabel("Otros:");
		otros_conyuge = new JTextField();
		otros_conyuge.setForeground(Color.gray);
		otros_conyuge.setFont(f);
		otros_conyuge.setMaximumSize(new Dimension(250, 20));

		JLabel l_valor = new JLabel("Valor:");
		valor_conyuge = new JTextField();
		valor_conyuge.setForeground(Color.gray);
		valor_conyuge.setFont(f);
		valor_conyuge.setMaximumSize(new Dimension(250, 20));

		JLabel l_cargasVivienda = new JLabel("Cargas vivienda: ");
		cargasVivienda_conyuge = new JTextField();
		cargasVivienda_conyuge.setForeground(Color.gray);
		cargasVivienda_conyuge.setFont(f);
		cargasVivienda_conyuge.setMaximumSize(new Dimension(250, 20));

		JLabel l_otrosBienes = new JLabel("Otro Bienes: ");
		otrosBienes_conyuge = new JTextArea();
		otrosBienes_conyuge.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		otrosBienes_conyuge.setForeground(Color.gray);
		otrosBienes_conyuge.setMaximumSize(new Dimension(375, 70));

		chb_propiedad_conyuge = new JCheckBox("Propiedad");
		chb_propiedad_conyuge.setForeground(Color.BLACK);
		chb_escritura_conyuge = new JCheckBox("Escritura");
		chb_escritura_conyuge.setForeground(Color.BLACK);
		chb_contratoPrivado_conyuge = new JCheckBox("Contrato Privado");
		chb_contratoPrivado_conyuge.setForeground(Color.BLACK);
		chb_otrosGastos_conyuge = new JCheckBox("Otros");
		chb_otrosGastos_conyuge.setForeground(Color.BLACK);
		chb_padres_conyuge = new JCheckBox("Padres");
		chb_padres_conyuge.setForeground(Color.BLACK);
		chb_alquiler_conyuge = new JCheckBox("Alquiler");
		chb_alquiler_conyuge.setForeground(Color.BLACK);

		panelEconomico.add(l_datoseconomicos);
		panelEconomico.add(Box.createRigidArea(new Dimension(0, 10)));
		panelEconomico.add(l_ingresosFijos);
		panelEconomico.add(ingresosFijos_conyuge);
		panelEconomico.add(Box.createRigidArea(new Dimension(0, 10)));
		panelEconomico.add(l_ingresosVariables);
		panelEconomico.add(ingresosVariables_conyuge);
		panelEconomico.add(Box.createRigidArea(new Dimension(0, 10)));
		panelEconomico.add(l_gastosAlquiler);
		panelEconomico.add(gastosAlquiler_conyuge);
		panelEconomico.add(Box.createRigidArea(new Dimension(0, 10)));
		panelEconomico.add(l_gastosHipoteca);
		panelEconomico.add(gastosHipoteca_conyuge);
		panelEconomico.add(Box.createRigidArea(new Dimension(0, 10)));
		panelEconomico.add(l_otros);
		panelEconomico.add(otros_conyuge);
		panelEconomico.add(Box.createRigidArea(new Dimension(0, 10)));
		panelEconomico.add(l_valor);
		panelEconomico.add(valor_conyuge);
		panelEconomico.add(Box.createRigidArea(new Dimension(0, 10)));
		panelEconomico.add(l_cargasVivienda);
		panelEconomico.add(cargasVivienda_conyuge);
		panelEconomico.add(Box.createRigidArea(new Dimension(0, 10)));
		panelEconomico.add(chb_propiedad_conyuge);
		panelEconomico.add(Box.createRigidArea(new Dimension(0, 2)));
		panelEconomico.add(chb_escritura_conyuge);
		panelEconomico.add(Box.createRigidArea(new Dimension(0, 2)));
		panelEconomico.add(chb_contratoPrivado_conyuge);
		panelEconomico.add(Box.createRigidArea(new Dimension(0, 2)));
		panelEconomico.add(chb_otrosGastos_conyuge);
		panelEconomico.add(Box.createRigidArea(new Dimension(0, 2)));
		panelEconomico.add(chb_padres_conyuge);
		panelEconomico.add(Box.createRigidArea(new Dimension(0, 2)));
		panelEconomico.add(chb_alquiler_conyuge);
		panelEconomico.add(Box.createRigidArea(new Dimension(0, 2)));
		panelEconomico.add(l_otrosBienes);
		panelEconomico.add(otrosBienes_conyuge);
		panelEconomico.add(Box.createRigidArea(new Dimension(0, 1)));
		
		JPanel auxiliar = new JPanel();
		auxiliar.setLayout(new BoxLayout(auxiliar, BoxLayout.X_AXIS));
		auxiliar.add(new JPanel());
		panelEconomico.setMaximumSize(new Dimension(255,2000));
		auxiliar.add(panelEconomico, BorderLayout.CENTER);
		auxiliar.add(new JPanel());
		
		return auxiliar;
	}

	public void insertarBBDD() {

		String[] botones = { "LLEVAR DATOS A LA BBDD", "NO INSERTAR" };

		int resp = JOptionPane.showOptionDialog(null, " Usted insertara estos datos en la bbdd", "¿Esta seguro?",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null/* icono */, botones, botones[0]);

		if (resp == JOptionPane.YES_OPTION) {
			try {

				c = new MisConexiones();

			} catch (InstantiationException e1) {

			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				ps = c.getPS(ConfigDir.getInstance().getProperty("query5"));

				ps.setString(1, finalidad.getText());
				ps.setInt(2, Integer.valueOf(valorAdquisicion.getText()));
				ps.setInt(3, Integer.valueOf(valorImporte.getText()));
				ps.setInt(4, Integer.valueOf(plazo.getText()));
				ps.setString(5, direccion.getText());
				ps.setInt(6, Integer.valueOf(cargas.getText()));
				ps.setString(7, vinculacion.getText());
				ps.setString(8, dninie.getText());
				ps.setString(9, apellido1.getText());
				ps.setString(10, apellido2.getText());
				ps.setString(11, nombre.getText());
				ps.setTimestamp(12, Timestamp.valueOf(fechaNacimiento.getText()));
				ps.setBoolean(13, chb_fijo.isSelected());
				ps.setBoolean(14, chb_temporal.isSelected());
				ps.setBoolean(15, chb_autonomo.isSelected());
				ps.setBoolean(16, chb_otrosCosas.isSelected());
				ps.setString(17, profesion.getText());
				ps.setString(18, domicilio.getText());
				ps.setString(19, poblacion.getText());
				ps.setString(20, codigoPostal.getText());
				ps.setString(21, nombreEmpresa.getText());
				ps.setString(22, actividad.getText());
				ps.setString(23, antiguedad.getText());
				ps.setInt(24, Integer.valueOf(puesto.getText()));
				ps.setString(25, direccionEmpresa.getText());
				ps.setString(26, contactoEmpresa.getText());
				ps.setBoolean(27, chb_editar.isSelected());
				ps.setString(28, comentarios.getText());
				ps.setInt(29, Integer.valueOf(ingresosFijos.getText()));
				ps.setInt(30, Integer.valueOf(ingresosVariables.getText()));
				ps.setInt(31, Integer.valueOf(gastosAlquiler.getText()));
				ps.setInt(32, Integer.valueOf(gastosHipoteca.getText()));
				ps.setString(33, otros.getText());
				ps.setInt(34, Integer.valueOf(valor.getText()));
				ps.setInt(35, Integer.valueOf(cargasVivienda.getText()));
				ps.setBoolean(36, chb_propiedad.isSelected());
				ps.setBoolean(37, chb_escritura.isSelected());
				ps.setBoolean(38, chb_contratoPrivado.isSelected());
				ps.setBoolean(39, chb_otrosGastos.isSelected());
				ps.setBoolean(40, chb_padres.isSelected());
				ps.setBoolean(41, chb_alquiler.isSelected());
				ps.setString(42, otrosBienes.getText());

				ps = c.getPS(ConfigDir.getInstance().getProperty("query11"));

				ps.setString(1, dninie_conyuge.getText());
				ps.setString(2, apellido1_conyuge.getText());
				ps.setString(3, apellido2_conyuge.getText());
				ps.setString(4, nombre_conyuge.getText());
				ps.setTimestamp(5, Timestamp.valueOf(fechaNacimiento_conyuge.getText()));
				ps.setBoolean(6, chb_fijo_conyuge.isSelected());
				ps.setBoolean(7, chb_temporal_conyuge.isSelected());
				ps.setBoolean(8, chb_autonomo_conyuge.isSelected());
				ps.setBoolean(9, chb_otrosCosas_conyuge.isSelected());
				ps.setString(10, profesion_conyuge.getText());
				ps.setString(11, domicilio_conyuge.getText());
				ps.setString(12, poblacion_conyuge.getText());
				ps.setString(13, codigoPostal_conyuge.getText());

				ps.setString(14, nombreEmpresa_conyuge.getText());
				ps.setString(15, actividad_conyuge.getText());
				ps.setString(16, antiguedad_conyuge.getText());
				ps.setString(17, puesto_conyuge.getText());
				ps.setString(18, direccionEmpresa_conyuge.getText());
				ps.setString(19, contactoEmpresa_conyuge.getText());

				ps.setString(20, ingresosFijos_conyuge.getText());
				ps.setString(21, ingresosVariables_conyuge.getText());
				ps.setString(22, gastosAlquiler_conyuge.getText());
				ps.setString(23, gastosHipoteca_conyuge.getText());
				ps.setString(24, otros_conyuge.getText());
				ps.setString(25, valor_conyuge.getText());

				ps.setBoolean(26, chb_propiedad_conyuge.isSelected());
				ps.setBoolean(27, chb_escritura_conyuge.isSelected());
				ps.setBoolean(28, chb_contratoPrivado_conyuge.isSelected());
				ps.setBoolean(29, chb_padres_conyuge.isSelected());
				ps.setBoolean(30, chb_otrosGastos_conyuge.isSelected());
				ps.setBoolean(31, chb_alquiler_conyuge.isSelected());

				ps.setString(32, otrosBienes_conyuge.getText());

				/*
				 * profesion_conyuge, domicilio_conyuge, poblacion_conyuge, poblacion_conyuge,
				 * nombreEmpresa_conyuge, actividad_conyuge, antiguedad_conyuge, puesto_conyuge,
				 * direccionEmpresa_conyuge, contactoEmpresa_conyuge, ingresosFijos_conyuge,
				 * 
				 * ingresosVariables_conyuge, gastosAlquiler_conyuge, gastosHipoteca_conyuge,
				 * otros_conyuge, valor_conyuge, cargasVivienda_conyuge;
				 * 
				 * chb_propiedad_conyuge, chb_escritura_conyuge, chb_contratoPrivado_conyuge,
				 * chb_otrosGastos_conyuge, chb_padres_conyuge, chb_alquiler_conyuge,
				 * chb_fijo_conyuge, chb_temporal_conyuge, chb_autonomo_conyuge,
				 * chb_otrosCosas_conyuge;
				 */
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			try {
				ps.executeUpdate();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		}
		if (resp == JOptionPane.NO_OPTION) {
			String box = "0";
		}

	}

	public void imprimir() {

		File miTemplate = new File("src\\com\\crm\\auxiliares\\templates\\informe_hipoteca.dotm");
		WordProcessing.createNewDocumentFromTemplate(miTemplate.getAbsolutePath());
		WordProcessing.typeTextAtBookmark("fin", finalidad.getText());
		WordProcessing.typeTextAtBookmark("valad", valorAdquisicion.getText());
		WordProcessing.typeTextAtBookmark("credito", valorImporte.getText());
		WordProcessing.typeTextAtBookmark("plazotot", plazo.getText());
		WordProcessing.typeTextAtBookmark("dom", direccion.getText());
		WordProcessing.typeTextAtBookmark("cargas", cargas.getText());
		WordProcessing.typeTextAtBookmark("dni_nie", dninie.getText());
		WordProcessing.typeTextAtBookmark("ape1", apellido1.getText());
		WordProcessing.typeTextAtBookmark("ape2", apellido2.getText());
		WordProcessing.typeTextAtBookmark("nombrecli", nombre.getText());
		WordProcessing.typeTextAtBookmark("fechan", fechaNacimiento.getText());
		WordProcessing.typeTextAtBookmark("prof", profesion.getText());
		WordProcessing.typeTextAtBookmark("dom", domicilio.getText());
		WordProcessing.typeTextAtBookmark("pob", poblacion.getText());
		WordProcessing.typeTextAtBookmark("cp", codigoPostal.getText());
		WordProcessing.typeTextAtBookmark("nomemp", nombreEmpresa.getText());
		WordProcessing.typeTextAtBookmark("activemp", actividad.getText());
		WordProcessing.typeTextAtBookmark("antiguo", antiguedad.getText());
		WordProcessing.typeTextAtBookmark("posicion", puesto.getText());
		WordProcessing.typeTextAtBookmark("diremp", direccionEmpresa.getText());
		WordProcessing.typeTextAtBookmark("telemp", contactoEmpresa.getText());
		WordProcessing.typeTextAtBookmark("fijosmen", ingresosFijos.getText());
		WordProcessing.typeTextAtBookmark("varmen", ingresosVariables.getText());
		WordProcessing.typeTextAtBookmark("gastosalq", gastosAlquiler.getText());
		WordProcessing.typeTextAtBookmark("gastoshipo", gastosHipoteca.getText());
		WordProcessing.typeTextAtBookmark("otrospres", otros.getText());
		WordProcessing.typeTextAtBookmark("valorvivi", valor.getText());
		WordProcessing.typeTextAtBookmark("cargasvivi", cargasVivienda.getText());
		WordProcessing.typeTextAtBookmark("propiedad", "" + chb_propiedad.isSelected());
		WordProcessing.typeTextAtBookmark("escritura", "" + chb_escritura.isSelected());
		WordProcessing.typeTextAtBookmark("contratpriv", "" + chb_contratoPrivado.isSelected());
		WordProcessing.typeTextAtBookmark("otros", "" + chb_otrosGastos.isSelected());
		WordProcessing.typeTextAtBookmark("padres", "" + chb_padres.isSelected());
		WordProcessing.typeTextAtBookmark("alquiler", "" + chb_alquiler.isSelected());
		WordProcessing.typeTextAtBookmark("fijo", "" + chb_fijo.isSelected());
		WordProcessing.typeTextAtBookmark("temp", "" + chb_temporal.isSelected());
		WordProcessing.typeTextAtBookmark("aut", "" + chb_autonomo.isSelected());
		WordProcessing.typeTextAtBookmark("otrosmas", "" + chb_otrosCosas.isSelected());
		WordProcessing.typeTextAtBookmark("dni_nie_conyu", dninie_conyuge.getText());
		WordProcessing.typeTextAtBookmark("ape1_conyu", apellido1_conyuge.getText());
		WordProcessing.typeTextAtBookmark("ape2_conyu", apellido2_conyuge.getText());
		WordProcessing.typeTextAtBookmark("nombrecli_conyu", nombre_conyuge.getText());
		WordProcessing.typeTextAtBookmark("fechan_conyu", fechaNacimiento_conyuge.getText());
		WordProcessing.typeTextAtBookmark("prof_conyu", profesion_conyuge.getText());
		WordProcessing.typeTextAtBookmark("dom_conyu", domicilio_conyuge.getText());
		WordProcessing.typeTextAtBookmark("pob_conyu", poblacion_conyuge.getText());
		WordProcessing.typeTextAtBookmark("cp_conyu", codigoPostal_conyuge.getText());
		WordProcessing.typeTextAtBookmark("nomemp_conyu", nombreEmpresa_conyuge.getText());
		WordProcessing.typeTextAtBookmark("activemp_conyu", actividad_conyuge.getText());
		WordProcessing.typeTextAtBookmark("antiguo_conyu", antiguedad_conyuge.getText());
		WordProcessing.typeTextAtBookmark("posicion_conyu", puesto_conyuge.getText());
		WordProcessing.typeTextAtBookmark("diremp_conyu", direccionEmpresa_conyuge.getText());
		WordProcessing.typeTextAtBookmark("telemp_conyu", contactoEmpresa_conyuge.getText());
		WordProcessing.typeTextAtBookmark("fijosmen_conyu", ingresosFijos_conyuge.getText());
		WordProcessing.typeTextAtBookmark("varmen_conyu", ingresosVariables_conyuge.getText());
		WordProcessing.typeTextAtBookmark("gastosalq_conyu", gastosAlquiler_conyuge.getText());
		WordProcessing.typeTextAtBookmark("gastoshipo_conyu", gastosHipoteca_conyuge.getText());
		WordProcessing.typeTextAtBookmark("otrospres_conyu", otros_conyuge.getText());
		WordProcessing.typeTextAtBookmark("valorvivi_conyu", valor_conyuge.getText());
		WordProcessing.typeTextAtBookmark("cargasvivi_conyu", cargasVivienda_conyuge.getText());
		WordProcessing.typeTextAtBookmark("propiedad_conyu", "" + chb_propiedad_conyuge.isSelected());
		WordProcessing.typeTextAtBookmark("escritura_conyu", "" + chb_escritura_conyuge.isSelected());
		WordProcessing.typeTextAtBookmark("contratpriv_conyu", "" + chb_contratoPrivado_conyuge.isSelected());
		WordProcessing.typeTextAtBookmark("otros_conyu", "" + chb_otrosGastos_conyuge.isSelected());
		WordProcessing.typeTextAtBookmark("padres_conyu", "" + chb_padres_conyuge.isSelected());
		WordProcessing.typeTextAtBookmark("alquiler_conyu", "" + chb_alquiler_conyuge.isSelected());
		WordProcessing.typeTextAtBookmark("fijo_conyu", "" + chb_fijo_conyuge.isSelected());
		WordProcessing.typeTextAtBookmark("temp_conyu", "" + chb_temporal_conyuge.isSelected());
		WordProcessing.typeTextAtBookmark("aut_conyu", "" + chb_autonomo_conyuge.isSelected());
		WordProcessing.typeTextAtBookmark("otrosmas_conyu", "" + chb_otrosCosas_conyuge.isSelected());

		String nombreHipotecado = nombre.getText();
		CreaCarpetaInformes(nombreHipotecado);
		WordProcessing.changeDocumentDirectory(System.getProperty("user.home") + "\\documents\\Informes_hipotecario\\" +nombreHipotecado);
		WordProcessing.saveDocumentAs(nombreHipotecado);
		WordProcessing.exec();

	}

	private void CreaCarpetaInformes(String nombreHipotecado) {
		String fileName = System.getProperty("user.home") + "\\documents\\Informes_hipotecario\\" + nombreHipotecado;

		Path path = Paths.get(fileName);

		if (!Files.exists(path)) {
			try {
				Files.createDirectory(path);
				System.out.println("New Directory created !   " + fileName);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("no se ha creado");
				e.printStackTrace();
			}

		} else {

			System.out.println("Directory already exists");
		}
	}

}
