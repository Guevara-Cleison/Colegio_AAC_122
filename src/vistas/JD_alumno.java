package vistas;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.awt.Color;

import javax.swing.JDesktopPane;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import controlador.AlumnoControlador;
import modelo.Alumno;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import util.MySQLConexion;

import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseListener;
import java.io.File;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

public class JD_alumno extends JDialog implements MouseListener, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JDesktopPane desktopPane;
	private JLabel lblCodigoDeAlumno;
	private JLabel lblNombres;
	private JLabel lblApellidos;
	private JLabel lblDni;
	private JLabel lblCelular;
	private JLabel lblEstado;
	private JTextField txtCodAlumno;
	private JTextField txtNombres;
	private JTextField txtApellidos;
	private JTextField txtDni;
	private JTextField txtCelular;

	private JButton btnConsultar;
	private JButton btnNuevo;
	private JButton btnAdicionar;
	private JButton btnModificar;
	private JButton btnCancelar;
	private JButton btnEliminar;
	private JTable tblAlumnos;
	private JScrollPane scrollPane;
	private JLabel lblCan;
	private JLabel lblRegistros;
	//
	public static DefaultTableModel modelo;
	public static JComboBox<String> cboEstado;
	//
	public static AlumnoControlador alu = new AlumnoControlador();
	private JLabel lblEdad;
	private JTextField txtEdad;
	private JButton btnSalir;
	private JButton btnExportar_Alumnos_pdf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JD_alumno dialog = new JD_alumno();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JD_alumno() {
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(JD_alumno.class.getResource("/imagenes/aac.jpg")));
		setResizable(false);
		setTitle("MANTENIMIENTO| ALUMNO");
		setBounds(100, 100, 1000, 700);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(240, 255, 240));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		desktopPane = new JDesktopPane();
		desktopPane.setToolTipText("");
		desktopPane.setBackground(new Color(240, 255, 240));
		desktopPane.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "ALUMNO", TitledBorder.LEFT,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		desktopPane.setBounds(10, 11, 670, 290);
		contentPanel.add(desktopPane);
		desktopPane.setLayout(null);

		lblCodigoDeAlumno = new JLabel("Codigo de Alumno :");
		lblCodigoDeAlumno.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCodigoDeAlumno.setBounds(10, 23, 135, 25);
		desktopPane.add(lblCodigoDeAlumno);

		lblNombres = new JLabel("Nombres :");
		lblNombres.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNombres.setBounds(10, 59, 135, 25);
		desktopPane.add(lblNombres);

		lblApellidos = new JLabel("Apellidos :");
		lblApellidos.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblApellidos.setBounds(10, 95, 135, 25);
		desktopPane.add(lblApellidos);

		lblDni = new JLabel("D.N.I :");
		lblDni.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDni.setBounds(10, 131, 135, 25);
		desktopPane.add(lblDni);

		lblCelular = new JLabel("Celular :");
		lblCelular.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCelular.setBounds(10, 203, 135, 25);
		desktopPane.add(lblCelular);

		lblEstado = new JLabel("Estado :");
		lblEstado.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEstado.setBounds(10, 239, 135, 25);
		desktopPane.add(lblEstado);

		txtCodAlumno = new JTextField();
		txtCodAlumno.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtCodAlumno.setBounds(155, 24, 200, 25);
		desktopPane.add(txtCodAlumno);
		txtCodAlumno.setColumns(10);

		txtNombres = new JTextField();
		txtNombres.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtNombres.setBounds(155, 60, 200, 25);
		desktopPane.add(txtNombres);
		txtNombres.setColumns(10);

		txtApellidos = new JTextField();
		txtApellidos.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtApellidos.setBounds(155, 96, 200, 25);
		desktopPane.add(txtApellidos);
		txtApellidos.setColumns(10);

		txtDni = new JTextField();
		txtDni.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtDni.setBounds(155, 132, 200, 25);
		desktopPane.add(txtDni);
		txtDni.setColumns(10);

		txtCelular = new JTextField();
		txtCelular.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtCelular.setBounds(155, 204, 200, 25);
		desktopPane.add(txtCelular);
		txtCelular.setColumns(10);

		cboEstado = new JComboBox<String>();
		cboEstado.setModel(new DefaultComboBoxModel(new String[] { "registrado", "matriculado", "retirado" }));
		cboEstado.setFont(new Font("Tahoma", Font.BOLD, 13));
		cboEstado.setBounds(155, 240, 200, 25);
		desktopPane.add(cboEstado);

		btnConsultar = new JButton("CONSULTAR");
		btnConsultar.setIcon(new ImageIcon(JD_alumno.class.getResource("/imagenes/buscar.png")));
		btnConsultar.addActionListener(this);
		btnConsultar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnConsultar.setBounds(365, 23, 205, 42);
		desktopPane.add(btnConsultar);

		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setIcon(new ImageIcon(JD_alumno.class.getResource("/imagenes/cancelar.png")));
		btnCancelar.addActionListener(this);
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCancelar.setBounds(455, 225, 205, 42);
		desktopPane.add(btnCancelar);

		lblEdad = new JLabel("Edad :");
		lblEdad.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEdad.setBounds(10, 167, 135, 25);
		desktopPane.add(lblEdad);

		txtEdad = new JTextField();
		txtEdad.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtEdad.setBounds(155, 170, 200, 25);
		desktopPane.add(txtEdad);
		txtEdad.setColumns(10);

		btnExportar_Alumnos_pdf = new JButton("");
		btnExportar_Alumnos_pdf.addActionListener(this);
		btnExportar_Alumnos_pdf.setForeground(new Color(0, 0, 0));
		btnExportar_Alumnos_pdf.setBackground(new Color(240, 255, 240));
		btnExportar_Alumnos_pdf.setIcon(new ImageIcon(JD_alumno.class.getResource("/imagenes/pdf.png")));
		btnExportar_Alumnos_pdf.setBounds(610, 164, 50, 50);
		desktopPane.add(btnExportar_Alumnos_pdf);

		btnNuevo = new JButton("NUEVO");
		btnNuevo.setIcon(new ImageIcon(JD_alumno.class.getResource("/imagenes/nuevo.png")));
		btnNuevo.addActionListener(this);
		btnNuevo.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNuevo.setBounds(734, 32, 205, 42);
		contentPanel.add(btnNuevo);

		btnAdicionar = new JButton("ADICIONAR");
		btnAdicionar.setIcon(new ImageIcon(JD_alumno.class.getResource("/imagenes/adicionar.png")));
		btnAdicionar.addActionListener(this);
		btnAdicionar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAdicionar.setBounds(734, 85, 205, 42);
		contentPanel.add(btnAdicionar);

		btnModificar = new JButton("MODIFICAR");
		btnModificar.setIcon(new ImageIcon(JD_alumno.class.getResource("/imagenes/editar.png")));
		btnModificar.addActionListener(this);
		btnModificar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnModificar.setBounds(734, 138, 205, 42);
		contentPanel.add(btnModificar);

		btnEliminar = new JButton("ELIMINAR");
		btnEliminar.setIcon(new ImageIcon(JD_alumno.class.getResource("/imagenes/eliminar.png")));
		btnEliminar.addActionListener(this);
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnEliminar.setBounds(734, 191, 205, 42);
		contentPanel.add(btnEliminar);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 312, 974, 307);
		contentPanel.add(scrollPane);

		tblAlumnos = new JTable();
		tblAlumnos.addMouseListener(this);
		scrollPane.setViewportView(tblAlumnos);

		lblCan = new JLabel("");
		lblCan.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCan.setForeground(new Color(30, 144, 255));
		lblCan.setBackground(new Color(0, 0, 0));
		lblCan.setBounds(922, 629, 62, 31);
		contentPanel.add(lblCan);

		lblRegistros = new JLabel("REGISTROS :");
		lblRegistros.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRegistros.setBounds(801, 629, 111, 31);
		contentPanel.add(lblRegistros);

		btnSalir = new JButton("SALIR");
		btnSalir.setIcon(new ImageIcon(JD_alumno.class.getResource("/imagenes/salir.png")));
		btnSalir.addActionListener(this);
		btnSalir.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSalir.setBounds(734, 244, 205, 42);
		contentPanel.add(btnSalir);

		//
		EncabezadoTablaAlumnos();
		cargarDatos();
		desHabilitarCajas();
		hade(true, false);

	}

	// *********TABLA MODELO****************************************
	public void EncabezadoTablaAlumnos() {
		modelo = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int colum) {
				return false;
			}
		};
		modelo.addColumn("Codigo");
		modelo.addColumn("Nombres");
		modelo.addColumn("Apellidos");
		modelo.addColumn("DNI");
		modelo.addColumn("Edad");
		modelo.addColumn("Celular");
		modelo.addColumn("Estado");
		tblAlumnos.setModel(modelo);
		// para_que_no_se_muevan_los_encabezados
		tblAlumnos.getTableHeader().setReorderingAllowed(false);
		tblAlumnos.getTableHeader().setResizingAllowed(false);
	}

	public void cargarDatos() {
		// limpiar_el_modelo
		modelo.setRowCount(0);
		ArrayList<Alumno> listaAlumnos = alu.listado();
		for (Alumno a : listaAlumnos) {
			Object fila[] = { a.getId(), a.getNombres(), a.getApellidos(), a.getDni(), a.getEdad(), a.getCelular(),
					a.getEstado() };
			// enviar_el_objeto_FILA_al_modelo
			modelo.addRow(fila);
			// mostrar_cantidad_de_registros
			lblCan.setText("" + modelo.getRowCount());
		}
	}

	public void mostrarDatos(int fila) {
		if (fila != -1) {
			String id, nom, ape, dni, edad, celular, estado;
			id = modelo.getValueAt(fila, 0).toString();
			nom = modelo.getValueAt(fila, 1).toString();
			ape = modelo.getValueAt(fila, 2).toString();
			dni = modelo.getValueAt(fila, 3).toString();
			edad = modelo.getValueAt(fila, 4).toString();
			celular = modelo.getValueAt(fila, 5).toString();
			estado = modelo.getValueAt(fila, 6).toString();
			// System.out.println(estado_);
			// enviar_datos_a_las_cajas
			txtCodAlumno.setText(id);
			txtNombres.setText(nom);
			txtApellidos.setText(ape);
			txtDni.setText(dni);
			txtEdad.setText(edad);
			txtCelular.setText(celular);
			cboEstado.setSelectedItem(estado);
		}
	}

	public void mouseClicked(MouseEvent arg0) {
	}

	public void mouseEntered(MouseEvent arg0) {
	}

	public void mouseExited(MouseEvent arg0) {
	}

	public void mousePressed(MouseEvent arg0) {
		if (arg0.getSource() == tblAlumnos) {
			mousePressedTblAlumnos(arg0);
		}
	}

	public void mouseReleased(MouseEvent arg0) {
	}

	protected void mousePressedTblAlumnos(MouseEvent arg0) {
		int fila;
		fila = tblAlumnos.getSelectedRow();
		mostrarDatos(fila);
		if (fila != -1) {
			HabilitarCajas();
			hade(false, true);
			btnAdicionar.setEnabled(false);
		}
	}

	// **********************************************************************

	// *******************METODOS UTILES***************************
	public void mensaje(String s) {
		JOptionPane.showMessageDialog(this, s);
	}

	public void HabilitarCajas() {
		txtCodAlumno.setEnabled(false);
		txtNombres.setEnabled(true);
		txtNombres.requestFocus();
		txtApellidos.setEnabled(true);
		txtDni.setEnabled(true);
		txtEdad.setEnabled(true);
		txtCelular.setEnabled(true);
	}

	public void desHabilitarCajas() {
		txtCodAlumno.setEnabled(false);
		txtNombres.setEnabled(false);
		txtApellidos.setEnabled(false);
		txtDni.setEnabled(false);
		txtEdad.setEnabled(false);
		txtCelular.setEnabled(false);
		cboEstado.setEnabled(false);
	}

	public void hade(boolean h, boolean d) {
		btnNuevo.setEnabled(h);
		btnAdicionar.setEnabled(d);
		btnCancelar.setEnabled(d);
		btnEliminar.setEnabled(d);
		btnModificar.setEnabled(d);
		btnConsultar.setEnabled(h);
	}

	void LimpiarCajas() {
		txtCodAlumno.setText("");
		txtNombres.setText("");
		txtApellidos.setText("");
		txtDni.setText("");
		txtEdad.setText("");
		txtCelular.setText("");
		cboEstado.setSelectedIndex(0);
	}

	// **********************************************************************

	// ********************************METODOS GENERALES**********************

	void registrarAlumno() {
		// variables
		// registrar_el_codigo
		String nom, ape, dni, edad, celu, estado;
		int codigo;
		// entrada_de_datos
		codigo = getCodigo();
		nom = getNombres();
		ape = getApellidos();
		dni = getDni();
		edad = getEdad();
		celu = getCelular();
		estado = getEstado();
		// _procesos
		// pasamos_los_datos_de_las_cajas_a_la_clase_alumno
		Alumno a = new Alumno();
		a.setId(codigo);
		a.setNombres(nom);
		a.setApellidos(ape);
		a.setDni(dni);
		a.setEdad(edad);
		a.setCelular(celu);
		a.setEstado(estado);
		// le_pasamos_de_la_clase_alumno_a_el_metodo_controlador
		int ok = alu.registrar(a);

		if (ok == 1) {
			mensaje("Registro EXITOSO!");
			cargarDatos();
			LimpiarCajas();
			hade(true, false);
			desHabilitarCajas();
			lblCan.setText("" + modelo.getRowCount());
		} else {
			mensaje("Error al registrar!!!");
		}
	}

	void actualizarAlumno() {
		Alumno a = new Alumno();
		a.setNombres(getNombres());
		a.setApellidos(getApellidos());
		a.setDni(getDni());
		a.setEdad(getEdad());
		a.setCelular(getCelular());
		a.setId(getCodigo());
		//
		int valor = alu.actualizar(a);
		if (valor == 1) {
			mensaje("Actualizacion EXITOSA!");
			cargarDatos();
			LimpiarCajas();
			hade(true, false);
			desHabilitarCajas();
			lblCan.setText("" + modelo.getRowCount());
		} else {
			mensaje("Error al modificar!!!");
		}
	}

	void eliminarAlumno() {

		int valor = alu.eliminar(getCodigo());
		if (valor == 1 && cboEstado.getSelectedItem() == "registrado") {
			mensaje("Eliminación EXITOSA!");
			cargarDatos();
			LimpiarCajas();
			hade(true, false);
			desHabilitarCajas();
			lblCan.setText("" + modelo.getRowCount());

		} else if (cboEstado.getSelectedItem() == "matriculado") {
			mensaje("no se puede eliminar un alumno Matriculado");
		} else if (cboEstado.getSelectedItem() == "retirado") {
			mensaje("no se puede eliminar un alumno Retirado");
		} else {
			mensaje("Error al eliminar!!!");
		}
	}

	void consultarAlumno() {
		try {
			//
			int buscar = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese codigo de alumno a buscar"));
			//
			ArrayList<Alumno> al = alu.buscarXCodigo(buscar);
			if (al != null) {
				for (Alumno a : al) {
					txtCodAlumno.setText("" + a.getId());
					txtNombres.setText("" + a.getNombres());
					txtApellidos.setText("" + a.getApellidos());
					txtDni.setText("" + a.getDni());
					txtEdad.setText("" + a.getEdad());
					txtCelular.setText("" + a.getCelular());
					cboEstado.setSelectedItem(a.getEstado());
					//
					HabilitarCajas();
					hade(false, true);
					btnAdicionar.setEnabled(false);
					txtNombres.requestFocus();
				}

			} else {
				mensaje("El Codigo ingresado no existe");
				hade(true, false);
				// txtCodAlumno.setText(getCodigoCorrelativo() + "");
				// cboEstado.setSelectedIndex(0);
			}
		} catch (Exception e) {
			mensaje("Error de ingreso");
			LimpiarCajas();
			desHabilitarCajas();
		}
	}

	// **********************************************************************

	// *METODOS_PARA_EXPORTAR************************************************

	void exportarAlumnoPDF() {

		JasperReport report;
		JasperPrint print;
		try {
			report = JasperCompileManager
					.compileReport(new File("").getAbsolutePath() + "/src/reportes/reporteAlumnos.jrxml");
			print = JasperFillManager.fillReport(report, null, MySQLConexion.conectar());
			JasperViewer vista = new JasperViewer(print, false);
			vista.setZoomRatio((float) 0.8);
			vista.setTitle("Reporte de Alumno");
			vista.setLocationRelativeTo(null);
			vista.setResizable(false);
			vista.setAlwaysOnTop(true);
			vista.setVisible(true);
			this.dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// **********************************************************************

	// ***************METODOS DE TEXTBOX***********************

	private int getCodigoCorrelativo() {
		// tiene_que_dar_correlativo
		ArrayList<Alumno> listaAlumno = alu.listado();
		if (listaAlumno.size() == 0) {
			return 20201001;
		} else {
			int correlativo;
			correlativo = (listaAlumno.get(listaAlumno.size() - 1).getId()) + 1;
			return correlativo;
		}
	}

	private int getCodigo() {
		int codigo = -1;
		try {
			codigo = Integer.parseInt(txtCodAlumno.getText());
		} catch (Exception e) {
			mensaje("No se ha seleccionado un alumno");
		}
		return codigo;
	}

	public String getNombres() {
		String nom = null;
		if (txtNombres.getText().trim().length() == 0) {
			mensaje("Ingresar nombres");
			return null;
		} else if (txtNombres.getText().trim().matches("[A-Z a-z]{3,50}")) {
			nom = txtNombres.getText();
		} else {
			mensaje("Ingresar nombres de max 50 letras");
			return null;
		}
		return nom;

	}

	public String getApellidos() {
		String ape = null;
		if (txtApellidos.getText().trim().length() == 0) {
			mensaje("Ingresar apellidos");
			return null;
		} else if (txtApellidos.getText().trim().matches("[A-Z a-z]{3,50}")) {
			ape = txtApellidos.getText();
		} else {
			mensaje("Ingresar apellidos de max 50 letras");
			return null;
		}
		return ape;

	}

	private String getDni() {
		String dni = null;
		if (txtDni.getText().trim().length() == 0) {
			mensaje("Falta registrar dni");
			return null;
		} else if (txtDni.getText().trim().matches("[0-9]{8}")) {
			dni = txtDni.getText();
		} else {
			mensaje("Ingresar dni de 8 digitos");
			return null;
		}
		return dni;
	}

	private String getEdad() {
		String edad = null;
		if (txtEdad.getText().trim().length() == 0) {
			mensaje("Falta registrar N.celular");
			return null;
		} else if (txtEdad.getText().trim().matches("[0-9]{1,3}")) {
			edad = txtEdad.getText();
		} else {
			mensaje("Ingresar edad max de 3 digitos");
			return null;
		}
		return edad;
	}

	private String getCelular() {
		String celu = null;
		if (txtCelular.getText().trim().length() == 0) {
			mensaje("Falta registrar N.celular");
			return null;
		} else if (txtCelular.getText().trim().matches("[0-9]{9}")) {
			celu = txtCelular.getText();
		} else {
			mensaje("Ingresar 9 digitos");
			return null;
		}
		return celu;
	}

	private String getEstado() {
		String estado = null;
		if (cboEstado.getSelectedIndex() != 0) {
			mensaje("El alumno solo se registrara como registrado");
			return null;
		} else {
			estado = cboEstado.getSelectedItem().toString();
		}
		return estado;
	}

	// **********************************************************************

	// ***************ACCION DE LOS BOTONES***********************

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnExportar_Alumnos_pdf) {
			actionPerformedBtnExportar_Alumnos_pdf(arg0);
		}
		if (arg0.getSource() == btnConsultar) {
			actionPerformedBtnConsultar(arg0);
		}
		if (arg0.getSource() == btnSalir) {
			actionPerformedBtnSalir(arg0);
		}
		if (arg0.getSource() == btnModificar) {
			actionPerformedBtnModificar(arg0);
		}
		if (arg0.getSource() == btnEliminar) {
			actionPerformedBtnEliminar(arg0);
		}
		if (arg0.getSource() == btnCancelar) {
			actionPerformedBtnCancelar(arg0);
		}
		if (arg0.getSource() == btnAdicionar) {
			actionPerformedBtnAdicionar(arg0);
		}
		if (arg0.getSource() == btnNuevo) {
			actionPerformedBtnNuevo(arg0);
		}
	}

	protected void actionPerformedBtnNuevo(ActionEvent arg0) {
		HabilitarCajas();
		hade(false, true);
		btnModificar.setEnabled(false);
		btnEliminar.setEnabled(false);
		txtCodAlumno.setText(getCodigoCorrelativo() + "");
		cboEstado.setSelectedIndex(0);
	}

	protected void actionPerformedBtnAdicionar(ActionEvent arg0) {
		registrarAlumno();
	}

	protected void actionPerformedBtnCancelar(ActionEvent arg0) {
		int can = JOptionPane.showConfirmDialog(this, "¿Esta seguro de Cancelar?", "Confirmacion",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (can == JOptionPane.YES_OPTION) {
			LimpiarCajas();
			hade(true, false);
			desHabilitarCajas();
		}
	}

	protected void actionPerformedBtnModificar(ActionEvent arg0) {
		int can = JOptionPane.showConfirmDialog(this, "¿Esta seguro de Modificar?", "Confirmacion",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (can == JOptionPane.YES_OPTION) {
			actualizarAlumno();
		}

	}

	protected void actionPerformedBtnEliminar(ActionEvent arg0) {
		int can = JOptionPane.showConfirmDialog(this, "¿Esta seguro de Eliminar?", "Confirmacion",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (can == JOptionPane.YES_OPTION) {
			eliminarAlumno();
		}
	}

	protected void actionPerformedBtnConsultar(ActionEvent arg0) {
		consultarAlumno();
	}

	protected void actionPerformedBtnSalir(ActionEvent arg0) {
		dispose();
	}

	protected void actionPerformedBtnExportar_Alumnos_pdf(ActionEvent arg0) {
		exportarAlumnoPDF();
	}
}
