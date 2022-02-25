package com.crm.graficos;

import java.awt.BorderLayout;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.MouseInputListener;
import javax.swing.table.DefaultTableModel;

import com.crm.pojos.Cliente;
import com.crm.pojos.Encargo;
import com.crm.auxiliares.WordProcessing;
import com.crm.persistencia.ConfigDir;
import com.crm.persistencia.MisConexiones;

public class PanelEncargos extends JPanel{
	DefaultTableModel dtm;
	JTable tabla;
	Encargo seleccionado;
	Encargo encargo;
	PreparedStatement ps;
	Vector v;
	List<Encargo> listaEncargos;
	JTextField tf_asunto, tf_email, tf_receptor, tf_cliente, tf_dniNie, tf_cliente2, tf_dniNie2, tf_fecha,tf_domicilio;
	JButton b_ver,b_registrar, b_borrar, b_imprimir;
	MisConexiones c;
	public PanelEncargos(int alto, int ancho) {
		setLayout(new BorderLayout());
		add(setPanelNorte(alto,ancho, setPanel1(alto,ancho), setPanel2(alto,ancho), setPanel3(alto,ancho)), BorderLayout.NORTH);
		add(setTabla(alto,ancho), BorderLayout.CENTER);
		
	}
	
	public JScrollPane setTabla(int alto, int ancho) {
	      dtm = new DefaultTableModel();
	      dtm.addColumn("Asunto");
	      dtm.addColumn("Email");
	      dtm.addColumn("Receptor");
	      dtm.addColumn("Cliente");
	      dtm.addColumn("DniNie");
	      dtm.addColumn("Cliente 2");
	     dtm.addColumn("DniNie 2");
	     dtm.addColumn("Fecha");
	     dtm.addColumn("Domicilio");
	     
	      
	      
	      tabla = new JTable(dtm);
	      tabla.addMouseListener(new gestorTabla());
	      JScrollPane p = new JScrollPane(tabla);
	      p.setPreferredSize(new Dimension((int) (ancho * 0.8), (int) (alto * 0.8)));
	      return p;
	}
	
	public JPanel setPanelNorte(int alto, int ancho, JPanel jp1, JPanel jp2,JPanel jp3) {
		JPanel panelNorte = new JPanel();
		panelNorte.setLayout(new BoxLayout(panelNorte, BoxLayout.Y_AXIS));
		panelNorte.setPreferredSize(new Dimension((int) (ancho * 0.1), (int) (alto * 0.2)));
		panelNorte.add(jp1);
		panelNorte.add(jp2);
		panelNorte.add(jp3);

		return panelNorte;
	}
	
	public JPanel setPanel1(int alto, int ancho) {

		JPanel panelDatos = new JPanel();
		panelDatos.setLayout(new BoxLayout(panelDatos, BoxLayout.X_AXIS));
		panelDatos.setPreferredSize(new Dimension((int) (ancho * 0.01), (int) (alto * 0.1)));
		
	
		
		tf_asunto = new JTextField();
		tf_asunto.setForeground(Color.gray);
		Font f = new Font("Italic", Font.ITALIC,12);
		tf_asunto.setFont(f);
		tf_asunto.setMaximumSize(new Dimension(250,20));
		tf_email = new JTextField();
		tf_email.setForeground(Color.gray);
		tf_email.setFont(f);
		tf_email.setMaximumSize(new Dimension(250,20));
		tf_receptor = new JTextField();
		tf_receptor.setForeground(Color.gray);
	
		tf_receptor.setFont(f);
		tf_receptor.setMaximumSize(new Dimension(250,20));
		
		b_ver = new JButton("VER TABLA");
		b_ver.setBackground(Color.PINK);
		b_ver.setMaximumSize(new Dimension(250, 30));
		b_ver.addActionListener(new gestorVisual());
		b_registrar = new JButton("Registrar");
		b_registrar.setBackground(Color.PINK);
		b_registrar.setMaximumSize(new Dimension(250, 30));
		b_registrar.addActionListener(new gestorRegistrar());

		
		panelDatos.add(new JLabel("Asunto: "));
		panelDatos.add(tf_asunto);
		panelDatos.add(Box.createRigidArea(new Dimension(0, 10)));
		panelDatos.add(new JLabel("Email: "));
		panelDatos.add(tf_email);
		panelDatos.add(Box.createRigidArea(new Dimension(0, 10)));
		panelDatos.add(new JLabel("Receptor: "));
		panelDatos.add(tf_receptor);
		panelDatos.add(Box.createRigidArea(new Dimension(0, 10)));
	    panelDatos.add(b_ver);
		panelDatos.add(Box.createRigidArea(new Dimension(0, 10)));
	    panelDatos.add(b_registrar);
		
		return panelDatos;
	}
	
	public JPanel setPanel2(int alto, int ancho) {
		JPanel panel2 = new JPanel();

		panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
		panel2.add(Box.createRigidArea(new Dimension(0, 1)));
		panel2.setPreferredSize(new Dimension((int) (ancho * 0.01), (int) (alto * 0.1)));
		
		tf_cliente = new JTextField();
		tf_cliente.setForeground(Color.gray);
		Font f = new Font("Italic", Font.ITALIC,12);
		tf_cliente.setFont(f);
		tf_cliente.setMaximumSize(new Dimension(250,20));
		tf_dniNie = new JTextField();
		tf_dniNie.setForeground(Color.gray);
		tf_dniNie.setFont(f);
		tf_dniNie.setMaximumSize(new Dimension(250,20));
		tf_cliente2 = new JTextField();
		tf_cliente2.setForeground(Color.gray);
		tf_cliente2.setFont(f);
		tf_cliente2.setMaximumSize(new Dimension(250,20));
		tf_dniNie2 = new JTextField();
		tf_dniNie2.setForeground(Color.gray);
		tf_dniNie2.setFont(f);
		tf_dniNie2.setMaximumSize(new Dimension(250,20));
		
		
		panel2.add(new JLabel("Cliente: "));
		panel2.add(tf_cliente);
		panel2.add(Box.createRigidArea(new Dimension(0, 10)));
		panel2.add(new JLabel("DniNie: "));
		panel2.add(tf_dniNie);
		panel2.add(Box.createRigidArea(new Dimension(0, 10)));
		panel2.add(new JLabel("Cliente 2: "));
		panel2.add(tf_cliente2);
		panel2.add(Box.createRigidArea(new Dimension(0, 10)));
		panel2.add(new JLabel("DniNie 2: "));
		panel2.add(tf_dniNie2);

	
		return panel2;
	}
	
	public JPanel setPanel3(int alto, int ancho) {
		
		JPanel panel3 = new JPanel();
		panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));
		panel3.add(Box.createRigidArea(new Dimension(0, 1)));
		panel3.setPreferredSize(new Dimension((int) (ancho * 0.01), (int) (alto * 0.1)));
		
		tf_fecha = new JTextField();
		tf_fecha.setForeground(Color.gray);
		Font f = new Font("Italic", Font.ITALIC,12);
		tf_fecha.setFont(f);
		tf_fecha.setMaximumSize(new Dimension(250,20));
		b_borrar = new JButton("BORRAR");
		b_borrar.setBackground(Color.PINK);
		b_borrar.setMaximumSize(new Dimension(250, 30));
		tf_domicilio = new JTextField();
		tf_domicilio.setForeground(Color.gray);
		tf_domicilio.setFont(f);
		tf_domicilio.setMaximumSize(new Dimension(250,20));
		b_imprimir = new JButton("IMPRIMIR");
		b_imprimir.setBackground(Color.PINK);
		b_imprimir.setMaximumSize(new Dimension(250, 30));
		
		b_borrar.addActionListener(new gestorBorrar());
		b_imprimir.addActionListener(new gestorImprimir());
		
		panel3.add(new JLabel("Fecha: "));
		panel3.add(tf_fecha);
		panel3.add(Box.createRigidArea(new Dimension(0, 10)));
		panel3.add(b_borrar);
		panel3.add(Box.createRigidArea(new Dimension(0, 10)));
		panel3.add(new JLabel("Domicilio: "));
		panel3.add(tf_domicilio);
		panel3.add(Box.createRigidArea(new Dimension(0, 10)));
		panel3.add(b_imprimir);
		
		return panel3;
		
	}
	
	public class gestorVisual implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		  Refresh();
		}

		}
		
	//si funciona
	public class gestorRegistrar implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try {
				c = new MisConexiones();
				ps = c.getPS(ConfigDir.getInstance().getProperty("query18"));

				ps.setString(1,tf_asunto.getText());
				ps.setString(2,tf_email.getText());
				ps.setString(3,tf_receptor.getText());
				ps.setString(4,tf_cliente.getText());
				ps.setString(5,tf_dniNie.getText());
				ps.setString(6,tf_cliente2.getText());
				ps.setString(7,tf_dniNie2.getText());
				ps.setTimestamp(8,Timestamp.valueOf(tf_fecha.getText()));
				ps.setString(9,tf_domicilio.getText());
				
				
				ps.executeUpdate();
			} catch (InstantiationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
	}
	
	public class gestorBorrar implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try {
				c = new MisConexiones();
				ps = c.getPS(ConfigDir.getInstance().getProperty("query19"));
				ps.setString(1, seleccionado.getDniNie());
				ps.executeUpdate();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Refresh();
		}
		
	}
	
	public class gestorImprimir implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		WordProcessing();
		}
		
	}
	
	

	public class gestorTabla implements MouseInputListener{
 

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			int j = tabla.getSelectedRow();
			
			seleccionado = listaEncargos.get(j);
			tf_asunto.setText(seleccionado.getAsunto());
			tf_email.setText(seleccionado.getEmail());
			tf_receptor.setText(seleccionado.getReceptor());
			tf_cliente.setText(seleccionado.getCliente());
			tf_dniNie.setText(seleccionado.getDniNie());
			tf_cliente2.setText(seleccionado.getCliente2());
			tf_dniNie2.setText(seleccionado.getDniNie2()); 
			tf_fecha.setText(""+seleccionado.getFecha());
			tf_domicilio.setText(seleccionado.getDomicilio());
			
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public void Refresh() {
		
		dtm.setRowCount(0);
		Encargo encargo;
		try {
		
			c = new MisConexiones();
			listaEncargos= new ArrayList<Encargo>();
			// listaClientes = new ArrayList<Cliente>();
		ResultSet rs = c.getRS(ConfigDir.getInstance().getProperty("query17"));
			//ResultSet rs = c.getRS(ConfigDir.getInstance().getProperty("query17"));
			while (rs.next()) {

				encargo = new Encargo(rs.getNString("asunto"), rs.getNString("email"), rs.getNString("receptor"), rs.getNString("cliente"), rs.getNString("dniNie"), rs.getNString("cliente2"), 
				rs.getNString("dniNie2"), rs.getNString("domicilio"), rs.getTimestamp("fecha"));
				
				v = new Vector();
				v.addElement(encargo.getAsunto());
				v.addElement(encargo.getEmail());
				v.addElement(encargo.getReceptor());
				v.addElement(encargo.getCliente());
				v.addElement(encargo.getDniNie());
				v.addElement(encargo.getCliente2());
				v.addElement(encargo.getDniNie2());
				v.addElement(encargo.getDomicilio());
				v.addElement(encargo.getFecha());
			
				dtm.addRow(v);
				listaEncargos.add(encargo);

			}
		} catch (Exception e1) {
			e1.printStackTrace();
			System.out.println(e1.getMessage());
		}
	}

	
	public void WordProcessing() {
		
		//tf_asunto.setText(""+seleccionado.getAsunto());
		//tf_email.setText(""+seleccionado.getEmail());
		// tf_receptor.setText(""+seleccionado.getReceptor()); 
		 //tf_cliente.setText(""+seleccionado.getCliente()); 
		 tf_dniNie.setText(""+seleccionado.getDniNie()); 
		 //tf_cliente2.setText(""+seleccionado.getCliente2()); 
		 tf_dniNie2.setText(""+seleccionado.getDniNie2()); 
		 tf_fecha.setText(""+seleccionado.getFecha());
		 tf_domicilio.setText(""+seleccionado.getDomicilio()); 
		 
		 if(seleccionado != null) {
			 File miTemplate = new File("src/com/rrhh/auxiliares/templates/encargo.dotx");
			WordProcessing.createNewDocumentFromTemplate(miTemplate.getAbsolutePath());

			WordProcessing.typeTextAtBookmark("dninieuno", tf_dniNie.getText());
		
			WordProcessing.typeTextAtBookmark("dniniedos", tf_dniNie2.getText());
			WordProcessing.typeTextAtBookmark("fecha", tf_fecha.getText());
			WordProcessing.typeTextAtBookmark("domicilio", tf_domicilio.getText());
			
		 }
		 
	}
	/*public void WordProcessing() {

		
		tf_nombre.setText("" + seleccionado.getNombre());
		tf_apellidos.setText("" + seleccionado.getApellido());
		tf_idDepartamento.setText("" + seleccionado.getId_departamento());
		tf_idPuesto.setText("" + seleccionado.getId_puesto());
		tf_salario.setText("" + seleccionado.getSalario());
		tf_fecha_nacimiento.setText("" + seleccionado.getFecha_nacimiento());
		chb_jefe.setSelected(seleccionado.isJefe());
		
		
		if(seleccionado != null) {
		File miTemplate = new File("src/com/rrhh/auxiliares/templates/informeempleado.dotm");
		WordProcessing.createNewDocumentFromTemplate(miTemplate.getAbsolutePath());
		WordProcessing.typeTextAtBookmark("nombre", tf_nombre.getText());
		WordProcessing.typeTextAtBookmark("apellidos", tf_apellidos.getText());
		WordProcessing.typeTextAtBookmark("fecha", tf_fecha_nacimiento.getText());
		WordProcessing.typeTextAtBookmark("idDep", tf_idDepartamento.getText());
		WordProcessing.typeTextAtBookmark("idPuesto", tf_idPuesto.getText());
		WordProcessing.typeTextAtBookmark("salario", tf_salario.getText());
		WordProcessing.typeTextAtBookmark("jefe", ""+chb_jefe.isSelected());
		if(chb_jefe.isSelected() == true) {
		           WordProcessing.typeTextAtBookmark("jefe", "true");
		}
		
		String nombreEmpleado = tf_nombre.getText();
		CreaCarpetaInformes(nombreEmpleado);
		
		WordProcessing.changeDocumentDirectory(
				System.getProperty("user.home") + "\\documents\\Informes_empleados\\" +nombreEmpleado);
		WordProcessing.saveDocumentAs(nombreEmpleado);
		WordProcessing.exec();
		}
		}*/
	
	/*private void CreaCarpetaInformes(String nombreEmpleado) {
		String fileName = System.getProperty("user.home") + "\\documents\\Informes_empleados\\" +nombreEmpleado ;

		Path path = Paths.get(fileName);

		if (!Files.exists(path)) {
			try {
				Files.createDirectory(path);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("New Directory created !   " + fileName);
		} else {

			System.out.println("Directory already exists");
		}
	}*/
	}

