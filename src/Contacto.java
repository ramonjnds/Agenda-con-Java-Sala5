//Menu para configurar las citas
//Agregar y eliminar consultorios -- agregar citas para los consultorios
//Agregar y eliminar doctores
public class Contacto {
    private String nombre;
    private String telefono;
    private String apellido;

    //Constructor
    public Contacto(String nombre, String apellido, String telefono){
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
    }

    // Getters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Contacto contacto = (Contacto) obj;
        return nombre.equalsIgnoreCase(contacto.nombre) && apellido.equalsIgnoreCase(contacto.apellido);
    }


    public int hashCode() {
        return (nombre + apellido).toLowerCase().hashCode();
    }

    public String toString() {
        return "Contacto: " + nombre + " " + apellido + " Telefono: " + telefono;
    }
}
