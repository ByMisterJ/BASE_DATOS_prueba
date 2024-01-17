package org.example.SQLite;

import java.sql.*;
import java.util.*;

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

    public void BorrarPiloto(Connection con, Piloto piloto) throws SQLException {
        String query = "DELETE FROM drivers WHERE driverid=?";

        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, piloto.getDriverid());

            int rowsDeleted = pstmt.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Registro eliminado exitosamente");
            } else {
                System.out.println("No se encontró ningún registro para eliminar");
            }
        }
    }

    public void MostrarClasificacionPiloto(Connection con) throws SQLException {
        List<Resultado> clasificacion = new ArrayList<>();

        // Obtener la clasificación de la base de datos
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
                "SELECT SUM(r.points) AS total_points, CONCAT(d.forename, ' ', d.surname) AS nombre_completo , co.name\n" +
                "FROM drivers AS d\n" +
                "JOIN constructors AS co ON co.constructorid = d.constructorid \n" +
                "JOIN results AS r ON d.driverid = r.driverid\n" +
                "GROUP BY nombre_completo , co.name\n" +
                "ORDER BY total_points DESC;");

        // Mostrar los resultados por pantalla
        System.out.println("Clasificación de Pilotos:");
        System.out.printf("%-30s %-20s %-20s%n", "Nombre Completo", "Equipo", "Puntos");
        System.out.println("----------------------------------------------------------");

        while (rs.next()) {
            String nombreCompleto = rs.getString("nombre_completo");
            String nombreEquipo = rs.getString("name");
            int totalPuntos = rs.getInt("total_points");

            System.out.printf("%-30s %-20s %-20d%n", nombreCompleto, nombreEquipo, totalPuntos);
        }

        // Cerrar recursos
        rs.close();
        stmt.close();
    }
}
