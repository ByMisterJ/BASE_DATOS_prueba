package org.example.SQLite;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class OperacionesCRUDPilotos {
    public void crearPiloto(Connection con) throws SQLException {
        Connection c = null;
        Statement stmt;

        try {
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String sql = "INSERT INTO actores (id,nombre,edad) "
                    + "VALUES (2, 'Paco', 32);";
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
    }
}
