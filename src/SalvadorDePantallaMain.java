import java.awt.*;

/**
 * Implementación del salvapantallas de líneas.
 *
 * @author Ignacio Gavia Plaza
 */
public final class SalvadorDePantallaMain {
    /**
     * Verificar colisiones y actualizar las velocidades.
     *
     * @param posicion vector de posiciones.
     * @param velocidad vector de velocidades.
     * @param min rango mínimo.
     * @param max rango máximo.
     */
    private static void moveLines(double[][] posicion, double[][] velocidad, double min, double max) {

        // Tabla:
        // x1 -> posiciones[0][0]
        // y1 -> posiciones[1][0]
        // x2 -> posiciones[2][0]
        // y2 -> posiciones[3][0]

        // vx1 -> velocidades[0][0]
        // vy1 -> velocidades[1][0]
        // vx2 -> velocidades[2][0]
        // vy2 -> velocidades[3][0]

        // Actualizar las velocidades si hay colisión

        // x1
        if (Math.abs(posicion[0][0] + velocidad[0][0]) > max - 0.02) {
            velocidad[0][0] *= -1;
        }
        // y1
        if (Math.abs(posicion[1][0] + velocidad[1][0]) > max - 0.02) {
            velocidad[1][0] *= -1;
        }
        // x2
        if (Math.abs(posicion[2][0] + velocidad[2][0]) > max - 0.02) {
            velocidad[2][0] *= -1;
        }
        // y2
        if (Math.abs(posicion[3][0] + velocidad[3][0]) > max - 0.02) {
            velocidad[3][0] *= -1;
        }

        // Intercambiar las posiciones
        for (int i = posicion[0].length - 1; i > 0; i--) {
            posicion[0][i] = posicion[0][i - 1];
            posicion[1][i] = posicion[1][i - 1];
            posicion[2][i] = posicion[2][i - 1];
            posicion[3][i] = posicion[3][i - 1];
        }

        // Actualizar las posiciones
        // x1
        posicion[0][0] += velocidad[0][0];
        // y1
        posicion[1][0] += velocidad[1][0];
        // x2
        posicion[2][0] += velocidad[2][0];
        // y2
        posicion[3][0] += velocidad[3][0];

    }

    /**
     * Dibujar las líneas.
     *
     * @param posiciones arreglo a utilizar.
     */
    private static void draw(double[][] posiciones) {
        // Arreglo de colores
        Color[] colores = {
                Color.RED,
                Color.BLACK,
                Color.DARK_GRAY,
                Color.GRAY,
                Color.LIGHT_GRAY,
                Color.PINK,
        };

        // dibujar la posición
        for (int i = 0; i < posiciones[0].length; i++) {
            // Establecer el color del lápiz
            StdDraw.setPenColor(colores[i]);
            // Linea
            StdDraw.line(posiciones[0][i], posiciones[1][i], posiciones[2][i], posiciones[3][i]);
        }
    }

    /**
     * Inicializar las velocidades de cada línea.
     *
     * @return el vector de velocidades.
     */
    private static double[][] inicializarVelocidades() {
        // Velocidades iniciales
        double vx = 0.012;
        double vy = 0.032;

        // vx1 -> velocidades[0][0]
        // vy1 -> velocidades[1][0]
        // vx2 -> velocidades[2][0]
        // vy2 -> velocidades[3][0]
        double[][] velocidades = new double[4][2];
        velocidades[0][0] = vx;
        velocidades[1][0] = vy;
        velocidades[2][0] = vx;
        velocidades[3][0] = vy;

        return velocidades;
    }

    /**
     * Crear una matriz doble con valores aleatorios en el archivo 0 y archivo 1.
     *
     * @param min a utilizar.
     * @param max a utilizar.
     * @param numeroDeLineas número de líneas.
     * @return el vector de dobles.
     */
    private static double[][] inicializarPosiciones(double min, double max, int numeroDeLineas) {
        // Declarar el vector
        double[][] posicion = new double[4][numeroDeLineas];

        // Posición inicial (aleatoria)
        double x1 = min + (max - min) * Math.random();
        double y1 = min + (max - min) * Math.random();

        double x2 = min + (max - min) * Math.random();
        double y2 = min + (max - min) * Math.random();

        // Llenar el arreglo con los mismos valores
        for (int i = 0; i < numeroDeLineas; i++) {
            // (x, y)
            posicion[0][i] = x1;
            posicion[1][i] = y1;

            // (x', y')
            posicion[2][i] = x2;
            posicion[3][i] = y2;
        }

        return posicion;
    }

    /**
     * Inicializar el lienzo.
     *
     * @param min para usar.
     * @param max para usar.
     */
    private static void inicializacion(double min, double max) {

        // Definicion de la escala del lienzo de dibujo
        StdDraw.setXscale(min, max);
        StdDraw.setYscale(min, max);
        // Evitar el parpadeo de la pantalla
        StdDraw.enableDoubleBuffering();

    }
    /**
     * The Main.
     */
    public static void main(String[] args) {

        // Tamaño del lienzo: desde -rango hasta +rango
        double range = 1.0;

        // Número de líneas
        int nLines = 6;

        // Crear la matriz de posiciones
        double[][] positions = inicializarPosiciones(-range, range, nLines);
        // Crear la matriz de velocidades
        double[][] velocities = inicializarVelocidades();

        // Inicializar el lienzo
        inicializacion(-range, range);

        // Ciclo while...
        while (true) {
            // Verificar la colisión y mover las líneas
            moveLines(positions, velocities, -range, range);

            // Limpiar lienzo
            StdDraw.clear();

            // Dibujar las posiciones
            draw(positions);

            // Mostrar y esperar
            StdDraw.show();
            StdDraw.pause(50);
        }
    }
}
