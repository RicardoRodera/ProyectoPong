package modelo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
/*
Esta clase es la que inicia la aplicacion y crea la ventana
 */
// TODO: 26/5/22 Llamar desde esta clase a la escena que contiene el menu de inicio

public class Main extends Application {
    private final static double WIDTH = 800;
    private final static double HEIGHT = 600;
    private final static Color COLOR_FONDO = Color.BLACK;
    private final static Color COLOR_ITEMS = Color.WHITE;
    @Override
    public void start(Stage stage) throws Exception {
        Pane root = new Pane();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.setFill(COLOR_FONDO);
        stage.setTitle("Proyecto Pong");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
