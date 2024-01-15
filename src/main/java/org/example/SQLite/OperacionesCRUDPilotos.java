package org.example.SQLite;

import java.sql.*;
import java.util.ArrayList;

public class OperacionesCRUDPilotos {

    public static void main(String[] args) {

    }
    public void crearPiloto(Connection con, Piloto piloto) throws SQLException {
        Statement stmt;

        try {
            con.setAutoCommit(false);
            stmt = con.createStatement();
            String sql = "INSERT INTO drivers (id,nombre,edad) "
                    + "VALUES (piloto);";
            stmt.executeUpdate(sql);

            stmt.close();
            con.commit();
            con.setAutoCommit(true);
            con.close();
        } catch (SQLException e) {
            con.rollback();
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }

    public Piloto LeerPiloto(Connection con, int id) throws SQLException {
        Piloto piloto= null;

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM drivers WHERE driverid = id;");
            piloto = (Piloto) rs;
            stmt.close();
            con.close();
        }catch(Exception e){
            System.out.println("no se ha encontrado a nadie con ese id");
            }
        return piloto;
    }

    public ArrayList<Piloto> LeerPilotos(Connection con) throws SQLException {
        ArrayList<Piloto> ListaPilotos = new ArrayList<>();

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM drivers ORDER BY driverid;");

        // Iterar sobre los resultados y agregar los pilotos a la lista
        while (rs.next()) {
            int driverId = rs.getInt("driverid");
            String code = rs.getString("code");
            String forename = rs.getString("forename");
            String surname = rs.getString("surname");
            String dob = rs.getString("dob");
            String nationality = rs.getString("nationality");
            String url = rs.getString("url");

            Piloto piloto = new Piloto(driverId, code, forename, surname, dob, nationality, url);
            ListaPilotos.add(piloto);
        }

        // Cerrar recursos
        rs.close();
        stmt.close();
        con.close();

        return ListaPilotos;
    }
}
