package modelo;

import java.sql.Date;

public class DatosPartida {


    private Date fecha;
    private String nivel;
    private int puntosEncajados;
    private int duracion;
    private int puntuacion;

    public DatosPartida( Date fecha, String nivel, int puntosEncajados, int duracion, int puntuacion) {

        this.fecha = fecha;
        this.nivel = nivel;
        this.puntosEncajados = puntosEncajados;
        this.duracion = duracion;
        this.puntuacion = puntuacion;
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
        final StringBuilder sb = new StringBuilder();

        sb.append(fecha);
        sb.append("\t\t ");
        sb.append(nivel.toUpperCase());
        sb.append("\t\t");
        sb.append(puntosEncajados);
        sb.append("\t\t  ");
        sb.append(duracion);
        sb.append("\t\t\t");
        sb.append(puntuacion);
        sb.append("\n");

        return sb.toString();
    }
}
