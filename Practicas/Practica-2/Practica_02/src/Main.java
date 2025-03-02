import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

public class Main {
    public static void main(String[] args) {
        Marco m = new Marco();
        m.setVisible(true);
    }
}

class Marco extends JFrame {
    public Marco() {
        setTitle("Practica 2");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLocationRelativeTo(null);
        Lamina m = new Lamina();
        add(m);
    }
}
class Lamina extends JPanel {
    JTextField txt_clave;
    JTextField txt_nombre;
    JTextField txt_ap;
    JTextField txt_am;
    JTextField txt_salario;
    public Lamina() {
        this.setLayout(new GridLayout(6, 2, 10, 10));
        this.setBorder(new EmptyBorder(10, 10, 80, 30)); // Agregar margen alrededor del panel
        //Creamos los componentes
        JLabel l1 = new JLabel("Clave: ");
        JLabel l2 = new JLabel("Nombre:");
        JLabel l3 = new JLabel("Apellido Paterno: ");
        JLabel l4 = new JLabel("Apellido Materno: ");
        JLabel l5 = new JLabel("Salario: ");
         txt_clave = new JTextField(10);
         txt_nombre = new JTextField(10);
         txt_ap = new JTextField(10);
         txt_am = new JTextField(10);
         txt_salario = new JTextField(10);
        JButton b1 = new JButton("Aceptar");

        //Agregamos los componentes a la lamina
        add(l1);
        add(txt_clave);

        add(l2);
        add(txt_nombre);

        add(l3);
        add(txt_ap);

        add(l4);
        add(txt_am);

        add(l5);
        add(txt_salario);
        // Panel para el botón, alineado a la derecha
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(b1);
        b1.addActionListener(e->crearCorreo());
        // Agregar un espacio vacío para mantener el diseño de dos columnas
        add(new JLabel(""));
        add(b1);
    }
    private void crearCorreo() {

        if (txt_clave.getText().equals("") || txt_nombre.getText().equals("") || txt_ap.getText().equals("") || txt_am.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Llena todos los campos","Error", JOptionPane.ERROR_MESSAGE);
        }else {
            String clave = txt_clave.getText();
            String nombre = txt_nombre.getText();
            String apellido_materno = txt_ap.getText();
            String salario = txt_salario.getText();
            String apellido_paterno = txt_ap.getText();
            Empleado e = new Empleado(clave, nombre, apellido_materno, salario, apellido_paterno);
            JOptionPane.showMessageDialog(null, "El correo es: " + e.generarCorreo(), "Informacion", JOptionPane.INFORMATION_MESSAGE);
            JOptionPane.showMessageDialog(null, "Clave: " + e.clave + " Nombre: " + e.nombre + " salario " + e.salario, "Informacion", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
class Empleado{
    public String clave;
    public String nombre;
    public String apellido_materno;
    public String salario;
    public String apellido_paterno;
    public String correo;

    public Empleado(String clave, String nombre, String apellido_materno, String salario, String apellido_paterno) {
        this.clave = clave;
        this.nombre = nombre;
        this.apellido_materno = apellido_materno;
        this.salario = salario;
        this.apellido_paterno = apellido_paterno;
    }
    public String generarCorreo() {
        correo = nombre.substring(0, 1) + primeraVocal(apellido_paterno) + nombre + "@empresa.com";
        return correo;

    }
    public static char primeraVocal(String cadena) {
        String vocales = "aeiouAEIOU"; // Definimos las vocales
        for (int i = 0; i < cadena.length(); i++) {
            if (vocales.contains(String.valueOf(cadena.charAt(i)))) {
                return cadena.charAt(i); // Retorna la primera vocal encontrada
            }
        }
        return '-'; // Retorno por defecto si no hay vocales
    }

}
