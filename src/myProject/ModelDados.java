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
    private HashMap<String, String> accion;
    private HashMap<String, String> activoInactivo;
    private HashMap<String, Dados> nombreAObjeto;
    private int puntaje;
    private int ronda;
    private int auxiliar = 0;
    private int flag;


    public ModelDados(){

        // Creacion de 10 dados
        dados = new ArrayList<>();
        for(int dado=0; dado < 10; dado++){
            dados.add(new Dados());
        }

        accion = new HashMap<>();
        nombreAObjeto = new HashMap<>();
        activoInactivo = new HashMap<>();
        dadosInactivos = new ArrayList<>();
        dadosUtilizados = new ArrayList<>();
        flag = 0;
        ronda = 1;
    }

    public ArrayList listaDados(){
        return dados;
    }

    public void dadosUtilizados(String nombreDado){
        for(int dado=0; dado < dados.size(); dado++){
            if(nombreDado == dados.get(dado).getNombreDado()){
                dadosUtilizados.add(dados.get(dado));
                dados.remove(dado);
                break;
            }
        }
    }

    public void lanzamientoDados(){
        identidadDado();
        asignacionAcciones();
    }

    // Escoge 3 dados inactivos al azar
    public void dadosInactivos(){
        for(int inactivo=0; inactivo < 3; inactivo++){
            Random random = new Random();
            dadoRandom = random.nextInt(dados.size());
            dados.get(dadoRandom).setActivoInactivo("inactivo");
        }
    }

    // Asigna el nombre y estado a cada dado
    public void identidadDado(){
        for(int dado=0; dado < dados.size(); dado++){
            dados.get(dado).setNombreDado("dado" + String.valueOf(dado+1));
            dados.get(dado).setActivoInactivo("activo");
        }

        // Establece los dados inactivos
        dadosInactivos();
        for(int dado=0; dado < dados.size(); dado++){
            activoInactivo.put(dados.get(dado).getNombreDado(), dados.get(dado).getActivoInactivo());
        }
    }

    public String activoInactivo(String nombreDado){
        return activoInactivo.get(nombreDado);
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
        flag = 1;
    }

    public String getAccionDado(String _nombreDado){
        for(int dado=0; dado < dados.size(); dado++){
            accion.put(dados.get(dado).getNombreDado(), dados.get(dado).getAccion());
        }

        String accionDado = accion.get(_nombreDado);
        return accionDado;
    }

    // Busca el dado seleccionado por su numero y lo tira nuevamente
    public void accionMepple(String nombreDado){
        for(int dado=0; dado < dados.size(); dado++){
            nombreAObjeto.put(dados.get(dado).getNombreDado(), dados.get(dado));
        }

        nombreAObjeto.get(nombreDado).setNumAccion();
        listaAcciones();
    }

    public void accionSuperHeroe(String nombreDado){
        for(int dado=0; dado < dados.size(); dado++){
            if(nombreDado == dados.get(dado).getNombreDado()){
                if(dados.get(dado).getAccion() == "mepple"){
                    dados.get(dado).setNumAccionNoAleatorio(5);
                    break;
                }else{
                    if(dados.get(dado).getAccion() == "cohete"){
                        dados.get(dado).setNumAccionNoAleatorio(1);
                        break;
                    }else{
                        if(dados.get(dado).getAccion() == "superheroe"){
                            dados.get(dado).setNumAccionNoAleatorio(3);
                            break;
                        }else{
                            if(dados.get(dado).getAccion() == "dragon"){
                                dados.get(dado).setNumAccionNoAleatorio(2);
                                break;
                            }else{
                                if(dados.get(dado).getAccion() == "corazon"){
                                    dados.get(dado).setNumAccionNoAleatorio(6);
                                    break;
                                }else{
                                    dados.get(dado).setNumAccionNoAleatorio(4);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void accionDragon(){
        puntaje = 0;
    }

    public void accionCorazon(String nombreDado){
        for(int dado=0; dado < dadosInactivos.size(); dado++){
            if(nombreDado == dadosInactivos.get(dado).getNombreDado()){
                dados.add(dadosInactivos.get(dado));
                dados.get(dados.indexOf(dadosInactivos.get(dado))).getNumAccion();
                dadosInactivos.remove(dado);
                break;
            }
        }
    }

    public void accionCohete(String nombreDado){
        for(int dado=0; dado < dados.size(); dado++){
            if(nombreDado == dados.get(dado).getNombreDado()){
                dadosInactivos.add(dados.get(dado));
                dados.remove(dado);
                break;
            }
        }
    }

    public void accion42(String nombreDado){
        for(int dado=0; dado < dados.size(); dado++){
            if(nombreDado == dados.get(dado).getNombreDado()){
                switch(ronda){
                    case 1:
                        puntaje = puntaje + 1;
                        break;
                    case 2:
                        puntaje = puntaje + 3;
                        break;
                    case 3:
                        puntaje = puntaje + 6;
                        break;
                    case 4:
                        puntaje = puntaje + 10;
                        break;
                    case 5:
                        puntaje = puntaje + 15;
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public void ronda(){
        if(dados.size() == 1){
            ronda = ronda + 1;
        }

        if(dados.get(0).getAccion() == "42"){
            accion42(dados.get(0).getAccion());
        }
    }


    // estado del juego
    public int getFlag(){
        if(ronda < 5){
            flag = 1; // continua el juego
        }else{
            flag = 2; // finaliza el juego
        }
        return flag;
    }
}
