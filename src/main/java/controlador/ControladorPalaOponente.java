package controlador;

import javafx.scene.shape.Rectangle;
import modelo.Bola;
import modelo.HelpTools;

public class ControladorPalaOponente {

    private static final double alto = 80;
    private static final double ancho = 10;

    private int velocidad = 5;
    private Rectangle rectangulo;

    public ControladorPalaOponente() {
        this.rectangulo = new Rectangle(HelpTools.WIDTH * 0.15, HelpTools.HEIGHT / 2 - alto / 2, ancho, alto);
        this.rectangulo.setFill(HelpTools.COLOR_ITEMS);
    }

    public int getVelocidad() {
        return velocidad;
    }

    public double getAlto() {
        return alto;
    }

    public double getAncho() {
        return ancho;
    }

    public Rectangle getRectangulo() {
        return rectangulo;
    }

    private void resetPosicion() {
        this.rectangulo.setX(HelpTools.WIDTH * 0.15);
        this.rectangulo.setY(HelpTools.HEIGHT / 2 - alto / 2);
    }

    public double getTopPala() {
        return this.rectangulo.getY();
    }

    public double getBottomPala() {
        return this.rectangulo.getY() + alto;
    }

    public double getCentroPala() {
        return this.getTopPala() + alto / 2;
    }

    public void moverArriba() {
        if (getTopPala() > 0)
            this.rectangulo.setY(this.rectangulo.getY() - velocidad);
    }

    public void moverAbajo() {
        if (getBottomPala() < HelpTools.HEIGHT)
            this.rectangulo.setY(this.rectangulo.getY() + velocidad);
    }

    public void mover(Bola bola) {
        if (bola.getPosicionEjeY() > getCentroPala() + getAlto()/10) {
            moverAbajo();
        } else if (bola.getPosicionEjeY() < getCentroPala() - getAlto()/10) {
            moverArriba();
        }
    }

    public void setDificultad(int velocidadBola){
        this.velocidad = velocidadBola * (2/3);
    }
}
