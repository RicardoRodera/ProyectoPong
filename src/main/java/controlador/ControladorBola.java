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
        this.velocidadX = - HelpTools.getVelocidadBola() * (Math.sqrt(3) / 2);
        this.velocidadY = HelpTools.getVelocidadBola() * (0.5);
    }

    public void manejarChoques(ControladorPalaJugador palaJugador, ControladorPalaOponente palaOponente) {
        manejarChoqueTecho();
        manejarChoqueSuelo();
        manejarChoquePalaJugador(palaJugador);
        manejarChoquePalaIA(palaOponente);
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

    public boolean puntoIA(){
        if (bola.getPosicionEjeX() + bola.getRadius() >= HelpTools.WIDTH) {
            return true;
        }
        return false;
    }

    public boolean puntoJugador(){
        if (bola.getPosicionEjeX() - bola.getRadius() <= 0) {
            return true;
        }
        return false;
    }


    private void manejarChoquePalaIA(ControladorPalaOponente palaOponente) {
        if (this.bola.getPosicionEjeX() + bola.getRadius() >= palaOponente.getRectangulo().getX()
                && this.bola.getPosicionEjeX() - bola.getRadius() <= palaOponente.getRectangulo().getX() + palaOponente.getAncho()) {
            if (this.bola.getPosicionEjeY() >= palaOponente.getTopPala() &&
                    this.bola.getPosicionEjeY() <= palaOponente.getBottomPala()) {
                int anguloDeRebote = calcularAngulo(palaOponente);
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
        if (this.bola.getPosicionEjeX() + bola.getRadius() >= palaJugador.getRectangulo().getX()
                && this.bola.getPosicionEjeX() - bola.getRadius() <= palaJugador.getRectangulo().getX() + palaJugador.getAncho()) {
            if (this.bola.getPosicionEjeY() >= palaJugador.getTopPala() &&
                    this.bola.getPosicionEjeY() <= palaJugador.getBottomPala()) {
                int anguloDeRebote = calcularAngulo(palaJugador);
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
        }
    }

    private int calcularAngulo(ControladorPalaJugador palaJugador) {
        double diferencia = bola.getPosicionEjeY() - palaJugador.getTopPala();
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
        } else if (diferencia <= palaJugador.getAlto()) {
            angulo = -60;
        }

        return angulo;
    }

    private void manejarChoqueSuelo() {
        if (bola.getPosicionEjeY() + bola.getRadius() >= HelpTools.HEIGHT) {
            this.velocidadY = Math.abs(this.velocidadY);
        }
    }

    private void manejarChoqueTecho() {
        if (bola.getPosicionEjeY() - bola.getRadius() <= 0) {
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

    public void reiniciarBolaPuntoIA(){
        this.bola.setPosicionEjeX(200);
        this.bola.setPosicionEjeY(300);
        this.velocidadX = HelpTools.getVelocidadBola() * (Math.sqrt(3) / 2);
        this.velocidadY = HelpTools.getVelocidadBola() * (0.5);
    }

    public void reiniciarBolaPuntoJugador(){
        this.bola.setPosicionEjeX(HelpTools.WIDTH - 200);
        this.bola.setPosicionEjeY(300);
        this.velocidadX = - HelpTools.getVelocidadBola() * (Math.sqrt(3) / 2);
        this.velocidadY = HelpTools.getVelocidadBola() * (0.5);
    }
}
