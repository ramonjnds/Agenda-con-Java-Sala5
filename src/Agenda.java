import java.util.ArrayList;

// Clase de contactos
public class Agenda {
    private ArrayList<Contacto> contactos;
    private int espacios;
    /**
     *  Contructor - capacidad por defecto
     */
    public Agenda() {
        this(10);
    }
    /**
     *
     * @param espacios
     * Constructor -- capacidad definida
     */
    public Agenda(int espacios){
        this.espacios = espacios;
        this.contactos = new ArrayList<>();
    }
    /**
     *
     * @param con
     * Agregar contacto
     */
    public void agregarContacto(Contacto con) {
        if (agendaLlena()) {
            System.out.println("No se puede agregar mas contactos :(");
        } else if (existeContacto(con)) {
            System.out.println("El contacto ya existe D:");
        }else{
            contactos.add(con);
            System.out.println("El contacto se agrego exitosamente ✅");
        }
    }

    /**
     *
     * @return
     * Si la agenda esta llena
     */
    public boolean agendaLlena(){
        return contactos.size() >= espacios;
    }

    /**
     *
     * @param con
     * @return
     * Verificar si existe contacto
     */
    public boolean existeContacto(Contacto con){
        return contactos.contains(con);
    }



    /**
     * Se utiliza para listar los contactos existentes
     */
    public void listarContacto() {
        if (contactos.isEmpty()) {
            System.out.println("No hay contactos registrados");
        } else {
            System.out.println("La lista de contactos es: ");
            for (Contacto con : contactos){
                    System.out.println(con);
            }
        }
    }
    /**
     *
     * @param nombre
     * Busca al contacto por nombre
     */
    public void buscaContacto(String nombre){
        for (Contacto con : contactos){
            if (con.getNombre().equalsIgnoreCase((nombre))) {
                System.out.println("Contacto : " + con);
                return;

            }
        }
        System.out.println("No se encontro el contacto ❌");
    }
    /**
     *
     * @param con
     * Elimina contacto
     */
    public void eliminarContacto(Contacto con){
        if (contactos.remove(con)){
            System.out.println("Contacto eliminado ");
        }else {
            System.out.println("No existe el contacto");
        }
    }
    /**
     *
     * @return
     * Saber cuantos espacios quedan disponibles
     */
    public int espaciosLibres() {
        return espacios - contactos.size();
    }
}
