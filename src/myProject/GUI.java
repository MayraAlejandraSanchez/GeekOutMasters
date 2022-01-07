/**
 *Clase principal
 * @autor Mayra Alejandra Sanchez - mayra.alejandra.sanchez@correounivalle.edu.co - 202040506
 * @autor Brayan Stiven Sanchez - brayan.sanchez.leon@correounivalle.edu.co - 202043554
 * @version 1.0.0 fecha 3/1/2022
 */
package myProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        constraints.fill=GridBagConstraints.NONE;
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

        botonExplicacion = new JButton("Explicacion dados");
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

        /**
         * Creacion de botones con imagen del dado
         */
        //dado1
        dado1 = new JButton();
        //imageDado = new ImageIcon(getClass().getResource("/recursos/" + ".png"));
        //dado1.setIcon(new ImageIcon(imageDado.getImage().getScaledInstance(dado1.getWidth(),dado1.getHeight(),Image.SCALE_SMOOTH)));
        //dado1.setBounds(3,3,200,200);
        dado1.setVisible(false);
        panelDadosActivos.add(dado1);

        //dado2
        dado2 = new JButton();
        //imageDado = new ImageIcon(getClass().getResource("/recursos/" + ".png"));
        //dado2.setIcon(new ImageIcon(imageDado.getImage().getScaledInstance(dado1.getWidth(),dado1.getHeight(),Image.SCALE_SMOOTH)));
        //dado2.setBounds(3,4,200,200);
        dado2.setVisible(false);
        panelDadosActivos.add(dado2);

        //dado3
        dado3 = new JButton();
        //imageDado = new ImageIcon(getClass().getResource("/recursos/" + ".png"));
        //dado3.setIcon(new ImageIcon(imageDado.getImage().getScaledInstance(dado1.getWidth(),dado1.getHeight(),Image.SCALE_SMOOTH)));
        //dado3.setBounds(3,4,200,200);
        dado3.setVisible(false);
        panelDadosActivos.add(dado3);

        //dado4
        dado4 = new JButton();
        //imageDado = new ImageIcon(getClass().getResource("/recursos/" + ".png"));
        //dado4.setIcon(new ImageIcon(imageDado.getImage().getScaledInstance(dado1.getWidth(),dado1.getHeight(),Image.SCALE_SMOOTH)));
        //dado4.setBounds(3,4,200,200);
        dado4.setVisible(false);
        panelDadosActivos.add(dado4);

        //dado5
        dado5 = new JButton();
        //imageDado = new ImageIcon(getClass().getResource("/recursos/" + ".png"));
        //dado5.setIcon(new ImageIcon(imageDado.getImage().getScaledInstance(dado1.getWidth(),dado1.getHeight(),Image.SCALE_SMOOTH)));
        //dado5.setBounds(3,4,200,200);
        dado5.setVisible(false);
        panelDadosActivos.add(dado5);

        //dado6
        dado6 = new JButton();
        //imageDado = new ImageIcon(getClass().getResource("/recursos/" + ".png"));
        //dado6.setIcon(new ImageIcon(imageDado.getImage().getScaledInstance(dado1.getWidth(),dado1.getHeight(),Image.SCALE_SMOOTH)));
        //dado6.setBounds(3,4,200,200);
        dado6.setVisible(false);
        panelDadosActivos.add(dado6);

        //dado7
        dado7 = new JButton();
        //imageDado = new ImageIcon(getClass().getResource("/recursos/" + ".png"));
        //dado7.setIcon(new ImageIcon(imageDado.getImage().getScaledInstance(dado1.getWidth(),dado1.getHeight(),Image.SCALE_SMOOTH)));
        //dado7.setBounds(3,4,200,200);
        dado7.setVisible(false);
        panelDadosActivos.add(dado7);

        //dado8
        dado8 = new JButton();
        //imageDado = new ImageIcon(getClass().getResource("/recursos/" + ".png"));
        //dado8.setIcon(new ImageIcon(imageDado.getImage().getScaledInstance(dado1.getWidth(),dado1.getHeight(),Image.SCALE_SMOOTH)));
        //dado8.setBounds(3,4,200,200);
        dado8.setVisible(false);
        panelDadosInactivos.add(dado8);

        //dado9
        dado9 = new JButton();
        //imageDado = new ImageIcon(getClass().getResource("/recursos/" + ".png"));
        //dado9.setIcon(new ImageIcon(imageDado.getImage().getScaledInstance(dado1.getWidth(),dado1.getHeight(),Image.SCALE_SMOOTH)));
        //dado9.setBounds(3,4,200,200);
        dado9.setVisible(false);
        panelDadosInactivos.add(dado9);

        //dado10
        dado10 = new JButton();
        //imageDado = new ImageIcon(getClass().getResource("/recursos/" + ".png"));
        //dado10.setIcon(new ImageIcon(imageDado.getImage().getScaledInstance(dado1.getWidth(),dado1.getHeight(),Image.SCALE_SMOOTH)));
        //dado10.setBounds(3,4,200,200);
        dado10.setVisible(false);
        panelDadosInactivos.add(dado10);

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
    private class Escucha implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if(e.getSource()==lanzar){


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
    }
}

