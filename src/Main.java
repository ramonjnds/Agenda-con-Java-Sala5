import java.util.Scanner;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AgendaGUI().setVisible(true));
        Scanner sc = new Scanner(System.in);
        Agenda agenda = new Agenda();
        int opcion;

        do {
        System.out.println("Agenda de contactos dentales 🦷🦷🦷");
        System.out.println("1. Agregar contacto");
        System.out.println("2. Ver contactos");
        System.out.println("3. Buscar contacto por nombre");
        System.out.println("4. Eliminar contacto por nombre");
        System.out.println("5. Ver si la agenda esta llena");
        System.out.println("6. Ver espacios desponibles");
        System.out.println("7. Salir");
        System.out.println("Seleccione una opcion: ");
        opcion = sc.nextInt();
        sc.nextLine();

        switch (opcion){
            case 1 -> {
                System.out.println("Nombre del contacto: ");
                String nombre = sc.nextLine();
                System.out.println("Apellido del contacto: ");
                String apellido = sc.nextLine();
                System.out.println("Telefono del contacto: ");
                String telefono = sc.nextLine();
                Contacto nuevo = new Contacto(nombre,apellido,telefono);
                agenda.agregarContacto(nuevo);
            }
            case 2 -> agenda.listarContacto();
            case 3 -> {
                System.out.println("Ingrese el nombre del contacto: ");
                String buscar = sc.nextLine();
                agenda.buscaContacto(buscar);
            }
            case 4 -> {
                System.out.println("Nombre del contacto: ");
                String nombreEliminar = sc.nextLine();
                System.out.println("Apellido del contacto: ");
                String apellidoEliminar = sc.nextLine();
                Contacto eliminar = new Contacto(nombreEliminar, apellidoEliminar, "");
                agenda.eliminarContacto(eliminar);
            }
            case 5 -> {
                if (agenda.agendaLlena()) {
                    System.out.println("Agenda llena ");
                }else {
                    System.out.println("Hay espacios");
                }
            }
            case 6 -> System.out.println("Espacios disponibles: " + agenda.espaciosLibres());
            case 7 -> System.out.println("Cerrando ...");
            default -> System.out.println("Opcion invlida, intenta de nuevo");
        }
        }while (opcion != 7);
        sc.close();
    }
}
