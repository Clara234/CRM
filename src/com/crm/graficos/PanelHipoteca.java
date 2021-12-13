package com.crm.graficos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.print.PrintException;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class PanelHipoteca extends JPanel {
	DefaultTableModel dtm;
	JTable tabla;
	JTextField finalidad, valorAdquisicion, valorImporte, plazo, direccion, cargas, vinculacion, busquedacliente,
		 dninie, apellido1, apellido2, nombre, fechaNacimiento, profesion, domicilio, poblacion,
			codigoPostal, nombreEmpresa, actividad, antiguedad, puesto, direccionEmpresa, contactoEmpresa,
			ingresosFijos, ingresosVariables, gastosAlquiler, gastosHipoteca, otros, valor, cargasVivienda;
	JButton imprimir, insertar, limpiar;
	JCheckBox chb_editar, chb_propiedad, chb_escritura, chb_contratoPrivado, chb_otrosGastos, chb_padres, chb_alquiler,
			chb_fijo, chb_temporal, chb_autonomo, chb_otros;
	JTextArea otrosBienes;
	public JComboBox tipo, nueva, estadoCivil, regimenBienes;

	public PanelHipoteca(int alto, int ancho) {
		setLayout(new BorderLayout());
		add(setPanelNorte(alto, ancho, setPanelNorteDatos1(alto, ancho), setPanelNorteDatos2(alto, ancho),
				setPanelNorteDatos3(alto, ancho)), BorderLayout.NORTH);
		add(setPanelOeste(alto, ancho, setPanelOesteDatos(alto, ancho)), BorderLayout.WEST);
		add(setPanelEste(alto, ancho, setPanelEsteDatos2(alto, ancho), setPanelControl(alto, ancho)));

	}

	public JPanel setPanelNorte(int alto, int ancho, JPanel jp1, JPanel jp2, JPanel jp3) {

		JPanel panelNorte = new JPanel();
		panelNorte.setLayout(new BorderLayout());
		panelNorte.setPreferredSize(new Dimension((int) (ancho * 0.4), (int) (alto * 0.2))); // no cambiarr porfavor
		panelNorte.add(jp1, BorderLayout.NORTH);
		panelNorte.add(jp2, BorderLayout.CENTER);
		panelNorte.add(jp3, BorderLayout.SOUTH);

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
		finalidad.setMaximumSize(new Dimension(250, 15));

		JLabel l_creditoSolicitado = new JLabel("CREDITO SOLICITADO : ");
		l_creditoSolicitado.setForeground(Color.BLACK);
		Font f1 = new Font("Serif", Font.BOLD, 12);
		l_creditoSolicitado.setFont(f1);

		JLabel l_valorAdquisicion = new JLabel("Valor de  adquisicion: ");
		valorAdquisicion = new JTextField();
		valorAdquisicion.setForeground(Color.gray);
		valorAdquisicion.setFont(f);
		valorAdquisicion.setMaximumSize(new Dimension(250, 15));

		JLabel l_importe = new JLabel("Importe Credito");
		valorImporte = new JTextField();
		valorImporte.setForeground(Color.BLACK);
		valorImporte.setFont(f);
		valorImporte.setMaximumSize(new Dimension(250, 15));

		JLabel l_plazo = new JLabel("Plazo Total(meses): ");
		plazo = new JTextField();
		plazo.setForeground(Color.BLACK);
		plazo.setFont(f);
		plazo.setMaximumSize(new Dimension(250, 15));

		panelPrimero.add(l_finalidad);
		panelPrimero.add(finalidad);
		panelPrimero.add(Box.createRigidArea(new Dimension(0, 10)));
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
		/*
		 * ^
		 */

		return panelPrimero;

	}

	public JPanel setPanelNorteDatos2(int alto, int ancho) {

		JPanel panelNorteDatos2 = new JPanel();
		panelNorteDatos2.setLayout(new BoxLayout(panelNorteDatos2, BoxLayout.X_AXIS));
		panelNorteDatos2.setBorder(BorderFactory.createLoweredBevelBorder());

		JLabel l_direccionVivienda = new JLabel("Direccion de Vivienda:");
		direccion = new JTextField();
		direccion.setForeground(Color.BLACK);
		Font f = new Font("Serif", Font.BOLD, 12);
		direccion.setFont(f);
		direccion.setMaximumSize(new Dimension(250, 20));

		JLabel l_tipo = new JLabel("Tipo:  ");
		tipo = new JComboBox();
		tipo.addItem("Libre");
		tipo.addItem("Con cargas");
		tipo.setForeground(Color.BLACK);
		tipo.setFont(f);
		tipo.setMaximumSize(new Dimension(250, 20));

		JLabel l_cargas = new JLabel("Cargas:  ");
		cargas = new JTextField();
		cargas.setForeground(Color.BLACK);
		cargas.setFont(f);
		cargas.setMaximumSize(new Dimension(250, 20));

		JLabel l_nueva = new JLabel("Nueva:  ");
		nueva = new JComboBox();
		nueva.addItem("Si");
		nueva.addItem("No");
		nueva.setForeground(Color.BLACK);
		nueva.setFont(f);
		nueva.setMaximumSize(new Dimension(250, 20));

		JLabel l_estadoCivil = new JLabel("Estado Civil:  ");
		estadoCivil = new JComboBox();
		estadoCivil.addItem("Solter@");
		estadoCivil.addItem("Casad@");
		estadoCivil.addItem("Divorciad@");
		estadoCivil.addItem("Separacion en proceso");
		estadoCivil.addItem("Viud@");
		estadoCivil.addItem("Concubinato");

		estadoCivil.setForeground(Color.BLACK);
		estadoCivil.setFont(f);
		estadoCivil.setMaximumSize(new Dimension(250, 20));

		JLabel l_regimenBienes = new JLabel("Regimen de bienes:  ");
		regimenBienes = new JComboBox();
		regimenBienes.addItem("Gananciales");
		regimenBienes.addItem("Separacion de bienes");
		regimenBienes.addItem("Participacion");
		regimenBienes.setForeground(Color.BLACK);
		regimenBienes.setFont(f);
		regimenBienes.setMaximumSize(new Dimension(250, 20));

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

		// panelNorteDatos2.add(Box.createRigidArea(new Dimension(0, 1)));
		// panelNorteDatos2.setPreferredSize(new Dimension((int) (alto * 0.9), (int)
		// (ancho*0.2)));

		return panelNorteDatos2;
	}

	public JPanel setPanelNorteDatos3(int alto, int ancho) {

		JPanel panelNorteDatos3 = new JPanel();
		panelNorteDatos3.setLayout(new BoxLayout(panelNorteDatos3, BoxLayout.X_AXIS));
		panelNorteDatos3.setBorder(BorderFactory.createLoweredBevelBorder());

		JLabel l_vinculacion = new JLabel("Vinculado a :");
		vinculacion = new JTextField();
		vinculacion.setForeground(Color.BLACK);
		Font f = new Font("Serif", Font.BOLD, 12);
		vinculacion.setFont(f);
		vinculacion.setMaximumSize(new Dimension(250, 20));
		JLabel l_busquedacliente = new JLabel("Busqueda cliente:");
		busquedacliente = new JTextField();
		busquedacliente.setForeground(Color.BLACK);
		busquedacliente.setFont(f);
		busquedacliente.setMaximumSize(new Dimension(250, 20));

		panelNorteDatos3.add(l_vinculacion);
		panelNorteDatos3.add(vinculacion);
		panelNorteDatos3.add(Box.createRigidArea(new Dimension(0, 10)));
		panelNorteDatos3.add(l_busquedacliente);
		panelNorteDatos3.add(busquedacliente);
		panelNorteDatos3.add(Box.createRigidArea(new Dimension(0, 10)));

		return panelNorteDatos3;

	}

	public JPanel setPanelEste(int alto, int ancho, JPanel jp1, JPanel jp2) {
		JPanel panelEste = new JPanel();
		panelEste.setLayout(new BorderLayout());
		panelEste.setPreferredSize(new Dimension((int) (ancho * 0.2), (int) (alto * 1.2)));
		panelEste.add(jp1, BorderLayout.CENTER);
		panelEste.add(jp2, BorderLayout.WEST);
		return panelEste;

	}

//correcto
	public JPanel setPanelEsteDatos2(int alto, int ancho) {

		JPanel panelEsteDatos2 = new JPanel();
		panelEsteDatos2.setBorder(BorderFactory.createLoweredBevelBorder()); // borde
		// para diferenciar paneles

		panelEsteDatos2.setLayout(new BoxLayout(panelEsteDatos2, BoxLayout.Y_AXIS));
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

		panelEsteDatos2.add(l_datoseconomicos);
		panelEsteDatos2.add(Box.createRigidArea(new Dimension(0, 10)));
		panelEsteDatos2.add(l_ingresosFijos);
		panelEsteDatos2.add(ingresosFijos);
		panelEsteDatos2.add(Box.createRigidArea(new Dimension(0, 10)));
		panelEsteDatos2.add(l_ingresosVariables);
		panelEsteDatos2.add(ingresosVariables);
		panelEsteDatos2.add(Box.createRigidArea(new Dimension(0, 10)));
		panelEsteDatos2.add(l_gastosAlquiler);
		panelEsteDatos2.add(gastosAlquiler);
		panelEsteDatos2.add(Box.createRigidArea(new Dimension(0, 10)));
		panelEsteDatos2.add(l_gastosHipoteca);
		panelEsteDatos2.add(gastosHipoteca);
		panelEsteDatos2.add(Box.createRigidArea(new Dimension(0, 10)));
		panelEsteDatos2.add(l_otros);
		panelEsteDatos2.add(otros);
		panelEsteDatos2.add(Box.createRigidArea(new Dimension(0, 10)));
		panelEsteDatos2.add(l_valor);
		panelEsteDatos2.add(valor);
		panelEsteDatos2.add(Box.createRigidArea(new Dimension(0, 10)));
		panelEsteDatos2.add(l_cargasVivienda);
		panelEsteDatos2.add(cargasVivienda);
		panelEsteDatos2.add(Box.createRigidArea(new Dimension(0, 10)));
		panelEsteDatos2.add(chb_propiedad);
		panelEsteDatos2.add(chb_escritura);
		panelEsteDatos2.add(chb_contratoPrivado);
		panelEsteDatos2.add(chb_otrosGastos);
		panelEsteDatos2.add(chb_padres);
		panelEsteDatos2.add(chb_alquiler);
		panelEsteDatos2.add(l_otrosBienes);
		panelEsteDatos2.add(otrosBienes);

		panelEsteDatos2.setPreferredSize(new Dimension((int) (ancho * 1.2), (int) (alto * 0.5)));

		return panelEsteDatos2;

	}

	public JPanel setPanelControl(int alto, int ancho) {

		JPanel panelControl = new JPanel();
		panelControl.setLayout(new BoxLayout(panelControl, BoxLayout.Y_AXIS));
		panelControl.setBorder(BorderFactory.createLoweredBevelBorder());

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

		JLabel l_contactoEmpresa = new JLabel("Telefono/fax/correo-e de la empresa:  ");
		contactoEmpresa = new JTextField();
		contactoEmpresa.setForeground(Color.gray);
		contactoEmpresa.setFont(f);
		contactoEmpresa.setMaximumSize(new Dimension(250, 20));
		JTable b = new JTable();
		imprimir = new JButton("IMPRIMIR PETICION");
		imprimir.setForeground(Color.BLUE);
		b.add(imprimir);
		insertar = new JButton("INSERTAR EN BBDD");
		insertar.setForeground(Color.BLUE);
		b.add(insertar);
		limpiar = new JButton("LIMPIAR");
		limpiar.setForeground(Color.BLUE);
		b.add(limpiar);
		chb_editar = new JCheckBox("Editar Campos");
		chb_editar.setForeground(Color.BLACK);
		b.add(chb_editar);
		imprimir.addActionListener(new gestorImprimir());
		insertar.addActionListener(new gestorInsertar());
		limpiar.addActionListener(new gestorLimpiar());

		b.setVisible(true);

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
		panelControl.add(Box.createRigidArea(new Dimension(0, 6)));
		panelControl.add(imprimir);
		panelControl.add(Box.createRigidArea(new Dimension(0, 6)));
		panelControl.add(insertar);
		panelControl.add(Box.createRigidArea(new Dimension(0, 6)));
		panelControl.add(limpiar);
		panelControl.add(Box.createRigidArea(new Dimension(0, 6)));
		panelControl.add(chb_editar);
		panelControl.add(Box.createRigidArea(new Dimension(0, 10)));

		panelControl.add(Box.createRigidArea(new Dimension(0, 1)));
		panelControl.add(b);
		panelControl.setPreferredSize(new Dimension((int) (alto * 0.5), (int) (ancho * 0.5)));
		panelControl.setVisible(true);
		return panelControl;

	}

	public class gestorImprimir implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
			PrinterJob pj =  PrinterJob.getPrinterJob();
         pj.setPrintable(null);
			boolean top = pj.printDialog();
			if(top) {
				pj.print();
			}

		}catch(PrinterException pex) {
			JOptionPane.showMessageDialog(null,"Eerrr de mesaej","error/a" +pex, JOptionPane.INFORMATION_MESSAGE);
		}

	
		
	}
	}

	public class gestorInsertar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

		}
	}

	public class gestorLimpiar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			// limpiar el jtextfield
			clearfinalidad();
			clearvalorAdquisicion();
			clearvalorImporte();
			clearplazo();
			cleardireccion();
			clearcargas();
			clearvinculacion();
			clearbusquedacliente();
			cleardninie();
			clearapellido1();
			clearapellido2();
			clearnombre();
			clearfechaNacimiento();
			clearprofesion();
			cleardomicilio();
			clearpoblacion();
			clearcodigoPostal();
			clearnombreEmpresa();
			clearactividad();
			clearantiguedad();
			clearpuesto();
			cleardireccionEmpresa();
			clearcontactoEmpresa();
			clearingresosFijos();
			clearingresosVariables();
			cleargastosAlquiler();
			cleargastosHipoteca();
			clearotros();
			clearvalor();
			clearcargasVivienda();
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
			chb_otros.setSelected(false);
			// limpiar el jcombobox
			tipo.setSelectedIndex(0);
			nueva.setSelectedIndex(0);
			estadoCivil.setSelectedIndex(0);
			regimenBienes.setSelectedIndex(0);
		

			// limpiar el jtextAREA
	
			clearotrosBienes();
		}

	}

	public void clearfinalidad() {
		finalidad.setText("");
	}

	public void clearvalorAdquisicion() {
		valorAdquisicion.setText("");
	}

	public void clearvalorImporte() {
		valorImporte.setText("");
	}

	public void clearplazo() {
		plazo.setText("");
	}

	public void cleardireccion() {
		direccion.setText("");
	}

	public void clearcargas() {
		cargas.setText("");
	}

	public void clearvinculacion() {
		vinculacion.setText("");
	}

	public void clearbusquedacliente() {
		busquedacliente.setText("");
	}


	public void cleardninie() {
		dninie.setText("");
	}

	public void clearapellido1() {
		apellido1.setText("");
	}

	public void clearapellido2() {
		apellido2.setText("");
	}

	public void clearnombre() {
		nombre.setText("");
	}

	public void clearfechaNacimiento() {
		fechaNacimiento.setText("");
	}

	public void clearprofesion() {
		profesion.setText("");
	}

	public void cleardomicilio() {
		domicilio.setText("");
	}

	public void clearpoblacion() {
		poblacion.setText("");
	}

	public void clearcodigoPostal() {
		codigoPostal.setText("");
	}

	public void clearnombreEmpresa() {
		nombreEmpresa.setText("");
	}

	public void clearactividad() {
		actividad.setText("");
	}

	public void clearantiguedad() {
		antiguedad.setText("");
	}

	public void clearpuesto() {
		puesto.setText("");
	}

	public void cleardireccionEmpresa() {
		direccionEmpresa.setText("");
	}

	public void clearcontactoEmpresa() {
		contactoEmpresa.setText("");
	}

	public void clearingresosFijos() {
		ingresosFijos.setText("");
	}

	public void clearingresosVariables() {
		ingresosVariables.setText("");
	}

	public void cleargastosAlquiler() {
		gastosAlquiler.setText("");
	}

	public void cleargastosHipoteca() {
		gastosHipoteca.setText("");
	}

	public void clearotros() {
		otros.setText("");
	}

	public void clearvalor() {
		valor.setText("");
	}

	public void clearcargasVivienda() {
		cargasVivienda.setText("");
	}


	public void clearotrosBienes() {
		otrosBienes.setText("");
	}

	public JPanel setPanelOeste(int alto, int ancho, JPanel j1) {
		JPanel panelOeste = new JPanel();
		panelOeste.setLayout(new BorderLayout());
		panelOeste.setPreferredSize(new Dimension((int) (ancho * 0.30), (int) (alto * 0.9)));
		panelOeste.add(j1, BorderLayout.WEST);
		return panelOeste;

	}

	public JPanel setPanelOesteDatos(int alto, int ancho) {
		JPanel panelOesteDatos = new JPanel();
		panelOesteDatos.setBorder(BorderFactory.createLoweredBevelBorder());
		panelOesteDatos.setLayout(new BoxLayout(panelOesteDatos, BoxLayout.Y_AXIS));
		panelOesteDatos.setSize(250, 250);
		panelOesteDatos.add(Box.createRigidArea(new Dimension(0, 1)));
		panelOesteDatos.setPreferredSize(new Dimension((int) (ancho * 0.5), (int) (alto * 0.3)));
		panelOesteDatos.setBorder(BorderFactory.createLoweredBevelBorder());
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
		chb_otros = new JCheckBox("Otros");
		chb_otros.setForeground(Color.BLACK);

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

		panelOesteDatos.add(l_datosPersonales);
		panelOesteDatos.add(Box.createRigidArea(new Dimension(0, 10)));
		panelOesteDatos.add(l_titular);
		panelOesteDatos.add(Box.createRigidArea(new Dimension(0, 10)));
		panelOesteDatos.add(l_dninie);
		panelOesteDatos.add(dninie);
		panelOesteDatos.add(Box.createRigidArea(new Dimension(0, 10)));
		panelOesteDatos.add(l_apellido1);
		panelOesteDatos.add(apellido1);
		panelOesteDatos.add(Box.createRigidArea(new Dimension(0, 10)));
		panelOesteDatos.add(l_apellido2);
		panelOesteDatos.add(apellido2);
		panelOesteDatos.add(Box.createRigidArea(new Dimension(0, 10)));
		panelOesteDatos.add(l_nombre);
		panelOesteDatos.add(nombre);
		panelOesteDatos.add(Box.createRigidArea(new Dimension(0, 10)));
		panelOesteDatos.add(l_fechanacimiento);
		panelOesteDatos.add(fechaNacimiento);
		panelOesteDatos.add(Box.createRigidArea(new Dimension(0, 10)));
		panelOesteDatos.add(l_ocupacion);
		panelOesteDatos.add(Box.createRigidArea(new Dimension(0, 10)));
		panelOesteDatos.add(chb_fijo);
		panelOesteDatos.add(chb_temporal);
		panelOesteDatos.add(chb_autonomo);
		panelOesteDatos.add(chb_otros);
		panelOesteDatos.add(l_profesion);
		panelOesteDatos.add(profesion);
		panelOesteDatos.add(Box.createRigidArea(new Dimension(0, 10)));
		panelOesteDatos.add(l_domicilio);
		panelOesteDatos.add(domicilio);
		panelOesteDatos.add(Box.createRigidArea(new Dimension(0, 10)));
		panelOesteDatos.add(l_poblacion);
		panelOesteDatos.add(poblacion);
		panelOesteDatos.add(Box.createRigidArea(new Dimension(0, 10)));
		panelOesteDatos.add(l_codigopostal);
		panelOesteDatos.add(codigoPostal);
		panelOesteDatos.add(Box.createRigidArea(new Dimension(0, 10)));

		return panelOesteDatos;

	}

}