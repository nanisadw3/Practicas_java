
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
class Marco extends JFrame{
    public Marco(){
        setTitle("Practica 7");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLocationRelativeTo(null);
        Lamina l = new Lamina();
        add(l);
    }
}
class Lamina extends JPanel{
    public Lamina(){

        setLayout(new BorderLayout());
        JPanel panel = crearPanel();
        add(panel, BorderLayout.CENTER);
    }
    ArrayList<Alumnos> alumnos = new ArrayList();
    JComboBox combo_nombres;
    JTextField txt_fecha;
    JCheckBox check_asistencias;
    private JPanel crearPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "Asistencia", TitledBorder.LEFT, TitledBorder.TOP));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;//los textfield se expanden y llenan toda la celda

        JLabel label_nombre = new JLabel("Nombre: ");
        JLabel label_fecha = new JLabel("Fecha: ");
        check_asistencias = new JCheckBox("Asistencia");
        combo_nombres = new JComboBox();
        txt_fecha = new JTextField(10);

        LocalDate fechaHoy = LocalDate.now();
        String hoy = fechaHoy.toString();
        txt_fecha.setText(hoy);
        txt_fecha.setEditable(false);
        txt_fecha.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btn_lista = new JButton("Guardar");
        JButton btn_mostrar = new JButton("Mostrar");
        btn_lista.addActionListener(e -> accion_pasarLista());
        btn_mostrar.addActionListener(e -> accion_mostrar());

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(label_nombre, gbc);
        gbc.gridx = 1;
        panel.add(combo_nombres, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(label_fecha, gbc);
        gbc.gridx = 1;
        panel.add(txt_fecha, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(check_asistencias, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(btn_lista, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(btn_mostrar, gbc);
        llenar_combo();
        return panel;
    }
    private void accion_mostrar(){
        String nombre;
        String fecha;
        String asistencia;
        if(alumnos != null){
            for (Alumnos a : alumnos){
                JOptionPane.showMessageDialog(null, "El alumno " + a.getNombre() + " " + a.getAsistencia());
            }
        }else{
            JOptionPane.showMessageDialog(null, "No hay alumnos registrados", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
    private void accion_pasarLista(){
        String nombre = combo_nombres.getSelectedItem().toString();
        String fecha = txt_fecha.getText();
        int asistio;
        String asistencia;
        if(nombre.equals("")||fecha.equals("")){
            JOptionPane.showMessageDialog(null, "Llena todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
        }else {
            if (check_asistencias.isSelected()) {
                asistio = 1;
                asistencia = "asistio";
            } else {
                asistio = 0;
                asistencia = "falto";
            }
            try {
                FileWriter fw = new FileWriter("Asistencias.txt",true);//para mantener lo ya escrito en dicho archivo
                BufferedWriter w = new BufferedWriter(fw);
                w.write("Nombre: " + nombre + " Fecha: " + fecha + " Asistencia: " + asistencia + "\n");

                w.close();
                check_asistencias.setSelected(false);
                Alumnos a = new Alumnos(nombre, fecha, asistencia);
                alumnos.add(a);
                JOptionPane.showMessageDialog(null, "Alumno " + nombre + " registrado", "Info", JOptionPane.INFORMATION_MESSAGE);

            }catch (Exception e){
                JOptionPane.showMessageDialog(null, "Error en la Escritura de los estudiantes", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private void llenar_combo(){
        try {
            FileReader ruta = new FileReader("nombres.txt");
            BufferedReader r = new BufferedReader(ruta);
            String linea;
            while (r.readLine() != null) { // Lee línea por línea
                combo_nombres.addItem(r.readLine());
            }
            r.close();

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error en la lectura de los estudiantes", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
class Alumnos{
    private String nombre;
    private String fecha;
    private String asistencia;

    public String getNombre() {
        return nombre;
    }

    public String getAsistencia() {
        return asistencia;
    }

    public Alumnos(String nombre, String fecha, String asistencia) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.asistencia = asistencia;
    }
}