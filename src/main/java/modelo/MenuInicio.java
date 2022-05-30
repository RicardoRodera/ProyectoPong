package modelo;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import controlador.PartidaControlador;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.print.attribute.standard.Media;

public class MenuInicio extends Application {
    private static final Font FONT = Font.font("", FontWeight.BOLD, 30);
    private VBox menuBox;
    private int itemActual = 0;
    private ScheduledExecutorService bgThread = Executors.newSingleThreadScheduledExecutor();
    private static Stage escenaInicio;

    private Parent crearContenido() {
        Pane root = new Pane();
        root.setPrefSize(HelpTools.WIDTH, HelpTools.HEIGHT);

        Rectangle bg = new Rectangle(900, 600);

        MarcoContenido frame = new MarcoContenido(crearTitulo());

        HBox hbox = new HBox(15, frame);
        hbox.setTranslateX(120);
        hbox.setTranslateY(50);

        MenuItem itemExit = new MenuItem("EXIT");
        itemExit.setOnActivate(() -> System.exit(0));

        menuBox = new VBox(10,
                new MenuItem("EASY"),
                new MenuItem("MEDIUM"),
                new MenuItem("HARD"),
                itemExit);
        menuBox.setAlignment(Pos.TOP_CENTER);
        menuBox.setTranslateX(360);
        menuBox.setTranslateY(300);

        Text informacion = new Text("IES Monte\n Naranco");
        informacion.setTranslateX(820);
        informacion.setTranslateY(570);
        informacion.setFill(HelpTools.COLOR_ITEMS);
        informacion.setFont(Font.font("", FontWeight.BOLD, 10));
        informacion.setOpacity(0.2);

        getMenuItem(0).setActive(true);

        root.getChildren().addAll(bg, hbox, menuBox, informacion);
        return root;
    }

    private Node crearTitulo() {
        String titulo = "PONG";
        HBox letras = new HBox(0);
        letras.setAlignment(Pos.CENTER);
        for (int i = 0; i < titulo.length(); i++) {
            Text letra = new Text(titulo.charAt(i) + "");
            letra.setFont(Font.font("", FontWeight.BOLD, 150));
            letra.setFill(HelpTools.COLOR_ITEMS);
            letra.setOpacity(0);
            letras.getChildren().add(letra);

            FadeTransition ft = new FadeTransition(Duration.seconds(2), letra);
            ft.setDelay(Duration.millis(i * 50));
            ft.setToValue(1);
            ft.setAutoReverse(true);
            ft.setCycleCount(TranslateTransition.INDEFINITE);
            ft.play();
        }

        return letras;
    }

    private MenuItem getMenuItem(int index) {
        return (MenuItem)menuBox.getChildren().get(index);
    }

    private static class MarcoContenido extends StackPane {
        public MarcoContenido(Node contenido) {
            setAlignment(Pos.CENTER);

            Rectangle frame = new Rectangle(650, 200);
            frame.setArcWidth(25);
            frame.setArcHeight(25);
            frame.setStroke(HelpTools.COLOR_ITEMS);

            getChildren().addAll(frame, contenido);
        }
    }

    private static class MenuItem extends HBox {
        private Text texto;
        private Runnable script;

        public MenuItem(String nombre) {
            super(15);
            setAlignment(Pos.CENTER);

            texto = new Text(nombre);
            texto.setFont(FONT);
            texto.setEffect(new GaussianBlur(2));

            getChildren().addAll(texto);
            setActive(false);
            setOnActivate(() ->

                   iniciarPartida(nombre));

        }

        public void setActive(boolean b) {
            texto.setFill(b ? HelpTools.COLOR_ITEMS : Color.GREY);
        }

        public void setOnActivate(Runnable r) {
            script = r;
        }

        public void activate() {
            if (script != null)
                script.run();
        }
    }

    public void start(Stage escenaInicio) throws Exception {
        this.escenaInicio = escenaInicio;
        Scene escena = new Scene(crearContenido());
        escena.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.UP) {
                if (itemActual > 0) {
                    getMenuItem(itemActual).setActive(false);
                    getMenuItem(--itemActual).setActive(true);
                }
            }

            if (event.getCode() == KeyCode.DOWN) {
                if (itemActual < menuBox.getChildren().size() - 1) {
                    getMenuItem(itemActual).setActive(false);
                    getMenuItem(++itemActual).setActive(true);
                }
            }

            if (event.getCode() == KeyCode.ENTER) {
                getMenuItem(itemActual).activate();
            }
        });

        escenaInicio.setScene(escena);
        escenaInicio.setOnCloseRequest(event -> {
            bgThread.shutdownNow();
        });
        escenaInicio.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private static void iniciarPartida(String nivel){

        switch (nivel){
            case "EASY":
                HelpTools.setVelocidadBola(Dificultad.EASY.getVelocidad());
                break;
            case "MEDIUM":
                HelpTools.setVelocidadBola(Dificultad.MEDIUM.getVelocidad());
                break;
            case "HARD":
                HelpTools.setVelocidadBola(Dificultad.HARD.getVelocidad());
                break;
        }

        PartidaControlador partidaControlador = new PartidaControlador();
        setScene(partidaControlador.getScene());

    }

    public static void setScene(Scene scene){
        escenaInicio.setScene(scene);
    }

}
