package org.example.F1;

import java.sql.*;

public class Procedimiento01 {
    public static void main(String[] args) {
        String urlConexion = "jdbc:postgresql://localhost:5432/F12006";
        String usuario = "postgres";
        String password = "postgres";

//      driverid, code, forename, surname, dob, nationality, constructorid, url
        String sentenciaSQL = "Select * FROM drivers";

        try (Connection con = DriverManager.getConnection(urlConexion, usuario, password);
            PreparedStatement procedimiento= con.prepareCall("{CALL get_results_by_driver(?)}")){
            procedimiento.setString(1,"ALO");
            procedimiento.execute();
            ResultSet rs=procedimiento.getResultSet();

            while (rs.next()){
                Integer round=rs.getInt("round");
                String circuit=rs.getString("circuit") ;
                Integer result=rs.getInt("result");
                Integer points=rs.getInt("points");
                Date date=new Date(rs.getDate("date").getTime());
                System.out.println("round: "+round+" circuit: "+circuit+" result: "+result+" points: "+points+" date: "+date);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
        }
    }
}
