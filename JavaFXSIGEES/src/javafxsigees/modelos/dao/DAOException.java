/*
 * Autor: Eduardo García Díaz
 * Fecha de creación: 16/05/2023
 * Descripción: Excepcion personalizada para ser lanzada en caso de situaciones de excepcion
 * en las operaciones realizadas por los DAO (Data Access Object).
 */

package javafxsigees.modelos.dao;

import javafxsigees.utils.Codigos;

public class DAOException extends Exception {
    private final Codigos codigo;
    
    public DAOException(String mensaje, Codigos codigo) {
        super(mensaje);
        this.codigo = codigo;
    }
    
    public Codigos getCodigo() {
        return codigo;
    }
}
