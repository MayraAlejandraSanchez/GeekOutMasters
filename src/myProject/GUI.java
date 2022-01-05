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
    private JLabel dado1,dado2,dado3,dado4,dado5,dado6,dado7,dado8,dado9,dado10, mano;
    private JButton lanzar,ayuda,salir, creditos;
    private JPanel panelDados;
    private ImageIcon imageDado;
    private JTextArea mensajesSalida;//,resultadosDados;
    private Escucha escucha;
    private ModelDados modelDados;
    /**
     * Constructor of GUI class
     */
    public GUI(){
        initGUI();

        //Default JFrame configuration
        this.setTitle("Geek Out Masters");
        //this.setSize(200,100);
        this.setUndecorated(true);
        //this.setBackground(new Color(255,255,255,0));
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * This method is used to set up the default JComponent Configuration,
     * create Listener and control Objects used for the GUI class
     */
    private void initGUI() {
        //Set up JFrame Container's Layout
        this.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        //Create Listener Object and Control Object
        escucha = new Escucha();
        modelDados = new ModelDados();
        //Set up JComponents
        headerProject = new Header("Geek Out Masters", Color.BLACK);

        constraints.gridx=3;
        constraints.gridy=1;
        constraints.gridwidth=3;
        constraints.fill=GridBagConstraints.HORIZONTAL;
        this.add(headerProject,constraints);

        //Boton de ayuda
        ayuda = new JButton(" help ");
        ayuda.addActionListener(escucha);
        ayuda.setBackground(Color.cyan);
        constraints.gridx=0;
        constraints.gridy=1;
        constraints.gridwidth=2;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.LINE_START;
        this.add(ayuda,constraints);

        //Boton creditos

        creditos = new JButton(" Creditos ");
        creditos.addActionListener(escucha);
        creditos.setBackground(Color.yellow);
        constraints.gridx=0;
        constraints.gridy=4;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.LINE_START;
        this.add(creditos,constraints);

        //Boton de salida
        salir = new JButton("Salir");
        salir.addActionListener(escucha);
        salir.setBackground(Color.red);
        constraints.gridx=6;
        constraints.gridy=1;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.LINE_END;
        this.add(salir,constraints);

        //Imagen
        imageDado = new ImageIcon(getClass().getResource("/recursos/mano apretada.png"));
        mano = new JLabel(imageDado);

        panelDados = new JPanel();
        panelDados.setPreferredSize(new Dimension(300,180));
        panelDados.setBorder(BorderFactory.createTitledBorder("Tira los dados"));
        panelDados.add(mano);

        constraints.gridx=3;
        constraints.gridy=2;
        constraints.gridwidth=3;
        constraints.fill=GridBagConstraints.BOTH;
        constraints.anchor=GridBagConstraints.CENTER;
        add(panelDados,constraints);

        lanzar = new JButton("Tirar los dados");
        lanzar.addActionListener(escucha);
        lanzar.setBackground(Color.pink);

        constraints.gridx=3;
        constraints.gridy=3;
        constraints.gridwidth=3;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.CENTER;
        add(lanzar,constraints);

        mensajesSalida= new JTextArea(2,28);
        mensajesSalida.setText("Usa el botón (help) para ver las reglas del juego");
        mensajesSalida.setBorder(BorderFactory.createTitledBorder("Atención: "));
        //mensajesSalida.setBackground(new Color(255,255,255,0));
        mensajesSalida.setEditable(false);
        constraints.gridx=3;
        constraints.gridy=4;
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
                        System.exit(0);
                    }
                }
            }

        }
    }
}

