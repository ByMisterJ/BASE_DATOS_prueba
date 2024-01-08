package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {
    public static void main(String[] args) {
        String sentenciaSQL = "Select * FROM actores";
        String usuario="postgres";
        String contraseña="postgres";
        try(Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/mi base 01",usuario,contraseña);
            PreparedStatement sentencia= con.prepareStatement(sentenciaSQL)){
            ResultSet resultados = sentencia.executeQuery();

            while (resultados.next()){
                Integer id= resultados.getInt("id");
                String nombre= resultados.getString("nombre");
                Integer edad= resultados.getInt("edad");
                System.out.println("id: "+id+" nombre: " +nombre+" edad: " +edad);
            }

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}