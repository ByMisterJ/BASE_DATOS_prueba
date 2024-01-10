package org.example.F1;

import java.sql.*;

public class PilotosEspanyoles {
    public static void main(String[] args) {
//        variables con los datos de conexion para la base de datos
        String urlConexion = "jdbc:postgresql://localhost:5432/F12006";
        String usuario = "postgres";
        String password = "postgres";

//        driverid, code, forename, surname, dob, nationality, constructorid, url
        String sentenciaSQL = "Select * FROM drivers";

//        conexion a la base de datos
        try (Connection con = DriverManager.getConnection(urlConexion, usuario, password);
             PreparedStatement sentencia= con.prepareStatement(sentenciaSQL)){
            ResultSet resultados = sentencia.executeQuery();

            while (resultados.next()){
                String nation = resultados.getString("nationality");
//                filtrado de los pilotos espanyoles por valor nationality
                if (nation.equalsIgnoreCase("Spanish")) {

                    Integer driverid = resultados.getInt("driverid");
                    String code = resultados.getString("code");
                    String forename = resultados.getString("forename");
                    String surname = resultados.getString("surname");
                    String dob = resultados.getString("dob");
                    String nationality = resultados.getString("nationality");
                    Integer constructorid = resultados.getInt("constructorid");
                    String url = resultados.getString("url");

                    System.out.println("driverid: "+driverid+" code: "+code+" forename: "+forename+" surname: "+surname+" dob: "+dob+" nationality: "+nationality+" constructorid: "+constructorid+" url: "+url);
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
        }
    }
}
