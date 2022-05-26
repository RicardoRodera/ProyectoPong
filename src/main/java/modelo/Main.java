package modelo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
/*
Esta clase es la que inicia la aplicacion y crea la ventana
 */

public class Main extends Application {
    private final static double WIDTH = 800;
    private final static double HEIGHT = 600;
    private final static Color COLOR_FONDO = Color.BLACK;
    private final static Color COLOR_ITEMS = Color.WHITE;
    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("Proyecto Pong");
        // TODO: 26/5/22 Llamar desde esta clase a la escena que contiene el menu de inicio
        stage.setScene(partida());
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    // TODO: 26/5/22 trasladar este codigo a la clase PartidaControlador
    private static Scene partida(){
        Pane root = new Pane();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.setFill(COLOR_FONDO);
        for(int i = 0;
            i < WIDTH;
            i += 50){
            //parametros line - startX, startY, endX, endY
            Line line  = new Line(WIDTH/2, i, WIDTH/2, i +10);
            line.setStroke(COLOR_ITEMS);
            line.setStrokeWidth(10);
            root.getChildren().add(line);
        }

        return scene;
    }
}
