package vistas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Font;
import javax.swing.ImageIcon;

public class JF_Principal extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnMantenimiento;
	private JMenuItem mntmAlumno;
	private JMenuItem mntmCurso;
	private JMenu mnArchivo;
	private JMenu mnRegistro;
	private JMenu mnConsulta;
	private JMenu mnReporte;
	private JMenuItem mntmSalir;
	private JMenuItem mntmMatricula;
	private JMenuItem mntmRetiro;
	private JMenuItem mntmAlumnosYCursos;
	private JMenuItem mntmMatriculasYRetiros;
	private JMenuItem mntmAlumnosConMatricula;
	private JMenuItem mntmAlumnosConMatriculas;
	private JMenuItem mntmAlumnosMatriculadosPor;
	private JLabel lblPrincipal;

	// para_la_imagen_de_fonfo_***************************************
	FondoPanel fondo = new FondoPanel();

	class FondoPanel extends JPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Image imagen;

		public void paint(Graphics g) {
			imagen = new ImageIcon(getClass().getResource("/imagenes/menu.jpg")).getImage();

			g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);

			setOpaque(false);

			super.paint(g);
		}
	}
	//***************************************************************
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JF_Principal frame = new JF_Principal();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JF_Principal() {
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("IE ANDRES AVELINO CACERES 122");
		setIconImage(Toolkit.getDefaultToolkit().getImage(JF_Principal.class.getResource("/imagenes/aac.jpg")));
		setBounds(100, 100, 1500, 900);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnArchivo = new JMenu("ARCHIVO");
		mnArchivo.setIcon(new ImageIcon(JF_Principal.class.getResource("/imagenes/archivo_barra.png")));
		mnArchivo.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 16));
		menuBar.add(mnArchivo);

		mntmSalir = new JMenuItem("SALIR");
		mntmSalir.setIcon(new ImageIcon(JF_Principal.class.getResource("/imagenes/salir_exti.png")));
		mntmSalir.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mntmSalir.addActionListener(this);
		mnArchivo.add(mntmSalir);

		mnMantenimiento = new JMenu("MANTENIMIENTO");
		mnMantenimiento.setIcon(new ImageIcon(JF_Principal.class.getResource("/imagenes/mantenimiento_1.png")));
		mnMantenimiento.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 16));
		menuBar.add(mnMantenimiento);

		mntmAlumno = new JMenuItem("ALUMNO");
		mntmAlumno.setIcon(new ImageIcon(JF_Principal.class.getResource("/imagenes/alumno.png")));
		mntmAlumno.addActionListener(this);
		mntmAlumno.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mnMantenimiento.add(mntmAlumno);

		mntmCurso = new JMenuItem("CURSO");
		mntmCurso.setIcon(new ImageIcon(JF_Principal.class.getResource("/imagenes/curso.png")));
		mntmCurso.addActionListener(this);
		mntmCurso.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mnMantenimiento.add(mntmCurso);

		mnRegistro = new JMenu("REGISTRO");
		mnRegistro.setIcon(new ImageIcon(JF_Principal.class.getResource("/imagenes/registro.png")));
		mnRegistro.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 16));
		menuBar.add(mnRegistro);

		mntmMatricula = new JMenuItem("MATRICULA");
		mntmMatricula.setIcon(new ImageIcon(JF_Principal.class.getResource("/imagenes/matricula.png")));
		mntmMatricula.addActionListener(this);
		mntmMatricula.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mnRegistro.add(mntmMatricula);

		mntmRetiro = new JMenuItem("RETIRO");
		mntmRetiro.setIcon(new ImageIcon(JF_Principal.class.getResource("/imagenes/retiro.png")));
		mntmRetiro.addActionListener(this);
		mntmRetiro.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mnRegistro.add(mntmRetiro);

		mnConsulta = new JMenu("CONSULTA");
		mnConsulta.setIcon(new ImageIcon(JF_Principal.class.getResource("/imagenes/consulta_barra.png")));
		mnConsulta.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 16));
		menuBar.add(mnConsulta);

		mntmAlumnosYCursos = new JMenuItem("ALUMNOS Y CURSOS");
		mntmAlumnosYCursos.setIcon(new ImageIcon(JF_Principal.class.getResource("/imagenes/alu_cur.png")));
		mntmAlumnosYCursos.addActionListener(this);
		mntmAlumnosYCursos.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mnConsulta.add(mntmAlumnosYCursos);

		mntmMatriculasYRetiros = new JMenuItem("MATRICULAS Y RETIROS");
		mntmMatriculasYRetiros.setIcon(new ImageIcon(JF_Principal.class.getResource("/imagenes/ma_re.png")));
		mntmMatriculasYRetiros.addActionListener(this);
		mntmMatriculasYRetiros.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mnConsulta.add(mntmMatriculasYRetiros);

		mnReporte = new JMenu("REPORTE");
		mnReporte.setIcon(new ImageIcon(JF_Principal.class.getResource("/imagenes/reporte.png")));
		mnReporte.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 16));
		menuBar.add(mnReporte);

		mntmAlumnosConMatricula = new JMenuItem("ALUMNOS CON MATRICULA VIGENTE");
		mntmAlumnosConMatricula.setIcon(new ImageIcon(JF_Principal.class.getResource("/imagenes/buscar_general.png")));
		mntmAlumnosConMatricula.addActionListener(this);
		mntmAlumnosConMatricula.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mnReporte.add(mntmAlumnosConMatricula);

		mntmAlumnosConMatriculas = new JMenuItem("ALUMNOS CON MATRICULAS PENDIENTE");
		mntmAlumnosConMatriculas.setIcon(new ImageIcon(JF_Principal.class.getResource("/imagenes/buscar_general.png")));
		mntmAlumnosConMatriculas.addActionListener(this);
		mntmAlumnosConMatriculas.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mnReporte.add(mntmAlumnosConMatriculas);

		mntmAlumnosMatriculadosPor = new JMenuItem("ALUMNOS MATRICULADOS POR CURSO");
		mntmAlumnosMatriculadosPor.setIcon(new ImageIcon(JF_Principal.class.getResource("/imagenes/buscar_general.png")));
		mntmAlumnosMatriculadosPor.addActionListener(this);
		mntmAlumnosMatriculadosPor.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mnReporte.add(mntmAlumnosMatriculadosPor);
		contentPane = new JPanel();
		contentPane.setVisible(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblPrincipal = new JLabel("");
		lblPrincipal.setBounds(0, 0, 1494, 847);
		contentPane.add(lblPrincipal);
		//
		this.setContentPane(fondo);
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == mntmAlumnosMatriculadosPor) {
			actionPerformedMntmAlumnosMatriculadosPor(arg0);
		}
		if (arg0.getSource() == mntmAlumnosConMatriculas) {
			actionPerformedMntmAlumnosConMatriculas(arg0);
		}
		if (arg0.getSource() == mntmAlumnosConMatricula) {
			actionPerformedMntmAlumnosConMatricula(arg0);
		}
		if (arg0.getSource() == mntmMatriculasYRetiros) {
			actionPerformedMntmMatriculasYRetiros(arg0);
		}
		if (arg0.getSource() == mntmAlumnosYCursos) {
			actionPerformedMntmAlumnosYCursos(arg0);
		}
		if (arg0.getSource() == mntmRetiro) {
			actionPerformedMntmRetiro(arg0);
		}
		if (arg0.getSource() == mntmMatricula) {
			actionPerformedMntmMatricula(arg0);
		}
		if (arg0.getSource() == mntmCurso) {
			actionPerformedMntmCurso(arg0);
		}
		if (arg0.getSource() == mntmAlumno) {
			actionPerformedMntmAlumno(arg0);
		}
		if (arg0.getSource() == mntmSalir) {
			actionPerformedMntmSalir(arg0);
		}
	}

	protected void actionPerformedMntmSalir(ActionEvent arg0) {
		int sa = JOptionPane.showConfirmDialog(this, "¿Desea salir del programa?", "CONFIRMACION",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (sa == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	protected void actionPerformedMntmAlumno(ActionEvent arg0) {
		JD_alumno a = new JD_alumno();
		a.setLocationRelativeTo(null);
		a.setVisible(true);
	}

	protected void actionPerformedMntmCurso(ActionEvent arg0) {
		JD_curso a = new JD_curso();
		a.setLocationRelativeTo(null);
		a.setVisible(true);
	}

	protected void actionPerformedMntmMatricula(ActionEvent arg0) {
		JD_matricula a = new JD_matricula();
		a.setLocationRelativeTo(null);
		a.setVisible(true);
	}

	protected void actionPerformedMntmRetiro(ActionEvent arg0) {
		JD_retiro a = new JD_retiro();
		a.setLocationRelativeTo(null);
		a.setVisible(true);
	}

	protected void actionPerformedMntmAlumnosYCursos(ActionEvent arg0) {
		JD_alumnos_cursos ac = new JD_alumnos_cursos();
		ac.setLocationRelativeTo(null);
		ac.setVisible(true);
	}

	protected void actionPerformedMntmMatriculasYRetiros(ActionEvent arg0) {
		JD_matriculas_retiros mr = new JD_matriculas_retiros();
		mr.setLocationRelativeTo(null);
		mr.setVisible(true);
	}

	protected void actionPerformedMntmAlumnosConMatricula(ActionEvent arg0) {
		JD_alumno_matricula_pendiente amp = new JD_alumno_matricula_pendiente();
		amp.setLocationRelativeTo(null);
		amp.setVisible(true);
	}

	protected void actionPerformedMntmAlumnosConMatriculas(ActionEvent arg0) {
		JD_alumno_matricula_vigente amv = new JD_alumno_matricula_vigente();
		amv.setLocationRelativeTo(null);
		amv.setVisible(true);
	}

	protected void actionPerformedMntmAlumnosMatriculadosPor(ActionEvent arg0) {
		JD_alumno_matriculado_x_curso mc = new JD_alumno_matriculado_x_curso();
		mc.setLocationRelativeTo(null);
		mc.setVisible(true);
	}
}
