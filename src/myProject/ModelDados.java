package myProject;

import java.util.ArrayList;
import java.util.Random;
/**
 * Clase Cartas genera un valor random entre 1 y 40
 * @autor Mayra Alejandra Sanchez - mayra.alejandra.sanchez@correounivalle.edu.co - 202040506
 * @autor Brayan Stiven Sanchez - brayan.sanchez.leon@correounivalle.edu.co - 202043554
 * @version 1.0.0 fecha 3/1/2022
 */
public class ModelDados {
    Dados dado1 = new Dados(1);
    Dados dado2 = new Dados(2);
    Dados dado3 = new Dados(3);
    Dados dado4 = new Dados(4);
    Dados dado5 = new Dados(5);
    Dados dado6 = new Dados(6);
    Dados dado7 = new Dados(7);
    Dados dado8 = new Dados(8);
    Dados dado9 = new Dados(9);
    Dados dado10 = new Dados(10);

    private int dadoRandom;
    private ArrayList<Dados> dados;
    private ArrayList<Dados> dadosInactivos;
    private ArrayList<Dados> dadosUtilizados;
    private int puntaje;
    private int ronda;
    private int flag;

    public ModelDados(){
        dados = new ArrayList<>();
        dados.add(dado1);
        dados.add(dado2);
        dados.add(dado3);
        dados.add(dado4);
        dados.add(dado5);
        dados.add(dado6);
        dados.add(dado7);
        dados.add(dado8);
        dados.add(dado9);
        dados.add(dado10);
        dadosInactivos = new ArrayList<>();
        dadosUtilizados = new ArrayList<>();
        flag = 0;
        ronda = 1;
    }

    // Escoge 3 dados inactivos al azar
    public void dadosInactivos(){
        for(int inactivo=0; inactivo < 3; inactivo++){
            Random random = new Random();
            dadoRandom = random.nextInt(dados.size());
            dadosInactivos.add(dados.get(dadoRandom));
            dados.remove(dadoRandom);
        }
    }

    // asigna las acciones a cada dado
    public void asignacionAcciones(){
        for(int i=0; i < dados.size(); i++){
            dados.get(i).setNumAccion();
        }

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
        flag = 1;
    }

    public void dadosUtilizados(String accion){
        for(int dado=0; dado < dados.size(); dado++){
            if(accion == dados.get(dado).getAccion()){
                dadosUtilizados.add(dados.get(dado));
                dados.remove(dado);
                break;
            }
        }
    }

    // Busca el dado seleccionado por su numero y lo tira nuevamente
    public void accionMepple(String accion){
        for(int dado=0; dado < dados.size(); dado++){
            if(accion == dados.get(dado).getAccion()){
                dados.get(dado).setNumAccion();
                break;
            }
        }
    }

    public void accionSuperHeroe(String accion){
        for(int dado=0; dado < dados.size(); dado++){
            if(accion == dados.get(dado).getAccion()){
                if(accion == "mepple"){
                    dados.get(dado).setNumAccionNoAleatorio(5);
                    break;
                }else{
                    if(accion == "cohete"){
                        dados.get(dado).setNumAccionNoAleatorio(1);
                        break;
                    }else{
                        if(accion == "superheroe"){
                            dados.get(dado).setNumAccionNoAleatorio(3);
                            break;
                        }else{
                            if(accion == "dragon"){
                                dados.get(dado).setNumAccionNoAleatorio(2);
                                break;
                            }else{
                                if(accion == "corazon"){
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

    public void accionCorazon(String accion){
        for(int dado=0; dado < dadosInactivos.size(); dado++){
            if(accion == dadosInactivos.get(dado).getAccion()){
                dados.add(dadosInactivos.get(dado));
                dados.get(dados.indexOf(dadosInactivos.get(dado))).getNumAccion();
                dadosInactivos.remove(dado);
                break;
            }
        }
    }

    public void accionCohete(String accion){
        for(int dado=0; dado < dados.size(); dado++){
            if(accion == dados.get(dado).getAccion()){
                dadosInactivos.add(dados.get(dado));
                dados.remove(dado);
                break;
            }
        }
    }

    public void accion42(String accion){
        for(int dado=0; dado < dados.size(); dado++){
            if(accion == dados.get(dado).getAccion()){
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
        if(dados.size() == 1){
            flag = 1;
        }
        return flag;
    }

}
