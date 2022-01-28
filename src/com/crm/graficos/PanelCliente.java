
package com.crm.graficos;




import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;



import javax.swing.event.MouseInputListener;
import javax.swing.table.DefaultTableModel;


import com.crm.persistencia.ConfigDir;
import com.crm.persistencia.MisConexiones;
import com.crm.pojos.Cliente;
import com.crm.auxiliares.Auxiliar;
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
	public JDialog dialogoinicial;
	public JComboBox<Object> combo;
	public JPasswordField clave;
	public JTextField mialias;
	public JLabel etiqueta;
	DefaultTableModel dtm;
	MisConexiones c;
	PreparedStatement ps;
	JTable tabla;
	JButton botonVer, botonInsertar, botonBorrar, botonActualizar, botonMusica, botonAcceder;
	JTextField tf_dninie, tf_correoe, tf_ciudad, tf_ubicacion, tf_telefono, tf_fecha_alta, tf_notas;
	JCheckBox chb_autorizado, chb_cliente, chb_adjunto, chb_root;
	JMenuItem miCalculadora, copiaBase,miNavegador;
	

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
		dtm.addColumn("Correoe");
		dtm.addColumn("Ciudad");
		dtm.addColumn("Ubicacion");
		dtm.addColumn("Fecha_alta");
		dtm.addColumn("Telefono");
		dtm.addColumn("Autorizado");
		dtm.addColumn("Cliente");
		dtm.addColumn("Adjunto");
		dtm.addColumn("Notas");

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
		panelEste.setPreferredSize(new Dimension((int) (ancho * 0.15), (int) (alto * 0.8)));
		panelEste.add(p1, BorderLayout.NORTH);
		panelEste.add(p2, BorderLayout.CENTER);
		return panelEste;
	}

	public JPanel setPanelEsteDatos(int alto, int ancho) {
		JPanel panelEsteDatos = new JPanel();
		panelEsteDatos.setLayout(new BoxLayout(panelEsteDatos, BoxLayout.Y_AXIS));

		JLabel l_dninie = new JLabel("Dni_nie");
		tf_dninie = new JTextField();
		tf_dninie.setForeground(Color.gray);
		Font f = new Font("Italic", Font.ITALIC, 12);
		tf_dninie.setFont(f);
		tf_dninie.setMaximumSize(new Dimension(250, 20));
		JLabel l_correoe = new JLabel("Correoe");
		tf_correoe = new JTextField();
		tf_correoe.setForeground(Color.gray);
		Font f2 = new Font("Italic", Font.ITALIC, 12);
		tf_correoe.setFont(f2);
		tf_correoe.setMaximumSize(new Dimension(250, 20));
		JLabel l_ciudad = new JLabel("Ciudad");
		tf_ciudad = new JTextField();
		tf_ciudad.setForeground(Color.gray);
		Font f3 = new Font("Italic", Font.ITALIC, 12);
		tf_ciudad.setFont(f3);
		tf_ciudad.setMaximumSize(new Dimension(250, 20));
		JLabel l_ubicacion = new JLabel("Ubicacion");
		tf_ubicacion = new JTextField();
		tf_ubicacion.setForeground(Color.gray);
		Font f4 = new Font("Italic", Font.ITALIC, 12);
		tf_ubicacion.setFont(f4);
		tf_ubicacion.setMaximumSize(new Dimension(250, 20));
		JLabel l_fecha_alta = new JLabel("Fecha_alta");
		tf_fecha_alta = new JTextField();
		tf_fecha_alta.setForeground(Color.gray);
		Font f5 = new Font("Italic", Font.ITALIC, 12);
		tf_fecha_alta.setFont(f5);
		tf_fecha_alta.setMaximumSize(new Dimension(250, 20));
		JLabel l_telefono = new JLabel("Telefono");
		tf_telefono = new JTextField();
		tf_telefono.setForeground(Color.gray);
		Font f6 = new Font("Italic", Font.ITALIC, 12);
		tf_telefono.setFont(f6);
		tf_telefono.setMaximumSize(new Dimension(250, 20));
		JLabel l_notas = new JLabel("Notas");
		tf_notas = new JTextField();
		tf_notas.setForeground(Color.gray);
		Font f7 = new Font("Italic", Font.ITALIC, 12);
		tf_notas.setFont(f7);
		tf_notas.setMaximumSize(new Dimension(250, 20));

		JLabel l_autorizado = new JLabel("Autorizado");
		l_autorizado.setForeground(Color.black);
		chb_autorizado = new JCheckBox();
		JLabel l_cliente = new JLabel("Cliente");
		l_cliente.setForeground(Color.black);
		chb_cliente = new JCheckBox();
		JLabel l_adjunto = new JLabel("Adjunto");
		l_adjunto.setForeground(Color.black);
		chb_adjunto = new JCheckBox();

		panelEsteDatos.add(l_dninie);
		panelEsteDatos.add(tf_dninie);
		panelEsteDatos.add(Box.createRigidArea(new Dimension(0, 10)));
		panelEsteDatos.add(l_correoe);
		panelEsteDatos.add(tf_correoe);
		panelEsteDatos.add(Box.createRigidArea(new Dimension(0, 10)));
		panelEsteDatos.add(l_ciudad);
		panelEsteDatos.add(tf_ciudad);
		panelEsteDatos.add(Box.createRigidArea(new Dimension(0, 10)));
		panelEsteDatos.add(l_ubicacion);
		panelEsteDatos.add(tf_ubicacion);
		panelEsteDatos.add(Box.createRigidArea(new Dimension(0, 10)));
		panelEsteDatos.add(l_fecha_alta);
		panelEsteDatos.add(tf_fecha_alta);
		panelEsteDatos.add(Box.createRigidArea(new Dimension(0, 10)));
		panelEsteDatos.add(l_telefono);
		panelEsteDatos.add(tf_telefono);
		panelEsteDatos.add(Box.createRigidArea(new Dimension(0, 10)));
		panelEsteDatos.add(l_autorizado);
		panelEsteDatos.add(chb_autorizado);
		panelEsteDatos.add(l_cliente);
		panelEsteDatos.add(chb_cliente);
		panelEsteDatos.add(l_adjunto);
		panelEsteDatos.add(chb_adjunto);
		panelEsteDatos.add(l_notas);
		panelEsteDatos.add(tf_notas);
		panelEsteDatos.add(Box.createRigidArea(new Dimension(0, 1)));

		panelEsteDatos.setPreferredSize(new Dimension((int) (ancho * 0.01), (int) (alto * 1.2)));
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
		chb_root.setForeground(Color.BLUE);
		chb_root.addActionListener(new gestorEdicion());
		botonVer = new JButton("   Ver");
		botonVer.setForeground(Color.BLUE);
		panelEsteControl.add(botonVer);
		panelEsteControl.add(Box.createRigidArea(new Dimension(0, 6)));
		botonInsertar = new JButton("Insertar");
		botonInsertar.setForeground(Color.BLUE);
		panelEsteControl.add(botonInsertar);
		panelEsteControl.add(Box.createRigidArea(new Dimension(0, 6)));
		botonBorrar = new JButton(" Borrar");
		botonBorrar.setForeground(Color.BLUE);
		panelEsteControl.add(botonBorrar);
		panelEsteControl.add(Box.createRigidArea(new Dimension(0, 6)));
		botonActualizar = new JButton("Actualizar");
		botonActualizar.setForeground(Color.BLUE);
		panelEsteControl.add(botonActualizar);
		panelEsteControl.add(Box.createRigidArea(new Dimension(0, 6)));
		botonMusica = new JButton("Musica");
		botonMusica.setForeground(Color.BLUE);
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
		try {
			ps = c.getPS(ConfigDir.getInstance().getProperty("query2"));
			
			 ps.setString(1, tf_dninie.getText());
			 ps.setString(2, tf_correoe.getText());
			 ps.setString(3, tf_ciudad.getText());
			 ps.setString(4, tf_ubicacion.getText());
			 ps.setTimestamp(5, Timestamp.valueOf(tf_fecha_alta.getText()));
			 ps.setInt(6, Integer.valueOf(tf_telefono.getText()));
			 ps.setBoolean(7,chb_autorizado.isSelected());
			 ps.setBoolean(8,chb_cliente.isSelected());
			 ps.setBoolean(9,chb_adjunto.isSelected());
			 ps.setString(10,tf_notas.getText());
			 
			 ps.executeUpdate();
			 
					
			 refresh();
			 
			 
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
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
					ps.setString(2, tf_correoe.getText());
					ps.setString(3, tf_ciudad.getText());
					ps.setString(4, tf_ubicacion.getText());
					ps.setTimestamp(5, Timestamp.valueOf(fechaEsp(tf_fecha_alta.getText())));
					ps.setInt(6, Integer.valueOf(tf_telefono.getText()));
					ps.setBoolean(7, chb_autorizado.isSelected());
					ps.setBoolean(7, chb_cliente.isSelected());
					ps.setBoolean(7, chb_adjunto.isSelected());
					ps.setString(8, tf_notas.getText());
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
			tf_correoe.setText(seleccionado.getCorreoe());
			tf_ciudad.setText(seleccionado.getCiudad());
			tf_ubicacion.setText(seleccionado.getUbicacion());
			tf_fecha_alta.setText(fechaEsp(seleccionado.getFecha_alta().toString()));
			tf_telefono.setText("" + seleccionado.getTelefono());
			chb_autorizado.setSelected(seleccionado.isAutorizado());
			chb_cliente.setSelected(seleccionado.isCliente());
			chb_adjunto.setSelected(seleccionado.isAdjunto());
			tf_notas.setText(seleccionado.getNotas());

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

				cliente = new Cliente(rs.getNString("Dni_nie"), rs.getNString("Correoe"), rs.getNString("Ciudad"),
						rs.getNString("Ubicacion"), rs.getNString("notas"), rs.getInt("Telefono"),
						rs.getTimestamp("Fecha_alta"), rs.getBoolean("Autorizado"), rs.getBoolean("Cliente"),
						rs.getBoolean("Adjunto"));
				v = new Vector();
				v.addElement(cliente.getDni_nie());
				v.addElement(cliente.getCorreoe());
				v.addElement(cliente.getCiudad());
				v.addElement(cliente.getUbicacion());
				// listaEmpleado.addElement(fechaEsp(empleado.getFecha_nacimiento()));
				v.addElement(cliente.getFecha_alta());
				v.addElement(cliente.getTelefono());
				v.addElement(formateoBoolean(cliente.isAutorizado()));
				v.addElement(formateoBoolean(cliente.isCliente()));
				v.addElement(formateoBoolean(cliente.isAdjunto()));
				dtm.addRow(v);
				listaClientes.add(cliente);

			}
		} catch (Exception e1) {
			e1.printStackTrace();
			System.out.println(e1.getMessage());
		}
	}

	/*public String fechaIng(String fecha) {
		// TODO Auto-generated method stub
		String fechaIng = "", anno = "", mes = "", dia = "", tiempo = "00:00:00";
		StringTokenizer st = new StringTokenizer(fecha.toString(), "-");
		dia = st.nextToken();
		mes = st.nextToken();
		anno = st.nextToken();
		// no modifico el orden del tiempo pero lo almaceno por si fuese necesario en el
		// futuro
		fechaIng = anno + "-" + mes + "-" + dia + " " + tiempo;

		return fechaIng;

	}*/

	public String fechaEsp(String fechahora) {
		String fechaEsp = "", fecha = "", tiempo = "", dia = "", mes = "",anno = "", hora = "", minuto = "",
				segundo = "";
		StringTokenizer st = new StringTokenizer(fechahora.toString(), " ");
		fecha = st.nextToken();
		tiempo = st.nextToken();
		st = new StringTokenizer(fecha.toString(), "-");
		anno = st.nextToken();
		mes = st.nextToken();
		dia = st.nextToken();
		st = new StringTokenizer(tiempo.toString(), ":");
		hora = st.nextToken();
		minuto = st.nextToken();
		segundo = st.nextToken();
		// no modifico el orden del tiempo pero lo almaceno por si fuese necesario en el
		// futuro
		fechaEsp = dia + "-" + mes + "-" + anno;
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
			tf_correoe.setEditable(true);
			tf_ciudad.setEditable(true);
			tf_ubicacion.setEditable(true);
			tf_telefono.setEditable(true);
			tf_fecha_alta.setEditable(true);
			tf_notas.setEditable(true);
			miCalculadora.setEnabled(true);
			copiaBase.setEnabled(true);
			miNavegador.setEnabled(true);
			chb_autorizado.setEnabled(true);
			chb_cliente.setEnabled(true);
			chb_adjunto.setEnabled(true);
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
			tf_correoe.setEditable(false);
			tf_ciudad.setEditable(false);
			tf_ubicacion.setEditable(false);
			tf_telefono.setEditable(false);
			tf_fecha_alta.setEditable(false);
			tf_notas.setEditable(false);
			miCalculadora.setEnabled(false);
			copiaBase.setEnabled(false);
			miNavegador.setEnabled(false);
			chb_autorizado.setEnabled(false);
			chb_cliente.setEnabled(false);
			chb_adjunto.setEnabled(false);
			chb_root.setEnabled(false);
			
			
			break;

		}

	}


	


	
	
	@Override
	public void addCliente(Cliente cli) throws SQLException, ClassNotFoundException {
		try {
			PreparedStatement ps = new MisConexiones().getConexion()
					.prepareStatement(ConfigDir.getInstance().getProperty("query2"));
			ps.setString(1, cli.getDni_nie());
			ps.setString(2, cli.getCorreoe());
			ps.setString(3, cli.getCiudad());
			ps.setString(4, cli.getUbicacion());
			// ps.setTimestamp(5, new java.sql.Timestamp(new java.util.Date().getTime()));
			ps.setTimestamp(5, new java.sql.Timestamp(new java.util.Date().getTime()));
			ps.setInt(6, cli.getTelefono());
			ps.setBoolean(7, cli.isAutorizado());
			ps.setBoolean(8, cli.isCliente());
			ps.setBoolean(9, cli.isAdjunto());
			ps.setString(10, cli.getNotas());

		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	 * }*/

	@Override
	public List<Cliente> getAllClientes() throws SQLException {
		// TODO Auto-generated method stub
		return null;
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
