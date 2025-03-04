import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Marco m = new Marco();
        m.setLocationRelativeTo(null);
        m.setResizable(false);
        m.setVisible(true);
    }
}
class Marco extends JFrame {
    public Marco() {
        setTitle("Practica 4");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        Lamina l = new Lamina();
        add(l);
    }
}
class Lamina extends JPanel {
    public Lamina() {
        setLayout(new BorderLayout());//establecemos Border layout para cantrar despues el panel
        setBorder(new EmptyBorder(10, 10, 10, 10)); // creamos los margenes de la aplicacion

        JPanel panelFormulario = crearPanel();
        add(panelFormulario, BorderLayout.CENTER);//centramos
    }

    public JTextField txt_nombre;
    public JTextField txt_cuenta;

    private ArrayList<Alumnos> lista = new ArrayList<>();

    private JPanel crearPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "Lista", TitledBorder.LEFT, TitledBorder.TOP));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 0, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;//los textfield se expanden y llenan toda la celda

        JLabel label_nombre = new JLabel("Nombre:");
        JLabel label_cuenta = new JLabel("Cuenta:");
        txt_nombre = new JTextField(15);
        txt_cuenta = new JTextField(15);
        JButton btn_agregar = new JButton("Agregar");
        JButton btn_Mostrar = new JButton("Mostrar");
        btn_agregar.addActionListener(e -> agregar());
        btn_Mostrar.addActionListener(e -> mostrar());
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(label_nombre, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(txt_nombre, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(label_cuenta, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(txt_cuenta, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(btn_agregar, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(btn_Mostrar, gbc);

        return panel;
    }

    public void mostrar() {
        if(lista == null) {
            JOptionPane.showMessageDialog(null, "No se ha registrado ningun alumno","Error", JOptionPane.ERROR_MESSAGE);

        }else {
            for (var e : lista) {
                JOptionPane.showMessageDialog(null, "Nombre: " + e.nombre + " Cuneta: " + e.cuenta, "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    public void agregar() {
        String nombre = txt_nombre.getText();
        String cuenta = txt_cuenta.getText();
        if(nombre.equals("") || cuenta.equals("")) {
            JOptionPane.showMessageDialog(null, "Llena todos los campos","Error", JOptionPane.ERROR_MESSAGE);
        }else{
            Alumnos a = new Alumnos(nombre, cuenta);
            lista.add(a);
            JOptionPane.showMessageDialog(null, "Registro generado con exito","Info", JOptionPane.INFORMATION_MESSAGE);

            txt_nombre.setText("");
            txt_cuenta.setText("");
        }

    }
}
class Alumnos{
    String nombre;
    String cuenta;

    public Alumnos(String nombre, String cuenta) {
        this.nombre = nombre;
        this.cuenta = cuenta;
    }
}