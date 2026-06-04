package vista;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import entidad.Concurso;
import model.ConcursoModel;
import util.ValidateUtil;

import java.awt.event.*;
import java.time.LocalDate;
import java.util.List;

public class FrmConsultaConcurso extends JFrame implements ActionListener, KeyListener {

    private JPanel contentPane;

    private JTextField txtNombre;
    private JTextField txtDesde;
    private JTextField txtHasta;

    private JTable table;

    private JButton btnFiltrar;
    private JButton btnCancelar;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                FrmConsultaConcurso frame = new FrmConsultaConcurso();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public FrmConsultaConcurso() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 844, 462);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("Consulta sobre Concurso");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(18f));
        lblTitulo.setBounds(10, 11, 808, 50);
        contentPane.add(lblTitulo);

        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setBounds(34, 72, 75, 14);
        contentPane.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(119, 69, 150, 20);
        contentPane.add(txtNombre);

        JLabel lblDesde = new JLabel("Fecha Concurso (desde)");
        lblDesde.setBounds(34, 113, 190, 14);
        contentPane.add(lblDesde);

        txtDesde = new JTextField();
        txtDesde.setBounds(286, 110, 115, 20);
        contentPane.add(txtDesde);

        JLabel lblHasta = new JLabel("(hasta)");
        lblHasta.setBounds(464, 113, 46, 14);
        contentPane.add(lblHasta);

        txtHasta = new JTextField();
        txtHasta.setBounds(539, 110, 132, 20);
        contentPane.add(txtHasta);

        btnFiltrar = new JButton("Filtrar");
        btnFiltrar.addActionListener(this);
        btnFiltrar.setBounds(308, 155, 89, 23);
        contentPane.add(btnFiltrar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(this);
        btnCancelar.setBounds(407, 155, 89, 23);
        contentPane.add(btnCancelar);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(34, 189, 765, 206);
        contentPane.add(scrollPane);

        table = new JTable();
        table.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {
                "Código", "Nombre", "Fecha Inicio", "Fecha Fin"
            }
        ));

        scrollPane.setViewportView(table);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnFiltrar) {
            filtrar();
        }

        if (e.getSource() == btnCancelar) {
            limpiar();
        }
    }

    private void filtrar() {

        String nombre = txtNombre.getText().trim();
        String desde = txtDesde.getText().trim();
        String hasta = txtHasta.getText().trim();

        // VALIDACIÓN FECHAS
        if (!desde.isEmpty() && !desde.matches(ValidateUtil.DATE_YYYY_MM_DD)) {
            JOptionPane.showMessageDialog(this, "Fecha desde inválida");
            return;
        }

        if (!hasta.isEmpty() && !hasta.matches(ValidateUtil.DATE_YYYY_MM_DD)) {
            JOptionPane.showMessageDialog(this, "Fecha hasta inválida");
            return;
        }

        if (!desde.isEmpty() && !hasta.isEmpty()) {
            LocalDate f1 = LocalDate.parse(desde);
            LocalDate f2 = LocalDate.parse(hasta);

            if (f1.isAfter(f2)) {
                JOptionPane.showMessageDialog(this, "Desde no puede ser mayor que Hasta");
                return;
            }
        }

        LocalDate fechaDesde = desde.isEmpty() ? LocalDate.of(1900, 1, 1) : LocalDate.parse(desde);
        LocalDate fechaHasta = hasta.isEmpty() ? LocalDate.of(9999, 12, 31) : LocalDate.parse(hasta);

        ConcursoModel model = new ConcursoModel();

        // ✔ CORRECTO SEGÚN TU MODELO (4 parámetros)
        List<Concurso> lista = model.listaConcurso(nombre, "", fechaDesde, fechaHasta);

        DefaultTableModel dt = (DefaultTableModel) table.getModel();
        dt.setRowCount(0);

        for (Concurso c : lista) {
            Object[] row = {
                c.getIdConcurso(),
                c.getNombre(),
                c.getFechaInicio(),
                c.getFechaFin()
            };
            dt.addRow(row);
        }
    }

    private void limpiar() {

        txtNombre.setText("");
        txtDesde.setText("");
        txtHasta.setText("");

        DefaultTableModel dt = (DefaultTableModel) table.getModel();
        dt.setRowCount(0);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    protected void do_txtDni_keyReleased(KeyEvent e) {

    }
}