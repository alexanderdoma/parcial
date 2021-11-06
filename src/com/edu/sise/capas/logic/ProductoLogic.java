/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edu.sise.capas.logic;

import com.edu.sise.capas.dao.IProductoDAO;
import com.edu.sise.capas.dao.mysql.MySqlDAOManager;
import com.edu.sise.capas.entity.Producto;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alexander
 */
public class ProductoLogic {
    MySqlDAOManager factory = MySqlDAOManager.getInstancia();
    IProductoDAO dao = factory.getProductoDAO();
    
    private DefaultTableModel obtenerTodos() throws Exception{
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Código");
        modelo.addColumn("Nombre");
        modelo.addColumn("Descripción");
        modelo.addColumn("Modelo");
        modelo.addColumn("Stock");
        modelo.addColumn("Precio");
        modelo.addColumn("Estado");
        List<Producto> lista = dao.obtenerTodos();
        for(Producto obj : lista){
            Object data[] = {
                obj.getId_producto(),
                obj.getNombre(),
                obj.getDescripcion(),
                obj.getModelo(),
                obj.getStock(),
                obj.getPrecio(),
                obj.isEstado()
            };
            modelo.addRow(data);
        }
        return modelo;
    }
    public void imprimirTB(JTable jtable) throws Exception{
        jtable.setModel(obtenerTodos());
    }
}
