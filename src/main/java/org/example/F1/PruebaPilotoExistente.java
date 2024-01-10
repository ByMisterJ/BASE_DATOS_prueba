package org.example.F1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class PruebaPilotoExistente {
    public static void main(String args[]) throws SQLException, ClassNotFoundException {
        Connection c = null;
        Statement stmt = null;
        String user="postgres";
        String password="postgres";
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/f12006", user, password);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        try {
            stmt = c.createStatement();
            String sql = "INSERT INTO drivers (driverid, code, forename, surname, dob, nationality, constructorid, url) "
                    + "VALUES (39, 'ALO', 'Hernando', 'Alfonso', '1982-08-30', 'Spanish', 39, 'http://en.wikipedia.org/wiki/Hernando_Alfonso')," +
                    "(40, 'ANA', 'Ana', 'Alfonso', '1982-08-30', 'Spanish', 40, 'http://en.wikipedia.org/wiki/Hernando_Alfonso');";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
        } catch (SQLException e) {
            c.rollback();
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }
}
