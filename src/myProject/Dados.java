package myProject;

import java.util.Random;

/**
 * Clase Cartas genera un valor random entre 1 y 40
 * @autor Mayra Alejandra Sanchez - mayra.alejandra.sanchez@correounivalle.edu.co - 202040506
 * @autor Brayan Stiven Sanchez - brayan.sanchez.leon@correounivalle.edu.co - 202043554
 * @version 1.0.0 fecha 3/1/2022
 */
public class Dados {
    private int numero;
    private int numeroAccion;
    private String accion;

    public Dados(int _numero){
        numero = _numero;
        numeroAccion = numeroAccion;
    }

    public void setNumAccion(){
        Random aleatorio = new Random();
        numeroAccion = aleatorio.nextInt(6)+1;
    }

    public void setNumAccionNoAleatorio(int numero){
        numeroAccion = numero;
    }

    public void setAccion(String _accion){
        accion = _accion;
    }

    public int getNumAccion(){
        return numeroAccion;
    }

    public String getAccion(){
        return accion;
    }

    public int getNumero(){
        return numero;
    }
}
