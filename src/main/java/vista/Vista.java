package vista;

import divisa.Divisa;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;


public class Vista extends Application{
	
	//Creamos los 4 valores: Euro, Libra, Dolar y Yen
	private Divisa Euro = new Divisa("Euro", 1.0);
	private Divisa Libra = new Divisa("Libra", 0.8873);
	private Divisa Dolar = new Divisa("Dolar", 1.2007);
	private Divisa Yen = new Divisa("Yen", 133.59);
	
	//Los introducimos en una tabla
	private Divisa[] divisas = {Euro, Libra, Dolar, Yen};
	
	//Inicializamos los botones que nos harán falta en la Aplicación
	private TextField origenTexto, destinoTexto;
	private ComboBox<Divisa> origenCombo, destinoCombo;
	private Button botonCambia;
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		
		origenTexto = new TextField("0");
		origenTexto.setPrefColumnCount(5);
		
		//Añadimos el combobox con las divisas de nuestra lista
		origenCombo = new ComboBox();
		origenCombo.getItems().addAll(divisas);
		origenCombo.getSelectionModel().select(Euro);
		
		//Junta los dos items anteriores
		HBox origenBox = new HBox();
		origenBox.setAlignment(Pos.BASELINE_CENTER);
		origenBox.setSpacing(5);
		origenBox.getChildren().addAll(origenTexto, origenCombo);
		
		//Ahora realizamos lo mismo pero con el destino
		destinoTexto = new TextField("0");
		destinoTexto.setPrefColumnCount(5);
		destinoTexto.setEditable(false);
		
		destinoCombo = new ComboBox();
		destinoCombo.getItems().addAll(divisas);
		destinoCombo.getSelectionModel().select(Libra);
		
		HBox destinoBox = new HBox();
		destinoBox.setAlignment(Pos.BASELINE_CENTER);
		destinoBox.setSpacing(5);
		destinoBox.getChildren().addAll(destinoTexto, destinoCombo);
		
		botonCambia = new Button("Cambiar");
		botonCambia.setOnAction(e -> alCambiarLaAccion(e));
		
		//Creamos la ventana en la cual metemos todo este royo xd
		VBox root = new VBox();
		root.setSpacing(5);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(origenBox, destinoBox, botonCambia);
		
		Scene escena = new Scene(root, 320, 200);
		
		primaryStage.setTitle("Cambio de Divisa");
		primaryStage.setScene(escena);
		primaryStage.show();
		
		
		
	}

	//Esta funcion detemina el valor a partir de los valores que le demos
	private void alCambiarLaAccion(ActionEvent e) {
		//Recoge el valor puesto y lo tranforma en double
		Double cantidadOrigen = Double.parseDouble(origenTexto.getText());
		
		//Recoge la moneda del comboBox origen
		Divisa divisaOrigen = origenCombo.getSelectionModel().getSelectedItem();
		
		//Recoge la moneda del comboBox destino
		Divisa divisaDestino = destinoCombo.getSelectionModel().getSelectedItem();
		
		
		Double enEuros = divisaOrigen.toEuro(cantidadOrigen);
		Double cantidadDestino = divisaDestino.fromEuro(enEuros);
		
		destinoTexto.setText(""+cantidadDestino);
		
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}

}