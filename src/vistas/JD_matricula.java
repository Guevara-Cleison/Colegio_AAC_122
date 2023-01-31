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

import controlador.MatriculaControlador;
import modelo.Matricula;
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
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseListener;
import java.io.File;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class JD_matricula extends JDialog implements MouseListener, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JDesktopPane desktopPane;
	private JLabel lblCodigoDeMtri;
	private JLabel lblAsignatura;
	private JLabel lblCiclo;
	private JLabel lblCreditos;
	private JTextField txtnumMatricula;
	private JTextField txtFecha;

	private JButton btnConsultar;
	private JButton btnNuevo;
	private JButton btnAdicionar;
	private JButton btnModificar;
	private JButton btnCancelar;
	private JButton btnEliminar;
	private JTable tblMatricula;
	private JScrollPane scrollPane;
	private JLabel lblCan;
	private JLabel lblRegistros;
	private JLabel lblHoras;
	private JTextField txtHora;
	private JButton btnSalir;
	private JTextField txtCodAlumno;
	private JTextField txtCodCurso;
	private JButton btnAlumno;
	//
	public static DefaultTableModel modelo;
	//
	public static MatriculaControlador ma = new MatriculaControlador();
	private JButton btnExportar_Matriculas_pdf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JD_matricula dialog = new JD_matricula();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings({})
	public JD_matricula() {
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(JD_matricula.class.getResource("/imagenes/aac.jpg")));
		setResizable(false);
		setTitle("REGISTRO| MATRICULA");
		setBounds(100, 100, 1000, 700);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(240, 255, 240));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		desktopPane = new JDesktopPane();
		desktopPane.setToolTipText("");
		desktopPane.setBackground(new Color(240, 255, 240));
		desktopPane.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "MATRICULA",
				TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		desktopPane.setBounds(10, 11, 670, 290);
		contentPanel.add(desktopPane);
		desktopPane.setLayout(null);

		lblCodigoDeMtri = new JLabel("N\u00B0 de Matricula :");
		lblCodigoDeMtri.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCodigoDeMtri.setBounds(10, 23, 135, 25);
		desktopPane.add(lblCodigoDeMtri);

		lblAsignatura = new JLabel("Codigo de Alumno :");
		lblAsignatura.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAsignatura.setBounds(10, 59, 135, 25);
		desktopPane.add(lblAsignatura);

		lblCiclo = new JLabel("Codigo de Curso :");
		lblCiclo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCiclo.setBounds(10, 95, 135, 25);
		desktopPane.add(lblCiclo);

		lblCreditos = new JLabel("Fecha :");
		lblCreditos.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCreditos.setBounds(10, 131, 135, 25);
		desktopPane.add(lblCreditos);

		txtnumMatricula = new JTextField();
		txtnumMatricula.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtnumMatricula.setBounds(155, 24, 200, 25);
		desktopPane.add(txtnumMatricula);
		txtnumMatricula.setColumns(10);

		txtFecha = new JTextField();
		txtFecha.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtFecha.setBounds(155, 132, 200, 25);
		desktopPane.add(txtFecha);
		txtFecha.setColumns(10);

		btnConsultar = new JButton("CONSULTAR");
		btnConsultar.setIcon(new ImageIcon(JD_matricula.class.getResource("/imagenes/buscar.png")));
		btnConsultar.addActionListener(this);
		btnConsultar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnConsultar.setBounds(365, 13, 205, 42);
		desktopPane.add(btnConsultar);

		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setIcon(new ImageIcon(JD_matricula.class.getResource("/imagenes/cancelar.png")));
		btnCancelar.addActionListener(this);
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCancelar.setBounds(455, 225, 205, 42);
		desktopPane.add(btnCancelar);

		lblHoras = new JLabel("Hora :");
		lblHoras.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblHoras.setBounds(10, 167, 135, 25);
		desktopPane.add(lblHoras);

		txtHora = new JTextField();
		txtHora.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtHora.setBounds(155, 170, 200, 25);
		desktopPane.add(txtHora);
		txtHora.setColumns(10);

		txtCodAlumno = new JTextField();
		txtCodAlumno.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtCodAlumno.setEnabled(false);
		txtCodAlumno.setColumns(10);
		txtCodAlumno.setBounds(155, 62, 200, 25);
		desktopPane.add(txtCodAlumno);

		txtCodCurso = new JTextField();
		txtCodCurso.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtCodCurso.setEnabled(false);
		txtCodCurso.setColumns(10);
		txtCodCurso.setBounds(155, 98, 200, 25);
		desktopPane.add(txtCodCurso);

		btnAlumno = new JButton("Buscar Alumno");
		btnAlumno.setIcon(new ImageIcon(JD_matricula.class.getResource("/imagenes/buscarAlumno.png")));
		btnAlumno.addActionListener(this);
		btnAlumno.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAlumno.setEnabled(true);
		btnAlumno.setBounds(365, 61, 205, 23);
		desktopPane.add(btnAlumno);

		btnNuevo = new JButton("NUEVO");
		btnNuevo.setIcon(new ImageIcon(JD_matricula.class.getResource("/imagenes/nuevo.png")));
		btnNuevo.addActionListener(this);
		btnNuevo.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNuevo.setBounds(734, 32, 205, 42);
		contentPanel.add(btnNuevo);

		btnAdicionar = new JButton("ADICIONAR");
		btnAdicionar.setIcon(new ImageIcon(JD_matricula.class.getResource("/imagenes/adicionar.png")));
		btnAdicionar.addActionListener(this);
		btnAdicionar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAdicionar.setBounds(734, 85, 205, 42);
		contentPanel.add(btnAdicionar);

		btnModificar = new JButton("MODIFICAR");
		btnModificar.setIcon(new ImageIcon(JD_matricula.class.getResource("/imagenes/editar.png")));
		btnModificar.addActionListener(this);
		btnModificar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnModificar.setBounds(734, 138, 205, 42);
		contentPanel.add(btnModificar);

		btnEliminar = new JButton("ELIMINAR");
		btnEliminar.setIcon(new ImageIcon(JD_matricula.class.getResource("/imagenes/eliminar.png")));
		btnEliminar.addActionListener(this);
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnEliminar.setBounds(734, 191, 205, 42);
		contentPanel.add(btnEliminar);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 312, 974, 307);
		contentPanel.add(scrollPane);

		tblMatricula = new JTable();
		tblMatricula.addMouseListener(this);
		scrollPane.setViewportView(tblMatricula);

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
		btnSalir.setIcon(new ImageIcon(JD_matricula.class.getResource("/imagenes/salir.png")));
		btnSalir.addActionListener(this);
		btnSalir.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSalir.setBounds(734, 244, 205, 42);
		contentPanel.add(btnSalir);

		//
		EncabezadoTablaCurso();
		cargarDatos();
		desHabilitarCajas();
		btnAlumno.setEnabled(false);

		btnExportar_Matriculas_pdf = new JButton("");
		btnExportar_Matriculas_pdf.addActionListener(this);
		btnExportar_Matriculas_pdf.setIcon(new ImageIcon(JD_matricula.class.getResource("/imagenes/pdf.png")));
		btnExportar_Matriculas_pdf.setForeground(Color.BLACK);
		btnExportar_Matriculas_pdf.setBackground(new Color(240, 255, 240));
		btnExportar_Matriculas_pdf.setBounds(610, 167, 50, 50);
		desktopPane.add(btnExportar_Matriculas_pdf);
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
		modelo.addColumn("Nro Matricula");
		modelo.addColumn("Codigo Alumno");
		modelo.addColumn("Codigo Curso");
		modelo.addColumn("Fecha");
		modelo.addColumn("Hora");
		tblMatricula.setModel(modelo);
		// para_que_no_se_muevan_los_encabezados
		tblMatricula.getTableHeader().setReorderingAllowed(false);
		tblMatricula.getTableHeader().setResizingAllowed(false);
	}

	public void cargarDatos() {
		// limpiar_el_modelo
		modelo.setRowCount(0);
		ArrayList<Matricula> listaMatricula = ma.listado();
		for (Matricula m : listaMatricula) {
			Object fila[] = { m.getNumMatricula(), m.getCodAlumno(), m.getCodCurso(), m.getFecha(), m.getHora() };
			// enviar_el_objeto_FILA_al_modelo
			modelo.addRow(fila);
			// mostrar_cantidad_de_registros
			lblCan.setText("" + modelo.getRowCount());
		}
	}

	public void mostrarDatos(int fila) {
		if (fila != -1) {
			String num, codA, codC, fecha, hora;
			num = modelo.getValueAt(fila, 0).toString();
			codA = modelo.getValueAt(fila, 1).toString();
			codC = modelo.getValueAt(fila, 2).toString();
			fecha = modelo.getValueAt(fila, 3).toString();
			hora = modelo.getValueAt(fila, 4).toString();
			// System.out.println(estado_);
			// enviar_datos_a_las_cajas
			txtnumMatricula.setText(num);
			txtCodAlumno.setText(codA);
			txtCodCurso.setText(codC);
			txtFecha.setText(fecha);
			txtHora.setText(hora);
		}
	}

	public void mouseClicked(MouseEvent arg0) {
	}

	public void mouseEntered(MouseEvent arg0) {
	}

	public void mouseExited(MouseEvent arg0) {
	}

	public void mousePressed(MouseEvent arg0) {
		if (arg0.getSource() == tblMatricula) {
			mousePressedTblAlumnos(arg0);
		}
	}

	public void mouseReleased(MouseEvent arg0) {
	}

	protected void mousePressedTblAlumnos(MouseEvent arg0) {
		int fila;
		fila = tblMatricula.getSelectedRow();
		mostrarDatos(fila);
		if (fila != -1) {
			desHabilitarCajas();
			hade(false, true);
			btnAdicionar.setEnabled(false);
			txtCodCurso.setEnabled(true);
		}
	}

	// **********************************************************************

	// *******************METODOS UTILES***************************
	public void mensaje(String s) {
		JOptionPane.showMessageDialog(this, s);
	}

	public void HabilitarCajas() {
		txtnumMatricula.setEnabled(false);
		txtCodAlumno.setEnabled(true);
		txtCodCurso.setEnabled(true);
		txtFecha.setEnabled(false);
		txtHora.setEnabled(false);
	}

	public void desHabilitarCajas() {
		txtnumMatricula.setEnabled(false);
		txtCodAlumno.setEnabled(false);
		txtCodCurso.setEnabled(false);
		txtFecha.setEnabled(false);
		txtHora.setEnabled(false);
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
		txtnumMatricula.setText("");
		txtCodAlumno.setText("");
		txtCodCurso.setText("");
		txtFecha.setText("Fecha del sistema");
		txtHora.setText("Hora del sistema");
	}

	// **********************************************************************

	// ********************************METODOS GENERALES**********************

	void registrarMatricula() {
		// variables
		int numM, codA, codC;
		// entrada_de_datos
		numM = getNumMatricula();
		codA = getCodAlumno();
		codC = getCodCurso();
		// _procesos
		// pasamos_los_datos_de_las_cajas_a_la_clase_curso
		Matricula m = new Matricula();
		m.setNumMatricula(numM);
		m.setCodAlumno(codA);
		m.setCodCurso(codC);
		// le_pasamos_de_la_clase_curso_a_el_metodo_controlador
		int ok = ma.registrar(m);

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

	void actualizarMatricula() {
		Matricula m = new Matricula();
		m.setCodCurso(getCodCurso());
		m.setNumMatricula(getNumMatricula());
		//
		int valor = ma.actualizar(m);
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

	void eliminarMatricula() {
		int valor = ma.eliminar(getNumMatricula(), getCodAlumno());
		if (valor == 1) {
			mensaje("Eliminación EXITOSA!");
			cargarDatos();
			LimpiarCajas();
			hade(true, false);
			desHabilitarCajas();
			lblCan.setText("" + modelo.getRowCount());
		} else {
			mensaje("Error al eliminar!!!");
		}
	}

	void consultarMatricula() {
		try {
			//
			int buscar = Integer
					.parseInt(JOptionPane.showInputDialog(this, "Ingrese codigo de Numero de Matricula a buscar"));
			//
			ArrayList<Matricula> mat = ma.buscarXCodigo(buscar);
			if (mat != null) {
				for (Matricula m : mat) {
					txtnumMatricula.setText("" + m.getNumMatricula());
					txtCodAlumno.setText("" + m.getCodAlumno());
					txtCodCurso.setText("" + m.getCodCurso());
					txtFecha.setText("" + m.getFecha());
					txtHora.setText("" + m.getHora());
					//
					HabilitarCajas();
					hade(false, true);
					btnAdicionar.setEnabled(false);
					txtnumMatricula.setEnabled(false);
				}

			} else {
				mensaje("El Numero de Matricula no existe");
				hade(true, false);
			}
		} catch (Exception e) {
			mensaje("Error de ingreso");
			LimpiarCajas();
			desHabilitarCajas();
		}
	}

	void consultarCodAlumnoXdni() {
		try {
			//
			int buscar = Integer
					.parseInt(JOptionPane.showInputDialog(this, "Ingrese el N° de DNI del Alumno a buscar"));
			//
			ArrayList<Matricula> mat = ma.buscarCodAluXdni(buscar);
			if (mat != null) {
				for (Matricula m : mat) {
					txtCodAlumno.setText("" + m.getCodAlumno());
					//
					HabilitarCajas();
					txtnumMatricula.setEnabled(false);
				}

			} else {
				mensaje("El DNI de Alumno no existe");
				hade(true, false);
			}
		} catch (Exception e) {
			mensaje("Error de ingreso");
		}
	}

	// **********************************************************************

	// *METODOS_PARA_EXPORTAR************************************************

	void exportarMatriculaPDF() {

		JasperReport report;
		JasperPrint print;
		try {
			report = JasperCompileManager
					.compileReport(new File("").getAbsolutePath() + "/src/reportes/reporteMatriculas.jrxml");
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

	private int getCodigoCorrelativo() {
		// tiene_que_dar_correlativo
		ArrayList<Matricula> listaMatricula = ma.listado();
		if (listaMatricula.size() == 0) {
			return 10001;
		} else {
			int correlativo;
			correlativo = (listaMatricula.get(listaMatricula.size() - 1).getNumMatricula()) + 1;
			return correlativo;
		}
	}

	private int getNumMatricula() {
		int codigo = -1;
		try {
			codigo = Integer.parseInt(txtnumMatricula.getText());
		} catch (Exception e) {
			mensaje("Ingresar condigo de Matricula");
		}
		return codigo;
	}

	public int getCodAlumno() {
		int codA = -1;
		try {
			codA = Integer.parseInt(txtCodAlumno.getText());
		} catch (Exception e) {
			mensaje("Ingresar Codigo de Alumno");
		}
		return codA;
	}

	public int getCodCurso() {
		int codC = -1;
		try {
			codC = Integer.parseInt(txtCodCurso.getText());
		} catch (Exception e) {
			mensaje("Ingresar Codigo de Curso");
		}
		return codC;
	}

	// **********************************************************************

	// ***************ACCION DE LOS BOTONES***********************

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnExportar_Matriculas_pdf) {
			actionPerformedBtnExportar_Matriculas_pdf(arg0);
		}
		if (arg0.getSource() == btnAlumno) {
			actionPerformedBtnAlumno(arg0);
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
		txtnumMatricula.setText(getCodigoCorrelativo() + "");
		txtCodAlumno.requestFocus();
		btnModificar.setEnabled(false);
		btnEliminar.setEnabled(false);
		btnAlumno.setEnabled(true);
	}

	protected void actionPerformedBtnAdicionar(ActionEvent arg0) {
		registrarMatricula();
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
			actualizarMatricula();
		}

	}

	protected void actionPerformedBtnEliminar(ActionEvent arg0) {
		int can = JOptionPane.showConfirmDialog(this, "¿Esta seguro de Eliminar?", "Confirmacion",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (can == JOptionPane.YES_OPTION) {
			eliminarMatricula();
		}
	}

	protected void actionPerformedBtnConsultar(ActionEvent arg0) {
		consultarMatricula();
	}

	protected void actionPerformedBtnAlumno(ActionEvent arg0) {
		consultarCodAlumnoXdni();
	}

	protected void actionPerformedBtnSalir(ActionEvent arg0) {
		dispose();
	}

	protected void actionPerformedBtnExportar_Matriculas_pdf(ActionEvent arg0) {
		exportarMatriculaPDF();
	}
}
