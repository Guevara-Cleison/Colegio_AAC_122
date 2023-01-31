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

import controlador.RetiroControlador;
import modelo.Retiro;
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

public class JD_retiro extends JDialog implements MouseListener, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JDesktopPane desktopPane;
	private JLabel lblCodigoDeMtri;
	private JLabel lblAsignatura;
	private JLabel lblCreditos;
	private JTextField txtnumRetiro;
	private JTextField txtFecha;

	private JButton btnConsultar;
	private JButton btnNuevo;
	private JButton btnAdicionar;
	private JButton btnModificar;
	private JButton btnCancelar;
	private JButton btnEliminar;
	private JTable tblRetiro;
	private JScrollPane scrollPane;
	private JLabel lblCan;
	private JLabel lblRegistros;
	private JLabel lblHoras;
	private JTextField txtHora;
	private JButton btnSalir;
	private JTextField txtnumMatricula;
	//
	public static DefaultTableModel modelo;
	//
	public static RetiroControlador re = new RetiroControlador();
	private JButton btnExportar_Matriculas_pdf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JD_retiro dialog = new JD_retiro();
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
	public JD_retiro() {
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(JD_retiro.class.getResource("/imagenes/aac.jpg")));
		setResizable(false);
		setTitle("REGISTRO| RETIRO");
		setBounds(100, 100, 1000, 700);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(240, 255, 240));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		desktopPane = new JDesktopPane();
		desktopPane.setToolTipText("");
		desktopPane.setBackground(new Color(240, 255, 240));
		desktopPane.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "RETIRO", TitledBorder.LEFT,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		desktopPane.setBounds(10, 11, 670, 290);
		contentPanel.add(desktopPane);
		desktopPane.setLayout(null);

		lblCodigoDeMtri = new JLabel("N\u00B0 de Retiro :");
		lblCodigoDeMtri.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCodigoDeMtri.setBounds(10, 23, 135, 25);
		desktopPane.add(lblCodigoDeMtri);

		lblAsignatura = new JLabel("N\u00B0 de Matricula :");
		lblAsignatura.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAsignatura.setBounds(10, 59, 135, 25);
		desktopPane.add(lblAsignatura);

		lblCreditos = new JLabel("Fecha :");
		lblCreditos.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCreditos.setBounds(10, 95, 135, 25);
		desktopPane.add(lblCreditos);

		txtnumRetiro = new JTextField();
		txtnumRetiro.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtnumRetiro.setBounds(155, 24, 200, 25);
		desktopPane.add(txtnumRetiro);
		txtnumRetiro.setColumns(10);

		txtFecha = new JTextField();
		txtFecha.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtFecha.setBounds(155, 96, 200, 25);
		desktopPane.add(txtFecha);
		txtFecha.setColumns(10);

		btnConsultar = new JButton("CONSULTAR");
		btnConsultar.setIcon(new ImageIcon(JD_retiro.class.getResource("/imagenes/buscar.png")));
		btnConsultar.addActionListener(this);
		btnConsultar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnConsultar.setBounds(365, 13, 205, 42);
		desktopPane.add(btnConsultar);

		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setIcon(new ImageIcon(JD_retiro.class.getResource("/imagenes/cancelar.png")));
		btnCancelar.addActionListener(this);
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCancelar.setBounds(455, 225, 205, 42);
		desktopPane.add(btnCancelar);

		lblHoras = new JLabel("Hora :");
		lblHoras.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblHoras.setBounds(10, 131, 135, 25);
		desktopPane.add(lblHoras);

		txtHora = new JTextField();
		txtHora.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtHora.setBounds(155, 134, 200, 25);
		desktopPane.add(txtHora);
		txtHora.setColumns(10);

		txtnumMatricula = new JTextField();
		txtnumMatricula.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtnumMatricula.setEnabled(false);
		txtnumMatricula.setColumns(10);
		txtnumMatricula.setBounds(155, 62, 200, 25);
		desktopPane.add(txtnumMatricula);

		btnExportar_Matriculas_pdf = new JButton("");
		btnExportar_Matriculas_pdf.addActionListener(this);
		btnExportar_Matriculas_pdf.setIcon(new ImageIcon(JD_retiro.class.getResource("/imagenes/pdf.png")));
		btnExportar_Matriculas_pdf.setForeground(Color.BLACK);
		btnExportar_Matriculas_pdf.setBackground(new Color(240, 255, 240));
		btnExportar_Matriculas_pdf.setBounds(610, 164, 50, 50);
		desktopPane.add(btnExportar_Matriculas_pdf);

		btnNuevo = new JButton("NUEVO");
		btnNuevo.setIcon(new ImageIcon(JD_retiro.class.getResource("/imagenes/nuevo.png")));
		btnNuevo.addActionListener(this);
		btnNuevo.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNuevo.setBounds(734, 32, 205, 42);
		contentPanel.add(btnNuevo);

		btnAdicionar = new JButton("ADICIONAR");
		btnAdicionar.setIcon(new ImageIcon(JD_retiro.class.getResource("/imagenes/adicionar.png")));
		btnAdicionar.addActionListener(this);
		btnAdicionar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAdicionar.setBounds(734, 85, 205, 42);
		contentPanel.add(btnAdicionar);

		btnModificar = new JButton("MODIFICAR");
		btnModificar.setIcon(new ImageIcon(JD_retiro.class.getResource("/imagenes/editar.png")));
		btnModificar.addActionListener(this);
		btnModificar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnModificar.setBounds(734, 138, 205, 42);
		contentPanel.add(btnModificar);

		btnEliminar = new JButton("ELIMINAR");
		btnEliminar.setIcon(new ImageIcon(JD_retiro.class.getResource("/imagenes/eliminar.png")));
		btnEliminar.addActionListener(this);
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnEliminar.setBounds(734, 191, 205, 42);
		contentPanel.add(btnEliminar);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 312, 974, 307);
		contentPanel.add(scrollPane);

		tblRetiro = new JTable();
		tblRetiro.addMouseListener(this);
		scrollPane.setViewportView(tblRetiro);

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
		btnSalir.setIcon(new ImageIcon(JD_retiro.class.getResource("/imagenes/salir.png")));
		btnSalir.addActionListener(this);
		btnSalir.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSalir.setBounds(734, 244, 205, 42);
		contentPanel.add(btnSalir);

		//
		EncabezadoTablaCurso();
		cargarDatos();
		desHabilitarCajas();
		hade(true, false);
		btnModificar.setEnabled(false);
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
		modelo.addColumn("Nro Retiro");
		modelo.addColumn("Nro Matricula");
		modelo.addColumn("Fecha");
		modelo.addColumn("Hora");
		tblRetiro.setModel(modelo);
		// para_que_no_se_muevan_los_encabezados
		tblRetiro.getTableHeader().setReorderingAllowed(false);
		tblRetiro.getTableHeader().setResizingAllowed(false);
	}

	public void cargarDatos() {
		// limpiar_el_modelo
		modelo.setRowCount(0);
		ArrayList<Retiro> listaRetiro = re.listado();
		for (Retiro r : listaRetiro) {
			Object fila[] = { r.getNumReti(), r.getNumMatricula(), r.getFecha(), r.getHora() };
			// enviar_el_objeto_FILA_al_modelo
			modelo.addRow(fila);
			// mostrar_cantidad_de_registros
			lblCan.setText("" + modelo.getRowCount());
		}
	}

	public void mostrarDatos(int fila) {
		if (fila != -1) {
			String numR, numM, fecha, hora;
			numR = modelo.getValueAt(fila, 0).toString();
			numM = modelo.getValueAt(fila, 1).toString();
			fecha = modelo.getValueAt(fila, 2).toString();
			hora = modelo.getValueAt(fila, 3).toString();
			// System.out.println(estado_);
			// enviar_datos_a_las_cajas
			txtnumRetiro.setText(numR);
			txtnumMatricula.setText(numM);
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
		if (arg0.getSource() == tblRetiro) {
			mousePressedTblAlumnos(arg0);
		}
	}

	public void mouseReleased(MouseEvent arg0) {
	}

	protected void mousePressedTblAlumnos(MouseEvent arg0) {
		int fila;
		fila = tblRetiro.getSelectedRow();
		mostrarDatos(fila);
		if (fila != -1) {
			desHabilitarCajas();
			hade(false, true);
			btnAdicionar.setEnabled(false);
			txtnumMatricula.setEnabled(true);
		}
	}

	// **********************************************************************

	// *******************METODOS UTILES***************************
	public void mensaje(String s) {
		JOptionPane.showMessageDialog(this, s);
	}

	public void HabilitarCajas() {
		txtnumRetiro.setEnabled(false);
		txtnumMatricula.setEnabled(true);
		txtFecha.setEnabled(false);
		txtHora.setEnabled(false);
	}

	public void desHabilitarCajas() {
		txtnumRetiro.setEnabled(false);
		txtnumMatricula.setEnabled(false);
		txtFecha.setEnabled(false);
		txtHora.setEnabled(false);
	}

	public void hade(boolean h, boolean d) {
		btnNuevo.setEnabled(h);
		btnAdicionar.setEnabled(d);
		btnCancelar.setEnabled(d);
		btnEliminar.setEnabled(d);
		// btnModificar.setEnabled(d);
		btnConsultar.setEnabled(h);
	}

	void LimpiarCajas() {
		txtnumRetiro.setText("");
		txtnumMatricula.setText("");
		txtFecha.setText("Fecha del sistema");
		txtHora.setText("Hora del sistema");
	}

	// **********************************************************************

	// ********************************METODOS GENERALES**********************

	void registrarRetiro() {
		// variables
		int numR, numM;
		// entrada_de_datos
		numR = getNumRetiro();
		numM = getNumMatricula();
		// _procesos
		// pasamos_los_datos_de_las_cajas_a_la_clase_curso
		Retiro r = new Retiro();
		r.setNumReti(numR);
		r.setNumMatricula(numM);
		// le_pasamos_de_la_clase_curso_a_el_metodo_controlador
		int ok = re.registrar(r);

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

	void eliminarRetiro() {
		int valor = re.eliminar(getNumRetiro(), getNumMatricula());
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
			ArrayList<Retiro> ret = re.buscarXCodigo(buscar);
			if (ret != null) {
				for (Retiro r : ret) {
					txtnumRetiro.setText("" + r.getNumReti());
					txtnumMatricula.setText("" + r.getNumMatricula());
					txtFecha.setText("" + r.getFecha());
					txtHora.setText("" + r.getHora());
					//
					HabilitarCajas();
					hade(false, true);
					btnAdicionar.setEnabled(false);
					txtnumRetiro.setEnabled(false);
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

	// **********************************************************************

	// *METODOS_PARA_EXPORTAR************************************************

	void exportarRetiroPDF() {

		JasperReport report;
		JasperPrint print;
		try {
			report = JasperCompileManager
					.compileReport(new File("").getAbsolutePath() + "/src/reportes/reporteRetiros.jrxml");
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
		ArrayList<Retiro> listaRetiro = re.listado();
		if (listaRetiro.size() == 0) {
			return 10001;
		} else {
			int correlativo;
			correlativo = (listaRetiro.get(listaRetiro.size() - 1).getNumReti()) + 1;
			return correlativo;
		}
	}

	private int getNumRetiro() {
		int codigo = -1;
		try {
			codigo = Integer.parseInt(txtnumRetiro.getText());
		} catch (Exception e) {
			mensaje("Ingresar Numero de Retiro");
		}
		return codigo;
	}

	public int getNumMatricula() {
		int codA = -1;
		try {
			codA = Integer.parseInt(txtnumMatricula.getText());
		} catch (Exception e) {
			mensaje("Ingresar Numero de Matricula");
		}
		return codA;
	}

	// **********************************************************************

	// ***************ACCION DE LOS BOTONES***********************

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnExportar_Matriculas_pdf) {
			actionPerformedBtnExportar_Matriculas_pdf(arg0);
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
		txtnumRetiro.setText(getCodigoCorrelativo() + "");
		txtnumMatricula.requestFocus();
		btnModificar.setEnabled(false);
		btnEliminar.setEnabled(false);
	}

	protected void actionPerformedBtnAdicionar(ActionEvent arg0) {
		registrarRetiro();
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
	}

	protected void actionPerformedBtnEliminar(ActionEvent arg0) {
		int can = JOptionPane.showConfirmDialog(this, "¿Esta seguro de Eliminar?", "Confirmacion",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (can == JOptionPane.YES_OPTION) {
			eliminarRetiro();
		}
	}

	protected void actionPerformedBtnConsultar(ActionEvent arg0) {
		consultarMatricula();
	}

	protected void actionPerformedBtnSalir(ActionEvent arg0) {
		dispose();
	}

	protected void actionPerformedBtnExportar_Matriculas_pdf(ActionEvent arg0) {
		exportarRetiroPDF();
	}
}
