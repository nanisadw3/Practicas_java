import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application{
    @Override
    public void start(Stage primaryStage) {
        Marco marco = new Marco();
        primaryStage.setScene(new Scene(marco, 300, 300));
        primaryStage.setTitle("Practica 1");
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
class Marco extends BorderPane {
    Marco() {
        Lamina lamina = new Lamina();
        setCenter(lamina);
    }
}

class Lamina extends VBox {
    private TextField text_saldo, text_cuenta, text_anualidad, text_Titular;
    private TextField txt_distancia, txt_tiempo;
    private TextField txt_diastolica, txt_sistolica;

    Lamina() {
        TabPane tabbedPane = new TabPane();
        tabbedPane.getTabs().add(new Tab("Programa 1", programa1()));
        tabbedPane.getTabs().add(new Tab("Programa 2", programa2()));
        tabbedPane.getTabs().add(new Tab("Programa 3", programa3()));
        tabbedPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        getChildren().add(tabbedPane);
    }
    private void mostrarMensaje(String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setContentText(mensaje);
        alert.show();
    }
    private GridPane programa1() {
        GridPane panel = new GridPane();
        panel.setPadding(new Insets(10));
        panel.setHgap(10);
        panel.setVgap(10);

        Label label_saldo = new Label("Saldo:");
        text_saldo = new TextField();
        Label label_cuenta = new Label("Cuenta:");
        text_cuenta = new TextField();
        Label label_anualidad = new Label("Anualidad:");
        text_anualidad = new TextField();
        Label label_Titular = new Label("Titular:");
        text_Titular = new TextField();
        Button btn_crear = new Button("Crear");
        btn_crear.setOnAction(e -> accionBtnCrear());

        panel.add(label_saldo, 0, 0); panel.add(text_saldo, 1, 0);
        panel.add(label_cuenta, 0, 1); panel.add(text_cuenta, 1, 1);
        panel.add(label_anualidad, 0, 2); panel.add(text_anualidad, 1, 2);
        panel.add(label_Titular, 0, 3); panel.add(text_Titular, 1, 3);
        panel.add(btn_crear, 1, 4);

        return panel;
    }

    private void accionBtnCrear() {
        if (text_Titular.getText().isEmpty() || text_cuenta.getText().isEmpty() || text_anualidad.getText().isEmpty() || text_saldo.getText().isEmpty()) {
            mostrarMensaje("Llena todos los campos", Alert.AlertType.ERROR);
        } else {
            mostrarMensaje("Objeto creado exitosamente", Alert.AlertType.INFORMATION);
        }
    }

    private GridPane programa2() {
        GridPane panel = new GridPane();
        panel.setPadding(new Insets(10));
        panel.setHgap(10);
        panel.setVgap(10);

        Label label_distancia = new Label("Distancia:");
        txt_distancia = new TextField();
        Label label_tiempo = new Label("Tiempo:");
        txt_tiempo = new TextField();
        Button btn_calcular = new Button("Calcular");
        btn_calcular.setOnAction(e -> accionBtnCalcular());

        panel.add(label_distancia, 0, 0); panel.add(txt_distancia, 1, 0);
        panel.add(label_tiempo, 0, 1); panel.add(txt_tiempo, 1, 1);
        panel.add(btn_calcular, 1, 2);

        return panel;
    }

    private void accionBtnCalcular() {
        if (txt_distancia.getText().isEmpty() || txt_tiempo.getText().isEmpty()) {
            mostrarMensaje("Campos vacíos", Alert.AlertType.ERROR);
        } else {
            int distancia = Integer.parseInt(txt_distancia.getText());
            int tiempo = Integer.parseInt(txt_tiempo.getText());
            int velocidad = distancia / tiempo;
            mostrarMensaje("La velocidad es " + velocidad, Alert.AlertType.INFORMATION);
        }
    }

    private GridPane programa3() {
        GridPane panel = new GridPane();
        panel.setPadding(new Insets(10));
        panel.setHgap(10);
        panel.setVgap(10);

        Label label_diastolica = new Label("Diastólica:");
        txt_diastolica = new TextField();
        Label label_sistolica = new Label("Sistólica:");
        txt_sistolica = new TextField();
        Button btn_precion = new Button("Calcular");
        btn_precion.setOnAction(e -> accionPrecion());

        panel.add(label_diastolica, 0, 0); panel.add(txt_diastolica, 1, 0);
        panel.add(label_sistolica, 0, 1); panel.add(txt_sistolica, 1, 1);
        panel.add(btn_precion, 1, 2);

        return panel;
    }
    private String obtenerPresion(int sistolica, int diastolica) {
        if (sistolica < 120 && diastolica < 80) return "Normal";
        if (sistolica >= 120 && sistolica <= 129 && diastolica < 80) return "Elevada";
        if ((sistolica >= 130 && sistolica <= 139) || (diastolica >= 80 && diastolica <= 89)) return "Hipertensión 1";
        if (sistolica >= 140 || diastolica >= 90) return "Hipertensión 2";
        return "Crisis Hipertensiva";
    }

    private void accionPrecion() {
        if (txt_sistolica.getText().isEmpty() || txt_diastolica.getText().isEmpty()) {
            mostrarMensaje("Campos vacíos", Alert.AlertType.ERROR);
        } else {
            int sistolica = Integer.parseInt(txt_sistolica.getText());
            int diastolica = Integer.parseInt(txt_diastolica.getText());
            mostrarMensaje("El estado es: " + obtenerPresion(sistolica, diastolica), Alert.AlertType.INFORMATION);
        }
    }
}
