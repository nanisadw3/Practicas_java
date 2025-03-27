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
import java.util.Calendar;
import java.util.Date;
import java.time.ZoneId;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
    Marco marco = new Marco();
    marco.setVisible(true);
    marco.setLocationRelativeTo(null);
    marco.setResizable(false);
    }
}
class Marco extends JFrame {
    public Marco() {
        setTitle("Marco");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        Panel panel = new Panel();
        add(panel);

    }
}
class Panel extends JPanel {
    JTextField txt_usuario;
    JTextField txt_contrasena;
    public Panel() {

        setLayout(new BorderLayout());
        JPanel panel = crearPanel();
        add(panel, BorderLayout.CENTER);
    }
    private JPanel crearPanel(){
        JPanel panel = new JPanel();

        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "Cafetera", TitledBorder.LEFT, TitledBorder.TOP));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;


        radio_Te = new JRadioButton("Te");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(radio_Te, gbc);

        radio_Cafe = new JRadioButton("Cafe");
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(radio_Cafe, gbc);

        JLabel calentar = new JLabel("Calentar:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(calentar, gbc);

        txt_calentar = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(txt_calentar, gbc);

        JButton btn_preparar = new JButton("Preparar");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;// el botón ocupará 2 columnas para centrarse

        panel.add(btn_preparar, gbc);

        btn_preparar.addActionListener(e -> accionPreparar());

        //creamos el grupo de los radiobuton para que si seleccionas uno se des seleccione el otro
        ButtonGroup grupoBebidas = new ButtonGroup();
        grupoBebidas.add(radio_Te);
        grupoBebidas.add(radio_Cafe);

        return panel;
    }
    private void accionPreparar() {
        try {

            int calentar = Integer.parseInt(txt_calentar.getText());
            if(radio_Te.isSelected()){
                Te te = new Te();
                te.calentar(calentar);
                String mensaje = te.mostrarDescripcion();
                JOptionPane.showMessageDialog(this, mensaje, "Mensaje", JOptionPane.INFORMATION_MESSAGE);

            }else if(radio_Cafe.isSelected()){
                Cafe cafe = new Cafe();
                cafe.calentar(calentar);
                String mensaje = cafe.mostrarDescripcion();
                JOptionPane.showMessageDialog(this, mensaje, "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al preparar "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    JRadioButton radio_Te;
    JRadioButton radio_Cafe;
    JTextField txt_calentar;
}