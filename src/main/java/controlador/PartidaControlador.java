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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;
import modelo.Bola;
import modelo.HelpTools;
import modelo.Marcador;

import static javafx.scene.input.KeyCode.*;


public class PartidaControlador  {

    private ControladorPalaJugador palaJugador;
    private ControladorPalaOponente controladorPalaOponente;
    private ControladorBola bola;
    private Scene scene;
    private Pane root;
    private Pane panePausa;
    private ControladorMarcador marcador;
    boolean palaMoviendoseArriba = false;
    boolean palaMoviendoseAbajo = false;
    boolean pausa = false;

    Timeline movimientoBola = null;

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public PartidaControlador() {

        this.palaJugador = new ControladorPalaJugador();
        this.controladorPalaOponente = new ControladorPalaOponente();
        this.bola = new ControladorBola();
        this.marcador = new ControladorMarcador();
        this.scene = crearScenePartida();
        this.panePausa = crearPanePausa();

    }

    private Pane crearPanePausa() {
        Pane pane = new Pane();
        pane.setPrefSize(HelpTools.WIDTH, HelpTools.HEIGHT);

        Rectangle bg = new Rectangle(HelpTools.WIDTH, HelpTools.HEIGHT);
        bg.setOpacity(0.8);

        Text texto = new Text("PULSA  [ESPACIO] \nPARA REANUDAR");
        texto.setFill(HelpTools.COLOR_ITEMS);
        texto.setFont(Font.font("", FontWeight.BOLD, 50));
        texto.setTranslateX(250);
        texto.setTranslateY(285);

        pane.getChildren().addAll(bg, texto);
        return pane;
    }

    private Scene crearScenePartida(){
        this.root = new Pane();
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
        root.getChildren().add(bola.getBola());
        root.getChildren().add(controladorPalaOponente.getRectangulo());
        root.getChildren().add(marcador.getMarcador());
        movimientoBola();

        scene.setOnKeyReleased(keyEvent -> {

            movimientoPala(keyEvent.getCode());

        });


        return scene;
    }


    /**
     * Crea un bucle que mueve la bola y se ejecuta 60 veces por segundo
     */
    private void movimientoBola(){
        final double DURACION_SEGUNDOS = 0.017;
        movimientoBola = new Timeline(
                new KeyFrame(Duration.seconds(DURACION_SEGUNDOS), (ActionEvent ae) ->{

                    bola.mover();
                    bola.manejarChoques(palaJugador, controladorPalaOponente);


                    scene.setOnKeyPressed(keyEvent -> {

                            movimientoPala(keyEvent.getCode());

                    });

                    scene.setOnKeyReleased(keyEvent -> {

                            finalizarMovimientoPala(keyEvent.getCode());

                    });

                    if(palaMoviendoseArriba){
                        palaJugador.moverArriba();
                    }
                    if(palaMoviendoseAbajo){
                        palaJugador.moverAbajo();
                    }
                    controladorPalaOponente.mover(bola.getBola());

                    if (bola.puntoIA()) {
                        marcador.anotarPuntoIA();
                    }

                    if (bola.puntoJugador()) {
                        marcador.anotarPuntoJugador();
                    }
                })

        );
        movimientoBola.setCycleCount(Timeline.INDEFINITE);//Esta linea hace que el bucle de movimiento de la bola sea infinito
        movimientoBola.play();

    }

    private void finalizarMovimientoPala(KeyCode code) {
        if (UP.equals(code)) {

            palaMoviendoseArriba = false;

        } else if (DOWN.equals(code)) {

            palaMoviendoseAbajo = false;

        }
    }


    public void movimientoPala(KeyCode event){

        if (UP.equals(event)) {

                palaMoviendoseArriba = true;

        } else if (DOWN.equals(event)) {

                palaMoviendoseAbajo = true;

        } else if (SPACE.equals(event)){

            if(pausa){
                pausa = false;
                movimientoBola.play();
                this.root.getChildren().remove(panePausa);
            } else{
                pausa= true;
                movimientoBola.pause();
                this.root.getChildren().add(panePausa);
            }

        }

    }

}
