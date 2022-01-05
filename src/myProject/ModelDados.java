package myProject;
/**
 * Clase ModelDados
 * @autor Mayra Alejandra Sanchez - mayra.alejandra.sanchez@correounivalle.edu.co - 202040506
 * @autor Brayan Stiven Sanchez - brayan.sanchez.leon@correounivalle.edu.co - 202043554
 * @version 1.0.0 fecha 3/1/2022
 */
public class ModelDados {
    private Dados dado1, dado2, dado3,dado4,dado5,dado6,dado7,dado8, dado9, dado10;
    private int estado, flag;
    private String[] estadoToString;
    private int[] dados;
    private int[] acciones;

    /**
     * Clase Constructor
     */
    public ModelDados() {
        dado1 = new Dados();
        dado2 = new Dados();
        dado3 = new Dados();
        dado4 = new Dados();
        dado5 = new Dados();
        dado6 = new Dados();
        dado7 = new Dados();
        dado8 = new Dados();
        dado9 = new Dados();
        dado10 = new Dados();
        dados = new int[10];
        acciones = new int[6];
        estadoToString = new String[2];
        flag = 0;
    }

    public void tiro(){
        dados[0] = dado1.getAcciones();
        dados[1] = dado2.getAcciones();
        dados[2] = dado3.getAcciones();
        dados[3] = dado4.getAcciones();
        dados[4] = dado5.getAcciones();
        dados[5] = dado6.getAcciones();
        dados[6] = dado7.getAcciones();
        dados[7] = dado8.getAcciones();
        dados[8] = dado9.getAcciones();
        dados[9] = dado10.getAcciones();

        if (flag == 0) {
            flag = 1;
        } else {
            flag = 0;
        }
/*
        switch (acciones[0]) {
            case 1:
                palosString[0] = "oro";
                break;
            case 2:
                palosString[0] = "copas";
                break;
            case 3:
                palosString[0] = "espadas";
                break;
            case 4:
                palosString[0] = "bastos";
                break;
            default:
                break;
        }
*/

    }
}
