/**
 *Clase principal
 * @autor Mayra Alejandra Sanchez - mayra.alejandra.sanchez@correounivalle.edu.co - 202040506
 * @autor Brayan Stiven Sanchez - brayan.sanchez.leon@correounivalle.edu.co - 202043554
 * @version 1.0.0 fecha 3/1/2022
 */
package myProject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

public class GUI extends JFrame{

    public  static final String CREDITOS="CREDITOS\n" +
            "BRAYAN STIVEN SANCHEZ LEON\n" +
            "MAYRA ALEJANDRA SANCHEZ SALINAS";

    public  static final String AYUDA="-> De los 10 dados que trae el juego se toman 3 y se colocan en el sector de \"Dados Inactivos\". Los otros 7\n" +
            "dados se tiran y pasan a ser los \"Dados Activos\".\n" +
            "-> Se van eligiendo los dados a utilizar según las habilidades de sus caras y se pasan al sector de \"Dados\n" +
            "Utilizados\".\n" +
            "-> Si como último dado activo queda un Dragón, se perderán todos los puntos acumulados.\n" +
            "-> Este juego lo jugará un único jugador y ganará si logra sumar 30 puntos en 5 rondas consecutivas de juego. ";

    private Header headerProject;
    private JLabel mano;
    private JButton lanzar, ayuda, salir, creditos, botonExplicacion;
    private JPanel panelDadosActivos, panelDadosUtilizados, panelDadosInactivos, panelPuntaje;
    private ImageIcon imageMano, imageExplicacion, imageDado;
    private JTextArea mensajesSalida;//,resultadosDados;
    private Escucha escucha;
    private CambiarImagen cambiarImagen;
    private AccionSuperHeroe superheroe;
    private AccionCorazon corazon;
    private AccionCohete cohete;
    private ModelDados modelDados;
    private ArrayList<JButton> botones;
    private ArrayList<JButton> botonesUtilizados;
    private ArrayList<JButton> botonesInactivos;
    private HashMap<String, JButton> valorBotones;
    private HashMap<JButton, String> botonANombre;
    private int nuevoEscucha = 0; // Dependiendo del numero usa un MouseListener distinto
    private int puntaje;
    private int ronda;

    /**
     * Constructor de la clase GUI
     */
    public GUI(){
        initGUI();

        //Configuracion por defecto del JFrame
        this.setTitle("Geek Out Masters");
        this.setUndecorated(true);
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Este método se utiliza para configurar la configuración predeterminada de JComponent,
     * crear objetos de escucha y control utilizados para la clase GUI
     */
    private void initGUI() {
        //Configurar el diseño del contenedor JFrame
        this.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        //Crear objeto de escucha y objeto de control
        escucha = new Escucha();
        cambiarImagen = new CambiarImagen();
        superheroe = new AccionSuperHeroe();
        corazon = new AccionCorazon();
        cohete = new AccionCohete();
        modelDados = new ModelDados();
        botones = new ArrayList<>();
        botonesUtilizados = new ArrayList<>();
        botonesInactivos = new ArrayList<>();
        valorBotones = new HashMap<>();
        botonANombre = new HashMap<>();
        puntaje = 0;
        ronda = 1;

        //Configurar JComponents

        //Titulo
        headerProject = new Header("Geek Out Masters", Color.BLACK);

        constraints.gridx=3;
        constraints.gridy=1;
        constraints.gridwidth=3;
        constraints.fill=GridBagConstraints.HORIZONTAL;
        this.add(headerProject,constraints);

        /**
         * Creacion de boton "Ayuda"
         */

        ayuda = new JButton(" help ");
        ayuda.addActionListener(escucha);
        ayuda.setBackground(Color.green);
        constraints.gridx=0;
        constraints.gridy=1;
        constraints.gridwidth=2;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.LINE_START;
        this.add(ayuda,constraints);

        /**
         * Creacion de boton "Creditos"
         */

        creditos = new JButton(" Creditos ");
        creditos.addActionListener(escucha);
        creditos.setBackground(Color.yellow);
        constraints.gridx=1;
        constraints.gridy=4;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.BOTH;
        constraints.anchor=GridBagConstraints.CENTER;
        this.add(creditos,constraints);

        /**
         * Creacion de boton "Salir"
         */

        salir = new JButton("Salir");
        salir.addActionListener(escucha);
        salir.setBackground(Color.red);
        constraints.gridx=8;
        constraints.gridy=1;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.LINE_END;
        this.add(salir,constraints);

        /**
         * Creacion de boton "Explicacion dados"
         */

        botonExplicacion = new JButton("Explicación dados");
        botonExplicacion.addActionListener(escucha);
        botonExplicacion.setBackground(Color.ORANGE);
        constraints.gridx=4;
        constraints.gridy=2;
        constraints.gridwidth=3;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.CENTER;
        this.add(botonExplicacion,constraints);

        //Imagen
        imageMano = new ImageIcon(getClass().getResource("/utilidad/mano apretada.png"));
        mano = new JLabel(imageMano);

        /**
         * Creacion de dados activos
         */

        panelDadosActivos = new JPanel();
        panelDadosActivos.setPreferredSize(new Dimension(300,300));
        panelDadosActivos.setBorder(BorderFactory.createTitledBorder("Tira los dados"));
        panelDadosActivos.setBackground(Color.white);
        panelDadosActivos.add(mano);

        constraints.gridx=3;
        constraints.gridy=3;
        constraints.gridwidth=3;
        constraints.fill=GridBagConstraints.BOTH;
        constraints.anchor=GridBagConstraints.CENTER;
        add(panelDadosActivos,constraints);

        /**
         * Creacion de panel dados utilizados
         */

        panelDadosUtilizados = new JPanel();
        panelDadosUtilizados.setPreferredSize(new Dimension(200,180));
        panelDadosUtilizados.setBorder(BorderFactory.createTitledBorder("Dados utilizados"));
        panelDadosUtilizados.setBackground(Color.cyan);

        constraints.gridx=0;
        constraints.gridy=2;
        constraints.gridwidth=3;
        constraints.fill=GridBagConstraints.BOTH;
        constraints.anchor=GridBagConstraints.CENTER;
        add(panelDadosUtilizados,constraints);

        /**
         * Creacion de panel dados inactivos
         */

        panelDadosInactivos = new JPanel();
        panelDadosInactivos.setPreferredSize(new Dimension(200,180));
        panelDadosInactivos.setBorder(BorderFactory.createTitledBorder("Dados Inactivos"));
        panelDadosInactivos.setBackground(Color.CYAN);

        constraints.gridx=6;
        constraints.gridy=2;
        constraints.gridwidth=3;
        constraints.fill=GridBagConstraints.BOTH;
        constraints.anchor=GridBagConstraints.CENTER;
        add(panelDadosInactivos,constraints);


        /**
         * Creacion de panel puntaje
         */

        panelPuntaje = new JPanel();
        panelPuntaje.setPreferredSize(new Dimension(200,225));
        panelPuntaje.setBorder(BorderFactory.createTitledBorder("Puntaje"));

        constraints.gridx=0;
        constraints.gridy=3;
        constraints.gridwidth=3;
        constraints.fill=GridBagConstraints.BOTH;
        constraints.anchor=GridBagConstraints.CENTER;
        add(panelPuntaje,constraints);

        /**
         * Creacion de boton "tirar dados
         */

        lanzar = new JButton("Tirar los dados");
        lanzar.addActionListener(escucha);
        lanzar.setBackground(Color.pink);

        constraints.gridx=7;
        constraints.gridy=3;
        constraints.gridwidth=3;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.CENTER;
        add(lanzar,constraints);

        mensajesSalida= new JTextArea(2,28);
        mensajesSalida.setText("Usa el botón (help) para ver las reglas del juego");
        mensajesSalida.setBorder(BorderFactory.createTitledBorder("Atención: "));
        mensajesSalida.setEditable(false);
        constraints.gridx=3;
        constraints.gridy=5;
        constraints.gridwidth=3;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.CENTER;
        add(mensajesSalida,constraints);

        // Creacion de dados

        modelDados.lanzamientoDados();

        // Inicializacion dados activos
        for(int dado=0; dado < modelDados.listaDados("activos").size(); dado++){
            botones.add(new JButton());
            botones.get(dado).setName("dado" + String.valueOf(dado+1));
            botones.get(dado).setBorder(null);
            botones.get(dado).setBackground(Color.white);
            botones.get(dado).addMouseListener(escucha);
            botones.get(dado).setVisible(false);
            imageDado = new ImageIcon(getClass().getResource("/recursos/" + modelDados.getAccionDado("dado" + String.valueOf(dado+1), "activos") + ".png"));
            botones.get(dado).setIcon(new ImageIcon(imageDado.getImage().getScaledInstance(80,80, Image.SCALE_DEFAULT)));
            panelDadosActivos.add(botones.get(dado));
        }

        // Inicializacion dados inactivos
        for(int dado=0; dado < modelDados.listaDados("inactivos").size(); dado++){
            botonesInactivos.add(new JButton());
            botonesInactivos.get(dado).setName("dado" + String.valueOf(dado+1));
            botonesInactivos.get(dado).setBorder(null);
            botonesInactivos.get(dado).setBackground(Color.cyan);
            botonesInactivos.get(dado).setVisible(false);
            imageDado = new ImageIcon(getClass().getResource("/recursos/" + modelDados.getAccionDado("dado" + String.valueOf(dado+1), "inactivos") + ".png"));
            botonesInactivos.get(dado).setIcon(new ImageIcon(imageDado.getImage().getScaledInstance(80,80, Image.SCALE_DEFAULT)));
            panelDadosInactivos.add(botonesInactivos.get(dado));
        }
    }

    /**
     * Main process of the Java program
     * @param args Object used in order to send input data from command line when
     *             the program is execute by console.
     */
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            myProject.GUI miProjectGUI = new myProject.GUI();
        });
    }

    public void actualizarPanel(String nombrePanel){
        if(nombrePanel == "activos"){
            panelDadosActivos.removeAll();
            for(int boton=0; boton < botones.size(); boton++){
                panelDadosActivos.add(botones.get(boton));
            }
            panelDadosActivos.updateUI();
        }else{
            if(nombrePanel == "inactivos"){
                panelDadosInactivos.removeAll();
                for(int boton=0; boton < botonesInactivos.size(); boton++){
                    panelDadosInactivos.add(botonesInactivos.get(boton));
                }
                panelDadosInactivos.updateUI();
            }else{
                panelDadosUtilizados.removeAll();
                for(int boton=0; boton < botonesUtilizados.size(); boton++){
                    panelDadosUtilizados.add(botonesUtilizados.get(boton));
                }
                panelDadosUtilizados.updateUI();
            }
        }
    }

    // Renombra los botones cada que se elimine o agregue un elemento de algun ArrayList
    public void renombrarBotones(String nombreArray){
        if(nombreArray == "activos"){
            for(int boton=0; boton < botones.size(); boton++){
                botones.get(boton).setName("dado" + String.valueOf(boton+1));
            }
        }else{
            if(nombreArray == "inactivos"){
                for(int boton=0; boton < botonesInactivos.size(); boton++){
                    botonesInactivos.get(boton).setName("dado" + String.valueOf(boton+1));
                }
            }else{
                for(int boton=0; boton < botonesUtilizados.size(); boton++){
                    botonesUtilizados.get(boton).setName("dado" + String.valueOf(boton+1));
                }
            }
        }
    }

    // Hace pares de nombre y JButton dependiendo del ArrayList y me retorna el JButton
    public JButton mappingJButton(String nombreArray, String nombreDado){
        if(nombreArray == "activos"){
            for(int boton=0; boton < botones.size(); boton++){
                valorBotones.put(botones.get(boton).getName(), botones.get(boton));
            }
        }else{
            if(nombreArray == "inactivos"){
                for(int boton=0; boton < botonesInactivos.size(); boton++){
                    valorBotones.put(botonesInactivos.get(boton).getName(), botonesInactivos.get(boton));
                }
            }else{
                for(int boton=0; boton < botonesUtilizados.size(); boton++){
                    valorBotones.put(botonesUtilizados.get(boton).getName(), botonesUtilizados.get(boton));
                }
            }
        }

        return valorBotones.get(nombreDado);
    }

    public void rondas(){
        System.out.println(botonesUtilizados.get(0).getName());
        int acumulador = 0; // 42
        int acumulador2 = 0; // dragones

        if(botones.size() == 0){
            puntaje += 0;
            ronda += 1;
            System.out.println("Tu puntaje es: " + String.valueOf(puntaje));
            System.out.println("Ronda: " + String.valueOf(ronda));
        }else{
            if(botones.size() == 1){
                if(modelDados.getAccionDado(botones.get(0).getName(), "activos") == "42"){
                    puntaje += 1;
                    ronda += 1;
                    System.out.println("Tu puntaje es: " + String.valueOf(puntaje));
                    System.out.println("Ronda: " + String.valueOf(ronda));
                }else{
                    if(modelDados.getAccionDado(botones.get(0).getName(), "activos") == "dragon"){
                        puntaje = 0;
                        ronda += 1;
                        System.out.println("Tu puntaje es: " + String.valueOf(puntaje));
                        System.out.println("Ronda: " + String.valueOf(ronda));
                    }else{
                        if(modelDados.getAccionDado(botones.get(0).getName(), "activos") == "corazon"){
                            nuevoEscucha = 0;
                            escuchas();
                        }else{
                            puntaje += 0;
                            ronda += 1;
                            System.out.println("Tu puntaje es: " + String.valueOf(puntaje));
                            System.out.println("Ronda: " + String.valueOf(ronda));
                        }
                    }
                }
            }else{
                // Cuenta el total de dados 42
                for (int boton=0; boton < botones.size(); boton++){
                    if(modelDados.getAccionDado(botones.get(boton).getName(), "activos") == "42"){
                        acumulador += 1;
                    }else{
                        if(modelDados.getAccionDado(botones.get(boton).getName(), "activos") == "dragon"){
                            acumulador2 += 1;
                        }else{
                            acumulador += 0;
                            acumulador2 += 0;
                        }
                    }
                }
                // Si la cantidad de dados 42 es igual al tamaño del ArrayList, gana
                if(acumulador == botones.size()){
                    switch (acumulador){
                        case 1:
                            puntaje += 1;
                            ronda += 1;
                            System.out.println("Tu puntaje es: " + String.valueOf(puntaje));
                            System.out.println("Ronda: " + String.valueOf(ronda));
                            break;
                        case 2:
                            puntaje += 3;
                            ronda += 1;
                            System.out.println("Tu puntaje es: " + String.valueOf(puntaje));
                            System.out.println("Ronda: " + String.valueOf(ronda));
                            break;
                        case 3:
                            puntaje += 6;
                            ronda += 1;
                            System.out.println("Tu puntaje es: " + String.valueOf(puntaje));
                            System.out.println("Ronda: " + String.valueOf(ronda));
                            break;
                        case 4:
                            puntaje += 10;
                            ronda += 1;
                            System.out.println("Tu puntaje es: " + String.valueOf(puntaje));
                            System.out.println("Ronda: " + String.valueOf(ronda));
                            break;
                        case 5:
                            puntaje += 15;
                            ronda += 1;
                            System.out.println("Tu puntaje es: " + String.valueOf(puntaje));
                            System.out.println("Ronda: " + String.valueOf(ronda));
                            break;
                        case 6:
                            puntaje += 21;
                            ronda += 1;
                            System.out.println("Tu puntaje es: " + String.valueOf(puntaje));
                            System.out.println("Ronda: " + String.valueOf(ronda));
                            break;
                        case 7:
                            puntaje += 28;
                            ronda += 1;
                            System.out.println("Tu puntaje es: " + String.valueOf(puntaje));
                            System.out.println("Ronda: " + String.valueOf(ronda));
                            break;
                        case 8:
                            puntaje += 36;
                            ronda += 1;
                            System.out.println("Tu puntaje es: " + String.valueOf(puntaje));
                            System.out.println("Ronda: " + String.valueOf(ronda));
                            break;
                        case 9:
                            puntaje += 45;
                            ronda += 1;
                            System.out.println("Tu puntaje es: " + String.valueOf(puntaje));
                            System.out.println("Ronda: " + String.valueOf(ronda));
                            break;
                        case 10:
                            puntaje += 55;
                            ronda += 1;
                            System.out.println("Tu puntaje es: " + String.valueOf(puntaje));
                            System.out.println("Ronda: " + String.valueOf(ronda));
                            break;
                        default:
                            break;
                    }
                }else{
                    if(acumulador + acumulador2 == botones.size()){
                        puntaje = 0;
                        ronda += 1;
                        System.out.println("Tu puntaje es: " + String.valueOf(puntaje));
                        System.out.println("Ronda: " + String.valueOf(ronda));
                    }else{
                        System.out.println("Sigue jugando");
                    }
                }
            }
        }
    }

    public void escuchas(){
        class GetEscuchas implements MouseListener{
            @Override
            public void mouseClicked(MouseEvent e) {
                switch (nuevoEscucha){
                    case 0:
                        escucha.mouseClicked(e);
                        break;
                    case 1:
                        cambiarImagen.mouseClicked(e);
                        break;
                    case 2:
                        superheroe.mouseClicked(e);
                        break;
                    case 3:
                        corazon.mouseClicked(e);
                        break;
                    case 4:
                        cohete.mouseClicked(e);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        }
    }

    // Realiza la accion del dado cohete
    private class AccionCohete implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            String botonSecundario = "";
            botonSecundario = e.getComponent().getName();
            modelDados.accionCohete(botonSecundario);
            mappingJButton("activos", botonSecundario).setEnabled(false); // Deshabilita el boton
            mappingJButton("activos", botonSecundario).removeMouseListener(this); // Remueve el MouseListener cohete
            botonesInactivos.add(mappingJButton("activos", botonSecundario)); // Adiciona el boton en la lista inactivos
            renombrarBotones("inactivos");
            botones.remove(mappingJButton("activos", botonSecundario)); // Borra el boton de la lista activos
            renombrarBotones("activos");
            actualizarPanel("inactivos");
            actualizarPanel("activos");

            for(int boton=0; boton < botones.size(); boton++){
                botones.get(boton).removeMouseListener(this);
                botones.get(boton).addMouseListener(escucha);
            }

            nuevoEscucha = 0;
            escuchas();
            rondas();
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    // Realiza la accion del dado corazon
    private class AccionCorazon implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            String botonSecundario = "";
            botonSecundario = e.getComponent().getName();
            modelDados.accionCorazon(botonSecundario);
            botones.add(mappingJButton("inactivos", botonSecundario)); // Adiciona el boton en la lista activos
            renombrarBotones("activos");
            botonesInactivos.remove(mappingJButton("inactivos", botonSecundario)); // Borra el boton de la lista inactivos
            renombrarBotones("inactivos");
            imageDado = new ImageIcon(getClass().getResource("/recursos/" + modelDados.getAccionDado("dado" + String.valueOf(botones.size()), "activos") + ".png")); // Al invocar accionCorazon, el dado ingresa de ultimo a la lista de activos
            mappingJButton("activos", "dado" + String.valueOf(botones.size())).setIcon(new ImageIcon(imageDado.getImage().getScaledInstance(80,80, Image.SCALE_DEFAULT)));
            actualizarPanel("activos");
            actualizarPanel("inactivos");

            for(int boton=0; boton < botonesInactivos.size(); boton++){
                botonesInactivos.get(boton).removeMouseListener(this);
                botonesInactivos.get(boton).setEnabled(false);
            }

            for(int boton=0; boton < botones.size(); boton++){
                botones.get(boton).removeMouseListener(this);
                botones.get(boton).addMouseListener(escucha);
            }
            nuevoEscucha = 0;
            escuchas();
            rondas();
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    // Realiza la accion del dado Superheroe
    private class AccionSuperHeroe implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            String botonSecundario = "";
            botonSecundario = e.getComponent().getName();
            modelDados.accionSuperHeroe(botonSecundario);
            imageDado = new ImageIcon(getClass().getResource("/recursos/" + modelDados.getAccionDado(botonSecundario, "activos") + ".png"));
            mappingJButton("activos", botonSecundario).setIcon(new ImageIcon(imageDado.getImage().getScaledInstance(80,80, Image.SCALE_DEFAULT)));

            for(int boton=0; boton < botones.size(); boton++){
                botones.get(boton).removeMouseListener(this);
                botones.get(boton).addMouseListener(escucha);
            }
            nuevoEscucha = 0;
            escuchas();
            rondas();
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    // Realiza la accion del dado Mepple
    private class CambiarImagen implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            String botonSecundario = "";
            botonSecundario = e.getComponent().getName();
            modelDados.accionMepple(botonSecundario);
            imageDado = new ImageIcon(getClass().getResource("/recursos/" + modelDados.getAccionDado(botonSecundario, "activos") + ".png"));
            mappingJButton("activos", botonSecundario).setIcon(new ImageIcon(imageDado.getImage().getScaledInstance(80,80, Image.SCALE_DEFAULT)));

            for(int boton=0; boton < botones.size(); boton++){
                botones.get(boton).removeMouseListener(this);
                botones.get(boton).addMouseListener(escucha);
            }
            nuevoEscucha = 0;
            escuchas();
            rondas();
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    /**
     * inner class that extends an Adapter Class or implements Listeners used by GUI class
     */

    // Evento principal cuando se lanza los dados y se presiona un dado
    private class Escucha implements ActionListener, MouseListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if(e.getSource()==lanzar){
                /**
                * Quita la mano que esta al inicio del juego
                */
                mano.setVisible(false);
                /**
                 * Aparecen los dados activos e inactivos
                 */

                for(int dado=0; dado < botones.size(); dado++){
                    botones.get(dado).setVisible(true);
                }

                for(int dado=0; dado < botonesInactivos.size(); dado++){
                    botonesInactivos.get(dado).setVisible(true);
                    botonesInactivos.get(dado).setEnabled(false);
                }

            }else{
                if(e.getSource()==creditos){
                    JOptionPane.showMessageDialog(null,CREDITOS);
                }else{
                    if (e.getSource()==ayuda){
                        JOptionPane.showMessageDialog(null,AYUDA);
                    }else{
                        if (e.getSource()==botonExplicacion){
                            imageExplicacion = new ImageIcon(getClass().getResource("/utilidad/explicacion.png"));
                            JOptionPane.showMessageDialog(null,"","Explicacion de cada cara del dado", JOptionPane.PLAIN_MESSAGE, imageExplicacion);
                        }else{
                            System.exit(0);
                        }
                    }
                }
            }
        }

        /**
         * Mouse clicked para los dados
         * @param e
         */

        @Override
        public void mouseClicked(MouseEvent e) {

            /**
             * Aqui empieza el juego, ya que al dar click en un dado se va a jugar con este
             */

            String nombreBoton = "";
            String nombreAccion = "";

            nombreBoton = e.getComponent().getName();
            nombreAccion = modelDados.getAccionDado(nombreBoton, "activos");
            mappingJButton("activos", nombreBoton).setEnabled(false); // Deshabilita el boton despues de presionarlo
            //panelDadosUtilizados.add(mappingJButton("activos", nombreBoton)); // Agrega el boton a la zona de utilizados
            //panelDadosActivos.remove(mappingJButton("activos", nombreBoton));
            botonesUtilizados.add(mappingJButton("activos", nombreBoton));
            renombrarBotones("utilizados"); // Actualiza los nombres de los botones del ArrayList utilizados
            botones.remove(mappingJButton("activos", nombreBoton));
            renombrarBotones("activos"); // Actualiza los nombres de los botones del ArrayList activos
            modelDados.dadosUtilizados(nombreBoton); // Remueve el dado de la zona de activos y lo mueve a utilizados
            actualizarPanel("activos");
            actualizarPanel("utilizados");
            valorBotones.clear();

            if(nombreAccion == "mepple") {
                for(int boton=0; boton < botones.size(); boton++){
                    botones.get(boton).removeMouseListener(this);
                    botones.get(boton).addMouseListener(cambiarImagen);
                }
                nuevoEscucha = 1;
                escuchas();
            }else{
                if(nombreAccion == "superheroe") {
                    for(int boton=0; boton < botones.size(); boton++){
                        botones.get(boton).removeMouseListener(this);
                        botones.get(boton).addMouseListener(superheroe);
                    }
                    nuevoEscucha = 2;
                    escuchas();
                }else{
                    if(nombreAccion == "dragon") {
                        nuevoEscucha = 0;
                        puntaje = 0;
                        escuchas();
                        rondas();
                    }else{
                        if(nombreAccion == "corazon") {
                            for(int boton=0; boton < botones.size(); boton++){
                                botones.get(boton).removeMouseListener(this);
                            }

                            for(int boton=0; boton < botonesInactivos.size(); boton++){
                                botonesInactivos.get(boton).setEnabled(true);
                                botonesInactivos.get(boton).addMouseListener(corazon);
                            }
                            nuevoEscucha = 3;
                            escuchas();
                        }else{
                            if(nombreAccion == "cohete") {
                                for(int boton=0; boton < botones.size(); boton++){
                                    botones.get(boton).removeMouseListener(this);
                                    botones.get(boton).addMouseListener(cohete);
                                }

                                nuevoEscucha = 4;
                                escuchas();
                            }else{
                                nuevoEscucha = 0;
                                escuchas();
                                rondas();
                            }
                        }
                    }
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}