package com.crm.graficos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class PanelHipoteca extends JPanel {
	DefaultTableModel dtm;
	JTable tabla;
	JTextField finalidad, valorAdquisicion, valorImporte, plazo, direccion, cargas, titular, dninie, apellido1, apellido2,
			nombre, fechaNacimiento, profesion, domicilio, poblacion, codigoPostal, nombreEmpresa, actividad,
			antiguedad, puesto, direccionEmpresa, contactoEmpresa, ingresosFijos, ingresosVariables, gastosAlquiler,
			gastosHipoteca, otros, valor, cargasVivienda;
	JButton imprimir, insertar, limpiar;
	JCheckBox chb_editar, chb_propiedad, chb_escritura, chb_contratoPrivado, chb_otrosGastos, chb_padres, chb_alquiler, chb_fijo,chb_temporal,chb_autonomo, chb_otros;
	JTextArea comentarios, otrosBienes;
	public JComboBox tipo, nueva, estadoCivil, regimenBienes, ocupacion;

	public PanelHipoteca(int alto, int ancho) {
		setLayout(new BorderLayout());

		add(setPanelControl(alto, ancho), BorderLayout.NORTH);
		add(setPanelNorte(alto, ancho, setPanelNorteDatos(alto, ancho)), BorderLayout.WEST);
		add(setPanelEste(alto, ancho, setPanelEsteDatos(alto, ancho), setPanelEsteDatos2(ancho, alto)),
				BorderLayout.EAST);
		// add(setTabla(), BorderLayout.NORTH);

	}

	public JPanel setPanelEste(int alto, int ancho, JPanel jp1, JPanel jp2) {
		JPanel panelEste = new JPanel();
		panelEste.setLayout(new BorderLayout());
		panelEste.setPreferredSize(new Dimension((int) (ancho * 0.30), (int) (alto * 0.9)));
		panelEste.add(jp1, BorderLayout.NORTH);
		panelEste.add(jp2, BorderLayout.CENTER);

		return panelEste;

	}

//correcto
	public JPanel setPanelEsteDatos(int alto, int ancho) {

		JPanel panelEsteDatos = new JPanel();
		panelEsteDatos.setBorder(BorderFactory.createLoweredBevelBorder());  //borde para diferenciar paneles

		panelEsteDatos.setLayout(new BoxLayout(panelEsteDatos, BoxLayout.Y_AXIS));
		JLabel l_datoseconomicos = new JLabel("DATOS ECONOMICOS: ");
		l_datoseconomicos.setSize(new Dimension(250, 20));

		JLabel l_ingresosFijos = new JLabel("Ingresos Fijos(mes):");
		ingresosFijos = new JTextField();
		ingresosFijos.setForeground(Color.gray);
		Font f = new Font("Italic", Font.ITALIC, 12);
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
		otrosBienes.setMaximumSize(new Dimension(250, 60));

		JLabel l_propiedad = new JLabel("Propiedad");
		l_propiedad.setForeground(Color.black);
		chb_propiedad = new JCheckBox();
		JLabel l_escritura = new JLabel("Escritura");
		l_escritura.setForeground(Color.black);
		chb_escritura = new JCheckBox();
		JLabel l_contratoprivado = new JLabel("Contrato privado");
		l_contratoprivado.setForeground(Color.black);
		chb_contratoPrivado = new JCheckBox();
		JLabel l_otrosGastos = new JLabel("Otros");
		l_otrosGastos.setForeground(Color.black);
		chb_otrosGastos = new JCheckBox();
		JLabel l_padres = new JLabel("Padres");
		l_padres.setForeground(Color.black);
		chb_padres = new JCheckBox();
		JLabel l_alquiler = new JLabel("Alquiler");
		l_alquiler.setForeground(Color.black);
		chb_alquiler = new JCheckBox();

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
		panelEsteDatos.add(l_propiedad);
		panelEsteDatos.add(chb_propiedad);
		panelEsteDatos.add(l_escritura);
		panelEsteDatos.add(chb_escritura);
		panelEsteDatos.add(l_contratoprivado);
		panelEsteDatos.add(chb_contratoPrivado);
		panelEsteDatos.add(l_otrosGastos);
		panelEsteDatos.add(chb_otrosGastos);
		panelEsteDatos.add(l_padres);
		panelEsteDatos.add(chb_padres);
		panelEsteDatos.add(l_alquiler);
		panelEsteDatos.add(chb_alquiler);
		panelEsteDatos.add(l_otrosBienes);
		panelEsteDatos.add(otrosBienes);
		panelEsteDatos.add(Box.createRigidArea(new Dimension(0, 10)));

		panelEsteDatos.add(Box.createRigidArea(new Dimension(0, 1)));
		panelEsteDatos.setPreferredSize(new Dimension((int) (ancho * 0.01), (int) (alto * 1.2)));

		return panelEsteDatos;

	}

	public JPanel setPanelEsteDatos2(int alto, int ancho) {

		JPanel PanelEsteDatos2 = new JPanel();
		PanelEsteDatos2.add(Box.createRigidArea(new Dimension(0, 1)));
		PanelEsteDatos2.setPreferredSize(new Dimension((int) (alto * 0.01), (int) (ancho * 0.01)));
		return PanelEsteDatos2;

	}

	public JPanel setPanelControl(int alto, int ancho) {

		JPanel panelControl = new JPanel();
		panelControl.setLayout(new BoxLayout(panelControl, BoxLayout.X_AXIS));
		//panelControl.setBorder(BorderFactory.createLoweredBevelBorder());  

		/*
		 * imprimir = new JButton("IMPRIMIR PETICION");
		 * imprimir.setForeground(Color.RED); insertar = new
		 * JButton("INSERTAR EN BBDD"); insertar.setForeground(Color.RED); limpiar = new
		 * JButton("LIMPIAR"); limpiar.setForeground(Color.RED);
		 * 
		 * 
		 * 
		 * panelControl.add(imprimir); panelControl.add(insertar);
		 * panelControl.add(limpiar); limpiar.setVisible(true);
		 */

		JTable tabla1 = new JTable();
		tabla1.setLocation(100, 50);

		JLabel l_finalidad = new JLabel("Finalidad: ");
		finalidad = new JTextField();
		finalidad.setForeground(Color.gray);
		Font f = new Font("Italic", Font.ITALIC, 12);
		finalidad.setFont(f);
		finalidad.setMaximumSize(new Dimension(70, 15));

		JLabel l_valorAdquisicion = new JLabel(" Valor Adquisicion: ");
		valorAdquisicion = new JTextField();
		valorAdquisicion.setForeground(Color.gray);
		valorAdquisicion.setFont(f);
		valorAdquisicion.setMaximumSize(new Dimension(70, 15));

		JLabel l_valorImporte = new JLabel(" Valor Importe: ");
		valorImporte = new JTextField();
		valorImporte.setForeground(Color.gray);
		valorImporte.setFont(f);
		valorImporte.setMaximumSize(new Dimension(70, 15));

		JLabel l_plazo = new JLabel(" PLazo: ");
		plazo = new JTextField();
		plazo.setForeground(Color.gray);
		plazo.setFont(f);
		plazo.setMaximumSize(new Dimension(70, 15));
		JTable tabla2 = new JTable();
		tabla2.setPreferredSize(new Dimension(200, 100));

		JLabel l_direccion = new JLabel(" Direccion vivienda: ");
		direccion = new JTextField();
		direccion.setForeground(Color.gray);
		direccion.setFont(f);
		direccion.setMaximumSize(new Dimension(70, 15));

		JLabel l_tipo = new JLabel(" Tipo: ");
		tipo = new JComboBox();
		tipo.addItem("Libre");
		tipo.addItem("Con cargas");

		tipo.setMaximumSize(new Dimension(70, 15));

		JLabel l_nueva = new JLabel(" Nueva: ");
		nueva = new JComboBox();
		nueva.addItem("SI");
		nueva.addItem("NO");
		nueva.setMaximumSize(new Dimension(70, 15));

		JLabel l_estadoCivil = new JLabel(" Estado Civil: ");
		estadoCivil = new JComboBox();
		estadoCivil.addItem("Solter@");
		estadoCivil.addItem("Casad@");
		estadoCivil.addItem("Viud@");
		estadoCivil.setMaximumSize(new Dimension(70, 15));

		JLabel l_regimenBienes = new JLabel(" Regimen Bienes: ");
		regimenBienes = new JComboBox();
		regimenBienes.addItem("Comunidad");
		regimenBienes.addItem("Separacion");
		regimenBienes.addItem("Mixtos");
		regimenBienes.setMaximumSize(new Dimension(70, 15));

		tabla1.setVisible(true);
		tabla2.setVisible(true);
		tabla1.add(l_finalidad);
		tabla1.add(finalidad);
		tabla1.add(l_valorAdquisicion);
		tabla1.add(valorAdquisicion);
		tabla1.add(l_valorImporte);
		tabla1.add(valorImporte);
		tabla1.add(l_plazo);
		tabla1.add(plazo);
		tabla2.add(l_direccion);
		tabla2.add(direccion);
		tabla2.add(l_tipo);
		tabla2.add(tipo);
		tabla2.add(l_nueva);
		tabla2.add(nueva);
		tabla2.add(l_estadoCivil);
		tabla2.add(estadoCivil);
		tabla2.add(l_regimenBienes);
		tabla2.add(regimenBienes);
		panelControl.add(tabla1);
		panelControl.add(tabla2);

		panelControl.add(Box.createRigidArea(new Dimension(0, 1)));
		panelControl.setPreferredSize(new Dimension((int) (alto * 0.3), (int) (ancho * 0.5)));
		return panelControl;

	}

	public JPanel setPanelNorte(int alto, int ancho, JPanel j1) {
		JPanel panelNorte = new JPanel();
		panelNorte.setLayout(new BorderLayout());
		panelNorte.setPreferredSize(new Dimension((int) (ancho * 0.30), (int) (alto * 0.9)));
		panelNorte.add(j1, BorderLayout.CENTER);
		return panelNorte;

	}

	public JPanel setPanelNorteDatos(int alto, int ancho) {
		JPanel panelNorteDatos = new JPanel();
		panelNorteDatos.setBorder(BorderFactory.createLoweredBevelBorder());  
		panelNorteDatos.setLayout(new BoxLayout(panelNorteDatos, BoxLayout.Y_AXIS));
		panelNorteDatos.setSize(250, 250);
		panelNorteDatos.add(Box.createRigidArea(new Dimension(0, 1)));
		panelNorteDatos.setPreferredSize(new Dimension((int) (ancho * 0.5), (int) (alto * 0.3)));
		
		
		JLabel l_datosPersonales = new JLabel("Datos Personales");
		l_datosPersonales.setForeground(Color.BLACK);
		
		JLabel l_titular = new JLabel("Titular");
		l_titular.setForeground(Color.BLACK);
		JLabel l_ocupacion = new JLabel("Ocupacion");
		l_ocupacion.setForeground(Color.BLACK);
		

		JLabel l_dninie = new JLabel("Dni|nie");
		dninie = new JTextField();
		dninie.setForeground(Color.gray);
		Font f = new Font("Italic", Font.ITALIC, 12);
		dninie.setFont(f);
		dninie.setMaximumSize(new Dimension(250, 20));
		
		JLabel l_apellido1 = new JLabel("Primer Apellido: ");
		apellido1 = new JTextField();
		apellido1.setForeground(Color.gray);
		apellido1.setFont(f);
		apellido1.setMaximumSize(new Dimension(250, 20));
		
		JLabel l_apellido2 = new JLabel("Segundo Apellido: ");
		apellido2  = new JTextField();
		apellido2 .setForeground(Color.gray);
		apellido2 .setFont(f);
		apellido2 .setMaximumSize(new Dimension(250, 20));
		
		JLabel l_nombre = new JLabel("Nombre: ");
		nombre  = new JTextField();
		nombre .setForeground(Color.gray);
		nombre .setFont(f);
		nombre .setMaximumSize(new Dimension(250, 20));
		
		JLabel l_fechanacimiento = new JLabel("Fecha de Nacimiento: ");
		fechaNacimiento  = new JTextField();
		fechaNacimiento .setForeground(Color.gray);
		fechaNacimiento .setFont(f);
		fechaNacimiento .setMaximumSize(new Dimension(250, 20));
		
		
		JLabel l_fijo = new JLabel("Fijo");
		l_fijo.setForeground(Color.black);
		chb_fijo = new JCheckBox();
		
		JLabel l_temporal = new JLabel("Temporal");
		l_temporal.setForeground(Color.black);
		chb_temporal = new JCheckBox();
		JLabel l_autonomo = new JLabel("Autonomo");
		l_autonomo.setForeground(Color.black);
		chb_autonomo = new JCheckBox();
		JLabel l_otros = new JLabel("Otros");
		l_otros.setForeground(Color.black);
		chb_otros = new JCheckBox();

		JLabel l_profesion = new JLabel("Profesion: ");
		profesion  = new JTextField();
		profesion .setForeground(Color.gray);
		profesion .setFont(f);
		profesion .setMaximumSize(new Dimension(250, 20));
		
		JLabel l_domicilio= new JLabel("Domicilio: ");
		domicilio  = new JTextField();
		domicilio .setForeground(Color.gray);
		domicilio .setFont(f);
		domicilio .setMaximumSize(new Dimension(250, 20));
		
		JLabel l_poblacion= new JLabel("Poblacion: ");
		poblacion  = new JTextField();
		poblacion .setForeground(Color.gray);
		poblacion .setFont(f);
		poblacion .setMaximumSize(new Dimension(250, 20));
		
		JLabel l_codigopostal= new JLabel("Codigo Postal: ");
		codigoPostal  = new JTextField();
		codigoPostal .setForeground(Color.gray);
		codigoPostal .setFont(f);
		codigoPostal .setMaximumSize(new Dimension(250, 20));
		
		
		
		
        
		panelNorteDatos.add(l_datosPersonales);
		panelNorteDatos.add(l_titular);
		panelNorteDatos.add(l_dninie);
		panelNorteDatos.add(dninie);
		panelNorteDatos.add(l_apellido1);
		panelNorteDatos.add(apellido1);
		panelNorteDatos.add(l_apellido2);
		panelNorteDatos.add(apellido2);
		panelNorteDatos.add(l_nombre);
		panelNorteDatos.add(nombre);
		panelNorteDatos.add(l_fechanacimiento);
		panelNorteDatos.add(fechaNacimiento);
		panelNorteDatos.add(l_ocupacion);
		panelNorteDatos.add(l_fijo);
		panelNorteDatos.add(chb_fijo);
		panelNorteDatos.add(l_temporal);
		panelNorteDatos.add(chb_temporal);
		panelNorteDatos.add(l_autonomo);
		panelNorteDatos.add(chb_autonomo);
		panelNorteDatos.add(l_otros);
		panelNorteDatos.add(chb_otros);
		panelNorteDatos.add(l_profesion);
		panelNorteDatos.add(profesion);
		panelNorteDatos.add(l_domicilio);
		panelNorteDatos.add(domicilio);
		panelNorteDatos.add(l_poblacion);
		panelNorteDatos.add(poblacion);
		panelNorteDatos.add(l_codigopostal);
		panelNorteDatos.add(codigoPostal);
		
		
		
		return panelNorteDatos;

	}

}
