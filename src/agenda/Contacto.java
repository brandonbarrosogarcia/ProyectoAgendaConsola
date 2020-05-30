
package agenda;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Brandon Barroso García
 */
public class Contacto implements Serializable {
    
    private final int id;
    private static int id_counter;
    private String dni;
    private String nombre;
    private String apellidos;
    private String fechaNacimiento;
    private int estrellas;
    private String email;
    private String telefono;

    /**
     * Constructor de contacto
     * @param dni
     * @param nombre
     * @param apellidos
     * @param fechaNacimiento
     * @param email
     * @param telefono 
     */
    public Contacto(String dni, String nombre, String apellidos, String fechaNacimiento, String email, String telefono) {
        this.id = id_counter++;
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.email = email;
        this.telefono = telefono;
    }
    
    public Contacto(String dni, String nombre){
        this.id = id_counter++;
        this.dni = dni;
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(int estrellas) {
        this.estrellas = estrellas;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getId() {
        return id;
    }
    
    @Override
    public String toString() {
        return "Contacto{" + "dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos + ", fechaNacimiento=" + fechaNacimiento + ", estrellas=" + estrellas + ", email=" + email + ", telefono=" + telefono + '}';
    }
    
    

} //fin clase
