/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edu.sise.capas.dao.mysql;

import com.edu.sise.capas.dao.IDAOManager;
import com.edu.sise.capas.dao.IProductoDAO;
import com.edu.sise.capas.datos.Conexion;
import java.sql.Connection;

/**
 *
 * @author Alexander
 */
public class MySqlDAOManager implements IDAOManager{

    //Singleton parte-1
    private static final MySqlDAOManager instancia = new MySqlDAOManager();
    private Connection cn;
    private MySqlDAOManager(){
        cn = new Conexion().obtenerConexion();
    }
    //Singleton parte-2
    public static MySqlDAOManager getInstancia(){
        
        return instancia;
    }
    
    //Factory
    private IProductoDAO productoDao = null;
    
    
    @Override
    public IProductoDAO getProductoDAO() {
        if(productoDao == null){
            productoDao = new MysqlProductoDAO(cn);
        }
        return productoDao;
    }
    
}
