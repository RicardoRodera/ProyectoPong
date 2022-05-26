package modelo;

import javafx.scene.shape.Circle;

public class Bola extends Circle{
    private static final double RADIO = 2;

    public Bola() {
        super(300, -400, RADIO);
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
