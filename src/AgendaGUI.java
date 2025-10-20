import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AgendaGUI extends JFrame {
    private Agenda agenda;
    private JTextField txtNombre, txtApellido, txtTelefono;
    private JTextArea areaContactos;

    public AgendaGUI() {
        // Inicializar agenda
        agenda = new Agenda();

        // Configuración general de la ventana
        setTitle("🦷 Agenda Dental - Clínica Sonrisa Saludable");
        setSize(500, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra la ventana
        setResizable(false);

        // Paleta de colores
        Color colorFondo = new Color(240, 248, 255);
        Color colorBoton = new Color(86, 170, 255);
        Color colorTexto = new Color(40, 40, 40);

        // Fuente general
        Font fuente = new Font("Segoe UI", Font.PLAIN, 14);

        // Panel principal
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(colorFondo);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Título
        JLabel titulo = new JLabel("Agenda Dental 🪥", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titulo.setForeground(new Color(0, 102, 204));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(titulo, gbc);

        // Campos
        gbc.gridwidth = 1;
        gbc.gridy++;
        panel.add(new JLabel("Nombre:"), gbc);
        txtNombre = new JTextField(15);
        gbc.gridx = 1;
        panel.add(txtNombre, gbc);

        gbc.gridx = 0; gbc.gridy++;
        panel.add(new JLabel("Apellido:"), gbc);
        txtApellido = new JTextField(15);
        gbc.gridx = 1;
        panel.add(txtApellido, gbc);

        gbc.gridx = 0; gbc.gridy++;
        panel.add(new JLabel("Teléfono:"), gbc);
        txtTelefono = new JTextField(15);
        gbc.gridx = 1;
        panel.add(txtTelefono, gbc);

        // Botones
        gbc.gridx = 0; gbc.gridy++;
        JButton btnAgregar = new JButton("Agregar Contacto");
        btnAgregar.setBackground(colorBoton);
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setFocusPainted(false);
        panel.add(btnAgregar, gbc);

        gbc.gridx = 1;
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBackground(new Color(255, 102, 102));
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFocusPainted(false);
        panel.add(btnEliminar, gbc);

        gbc.gridx = 0; gbc.gridy++;
        JButton btnListar = new JButton("Ver Agenda");
        btnListar.setBackground(new Color(0, 153, 102));
        btnListar.setForeground(Color.WHITE);
        btnListar.setFocusPainted(false);
        gbc.gridwidth = 2;
        panel.add(btnListar, gbc);

        // Área de texto para mostrar los contactos
        gbc.gridy++;
        areaContactos = new JTextArea(10, 30);
        areaContactos.setEditable(false);
        areaContactos.setFont(fuente);
        areaContactos.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180)));
        panel.add(new JScrollPane(areaContactos), gbc);

        // Acción de botones
        btnAgregar.addActionListener(this::agregarContacto);
        btnEliminar.addActionListener(this::eliminarContacto);
        btnListar.addActionListener(this::listarContactos);

        // Agregar panel a la ventana
        add(panel);
    }

    // Métodos de acción
    private void agregarContacto(ActionEvent e) {
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        String telefono = txtTelefono.getText().trim();

        if (nombre.isEmpty() || apellido.isEmpty() || telefono.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor completa todos los campos.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Contacto nuevo = new Contacto(nombre, apellido, telefono);
        agenda.agregarContacto(nuevo);
        JOptionPane.showMessageDialog(this, "Contacto agregado correctamente ✅", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        limpiarCampos();
    }

    private void eliminarContacto(ActionEvent e) {
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();

        if (nombre.isEmpty() || apellido.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingresa nombre y apellido.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Contacto eliminar = new Contacto(nombre, apellido, "");
        agenda.eliminarContacto(eliminar);
        JOptionPane.showMessageDialog(this, "Contacto eliminado correctamente 🗑️", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        limpiarCampos();
    }

    private void listarContactos(ActionEvent e) {
        areaContactos.setText("");
        if (agenda.getContactos().isEmpty()) {
            areaContactos.append("📭 No hay contactos registrados.\n");
        } else {
            areaContactos.append("📋 Lista de contactos:\n\n");
            for (Contacto c : agenda.getContactos()) {
                areaContactos.append(c + "\n");
            }
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtApellido.setText("");
        txtTelefono.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AgendaGUI().setVisible(true));
    }
}
