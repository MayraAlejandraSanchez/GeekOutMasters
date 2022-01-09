package myProject;

import java.util.Random;

/**
 * Clase Ddos genera un numero del 1 al 6
 * @autor Mayra Alejandra Sanchez - mayra.alejandra.sanchez@correounivalle.edu.co - 202040506
 * @autor Brayan Stiven Sanchez - brayan.sanchez.leon@correounivalle.edu.co - 202043554
 * @version 1.0.0 fecha 3/1/2022
 */
public class Dados {
    private int numeroAccion;
    private String accion;
    private String nombreDado;

    public Dados(){
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

    public void setNombreDado(String _nombre){
        nombreDado = _nombre;
    }

    public int getNumAccion(){
        return numeroAccion;
    }

    public String getAccion(){
        return accion;
    }

    public String getNombreDado(){
        return nombreDado;
    }
}
