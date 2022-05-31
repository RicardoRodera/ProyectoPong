


package modelo;
import controlador.ControladorBaseDatos;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;

public class EscenaFin{

Scene escena;
boolean victoriaJugador;
private DatosPartida ultimaPartida;

    public EscenaFin(boolean victoriaJugador, DatosPartida datosPartida) {
        this.escena = EndScene();
        this.victoriaJugador = victoriaJugador;
        this.ultimaPartida = datosPartida;
    }

    public void setUltimaPartida(DatosPartida ultimaPartida) {
        this.ultimaPartida = ultimaPartida;

    }

    public Scene EndScene() {

        crearTexto(victoriaJugador);
        Pane layout = new Pane();
        Text newGame = new Text();
        Text quitGame = new Text();

        newGame.setText("U: Nueva Partida");
        newGame.setFont(Font.font("impact", FontWeight.NORMAL, FontPosture.REGULAR, 15));
        newGame.setFill(HelpTools.COLOR_ITEMS);

        quitGame.setText("D: Salir de la aplicación");
        quitGame.setFont(Font.font("impact", FontWeight.NORMAL, FontPosture.REGULAR, 15));
        quitGame.setFill(HelpTools.COLOR_ITEMS);


        layout.getChildren().add(newGame);
        layout.getChildren().add(quitGame);
        try {
            layout.getChildren().add(historico());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //layout.getChildren().add(ultimaPartida());
        layout.setBackground(new Background(new BackgroundFill(HelpTools.COLOR_FONDO, CornerRadii.EMPTY, Insets.EMPTY)));
        Scene scene = new Scene(layout, HelpTools.WIDTH, HelpTools.HEIGHT);

        scene.setCursor(Cursor.DISAPPEAR);
        quitGame.setLayoutX(350);
        newGame.setLayoutX(350);
        newGame.setLayoutY(400);
        quitGame.setLayoutY(450);
        layout.getChildren().add(crearTexto(victoriaJugador));
        crearTexto(victoriaJugador).setLayoutX(300);
        crearTexto(victoriaJugador).setLayoutY(50);

        scene.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent tocaTecla) -> {
            if(tocaTecla.getCode() == KeyCode.UP){
            MenuInicio.reiniciar();

            }
            if(tocaTecla.getCode() == KeyCode.DOWN){
                Platform.exit();
            }
        });

        return scene;

    }











    private Node crearTexto(boolean victoriaJugador) {
        if(!victoriaJugador){
        String header = "GAME OVER";
        HBox letras = new HBox(0);
        letras.setAlignment(Pos.CENTER);
        for (int i = 0; i < header.length(); i++) {
            Text letra = new Text(header.charAt(i) + "");
            letra.setFont(Font.font("", FontWeight.BOLD, 150));
            letra.setFill(Color.WHITE);
            letra.setOpacity(0);
            letras.getChildren().add(letra);

            FadeTransition fachero = new FadeTransition(Duration.seconds(2), letra);
            fachero.setDelay(Duration.millis(i * 50));
            fachero.setToValue(1);
            fachero.setAutoReverse(true);
            fachero.setCycleCount(1);
            fachero.play();
        }return letras;
        }
        else{
            String header = "OF \nBITCH MOTHER";
            HBox letras = new HBox(0);
            letras.setAlignment(Pos.CENTER);
            for (int i = 0; i < header.length(); i++) {
                Text letraz = new Text(header.charAt(i) + "");
                letraz.setFont(Font.font("", FontWeight.BOLD, 100));
                letraz.setFill(Color.WHITE);
                letraz.setOpacity(0);
                letras.getChildren().add(letraz);

                FadeTransition fachero = new FadeTransition(Duration.seconds(2), letraz);
                fachero.setDelay(Duration.millis(i * 50));
                fachero.setToValue(1);
                fachero.setAutoReverse(true);
                fachero.setCycleCount(1);
                fachero.play();
            }return letras;
        }


    }


    public Scene getEscena() {
        return escena;
    }


    private VBox historico() throws SQLException {
        ControladorBaseDatos controladorBaseDatos = new ControladorBaseDatos();
        VBox contenido = new VBox();
        DatosRanking datosRanking =  controladorBaseDatos.leerDatosRanking(dificultad());
        String historico = datosRanking.toString();
        Text base = new Text(historico);
        base.setFill(HelpTools.COLOR_ITEMS);
        contenido.getChildren().add(base);
        contenido.setLayoutX(300);
        contenido.setLayoutY(200);
        return contenido;
    }

    private HBox ultimaPartida(){
        HBox contenido = new HBox();
        Text texto = new Text(ultimaPartida.toString());
        texto.setFill(HelpTools.COLOR_ITEMS);
        contenido.getChildren().add(texto);
        contenido.setLayoutX(300);
        contenido.setLayoutY(400);

        return contenido;
    }

    private String dificultad(){
        if(HelpTools.getVelocidadBola() == 4){
            return  "EASY";
        }else if(HelpTools.getVelocidadBola() == 6){
            return  "MEDIUM";
        }else {
            return  "HARD";
        }
    }
}







