package myProject;

import java.util.Random;

/**
 * Clase Cartas genera un valor random entre 1 y 40
 * @autor Mayra Alejandra Sanchez - mayra.alejandra.sanchez@correounivalle.edu.co - 202040506
 * @autor Brayan Stiven Sanchez - brayan.sanchez.leon@correounivalle.edu.co - 202043554
 * @version 1.0.0 fecha 3/1/2022
 */
public class Dados {
    /**
     * Atributos de los dados
     *
     * Acciones:
     * -> Meeple: permite relanzar otro dado en juego, es decir, de la sección dados activos.
     * -> Nave espacial: envía un dado no usado (de la sección dados activos) a la sección de dados
     * inactivos
     * -> Dragón: es la cara que se quiere evitar, ya que si al final de la ronda es el último dado activo que
     * queda se habrán perdido todos los puntos ganados y acumulados.
     * -> Superhéroe: permite que cualquier dado no usado (sección dados activos) sea volteado y
     * colocado en su cara opuesta.
     * -> 42: es cara que permite sumar puntos al final de la ronda.
     * -> Corazon:permite tomar un dado de la sección de dados inactivos y lanzarlo para que sea un
     * nuevo dado activo.
     */
    private int acciones;
    /**
     * Metodo que genera el numero aleatorio entre 1 y 6 para las caras del dado
     * @return valor de la carta
     *
     */
    public int getAcciones() {
        Random aleatorio = new Random();
        acciones = aleatorio.nextInt(6)+1;
        return acciones;
    }

}
