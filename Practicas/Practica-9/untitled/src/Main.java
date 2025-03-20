//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.chrono.JapaneseDate;
import java.util.ArrayList;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import com.toedter.calendar.JDateChooser;
import java.util.Calendar;
import java.util.Date;
import java.time.ZoneId;

public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.

        Marco marco = new Marco();
        marco.setVisible(true);
    }
}
class Marco extends JFrame {
    public Marco() {
        setTitle("Practica 9");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setResizable(false);
        setLocationRelativeTo(null);
        Lamina l = new Lamina();
        add(l);

    }
}
class Lamina extends JPanel {
    public Lamina() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "Registro", TitledBorder.LEFT, TitledBorder.TOP));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel usuario = new JLabel("Usuario:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(usuario, gbc);

        txt_usuario = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(txt_usuario, gbc);


        JLabel contrasena = new JLabel("Contrasena:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(contrasena, gbc);

        txt_contrasena = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(txt_contrasena, gbc);

        JLabel nombre = new JLabel("Fecha de Nacimiento:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(nombre, gbc);

        dateChooser = new JDateChooser();
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(dateChooser, gbc);

        JButton registrar = new JButton("Registrar");
        registrar.addActionListener(e -> accion_Registrar());
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(registrar, gbc);

        add(panel, gbc);
    }
    private void cargar(){
        try {
        FileReader fr = new FileReader("usuarios.txt");
        BufferedReader br = new BufferedReader(fr);
        String linea;
        while((linea = br.readLine()) != null){
        String[] datos = linea.split(",");
        String usuario = datos[0];
        String contrasena = datos[1];
        String fecha = datos[2];
        Usuarios u = new Usuarios(usuario, contrasena, fecha);
        usuarios.add(u);
        }
        br.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e, "Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    private void accion_Registrar() {

        try {
            if(txt_contrasena.getText().isEmpty()||txt_usuario.getText().isEmpty()||dateChooser.getDate() == null){
                throw new MiexepcionCampoVacio();
            }else if((txt_contrasena.getText()).length()<6){

                JOptionPane.showMessageDialog(null,"La contrasenia no puede tener menos de 6 caracteres", "Error",JOptionPane.ERROR_MESSAGE);
            }else{
                int year = dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear();
                if (year > 2009) { // Si el año es mayor a 2009, mostramos el error
                    JOptionPane.showMessageDialog(null, "El año no puede ser superior a 2009", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    boolean bandera = false;
                    String usuario = txt_usuario.getText();
                    for (var u : usuarios){
                        if(usuario.equals(u.usuario)){
                            bandera = true;
                            break;
                        }
                    }
                    if(bandera){
                        JOptionPane.showMessageDialog(null, "El usuario ya esta registrado", "Error", JOptionPane.ERROR_MESSAGE);
                    }else{
                        String contrasena = txt_contrasena.getText();
                        String fecha = dateChooser.getDate().toString();
                        FileWriter fw = new FileWriter("usuarios.txt",true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        bw.write(usuario + "," + contrasena + "," + fecha + "\n");
                        bw.close();

                        Usuarios u = new Usuarios(usuario, contrasena, fecha);
                        usuarios.add(u);
                        JOptionPane.showMessageDialog(null, "Usuario registrado exitosamente", "Info", JOptionPane.INFORMATION_MESSAGE);

                    }
                }
            }


        } catch (MiexepcionCampoVacio ex) {
            JOptionPane.showMessageDialog(null, "Error " + ex, "Error", JOptionPane.ERROR_MESSAGE);
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
    JTextField txt_usuario;
    JTextField txt_contrasena;
    JDateChooser dateChooser;
    ArrayList<Usuarios> usuarios = new ArrayList<Usuarios>();
}
class  MiexepcionCampoVacio extends Exception {
    public MiexepcionCampoVacio() {
        super("Los campos estan vacios");
    }
}
class Usuarios{
    String usuario;
    String contrasena;
    String fecha;

    public Usuarios(String usuario, String contrasena, String fecha) {
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.fecha = fecha;
    }
}