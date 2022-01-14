package myProject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
/**
 * Clase ModelDados
 * @autor Mayra Alejandra Sanchez - mayra.alejandra.sanchez@correounivalle.edu.co - 202040506
 * @autor Brayan Stiven Sanchez - brayan.sanchez.leon@correounivalle.edu.co - 202043554
 * @version 1.0.0 fecha 3/1/2022
 */
public class ModelDados {

    private int dadoRandom;
    private ArrayList<Dados> dados;
    private ArrayList<Dados> dadosInactivos;
    private ArrayList<Dados> dadosUtilizados;
    private HashMap<String, String> nombreAAccion; // Sirve para identificar la accion por medio de su nombre
    private HashMap<String, String> nombreAEstado; // Sirve para identificar cuales dados son activos e inactivos por medio de su nombre
    private HashMap<String, Dados> nombreAObjeto; // Sirve para retornar el objeto por medio de su nombre

    public ModelDados(){

        // Creacion de 10 dados
        dados = new ArrayList<>();
        for(int dado=0; dado < 10; dado++){
            dados.add(new Dados());
        }

        nombreAAccion = new HashMap<>();
        nombreAObjeto = new HashMap<>();
        nombreAEstado = new HashMap<>();
        dadosInactivos = new ArrayList<>();
        dadosUtilizados = new ArrayList<>();
    }

    // Retorna el array ingresado
    public ArrayList listaDados(String nombreArray){
        ArrayList<Dados> auxiliar;
        if(nombreArray == "activos"){
            auxiliar = dados;
        }else{
            if(nombreArray == "inactivos"){
                auxiliar = dadosInactivos;
            }else{
                auxiliar = dadosUtilizados;
            }
        }
        return auxiliar;
    }

    public void dadosUtilizados(String nombreDado){
        for(int dado=0; dado < dados.size(); dado++){
            nombreAObjeto.put(dados.get(dado).getNombreDado(), dados.get(dado));
        }

        dadosUtilizados.add(nombreAObjeto.get(nombreDado));
        dados.remove(nombreAObjeto.get(nombreDado));
        identidadDado("activos");
        identidadDado("utilizados");
        identidadDado("inactivos");
        nombreAObjeto.clear();
    }

    // Inicio del juego
    public void lanzamientoDados(){
        asignacionAcciones(); // Asigna todas las acciones del ArrayList dados
        setActivo(); // Establece estado activo a todos los dados
        dadosInactivos(); // Selecciona 3 dados inactivos y los borra del Arraylist dados
        identidadDado("activos"); // Actualiza los nombres del ArrayList dados
        identidadDado("inactivos"); // Actualiza los nombres del ArrayList dadosInactivos
    }

    // Escoge 3 dados inactivos al azar
    public void dadosInactivos(){
        for(int inactivo=0; inactivo < 3; inactivo++){
            Random random = new Random();
            dadoRandom = random.nextInt(dados.size());
            dados.get(dadoRandom).setActivoInactivo("inactivo");
            dadosInactivos.add(dados.get(dadoRandom));
            dados.remove(dadoRandom);
        }

        identidadDado("activos");
        identidadDado("inactivos");
    }

    // Asigna el nombre a cada dado dependiendo del ArrayList
    public void identidadDado(String array){
        if(array == "activos"){
            for(int dado=0; dado < dados.size(); dado++){
                dados.get(dado).setNombreDado("dado" + String.valueOf(dado+1));
            }
        }else{
            if(array == "inactivos"){
                for(int dado=0; dado < dadosInactivos.size(); dado++){
                    dadosInactivos.get(dado).setNombreDado("dado" + String.valueOf(dado+1));
                }
            }else{
                for(int dado=0; dado < dadosUtilizados.size(); dado++){
                    dadosUtilizados.get(dado).setNombreDado("dado" + String.valueOf(dado+1));
                }
            }
        }
    }

    // Establece el estado inicial (activo) del ArrayList dados
    public void setActivo(){
        for(int dado=0; dado < dados.size(); dado++){
            dados.get(dado).setActivoInactivo("activo");
        }
    }

    public void listaAcciones(){
        for(int numero=0; numero < dados.size(); numero++){
            switch(dados.get(numero).getNumAccion()){
                case 1:
                    dados.get(numero).setAccion("mepple");
                    break;
                case 2:
                    dados.get(numero).setAccion("superheroe");
                    break;
                case 3:
                    dados.get(numero).setAccion("dragon");
                    break;
                case 4:
                    dados.get(numero).setAccion("corazon");
                    break;
                case 5:
                    dados.get(numero).setAccion("cohete");
                    break;
                case 6:
                    dados.get(numero).setAccion("42");
                    break;
                default:
                    break;
            }
        }
    }

    // Asigna las acciones a cada dado
    public void asignacionAcciones(){
        for(int i=0; i < dados.size(); i++){
            dados.get(i).setNumAccion();
        }

        listaAcciones();
    }

    public void adicionarDado(String array, String nombreDado){
        for(int dado=0; dado < dadosInactivos.size(); dado++){
            nombreAObjeto.put(dadosInactivos.get(dado).getNombreDado(), dadosInactivos.get(dado));
        }

        listaDados(array).add(nombreAObjeto.get(nombreDado));
        dadosInactivos.remove(nombreAObjeto.get(nombreDado));
        identidadDado("activos");
        identidadDado("inactivos");
        nombreAObjeto.clear();
    }

    // Retorna la accion de un dado, dependiendo del array donde este
    public String getAccionDado(String _nombreDado, String nombreArray){
        String accionDado = "";
        if(nombreArray == "activos"){
            for(int dado=0; dado < dados.size(); dado++){
                nombreAAccion.put(dados.get(dado).getNombreDado(), dados.get(dado).getAccion());
            }
            accionDado = nombreAAccion.get(_nombreDado);
        }else{
            for(int dado=0; dado < dadosInactivos.size(); dado++){
                nombreAAccion.put(dados.get(dado).getNombreDado(), dados.get(dado).getAccion());
            }
            accionDado = nombreAAccion.get(_nombreDado);
        }

        return accionDado;
    }

    // Busca el dado seleccionado por su numero y lo tira nuevamente
    public void accionMepple(String nombreDado){
        for(int dado=0; dado < dados.size(); dado++){
            nombreAObjeto.put(dados.get(dado).getNombreDado(), dados.get(dado));
        }

        nombreAObjeto.get(nombreDado).setNumAccion();
        listaAcciones();
        nombreAObjeto.clear();
    }

    public void accionSuperHeroe(String nombreDado){
        for(int dado=0; dado < dados.size(); dado++){
            nombreAObjeto.put(dados.get(dado).getNombreDado(), dados.get(dado));
        }

        if(nombreAObjeto.get(nombreDado).getAccion() == "mepple"){
            nombreAObjeto.get(nombreDado).setNumAccionNoAleatorio(5);
            listaAcciones();
        }else{
            if(nombreAObjeto.get(nombreDado).getAccion() == "superheroe"){
                nombreAObjeto.get(nombreDado).setNumAccionNoAleatorio(3);
                listaAcciones();
            }else{
                if(nombreAObjeto.get(nombreDado).getAccion() == "dragon"){
                    nombreAObjeto.get(nombreDado).setNumAccionNoAleatorio(2);
                    listaAcciones();
                }else{
                    if(nombreAObjeto.get(nombreDado).getAccion() == "corazon"){
                        nombreAObjeto.get(nombreDado).setNumAccionNoAleatorio(6);
                        listaAcciones();
                    }else{
                        if(nombreAObjeto.get(nombreDado).getAccion() == "cohete"){
                            nombreAObjeto.get(nombreDado).setNumAccionNoAleatorio(1);
                            listaAcciones();
                        }else{
                            if(nombreAObjeto.get(nombreDado).getAccion() == "42"){
                                nombreAObjeto.get(nombreDado).setNumAccionNoAleatorio(4);
                                listaAcciones();
                            }
                        }
                    }
                }
            }
        }
        nombreAObjeto.clear();
    }

    public void accionCorazon(String nombreDado){
        for(int dado=0; dado < dadosInactivos.size(); dado++){
            nombreAObjeto.put(dadosInactivos.get(dado).getNombreDado(), dadosInactivos.get(dado));
        }

        nombreAObjeto.get(nombreDado).setNumAccion();
        nombreAObjeto.get(nombreDado).setActivoInactivo("activo");
        dados.add(nombreAObjeto.get(nombreDado));
        listaAcciones();
        dadosInactivos.remove(nombreAObjeto.get(nombreDado));
        identidadDado("activos");
        identidadDado("inactivos");
        nombreAObjeto.clear();
    }

    public void accionCohete(String nombreDado){
        for(int dado=0; dado < dados.size(); dado++){
            nombreAObjeto.put(dados.get(dado).getNombreDado(), dados.get(dado));
        }

        nombreAObjeto.get(nombreDado).setActivoInactivo("inactivo");
        dadosInactivos.add(nombreAObjeto.get(nombreDado));
        dados.remove(nombreAObjeto.get(nombreDado));
        identidadDado("activos");
        identidadDado("inactivos");
        nombreAObjeto.clear();
    }
}