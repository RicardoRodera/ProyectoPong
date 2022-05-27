package controlador;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import modelo.Bola;
import modelo.HelpTools;

import static javafx.scene.input.KeyCode.*;


public class PartidaControlador  {

    ControladorPalaJugador palaJugador;
    //PalaAutomatica palaAutomatica
    private Bola bola;
    private Scene scene;
    private int velocidadX;
    private int velocidadY;

    private boolean parado = false;
    Timeline movimientoBola = null;

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
        this.velocidadX = HelpTools.VELOCIDAD_BOLA_NORMAL;
        this.velocidadY = HelpTools.VELOCIDAD_BOLA_NORMAL;
        this.scene = crearScenePartida();
    }

    private Scene crearScenePartida(){
        Pane root = new Pane();
        Scene scene = new Scene(root, HelpTools.WIDTH, HelpTools.HEIGHT);
        scene.setFill(HelpTools.COLOR_FONDO);


        for(int i = 0;
            i < HelpTools.HEIGHT ;
            i += 45){
            //parametros line - startX, startY, endX, endY
            Line line  = new Line(HelpTools.WIDTH/2, i, HelpTools.WIDTH/2, i + 20);
            line.setStroke(HelpTools.COLOR_ITEMS);
            line.setStrokeWidth(5);
            root.getChildren().add(line);

        }

        root.getChildren().add(palaJugador.getRectangulo());
        root.getChildren().add(bola);
        movimientoBola();




        return scene;
    }

    // Aqui quede pillado, no se como se hace una "pantalla".

    /* private Scene crearPause(Pane root){
        Scene scene = new Scene(root, HelpTools.WIDTH, HelpTools.HEIGHT);
        scene.setFill(HelpTools.COLOR_FONDO_PAUSE);


        for(int i = 0;
            i < HelpTools.HEIGHT ;
            i += 45){
            //parametros line - startX, startY, endX, endY
            Line line  = new Line(HelpTools.WIDTH/2, i, HelpTools.WIDTH/2, i + 20);
            line.setStroke(HelpTools.COLOR_ITEMS);
            line.setStrokeWidth(5);
            root.getChildren().add(line);

        }
        return scene;
    } */

    /**
     * Crea un bucle que mueve la bola y se ejecuta 60 veces por segundo
     */
    private void movimientoBola(){

        movimientoBola = new Timeline(
                new KeyFrame(Duration.seconds(0.017), (ActionEvent ae) ->{
                    bola.setPosicionEjeY(bola.getPosicionEjeY() + velocidadY);
                    bola.setPosicionEjeX(bola.getPosicionEjeX() + velocidadX);
                    if(bola.getPosicionEjeY() >= HelpTools.HEIGHT){
                        this.velocidadY = -3;
                    }
                    if (bola.getPosicionEjeY() <= 0){
                        this.velocidadY = 3;
                    }

                    if(bola.getPosicionEjeX() >= HelpTools.WIDTH){
                        this.velocidadX = -3;
                    }

                    if(bola.getPosicionEjeX() <= 0 ){
                        this.velocidadX = 3;
                    }

                    scene.setOnKeyPressed(keyEvent -> {

                        if(parado){
                            if(ESCAPE.equals(keyEvent.getCode())){
                                movimientoBola.play();
                                parado = false;
                            }
                        }else{
                            if(ESCAPE.equals(keyEvent.getCode())){
                                movimientoBola.pause();
                                parado = true;
                            }
                            movimientoPala(keyEvent.getCode());
                        }

                    });
                })
        );
        movimientoBola.setCycleCount(Timeline.INDEFINITE);//Esta linea hace que bucle de movimiento de la bola sea infinito
        movimientoBola.play();

    }


    public void movimientoPala(KeyCode event){

        if (UP.equals(event)) {

                palaJugador.moverArriba();

        } else if (DOWN.equals(event)) {

                palaJugador.moverAbajo();


        }

    }




}
