import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AgendaGUI extends JFrame {
    private Agenda agenda;
    private JTextField txtNombre, txtApellido, txtTelefono;
    private JTextPane areaContactos;
    private JLabel lblEspacios;
    private JPanel panelPrincipal;
    private CardLayout cardLayout;

    public AgendaGUI() {
        agenda = new Agenda();

        // Configuración general
        setTitle("🦷 Agenda Dental - Clínica Sonrisa Saludable");
        setSize(600, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Paleta dental
        Color fondoPrincipal = new Color(240, 252, 255);
        Color colorPrimario = new Color(0, 153, 153);
        Color colorBoton = new Color(0, 204, 204);
        Color colorEliminar = new Color(255, 102, 102);
        Color colorBuscar = new Color(0, 153, 255);

        Font fuenteGeneral = new Font("Segoe UI", Font.PLAIN, 15);
        Font fuenteTitulo = new Font("Segoe UI Semibold", Font.BOLD, 26);

        // CardLayout para cambiar vistas
        cardLayout = new CardLayout();
        panelPrincipal = new JPanel(cardLayout);
        panelPrincipal.setBackground(fondoPrincipal);

        // ====== PANTALLA MENÚ ======
        JPanel panelMenu = new JPanel(new GridBagLayout());
        panelMenu.setBackground(fondoPrincipal);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        // Ícono dental
        JLabel icono = new JLabel("🦷", SwingConstants.CENTER);
        icono.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 50));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panelMenu.add(icono, gbc);

        // Título
        JLabel titulo = new JLabel("Agenda Dental", SwingConstants.CENTER);
        titulo.setFont(fuenteTitulo);
        titulo.setForeground(colorPrimario);
        gbc.gridy++;
        panelMenu.add(titulo, gbc);

        // Línea separadora
        gbc.gridy++;
        JSeparator separator = new JSeparator();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelMenu.add(separator, gbc);

        // Campos
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridy++;
        gbc.gridx = 0;
        panelMenu.add(new JLabel("Nombre:"), gbc);
        txtNombre = new JTextField(15);
        txtNombre.setFont(fuenteGeneral);
        gbc.gridx = 1;
        panelMenu.add(txtNombre, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        panelMenu.add(new JLabel("Apellido:"), gbc);
        txtApellido = new JTextField(15);
        txtApellido.setFont(fuenteGeneral);
        gbc.gridx = 1;
        panelMenu.add(txtApellido, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        panelMenu.add(new JLabel("Teléfono:"), gbc);
        txtTelefono = new JTextField(15);
        txtTelefono.setFont(fuenteGeneral);
        gbc.gridx = 1;
        panelMenu.add(txtTelefono, gbc);

        // ====== BOTONES ======
        gbc.gridy++;
        gbc.gridx = 0;
        JButton btnAgregar = crearBoton("Agregar Contacto", colorBoton, null);
        panelMenu.add(btnAgregar, gbc);

        gbc.gridx = 1;
        JButton btnEliminar = crearBoton("Eliminar Contacto", colorEliminar, null);
        panelMenu.add(btnEliminar, gbc);

        gbc.gridy++;
        gbc.gridx = 0; gbc.gridwidth = 2;
        JButton btnBuscar = crearBoton("Buscar Contacto", colorBuscar, null);
        panelMenu.add(btnBuscar, gbc);

        gbc.gridy++;
        JButton btnVerAgenda = crearBoton("Ver Agenda Completa", colorPrimario, null);
        panelMenu.add(btnVerAgenda, gbc);

        // Etiqueta de espacios libres
        gbc.gridy++;
        lblEspacios = new JLabel("Espacios disponibles: " + agenda.espaciosLibres(), SwingConstants.CENTER);
        lblEspacios.setFont(new Font("Segoe UI", Font.ITALIC, 13));
        lblEspacios.setForeground(new Color(70, 70, 70));
        panelMenu.add(lblEspacios, gbc);

        // Pie de página
        gbc.gridy++;
        JLabel pie = new JLabel("Clínica Sonrisa Saludable © 2025", SwingConstants.CENTER);
        pie.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        pie.setForeground(new Color(90, 90, 90));
        panelMenu.add(pie, gbc);

        // ====== PANEL AGENDA ======
        JPanel panelAgenda = new JPanel(new BorderLayout());
        panelAgenda.setBackground(fondoPrincipal);

        areaContactos = new JTextPane();
        areaContactos.setContentType("text/html");
        areaContactos.setEditable(false);
        areaContactos.setBackground(Color.WHITE);
        areaContactos.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
        areaContactos.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JScrollPane scroll = new JScrollPane(areaContactos);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(200, 220, 220), 1));
        scroll.setPreferredSize(new Dimension(550, 400));

        panelAgenda.add(scroll, BorderLayout.CENTER);

        JButton btnVolver = crearBoton("⬅️ Volver al Menú", colorPrimario, null);
        btnVolver.setFont(new Font("Segoe UI", Font.BOLD, 14));
        panelAgenda.add(btnVolver, BorderLayout.SOUTH);

        // Agregar paneles al CardLayout
        panelPrincipal.add(panelMenu, "MENU");
        panelPrincipal.add(panelAgenda, "AGENDA");

        // ====== EVENTOS ======
        btnAgregar.addActionListener(this::agregarContacto);
        btnEliminar.addActionListener(this::eliminarContacto);
        btnBuscar.addActionListener(this::buscarContacto);
        btnVerAgenda.addActionListener(e -> {
            mostrarContactos();
            cardLayout.show(panelPrincipal, "AGENDA");
        });
        btnVolver.addActionListener(e -> cardLayout.show(panelPrincipal, "MENU"));

        add(panelPrincipal);
    }

    // ================= MÉTODOS =================

    private JButton crearBoton(String texto, Color color, String rutaIcono) {
        JButton boton = new JButton(texto);
        boton.setBackground(color);
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(color.darker(), 1),
                BorderFactory.createEmptyBorder(8, 15, 8, 15)
        ));
        if (rutaIcono != null) {
            boton.setIcon(new ImageIcon(getClass().getResource(rutaIcono)));
        }
        return boton;
    }

    // Agregar contacto
    private void agregarContacto(ActionEvent e) {
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        String telefono = txtTelefono.getText().trim();

        if (nombre.isEmpty() || apellido.isEmpty() || telefono.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completa todos los campos antes de agregar.", "Campos vacíos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Contacto nuevo = new Contacto(nombre, apellido, telefono);

        if (agenda.existeContacto(nuevo)) {
            JOptionPane.showMessageDialog(this, "El contacto ya existe ❗", "Duplicado", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (agenda.agendaLlena()) {
            JOptionPane.showMessageDialog(this, "La agenda está llena 🛑", "Sin espacio", JOptionPane.ERROR_MESSAGE);
            return;
        }

        agenda.agregarContacto(nuevo);
        JOptionPane.showMessageDialog(this, "Contacto agregado correctamente ✅", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        actualizarEspacios();
        limpiarCampos();
    }

    // Eliminar contacto con menú desplegable
    private void eliminarContacto(ActionEvent e) {
        if (agenda.getContactos().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay contactos para eliminar.", "Agenda vacía", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String[] nombres = agenda.getContactos().stream()
                .map(c -> c.getNombre() + " " + c.getApellido())
                .toArray(String[]::new);

        String seleccionado = (String) JOptionPane.showInputDialog(
                this,
                "Selecciona el contacto que deseas eliminar:",
                "🗑️ Eliminar Contacto",
                JOptionPane.PLAIN_MESSAGE,
                null,
                nombres,
                nombres[0]
        );

        if (seleccionado != null) {
            Contacto eliminar = null;
            for (Contacto c : agenda.getContactos()) {
                String nombreCompleto = c.getNombre() + " " + c.getApellido();
                if (nombreCompleto.equals(seleccionado)) {
                    eliminar = c;
                    break;
                }
            }

            if (eliminar != null) {
                agenda.eliminarContacto(eliminar);
                JOptionPane.showMessageDialog(this, "Contacto eliminado correctamente 🗑️", "Eliminado", JOptionPane.INFORMATION_MESSAGE);
                actualizarEspacios();
            }
        }
    }

    // Buscar contacto — muestra todos los que tengan el mismo nombre
    private void buscarContacto(ActionEvent e) {
        String nombre = JOptionPane.showInputDialog(this, "Ingrese el nombre del contacto a buscar:");
        if (nombre == null || nombre.trim().isEmpty()) return;

        StringBuilder resultados = new StringBuilder();
        for (Contacto c : agenda.getContactos()) {
            if (c.getNombre().equalsIgnoreCase(nombre)) {
                resultados.append("👤 ")
                        .append(c.getNombre()).append(" ").append(c.getApellido())
                        .append(" — 📞 ").append(c.getTelefono()).append("\n");
            }
        }

        if (resultados.length() > 0) {
            JOptionPane.showMessageDialog(this, resultados.toString(), "Resultados de búsqueda", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "❌ No se encontraron contactos con ese nombre.", "Sin resultados", JOptionPane.WARNING_MESSAGE);
        }
    }

    // Mostrar contactos
    private void mostrarContactos() {
        StringBuilder html = new StringBuilder("<html><body style='font-family:Segoe UI; font-size:13px; color:#333;'>");

        if (agenda.getContactos().isEmpty()) {
            html.append("<p>📭 <b>No hay contactos registrados.</b></p>");
        } else {
            html.append("<h3 style='color:#007b7b;'>📋 Lista de contactos:</h3>");
            for (Contacto c : agenda.getContactos()) {
                html.append("<p style='margin-bottom:8px;'>")
                        .append("• <b style='color:#006666;'>Contacto:</b> ")
                        .append(c.getNombre()).append(" ").append(c.getApellido())
                        .append("&nbsp;&nbsp;&nbsp;")
                        .append("<b style='color:#004488;'>Teléfono:</b> ")
                        .append(c.getTelefono())
                        .append("</p>");
            }
        }

        html.append("</body></html>");
        areaContactos.setText(html.toString());
        areaContactos.setCaretPosition(0);
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtApellido.setText("");
        txtTelefono.setText("");
    }

    private void actualizarEspacios() {
        lblEspacios.setText("Espacios disponibles: " + agenda.espaciosLibres());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AgendaGUI().setVisible(true));
    }
}
