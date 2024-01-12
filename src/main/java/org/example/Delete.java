package org.example;

import java.sql.*;

public class Delete {
    public static void main(String args[]) throws SQLException, ClassNotFoundException {
        Connection c = null;
        String user="postgres";
        String password="postgres";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/mi base01", user, password);
            PreparedStatement st = connection.prepareStatement("DELETE FROM actores WHERE nombre = ?");
            st.setString(1,"Paco");
            st.executeUpdate();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        try {
            PreparedStatement st = c.prepareStatement("DELETE FROM Table WHERE nombre = Paco ;");
            st.executeUpdate();

            st.close();
            c.commit();
            c.setAutoCommit(true);
            c.close();
        } catch (SQLException e) {

            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }
}
