package controlador;

import javafx.scene.Node;
import modelo.Bola;
import modelo.HelpTools;

public class ControladorBola extends Node {

    private Bola bola;
    private double velocidadX;
    private double velocidadY;

    public ControladorBola() {
        this.bola = new Bola();
        this.velocidadX = HelpTools.VELOCIDAD_BOLA_NORMAL;
        this.velocidadY = HelpTools.VELOCIDAD_BOLA_NORMAL;
    }

    public void manejarChoques(ControladorPalaJugador palaJugador) {
        manejarChoqueTecho();
        manejarChoqueSuelo();
        manejarChoquePalaJugador(palaJugador);
        manejarChoquePalaIA();
        manejarChoqueParedes();
    }

    private void manejarChoqueParedes() {
        if (bola.getPosicionEjeX() + bola.getRadius() >= HelpTools.WIDTH) {
            this.velocidadX *= -1;
        }

        if (bola.getPosicionEjeX() - bola.getRadius() <= 0) {
            this.velocidadX *= -1;
        }
    }

    private void manejarChoquePalaIA() {

    }

    // TODO: 27/05/2022 La pelota debe rebotar solo hacia la izquierda cuando hagamos los cambios que permitan marcar puntos.
    private void manejarChoquePalaJugador(ControladorPalaJugador palaJugador) {
        if (this.bola.getPosicionEjeX() + bola.getRadius() >= palaJugador.getRectangulo().getX()
                && this.bola.getPosicionEjeX() - bola.getRadius() <= palaJugador.getRectangulo().getX() + palaJugador.getAncho()) {
            if (this.bola.getPosicionEjeY() >= palaJugador.getTopPala() &&
                    this.bola.getPosicionEjeY() <= palaJugador.getBottomPala()) {
                this.velocidadX *= -1;
            }
        }
    }

    private void manejarChoqueSuelo() {
        if (bola.getPosicionEjeY() + bola.getRadius() >= HelpTools.HEIGHT) {
            this.velocidadY *= -1;
        }
    }

    private void manejarChoqueTecho() {
        if (bola.getPosicionEjeY() - bola.getRadius() <= 0) {
            this.velocidadY *= -1;
        }
    }

    public Bola getBola() {
        return bola;
    }

    public void mover() {
        bola.setPosicionEjeY(bola.getPosicionEjeY() + velocidadY);
        bola.setPosicionEjeX(bola.getPosicionEjeX() + velocidadX);
    }
}
