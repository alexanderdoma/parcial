/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edu.sise.capas.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Alexander
 */
public class Conexion {
    Connection cn = null;
    private static String url = "jdbc:mysql://localhost:3306/EXAMEN_PARCIAL";
    private static String user = "root";
    private static String pass = "";
    private static String driver = "com.mysql.cj.jdbc.Driver";
    
    public Connection obtenerConexion(){
        try {
            Class.forName(driver);
            try {
                cn = DriverManager.getConnection(url, user, pass);
            } catch (SQLException ex) {
                System.out.println("Error al obtener conexión: "+ex);
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Error de clase: "+ex);
        }
        return cn;
    }
}
