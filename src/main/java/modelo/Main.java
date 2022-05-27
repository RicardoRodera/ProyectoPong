package modelo;

import controlador.PartidaControlador;
import javafx.application.Application;
import javafx.stage.Stage;

/*
Esta clase es la que inicia la aplicacion y crea la ventana
 */

public class Main extends Application {


   static PartidaControlador partidaControlador = new PartidaControlador();
    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("Proyecto Pong");
        // TODO: 26/5/22 Llamar desde esta clase a la escena que contiene el menu de inicio
        stage.setScene(partidaControlador.getScene());
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }



}
