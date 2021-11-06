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

/**
 *
 * @author Alexander
 */
public class MysqlProductoDAO implements IProductoDAO{
    
    final String GETALL = "SELECT * FROM productos";
    final String INSERT = "INSERT INTO productos(nombre, descripcion, modelo, stock, precio, estado) VALUES(?,?,?,?,?,?)";
    final String UPDATE = "UPDATE productos SET nombre=?, descripcion=?, modelo=?, stock=?, precio=?, estado=? WHERE id_producto=?";
    final String DELETE = "DELETE FROM productos WHERE id_producto = ?";
    final String QUERY = "SELECT * FROM productos WHERE nombre LIKE ? || descripcion LIKE ?";
    
    private Connection cn;

    public MysqlProductoDAO(Connection cn) {
        this.cn = cn;
    }
    
    @Override
    public void insertar(Producto o) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            ps = cn.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
            int i = 1;
            ps.setString(i++, o.getNombre());
            ps.setString(i++, o.getDescripcion());
            ps.setString(i++, o.getModelo());
            ps.setInt(i++, o.getStock());
            ps.setDouble(i++, o.getPrecio());
            ps.setInt(i++, o.getEstado());
            
            if(ps.executeUpdate()==0)
                throw new DAOException("¡No se pudo registrar el registro!");
            rs = ps.getGeneratedKeys();
            if(rs.next()){
                o.setId_producto(rs.getInt(1));
                
            }else{
                throw new DAOException("No se puede generar el id del producto");
            }
        } catch (SQLException ex) {
            throw new DAOException(ex);
            
        }finally{
            
            try {
                if(ps!=null)ps.close();
                if(rs!=null) rs.close();
            } catch (SQLException ex) {
                throw new DAOException("Error al finalizar",ex);
            }
        }
    }

    @Override
    public void eliminar(Producto o) throws DAOException {
        PreparedStatement ps = null;
        
        try {
            ps = cn.prepareStatement(DELETE);
            ps.setInt(1, o.getId_producto());
            ps.execute();
        } catch (SQLException ex) {
            throw new DAOException("Error al eliminar: ", ex);
        }
    }

    @Override
    public void modificar(Producto o) throws DAOException {
        PreparedStatement ps = null;
        try {
            ps = cn.prepareStatement(UPDATE);
            int i = 1;
            ps.setString(i++, o.getNombre());
            ps.setString(i++, o.getDescripcion());
            ps.setString(i++, o.getModelo());
            ps.setInt(i++, o.getStock());
            ps.setDouble(i++, o.getPrecio());
            ps.setInt(i++, o.getEstado());
            ps.setInt(i++, o.getId_producto());
                    
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new DAOException(ex);
        }
        
    }

    @Override
    public Producto obtenerxId(Integer id) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //Metodo para obtener un lista
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
             try {
                if(rs!=null)rs.close();
                if(ps!=null)ps.close();
            } catch (SQLException ex) {
                throw new DAOException("Error en sql",ex);
            }
        }
        return lista;
    }
    
    //Este metodo devuelve un objeto producto, le asignamos un objeto resulset y le asignamos los datos a los parametros de nuestro constructor Producto()
    private Producto getRs(ResultSet rs) throws SQLException{
        return new Producto(
            rs.getInt("id_producto"),
            rs.getString("nombre"),
            rs.getString("descripcion"),
            rs.getString("modelo"),
            rs.getInt("stock"),
            rs.getDouble("precio"),
            rs.getInt("estado")
        );
    }

    @Override
    public List<Producto> buscar(String busqueda) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Producto> lista = new ArrayList<>();
        try {
            ps = cn.prepareStatement(QUERY);
            String consulta = "%" + busqueda + "%";
            ps.setString(1, consulta);
            ps.setString(2, consulta);
            rs = ps.executeQuery();
            while(rs.next()){
                lista.add(getRs(rs));
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en sql qui"+ex);
        }finally{
             try {
                if(rs!=null)rs.close();
                if(ps!=null)ps.close();
            } catch (SQLException ex) {
                throw new DAOException("Error en sql",ex);
            }
        }
        return lista;
    }
}
