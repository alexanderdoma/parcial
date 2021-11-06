/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.edu.sise.capas.dao;

import java.util.List;

/**
 *
 * @author Alexander
 */
public interface GenericDAO<T, K>{

    void insertar(T o) throws DAOException;
    void eliminar(T o)throws DAOException;
    void modificar(T o)throws DAOException;
    T obtenerxId(K id)throws DAOException;
    List<T> obtenerTodos()throws DAOException;
}
