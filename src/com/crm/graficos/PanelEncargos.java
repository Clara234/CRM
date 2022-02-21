package com.crm.graficos;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

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

public class PanelEncargos extends JPanel{
	DefaultTableModel dtm;
	JTable tabla;
	
	JTextField tf_asunto, tf_email, tf_receptor, tf_cliente, tf_dniNie, tf_cliente2, tf_dniNie2, tf_fecha,tf_domicilio;
	JButton b_registrar, b_borrar, b_imprimir;
	
	public PanelEncargos(int alto, int ancho) {
		setLayout(new BorderLayout());
		add(setTabla(alto,ancho), BorderLayout.CENTER);
		add(setPanelEste(alto,ancho, setPanelDatos(alto,ancho), setPanelBotones(alto,ancho)), BorderLayout.EAST);
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
	
	public JPanel setPanelEste(int alto, int ancho, JPanel jp1, JPanel jp2) {
		JPanel panelEste = new JPanel();
		panelEste.setLayout(new BoxLayout(panelEste, BoxLayout.Y_AXIS));
		panelEste.setPreferredSize(new Dimension((int) (ancho * 0.25), (int) (alto * 0.8)));
		panelEste.add(jp1, BorderLayout.NORTH);
		panelEste.add(jp2, BorderLayout.CENTER);

		return panelEste;
	}
	
	public JPanel setPanelDatos(int alto, int ancho) {

		JPanel panelDatos = new JPanel();
		panelDatos.setLayout(new BoxLayout(panelDatos, BoxLayout.Y_AXIS));
		panelDatos.setPreferredSize(new Dimension((int) (ancho * 0.1), (int) (alto * 1.1)));
		
		/*tf_asunto, tf_email, tf_receptor, tf_cliente, tf_dniNie, tf_cliente2, tf_dniNie2, tf_fecha,tf_domicilio;
	tf_nombre = new JTextField();
		tf_nombre.setForeground(Color.gray);
		Font f3 = new Font("Italic", Font.ITALIC, 12);
		tf_nombre.setFont(f3);
		tf_nombre.setMaximumSize(new Dimension(250, 20));*/
		
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
		tf_cliente = new JTextField();
		tf_cliente.setForeground(Color.gray);
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
		tf_fecha = new JTextField();
		tf_fecha.setForeground(Color.gray);
		tf_fecha.setFont(f);
		tf_fecha.setMaximumSize(new Dimension(250,20));
		tf_domicilio = new JTextField();
		tf_domicilio.setForeground(Color.gray);
		tf_domicilio.setFont(f);
		tf_domicilio.setMaximumSize(new Dimension(250,20));
		
		panelDatos.add(new JLabel("Asunto"));
		panelDatos.add(tf_asunto);
		panelDatos.add(new JLabel("Email"));
		panelDatos.add(tf_email);
		panelDatos.add(new JLabel("Receptor"));
		panelDatos.add(tf_receptor);
		panelDatos.add(new JLabel("Cliente"));
		panelDatos.add(tf_cliente);
		panelDatos.add(new JLabel("DniNie"));
		panelDatos.add(tf_dniNie);
		panelDatos.add(new JLabel("Cliente2"));
		panelDatos.add(tf_cliente2);
		panelDatos.add(new JLabel("DniNie2"));
		panelDatos.add(tf_dniNie2);
		panelDatos.add(new JLabel("Fecha"));
		panelDatos.add(tf_fecha);
		panelDatos.add(new JLabel("Domicilio"));
		panelDatos.add(tf_domicilio);
		
		
		return panelDatos;
	}
	
	public JPanel setPanelBotones(int alto, int ancho) {
		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));
		panelBotones.add(Box.createRigidArea(new Dimension(0, 5)));
		panelBotones.setPreferredSize(new Dimension((int) (alto * 0.01), (int) (ancho * 0.01)));
		
		
		b_registrar = new JButton("Registrar");
		b_registrar.setForeground(Color.blue);
		b_registrar.setMaximumSize(new Dimension(250, 30));
		b_borrar = new JButton("Borrar");
		b_borrar.setForeground(Color.blue);
		b_borrar.setMaximumSize(new Dimension(250, 30));
		b_imprimir = new JButton("Imprimir");
		b_imprimir.setForeground(Color.blue);
		b_imprimir.setMaximumSize(new Dimension(250, 30));
		
		
		panelBotones.add(b_registrar);
		panelBotones.add(b_borrar);
		panelBotones.add(b_imprimir);
		
		b_registrar.addActionListener(new gestorRegistrar());
		b_borrar.addActionListener(new gestorBorrar());
		b_imprimir.addActionListener(new gestorImprimir());
		return panelBotones;
	}
	public class gestorRegistrar implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public class gestorBorrar implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public class gestorImprimir implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		//	WordProcessing();
		}
		
	}
	
	

	public class gestorTabla implements MouseInputListener{
 

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
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

