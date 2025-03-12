import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.border.TitledBorder;

public class Main {
    public static void main(String[] args) {
        Marco m = new Marco();
        m.setVisible(true);
    }
}

class Marco extends JFrame {
    public Marco() {
        setTitle("Marco");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setResizable(false);
        setLocationRelativeTo(null);
        Lamina l = new Lamina();
        add(l);
    }
}

class Lamina extends JPanel {
    private ArrayList<Pizzeria> lista = new ArrayList<>();
    private JComboBox<String> combo_clave;
    private JComboBox<String> combo_pedido;
    private JTextField txt_repartidor;
    private JButton btn_actualizar;

    public Lamina() {
        setLayout(new BorderLayout());
        JPanel panel = crearPanel();
        add(panel, BorderLayout.CENTER);

        // Inicializar opciones del combo antes de cargar datos
        combo_pedido.addItem("Preparado");
        combo_pedido.addItem("Horneando");
        combo_pedido.addItem("Entregado");

        cargar(); // Cargar pedidos desde el archivo
        combo_clave.addActionListener(e -> accionClave());
        combo_pedido.addActionListener(e -> accionPedido());
        btn_actualizar.addActionListener(e -> actualizarPedido());
    }

    private JPanel crearPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "Pizzeria", TitledBorder.LEFT, TitledBorder.TOP));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel clavePedido = new JLabel("Clave pedido: ");
        JLabel estatusPedido = new JLabel("Estatus Pedido: ");
        JLabel repartidor = new JLabel("Repartidor: ");

        combo_clave = new JComboBox<>();
        combo_pedido = new JComboBox<>();
        txt_repartidor = new JTextField(10);
        btn_actualizar = new JButton("Actualizar");

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(clavePedido, gbc);
        gbc.gridx = 1;
        panel.add(combo_clave, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(estatusPedido, gbc);
        gbc.gridx = 1;
        panel.add(combo_pedido, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(repartidor, gbc);
        gbc.gridx = 1;
        panel.add(txt_repartidor, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(btn_actualizar, gbc);

        return panel;
    }

    private void accionPedido() {
        String clave = (String) combo_clave.getSelectedItem();
        if (clave == null) return;

        String repartidor = "";
        for (Pizzeria pizza : lista) {
            if (pizza.clave.equals(clave)) {
                repartidor = pizza.repartidor.trim();
                break;
            }
        }
            txt_repartidor.setEnabled(true);
            txt_repartidor.setEditable(true);

    }

    private void accionClave() {
        String clave = (String) combo_clave.getSelectedItem();
        if (clave == null) return;

        String pedido = "";
        String repartidor = "";

        for (Pizzeria pizza : lista) {
            if (pizza.clave.equals(clave)) {
                pedido = pizza.pedido.trim();
                repartidor = pizza.repartidor.trim();
                break;
            }
        }

        combo_pedido.setSelectedItem(pedido);
        txt_repartidor.setText(repartidor);
        accionPedido(); // Llamar para actualizar estado de edición del repartidor
    }

    private void cargar() {
        try (BufferedReader r = new BufferedReader(new FileReader("Entregas.dat"))) {
            String linea;
            while ((linea = r.readLine()) != null) {
                String[] datos = linea.split("\\|");
                if (datos.length < 2) continue;

                String clave = datos[0];
                String pedido = datos[1].trim();
                String repartidor = (datos.length > 2) ? datos[2].trim() : "Sin asignar";

                // Normalizar estados
                if (pedido.equalsIgnoreCase("Preparando")) pedido = "Preparado";
                if (pedido.equalsIgnoreCase("Entregando")) pedido = "Entregado";

                Pizzeria p = new Pizzeria(clave, pedido, repartidor);
                combo_clave.addItem(clave);
                lista.add(p);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la carga", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarPedido() {
        String clave = (String) combo_clave.getSelectedItem();
        if (clave == null) return;

        String nuevoPedido = (String) combo_pedido.getSelectedItem();
        String nuevoRepartidor = txt_repartidor.getText().trim();

        for (Pizzeria pizza : lista) {
            if (pizza.clave.equals(clave)) {
                pizza.pedido = nuevoPedido;
                pizza.repartidor = nuevoRepartidor;
                break;
            }
        }

        // Guardar los cambios en el archivo
        guardarCambios();
        JOptionPane.showMessageDialog(null, "Pedido actualizado correctamente.");
    }

    private void guardarCambios() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("Entregas.dat"))) {
            for (Pizzeria pizza : lista) {
                writer.println(pizza.clave + "|" + pizza.pedido + "|" + pizza.repartidor);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al guardar cambios", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

class Pizzeria {
    String clave;
    String pedido;
    String repartidor;

    public Pizzeria(String clave, String pedido, String repartidor) {
        this.clave = clave;
        this.pedido = pedido;
        this.repartidor = repartidor;
    }
}
