import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
    Marco m = new Marco();
    m.setVisible(true);
    m.setResizable(false);
    }
}
class Marco extends JFrame {
    public Marco() {
        setTitle("Practica 5");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 350);
        setLocationRelativeTo(null);
        Lamina l = new Lamina();
        add(l);
    }
}
class Lamina extends JPanel {
    public Lamina() {

        setLayout(new GridLayout(1, 2));
        add(crearLamina1());
        add(crearLamina2());


    }
    ArrayList<Guitarra> guitarras = new ArrayList<>();
    JTextField txt_clave;
    JTextField txt_nombre;
    JTextField txt_precio;
    JSpinner sp_ncuerdas;
    private JPanel crearLamina1(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "Registro", TitledBorder.LEFT, TitledBorder.TOP));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 4, 4);
        gbc.fill = GridBagConstraints.HORIZONTAL;//los textfield se expanden y llenan toda la celda

        JLabel label_clave = new JLabel("Clave: ");
        JLabel label_nombre = new JLabel("Nomvre: ");
        JLabel label_precio = new JLabel("Precio: ");
        JLabel label_ncuerdas = new JLabel("Numero de cuerdas: ");

        txt_clave = new JTextField(10);//le mandamos por parametros lo largo que este
        txt_nombre = new JTextField(10);
        txt_precio = new JTextField(10);
        sp_ncuerdas = new JSpinner(new SpinnerNumberModel(5, 4, 7, 1));// Ejemplo si quieres entre 4-7 cuerdas

        JButton btn_registrar = new JButton("Registrar");

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(label_clave, gbc);
        gbc.gridx = 1;
        panel.add(txt_clave, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(label_nombre, gbc);
        gbc.gridx = 1;
        panel.add(txt_nombre, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(label_precio, gbc);
        gbc.gridx = 1;
        panel.add(txt_precio, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(label_ncuerdas, gbc);
        gbc.gridx = 1;
        panel.add(sp_ncuerdas, gbc);
        gbc.gridx = 0;                // inicia desde la columna 0
        gbc.gridy = 5;                // la fila en la que deseas el botón
        gbc.gridwidth = 2;            // el botón ocupará 2 columnas para centrarse
        btn_registrar.addActionListener(e -> accionRegistrar());
        panel.add(btn_registrar, gbc);


        return panel;
    }
    private void accionRegistrar() {
        String clave = txt_clave.getText();
        String nombre = txt_nombre.getText();
        float precio = Float.parseFloat(txt_precio.getText());
        int numCuerdas = (Integer) sp_ncuerdas.getValue();//de esta forma se parcea

        if(clave.equals("") || nombre.equals("") || precio < 0) {
            JOptionPane.showMessageDialog(this, "Llena todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
        }else if(precio < 0){
            JOptionPane.showMessageDialog(this, "El precio no puede ser negativo", "Error", JOptionPane.ERROR_MESSAGE);
        }else if(numCuerdas < 4 || numCuerdas > 7){
            JOptionPane.showMessageDialog(this, "El numero de cuerdas es incorrecto", "Error", JOptionPane.ERROR_MESSAGE);
        }else{
            Guitarra guitar = new Guitarra(clave, nombre, precio, numCuerdas);
            guitarras.add(guitar);
            JOptionPane.showMessageDialog(null, "Se ha almacenado el registro", "Registro", JOptionPane.INFORMATION_MESSAGE);
            txt_clave.setText("");
            txt_nombre.setText("");
            txt_precio.setText("");
            sp_ncuerdas.setValue(4);

        }
    }

    JTextArea txt_info = new JTextArea(5, 20);// filas, columnas aproximadas
    JScrollPane scroller =  new JScrollPane(txt_info);
    private JPanel crearLamina2(){
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "Consulta", TitledBorder.LEFT, TitledBorder.TOP));
        GridBagConstraints gbc = new GridBagConstraints();
        txt_info.setEditable(false);
        JButton btn_actualizar = new JButton("Actualizar");

        panel.add(scroller, BorderLayout.CENTER);
        btn_actualizar.addActionListener(e -> accionActualizar());
        panel.add(btn_actualizar, BorderLayout.SOUTH);


        return panel;
    }
    public void accionActualizar(){

        txt_info.setText("");
        String info = "";
        if(guitarras.size() == 0){
            JOptionPane.showMessageDialog(null, "No hay guitarras", "Error", JOptionPane.ERROR_MESSAGE);
        }else{
            for(Guitarra g : guitarras){
                info += "Guitarra: " + g.nombre + " Precio: " + g.calcularPrecio() + " Numero de cuerdas: " + g.getNumeroDeCuerdas()+ " Clave: "+ g.clave+ "\n";
            }
        }
        txt_info.setText(info);

    }
}
class Guitarra extends Instrumento {
    private int numeroDeCuerdas;

    public int getNumeroDeCuerdas() {
        return numeroDeCuerdas;
    }

    public Guitarra(String clave, String nombre, float precio, int numCuerdas) {

        this.clave = clave;
        this.nombre = nombre;
        this.precio = precio;
        this.numeroDeCuerdas = numCuerdas;

    }
    @Override
    public float calcularPrecio() {
        return precio * 1.25f;//f "float"
    }
}
class Instrumento{
    public String nombre, clave;
    public float precio;
    public float calcularPrecio(){

        return precio;
    }
}