package modelo;

import controlador.PartidaControlador;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
Esta clase es la que inicia la aplicacion y crea la ventana
 */

public class Main extends Application {


    private final static int MENU_INICIO = 0;
    private final static int PARTIDA = 1;
    private final static int PAUSA = 2;
    private final static int FINAL_PARTIDA = 3;
    private static int semaforo = PARTIDA;
   static PartidaControlador partidaControlador = new PartidaControlador();
    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("Proyecto Pong");
        // TODO: 26/5/22 Llamar desde esta clase a la escena que contiene el menu de inicio
        stage.setScene(cargarScene());
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private Scene cargarScene(){

        switch (semaforo){
        //    case MENU_INICIO: return MenuInicio.;

            case PARTIDA: return partidaControlador.getScene();

            case PAUSA:

            case FINAL_PARTIDA:

        }

        return null;
    }



}
