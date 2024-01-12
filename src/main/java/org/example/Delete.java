package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Delete {
    public static void main(String args[]) throws SQLException, ClassNotFoundException {
        Connection c = null;
        Statement stmt = null;
        String user="postgres";
        String password="postgres";
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/mi base01", user, password);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        try {
            stmt = c.createStatement();
            String sql = "DELETE FROM actores"
                    + "WHERE nombre=Paco";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.setAutoCommit(true);
            c.close();
        } catch (SQLException e) {
            c.rollback();
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }
}
