package controlador;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;
import modelo.*;

import java.sql.SQLException;

import static javafx.scene.input.KeyCode.*;


public class ControladorPartida {

    private ControladorPalaJugador palaJugador;
    private ControladorPalaOponente controladorPalaOponente;
    private ControladorBola bola;
    private Scene scene;
    private Pane root;
    private Pane panePausa;
    private ControladorMarcador marcador;
    private boolean palaMoviendoseArriba = false;
    private boolean palaMoviendoseAbajo = false;
    private boolean pausa = false;
    private double ciclos;
    private Timeline movimientoBola = null;
    private int datosPartida;
    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public ControladorPartida() {

        this.palaJugador = new ControladorPalaJugador();
        this.controladorPalaOponente = new ControladorPalaOponente();
        controladorPalaOponente.setDificultad(HelpTools.getVelocidadBola());
        this.bola = new ControladorBola();
        this.marcador = new ControladorMarcador();
        this.scene = crearScenePartida();
        this.panePausa = crearPanePausa();
        this.ciclos = 0;
    }

    private Pane crearPanePausa() {
        Pane pane = new Pane();
        pane.setPrefSize(HelpTools.WIDTH, HelpTools.HEIGHT);

        Rectangle bg = new Rectangle(HelpTools.WIDTH, HelpTools.HEIGHT);
        bg.setOpacity(0.8);

        Text textoReanudar = new Text("PULSA  [ESPACIO] \nPARA REANUDAR");
        textoReanudar.setFill(HelpTools.COLOR_ITEMS);
        textoReanudar.setFont(Font.font("", FontWeight.BOLD, 50));
        textoReanudar.setTranslateX(250);
        textoReanudar.setTranslateY(285);

        Text textoSalir = new Text("PULSA  [ESC]  PARA ABANDONAR LA PARTIDA");
        textoSalir.setTranslateX(30);
        textoSalir.setTranslateY(570);
        textoSalir.setFill(HelpTools.COLOR_ITEMS);
        textoSalir.setFont(Font.font("", FontWeight.BOLD, 17));
        textoSalir.setOpacity(0.5);

        pane.getChildren().addAll(bg, textoReanudar, textoSalir);
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
                    this.ciclos++;
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
                        bola.reiniciarBolaPuntoIA();
                        palaJugador.resetearPala();
                        controladorPalaOponente.resetearPala();
                    }

                    if (bola.puntoJugador()) {
                        marcador.anotarPuntoJugador();
                        bola.reiniciarBolaPuntoJugador();
                        palaJugador.resetearPala();
                        controladorPalaOponente.resetearPala();
                    }

                    if(marcador.ganadorIA()){
                        finalizar(false);
                    }

                    if(marcador.ganadorJugador()){
                        finalizar(true);
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

        } else if(ESCAPE.equals(event)){
            if(pausa) {
                MenuInicio.reiniciar();
            }
        }

    }

    private void finalizar(boolean ganador){
        movimientoBola.stop();
        try {
          grabarPartida();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            EscenaFin escenaFin = new EscenaFin(ganador, datosPartida);
            MenuInicio.setScene(escenaFin.getEscena());
        }

    }

    private void grabarPartida() throws SQLException {
        String nivel;
        int puntos = marcador.getPuntosJugador() - marcador.getPuntosIA();
        int duracion = (int) (ciclos / 60);
        int puntuacion = (puntos * 100) - duracion;
        this.datosPartida =  puntuacion;



        if(HelpTools.getVelocidadBola() == 4){
            nivel = "EASY";
        }else if(HelpTools.getVelocidadBola() == 6){
            nivel = "MEDIUM";
        }else {
            nivel = "HARD";
        }

        if(puntos > 0){

            ControladorBaseDatos controladorBaseDatos = null;
            try {
                controladorBaseDatos = new ControladorBaseDatos();
                controladorBaseDatos.grabarPartida(nivel, puntos, duracion, puntuacion);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }


    }




}
