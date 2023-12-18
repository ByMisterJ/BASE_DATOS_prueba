package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {
    public static void main(String[] args) {
        String sentenciaSQL = "Select * FROM personas";
        try(Connection con = DriverManager.getConnection("jdbc:postgresql://localhost");
            PreparedStatement sentencia= con.prepareStatement(sentenciaSQL)){
            ResultSet resultados = sentencia.executeQuery();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}