import javax.swing.*;
import java.awt.*;
import java.time.chrono.JapaneseDate;
import java.util.ArrayList;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import com.toedter.calendar.JDateChooser;
import java.util.Calendar;
import java.util.Date;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        Marco m = new Marco();
        m.setVisible(true);
        //m.setResizable(false);
    }
}
class Marco extends JFrame {
    public Marco() {
        setTitle("Practica 6");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 250);
        setLocationRelativeTo(null);
        Lamina l = new Lamina();
        add(l);

    }
}
class Lamina extends JPanel{
    public Lamina() {
        setLayout(new BorderLayout());
        JPanel panel = crearPanel();
        add(panel, BorderLayout.CENTER);
    }
    JTextField txt_usuario;
    JTextField txt_constra;
    JTextField txt_fecha;
    JDateChooser dateChooser;


    private JPanel crearPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "Registro", TitledBorder.LEFT, TitledBorder.TOP));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 4, 4);
        gbc.fill = GridBagConstraints.HORIZONTAL;//los textfield se expanden y llenan toda la celda

        JLabel label_usuario = new JLabel("Usuario: ");
        JLabel label_contra = new JLabel("Contrasena: ");
        JLabel label_fechaN = new JLabel("Fecha de nacimiento: ");

        txt_usuario = new JTextField(10);
        txt_constra = new JTextField(10);
        dateChooser = new JDateChooser();


        gbc.gridy = 0;
        gbc.gridx = 0;
        panel.add(label_usuario, gbc);
        gbc.gridy = 0;
        gbc.gridx = 1;
        panel.add(txt_usuario, gbc);
        gbc.gridy = 1;
        gbc.gridx = 0;
        panel.add(label_contra, gbc);
        gbc.gridy = 1;
        gbc.gridx = 1;
        panel.add(txt_constra, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        panel.add(label_fechaN, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(dateChooser,gbc);

        JButton boton = new JButton("Registrar");
        boton.addActionListener(e -> accionRegistrar());
        gbc.gridy = 3;
        gbc.gridx = 1;

        panel.add(boton, gbc);

        return panel;
    }
    private void accionRegistrar() {
        String usuario = txt_usuario.getText();
        String contra = txt_constra.getText();

        if(usuario.equals("") || contra.equals("") || dateChooser.getDate() ==null ) {
            JOptionPane.showMessageDialog(null, "Llena todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
        }else if(contra.length() < 6) {
            JOptionPane.showMessageDialog(null, "La contrasena no puede tener menos de 6 caracteres", "Error", JOptionPane.ERROR_MESSAGE);
        }else{
            Date fechaSeleccionada = dateChooser.getDate();
            // Extraer el año de la fecha seleccionada
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fechaSeleccionada);
            int year = calendar.get(Calendar.YEAR);

            if (year > 2012) {
                JOptionPane.showMessageDialog(null, "El año no puede ser superior al 2012", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                String seleccionada_s = dateChooser.getDate().toString();
                Usuario u = new Usuario(usuario, contra, seleccionada_s);
                crearArchivo(usuario, contra, seleccionada_s);

                JOptionPane.showMessageDialog(null, "Registro exitoso", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    private void crearArchivo(String nombre, String contra, String fecha) {
        try {

            BufferedWriter bw = new BufferedWriter(new FileWriter("usuarios.txt"));
            bw.write("Nombre "+ nombre + " Contrasena "+ contra + " Fecha de nacimiento "+fecha);
            bw.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al crear el archivo", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
class Usuario{
    String usuario;
    String contra;
    String fecha;

    public Usuario(String usuario, String contra, String fecha) {
        this.usuario = usuario;
        this.contra = contra;
        this.fecha = fecha;
    }
}