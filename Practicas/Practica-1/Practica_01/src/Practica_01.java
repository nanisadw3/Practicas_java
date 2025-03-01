
import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

public class Practica_01 {
    public static void main(String[] args) {


        Marco m = new Marco();
        m.setVisible(true);

    }
}
class Marco extends JFrame {
    Marco() {
        setTitle("Practica 1");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLocationRelativeTo(null);
        Lamina l = new Lamina();
        add(l);
    }
}
class Lamina extends JPanel {
    Lamina(){
        // Asegura que el tabbedPane ocupe todo el espacio
        setLayout(new BorderLayout());
        //creamos el JTabbedPane
        JTabbedPane tabbedPane = new JTabbedPane();
        // Agregar pestañas al JTabbedPane
        tabbedPane.addTab("Programa 1", progtama1());
        tabbedPane.addTab("Programa 2", progtama2());
        tabbedPane.addTab("Programa 3", progtama3());

        add(tabbedPane, BorderLayout.CENTER);
    }
    //variables globales
    JTextField text_saldo;
    JTextField text_cuenta;
    JTextField text_anualidad;
    JTextField text_Titular;

    private JPanel progtama1() {
        JPanel panel = new JPanel();
        panel.setLayout(null); // Desactiva el layout manager

        // Crear componentes
        JLabel label_saldo = new JLabel("Saldo:");
        text_saldo = new JTextField();
        JLabel label_cuenta = new JLabel("Cuenta:");
        text_cuenta = new JTextField();
        JLabel label_anualidad = new JLabel("Anualidad:");
        text_anualidad = new JTextField();
        JLabel label_Titular = new JLabel("Titular:");
        text_Titular = new JTextField();
        JButton btn_crear = new JButton("Crear");
        // Definir posiciones y tamaños manualmente
        label_saldo.setBounds(30, 20, 100, 30); // x, y, ancho, alto
        text_saldo.setBounds(80, 25, 100, 20);
        label_cuenta.setBounds(20, 45, 100, 30);
        text_cuenta.setBounds(80, 50, 100, 20);
        label_anualidad.setBounds(5, 70, 100, 30);
        text_anualidad.setBounds(80, 75, 100, 20);
        label_Titular.setBounds(25, 95, 100, 30);
        text_Titular.setBounds(80, 100, 100, 20);
        btn_crear.setBounds(85, 130, 90, 20);

        //agrego accion al boton
        btn_crear.addActionListener(e ->accionBtnCrear());
        // Agregar componentes al panel
        panel.add(label_saldo);
        panel.add(text_saldo);
        panel.add(label_cuenta);
        panel.add(text_cuenta);
        panel.add(label_anualidad);
        panel.add(text_anualidad);
        panel.add(label_Titular);
        panel.add(text_Titular);
        panel.add(btn_crear);

        return panel;
    }
    private void accionBtnCrear() {

        String titular = text_Titular.getText();
        String numeroCuenta = text_cuenta.getText();
        String anualidad = text_anualidad.getText();
        if (titular.equals("") || numeroCuenta.equals("") || anualidad.equals("") || text_saldo.getText() == "") {
            JOptionPane.showMessageDialog(null, "Llena todos los campos", "Error", JOptionPane.ERROR_MESSAGE);

        }else {
            int saldo = Integer.parseInt( text_saldo.getText());
            Cuenta c = new Cuenta(saldo, titular, numeroCuenta, anualidad);
            JOptionPane.showMessageDialog(null, "Objeto creado exitosamente", "Info", JOptionPane.INFORMATION_MESSAGE);
            text_anualidad.setText("");
            text_saldo.setText("");
            text_cuenta.setText("");
            text_Titular.setText("");

        }
    }

    JTextField txt_distancia;
    JTextField txt_tiempo ;

    private JPanel progtama2(){
        //declaro el panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));
        panel.setBorder(new EmptyBorder(10, 30, 140, 30)); // Agregar margen alrededor del panel

        //declaro elementos
        JLabel label_distancia = new JLabel("Distancia:");
        txt_distancia = new JTextField();
        JLabel label_tiempo = new JLabel("Tiempo:");
        JButton btn_calcular = new JButton("Calcular");
        txt_tiempo = new JTextField();
        //asigno funcion al botton calcular
        btn_calcular.addActionListener(e ->accionBtnCalcular());

        //asigno posiciones
        panel.add(label_distancia);
        panel.add(txt_distancia);
        panel.add(label_tiempo);
        panel.add(txt_tiempo);
        panel.add(btn_calcular);


        return panel;
    }
    private void accionBtnCalcular() {
        if (txt_distancia.getText().equals("") || txt_tiempo.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Campos vacios", "Error", JOptionPane.ERROR_MESSAGE);
        }else{
            int distancia = Integer.parseInt(txt_distancia.getText());
            int tiempo = Integer.parseInt(txt_tiempo.getText());
            int velocidad = distancia / tiempo;
            JOptionPane.showMessageDialog(null,"La velocidad es " + velocidad, "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    JTextField txt_diastolica;
    JTextField txt_sistolica;
    private JPanel progtama3(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));
        panel.setBorder(new EmptyBorder(10, 30, 140, 30));

        JLabel label_diastolica = new JLabel("Diastolica:");
        txt_diastolica = new JTextField();
        JLabel label_sistolica = new JLabel("Sistolica:");
        txt_sistolica = new JTextField();
        JButton btn_precion = new JButton("Calcular");

        btn_precion.addActionListener(e ->accionPrecion());
        panel.add(label_diastolica);
        panel.add(txt_diastolica);
        panel.add(label_sistolica);
        panel.add(txt_sistolica);
        panel.add(btn_precion);


        return panel;
    }
    private void accionPrecion() {
        if (txt_sistolica.equals("") || txt_diastolica.equals(""))
        {
        JOptionPane.showMessageDialog(null, "Campos vacios", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            int sistolica = Integer.parseInt(txt_sistolica.getText());
            int diastolica = Integer.parseInt(txt_diastolica.getText());
            Precion precion = new Precion(sistolica, diastolica);
            JOptionPane.showMessageDialog(null, "El estado es: " + precion.obtenerPrecion(), "Info", JOptionPane.INFORMATION_MESSAGE);
            txt_sistolica.setText("");
            txt_diastolica.setText("");
        }
    }
}
class Cuenta{
    private int saldo;
    private String titular;
    private String numeroCuenta;
    private String anualidad;

    public Cuenta(int saldo, String titular, String numeroCuenta, String anualidad) {
        this.saldo = saldo;
        this.titular = titular;
        this.numeroCuenta = numeroCuenta;
        this.anualidad = anualidad;
    }
}
class Precion{
    private int sistolica;
    private int diastolica;
    public Precion(int sistolica, int diastolica) {
        this.sistolica = sistolica;
        this.diastolica = diastolica;
    }
    public String obtenerPrecion() {
        if (sistolica < 120 && diastolica < 80)
        {
            return "Normal";
        }
        else if (sistolica >= 120 && sistolica <= 129 && diastolica <= 80)
        {
            return "Elevada";
        }
        else if (sistolica > 129 && sistolica <= 139 && diastolica > 80 && diastolica <= 120)
        {
            return "Hipertensión 1";
        }
        else if (sistolica > 132 && sistolica <= 180 && diastolica > 90 && diastolica <= 120)
        {
            return "Hipertensión 1";
        }
        else if (sistolica > 180 && diastolica > 120)
        {
            return "crisis Hipotermica";
        }
        else
        {
            return "Error";
        }
    }
}