package modelo;

import javafx.scene.paint.Color;

public class HelpTools {

    public final static double WIDTH = 900;
    public final static double HEIGHT = 600;
    public final static Color COLOR_FONDO = Color.BLACK;
    public final static Color COLOR_ITEMS = Color.WHITE;

    public final static Color COLOR_FONDO_PAUSE = Color.DARKGRAY;
    private static int VELOCIDAD_BOLA;

    public static void setVelocidadBola(int velocidadBola) {
        VELOCIDAD_BOLA = velocidadBola;
    }

    public static int getVelocidadBola() {
        return VELOCIDAD_BOLA;
    }
}
