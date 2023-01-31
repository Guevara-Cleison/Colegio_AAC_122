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
import controlador.CursoControlador;
import controlador.MatriculaControlador;
import modelo.Alumno;
import modelo.Curso;
import modelo.Matricula;

import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class JD_alumno_matriculado_x_curso extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JDesktopPane desktopPane;
	private JLabel lblname;

	private JButton btnConsultar;
	private JTable tblAlumnos;
	private JScrollPane scrollPane;
	private JLabel lblCan;
	private JLabel lblRegistros;
	private JButton btnSalir;
	//
	public static DefaultTableModel modelo;
	//
	public static AlumnoControlador alu = new AlumnoControlador();
	public static MatriculaControlador mat = new MatriculaControlador();
	public static CursoControlador cur = new CursoControlador();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JD_alumno_matriculado_x_curso dialog = new JD_alumno_matriculado_x_curso();
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
	public JD_alumno_matriculado_x_curso() {
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(JD_alumno_matriculado_x_curso.class.getResource("/imagenes/aac.jpg")));
		setResizable(false);
		setTitle("ALUMNO MATRICULADO POR CURSO");
		setBounds(100, 100, 1000, 700);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(240, 255, 240));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		desktopPane = new JDesktopPane();
		desktopPane.setToolTipText("");
		desktopPane.setBackground(new Color(240, 255, 240));
		desktopPane.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "ALUMNO MATRICULADOS",
				TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		desktopPane.setBounds(10, 11, 670, 81);
		contentPanel.add(desktopPane);
		desktopPane.setLayout(null);

		lblname = new JLabel("Mostrar Alumnos en Cursos Distintos");
		lblname.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblname.setBounds(10, 28, 296, 35);
		desktopPane.add(lblname);

		btnConsultar = new JButton("CONSULTAR");
		btnConsultar.setBounds(455, 23, 205, 42);
		desktopPane.add(btnConsultar);
		btnConsultar.setIcon(new ImageIcon(JD_alumno_matriculado_x_curso.class.getResource("/imagenes/buscar.png")));
		btnConsultar.addActionListener(this);
		btnConsultar.setFont(new Font("Tahoma", Font.BOLD, 14));

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 212, 974, 406);
		contentPanel.add(scrollPane);

		tblAlumnos = new JTable();
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
		btnSalir.setIcon(new ImageIcon(JD_alumno_matriculado_x_curso.class.getResource("/imagenes/salir.png")));
		btnSalir.addActionListener(this);
		btnSalir.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSalir.setBounds(734, 37, 205, 42);
		contentPanel.add(btnSalir);

		//
		EncabezadoTablaAlumnos();
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
		modelo.addColumn("Codigo Curso");
		modelo.addColumn("Nombre de Curso");
		modelo.addColumn("Nombres");
		modelo.addColumn("Apellidos");
		tblAlumnos.setModel(modelo);
		// para_que_no_se_muevan_los_encabezados
		tblAlumnos.getTableHeader().setReorderingAllowed(false);
		tblAlumnos.getTableHeader().setResizingAllowed(false);
	}

	public void cargarDatos() {
		// limpiar_el_modelo
		modelo.setRowCount(0);
		ArrayList<Matricula> listaMatrictla = mat.listado();
		for (Matricula m : listaMatrictla) {
			int alumno = m.getCodAlumno();
			ArrayList<Alumno> listaAlumno = alu.buscarXCodigo(alumno);
			for (Alumno a : listaAlumno) {
				int curso = m.getCodCurso();
				ArrayList<Curso> listaCurso = cur.buscarXCodigo(curso);
				for (Curso c : listaCurso) {
					Object fila[] = { m.getCodCurso(), c.getAsignatura(), a.getNombres(), a.getApellidos() };
					// enviar_el_objeto_FILA_al_modelo
					modelo.addRow(fila);
					// mostrar_cantidad_de_registros
					lblCan.setText("" + modelo.getRowCount());
				}

			}
		}

	}

	void mensaje(String m) {
		JOptionPane.showMessageDialog(this, m);
	}

	// **********************************************************************

	// ***************ACCION DE LOS BOTONES***********************

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnConsultar) {
			actionPerformedBtnConsultar(arg0);
		}
		if (arg0.getSource() == btnSalir) {
			actionPerformedBtnSalir(arg0);
		}
	}

	protected void actionPerformedBtnConsultar(ActionEvent arg0) {
		cargarDatos();
		if (modelo.getRowCount() == 0) {
			mensaje("No existen alumnos matriculados");
		}
	}

	protected void actionPerformedBtnSalir(ActionEvent arg0) {
		dispose();
	}
}
