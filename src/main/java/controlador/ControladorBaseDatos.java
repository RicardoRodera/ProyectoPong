package controlador;

import modelo.DatosPartida;
import modelo.DatosRanking;

import java.sql.*;

/**
 * Esta clase controla la base de datos
 */
public class ControladorBaseDatos {
    private final String RUTA = "jdbc:mysql://localhost/proyecto_pong";
    private final String USUARIO = "pong";
    private final String PASSWORD = "1551";
    private Connection conexion;
    public ControladorBaseDatos() throws SQLException, ClassNotFoundException {
        conexion();
    }
//Prueba
    private void conexion() throws ClassNotFoundException, SQLException {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(RUTA
                    ,USUARIO,PASSWORD);
                    System.out.println("Exito en la conexion");
                    this.conexion = conexion;



    }

    public DatosRanking leerDatosRanking(String nivelParametro) throws SQLException {
        Statement consulta = conexion.createStatement();
        String sentencia = generarConsulta(nivelParametro);
        ResultSet resultado = consulta.executeQuery(sentencia);
        DatosRanking datosRanking = new DatosRanking();
        int contador = 0;
        while(resultado.next()){
            Date fechaYHora = resultado.getDate("fechayhora");
            String nivel = resultado.getString("nivel");
            int puntosEncajados = resultado.getInt("puntosencajados");
            int duracion = resultado.getInt("duracion");
            int puntuacion = resultado.getInt("puntuacion");

            DatosPartida partida = new DatosPartida(fechaYHora, nivel, puntosEncajados, duracion, puntuacion);

            datosRanking.anadirPartida(partida);
            contador++;
            if(contador == 5){
                break;
            }
        }
        resultado.close();
        consulta.close();
        return datosRanking;


    }

    private String generarConsulta(String nivelParametro){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ");

        switch (nivelParametro){
            case "EASY": sb.append("rankingeasy");
                break;
            case "MEDIUM": sb.append("rankingmedium");
                break;
            case "HARD": sb.append("rankinghard");
                break;
        }


        return sb.toString();
    }

    public void grabarPartida( String nivel, int puntosEncajados, int duracion, int puntuacion) throws SQLException {
        Statement consulta = conexion.createStatement();
        String insertar = generarInsert( nivel,puntosEncajados, duracion, puntuacion);
        System.out.println(insertar);
        consulta.executeUpdate(insertar);
        consulta.close();
    }

    private String generarInsert(String nivel, int puntosEncajados, int duracion, int puntuacion){
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO partida (nivel, puntosencajados, duracion, puntuacion) VALUES ( '");
        sb.append(nivel);
        sb.append("', ");
        sb.append(puntosEncajados);
        sb.append(", ");
        sb.append(duracion);
        sb.append(", ");
        sb.append(puntuacion);
        sb.append(");");
        return sb.toString();


    }

}
