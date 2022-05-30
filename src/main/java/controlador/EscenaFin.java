


package controlador;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
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
import HelpTools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class EscenaFin{





    @Override
    public void start(Stage escenaFin) throws FileNotFoundException {

        crearTexto();
        Pane layout = new Pane();
        Text newGame = new Text();
        Text quitGame = new Text();
        escenaFin.setTitle("PONG");
        newGame.setText("U: Nueva Partida");
        newGame.setFont(Font.font("impact", FontWeight.NORMAL, FontPosture.REGULAR, 15));
        newGame.setFill(HelpTools.COLOR_ITEMS);

        quitGame.setText("D: Salir de la aplicación");
        quitGame.setFont(Font.font("impact", FontWeight.NORMAL, FontPosture.REGULAR, 15));
        quitGame.setFill(HelpTools.COLOR_ITEMS);


        layout.getChildren().add(newGame);
        layout.getChildren().add(quitGame);
        layout.setBackground(new Background(new BackgroundFill(HelpTools.COLOR_FONDO, CornerRadii.EMPTY, Insets.EMPTY)));
        Scene scene = new Scene(layout, HelpTools.WIDTH, HelpTools.HEIGHT);
        escenaFin.setScene(scene);
        scene.setCursor(Cursor.DISAPPEAR);
        quitGame.setLayoutX(350);
        newGame.setLayoutX(350);
        newGame.setLayoutY(400);
        quitGame.setLayoutY(450);
        layout.getChildren().add(crearTexto());
        crearTexto().setLayoutX(300);
        crearTexto().setLayoutY(50);
        Pane layout2 = new Pane();
        layout2.setBackground(new Background(new BackgroundFill(HelpTools.COLOR_FONDO, CornerRadii.EMPTY, Insets.EMPTY)));
        layout2.getChildren().add(crearTexto());
        crearTexto().setLayoutX(300);
        crearTexto().setLayoutY(50);
        Text easy = new Text();
        Text medium = new Text();
        Text hard = new Text();
        easy.setText("U: Difícil");
        easy.setFont(Font.font("impact", FontWeight.NORMAL, FontPosture.REGULAR, 15));
        easy.setFill(HelpTools.COLOR_ITEMS);
        medium.setText("R: Dark Souls");
        medium.setFont(Font.font("impact", FontWeight.NORMAL, FontPosture.REGULAR, 15));
        medium.setFill(HelpTools.COLOR_ITEMS);
        hard.setText("D: Ikaruga");
        hard.setFont(Font.font("impact", FontWeight.NORMAL, FontPosture.REGULAR, 15));
        hard.setFill(HelpTools.COLOR_ITEMS);
        layout2.getChildren().add(easy);
        layout2.getChildren().add(medium);
        layout2.getChildren().add(hard);
        easy.setLayoutX(350);
        medium.setLayoutX(350);
        hard.setLayoutX(350);
        easy.setLayoutY(350);
        medium.setLayoutY(400);
        hard.setLayoutY(450);
        Scene scene2 = new Scene(layout2, 800, 600);
        escenaFin.initStyle(StageStyle.DECORATED);
        escenaFin.show();
        escenaFin.requestFocus();
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent event) -> {
            if(event.getCode() == KeyCode.UP){
                escenaFin.setScene(scene2);
                escenaFin.requestFocus();

            }
            if(event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.ESCAPE){
                escenaFin.close();
            }
        });
        scene2.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent event) -> {
            if(event.getCode() == KeyCode.UP){
                escenaFin.setScene(scene);
                escenaFin.requestFocus();

            }
            if(event.getCode() == KeyCode.RIGHT){
                escenaFin.setScene(scene);
                escenaFin.requestFocus();
            }
            if(event.getCode() == KeyCode.DOWN){
                escenaFin.setScene(scene);
                escenaFin.requestFocus();
            }
            if(event.getCode() == KeyCode.ESCAPE){
                escenaFin.setScene(scene);
            }
        });



    }

    private Node crearTexto() {
        String header = "GAME OVER";
        HBox letras = new HBox(0);
        letras.setAlignment(Pos.CENTER);
        for (int i = 0; i < header.length(); i++) {
            Text letra = new Text(header.charAt(i) + "");
            letra.setFont(Font.font("", FontWeight.BOLD, 135));
            letra.setFill(Color.WHITE);
            letra.setOpacity(0);
            letras.getChildren().add(letra);

            FadeTransition fachero = new FadeTransition(Duration.seconds(2), letra);
            fachero.setDelay(Duration.millis(i * 50));
            fachero.setToValue(1);
            fachero.setAutoReverse(true);
            fachero.setCycleCount(1);
            fachero.play();
        }
    return letras;
    }








    public static void main (String[]args){
            Application.launch(args);
        }


    }







