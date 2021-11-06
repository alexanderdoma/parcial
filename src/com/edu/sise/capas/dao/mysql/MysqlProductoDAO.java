/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edu.sise.capas.dao.mysql;

import com.edu.sise.capas.dao.DAOException;
import com.edu.sise.capas.dao.IProductoDAO;
import com.edu.sise.capas.entity.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alexander
 */
public class MysqlProductoDAO implements IProductoDAO{
    
    final String GETALL = "SELECT * FROM productos";
    final String INSERT = "INSERT INTO productos VALUES(nombre, descripcion, modelo, stock, precio, estado,) VALUES(?,?,?,?,?,?)";
    final String UPDATE = "UPDATE productos" + "SET nombre=?, descripcion=?, modelo=?, stock=?, precio=?, estado=?" + "WHERE id_producto=?";
    final String DELETE = "DELETE FROM productos WHERE id_producto = ?";
    
    private Connection cn;

    public MysqlProductoDAO(Connection cn) {
        this.cn = cn;
    }
    
    @Override
    public void insertar(Producto o) throws DAOException {
        PreparedStatement ps = null;
        try {
            ps.setString(0, o.getNombre());
            ps.setString(1, o.getDescripcion());
            ps.setString(2, o.getModelo());
            ps.setInt(3, o.getStock());
            ps.setDouble(4, o.getPrecio());
            ps.setBoolean(5, o.isEstado());
            ps.execute(INSERT);
        } catch (SQLException ex) {
            Logger.getLogger(MysqlProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void eliminar(Producto o) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar(Producto o) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Producto obtenerxId(Integer id) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Producto> obtenerTodos() throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Producto> lista = new ArrayList<>();
        try {
            ps = cn.prepareStatement(GETALL);
            rs = ps.executeQuery();
            while(rs.next()){
                lista.add(getRs(rs));
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en sql",ex);
        }finally{
            if(rs!=null) try {
                rs.close();
                ps.close();
            } catch (SQLException ex) {
                throw new DAOException("Error en sql",ex);
            }
        }
        return lista;
    }
    private Producto getRs(ResultSet rs) throws SQLException{
        return new Producto(
            rs.getInt("id_producto"),
            rs.getString("nombre"),
            rs.getString("descripcion"),
            rs.getString("modelo"),
            rs.getInt("stock"),
            rs.getDouble("precio"),
            rs.getBoolean("estado")
        );
    }
}
