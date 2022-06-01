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
        this.velocidadX = -HelpTools.getVelocidadBola() * (Math.sqrt(3) / 2);
        this.velocidadY = HelpTools.getVelocidadBola() * (0.5);
    }

    public void manejarChoques(ControladorPalaJugador palaJugador, ControladorPalaOponente palaOponente) {
        manejarChoqueTecho();
        manejarChoqueSuelo();
        manejarChoquePalaJugador(palaJugador);
        manejarChoquePalaIA(palaOponente);
    }

    public boolean puntoIA() {
        if (bola.getPosicionEjeX() + bola.getLado() >= HelpTools.WIDTH) {
            return true;
        }
        return false;
    }

    public boolean puntoJugador() {
        if (bola.getPosicionEjeX() <= 0) {
            return true;
        }
        return false;
    }


    private void manejarChoquePalaIA(ControladorPalaOponente palaOponente) {
        if (this.bola.getPosicionEjeX() <= palaOponente.getRectangulo().getX() + palaOponente.getAncho()
                && this.bola.getPosicionEjeX() + bola.getLado() >= palaOponente.getRectangulo().getX()) {
            if (this.bola.getPosicionEjeY() + bola.getLado() >= palaOponente.getTopPala() &&
                    this.bola.getPosicionEjeY() <= palaOponente.getBottomPala()) {
                int anguloDeRebote = calcularAngulo(palaOponente);
                aplicarAnguloPalaIAAVelocidad(anguloDeRebote);
            }
        }
    }

    private void aplicarAnguloPalaIAAVelocidad(int anguloDeRebote) {
        int velocidadBola = HelpTools.getVelocidadBola();
        switch (anguloDeRebote) {
            case 60:
                this.velocidadX = velocidadBola * (0.5);
                this.velocidadY = velocidadBola * (Math.sqrt(3) / 2);
                break;
            case 45:
                this.velocidadX = velocidadBola * (Math.sqrt(2) / 2);
                this.velocidadY = velocidadBola * (Math.sqrt(2) / 2);
                break;
            case 30:
                this.velocidadX = velocidadBola * (Math.sqrt(3) / 2);
                this.velocidadY = velocidadBola * (0.5);
                break;
            case 0:
                this.velocidadX = velocidadBola;
                this.velocidadY = 0;
                break;
            case -30:
                this.velocidadX = velocidadBola * (Math.sqrt(3) / 2);
                this.velocidadY = -velocidadBola * (0.5);
                break;
            case -45:
                this.velocidadX = velocidadBola * (Math.sqrt(2) / 2);
                this.velocidadY = -velocidadBola * (Math.sqrt(2) / 2);
                break;
            case -60:
                this.velocidadX = velocidadBola * (0.5);
                this.velocidadY = -velocidadBola * (Math.sqrt(3) / 2);
                break;
        }
    }

    private int calcularAngulo(ControladorPalaOponente palaOponente) {
        double diferencia = bola.getPosicionEjeY() - palaOponente.getTopPala();
        int angulo = 0;
        if (diferencia < palaOponente.getAlto() / 7) {
            angulo = 60;
        } else if (diferencia < palaOponente.getAlto() * 2 / 7) {
            angulo = 45;
        } else if (diferencia < palaOponente.getAlto() * 3 / 7) {
            angulo = 30;
        } else if (diferencia < palaOponente.getAlto() * 4 / 7) {
            angulo = 0;
        } else if (diferencia < palaOponente.getAlto() * 5 / 7) {
            angulo = -30;
        } else if (diferencia < palaOponente.getAlto() * 6 / 7) {
            angulo = -45;
        } else if (diferencia <= palaOponente.getAlto()) {
            angulo = -60;
        }

        return angulo;
    }


    private void manejarChoquePalaJugador(ControladorPalaJugador palaJugador) {
        if (this.bola.getPosicionEjeX() + bola.getLado() >= palaJugador.getRectangulo().getX()
                && this.bola.getPosicionEjeX() - bola.getLado() <= palaJugador.getRectangulo().getX()) {
            if (this.bola.getPosicionEjeY() + bola.getLado() >= palaJugador.getTopPala() &&
                    this.bola.getPosicionEjeY() <= palaJugador.getBottomPala()) {
                int anguloDeRebote = calcularAngulo(palaJugador);
                aplicarAnguloJugadorAVelocidad(anguloDeRebote);

            }
        }
    }

    private void aplicarAnguloJugadorAVelocidad(int anguloDeRebote) {
        int velocidadBola = HelpTools.getVelocidadBola();
        switch (anguloDeRebote) {
            case 60:
                this.velocidadX = -velocidadBola * (0.5);
                this.velocidadY = velocidadBola * (Math.sqrt(3) / 2);
                break;
            case 45:
                this.velocidadX = -velocidadBola * (Math.sqrt(2) / 2);
                this.velocidadY = velocidadBola * (Math.sqrt(2) / 2);
                break;
            case 30:
                this.velocidadX = -velocidadBola * (Math.sqrt(3) / 2);
                this.velocidadY = velocidadBola * (0.5);
                break;
            case 0:
                this.velocidadX = -velocidadBola;
                this.velocidadY = 0;
                break;
            case -30:
                this.velocidadX = -velocidadBola * (Math.sqrt(3) / 2);
                this.velocidadY = -velocidadBola * (0.5);
                break;
            case -45:
                this.velocidadX = -velocidadBola * (Math.sqrt(2) / 2);
                this.velocidadY = -velocidadBola * (Math.sqrt(2) / 2);
                break;
            case -60:
                this.velocidadX = -velocidadBola * (0.5);
                this.velocidadY = -velocidadBola * (Math.sqrt(3) / 2);
                break;
        }
    }

    private int calcularAngulo(ControladorPalaJugador palaJugador) {
        double diferencia = bola.getPosicionEjeY() + bola.getLado()/2 - palaJugador.getTopPala();
        int angulo = 0;
        if (diferencia < palaJugador.getAlto() / 7) {
            angulo = 60;
        } else if (diferencia < palaJugador.getAlto() * 2 / 7) {
            angulo = 45;
        } else if (diferencia < palaJugador.getAlto() * 3 / 7) {
            angulo = 30;
        } else if (diferencia < palaJugador.getAlto() * 4 / 7) {
            angulo = 0;
        } else if (diferencia < palaJugador.getAlto() * 5 / 7) {
            angulo = -30;
        } else if (diferencia < palaJugador.getAlto() * 6 / 7) {
            angulo = -45;
        } else {
            angulo = -60;
        }

        return angulo;
    }

    private void manejarChoqueSuelo() {
        if (bola.getPosicionEjeY() + bola.getLado() >= HelpTools.HEIGHT) {
            this.velocidadY = Math.abs(this.velocidadY);
        }
    }

    private void manejarChoqueTecho() {
        if (bola.getPosicionEjeY() <= 0) {
            this.velocidadY = -1 * Math.abs(this.velocidadY);
        }
    }

    public Bola getBola() {
        return bola;
    }

    public void mover() {
        bola.setPosicionEjeY(bola.getPosicionEjeY() - velocidadY);
        bola.setPosicionEjeX(bola.getPosicionEjeX() + velocidadX);
    }

    public void reiniciarBolaPuntoIA() {
        this.bola.setPosicionEjeX(200);
        this.bola.setPosicionEjeY(300);
        aplicarAnguloPalaIAAVelocidad(getAnguloAleatorio());
    }

    public void reiniciarBolaPuntoJugador() {
        this.bola.setPosicionEjeX(HelpTools.WIDTH - 200);
        this.bola.setPosicionEjeY(300);
        aplicarAnguloJugadorAVelocidad(getAnguloAleatorio());
    }

    private int getAnguloAleatorio() {
        int random = (int) (Math.random() * 7);
        switch (random) {
            case 0:
                return 60;
            case 1:
                return 45;
            case 2:
                return 30;
            case 3:
                return 0;
            case 4:
                return -30;
            case 5:
                return -45;
            case 6:
                return -60;
            default:
                return 0;
        }
    }
}
