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
    private JLabel mano, textoPuntaje, textoPuntajeTotal, textoRonda;
    private JButton lanzar, ayuda, salir, creditos, botonExplicacion, continuarReiniciar;
    private JPanel panelDadosActivos, panelDadosUtilizados, panelDadosInactivos, panelPuntaje, panelRonda;
    private ImageIcon imageMano, imageExplicacion, imageDado;
    private JTextArea mensajesSalida; // resultadosDados;
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
    private int estadoDelJuego; // 0 si sigue tirando dados, 1 si ya termino la ronda

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
        estadoDelJuego = 0;

        //Configurar JComponents

        //Titulo
        headerProject = new Header("Geek Out Masters", Color.BLACK);

        constraints.gridx=3;
        constraints.gridy=1;
        constraints.gridwidth=3;
        constraints.fill=GridBagConstraints.CENTER;
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
        constraints.anchor=GridBagConstraints.CENTER;
        this.add(ayuda,constraints);

        /**
         * Creacion de boton "Creditos"
         */
        creditos = new JButton(" Creditos ");
        creditos.addActionListener(escucha);
        creditos.setBackground(Color.yellow);
        constraints.gridx=2;
        constraints.gridy=1;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.CENTER;
        constraints.anchor=GridBagConstraints.LINE_START;
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
        constraints.gridx=6;
        constraints.gridy=3;
        constraints.gridwidth=3;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.CENTER;
        this.add(botonExplicacion,constraints);

        // Imagen
        imageMano = new ImageIcon(getClass().getResource("/utilidad/mano apretada.png"));
        mano = new JLabel(imageMano);

        // Puntaje ronda
        textoPuntaje = new JLabel();
        textoPuntaje.setHorizontalAlignment(SwingConstants.CENTER);

        // Puntaje total
        textoPuntajeTotal = new JLabel();
        textoPuntajeTotal.setHorizontalAlignment(SwingConstants.CENTER);

        // Ronda
        textoRonda = new JLabel();

        /**
         * Creacion de dados activos
         */

        panelDadosActivos = new JPanel();
        panelDadosActivos.setPreferredSize(new Dimension(300,300));
        panelDadosActivos.setBorder(BorderFactory.createTitledBorder("Dados Activos"));
        panelDadosActivos.setBackground(Color.white);
        panelDadosActivos.add(mano);

        constraints.gridx=3;
        constraints.gridy=2;
        constraints.gridwidth=3;
        constraints.fill=GridBagConstraints.BOTH;
        constraints.anchor=GridBagConstraints.CENTER;
        add(panelDadosActivos,constraints);

        /**
         * Creacion de panel dados utilizados
         */

        panelDadosUtilizados = new JPanel();
        panelDadosUtilizados.setPreferredSize(new Dimension(300,300));
        panelDadosUtilizados.setBorder(BorderFactory.createTitledBorder("Dados utilizados"));
        panelDadosUtilizados.setBackground(Color.white);

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
        panelDadosInactivos.setPreferredSize(new Dimension(300,300));
        panelDadosInactivos.setBorder(BorderFactory.createTitledBorder("Dados Inactivos"));
        panelDadosInactivos.setBackground(Color.white);

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
        panelPuntaje.setLayout(new GridLayout(0,1));
        panelPuntaje.setPreferredSize(new Dimension(200,100));
        panelPuntaje.setBorder(BorderFactory.createTitledBorder("Puntaje"));
        panelPuntaje.setBackground(new Color(112, 215, 163, 255));
        panelPuntaje.add(textoPuntajeTotal);
        panelPuntaje.add(textoPuntaje);

        constraints.gridx=3;
        constraints.gridy=3;
        constraints.gridwidth=3;
        constraints.fill=GridBagConstraints.BOTH;
        constraints.anchor=GridBagConstraints.CENTER;
        add(panelPuntaje,constraints);

        /**
         *  Creación panel ronda
         */
        panelRonda = new JPanel();
        panelRonda.setPreferredSize(new Dimension(200,100));
        panelRonda.setBorder(BorderFactory.createTitledBorder("Ronda"));
        panelRonda.setBackground(new Color(69, 201, 248, 255));

        constraints.gridx=3;
        constraints.gridy=4;
        constraints.gridwidth=3;
        constraints.fill=GridBagConstraints.BOTH;
        constraints.anchor=GridBagConstraints.CENTER;
        add(panelRonda,constraints);

        /**
         * Creacion de boton "tirar dados"
         */

        lanzar = new JButton("Tirar los dados");
        lanzar.addActionListener(escucha);
        lanzar.setBackground(Color.pink);

        constraints.gridx=1;
        constraints.gridy= 3;
        constraints.gridwidth=3;
        constraints.fill=GridBagConstraints.CENTER;
        constraints.anchor=GridBagConstraints.CENTER;
        add(lanzar,constraints);

        /**
         * Creacion de boton "Nuevo dado"
         */

        continuarReiniciar = new JButton();
        continuarReiniciar.setText("Continuar ronda");
        continuarReiniciar.addActionListener(escucha);
        continuarReiniciar.setName("continuarReiniciar");
        continuarReiniciar.setBackground(Color.cyan);
        continuarReiniciar.setEnabled(false);

        constraints.gridx=1;
        constraints.gridy= 4;
        constraints.gridwidth=3;
        constraints.fill=GridBagConstraints.CENTER;
        constraints.anchor=GridBagConstraints.CENTER;
        add(continuarReiniciar,constraints);

        mensajesSalida= new JTextArea(2,28);
        mensajesSalida.setText("Usa el botón (help) para ver las reglas del juego");
        mensajesSalida.setBorder(BorderFactory.createTitledBorder("Atención: "));
        mensajesSalida.setEditable(false);
        mensajesSalida.setBackground(new Color(241, 113, 113, 255));
        constraints.gridx=3;
        constraints.gridy=5;
        constraints.gridwidth=3;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.CENTER;
        add(mensajesSalida,constraints);

        // Creacion de dados
        modelDados.lanzamientoDados();
        inicializarBotones();
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

    public void inicializarBotones(){
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
            botonesInactivos.get(dado).setBackground(Color.white);
            botonesInactivos.get(dado).setVisible(false);
            imageDado = new ImageIcon(getClass().getResource("/recursos/" + modelDados.getAccionDado("dado" + String.valueOf(dado+1), "inactivos") + ".png"));
            botonesInactivos.get(dado).setIcon(new ImageIcon(imageDado.getImage().getScaledInstance(80,80, Image.SCALE_DEFAULT)));
            panelDadosInactivos.add(botonesInactivos.get(dado));
        }
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
        int dados42 = 0; // 42
        int dadosDragon = 0; // dragones
        int puntajeRonda = 0;
        String resultadoPuntaje = "";

        if(botones.size() == 0){
            puntajeRonda = 0;
            puntaje += puntajeRonda;
            ronda += 1;
            estadoDelJuego = 1;
            resultadoPuntaje = "Tu puntaje es: " + String.valueOf(puntajeRonda);
        }else{
            if(botones.size() == 1){
                if(modelDados.getAccionDado(botones.get(0).getName(), "activos") == "corazon"){
                    nuevoEscucha = 0;
                    estadoDelJuego = 0;
                    resultadoPuntaje = "¡Sigue lanzando!";
                    escuchas();
                }else{
                    if(modelDados.getAccionDado(botones.get(0).getName(), "activos") == "42"){
                        puntajeRonda = 1;
                        puntaje += puntajeRonda;
                        ronda += 1;
                        estadoDelJuego = 1;
                        resultadoPuntaje = "Tu puntaje es: " + String.valueOf(puntajeRonda);
                    }else{
                        if(modelDados.getAccionDado(botones.get(0).getName(), "activos") == "dragon"){
                            puntajeRonda = 0;
                            puntaje = puntajeRonda;
                            ronda += 1;
                            estadoDelJuego = 1;
                            resultadoPuntaje = "Tu puntaje es: " + String.valueOf(puntajeRonda);
                        }else{
                            puntajeRonda = 0;
                            puntaje += puntajeRonda;
                            ronda += 1;
                            estadoDelJuego = 1;
                            resultadoPuntaje = "Tu puntaje es: " + String.valueOf(puntajeRonda);
                        }
                    }
                }
            }else{
                // Cuenta el total de dados 42, dragones o si el ultimo dado es un corazon
                for (int boton=0; boton < botones.size(); boton++){
                    if(modelDados.getAccionDado(botones.get(boton).getName(), "activos") == "42"){
                        dados42 += 1;
                    }else{
                        if(modelDados.getAccionDado(botones.get(boton).getName(), "activos") == "dragon"){
                            dadosDragon += 1;
                        }
                    }
                }
                // Si la cantidad de dados 42 es igual al tamaño del ArrayList, gana
                if(dados42 == botones.size()){
                    switch (dados42){
                        case 2:
                            puntajeRonda = 3;
                            puntaje += puntajeRonda;
                            ronda += 1;
                            estadoDelJuego = 1;
                            resultadoPuntaje = "Tu puntaje es: " + String.valueOf(puntajeRonda);
                            break;
                        case 3:
                            puntajeRonda = 6;
                            puntaje += puntajeRonda;
                            ronda += 1;
                            estadoDelJuego = 1;
                            resultadoPuntaje = "Tu puntaje es: " + String.valueOf(puntajeRonda);
                            break;
                        case 4:
                            puntajeRonda = 10;
                            puntaje += puntajeRonda;
                            ronda += 1;
                            estadoDelJuego = 1;
                            resultadoPuntaje = "Tu puntaje es: " + String.valueOf(puntajeRonda);
                            break;
                        case 5:
                            puntajeRonda = 15;
                            puntaje += puntajeRonda;
                            ronda += 1;
                            estadoDelJuego = 1;
                            resultadoPuntaje = "Tu puntaje es: " + String.valueOf(puntajeRonda);
                            break;
                        case 6:
                            puntajeRonda = 21;
                            puntaje += puntajeRonda;
                            ronda += 1;
                            estadoDelJuego = 1;
                            resultadoPuntaje = "Tu puntaje es: " + String.valueOf(puntajeRonda);
                            break;
                        case 7:
                            puntajeRonda = 28;
                            puntaje += puntajeRonda;
                            ronda += 1;
                            estadoDelJuego = 1;
                            resultadoPuntaje = "Tu puntaje es: " + String.valueOf(puntajeRonda);
                            break;
                        case 8:
                            puntajeRonda = 36;
                            puntaje += puntajeRonda;
                            ronda += 1;
                            estadoDelJuego = 1;
                            resultadoPuntaje = "Tu puntaje es: " + String.valueOf(puntajeRonda);
                            break;
                        case 9:
                            puntajeRonda = 45;
                            puntaje += puntajeRonda;
                            ronda += 1;
                            estadoDelJuego = 1;
                            resultadoPuntaje = "Tu puntaje es: " + String.valueOf(puntajeRonda);
                            break;
                        case 10:
                            puntajeRonda = 55;
                            puntaje += puntajeRonda;
                            ronda += 1;
                            estadoDelJuego = 1;
                            resultadoPuntaje = "Tu puntaje es: " + String.valueOf(puntajeRonda);
                            break;
                        default:
                            break;
                    }
                }else{
                    if(dados42 + dadosDragon == botones.size()){
                        puntajeRonda = 0;
                        puntaje = 0;
                        ronda += 1;
                        estadoDelJuego = 1;
                        resultadoPuntaje = "Tu puntaje es: " + String.valueOf(puntajeRonda);
                    }else{
                        estadoDelJuego = 0;
                        resultadoPuntaje = "¡Sigue lanzando!";
                    }
                }
            }
        }

        if(estadoDelJuego == 1){
            for(int boton=0; boton < botones.size(); boton++){
                botones.get(boton).removeMouseListener(escucha);
            }

            // Se limpian los ArrayList de Dados para comenzar una nueva ronda
            modelDados.listaDados("activos").clear();
            modelDados.listaDados("inactivos").clear();
            modelDados.listaDados("utilizados").clear();

            // Se limpian los ArrayList de botones para comenzar una nueva ronda
            botones.clear();
            botonesInactivos.clear();
            botonesUtilizados.clear();

            modelDados.lanzamientoDados();
            textoPuntajeTotal.setText("Puntaje total: " + String.valueOf(puntaje));
            textoPuntaje.setText(resultadoPuntaje);
            inicializarBotones();

            if(ronda < 6 && puntaje < 29){
                continuarReiniciar.setEnabled(true);
            }else{
                if(ronda < 6 && puntaje > 29){
                    resultadoPuntaje = "Tu puntaje es: " + String.valueOf(puntaje) + " ¡Has ganado!";
                }else{
                    resultadoPuntaje = "Tu puntaje es: " + String.valueOf(puntaje) + " ¡Has perdido!";
                }
                puntaje = 0;
                ronda = 1;
                continuarReiniciar.setText("Jugar de nuevo");
                continuarReiniciar.setEnabled(true);
            }
        }

        textoPuntaje.setText(resultadoPuntaje);
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

            // Si el nombre del dado es un superheroe, vuelve a repetir el lanzamiento
            if(modelDados.getAccionDado(botonSecundario, "activos") == "superheroe"){
                textoPuntaje.setText("No se puede girar otro superheroe");
                nuevoEscucha = 2;
                escuchas();
            }else{
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
                lanzar.setEnabled(false);

                textoRonda.setText("Ronda: " + String.valueOf(ronda));
                panelRonda.add(textoRonda);
                continuarReiniciar.setText("Continuar ronda");
                textoPuntajeTotal.setText("Puntaje total: " + String.valueOf(puntaje));
                textoPuntaje.setText("¡Lanza un dado!");


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
                            if(e.getSource() == continuarReiniciar){
                                actualizarPanel("activos");
                                actualizarPanel("inactivos");
                                actualizarPanel("utilizados");
                                textoRonda.setText("Ronda: " + String.valueOf(ronda));
                                textoPuntajeTotal.setText("Puntaje total: " + String.valueOf(puntaje));
                                textoPuntaje.setText(null);
                                continuarReiniciar.setEnabled(false);
                                lanzar.setEnabled(true);
                            }else{
                                System.exit(0);
                            }
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
            mappingJButton("activos", nombreBoton).removeMouseListener(this);
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
                textoPuntaje.setText("Accion mepple activado");
                escuchas();
            }else{
                if(nombreAccion == "superheroe") {
                    // Si quedan dos dados superheroes, se lanza uno sin activar su accion y gana 0 puntos al final de la ronda
                    if(botones.size() == 1 && modelDados.getAccionDado(botones.get(0).getName(), "activos") == "superheroe"){
                        rondas();
                    }else{
                        for(int boton=0; boton < botones.size(); boton++){
                            botones.get(boton).removeMouseListener(this);
                            botones.get(boton).addMouseListener(superheroe);
                        }

                        textoPuntaje.setText("Accion superheroe activado");
                        nuevoEscucha = 2;
                        escuchas();
                    }
                }else{
                    if(nombreAccion == "dragon") {
                        nuevoEscucha = 0;
                        puntaje = 0;
                        escuchas();
                        rondas();
                    }else{
                        if(nombreAccion == "corazon") {
                            if(botonesInactivos.size() > 0){
                                for(int boton=0; boton < botones.size(); boton++){
                                    botones.get(boton).removeMouseListener(this);
                                }

                                for(int boton=0; boton < botonesInactivos.size(); boton++){
                                    botonesInactivos.get(boton).setEnabled(true);
                                    botonesInactivos.get(boton).addMouseListener(corazon);
                                }

                                nuevoEscucha = 3;
                                textoPuntaje.setText("Accion corazon activado");
                                escuchas();
                            }else{
                                nuevoEscucha = 0;
                                escuchas();
                                rondas();
                            }
                        }else{
                            if(nombreAccion == "cohete") {
                                for(int boton=0; boton < botones.size(); boton++){
                                    botones.get(boton).removeMouseListener(this);
                                    botones.get(boton).addMouseListener(cohete);
                                }

                                nuevoEscucha = 4;
                                textoPuntaje.setText("Accion cohete activado");
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