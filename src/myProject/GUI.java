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
    private JButton lanzar,ayuda,salir, creditos, botonExplicacion, dado1,dado2,dado3,dado4,dado5,dado6,dado7,dado8,dado9,dado10 ;
    private JPanel panelDadosActivos, panelDadosUtilizados, panelDadosInactivos, panelPuntaje;
    private ImageIcon imageMano, imageExplicacion, imageDado;
    private JTextArea mensajesSalida;//,resultadosDados;
    private Escucha escucha;
    private ModelDados modelDados;
    private ArrayList<JButton> botones ;
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
        modelDados = new ModelDados();
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

    /**
     * inner class that extends an Adapter Class or implements Listeners used by GUI class
     */
    private class Escucha implements ActionListener, MouseListener {

        ModelDados modeldados = new ModelDados();

        @Override
        public void actionPerformed(ActionEvent e) {

            if(e.getSource()==lanzar){
                /**
                * Quita la mano que esta al inicio del juego
                */
                mano.setVisible(false);
                /**
                 * Aparecen y se crean los dados
                 */

                modeldados.asignacionAcciones();

                //dado1
                dado1 = new JButton();
                imageDado = new ImageIcon(getClass().getResource("/recursos/" + modeldados.getAccionDado("dado1") + ".png"));
                dado1.setIcon(new ImageIcon(imageDado.getImage().getScaledInstance(80,80, Image.SCALE_DEFAULT)));
                dado1.setBackground(Color.white);
                dado1.setBorder(null);
                dado1.addMouseListener(escucha);
                dado1.setName("dado1");
                panelDadosActivos.add(dado1);

                //dado2
                dado2 = new JButton();
                imageDado = new ImageIcon(getClass().getResource("/recursos/" + modeldados.getAccionDado("dado2") + ".png"));
                dado2.setIcon(new ImageIcon(imageDado.getImage().getScaledInstance(80,80, Image.SCALE_DEFAULT)));
                dado2.setBackground(Color.white);
                dado2.setBorder(null);
                dado2.addMouseListener(escucha);
                dado2.setName("dado2");
                panelDadosActivos.add(dado2);

                //dado3
                dado3 = new JButton();
                imageDado = new ImageIcon(getClass().getResource("/recursos/" + modeldados.getAccionDado("dado3") + ".png"));
                dado3.setIcon(new ImageIcon(imageDado.getImage().getScaledInstance(80,80, Image.SCALE_DEFAULT)));
                dado3.setBackground(Color.white);
                dado3.setBorder(null);
                dado3.addMouseListener(escucha);
                dado3.setName("dado3");
                panelDadosActivos.add(dado3);

                //dado4
                dado4 = new JButton();
                imageDado = new ImageIcon(getClass().getResource("/recursos/" + modeldados.getAccionDado("dado4") + ".png"));
                dado4.setIcon(new ImageIcon(imageDado.getImage().getScaledInstance(80,80, Image.SCALE_DEFAULT)));
                dado4.setBackground(Color.white);
                dado4.setBorder(null);
                dado4.addMouseListener(escucha);
                dado4.setName("dado4");
                panelDadosActivos.add(dado4);

                //dado5
                dado5 = new JButton();
                imageDado = new ImageIcon(getClass().getResource("/recursos/" + modeldados.getAccionDado("dado5") + ".png"));
                dado5.setIcon(new ImageIcon(imageDado.getImage().getScaledInstance(80,80, Image.SCALE_DEFAULT)));
                dado5.setBackground(Color.white);
                dado5.setBorder(null);
                dado5.addMouseListener(escucha);
                dado5.setName("dado5");
                panelDadosActivos.add(dado5);

                //dado6
                dado6 = new JButton();
                imageDado = new ImageIcon(getClass().getResource("/recursos/" + modeldados.getAccionDado("dado6") + ".png"));
                dado6.setIcon(new ImageIcon(imageDado.getImage().getScaledInstance(80,80, Image.SCALE_DEFAULT)));
                dado6.setBackground(Color.white);
                dado6.setBorder(null);
                dado6.addMouseListener(escucha);
                dado6.setName("dado6");
                panelDadosActivos.add(dado6);

                //dado7
                dado7 = new JButton();
                imageDado = new ImageIcon(getClass().getResource("/recursos/" + modeldados.getAccionDado("dado7") + ".png"));
                dado7.setIcon(new ImageIcon(imageDado.getImage().getScaledInstance(80,80, Image.SCALE_DEFAULT)));
                dado7.setBackground(Color.white);
                dado7.setBorder(null);
                dado7.addMouseListener(escucha);
                dado7.setName("dado7");
                panelDadosActivos.add(dado7);

                //dado8
                dado8 = new JButton();
                imageDado = new ImageIcon(getClass().getResource("/recursos/" + modeldados.getAccionDado("dado8") + ".png"));
                dado8.setIcon(new ImageIcon(imageDado.getImage().getScaledInstance(80,80, Image.SCALE_DEFAULT)));
                dado8.setBackground(Color.cyan);
                dado8.setBorder(null);
                dado8.addMouseListener(escucha);
                dado8.setName("dado8");
                panelDadosInactivos.add(dado8);

                //dado9
                dado9 = new JButton();
                imageDado = new ImageIcon(getClass().getResource("/recursos/" + modeldados.getAccionDado("dado9") + ".png"));
                dado9.setIcon(new ImageIcon(imageDado.getImage().getScaledInstance(80,80, Image.SCALE_DEFAULT)));
                dado9.setBackground(Color.cyan);
                dado9.setBorder(null);
                dado9.addMouseListener(escucha);
                dado9.setName("dado9");
                panelDadosInactivos.add(dado9);

                //dado10
                dado10 = new JButton();
                imageDado = new ImageIcon(getClass().getResource("/recursos/" + modeldados.getAccionDado("dado10") + ".png"));
                dado10.setIcon(new ImageIcon(imageDado.getImage().getScaledInstance(80,80, Image.SCALE_DEFAULT)));
                dado10.setBackground(Color.cyan);
                dado10.setBorder(null);
                dado10.addMouseListener(escucha);
                dado10.setName("dado10");
                panelDadosInactivos.add(dado10);

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

            ArrayList<JButton> botones = new ArrayList<>();

            botones.add(dado1);
            botones.add(dado2);
            botones.add(dado3);
            botones.add(dado4);
            botones.add(dado5);
            botones.add(dado6);
            botones.add(dado7);
            botones.add(dado8);
            botones.add(dado9);
            botones.add(dado10);

            for(int dado=1; dado < 11; dado++){
                if(e.getSource() == botones.get(dado)){
                    modeldados.dadosUtilizados(botones.get(dado).getName());
                    if (modeldados.getAccionDado(botones.get(dado).getName()) == "mepple"){
                        modeldados.accionMepple(botones.get(dado).getName());
                        break;
                    }else{
                        if (modeldados.getAccionDado(botones.get(dado).getName()) == "superheroe"){
                            modeldados.accionSuperHeroe(botones.get(dado).getName());
                        }else{
                            if (modeldados.getAccionDado(botones.get(dado).getName()) == "dragon"){
                                modeldados.accionDragon();
                            }else{
                                if (modeldados.getAccionDado(botones.get(dado).getName()) == "corazon"){
                                    modeldados.accionCorazon(botones.get(dado).getName());
                                }else{
                                    if (modeldados.getAccionDado(botones.get(dado).getName()) == "cohete"){
                                        modeldados.accionCohete(botones.get(dado).getName());
                                    }else{
                                        if (modeldados.getAccionDado(botones.get(dado).getName())=="42"){
                                            modeldados.accion42(botones.get(dado).getName());
                                        }else{
                                        }
                                    }
                                }
                            }
                        }

                    }

                }
            }


/*
if (e.getSource()==dado1 & e.getClickCount()==1){
                modeldados.dadosUtilizados("dado1");
                if (modeldados.getAccionDado("dado1") == "mepple"){
                    modeldados.accionMepple("dado1");
                }else{
                    if (modeldados.getAccionDado("dado1") == "superheroe"){
                        modeldados.accionSuperHeroe("dado1");
                    }else{
                        if (modeldados.getAccionDado("dado1") == "dragon"){
                            modeldados.accionDragon();
                        }else{
                            if (modeldados.getAccionDado("dado1") == "corazon"){
                                modeldados.accionCorazon("dado1");
                            }else{
                                if (modeldados.getAccionDado("dado1") == "cohete"){
                                    modeldados.accionCohete("dado1");
                                }else{
                                    if (modeldados.getAccionDado("dado1") == "42"){
                                        modeldados.accion42("dado1");
                                    }
                                }
                            }
                        }
                    }

                }

            }else{
/**
 * dado2
 */
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

