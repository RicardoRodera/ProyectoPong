package modelo;

import java.sql.Date;

public class DatosPartida {

    private String jugador;
    private Date fecha;
    private String nivel;
    private int puntosEncajados;
    private int duracion;
    private int puntuacion;

    public DatosPartida(String jugador, Date fecha, String nivel, int puntosEncajados, int duracion, int puntuacion) {
        this.jugador = jugador;
        this.fecha = fecha;
        this.nivel = nivel;
        this.puntosEncajados = puntosEncajados;
        this.duracion = duracion;
        this.puntuacion = puntuacion;
    }

    public String getJugador() {
        return jugador;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getNivel() {
        return nivel;
    }

    public int getPuntosEncajados() {
        return puntosEncajados;
    }

    public int getDuracion() {
        return duracion;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DatosPartida{");
        sb.append("jugador='").append(jugador).append('\'');
        sb.append(", fecha=").append(fecha);
        sb.append(", nivel='").append(nivel).append('\'');
        sb.append(", puntosEncajados=").append(puntosEncajados);
        sb.append(", duracion=").append(duracion);
        sb.append(", puntuacion=").append(puntuacion);
        sb.append('}');
        return sb.toString();
    }
}
