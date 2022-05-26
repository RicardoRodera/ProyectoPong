package controlador;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import modelo.Bola;
import modelo.HelpTools;


public class PartidaControlador  {

    ControladorPalaJugador palaJugador;
    //PalaAutomatica palaAutomatica
    Bola bola;
    Scene scene;

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public PartidaControlador() {

        this.palaJugador = new ControladorPalaJugador();
        //this.palaAutomatica = new PalaAutomatica();
        this.bola = new Bola();
        this.scene = crearScenePartida();
    }

    private Scene crearScenePartida(){
        Pane root = new Pane();
        Scene scene = new Scene(root, HelpTools.WIDTH, HelpTools.HEIGHT);
        scene.setFill(HelpTools.COLOR_FONDO);
        for(int i = 0;
            i < HelpTools.WIDTH;
            i += 50){
            //parametros line - startX, startY, endX, endY
            Line line  = new Line(HelpTools.WIDTH/2, i, HelpTools.WIDTH/2, i +10);
            line.setStroke(HelpTools.COLOR_ITEMS);
            line.setStrokeWidth(10);
            root.getChildren().add(line);

        }
       // root.getChildren().add(rectangle);
        root.getChildren().add(palaJugador.getRectangulo());
        root.getChildren().add(bola);
        return scene;
    }




}
