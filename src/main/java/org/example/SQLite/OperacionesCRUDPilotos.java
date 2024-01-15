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

        try {
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
        }catch(Exception e){
            System.out.println("no se ha encontrado a nadie con ese id");
        }

        return ListaPilotos;
    }

    public void ActualizarPiloto(Connection con, Piloto piloto) throws SQLException {
        String query = "UPDATE drivers SET code=?, forename=?, surname=?, dob=?, nationality=?, url=? WHERE driverid=?";

        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, piloto.getCode());
            pstmt.setString(2, piloto.getForename());
            pstmt.setString(3, piloto.getSurname());
            pstmt.setString(4, piloto.getDob());
            pstmt.setString(5, piloto.getNationality());
            pstmt.setString(6, piloto.getUrl());
            pstmt.setInt(7, piloto.getDriverid());

            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Registro actualizado exitosamente");
            } else {
                System.out.println("No se encontró ningún registro para actualizar");
            }
        }
    }
}
