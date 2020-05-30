package menu;

import agenda.Agenda;
import agenda.Contacto;
import java.io.File;
import utilidades.Utilidades;
import coloresConsola.Colores;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import utilidadesFicheros.Fichero;

/**
 *
 * @author Brandon Barroso García
 */
public class MenuAgenda implements Colores {

    private final Agenda miAgenda;
    private final String ruta_menu;
    private final String ruta_submenu;
    private final String[] menu_saved;
    private final String[] submenu_saved;

    /**
     * Constructor MenuAgenda
     *
     * @param miAgenda instancia de la clase Agenda
     * @param ruta_fichero_menu String con la ruta fichero con las opciones del
     * menú
     * @param ruta_fichero_submenu String con la ruta fichero con las opciones
     * del submenú CRUD
     */
    public MenuAgenda(Agenda miAgenda, String ruta_fichero_menu, String ruta_fichero_submenu) {
        this.miAgenda = miAgenda;
        this.ruta_menu = ruta_fichero_menu;
        this.ruta_submenu = ruta_fichero_submenu;
        this.menu_saved = Fichero.ficheroTexto2Array(ruta_menu); //lee el menu y lo almacena en un array 
        this.submenu_saved = Fichero.ficheroTexto2Array(ruta_submenu); // lee el submenu y lo almacena
    }

    /**
     * Muestra el menu por consola almacenado en un fichero de texto
     *
     * @param rutaFichero Ruta del fichero donde se encuentra el menu
     */
    public void mostrarMenu(String rutaFichero) {
        
        if (Fichero.existeFichero(rutaFichero)) {
            Fichero.leerFichero(rutaFichero);
        }
    }

    /**
     * Muestra el menu almacenado en un array
     */
    public void mostrarMenuAlmacenado() {
        if (this.menu_saved != null) {
            for (String linea : this.menu_saved) {
                System.out.println(linea);
            }
        } else {
            System.out.println("Error el menú no se ha cargado correctamente o se encuentra vacío.");
        }
    }

    /**
     * Muestra el submenu almacenado en un array
     */
    public void mostrarSubmenuAlmacenado() {
        if (this.submenu_saved != null) {
            for (String linea : this.submenu_saved) {
                System.out.println(linea);
            }
        } else {
            System.out.println("Error el submenú no se ha cargado correctamente o se encuentra vacío.");
        }
    }

    /**
     * Busca un contacto por su nombre y lo muestra
     *
     * @param nombre String con el nombre del contacto
     */
    public void buscarContactoNombre(String nombre) {

        ArrayList<Contacto> contactos = this.miAgenda.getContactos();
        ArrayList<Contacto> resultadoBusqueda = new ArrayList<>();
        for (Contacto c : contactos) {
            if (c.getNombre().startsWith(nombre)) {
                resultadoBusqueda.add(c);
            }
        }
        if (resultadoBusqueda.isEmpty()) {
            System.out.println("\n" + Colores.ROJO + " -> No se han encontrado contactos con el nombre: " + nombre);
        } else {
            System.out.println("\n" + Colores.VERDE + " -> Se ha encontrado el contacto con el nombre: " + nombre);
            this.miAgenda.mostrarContactos(resultadoBusqueda); //muestra los resultados
        }

    }

    /**
     * Ejecuta el menú
     */
    public void ejecutarMenu() {
        int opcion;

        do {
            //mostrarMenu(this.ruta_menu);
            mostrarMenuAlmacenado();
            opcion = Utilidades.introducirNumero(Colores.AMARILLO + "Selecciona una opción [1-5]: " + Colores.RESET);
            switch (opcion) {
                case 1:
                    System.out.println("=>Opción seleccionada: Serializar");
                    if (miAgenda.guardarAgenda()) {
                        System.out.println(Colores.VERDE + "Serialización correcta, se ha guardado la agenda en el fichero." + Colores.RESET);
                    } else {
                        System.out.println(Colores.ROJO + " Error al serializar." + Colores.RESET);
                    }
                    break;
                case 2:
                    System.out.println("=> Opción seleccionada: Deserializar");
                    if (miAgenda.cargarContactos()) {
                        System.out.println(Colores.VERDE + "Deserialización correcta, se ha cargado la agenda desde el fichero." + Colores.RESET);
                    } else {
                        System.out.println(Colores.ROJO + " Error al Deserializar." + Colores.RESET);
                    }

                    break;
                case 3:
                    System.out.println("=> Opción seleccionada: CRUD");
                    ejecutarCrud();
                    break;
                case 4:
                    System.out.println("=> Opción seleccionada: Mostrar por consola el fichero del menú");
                    mostrarMenu(this.ruta_menu);
                    break;
                case 5:
                    System.out.println("Saliendo de la agenda...");
                    break;
                default:
                    System.out.println(Colores.ROJO + "Opción no encontrada, inténtelo de nuevo." + Colores.ROJO);

            }

        } while (opcion != 5);
    }

    /**
     * muestra los contactos ordenados alfabéticamente por su apellido
     *
     * @param listaContactos ArrayList de contactos
     */
    public void mostrarContactosOrdenados(ArrayList<Contacto> listaContactos) {
        ArrayList<Contacto> listaContactosTemp = (ArrayList<Contacto>) listaContactos.clone(); //copia temporal de los contactos

        Collections.sort(listaContactosTemp, new Comparator<Contacto>() { //ordena los contactos 
            @Override
            public int compare(Contacto c1, Contacto c2) {
                return c1.getApellidos().compareTo(c2.getApellidos());
            }
        });

        this.miAgenda.mostrarContactos(listaContactosTemp); // Muestra los contactos ordenados
    }

    /**
     * Borra un contacto introduciendo su posición en la tabla
     *
     * @param listaContactos Arraylist con los contactos.
     */
    public void borrarContactoPosicion(ArrayList<Contacto> listaContactos) {
        this.miAgenda.mostrarContactos(listaContactos);
        int posicion;
        do {
            posicion = Utilidades.introducirNumero("Introduce la posición del contacto a borrar: ");
        } while (posicion < 0 || posicion > listaContactos.size() - 1);

        listaContactos.remove(posicion);
        System.out.println("Se ha borrado el contacto " + posicion + ".");
    }

    /**
     * Menu del crud
     */
    public void ejecutarCrud() {
        int opcion;

        do {
            //mostrarMenu(this.ruta_submenu); //muestra menu crud
            mostrarSubmenuAlmacenado();
            opcion = Utilidades.introducirNumero(Colores.AMARILLO + "Selecciona una opción [1-6]: " + Colores.RESET);
            switch (opcion) {
                case 1:
                    System.out.println("=>Opción seleccionada: Crear un contacto.");
                    crearContacto();
                    break;
                case 2:
                    System.out.println("=> Opción seleccionada: Mostrar todos los contactos");
                    this.miAgenda.mostrarContactos(this.miAgenda.getContactos());
                    break;
                case 3:
                    System.out.println("=> Opción seleccionada: Buscar un contacto por nombre, apellido, DNI");
                    buscarContactoNombre(Utilidades.introducirString("Introduce el nombre del contacto a buscar: "));
                    break;
                case 4:
                    System.out.println("=> Opción seleccionada: Ordenar los contactos por Apellido");
                    mostrarContactosOrdenados(this.miAgenda.getContactos());
                    break;
                case 5:
                    System.out.println("=> Opción seleccionada: Borrar por posición");
                    borrarContactoPosicion(this.miAgenda.getContactos());
                    break;
                case 6:
                    System.out.println("Saliendo del CRUD...");
                    this.miAgenda.guardarAgenda();
                    break;
                default:
                    System.out.println(Colores.ROJO + "Opción no encontrada, inténtelo de nuevo." + Colores.ROJO);

            }

        } while (opcion != 6);
    }

    /**
     * Crea un contacto con sus datos y lo añade al arrayList de miAgenda
     */
    public void crearContacto() {
        String dni, nombre, apellidos, email, telefono, fechaNacimiento;

        dni = Utilidades.pedirDni();
        nombre = Utilidades.validarNombre("Introduce el nombre del contacto: ");
        apellidos = Utilidades.validarApellidos("Introduce los apellidos del contacto: ");
        fechaNacimiento = Utilidades.validarFecha("Introduce la fecha de nacimiento del contacto (ej 10-02-1975): ");
        email = Utilidades.validarEmail("Introduce el email del contacto: ");
        telefono = Utilidades.introducirString("Introduce el número de teléfono del contacto: ");

        Contacto nuevoContacto = new Contacto(dni, nombre, apellidos, fechaNacimiento, email, telefono);
        this.miAgenda.añadirContacto(nuevoContacto);
    }

    public static void main(String[] args) {
        //rutas de los ficheros
        String ruta_carpeta_usuario = System.getProperty("user.dir"); //directorio usuario
        String ruta_fichero_menu = ruta_carpeta_usuario + File.separator + "menu.txt"; //ruta fichero menu
        String ruta_fichero_submenu = ruta_carpeta_usuario + File.separator + "submenu.txt"; //ruta fichero menu
        String ruta_contactos = ruta_carpeta_usuario + File.separator + "contactos.dat"; //ruta fichero contactos

        //contactos de ejemplo
        Contacto c1 = new Contacto("45517717G", "Pipo", "Alfonso Herrera", "10-04-1975", "pipo@gmail.com", "625052514");
        Contacto c2 = new Contacto("39406470H", "Juan Pedro", "Solano Alcantara", "02-03-1990", "juan@gmail.com", "625057514");
        Contacto c3 = new Contacto("50743897D", "Borja", "Pascual Pazos", "01-12-1996", "borja@gmail.com", "625752514");
        Contacto c4 = new Contacto("60743897W", "Amelia", "Navarro Garcia", "04-11-1991", "amelia@gmail.com", "621752514");

        Agenda miAgenda = new Agenda(ruta_contactos); //instancia del objeto de agenda
        //miAgenda.añadirContacto(c1);
        //miAgenda.añadirContacto(c2);
        //miAgenda.añadirContacto(c3);
        //miAgenda.añadirContacto(c4);
        MenuAgenda menu = new MenuAgenda(miAgenda, ruta_fichero_menu, ruta_fichero_submenu); //creo la instancia del menu

        menu.ejecutarMenu();

    } //fin main

} //fin clase
