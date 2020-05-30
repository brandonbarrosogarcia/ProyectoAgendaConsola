package coloresConsola;

/**
 * Esta interfaz permite añadir colores al texto de la consola usando las
 * constantes.
 *
 * @author Brandon Barroso García
 */
public interface Colores {

    /**
     * Reset el estilo
     */
    public final String RESET = "\033[0m";
    /**
     * Color Rojo
     */
    public final String ROJO = "\033[0;31m";
    /**
     * Color Amarillo
     */
    public final String AMARILLO = "\033[0;33m";
    /**
     * Color Azul
     */
    public final String AZUL = "\033[0;34m";
    /**
     * Color Verde
     */
    public final String VERDE = "\033[0;32m";
    
    /**
     * Color Violeta
     */
    public final String VIOLETA = "\033[0;35m";
    /**
     * Color CYAN
     */
    public final String CYAN = "\033[0;36m";
    /**
     * Color Blanco
     */
    public final String BLANCO = "\033[0;37m";
    /**
     * Fondo Rojo
     */
    public final String FONDO_ROJO = "\033[41m";
    /**
     * Fondo Verde
     */
    public final String FONDO_VERDE = "\033[42m";
    /**
     * Fondo Amarillo
     */
    public final String FONDO_AMARILLO = "\033[43m";
    /**
     * Fondo Azul
     */
    public final String FONDO_AZUL = "\033[44m";
    /**
     * Fondo Violeta
     */
    public final String FONDO_VIOLETA = "\033[45m";
    /**
     * Fondo Cyan
     */
    public final String FONDO_CYAN = "\033[46m";
    /**
     * Fondo Blanco
     */
    public final String FONDO_BLANCO = "\033[47m";
}
