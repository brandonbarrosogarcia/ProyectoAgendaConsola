package utilidades;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Utilidades {

    /**
     * Método para pedir un caracter por teclado.
     *
     * @param texto Texto a mostrar para pedir al usuario el caracter
     * @return Retorna el Caracter
     */
    public static char introducirChar(String texto) {
        char caracter;

        Scanner keyboard = new Scanner(System.in);
        System.out.print(texto);
        caracter = keyboard.next().charAt(0);//Elimina espacios inecesarios
        System.out.println();

        return caracter;
    } //Fin introducirChar  

    /**
     * pedir numero double por teclado
     *
     * @param texto Texto a mostrar para pedir al usuario el numero
     * @return retorna el número pedido:
     */
    public static double introducirDouble(String texto) {
        double n = -1;
        boolean esNumero;
        Scanner keyboard = new Scanner(System.in);

        do {
            System.out.print(texto);
            if (keyboard.hasNextDouble()) {
                n = keyboard.nextDouble();
                esNumero = true;
            } else {
                esNumero = false;
                System.out.print("Valor introducido no válido inténtelo de nuevo.");
                keyboard.next();
            }
            System.out.println();
        } while (!esNumero);
        return n;
    }//Fin pedir double

    /**
     * pedir numero entero por teclado
     *
     * @param texto Texto a mostrar para pedir al usuario el numero
     * @return retorna el número pedido:
     */
    public static int introducirNumero(String texto) {
        int n = -1;
        boolean esNumero;
        Scanner keyboard = new Scanner(System.in);

        do {
            System.out.print(texto);
            if (keyboard.hasNextInt()) {
                n = keyboard.nextInt();
                esNumero = true;
            } else {
                esNumero = false;
                System.out.print("Valor introducido no válido inténtelo de nuevo.");
                keyboard.next();
            }
            System.out.println();
        } while (!esNumero);
        return n;
    }//Fin pedir numero entero

    /**
     * Método para pedir un String por teclado, sin espacios sobrantes.
     *
     * @param texto Texto a mostrar para pedir al usuario el String
     * @return Retorna el String
     */
    public static String introducirString(String texto) {
        String cadena;

        Scanner keyboard = new Scanner(System.in);
        System.out.print(texto);
        cadena = keyboard.nextLine().trim().replaceAll("\\s+", " ");//Elimina espacios inecesarios

        return cadena;
    } //Fin introducirString 

    /**
     * Metodo para pedir al usuario que pulse Enter para continuar
     *
     */
    public static void pulsarTeclaPausa() {
        String entrada;
        do {
            System.out.println("\nPulse Enter para continuar...\n");
            entrada = new Scanner(System.in).nextLine();
        } while (!entrada.equals(""));

    } //Fin tecla continuar

    /**
     * pedir por teclado un numero entero positivo
     *
     * @param texto Texto a mostrar para pedir al usuario el numero
     * @return retorna el numero entero positivo
     */
    public static int introducirNumeroPositivo(String texto) {
        int numeroPositivo;
        numeroPositivo = introducirNumero(texto); //LLama a el método introducirNumero para verificar que es un entero

        if (numeroPositivo < 0) {
            do {
                System.out.print("El número introducido no es positivo, inténtelo de nuevo.\n");
                numeroPositivo = introducirNumero(texto);
            } while (numeroPositivo < 0);
        } //Fin if 

        return numeroPositivo;
    }//Fin pedir numero positivo

    /**
     * Método para llenar el Array con numeros aleatorios entre [0-10]
     *
     * @param array Array
     */
    public static void llenarArrayRandom(int array[]) {
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 11);
        }

    }//Fin metodo llenar array aleatorios   

    /**
     * Pasa a un String el contenido de un Array de Strings.
     *
     * @param array Array que queremos pasar al String.
     * @return Retorna un string con el contenido del Array.
     */
    public static String pasarArrayEnString(String array[]) {
        String cadena = "";
        for (int i = 0; i < array.length; i++) {
            cadena += array[i] + " ";
        }

        return cadena;
    } //Fin convertirArrayEnString 

    /**
     * Pide al usuario introducir un email y lo valida
     *
     * @param mensaje para introducir email
     * @return retorna un string con el email
     */
    public static String validarEmail(String mensaje) {
        do {
            String email = introducirString(mensaje);
            String regex = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?"
                    + "`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
            if (email.matches(regex)) {
                return email;
            } else {
                System.out.println("Debes introducir una dirección de correo "
                        + "válida! ");
            }
        } while (true);
    }

    /**
     * Pide al usuario introducir una fecha
     *
     * @param mensaje para introducir la fecha
     * @return retorna un string con la fecha
     */
    public static String validarFecha(String mensaje) {
        do {
            String fecha = introducirString(mensaje);
            String regex = "^\\s*(3[01]|[12][0-9]|0?[1-9])\\-(1[012]|0?[1-9])\\-((?:19|20)\\d{2})\\s*$";
            if (fecha.matches(regex)) {
                return fecha;
            } else {
                System.out.println("Debes introducir una fecha válida! ");
            }
        } while (true);
    }

    /**
     * Pide al usuario introducir un nombre y lo valida
     *
     * @param mensaje para introducir el nombre
     * @return retorna un string con el nombre
     */
    public static String validarNombre(String mensaje) {
        do {
            String nombre = introducirString(mensaje);
            String regex = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
            if (nombre.matches(regex) && nombre.length() < 25) {
                return nombre;
            } else {
                System.out.println("Debes introducir un nombre "
                        + "válido! ");
            }
        } while (true);
    }

    /**
     * Pide al usuario introducir apellidos y lo valida
     *
     * @param mensaje para introducir los apellidos
     * @return retorna un string con los apellidos
     */
    public static String validarApellidos(String mensaje) {
        do {
            String apellidos = introducirString(mensaje);
            String regex = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
            if (apellidos.matches(regex)) {
                return apellidos;
            } else {
                System.out.println("Debes introducir apellidos "
                        + "válidos! ");
            }
        } while (true);
    }

    /**
     * Formatea un string con una fecha con el formato [dd-MM-yyyy] y la
     * devuelve en el formato [dd/MMMM/yyyy]
     *
     * @param fecha String con la fecha
     * @return retorna un String con la fecha formateada y verificada.
     */
    public String formatFecha(String fecha) {
        String fNacimientoFormated = null;
        SimpleDateFormat formato_out = new SimpleDateFormat("dd/MMMM/yyyy");
        SimpleDateFormat formato_in = new SimpleDateFormat("dd-MM-yyyy");

        Date date;
        try {
            date = formato_in.parse(fecha);
            fNacimientoFormated = formato_out.format(date);
        } catch (ParseException ex) {
            System.out.println(ex);
        }
        return fNacimientoFormated;
    }

    /**
     * Verifica que el dni sea válido comprobando su letra.
     *
     * @param dni String con el dni
     * @return retorna el string con el dni si válido, si no es válido retorna
     * null.
     */
    public static boolean verificarDni(String dni) {
        char letraDni[] = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};
        int dniNumero = Integer.parseInt(dni.substring(0, 8));
        int restoDni = dniNumero % 23;

        String dni_comprobar = Integer.toString(dniNumero) + Character.toString(letraDni[restoDni]);

        return dni.equals(dni_comprobar);
    }

    /**
     * Pide un dni por consola y lo válida con una expresión regular
     *
     * @return retorna un String con el dni
     */
    public static String pedirDni() {
        String dni = "";
        String dniRegex = "^[0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKE]$";

        do {
            dni = introducirString("Introduce un dni válido (ej: 00000000Z): ");
        } while (!dni.matches(dniRegex));
        return dni;
    } //Fin metodo pedir numero dni

    public static void main(String[] args) {
        String dni = pedirDni();

        System.out.println("dni : " + dni);
    }

} //fin clase Utilidades
