package vistas;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;

import controlador.AlumnoControlador;
import controlador.CursoControlador;
import controlador.MatriculaControlador;
import controlador.RetiroControlador;
import modelo.Alumno;
import modelo.Curso;
import modelo.Matricula;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class JD_alumnos_cursos extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	//
	public static DefaultTableModel modelo;
	//
	public static RetiroControlador re = new RetiroControlador();
	private JButton button;
	private JLabel lblCodAlumno;
	private JTextField txtCodAlumno;
	private JButton btnConsultarAlu;
	private JTextArea txtAlumnoSalida;
	private JScrollPane scrollPane;
	private JLabel label;
	private JTextField txtCodCurso;
	private JButton btnConsultarCur;
	private JTextArea txtCursoSalida;
	private JScrollPane scrollPane_1;
	//
	AlumnoControlador alu = new AlumnoControlador();
	CursoControlador cur = new CursoControlador();
	MatriculaControlador mat = new MatriculaControlador();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JD_alumnos_cursos dialog = new JD_alumnos_cursos();
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
	public JD_alumnos_cursos() {
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(JD_alumnos_cursos.class.getResource("/imagenes/aac.jpg")));
		setResizable(false);
		setTitle("CONSULTA |Alumnos y Cursos");
		setBounds(100, 100, 1000, 700);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(240, 255, 240));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		button = new JButton("SALIR");
		button.addActionListener(this);
		button.setIcon(new ImageIcon(JD_alumnos_cursos.class.getResource("/imagenes/salir.png")));
		button.setFont(new Font("Tahoma", Font.BOLD, 14));
		button.setBounds(779, 19, 205, 42);
		contentPanel.add(button);

		lblCodAlumno = new JLabel("Codigo de Alumno :");
		lblCodAlumno.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCodAlumno.setBounds(10, 29, 135, 25);
		contentPanel.add(lblCodAlumno);

		txtCodAlumno = new JTextField();
		txtCodAlumno.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtCodAlumno.setEnabled(false);
		txtCodAlumno.setColumns(10);
		txtCodAlumno.setBounds(155, 32, 200, 25);
		contentPanel.add(txtCodAlumno);

		btnConsultarAlu = new JButton("CONSULTAR");
		btnConsultarAlu.addActionListener(this);
		btnConsultarAlu.setIcon(new ImageIcon(JD_alumnos_cursos.class.getResource("/imagenes/buscar.png")));
		btnConsultarAlu.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnConsultarAlu.setEnabled(true);
		btnConsultarAlu.setBounds(394, 22, 205, 36);
		contentPanel.add(btnConsultarAlu);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 65, 974, 260);
		contentPanel.add(scrollPane);

		txtAlumnoSalida = new JTextArea();
		txtAlumnoSalida.setFont(new Font("Monospaced", Font.BOLD, 15));
		scrollPane.setViewportView(txtAlumnoSalida);

		label = new JLabel("Codigo de Curso :");
		label.setFont(new Font("Tahoma", Font.BOLD, 13));
		label.setBounds(10, 348, 135, 25);
		contentPanel.add(label);

		txtCodCurso = new JTextField();
		txtCodCurso.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtCodCurso.setEnabled(false);
		txtCodCurso.setColumns(10);
		txtCodCurso.setBounds(155, 351, 200, 25);
		contentPanel.add(txtCodCurso);

		btnConsultarCur = new JButton("CONSULTAR");
		btnConsultarCur.addActionListener(this);
		btnConsultarCur.setIcon(new ImageIcon(JD_alumnos_cursos.class.getResource("/imagenes/buscar.png")));
		btnConsultarCur.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnConsultarCur.setEnabled(true);
		btnConsultarCur.setBounds(394, 341, 205, 36);
		contentPanel.add(btnConsultarCur);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(11, 385, 972, 260);
		contentPanel.add(scrollPane_1);

		txtCursoSalida = new JTextArea();
		txtCursoSalida.setFont(new Font("Monospaced", Font.BOLD, 15));
		scrollPane_1.setViewportView(txtCursoSalida);

		//
		txtCodAlumno.setEnabled(true);
		txtAlumnoSalida.setEditable(false);
		txtCodCurso.setEnabled(true);
		txtCursoSalida.setEditable(false);
	}

	void consultarAlumno() {
		try {
			ArrayList<Alumno> al = alu.buscarXCodigo(LeerCodAlumno());
			ArrayList<Matricula> ma = mat.buscarXCodAlu(LeerCodAlumno());

			for (Alumno a : al) {

				txtAlumnoSalida.setText("");
				imprimirAlumno("Codigo    : " + a.getId());
				imprimirAlumno("Nombres   : " + a.getNombres());
				imprimirAlumno("Apellidos : " + a.getApellidos());
				imprimirAlumno("DNI       : " + a.getDni());
				imprimirAlumno("Edad      : " + a.getEdad());
				imprimirAlumno("Celular   : " + a.getCelular());
				imprimirAlumno("Estado    : " + a.getEstado());

				for (Matricula m : ma) {
					// obtener_el_codigo_de_curso
					// GET_CODALUMNO()_OBTIENE_EL_CODIGO_DEL_CURSO_PORQUE_ES_EL_UNICO_VALOR_DE_LA_CONSULTA_SQL
					int curso = m.getCodAlumno();
					ArrayList<Curso> cu = cur.buscarXCodigo(curso);
					for (Curso c : cu) {

						txtAlumnoSalida.setText("");
						imprimirAlumno("Codigo     : " + a.getId());
						imprimirAlumno("Nombres    : " + a.getNombres());
						imprimirAlumno("Apellidos  : " + a.getApellidos());
						imprimirAlumno("DNI        : " + a.getDni());
						imprimirAlumno("Edad       : " + a.getEdad());
						imprimirAlumno("Celular    : " + a.getCelular());
						imprimirAlumno("Estado     : " + a.getEstado());
						imprimirAlumno("========================================");
						imprimirAlumno("Codigo     : " + c.getId());
						imprimirAlumno("Asignatura : " + c.getAsignatura());
						imprimirAlumno("Ciclo      : " + c.getCiclo());
						imprimirAlumno("Creditos   : " + c.getCreditos());
						imprimirAlumno("Horas      : " + c.getHoras());

					}
				}
			}

		} catch (Exception e) {
			mensaje("Error de ingreso");
			txtCodAlumno.setText("");
			txtAlumnoSalida.setText("");
		}

	}

	void consultarCurso() {
		try {
			ArrayList<Curso> cu = cur.buscarXCodigo(LeerCodCurso());
			for (Curso c : cu) {
				txtCursoSalida.setText("");
				imprimirCurso("Codigo     : " + c.getId());
				imprimirCurso("Asignatura : " + c.getAsignatura());
				imprimirCurso("Ciclo      : " + c.getCiclo());
				imprimirCurso("Creditos   : " + c.getCreditos());
				imprimirCurso("Horas      : " + c.getHoras());
			}
		} catch (Exception e) {
			mensaje("Error de ingreso");
			txtCursoSalida.setText("");
			txtCodCurso.setText("");
		}
	}

	//
	void imprimirAlumno(String s) {
		txtAlumnoSalida.append(s + "\n");
	}

	void imprimirCurso(String s) {
		txtCursoSalida.append(s + "\n");
	}

	int LeerCodAlumno() {
		return Integer.parseInt(txtCodAlumno.getText());
	}

	int LeerCodCurso() {
		return Integer.parseInt(txtCodCurso.getText());
	}

	void mensaje(String m) {
		JOptionPane.showMessageDialog(this, m);
	}

	// ************************************************************

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == button) {
			actionPerformedButton(arg0);
		}
		if (arg0.getSource() == btnConsultarCur) {
			actionPerformedBtnConsultarCur(arg0);
		}
		if (arg0.getSource() == btnConsultarAlu) {
			actionPerformedBtnConsultarAlu(arg0);
		}
	}

	protected void actionPerformedBtnConsultarAlu(ActionEvent arg0) {
		consultarAlumno();
	}

	protected void actionPerformedBtnConsultarCur(ActionEvent arg0) {
		consultarCurso();
	}

	protected void actionPerformedButton(ActionEvent arg0) {
		dispose();
	}
}
