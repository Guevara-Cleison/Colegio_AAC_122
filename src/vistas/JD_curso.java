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

import controlador.CursoControlador;
import modelo.Curso;
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

public class JD_curso extends JDialog implements MouseListener, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JDesktopPane desktopPane;
	private JLabel lblCodigoDeCurso;
	private JLabel lblAsignatura;
	private JLabel lblCiclo;
	private JLabel lblCreditos;
	private JTextField txtCodCurso;
	private JTextField txtAsignatura;
	private JTextField txtCreditos;

	private JButton btnConsultar;
	private JButton btnNuevo;
	private JButton btnAdicionar;
	private JButton btnModificar;
	private JButton btnCancelar;
	private JButton btnEliminar;
	private JTable tblCurso;
	private JScrollPane scrollPane;
	private JLabel lblCan;
	private JLabel lblRegistros;
	private JLabel lblHoras;
	private JTextField txtHoras;
	private JButton btnSalir;
	//
	public static DefaultTableModel modelo;
	public static JComboBox<String> cboCiclo;
	//
	public static CursoControlador cur = new CursoControlador();
	private JButton btnExportar_Cursos_pdf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JD_curso dialog = new JD_curso();
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
	public JD_curso() {
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(JD_curso.class.getResource("/imagenes/aac.jpg")));
		setResizable(false);
		setTitle("MANTENIMIENTO| CURSO");
		setBounds(100, 100, 1000, 700);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(240, 255, 240));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		desktopPane = new JDesktopPane();
		desktopPane.setToolTipText("");
		desktopPane.setBackground(new Color(240, 255, 240));
		desktopPane.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "CURSO", TitledBorder.LEFT,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		desktopPane.setBounds(10, 11, 670, 290);
		contentPanel.add(desktopPane);
		desktopPane.setLayout(null);

		lblCodigoDeCurso = new JLabel("Codigo de Curso :");
		lblCodigoDeCurso.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCodigoDeCurso.setBounds(10, 23, 135, 25);
		desktopPane.add(lblCodigoDeCurso);

		lblAsignatura = new JLabel("Asignatura :");
		lblAsignatura.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAsignatura.setBounds(10, 59, 135, 25);
		desktopPane.add(lblAsignatura);

		lblCiclo = new JLabel("Ciclo :");
		lblCiclo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCiclo.setBounds(10, 95, 135, 25);
		desktopPane.add(lblCiclo);

		lblCreditos = new JLabel("Creditos :");
		lblCreditos.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCreditos.setBounds(10, 131, 135, 25);
		desktopPane.add(lblCreditos);

		txtCodCurso = new JTextField();
		txtCodCurso.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtCodCurso.setBounds(155, 24, 200, 25);
		desktopPane.add(txtCodCurso);
		txtCodCurso.setColumns(10);

		txtAsignatura = new JTextField();
		txtAsignatura.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtAsignatura.setBounds(155, 60, 200, 25);
		desktopPane.add(txtAsignatura);
		txtAsignatura.setColumns(10);

		txtCreditos = new JTextField();
		txtCreditos.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtCreditos.setBounds(155, 132, 200, 25);
		desktopPane.add(txtCreditos);
		txtCreditos.setColumns(10);

		cboCiclo = new JComboBox<String>();
		cboCiclo.setModel(new DefaultComboBoxModel(new String[] { "Seleccione", "Primero", "Segundo", "Tercero",
				"Cuarto", "Quinto", "Sexto", "Titulacion" }));
		cboCiclo.setFont(new Font("Tahoma", Font.BOLD, 13));
		cboCiclo.setBounds(155, 95, 200, 25);
		desktopPane.add(cboCiclo);

		btnConsultar = new JButton("CONSULTAR");
		btnConsultar.setIcon(new ImageIcon(JD_curso.class.getResource("/imagenes/buscar.png")));
		btnConsultar.addActionListener(this);
		btnConsultar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnConsultar.setBounds(365, 23, 205, 42);
		desktopPane.add(btnConsultar);

		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setIcon(new ImageIcon(JD_curso.class.getResource("/imagenes/cancelar.png")));
		btnCancelar.addActionListener(this);
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCancelar.setBounds(455, 225, 205, 42);
		desktopPane.add(btnCancelar);

		lblHoras = new JLabel("Horas :");
		lblHoras.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblHoras.setBounds(10, 167, 135, 25);
		desktopPane.add(lblHoras);

		txtHoras = new JTextField();
		txtHoras.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtHoras.setBounds(155, 170, 200, 25);
		desktopPane.add(txtHoras);
		txtHoras.setColumns(10);

		btnExportar_Cursos_pdf = new JButton("");
		btnExportar_Cursos_pdf.addActionListener(this);
		btnExportar_Cursos_pdf.setIcon(new ImageIcon(JD_curso.class.getResource("/imagenes/pdf.png")));
		btnExportar_Cursos_pdf.setForeground(Color.BLACK);
		btnExportar_Cursos_pdf.setBackground(new Color(240, 255, 240));
		btnExportar_Cursos_pdf.setBounds(610, 167, 50, 50);
		desktopPane.add(btnExportar_Cursos_pdf);

		btnNuevo = new JButton("NUEVO");
		btnNuevo.setIcon(new ImageIcon(JD_curso.class.getResource("/imagenes/nuevo.png")));
		btnNuevo.addActionListener(this);
		btnNuevo.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNuevo.setBounds(734, 32, 205, 42);
		contentPanel.add(btnNuevo);

		btnAdicionar = new JButton("ADICIONAR");
		btnAdicionar.setIcon(new ImageIcon(JD_curso.class.getResource("/imagenes/adicionar.png")));
		btnAdicionar.addActionListener(this);
		btnAdicionar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAdicionar.setBounds(734, 85, 205, 42);
		contentPanel.add(btnAdicionar);

		btnModificar = new JButton("MODIFICAR");
		btnModificar.setIcon(new ImageIcon(JD_curso.class.getResource("/imagenes/editar.png")));
		btnModificar.addActionListener(this);
		btnModificar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnModificar.setBounds(734, 138, 205, 42);
		contentPanel.add(btnModificar);

		btnEliminar = new JButton("ELIMINAR");
		btnEliminar.setIcon(new ImageIcon(JD_curso.class.getResource("/imagenes/eliminar.png")));
		btnEliminar.addActionListener(this);
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnEliminar.setBounds(734, 191, 205, 42);
		contentPanel.add(btnEliminar);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 312, 974, 307);
		contentPanel.add(scrollPane);

		tblCurso = new JTable();
		tblCurso.addMouseListener(this);
		scrollPane.setViewportView(tblCurso);

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
		btnSalir.setIcon(new ImageIcon(JD_curso.class.getResource("/imagenes/salir.png")));
		btnSalir.addActionListener(this);
		btnSalir.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSalir.setBounds(734, 244, 205, 42);
		contentPanel.add(btnSalir);

		//
		EncabezadoTablaCurso();
		cargarDatos();
		desHabilitarCajas();
		hade(true, false);

	}

	// *********TABLA MODELO****************************************
	public void EncabezadoTablaCurso() {
		modelo = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int colum) {
				return false;
			}
		};
		modelo.addColumn("Codigo");
		modelo.addColumn("Asignatura");
		modelo.addColumn("Ciclo");
		modelo.addColumn("Creditos");
		modelo.addColumn("Horas");
		tblCurso.setModel(modelo);
		// para_que_no_se_muevan_los_encabezados
		tblCurso.getTableHeader().setReorderingAllowed(false);
		tblCurso.getTableHeader().setResizingAllowed(false);
	}

	public void cargarDatos() {
		// limpiar_el_modelo
		modelo.setRowCount(0);
		ArrayList<Curso> listaCurso = cur.listado();
		for (Curso c : listaCurso) {
			Object fila[] = { c.getId(), c.getAsignatura(), c.getCiclo(), c.getCreditos(), c.getHoras() };
			// enviar_el_objeto_FILA_al_modelo
			modelo.addRow(fila);
			// mostrar_cantidad_de_registros
			lblCan.setText("" + modelo.getRowCount());
		}
	}

	public void mostrarDatos(int fila) {
		if (fila != -1) {
			String id, asig, ciclo, cred, horas;
			id = modelo.getValueAt(fila, 0).toString();
			asig = modelo.getValueAt(fila, 1).toString();
			ciclo = modelo.getValueAt(fila, 2).toString();
			cred = modelo.getValueAt(fila, 3).toString();
			horas = modelo.getValueAt(fila, 4).toString();
			// System.out.println(estado_);
			// enviar_datos_a_las_cajas
			txtCodCurso.setText(id);
			txtAsignatura.setText(asig);
			cboCiclo.setSelectedItem(ciclo);
			txtCreditos.setText(cred);
			txtHoras.setText(horas);
		}
	}

	public void mouseClicked(MouseEvent arg0) {
	}

	public void mouseEntered(MouseEvent arg0) {
	}

	public void mouseExited(MouseEvent arg0) {
	}

	public void mousePressed(MouseEvent arg0) {
		if (arg0.getSource() == tblCurso) {
			mousePressedTblAlumnos(arg0);
		}
	}

	public void mouseReleased(MouseEvent arg0) {
	}

	protected void mousePressedTblAlumnos(MouseEvent arg0) {
		int fila;
		fila = tblCurso.getSelectedRow();
		mostrarDatos(fila);
		if (fila != -1) {
			HabilitarCajas();
			hade(false, true);
			btnAdicionar.setEnabled(false);
			txtCodCurso.setEnabled(false);
		}
	}

	// **********************************************************************

	// *******************METODOS UTILES***************************
	public void mensaje(String s) {
		JOptionPane.showMessageDialog(this, s);
	}

	public void HabilitarCajas() {
		txtCodCurso.setEnabled(true);
		txtCodCurso.requestFocus();
		txtAsignatura.setEnabled(true);
		cboCiclo.setEnabled(true);
		txtCreditos.setEnabled(true);
		txtHoras.setEnabled(true);
	}

	public void desHabilitarCajas() {
		txtCodCurso.setEnabled(false);
		txtAsignatura.setEnabled(false);
		cboCiclo.setEnabled(false);
		txtCreditos.setEnabled(false);
		txtHoras.setEnabled(false);
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
		txtCodCurso.setText("");
		txtAsignatura.setText("");
		cboCiclo.setSelectedIndex(0);
		txtCreditos.setText("");
		txtHoras.setText("");
	}

	// **********************************************************************

	// ********************************METODOS GENERALES**********************

	void registrarCurso() {
		// variables
		// registrar_el_codigo
		String asig, cic, creditos, horas;
		int codigo;
		// entrada_de_datos
		codigo = getCodigo();
		asig = getAsignatura();
		cic = getCiclo();
		creditos = getCreditos();
		horas = getHoras();
		// _procesos
		// pasamos_los_datos_de_las_cajas_a_la_clase_curso
		Curso c = new Curso();
		c.setId(codigo);
		c.setAsignatura(asig);
		c.setCiclo(cic);
		c.setCreditos(creditos);
		c.setHoras(horas);
		// le_pasamos_de_la_clase_curso_a_el_metodo_controlador
		int ok = cur.registrar(c);

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

	void actualizarCurso() {
		Curso c = new Curso();
		c.setAsignatura(getAsignatura());
		c.setCiclo(getCiclo());
		c.setCreditos(getCreditos());
		c.setHoras(getHoras());
		c.setId(getCodigo());
		//
		int valor = cur.actualizar(c);
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

	void eliminarCurso() {
		int valor = cur.eliminar(getCodigo());
		if (valor == 1) {
			mensaje("Eliminaci�n EXITOSA!");
			cargarDatos();
			LimpiarCajas();
			hade(true, false);
			desHabilitarCajas();
			lblCan.setText("" + modelo.getRowCount());
		} else {
			mensaje("Error al eliminar!!!");
		}
	}

	void consultarCurso() {
		try {
			//
			int buscar = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese codigo de curso a buscar"));
			//
			ArrayList<Curso> cu = cur.buscarXCodigo(buscar);
			if (cu != null) {
				for (Curso c : cu) {
					txtCodCurso.setText("" + c.getId());
					txtAsignatura.setText("" + c.getAsignatura());
					cboCiclo.setSelectedItem(c.getCiclo());
					txtCreditos.setText("" + c.getCreditos());
					txtHoras.setText("" + c.getHoras());
					//
					HabilitarCajas();
					hade(false, true);
					btnAdicionar.setEnabled(false);
					txtCodCurso.setEnabled(false);
				}

			} else {
				mensaje("El Codigo ingresado no existe");
				hade(true, false);
			}
		} catch (Exception e) {
			mensaje("Error de ingreso");
			LimpiarCajas();
			desHabilitarCajas();
		}
	}

	// **********************************************************************

	// *METODOS_PARA_EXPORTAR************************************************

	void exportarCursoPDF() {

		JasperReport report;
		JasperPrint print;
		try {
			report = JasperCompileManager
					.compileReport(new File("").getAbsolutePath() + "/src/reportes/reporteCursos.jrxml");
			print = JasperFillManager.fillReport(report, null, MySQLConexion.conectar());
			JasperViewer vista = new JasperViewer(print, false);
			vista.setZoomRatio((float) 0.8);
			vista.setTitle("Reporte de Curso");
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

	private int getCodigo() {
		int codigo = -1;
		try {
			codigo = Integer.parseInt(txtCodCurso.getText());
		} catch (Exception e) {
			mensaje("Ingresar condigo de Curso");
		}
		return codigo;
	}

	public String getAsignatura() {
		String asi = null;
		if (txtAsignatura.getText().trim().length() == 0) {
			mensaje("Ingresar Asignatura");
			return null;
		} else if (txtAsignatura.getText().trim().matches("[A-Z a-z]{3,50}")) {
			asi = txtAsignatura.getText();
		} else {
			mensaje("Ingresar Asignatura de max 50 letras");
			return null;
		}
		return asi;

	}

	private String getCiclo() {
		String ciclo = null;
		if (cboCiclo.getSelectedIndex() == 0) {
			mensaje("Seleccione un nivel (ciclo) del Curso");
			return null;
		} else {
			ciclo = cboCiclo.getSelectedItem().toString();
		}
		return ciclo;
	}

	public String getCreditos() {
		String cre = null;
		if (txtCreditos.getText().trim().length() == 0) {
			mensaje("Ingresar Creditos");
			return null;
		} else if (txtCreditos.getText().trim().matches("[0-9]{1,3}")) {
			cre = txtCreditos.getText();
		} else {
			mensaje("Ingresar Creditos de max 3 digitos");
			return null;
		}
		return cre;

	}

	private String getHoras() {
		String horas = null;
		if (txtHoras.getText().trim().length() == 0) {
			mensaje("Falta ingresar Horas");
			return null;
		} else if (txtHoras.getText().trim().matches("[0-9]{1,3}")) {
			horas = txtHoras.getText();
		} else {
			mensaje("Ingresar horas max 3 digitos");
			return null;
		}
		return horas;
	}

	// **********************************************************************

	// ***************ACCION DE LOS BOTONES***********************

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnExportar_Cursos_pdf) {
			actionPerformedBtnExportar_Cursos_pdf(arg0);
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
		LimpiarCajas();
		hade(false, true);
		btnModificar.setEnabled(false);
		btnEliminar.setEnabled(false);
	}

	protected void actionPerformedBtnAdicionar(ActionEvent arg0) {
		registrarCurso();
	}

	protected void actionPerformedBtnCancelar(ActionEvent arg0) {
		int can = JOptionPane.showConfirmDialog(this, "�Esta seguro de Cancelar?", "Confirmacion",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (can == JOptionPane.YES_OPTION) {
			LimpiarCajas();
			hade(true, false);
			desHabilitarCajas();
		}
	}

	protected void actionPerformedBtnModificar(ActionEvent arg0) {
		int can = JOptionPane.showConfirmDialog(this, "�Esta seguro de Modificar?", "Confirmacion",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (can == JOptionPane.YES_OPTION) {
			actualizarCurso();
		}

	}

	protected void actionPerformedBtnEliminar(ActionEvent arg0) {
		int can = JOptionPane.showConfirmDialog(this, "�Esta seguro de Eliminar?", "Confirmacion",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (can == JOptionPane.YES_OPTION) {
			eliminarCurso();
		}
	}

	protected void actionPerformedBtnConsultar(ActionEvent arg0) {
		consultarCurso();
	}

	protected void actionPerformedBtnSalir(ActionEvent arg0) {
		dispose();
	}

	protected void actionPerformedBtnExportar_Cursos_pdf(ActionEvent arg0) {
		exportarCursoPDF();
	}
}
