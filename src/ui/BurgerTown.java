/**
 * La clase BurgerTown gestiona un menú interactivo que permite registrar y consultar
 * las ventas de platos de un restaurante, calculando diferentes estadísticas sobre
 * las ventas del día.
 * <p>
 * Se puede solicitar precios, calcular cantidad de platos vendidos, precio promedio,
 * ventas totales y consultar cuántos platos superaron un límite de ventas.
 * </p>
 * 
 * @author josseivan
 */
public class BurgerTown {

    /**
     * Escáner para recibir entradas del usuario.
     */
    public static Scanner reader;

    /**
     * Arreglo que contiene los precios de los platos vendidos.
     */
    public static double[] precios;

    /**
     * Arreglo que contiene las cantidades de unidades vendidas de cada plato.
     */
    public static int[] unidades;

    /**
     * Método principal que inicializa las variables globales y ejecuta el menú interactivo.
     * 
     * @param args Los argumentos de línea de comando.
     */
    public static void main(String[] args) {
        inicializarGlobales();
        menu();
    }

     /**
     * Descripcion: Este metodo se encarga de iniciar las variables globales
     * pre: El Scanner reader debe estar declarado
     * pos: l Scanner reader queda inicializado
    */
    public static void inicializarGlobales() {
        reader = new Scanner(System.in);
    }

    /**
     * Descripcion: Este metodo se encarga de desplegar el menu al usuario y mostrar los mensajes de resultado para cada funcionalidad
     * pre: El Scanner reader debe estar inicializado
     * pre: El arreglo precios debe estar inicializado
    */

    public static void menu() {
        System.out.println("Bienvenido a BurgerTown!");

        establecerCantidadVendida();

        boolean salir = false;

        do {
            System.out.println("\nMenu Principal:");
            System.out.println("1. Solicitar precios ($) y cantidades de cada plato vendido en el día");
            System.out.println("2. Calcular la cantidad total de platos vendidos en el día");
            System.out.println("3. Calcular el precio promedio de los platos vendidos en el día");
            System.out.println("4. Calcular las ventas totales (dinero recaudado) durante el día");
            System.out.println("5. Consultar el número de platos que en el día han superado un límite mínimo de ventas");
            System.out.println("6. Salir");
            System.out.println("\nDigite la opción a ejecutar");
            int opcion = reader.nextInt();

            switch (opcion) {
                case 1:
                    solicitarDatos();
                    break;
                case 2:
                    System.out.println("\nLa cantidad total de platos vendidos en el día fue de: " + calcularTotalPlatosVendidos());
                    break;
                case 3:
                    System.out.println("\nEl precio promedio de los platos vendidos en el día fue de: " + calcularPrecioPromedio());
                    break;
                case 4:
                    System.out.println("\nLas ventas totales (dinero recaudado) durante el día fueron: " + calcularVentasTotales());
                    break;
                case 5:
                    System.out.println("\nDigite el límite mínimo de ventas a analizar");
                    double limite = reader.nextDouble();
                    System.out.println("\nDe las " + precios.length + " referencias vendidas en el día, " + consultarPlatosSobreLimite(limite) + " superaron el límite mínimo de ventas de " + limite);
                    break;
                case 6:
                    System.out.println("\nGracias por usar nuestros servicios!");
                    salir = true;
                    reader.close();
                    break;
                default:
                    System.out.println("\nOpción inválida, intenta nuevamente.");
                    break;
            }

        } while (!salir);

    }

    /**
     * Descripcion: Este metodo se encarga de preguntar al usuario el numero de platos diferentes 
     * vendidos en el dia e inicializa con ese valor los arreglos encargados de almacenar precios y cantidades
     * pre: El Scanner reader debe estar inicializado
     * pre: Los arreglos precios y unidades deben estar declarados
     * pos: Los arreglos precios y unidades quedan inicializados
     */
    public static void establecerCantidadVendida() {
        System.out.println("\nDigite el número de platos diferentes vendidos en el día ");
        int platos = reader.nextInt();

        precios = new double[platos];
        unidades = new int[platos];
    }

    /**
     * Solicita al usuario los datos de precios y unidades vendidas para cada plato.
     */
    public static void solicitarDatos() {
        for (int i = 0; i < precios.length; i++) {
            System.out.println("Digite el precio del plato " + (i + 1) + ": ");
            precios[i] = reader.nextDouble();

            System.out.println("Digite la cantidad de unidades vendidas del plato " + (i + 1) + ": ");
            unidades[i] = reader.nextInt();
        }
    }

    /**
     * Calcula la cantidad total de platos vendidos durante el día sumando todas las unidades vendidas.
     * 
     * @return El total de platos vendidos.
     */
    public static int calcularTotalPlatosVendidos() {
        int total = 0;

        for (int i = 0; i < unidades.length; i++) {
            total += unidades[i];
        }

        return total;
    }

    /**
     * Calcula el precio promedio de los platos vendidos durante el día.
     * 
     * @return El precio promedio de los platos vendidos.
     */
    public static double calcularPrecioPromedio() {
        double sumaPrecios = 0;

        for (int i = 0; i < precios.length; i++) {
            sumaPrecios += precios[i];
        }

        return sumaPrecios / precios.length;
    }

    /**
     * Calcula el total de ventas (dinero recaudado) durante el día.
     * 
     * @return El total de ventas en dinero.
     */
    public static double calcularVentasTotales() {
        double totalVentas = 0;

        for (int i = 0; i < precios.length; i++) {
            totalVentas += precios[i] * unidades[i];
        }

        return totalVentas;
    }

    /**
     * Consulta cuántos platos han superado un límite mínimo de ventas en términos monetarios.
     * 
     * @param limite El límite mínimo de ventas en dinero.
     * @return El número de platos que han superado el límite de ventas.
     */
    public static int consultarPlatosSobreLimite(double limite) {
        int platosSobreLimite = 0;

        for (int i = 0; i < precios.length; i++) {
            if (precios[i] * unidades[i] > limite) {
                platosSobreLimite++;
            }
        }

        return platosSobreLimite;
    }
}
