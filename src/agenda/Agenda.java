package agenda;

import coloresConsola.Colores;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import utilidadesFicheros.Fichero;

/**
 *
 * @author Brandon Barroso García
 */
public class Agenda {

    //Arraylist con los contactos
    private ArrayList<Contacto> contactos = new ArrayList<>();
    private String ruta_fichero_agenda;

    /**
     * Constructor Agenda
     *
     * @param ruta_agenda String con la ruta del fichero con la agenda
     */
    public Agenda(String ruta_agenda) {
        if (comprobarFicheroAlmacenamiento(ruta_agenda)) {
            this.ruta_fichero_agenda = ruta_agenda;
            cargarContactos();
        }

    }

    /**
     * Getter contactos
     *
     * @return retorna ArrayList de Contacto
     */
    public ArrayList<Contacto> getContactos() {
        return contactos;
    }

    /**
     * Crear un contacto
     *
     * @param dni
     * @param nombre
     * @return Contacto
     */
    public Contacto crearContacto(String dni, String nombre) {
        Contacto c = new Contacto(dni, nombre);
        return c;
    }

    /**
     * Añade un contacto a la lista de contactos
     *
     * @param c Instancia del contacto
     */
    public void añadirContacto(Contacto c) {
        this.contactos.add(c);
        System.out.println("Contacto añadido correctamente");
    }

    /**
     * Comprueba si el fichero existe de lo contrario lo crea
     *
     * @param ruta String con la ruta del fichero
     * @return retorna True si el fichero existe o se ha creado correctamente,
     * False si ha habido algun error.
     */
    public boolean comprobarFicheroAlmacenamiento(String ruta) {
        File f = new File(ruta);
        if (f.exists() && f.isFile()) {
            return true;
        } else {
            try {
                System.out.println("Creando " + f.getName() + "...");
                f.createNewFile();
                return true;
            } catch (IOException ex) {
                System.out.println("Error al crear el fichero de contactos :" + ex);
            }

        }
        return false;
    }

    /**
     * Carga los contactos en la agenda
     *
     * @return retorna true si se ha realizado correctamente
     */
    public boolean cargarContactos() {
        if (Fichero.existeFichero(this.ruta_fichero_agenda)) {
            try (ObjectInputStream agendaInput = new ObjectInputStream(new FileInputStream(this.ruta_fichero_agenda))) {
                ArrayList temp = (ArrayList) agendaInput.readObject();
                if (temp != null) {
                    this.contactos = temp;
                }
                return true;
            } catch (EOFException e) {
                return true;
            } catch (IOException | ClassNotFoundException ex) {
                System.out.println("Error al intentar cargar los contactos : " + ex);
            }

        }
        return false;
    }

    /**
     * Guarda la agenda en el fichero especificado.
     *
     * @return retorna true si se ha realizado correctamente
     */
    public boolean guardarAgenda() {
        if (Fichero.existeFichero(this.ruta_fichero_agenda)) {
            try (ObjectOutputStream agendaOut = new ObjectOutputStream(new FileOutputStream(this.ruta_fichero_agenda, false))) {
                agendaOut.writeObject(this.contactos);
                return true;
            } catch (IOException e) {
                System.out.println("Error al intentar guardar la agenda :" + e);

            }
        }
        return false;
    }

    /**
     * Muestra la agenda y sus contactos en forma de tabla
     *
     * @param listaContactos
     */
    public void mostrarContactos(ArrayList<Contacto> listaContactos) {

        String borde_color = Colores.CYAN + "|" + Colores.RESET; //borde vertical azul
        String borde_horizontal = Colores.CYAN + "+------+-----------------+----------------------+-----------+--------------------+-------------------------------------+-----------+";
        String formatoCamposTabla = borde_color + " %-4.4s " + borde_color + "  %-14.14s " + borde_color + " %-20.20s " + borde_color + " %-9.9s " + borde_color + " %-19.19s"
                + borde_color + " %-36.36s" + borde_color + " %-9.9s " + borde_color + "%n" + Colores.RESET; //formato printf campos

        System.out.println(borde_horizontal);
        System.out.format("|" + Colores.ROJO + " Nº   " + Colores.RESET + borde_color + Colores.AMARILLO + "     NOMBRE      " + borde_color + Colores.AMARILLO + "       APELLIDOS      "
                + borde_color + Colores.VERDE + "    DNI    " + borde_color + Colores.AMARILLO + "  FECHA NACIMIENTO  " + borde_color + Colores.AMARILLO + "                 EMAIL               "
                + borde_color + Colores.AMARILLO + " TELÉFONO  " + borde_color + "%n");
        System.out.println(borde_horizontal);

        Contacto tmp;
        for (int i = 0; i < listaContactos.size(); i++) { //muestra los campos de los contactos
            tmp = listaContactos.get(i);
            System.out.printf(formatoCamposTabla, i, tmp.getNombre(), tmp.getApellidos(), tmp.getDni(), tmp.getFechaNacimiento(), tmp.getEmail(), tmp.getTelefono());
        }
        System.out.println(borde_horizontal);
    }

    public static void main(String[] args) { //para testing
        Agenda miAgenda = new Agenda("contactos.dat");
        //miAgenda.mostrarContactos(); 

    }
} //fin clase
