package dad.CambioDivisa;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CambioDivisa extends Application {

	//Model
	TextField campoUno;
	TextField campoDos;
	ComboBox<Divisa> comboBoxUno;
	ComboBox<Divisa> comboBoxDos;
	
	Button cambiar;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		campoUno = new TextField();
		campoDos = new TextField();
		campoDos.setEditable(false);
		
		cambiar = new Button("Cambiar");
		
		comboBoxUno = new ComboBox<Divisa>();
		comboBoxUno.getItems().addAll(
					new Divisa("Euro", 1.0),
					new Divisa("Libra", 0.8873),
					new Divisa("Dolar", 1.2007),
					new Divisa("Yen", 133.59)
				);
		comboBoxUno.getSelectionModel().selectFirst();
		
		comboBoxDos = new ComboBox<Divisa>();
		comboBoxDos.getItems().addAll(
					new Divisa("Euro", 1.0),
					new Divisa("Libra", 0.8873),
					new Divisa("Dolar", 1.2007),
					new Divisa("Yen", 133.59)
				);
		comboBoxDos.getSelectionModel().select(1);
		
		GridPane root = new GridPane();
		root.setAlignment(Pos.CENTER);
		root.setPadding(new Insets(5));
		root.setHgap(5);
		root.setVgap(5);
		root.addRow(0, campoUno, comboBoxUno);
		root.addRow(1, campoDos, comboBoxDos);
		root.addRow(2, cambiar);
		
		GridPane.setColumnSpan(cambiar, GridPane.REMAINING);
		
		Scene scene = new Scene(root, 400, 180);
		
		primaryStage.setTitle("Cambio Divisa");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		//bindeos
		
		cambiar.setOnAction(e -> onPulsarListener(e));

	}

	private void onPulsarListener(ActionEvent e) {

		try {
			Divisa origen = comboBoxUno.getValue();
			Divisa destino = comboBoxDos.getValue();
			
			campoDos.setText(
					Double.toString(
							destino.fromEuro(origen.toEuro((double)Integer.parseInt(campoUno.getText())))
							)
					);		
		}
		catch (NumberFormatException error) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Cambio de Divisa");
			alert.setHeaderText("Error");
			alert.setContentText("Por favor, introduzca un valor numérico");

			alert.showAndWait();
		}
		
	}

	public static void main(String[] args) {
		launch(args);

	}

}
