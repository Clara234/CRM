
package com.crm.graficos;

import javax.sound.sampled.AudioInputStream;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.MouseInputListener;
import javax.swing.table.DefaultTableModel;

import org.pushingpixels.substance.api.SubstanceConstants;
import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import org.pushingpixels.substance.api.shaper.StandardButtonShaper;

import com.crm.persistencia.ConfigDir;
import com.crm.persistencia.MisConexiones;
import com.crm.pojos.Cliente;
import com.crm.auxiliares.Auxiliar;
import com.crm.auxiliares.BotonRedondeado;
import com.crm.auxiliares.DameFecha;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import java.util.Vector;
import java.awt.*;

public class PanelCliente<Reproductor> extends JPanel implements Servicios {

	Cliente seleccionado;
	Cliente cliente;
	Cliente cli;
	Vector v;
	public JDialog dialogoinicial, dialogoUsuario;
	public JComboBox<Object> combo;
	public JPasswordField clave;
	public JTextField mialias;
	public JLabel etiqueta;
	JPanel subPanel;
	DefaultTableModel dtm;
	MisConexiones c;
	PreparedStatement ps;
	private JTabbedPane datosclientes;
	JTable tabla;
	JButton botonVer, botonInsertar, botonBorrar, botonActualizar, botonMusica, botonAcceder, botonFuera, botonAlta;;

	JTextField tf_dninie, tf_nombre, tf_ape1, tf_ape2, tf_fechaNacimiento, tf_profesion, tf_domicilio, tf_poblacion,
			tf_codigoPostal;
	JCheckBox chb_fijo, chb_temporal, chb_autonomo, chb_otros, chb_root;
	JMenuItem miCalculadora, copiaBase, miNavegador;

	List<Cliente> listaClientes;

	public PanelCliente(int ancho, int alto) {
		// disposiciones de los objetos
		setLayout(new BorderLayout());
		add(setTabla(alto, ancho), BorderLayout.CENTER);
		add(setMenuBar(alto, ancho), BorderLayout.NORTH);
		add(setPanelEste(alto, ancho, setPanelEsteDatos(alto, ancho), setPanelEsteControl(ancho, alto)),
				BorderLayout.EAST);
	}

	public JScrollPane setTabla(int alto, int ancho) {
		// objeto previo configurador
		dtm = new DefaultTableModel();
		// todos los datos que tendra

		dtm.addColumn("Dni_nie");
		dtm.addColumn("Nombre");
		dtm.addColumn("Apellido1");
		dtm.addColumn("Apellido2");
		dtm.addColumn("Fecha Nacimiento");
		dtm.addColumn("Fijo");
		dtm.addColumn("Temporal");
		dtm.addColumn("Autonomo");
		dtm.addColumn("Otros");
		dtm.addColumn("Profesion");
		dtm.addColumn("Domicilio");
		dtm.addColumn("Poblacion");
		dtm.addColumn("Codigo Postal");

		// se crea una tabla con la configuracion dtm que hemos creado
		tabla = new JTable(dtm);
		tabla.addMouseListener(new gestorTabla());
		// creamos una panel con scroll al que añadirle la tabla que acabamos de crear
		JScrollPane sp = new JScrollPane(tabla);
		// damos valores al tamaño del JScrollPane
		sp.setPreferredSize(new Dimension((int) (ancho * 0.8), (int) (alto * 0.8)));
		// devolvemos el panel scroll con todo lo que hemos creado dentro
		return sp;
	}

	public JMenuBar setMenuBar(int alto, int ancho) {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Accesos Rápidos");

		miCalculadora = new JMenuItem("Calculadora");
		miCalculadora.setForeground(Color.darkGray);
		miCalculadora.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ejecutarComando("calc.exe");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

		});
		miNavegador = new JMenuItem("Navegador");
		miNavegador.setForeground(Color.darkGray);
		miNavegador.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ejecutarComando("MicrosoftEdge.exe");

				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		copiaBase = new JMenuItem("Backup");
		copiaBase.setForeground(Color.darkGray);
		copiaBase.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					creaBackupTablas();
					// if((proceso!=null)&&((Calendar.DAY_OF_MONTH==7)||(Calendar.DAY_OF_MONTH%7==0)))
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		menu.add(miCalculadora);
		menu.add(miNavegador);
		menu.add(copiaBase);
		menuBar.add(menu);

		setVisible(true);
		return menuBar;
	}

	public JPanel setPanelEste(int alto, int ancho, JPanel p1, JPanel p2) {
		JPanel panelEste = new JPanel();
		panelEste.setLayout(new BorderLayout());
		panelEste.setPreferredSize(new Dimension((int) (ancho * 0.25), (int) (alto * 0.8)));
		panelEste.add(p1, BorderLayout.NORTH);
		panelEste.add(p2, BorderLayout.CENTER);
		return panelEste;
	}

	public JPanel setPanelEsteDatos(int alto, int ancho) {
		JPanel panelEsteDatos = new JPanel();
		panelEsteDatos.setLayout(new BoxLayout(panelEsteDatos, BoxLayout.Y_AXIS));
		panelEsteDatos.setLayout(new BorderLayout());

		JScrollPane datos = new JScrollPane(subPanel);
		subPanel = new JPanel();
		subPanel.setLayout(new BorderLayout());
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.Y_AXIS));

		datos.setViewportView(subPanel);

		datos.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		datos.setMinimumSize(new Dimension(250, 500));
		datos.setVisible(true);

		tf_dninie = new JTextField();
		tf_dninie.setForeground(Color.gray);
		Font f = new Font("Italic", Font.ITALIC, 12);
		tf_dninie.setFont(f);
		tf_dninie.setMaximumSize(new Dimension(250, 20));

		tf_nombre = new JTextField();
		tf_nombre.setForeground(Color.gray);
		tf_nombre.setFont(f);
		tf_nombre.setMaximumSize(new Dimension(250, 20));

		tf_ape1 = new JTextField();
		tf_ape1.setForeground(Color.gray);
		Font f2 = new Font("Italic", Font.ITALIC, 12);
		tf_ape1.setFont(f2);
		tf_ape1.setMaximumSize(new Dimension(250, 20));

		tf_ape2 = new JTextField();
		tf_ape2.setForeground(Color.gray);
		Font f3 = new Font("Italic", Font.ITALIC, 12);
		tf_ape2.setFont(f3);
		tf_ape2.setMaximumSize(new Dimension(250, 20));

		tf_fechaNacimiento = new JTextField();
		tf_fechaNacimiento.setForeground(Color.gray);
		Font f4 = new Font("Italic", Font.ITALIC, 12);
		tf_fechaNacimiento.setFont(f4);
		tf_fechaNacimiento.setMaximumSize(new Dimension(250, 20));

		chb_fijo = new JCheckBox();

		chb_temporal = new JCheckBox();

		chb_autonomo = new JCheckBox();

		chb_otros = new JCheckBox();
		tf_profesion = new JTextField();
		tf_profesion.setForeground(Color.gray);
		tf_profesion.setFont(f2);
		tf_profesion.setMaximumSize(new Dimension(250, 20));
		tf_domicilio = new JTextField();
		tf_domicilio.setForeground(Color.gray);
		tf_domicilio.setFont(f2);
		tf_domicilio.setMaximumSize(new Dimension(250, 20));

		tf_poblacion = new JTextField();
		tf_poblacion.setForeground(Color.gray);
		tf_poblacion.setFont(f2);
		tf_poblacion.setMaximumSize(new Dimension(250, 20));

		tf_codigoPostal = new JTextField();
		tf_codigoPostal.setForeground(Color.gray);
		tf_codigoPostal.setFont(f2);
		tf_codigoPostal.setMaximumSize(new Dimension(250, 20));

		subPanel.add(new JLabel("DniNie: "));
		subPanel.add(tf_dninie);
		subPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		subPanel.add(new JLabel("Nombre :"));
		subPanel.add(tf_nombre);
		subPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		subPanel.add(new JLabel("Apellido 1: "));
		subPanel.add(tf_ape1);
		subPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		subPanel.add(new JLabel("Apellido 2: "));
		subPanel.add(tf_ape2);
		subPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		subPanel.add(new JLabel("Fecha Nacimiento: "));
		subPanel.add(tf_fechaNacimiento);
		subPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		subPanel.add(new JLabel("Fijo: "));
		subPanel.add(chb_fijo);
		subPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		subPanel.add(new JLabel("Temporal: "));
		subPanel.add(chb_temporal);
		subPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		subPanel.add(new JLabel("Autonomo: "));
		subPanel.add(chb_autonomo);
		subPanel.add(new JLabel("Otros: "));
		subPanel.add(chb_otros);
		subPanel.add(new JLabel("Profesion: "));
		subPanel.add(tf_profesion);
		subPanel.add(new JLabel("Domicilio: "));
		subPanel.add(tf_domicilio);
		subPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		subPanel.add(new JLabel("Poblacion: "));
		subPanel.add(tf_poblacion);
		subPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		subPanel.add(new JLabel("Codigo Postal: "));
		subPanel.add(tf_codigoPostal);

		subPanel.setVisible(true);

		panelEsteDatos.add(datos);

		panelEsteDatos.setPreferredSize(new Dimension((int) (ancho * 0.01), (int) (alto * 0.9)));
		// panelEsteDatos.setBackground(Color.red);
		return panelEsteDatos;
	}

	public JPanel setPanelEsteControl(int alto, int ancho) {
		JPanel panelEsteControl = new JPanel();
		// bajarlooo
		panelEsteControl.setLayout(new BoxLayout(panelEsteControl, BoxLayout.Y_AXIS));
		panelEsteControl.add(Box.createRigidArea(new Dimension(0, 1)));
		panelEsteControl.setPreferredSize(new Dimension((int) (alto * 0.01), (int) (ancho * 0.01)));

		chb_root = new JCheckBox("InicioSesion");
		chb_root.setForeground(Color.PINK);
		chb_root.addActionListener(new gestorEdicion());
		botonVer = new JButton("Ver");
		botonVer.setBackground(Color.PINK);
		
		botonVer.setMaximumSize(new Dimension(250, 30));
		panelEsteControl.add(botonVer);
		panelEsteControl.add(Box.createRigidArea(new Dimension(0, 6)));
		botonInsertar = new JButton("Insertar");
		botonInsertar.setBackground(Color.PINK);
		botonInsertar.setMaximumSize(new Dimension(250, 30));
		panelEsteControl.add(botonInsertar);
		panelEsteControl.add(Box.createRigidArea(new Dimension(0, 6)));
		botonBorrar = new JButton(" Borrar");
		botonBorrar.setBackground(Color.PINK);
		botonBorrar.setMaximumSize(new Dimension(250, 30));
		panelEsteControl.add(botonBorrar);
		panelEsteControl.add(Box.createRigidArea(new Dimension(0, 6)));
		botonActualizar = new JButton("Actualizar");
		botonActualizar.setBackground(Color.PINK);
		botonActualizar.setMaximumSize(new Dimension(250, 30));
		panelEsteControl.add(botonActualizar);
		panelEsteControl.add(Box.createRigidArea(new Dimension(0, 6)));
		botonMusica = new JButton("Musica");
		botonMusica.setBackground(Color.PINK);
		botonMusica.setMaximumSize(new Dimension(250, 30));
		panelEsteControl.add(botonMusica);
		panelEsteControl.add(Box.createRigidArea(new Dimension(0, 6)));
		panelEsteControl.add(chb_root);
		panelEsteControl.add(Box.createRigidArea(new Dimension(0, 6)));

		botonVer.addActionListener(new gestorVer());
		botonInsertar.addActionListener(new gestorInsertar());
		botonBorrar.addActionListener(new gestorBorrar());
		botonActualizar.addActionListener(new gestorActualizar());
		botonMusica.addActionListener(new gestorMusica());

		// devolvemos el panel de control
		return panelEsteControl;
	}

	public class gestorVer implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// formateamos la tabla para evita que se vean llamadas anteriores a la tabla
			refresh();
		}

	}

	public class gestorInsertar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			try {
				c = new MisConexiones();

				ps = c.getPS(ConfigDir.getInstance().getProperty("query2"));

				ps.setString(1, tf_dninie.getText());
				ps.setString(2, tf_nombre.getText());
				ps.setString(3, tf_ape1.getText());
				ps.setString(4, tf_ape2.getText());
				ps.setTimestamp(5, Timestamp.valueOf(tf_fechaNacimiento.getText()));
				ps.setBoolean(6, chb_fijo.isSelected());
				ps.setBoolean(7, chb_temporal.isSelected());
				ps.setBoolean(8, chb_autonomo.isSelected());
				ps.setString(9, chb_otros.getText());
				ps.setString(10, tf_profesion.getText());
				ps.setString(11, tf_domicilio.getText());
				ps.setString(12, tf_poblacion.getText());
				ps.setString(13, tf_codigoPostal.getText());

				ps.executeUpdate();

				refresh();

			} catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e1) {
				System.out.println("Error al insertar cliente");
				e1.printStackTrace();
			}

		}

	}

	public class gestorBorrar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			MisConexiones c1 = null;
			String box2;

			int resp = JOptionPane.showConfirmDialog(null, "Usted eliminará a este usuario" + "¿Esta seguro?", // <- EL
																												// MENSAJE
					"Alerta!"/* <- El título de la ventana */, JOptionPane.YES_NO_OPTION/* Las opciones (si o no) */,
					JOptionPane.WARNING_MESSAGE/* El tipo de ventana, en este caso WARNING */);
			// Si la respuesta es sí(YES_OPTION)
			if (resp == JOptionPane.YES_OPTION) {

				try {
					c1 = new MisConexiones();
				} catch (InstantiationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				PreparedStatement ps = null;
				try {
					ps = c1.getPS(ConfigDir.getInstance().getProperty("query3"));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {

					ps.setString(1, seleccionado.getDni_nie());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					ps.executeUpdate();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				refresh();

			} // El valor de box2 sera 1
				// Si la respuesta es no (NO_OPTION)
			if (resp == JOptionPane.NO_OPTION) {
				box2 = "0";
			} // El valor de box2 sera o
		}

	}

	public class gestorActualizar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			MisConexiones c2 = null;
			String box2;
			int resp = JOptionPane.showConfirmDialog(null, "" + "¿Esta seguro?", // <- EL
					// MENSAJE
					"Alerta!"/* <- El título de la ventana */, JOptionPane.YES_NO_OPTION/* Las opciones (si o no) */,
					JOptionPane.WARNING_MESSAGE/* El tipo de ventana, en este caso WARNING */);
			if (resp == JOptionPane.YES_OPTION) {
				try {
					c2 = new MisConexiones();
				} catch (InstantiationException e1) {

				} catch (IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				PreparedStatement ps = null;

				try {
					ps = c2.getPS(ConfigDir.getInstance().getProperty("query4"));

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					ps.setString(1, tf_dninie.getText());
					ps.setString(2, tf_nombre.getText());
					ps.setString(3, tf_ape1.getText());
					ps.setString(4, tf_ape2.getText());
					ps.setTimestamp(5, Timestamp.valueOf(tf_fechaNacimiento.getText()));
					ps.setBoolean(6, chb_fijo.isSelected());
					ps.setBoolean(7, chb_temporal.isSelected());
					ps.setBoolean(8, chb_autonomo.isSelected());
					ps.setString(9, chb_otros.getText());
					ps.setString(10, tf_profesion.getText());
					ps.setString(11, tf_domicilio.getText());
					ps.setString(12, tf_poblacion.getText());
					ps.setString(13, tf_codigoPostal.getText());

				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				try {
					ps.executeUpdate();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				refresh();

			}
		}
	}

	public class gestorMusica implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			AudioInputStream audioInputStream;
			Clip clip;
			try {

				audioInputStream = AudioSystem.getAudioInputStream(new File("music.wav").getAbsoluteFile());
				clip = AudioSystem.getClip();
				clip.open(audioInputStream);

				if (clip.isRunning()) {
					clip.close();
				} else {
					clip.start();

				}

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}

	public class gestorTabla implements MouseInputListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			int j = tabla.getSelectedRow();

			seleccionado = listaClientes.get(j);
			tf_dninie.setText(seleccionado.getDni_nie());
			tf_nombre.setText(seleccionado.getNombre());
			tf_ape1.setText(seleccionado.getApe1());
			tf_ape2.setText(seleccionado.getApe2());
			tf_fechaNacimiento.setText("" + seleccionado.getFechaNacimiento());
			chb_fijo.setSelected(seleccionado.isFijo());
			chb_temporal.setSelected(seleccionado.isTemporal());
			chb_autonomo.setSelected(seleccionado.isAutonomo());
			chb_otros.setSelected(seleccionado.isOtros());
			tf_profesion.setText(seleccionado.getProfesion());
			tf_domicilio.setText(seleccionado.getDomicilio());
			tf_poblacion.setText(seleccionado.getPoblacion());
			tf_codigoPostal.setText(seleccionado.getCodigoPostal());

		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mouseDragged(MouseEvent e) {
		}

		@Override
		public void mouseMoved(MouseEvent e) {
		}
	}

	public void refresh() {
		// TODO Auto-generated method stub
		dtm.setRowCount(0);
		Cliente cliente;
		try {
			MisConexiones c;
			c = new MisConexiones();
			listaClientes = new ArrayList<Cliente>();
			// listaClientes = new ArrayList<Cliente>();
			ResultSet rs = c.getRS(ConfigDir.getInstance().getProperty("query1"));
			while (rs.next()) {

				cliente = new Cliente(rs.getString("dni_nie"), rs.getString("nombre"), rs.getString("apellido1"),
						rs.getString("apellido2"), rs.getTimestamp("fecha_nacimiento"), rs.getBoolean("fijo"),
						rs.getBoolean("temporal"), rs.getBoolean("autonomo"), rs.getBoolean("otros"),
						rs.getString("profesion"), rs.getString("domicilio"), rs.getString("poblacion"),
						rs.getString("codigoPostal"));
				v = new Vector();
				v.addElement(cliente.getDni_nie());
				v.addElement(cliente.getNombre());
				v.addElement(cliente.getApe1());
				v.addElement(cliente.getApe2());
				v.addElement(cliente.getFechaNacimiento());
				// listaEmpleado.addElement(fechaEsp(empleado.getFecha_nacimiento()));
				v.addElement(formateoBoolean(cliente.isFijo()));
				v.addElement(formateoBoolean(cliente.isTemporal()));
				v.addElement(formateoBoolean(cliente.isAutonomo()));
				v.addElement(formateoBoolean(cliente.isOtros()));
				v.addElement(cliente.getProfesion());
				v.addElement(cliente.getDomicilio());
				v.addElement(cliente.getPoblacion());
				v.addElement(cliente.getCodigoPostal());
				dtm.addRow(v);
				listaClientes.add(cliente);

			}
		} catch (Exception e1) {
			e1.printStackTrace();
			System.out.println(e1.getMessage());
		}
	}

	/*
	 * public String fechaIng(String fecha) { // TODO Auto-generated method stub
	 * String fechaIng = "", anno = "", mes = "", dia = "", tiempo = "00:00:00";
	 * StringTokenizer st = new StringTokenizer(fecha.toString(), "-"); dia =
	 * st.nextToken(); mes = st.nextToken(); anno = st.nextToken(); // no modifico
	 * el orden del tiempo pero lo almaceno por si fuese necesario en el // futuro
	 * fechaIng = anno + "-" + mes + "-" + dia + " " + tiempo;
	 * 
	 * return fechaIng;
	 * 
	 * }
	 */

	public String fechaEsp(String fechaE) {
		String fechaEsp = "", fecha = "", dia = "", mes = "", anno = "";

		StringTokenizer st = new StringTokenizer(fechaE, " ");
		fecha = st.nextToken();
		st = new StringTokenizer(fecha.toString(), "-");
		anno = st.nextToken();
		mes = st.nextToken();
		dia = st.nextToken();

		// no modifico el orden del tiempo pero lo almaceno por si fuese necesario en el
		// futuro
		fechaEsp = dia + "/" + mes + "/" + anno;
		return fechaEsp;
	}

	public void creaBackupTablas() throws IOException {
		File fbackup = new File("ejercicioregiones.sql");

		String[] command = new String[] { "cmd.exe", "/c",
				"mysqldump.exe --quick --lock-tables  --user=root  --password=root   ejercicioregiones" };

		final Process proceso = Runtime.getRuntime().exec(command);
		if (proceso != null) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						try (BufferedReader reader = new BufferedReader(
								new InputStreamReader(new DataInputStream(proceso.getInputStream())));
								BufferedWriter writer = new BufferedWriter(new FileWriter(fbackup))) {
							String line;
							while ((line = reader.readLine()) != null) {
								writer.write(line + "\n");
								writer.newLine();
							}

						}

					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}

	}

	private Object formateoBoolean(boolean autorizado) {
		// TODO Auto-generated method stub
		return null;
	}

	public void ejecutarComando(String comando) throws IOException {
		String[] comandito = new String[] { comando };
		final Process proceso = Runtime.getRuntime().exec(comandito);
	}

	public void ejecutarComando(String comando1, String comando2) throws IOException {
		String[] comandito = new String[] { comando1, comando2 };
		final Process proceso = Runtime.getRuntime().exec(comandito);
	}

	public class gestorEdicion implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (chb_root.isSelected()) {

				dialogoinicial = new JDialog(new JFrame(), true);
				dialogoinicial.setResizable(false);
				dialogoinicial.setBackground(new Color(206, 238, 244));
				dialogoinicial.setForeground(new Color(206, 237, 244));
				dialogoinicial.setSize(150, 150);
				dialogoinicial.setMinimumSize(new Dimension(200, 300));
				dialogoinicial.setLocation(220, 220);
				dialogoinicial.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				mialias = new JTextField(16);
				clave = new JPasswordField(16);
				dialogoinicial.setTitle("VALIDACION USUARIOS");
				combo = new JComboBox<Object>();
				combo.addItem(dameObjeto("1"));
				combo.addItem(dameObjeto("2"));
				combo.addItem(dameObjeto("3"));
				JLabel l_copyrigth = new JLabel("Copyright by Clara");
				Font f = new Font("Arial", Font.BOLD, 12);
				l_copyrigth.setFont(f);
				botonAcceder = new JButton("Acceder");

				botonAcceder.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {

						try {

							PreparedStatement ps = new MisConexiones()
									.getPS(ConfigDir.getInstance().getProperty("validacionUsu"));
							ps.setString(1, mialias.getText());
							ps.setString(2, Auxiliar.dameContrasenna(clave.getPassword()));
							ps.setInt(3, combo.getSelectedIndex() + 1);
							ResultSet rs = ps.executeQuery();
							if (rs.next()) {
								editHabCosas(rs.getInt("grupo"));
								dialogoinicial.dispose();
							} else {
								JOptionPane.showMessageDialog(null, "Ese usuario no existe");
							}

						} catch (Exception e1) {
							e1.printStackTrace();
						}

					}

				});

				botonAcceder.setForeground(Color.pink);

				dialogoinicial.add(mialias);
				dialogoinicial.add(clave);
				dialogoinicial.add(combo);
				dialogoinicial.add(botonAcceder);
				dialogoinicial.add(l_copyrigth);

				JPanel panelentrada = new JPanel();
				panelentrada.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));
				panelentrada.add(new JLabel("Introduzca su  alias"));
				panelentrada.add(mialias);
				panelentrada.add(new JLabel("Introduzca su contraseña"));
				panelentrada.add(clave);
				panelentrada.add(new JLabel("GRUPOS"));
				panelentrada.add(combo);
				// panelentrada.add(new JLabel(" copyright by Clara"));
				panelentrada.add(new JLabel(""));
				panelentrada.add(botonAcceder);
				panelentrada.add(l_copyrigth);

				panelentrada.setSize(230, 230);
				panelentrada.setBackground(new Color(209, 222, 244));
				panelentrada.setForeground(new Color(209, 222, 224));
				dialogoinicial.add(panelentrada);
				dialogoinicial.setVisible(true);
				panelentrada.setVisible(true);

			} else {
				chb_root.setSelected(false);
				editHabCosas(0);
			}
			

		}

	}

	private Object dameObjeto(String item) {
		// TODO Auto-generated method stub
		return new Object() {
			public String toString() {
				return item;
			}
		};
	}

	public void editHabCosas(int grado) {
		switch (grado) {
		case 1:
			editHabCosas(0);
			refresh();
			int n = JOptionPane.showConfirmDialog(new JDialog(), "Desea dar de alta alguno?", "Usuarios",
					JOptionPane.YES_NO_OPTION);
			if (n == JOptionPane.YES_OPTION) {
				// veamos = new MiPractica();
				dialogoinicial.dispose();
				altaUsuario();
			}

			else if (n == JOptionPane.NO_OPTION) {
				dialogoinicial.dispose();
			}
			System.out.println("Administrador");
			botonVer.setEnabled(true);
			botonInsertar.setEnabled(true);
			botonBorrar.setEnabled(true);
			botonActualizar.setEnabled(true);
			botonMusica.setEnabled(true);
			tf_dninie.setEditable(true);
			tf_ape1.setEditable(true);
			tf_ape2.setEditable(true);
			tf_nombre.setEditable(true);
			tf_fechaNacimiento.setEditable(true);
			tf_profesion.setEditable(true);
			tf_poblacion.setEditable(true);
			tf_domicilio.setEditable(true);
			tf_codigoPostal.setEditable(true);
			miCalculadora.setEnabled(true);
			copiaBase.setEnabled(true);
			miNavegador.setEnabled(true);
			chb_fijo.setEnabled(true);
			chb_temporal.setEnabled(true);
			chb_autonomo.setEnabled(true);
			chb_otros.setEnabled(true);
			chb_root.setEnabled(true);

			break;
		case 2:
			editHabCosas(0);
			refresh();

			System.out.println("Avanzado");
			botonVer.setEnabled(true);
			botonInsertar.setEnabled(true);

			break;
		case 3:
			editHabCosas(0);
			refresh();
			botonVer.setEnabled(true);
			System.out.println("Usuario basico");
			break;
		case 0:
			dtm.setRowCount(0);
			botonVer.setEnabled(false);
			botonInsertar.setEnabled(false);
			botonBorrar.setEnabled(false);
			botonActualizar.setEnabled(false);
			botonMusica.setEnabled(false);
			tf_dninie.setEditable(false);
			tf_ape1.setEditable(false);
			tf_ape2.setEditable(false);
			tf_nombre.setEditable(false);
			tf_fechaNacimiento.setEditable(false);
			tf_profesion.setEditable(false);
			tf_poblacion.setEditable(false);
			tf_domicilio.setEditable(false);
			tf_codigoPostal.setEditable(false);
			miCalculadora.setEnabled(false);
			copiaBase.setEnabled(false);
			miNavegador.setEnabled(false);
			chb_fijo.setEnabled(true);
			chb_temporal.setEnabled(true);
			chb_autonomo.setEnabled(true);
			chb_otros.setEnabled(true);
			chb_root.setEnabled(false);

			break;

		}

	}
	
	 public void altaUsuario() {
			// TODO Auto-generated method stub
			
			dialogoUsuario = new JDialog(new JFrame());
			dialogoUsuario.setResizable(true);
			dialogoUsuario.setBackground(Color.CYAN);
			dialogoUsuario.setForeground(Color.PINK);
			dialogoUsuario.setSize(250, 250);
			dialogoUsuario.setMinimumSize(new Dimension(250, 250));
			dialogoUsuario.setLocation(250, 250);
			dialogoUsuario.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			mialias = new JTextField(16);
			clave = new JPasswordField(16);
			dialogoUsuario.setTitle("ALTA USUARIOS");
			combo = new JComboBox<Object>();
			combo.addItem(dameObjeto("1"));
			combo.addItem(dameObjeto("2"));
			combo.addItem(dameObjeto("3"));
			botonAlta = new JButton("Dar de Alta");
			botonFuera = new JButton("Salir");
		    botonAlta.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					try {
						c = new MisConexiones();
						PreparedStatement ps = c.getPS(ConfigDir.getInstance().getProperty("altaUsu"));
						ps.setString(1, mialias.getText());
						ps.setString(2, Auxiliar.dameContrasenna(clave.getPassword()));
						ps.setInt(3, combo.getSelectedIndex() + 1);
						ps.execute();
						
						refresh();
						 
						
					}catch(Exception e1) {e1.printStackTrace();}
				}
		    	
		    });
		    
		    botonFuera.addActionListener(new ActionListener() {
		    	@Override
		    	public void actionPerformed(ActionEvent e) {
		    		
		    		dialogoUsuario.dispose();
		    	}
		    });
		    
		    
		    dialogoUsuario.add(mialias);
		    dialogoUsuario.add(clave);
		    dialogoUsuario.add(combo);
		    dialogoUsuario.add(botonAlta);
		    dialogoUsuario.add(botonFuera);
		    
		    JPanel panel = new JPanel();
		    panel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));

		    panel.add(new JLabel("Introduzca su  alias"));
		    panel.add(mialias);
		    panel.add(new JLabel("Introduzca su contraseña"));
			panel.add(clave);
			panel.add(new JLabel(" GRUPOS"));
			panel.add(combo);
			panel.add(new JLabel("  copyright by Clara"));

			panel.add(botonAlta);
			panel.add(botonFuera);

			panel.setSize(250, 250);
			panel.setBackground(new Color(209, 222, 244));
			dialogoUsuario.add(panel);
			dialogoUsuario.setVisible(true);
			panel.setVisible(true);
		    
		    
		    
		}

	
	/*
	 * public void addCliente(Cliente cli) throws SQLException,
	 * ClassNotFoundException try{ PreparedStatement ps = new
	 * MisConexiones().dameConexion().prepareStatement(ConfigDir.getInstance().
	 * getProperty("query2") ps.setString(1,cli.getDninie());
	 * ps.setString(2,cli.getCorreoe()); ps.setString(3, cli.getCiudad());
	 * ps.setString(4, cli.getUbicacion()); ps.setTimestamp(5,new
	 * java.sql.Timestamp(new java.util.Date().getTime())); ps.setInt(6,
	 * cli.getTelefono()); ps.setBoolean(7, cli.isAutorizado));
	 * ps.setBoolean(8,cli.isACliente)); ps.setBoolean(9, cli.isAdjunto));
	 * ps.setString(10, cli.getNotas());
	 * 
	 * 
	 * } public class GestorAdd implements ActionListener{ public void
	 * actionPerformed(ActionEvent e) { Cliente cli = new Cliente(); cli.setId(); }
	 * }
	 */

	@Override
	public List<Cliente> getAllClientes() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	/*
	 * @Override public Cliente getbyDni_nie(String dni_nie) throws SQLException {
	 * // TODO Auto-generated method stub return null; }
	 * 
	 * @Override public Cliente updateCliente(Cliente cliente) throws SQLException {
	 * // TODO Auto-generated method stub return null; }
	 * 
	 * @Override public void deleteCliente(String dni_nie) throws SQLException { //
	 * TODO Auto-generated method stub
	 * 
	 * }
	 */

	@Override
	public void addCliente(Cliente cliente) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public Cliente getbyDni_nie(String dni_nie) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cliente updateCliente(Cliente cliente) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCliente(String dni_nie) throws SQLException {
		// TODO Auto-generated method stub

	}
}
