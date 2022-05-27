package controlador;

import javafx.scene.shape.Rectangle;
import modelo.HelpTools;

public class ControladorPalaJugador {

    private static final double alto = 80;
    private static final double ancho = 10;

    private static final int velocidad = 5;
    private Rectangle rectangulo;


    public ControladorPalaJugador() {
        this.rectangulo = new Rectangle(HelpTools.WIDTH * 0.85, HelpTools.HEIGHT / 2 - alto / 2, ancho, alto);
        this.rectangulo.setFill(HelpTools.COLOR_ITEMS);
    }

    public int getVelocidad() {
        return velocidad;
    }

    public double getAlto(){
        return alto;
    }

    public double getAncho(){
        return ancho;
    }

    public Rectangle getRectangulo() {
        return rectangulo;
    }

    private void resetPosicion() {
        this.rectangulo.setX(HelpTools.WIDTH * 0.85);
        this.rectangulo.setY(HelpTools.HEIGHT / 2 - alto / 2);
    }

    public double getTopPala() {
        return this.rectangulo.getY();
    }

    public double getBottomPala() {
        return this.rectangulo.getY() + alto;
    }

    public void moverArriba() {
        if (getTopPala() > 0)
        this.rectangulo.setY(this.rectangulo.getY() - velocidad);
    }


    public void moverAbajo() {
        if (getBottomPala() < HelpTools.HEIGHT)
            this.rectangulo.setY(this.rectangulo.getY() + velocidad);
    }
}
