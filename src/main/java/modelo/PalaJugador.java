package modelo;

import controlador.ControladorPalaJugador;
import javafx.scene.shape.Rectangle;

public class PalaJugador {

    private static final double alto = 100;
    private static final double ancho = 10;

    private int velocidad;
    private Rectangle rectangulo;

    public PalaJugador() {
        this.velocidad = 0;
        this.rectangulo = new Rectangle(HelpTools.WIDTH * 0.85, HelpTools.HEIGHT / 2 + alto / 2, alto, ancho);
        this.rectangulo.setFill(HelpTools.COLOR_ITEMS);
        ControladorPalaJugador.resetPosicion();
    }

    public int getVelocidad() {
        return velocidad;
    }

    public Rectangle getRectangulo() {
        return rectangulo;
    }
}
