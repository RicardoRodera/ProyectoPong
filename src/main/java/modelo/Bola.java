package modelo;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Bola extends Circle{
    private static final double RADIO = 10;

    public Bola() {
        super(300, 400, RADIO, HelpTools.COLOR_ITEMS);
    }

    public double getPosicionEjeX() {
        return super.getCenterX();
    }

    public void setPosicionEjeX(double posicionEjeX) {
        super.setCenterX(posicionEjeX);
    }

    public double getPosicionEjeY() {
        return super.getCenterY();
    }

    public void setPosicionEjeY(double posicionEjeY) {
        super.setCenterY(posicionEjeY);
    }
}
