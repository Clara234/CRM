package com.crm.graficos;

import javax.print.attribute.standard.Media;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.event.MouseInputListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import com.crm.persistencia.ConfigDir;
import com.crm.persistencia.MisConexiones;
import com.crm.pojos.Cliente;
import com.crm.pojos.Empleado;

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
import java.lang.NumberFormatException;
import java.util.Vector;
import java.awt.*;

public class PanelEmpleado<Reproductor> extends JPanel {

	Empleado seleccionado;
	Vector v;
	DefaultTableModel dtm;
	JTable tabla;
	JTextField tf_idDepartamento, tf_idPuesto, tf_nombre, tf_apellido, tf_salario, tf_fecha_nacimiento;
	JCheckBox chb_jefe;
	List<Empleado> listaEmpleados;

	public PanelEmpleado(int ancho, int alto) {
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
		dtm.addColumn("Id_Dep");
		dtm.addColumn("Id_Puesto");
		dtm.addColumn("Nombre");
		dtm.addColumn("Apellido");
		dtm.addColumn("Salario");
		dtm.addColumn("Fecha_nacimiento");
		dtm.addColumn("Jefe");

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

	public JPanel setPanelEsteDatos(int alto, int ancho) {
		JPanel panelEsteDatos = new JPanel();
		panelEsteDatos.setLayout(new BoxLayout(panelEsteDatos, BoxLayout.Y_AXIS));
		JLabel l_idDepartamento = new JLabel("Id departamento");
		tf_idDepartamento = new JTextField();
		tf_idDepartamento.setForeground(Color.gray);
		Font f = new Font("Italic", Font.ITALIC, 12);
		tf_idDepartamento.setFont(f);
		tf_idDepartamento.setMaximumSize(new Dimension(250, 20));
		JLabel l_idPuesto = new JLabel("Idpuesto");
		tf_idPuesto = new JTextField();
		tf_idPuesto.setForeground(Color.gray);
		Font f2 = new Font("Italic", Font.ITALIC, 12);
		tf_idPuesto.setFont(f2);
		tf_idPuesto.setMaximumSize(new Dimension(250, 20));
		JLabel l_nombre = new JLabel("Nombre");
		tf_nombre = new JTextField();
		tf_nombre.setForeground(Color.gray);
		Font f3 = new Font("Italic", Font.ITALIC, 12);
		tf_nombre.setFont(f3);
		tf_nombre.setMaximumSize(new Dimension(250, 20));
		JLabel l_apellido = new JLabel("Apellido");
		tf_apellido = new JTextField();
		tf_apellido.setForeground(Color.gray);
		Font f4 = new Font("Italic", Font.ITALIC, 12);
		tf_apellido.setFont(f4);
		tf_apellido.setMaximumSize(new Dimension(250, 20));
		JLabel l_salario = new JLabel("Salario");
		tf_salario = new JTextField();
		tf_salario.setForeground(Color.gray);
		Font f5 = new Font("Italic", Font.ITALIC, 12);
		tf_salario.setFont(f5);
		tf_salario.setMaximumSize(new Dimension(250, 20));
		JLabel l_fecha_nacimiento = new JLabel("Fecha_nacimiento");
		tf_fecha_nacimiento = new JTextField();
		tf_fecha_nacimiento.setForeground(Color.gray);
		Font f6 = new Font("Italic", Font.ITALIC, 12);
		tf_fecha_nacimiento.setFont(f6);
		tf_fecha_nacimiento.setMaximumSize(new Dimension(250, 20));

		JLabel l_jefe = new JLabel("Jefe");
		l_jefe.setForeground(Color.black);
		chb_jefe = new JCheckBox();
		panelEsteDatos.add(l_idDepartamento);
		panelEsteDatos.add(tf_idDepartamento);
		panelEsteDatos.add(Box.createRigidArea(new Dimension(0, 10)));
		panelEsteDatos.add(l_idPuesto);
		panelEsteDatos.add(tf_idPuesto);
		panelEsteDatos.add(Box.createRigidArea(new Dimension(0, 10)));
		panelEsteDatos.add(l_nombre);
		panelEsteDatos.add(tf_nombre);
		panelEsteDatos.add(Box.createRigidArea(new Dimension(0, 10)));
		panelEsteDatos.add(l_apellido);
		panelEsteDatos.add(tf_apellido);
		panelEsteDatos.add(Box.createRigidArea(new Dimension(0, 10)));
		panelEsteDatos.add(l_salario);
		panelEsteDatos.add(tf_salario);
		panelEsteDatos.add(Box.createRigidArea(new Dimension(0, 10)));
		panelEsteDatos.add(l_fecha_nacimiento);
		panelEsteDatos.add(tf_fecha_nacimiento);
		panelEsteDatos.add(Box.createRigidArea(new Dimension(0, 10)));
		panelEsteDatos.add(l_jefe);
		panelEsteDatos.add(chb_jefe);
		panelEsteDatos.setPreferredSize(new Dimension((int) (ancho * 0.1), (int) (alto * 0.9)));
		// panelEsteDatos.setBackground(Color.red);
		return panelEsteDatos;
	}

	public JPanel setPanelEsteControl(int alto, int ancho) {
		JPanel panelEsteControl = new JPanel();
		panelEsteControl.setLayout(new BoxLayout(panelEsteControl, BoxLayout.Y_AXIS));
		panelEsteControl.add(Box.createRigidArea(new Dimension(0, 10)));
		panelEsteControl.setPreferredSize(new Dimension((int) (alto * 0.01), (int) (ancho * 0.01)));
		JButton botonConexion = new JButton("Ver");
		botonConexion.setForeground(Color.BLUE);
		panelEsteControl.add(botonConexion);
		JButton botonInsertar = new JButton("Insertar");
		botonInsertar.setForeground(Color.BLUE);
		panelEsteControl.add(botonInsertar);
		JButton botonBorrar = new JButton("Borrar");
		botonBorrar.setForeground(Color.BLUE);
		panelEsteControl.add(botonBorrar);
		JButton botonActualizar = new JButton("Actualizar");
		botonActualizar.setForeground(Color.BLUE);
		panelEsteControl.add(botonActualizar);
		JButton botonMusica = new JButton("Activar Musica");
		botonMusica.setForeground(Color.BLUE);
		panelEsteControl.add(botonMusica);

		botonConexion.addActionListener(new gestorVer());
		botonInsertar.addActionListener(new gestorInsertar());
		botonBorrar.addActionListener(new gestorBorrar());
		botonActualizar.addActionListener(new gestorActualizar());
		botonMusica.addActionListener(new gestorMusica());

		// panelEsteControl.add(chb_root);
		// devolvemos el panel de control
		return panelEsteControl;
	}

	public JPanel setPanelEste(int alto, int ancho, JPanel p1, JPanel p2) {
		JPanel panelEste = new JPanel();
		panelEste.setLayout(new BorderLayout());
		panelEste.setPreferredSize(new Dimension((int) (ancho * 0.15), (int) (alto * 0.8)));
		panelEste.add(p1, BorderLayout.NORTH);
		panelEste.add(p2, BorderLayout.CENTER);
		return panelEste;
	}

	public JMenuBar setMenuBar(int alto, int ancho) {
		JMenuBar menuBar = new JMenuBar();
		menuBar.setPreferredSize(new Dimension(ancho, (int) (alto * 0.045)));
		JMenu menu = new JMenu("Accesos Rápidos");
		JMenuItem miCalculadora = new JMenuItem("Calculadora");
		miCalculadora.setForeground(Color.BLUE);
		miCalculadora.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
				ejecutarComando("calc.exe");
				}catch(IOException e1) {
					e1.printStackTrace();
				}
			}



		});
		JMenuItem miNavegador = new JMenuItem("Navegador");
		miNavegador.setForeground(Color.BLUE);
		miNavegador.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try{ejecutarComando("MicrosoftEdge.exe");
				
				}catch(IOException e1) {
					e1.printStackTrace();
				}
				}
			});
			

			

		

		JMenuItem copiaBase = new JMenuItem("Backup");
		copiaBase.setForeground(Color.BLUE);
		copiaBase.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
               try {
				creaBackupTablas();
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

		return menuBar;
	}

	public void creaBackupTablas() throws IOException{
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
				MisConexiones c = new MisConexiones();
				PreparedStatement ps = c.getPS(ConfigDir.getInstance().getProperty("query2"));
				ps.setInt(1, Integer.valueOf(tf_idDepartamento.getText()));
				ps.setInt(2, Integer.valueOf(tf_idPuesto.getText()));
				ps.setString(3, tf_nombre.getText());
				ps.setString(4, tf_apellido.getText());
				ps.setDouble(5, Double.valueOf(tf_salario.getText()));
				System.out.println(tf_fecha_nacimiento.getText());
				System.out.println(fechaIng(tf_fecha_nacimiento.getText()));
				ps.setTimestamp(6, Timestamp.valueOf(fechaIng(tf_fecha_nacimiento.getText())));
				ps.setBoolean(7, chb_jefe.isSelected());
				ps.executeUpdate();
				refresh();
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}

	}

	public class gestorTabla implements MouseInputListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			int j = tabla.getSelectedRow();
			seleccionado = listaEmpleados.get(j);
			tf_idDepartamento.setText("" + seleccionado.getId_departamento());
			tf_idPuesto.setText("" + seleccionado.getId_puesto());
			tf_nombre.setText(seleccionado.getNombre());
			tf_apellido.setText(seleccionado.getApellido());
			tf_salario.setText(String.valueOf(seleccionado.getSalario()));
			tf_fecha_nacimiento.setText(fechaEsp(seleccionado.getFecha_nacimiento().toString()));
			chb_jefe.setSelected(seleccionado.isJefe());
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
					ps.setInt(1, seleccionado.getId());
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

	public void refresh() {
		// TODO Auto-generated method stub
		dtm.setRowCount(0);
		Empleado empleado;
		try {
			MisConexiones c;
			c = new MisConexiones();
			listaEmpleados = new ArrayList<Empleado>();
			ResultSet rs = c.getRS(ConfigDir.getInstance().getProperty("query1"));
			while (rs.next()) {
				empleado = new Empleado(rs.getInt("id"), rs.getNString("nombre"), rs.getNString("apellido"),
						rs.getTimestamp("fecha_nacimiento"), rs.getDouble("salario"), rs.getBoolean("jefe"),
						rs.getInt("idDepartamento"), rs.getInt("idPuesto"));
				v = new Vector();
				v.addElement(empleado.getId_departamento());
				v.addElement(empleado.getId_puesto());
				v.addElement(empleado.getNombre());
				v.addElement(empleado.getApellido());
				v.addElement(empleado.getSalario());
				// listaEmpleado.addElement(fechaEsp(empleado.getFecha_nacimiento()));
				v.addElement(empleado.getFecha_nacimiento());
				v.addElement(formateoBoolean(empleado.isJefe()));
				dtm.addRow(v);
				listaEmpleados.add(empleado);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			System.out.println(e1.getMessage());
		}
	}

	public String fechaIng(String fecha) {
		// TODO Auto-generated method stub
		String fechaIng = "", tiempo = "00:00:00", anno = "", mes = "", dia = "";
		StringTokenizer st = new StringTokenizer(fecha.toString(), "-");
		dia = st.nextToken();
		mes = st.nextToken();
		anno = st.nextToken();
		// no modifico el orden del tiempo pero lo almaceno por si fuese necesario en el
		// futuro
		fechaIng = anno + "-" + mes + "-" + dia + " " + tiempo;
		return fechaIng;

	}

	public String fechaEsp(String fechahora) {
		String fechaEsp = "", fecha = "", tiempo = "", anno = "", mes = "", dia = "", hora = "", minuto = "",
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

	private Object formateoBoolean(boolean jefe) {
		return jefe;

	}

	public void ejecutarComando(String comando) throws IOException {
		String[] comandito = new String[] { comando };
		final Process proceso = Runtime.getRuntime().exec(comandito);
	}

	public void ejecutarComando(String comando1, String comando2) throws IOException {
		String[] comandito = new String[] { comando1, comando2 };
		final Process proceso = Runtime.getRuntime().exec(comandito);
	}

	public class gestorActualizar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				PreparedStatement ps = new MisConexiones().getPS(ConfigDir.getInstance().getProperty("query4"));
				ps.setInt(1, Integer.valueOf(tf_idDepartamento.getText()));
				ps.setInt(2, Integer.valueOf(tf_idPuesto.getText()));
				ps.setString(3, tf_nombre.getText());
				ps.setString(4, tf_apellido.getText());
				ps.setDouble(5, Double.valueOf(tf_salario.getText()));
				ps.setTimestamp(6, Timestamp.valueOf(fechaIng(tf_fecha_nacimiento.getText())));
				ps.setBoolean(7, chb_jefe.isSelected());
				ps.setInt(8, seleccionado.getId());
				ps.executeUpdate();
				refresh();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
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

}
