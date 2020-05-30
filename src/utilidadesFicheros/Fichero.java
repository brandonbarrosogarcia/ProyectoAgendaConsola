package utilidadesFicheros;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static utilidades.Utilidades.introducirString;

public class Fichero {

    /**
     * Cuenta las lineas de un fichero de texto.
     *
     * @param nombre_fichero nombre o ruta del fichero.
     * @return retorna un entero con el numero de lineas del fichero
     */
    public static int contarLineasFichero(String nombre_fichero) {
        int lineas = 0;
        String contenidoLinea;

        try (BufferedReader br = new BufferedReader(new FileReader(nombre_fichero))) {

            while ((contenidoLinea = br.readLine()) != null) { //cuenta las lineas del fichero
                lineas++;
            }
            return lineas;
        } catch (FileNotFoundException ex) {
            System.out.println("Fichero no encontrado.");
        } catch (IOException e) {
            System.out.println("Error:" + e);
        }
        return lineas;
    } //fin contarLineasFichero

    /**
     * Extrae las lineas de un fichero de texto y las almacena en un array de
     * Strings
     *
     * @param nombreFichero nombre del fichero.
     * @return retorna un Array de Strings con las lineas del fichero.
     */
    public static String[] ficheroTexto2Array(String nombreFichero) {

        String[] contenidoFichero;
        String contenidoLinea;

        if (nombreFichero.isEmpty()) {//comprueba si está vacio
            System.out.println("El nombre del fichero no puede estar vacio, inténtelo de nuevo.");
            return null;
        } else {
            contenidoFichero = new String[contarLineasFichero(nombreFichero)]; //llama al metodo contarLineasFichero para asignar el tamaño del array
            try (
                    BufferedReader br = new BufferedReader(new FileReader(nombreFichero)); //crea el objeto BufferReader usando try-with-resources
                    ) {

                int i = 0;
                while ((contenidoLinea = br.readLine()) != null) { // almacena el contenido del fichero en array
                    contenidoFichero[i] = contenidoLinea;
                    i++;
                }
            } catch (FileNotFoundException ex) {
                System.out.println("Error fichero no encontrado.");
            } catch (IOException e) {
                System.out.println("Error: " + e);
            }
        } //fin else
        return contenidoFichero;
    } //fin ficheroTexto2Array    

    /**
     * Crear un fichero con el nombre deseado por el usuario
     *
     * @param nombreFichero nombre del fichero a crear o de la ruta del fichero.
     */
    public static void crearFichero(String nombreFichero) {
        if (nombreFichero.length() == 0) {
            System.out.println("El nombre del fichero no puede estar vacio, inténtelo de nuevo.");
        } else {

            try {
                File fichero = new File(nombreFichero);
                if (fichero.createNewFile()) {
                    System.out.println("Fichero creado: " + fichero.getName() + " en " + fichero.getAbsolutePath());
                } else {
                    System.out.println("El fichero ya existe, inténtelo con otro nombre.");
                }

            } catch (IOException e) {
                System.out.println("Error: " + e);
            }
        }
    } //fin crear fichero

    /**
     * leer el contenido de un fichero
     *
     * @param nombreFichero nombre o ruta del fichero.
     */
    public static void leerFichero(String nombreFichero) {
        if (nombreFichero.length() == 0) {
            System.out.println("El nombre del fichero no puede estar vacio, inténtelo de nuevo.");
        } else {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(nombreFichero), "UTF-8"))) {
                String linea;

                while ((linea = br.readLine()) != null) {
                    System.out.println(linea);
                }

            } catch (FileNotFoundException er) {
                System.out.println("Fichero no encontrado, inténtelo de nuevo.");
            } catch (IOException e) {
                System.out.println("Error: " + e);
            }
        }
    } //fin leer fichero    

    /**
     * Clona un fichero en otro fichero con el nombre deseado
     *
     * @param nombreFichero nombre o ruta del fichero a clonar.
     * @param nombreFicheroDestino nombre o ruta del fichero destino.
     */
    public static void clonar(String nombreFichero, String nombreFicheroDestino) {
        int contador = 0;
        int datos_fichero[]; //array con los datos del fichero en bytes

        try (
                FileInputStream fis = new FileInputStream(nombreFichero); //no es necesario cerrar los stream ya que utilizo try-with-resources
                FileOutputStream fos = new FileOutputStream(nombreFicheroDestino);) {

            long tamanioFichero = fis.getChannel().size(); //guarda el tamaño del fichero en un long

            datos_fichero = new int[(int) tamanioFichero]; //array con los datos del fichero se le asigna un tamaño

            int byteFichero; //entero con contenido del byte

            while ((byteFichero = fis.read()) != -1) { //lectura del fichero y se almacena en el array
                datos_fichero[contador] = byteFichero;
                contador++;
            }

            for (int i : datos_fichero) { //se escribe desde el array al fichero destino el contenido del mismo
                fos.write(i);
            }
            System.out.println("Se ha clonado correctamente el fichero!");

        } catch (FileNotFoundException ex) {
            System.out.println("Fichero no encontrado inténtelo de nuevo");
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }

    } //fin clonar

    /**
     * Escribe en un fichero el contenido que el usuario introduzca por teclado
     * usando centinela (lo añade) no sobreescribe.
     *
     * @param nombreFichero Nombre del fichero o ruta.
     */
    public static void escribirFichero(String nombreFichero) {
        String contenido;

        if (nombreFichero.isEmpty()) {
            System.out.println("El nombre del fichero no puede estar vacio, inténtelo de nuevo.");
        } else {
            try (
                    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(nombreFichero, true), "UTF-8"))) {
                do {
                    contenido = introducirString("Introduce el contenido (-1 para salir): ");
                    if (!contenido.contains("-1")) {
                        out.write(contenido);
                        out.newLine();
                    }
                } while (!contenido.contains("-1"));
                System.out.println("Escritura correcta!");
            } catch (FileNotFoundException ex) {
                System.out.println("Fichero no encontrado inténtelo de nuevo.");
            } catch (IOException e) {
                System.out.println("Error: " + e);
            }
        }

    } //fin escribirFichero

    /**
     * Verifica si un fichero existe o no
     *
     * @param nombreFichero nombre del fichero
     * @return retorna true si existe de lo contrario false.
     */
    public static boolean existeFichero(String nombreFichero) {

        File fichero = new File(nombreFichero);
        if (!fichero.exists()) {
            System.out.println("Error no se ha encontrado el fichero: " + nombreFichero);
        }
        return fichero.exists();

    } //fin existe fichero

    /**
     * Muestra la información de un fichero
     *
     * @param nombreFichero nombre o ruta del fichero.
     */
    public static void getInfoFichero(String nombreFichero) {
        File fichero = new File(nombreFichero);
        if (fichero.exists()) {
            System.out.println("\n\t Información Fichero");
            System.out.println("|-|=================================|-|");
            System.out.println("- Nombre del fichero: " + fichero.getName());
            System.out.println("- Ruta absouluta: " + fichero.getAbsolutePath());
            System.out.println("- Permisos de escritura: " + fichero.canWrite());
            System.out.println("- Permisos de lectura: " + fichero.canRead());
            System.out.println("- Tamaño del archivo en bytes: " + fichero.length());
            System.out.println("|====================================|\n");
        } else {
            System.out.println("El fichero no existe.");
        }

    } //fin getInfoFichero

    /**
     * Pasa un array de strings a un fichero
     *
     * @param array array de strings
     * @param nombreFichero nombre del fichero
     */
    public static void stringArrayAFichero(String[] array, String nombreFichero) {
        if (nombreFichero.isEmpty()) {//comprueba si está vacio
            System.out.println("El nombre del fichero no puede estar vacio, inténtelo de nuevo.");
        } else {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreFichero))) {
                if (array.length == 0) {//comprueba si el array está vacio
                    System.out.println("Error, el array está vacio.");
                } else {
                    for (String string : array) { //escribe las lineas
                        bw.write(string);
                        bw.newLine();
                    }
                } //fin else

            } catch (FileNotFoundException er) {
                System.out.println("Error fichero no encontrado.");
            } catch (IOException e) {
                System.out.println("Error: " + e);
            }
        } //fin else

    } //fin arrayAFichero
    
    
    public static void main(String[] args) {
       String ruta_carpeta_usuario =  System.getProperty("user.dir");
        System.out.println(ruta_carpeta_usuario );
        existeFichero(ruta_carpeta_usuario + File.separator +"menu.txt");

    } //fin main
} //fin clase
