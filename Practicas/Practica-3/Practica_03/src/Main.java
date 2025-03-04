import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.border.TitledBorder;

import javax.swing.border.EmptyBorder;

public class Main {
    public static void main(String[] args) {

        Marco marco = new Marco();
        marco.setVisible(true);
        marco.setLocationRelativeTo(null);
    }
}
class Marco extends JFrame {
    Marco() {
        setTitle("Practica 3");
        setSize(700, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Lamina panel = new Lamina();
        add(panel);
    }
}
class Lamina extends JPanel {
    Lamina() {
        setLayout(new GridLayout(1, 2, 5, 5));
        JPanel panel1 = primerLamina();
        JPanel panel2 = segundoLamina();

        add(panel1);
        add(panel2);
    }

    ArrayList<Almacen> productos = new ArrayList<Almacen>();

    JTextField txt_clave;
    JTextField txt_nombre;
    JTextField txt_ubicacion;
    JTextField txt_cantidad;
    public JPanel primerLamina() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 5, 5));

        //esto es un equivalente a un GrubBox pero en java
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Alta", TitledBorder.LEFT, TitledBorder.TOP),
                new EmptyBorder(1, 1, 90, 70) // Agregar margen interno
        ));

        JLabel label_clave = new JLabel("Clave: ");
        txt_clave = new JTextField();
        JLabel label_nombre = new JLabel("Nombre: ");
        txt_nombre = new JTextField();
        JLabel label_ubicacion = new JLabel("Ubicacion: ");
        txt_ubicacion = new JTextField();
        JLabel label_cantidad = new JLabel("Cantidad: ");
        txt_cantidad = new JTextField();
        JLabel extra = new JLabel("");
        JButton btn_agregar = new JButton("Agregar");

        btn_agregar.addActionListener(e -> accion_Agregar());

        panel.add(label_clave);
        panel.add(txt_clave);
        panel.add(label_nombre);
        panel.add(txt_nombre);
        panel.add(label_ubicacion);
        panel.add(txt_ubicacion);
        panel.add(label_cantidad);
        panel.add(txt_cantidad);
        panel.add(extra);
        panel.add(btn_agregar);

        return panel;
    }
    private void accion_Agregar() {
        String clave = txt_clave.getText();
        String nombre = txt_nombre.getText();
        String ubicacion = txt_ubicacion.getText();


        if (clave.equals("") || nombre.equals("") || ubicacion.equals("") || txt_cantidad.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Llena todos los campos","Error", JOptionPane.ERROR_MESSAGE);
        }else if(Integer.parseInt(txt_cantidad.getText())<0) {
            JOptionPane.showMessageDialog(null, "La cantidad no puede ser menor a 0","Error", JOptionPane.ERROR_MESSAGE);
        }else{
            int cantidad = Integer.parseInt(txt_cantidad.getText());
            Almacen a = new Almacen(clave, nombre, ubicacion, cantidad);
            productos.add(a);
            JOptionPane.showMessageDialog(null, "El producto fue almacenado con exito","Info", JOptionPane.INFORMATION_MESSAGE);

            txt_clave.setText("");
            txt_nombre.setText("");
            txt_ubicacion.setText("");
            txt_cantidad.setText("");

        }
    }
    JTextField txt_buscar;
    public JPanel segundoLamina() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2, 5, 5));

        //esto es un equivalente a un GrubBox pero en java
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Buscar", TitledBorder.LEFT, TitledBorder.TOP),
                new EmptyBorder(1, 1, 180, 70) // Agregar margen interno
        ));
        JLabel label_buscar = new JLabel("Buscar: ");
        txt_buscar = new JTextField();
        JLabel label_extra = new JLabel("");
        JButton btn_buscar = new JButton("Buscar");

        btn_buscar.addActionListener(e -> accion_Buscar());

        panel.add(label_buscar);
        panel.add(txt_buscar);
        panel.add(label_extra);
        panel.add(btn_buscar);

        return panel;
    }
    private void accion_Buscar() {

        String ubicacion = txt_buscar.getText();
        String nombre ="";
        String clave="";
        int cantidad=0;
        boolean existe = false;

        //Cuando usas ==, Java no compara el contenido del String, sino su referencia en la memoria.
        if (ubicacion.equals("")) {
            JOptionPane.showMessageDialog(null, "Llena el campo para hacer una busqueda","Error", JOptionPane.ERROR_MESSAGE);
        }else{
            for (Almacen a : productos) {

                if(ubicacion.equals(a.ubicacion)){
                existe = true;
                nombre = a.nombre;
                cantidad = a.cantidad;
                clave = a.clave;
                }
            }
            if (existe) {
                JOptionPane.showMessageDialog(null, "Clave: " + clave + " Nombre: " + nombre + " Cantidad: " + cantidad + " Ubicasio: " + ubicacion ,"Info", JOptionPane.INFORMATION_MESSAGE);
            }else {
                JOptionPane.showMessageDialog(null, "No hay productos registrados en esa ubicacion","Error", JOptionPane.ERROR_MESSAGE);
            }
            txt_buscar.setText("");

        }

    }

}
class Almacen{
    public String clave;
    public String nombre;
    public String ubicacion;
    int cantidad;

    public Almacen(String clave, String nombre, String ubicacion, int cantidad) {
        this.clave = clave;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.cantidad = cantidad;
    }
}