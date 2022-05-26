package controlador;

import javafx.scene.shape.Rectangle;
import modelo.HelpTools;

public class ControladorPalaJugador {

    private static final double alto = 80;
    private static final double ancho = 10;

    private static final int velocidad = 20;
    private Rectangle rectangulo;

    public ControladorPalaJugador() {
        this.rectangulo = new Rectangle(HelpTools.WIDTH * 0.85, HelpTools.HEIGHT / 2 - alto / 2, ancho, alto);
        this.rectangulo.setFill(HelpTools.COLOR_ITEMS);
    }

    public int getVelocidad() {
        return velocidad;
    }

    public Rectangle getRectangulo() {
        return rectangulo;
    }

    private void resetPosicion() {
        this.rectangulo.setX(HelpTools.WIDTH * 0.85);
        this.rectangulo.setY(HelpTools.HEIGHT / 2 - alto / 2);
    }

    public void moverArriba() {
        this.rectangulo.setY(this.rectangulo.getY() - velocidad);
    }

    public void moverAbajo() {
        this.rectangulo.setY(this.rectangulo.getY() + velocidad);
    }
}
