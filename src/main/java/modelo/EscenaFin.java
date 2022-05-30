


package modelo;
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

public class EscenaFin{

Scene escena;


    public EscenaFin() {
        this.escena = EndScene();
    }


    public Scene EndScene() {

        crearTexto();
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
        layout.setBackground(new Background(new BackgroundFill(HelpTools.COLOR_FONDO, CornerRadii.EMPTY, Insets.EMPTY)));
        Scene scene = new Scene(layout, HelpTools.WIDTH, HelpTools.HEIGHT);

        scene.setCursor(Cursor.DISAPPEAR);
        quitGame.setLayoutX(350);
        newGame.setLayoutX(350);
        newGame.setLayoutY(400);
        quitGame.setLayoutY(450);
        layout.getChildren().add(crearTexto());
        crearTexto().setLayoutX(300);
        crearTexto().setLayoutY(50);

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











    private Node crearTexto() {
        String header = "GAME OVER";
        HBox letras = new HBox(0);
        letras.setAlignment(Pos.CENTER);
        for (int i = 0; i < header.length(); i++) {
            Text letra = new Text(header.charAt(i) + "");
            letra.setFont(Font.font("", FontWeight.BOLD, 155));
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


    public Scene getEscena() {
        return escena;
    }
}







