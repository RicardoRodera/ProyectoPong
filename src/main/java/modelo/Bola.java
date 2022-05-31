package modelo;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Bola extends Rectangle {
    private static final double LADO = 20;

    public double getLado(){
        return LADO;
    }
    public Bola() {
        super(300, 300, LADO, LADO);
        super.setFill(HelpTools.COLOR_ITEMS);
    }

    public double getPosicionEjeX() {
        return super.getX();
    }

    public void setPosicionEjeX(double posicionEjeX) {
        super.setX(posicionEjeX);
    }

    public double getPosicionEjeY() {
        return super.getY();
    }

    public void setPosicionEjeY(double posicionEjeY) {
        super.setY(posicionEjeY);
    }
}
