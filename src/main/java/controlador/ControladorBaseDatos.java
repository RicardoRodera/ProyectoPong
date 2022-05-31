package controlador;

import modelo.DatosPartida;
import modelo.DatosRanking;

import java.sql.*;

public class ControladorBaseDatos {
    private final String RUTA = "jdbc:mysql://localhost/proyecto_pong";
    private final String USUARIO = "pong";
    private final String PASSWORD = "1551";
    private Connection conexion;
    public ControladorBaseDatos() {
        this.conexion = conexion();
    }

    private Connection conexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(RUTA
                    ,USUARIO,PASSWORD);
                    System.out.println("Exito en la conexion");
                    return conexion;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

            return null;
    }

    public void leerDatosRankingEasy() throws SQLException {
        Statement consulta = conexion.createStatement();
        ResultSet resultado = consulta.executeQuery("SELECT * FROM rankingeasy");
        DatosRanking datosRanking = new DatosRanking();
        while(resultado.next()){
            String jugador = resultado.getString("jugador");
            Date fechaYHora = resultado.getDate("fechayhora");
            String nivel = resultado.getString("nivel");
            int puntosEncajados = resultado.getInt("puntosencajados");
            int duracion = resultado.getInt("duracion");
            int puntuacion = resultado.getInt("puntuacion");

            DatosPartida partida = new DatosPartida(jugador, fechaYHora, nivel, puntosEncajados, duracion, puntuacion);

            datosRanking.anadirPartida(partida);
        }
        System.out.println( datosRanking.toString());
        resultado.close();
        consulta.close();

    }

    public void grabarPartida(String jugador, Date fechaYHora, String nivel, int puntosEncajados, int duracion, int puntuacion) throws SQLException {
        Statement consulta = conexion.createStatement();
        String insertar ="INSERT INTO partida VALUES ( " + jugador + fechaYHora  + nivel + puntosEncajados + duracion + puntuacion + " )";
        consulta.executeUpdate(insertar);
    }

}
