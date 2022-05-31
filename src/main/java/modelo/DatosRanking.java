package modelo;

import java.util.*;

public class DatosRanking {

    private List<DatosPartida> lista;

    public DatosRanking() {
        this.lista = new ArrayList<>();
    }

    public List<DatosPartida> getLista() {
        return lista;
    }

    public void anadirPartida(DatosPartida datosPartida){

        lista.add(datosPartida);

    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("\tFECHA  \t  \t  NIVEL\t PUNTOS \tTIEMPO\t PUNTUACION FINAL\n");
        for(DatosPartida datosPartida: lista){
            sb.append(datosPartida.toString());
        }

        return sb.toString();
    }
}
