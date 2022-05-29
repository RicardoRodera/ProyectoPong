package controlador;

import javafx.scene.layout.HBox;
import modelo.Marcador;

public class ControladorMarcador {
    private static int puntosIA;
    private static int puntosJugador;
    private Marcador marcador;

    public ControladorMarcador() {
        this.puntosIA = 0;
        this.puntosJugador = 0;
        this.marcador = new Marcador();
    }

    public int getPuntosIA() {
        return puntosIA;
    }

    public int getPuntosJugador() {
        return puntosJugador;
    }

    public HBox getMarcador() {
        return marcador;
    }

    public void setPuntosIA(int puntosIA) {
        this.puntosIA = puntosIA;
    }

    public void anotarPuntoIA(){
        puntosIA++;
        marcador.anotarPuntoIA(puntosIA);
    }

    public void anotarPuntoJugador(){
        puntosJugador++;
        marcador.anotarPuntoJugador(puntosJugador);
    }

    public void setPuntosJugador(int puntosJugador) {
        this.puntosJugador = puntosJugador;
    }
}
