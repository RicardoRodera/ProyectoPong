


package modelo;
import controlador.ControladorBaseDatos;
import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class EscenaFin{

Scene escena;
boolean victoriaJugador;
private ScheduledExecutorService bgThread = Executors.newSingleThreadScheduledExecutor();
private int puntuacion;
private VBox menuFinBox;
private int itemActual = 0;
private Stage escenaFin;

    public EscenaFin(boolean victoriaJugador, int datosPartida) {
        this.puntuacion = datosPartida;
        this.escena = endScene();
        this.victoriaJugador = victoriaJugador;

    }

    private MenuInicio.MenuItem getMenuItem(int index) {
        return (MenuInicio.MenuItem)menuFinBox.getChildren().get(index);
    }

    public Scene endScene() {

        crearTexto(victoriaJugador);
        Pane layout = new Pane();


        MenuInicio.MenuItem itemExit = new MenuInicio.MenuItem("EXIT");
        itemExit.setOnActivate(() -> System.exit(0));

        menuFinBox = new VBox(10,
                new MenuInicio.MenuItem("PLAY AGAIN"),
                itemExit);
        menuFinBox.setAlignment(Pos.TOP_CENTER);
        menuFinBox.setTranslateX(360);
        menuFinBox.setTranslateY(450);

        getMenuItem(0).setActive(true);

        layout.getChildren().add(menuFinBox);

        try {
            try {
                layout.getChildren().add(historico());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        layout.getChildren().add(ultimaPartida());
        layout.setBackground(new Background(new BackgroundFill(HelpTools.COLOR_FONDO, CornerRadii.EMPTY, Insets.EMPTY)));
        Scene scene = new Scene(layout, HelpTools.WIDTH, HelpTools.HEIGHT);

        scene.setCursor(Cursor.DISAPPEAR);

        layout.getChildren().add(crearTexto(victoriaJugador));
        crearTexto(victoriaJugador).setLayoutX(300);
        crearTexto(victoriaJugador).setLayoutY(50);

        scene.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent tocaTecla) -> {
            if (tocaTecla.getCode() == KeyCode.UP) {
                if (itemActual > 0) {
                    getMenuItem(itemActual).setActive(false);
                    getMenuItem(--itemActual).setActive(true);
                }
            }

            if (tocaTecla.getCode() == KeyCode.DOWN) {
                if (itemActual < menuFinBox.getChildren().size() - 1) {
                    getMenuItem(itemActual).setActive(false);
                    getMenuItem(++itemActual).setActive(true);
                }
            }

            if (tocaTecla.getCode() == KeyCode.ENTER) {
                getMenuItem(itemActual).activate();
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
            letra.setFont(Font.font("", FontWeight.BOLD, 125));
            letras.setLayoutX(80);
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


    private VBox historico() throws SQLException, ClassNotFoundException {
        ControladorBaseDatos controladorBaseDatos = new ControladorBaseDatos();
        VBox contenido = new VBox();
        DatosRanking datosRanking =  controladorBaseDatos.leerDatosRanking(dificultad());
        String historico = datosRanking.toString();
        Text base = new Text(historico);
        base.setFill(HelpTools.COLOR_ITEMS);
        base.setFont(Font.font("", FontWeight.BOLD, 20));
        contenido.getChildren().add(base);
        contenido.setLayoutX(100);
        contenido.setLayoutY(200);
        return contenido;
    }

    private HBox ultimaPartida(){
        HBox contenido = new HBox();
        Text texto = new Text(puntuacionString());
        texto.setFill(HelpTools.COLOR_ITEMS);
        texto.setFont(Font.font("", FontWeight.BOLD, 20));
        contenido.getChildren().add(texto);

        contenido.setLayoutX(350);
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

    private String puntuacionString(){
        StringBuilder sb = new StringBuilder();

        sb.append("PUNTUACION\t");
        sb.append(this.puntuacion);
        System.out.println(this.puntuacion);
        return sb.toString();

    }
}







