/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DBFerreteria;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import Excepciones.*;

public class ConexionBD {
    Connection conn;
    Statement stmt;
    
    static ConexionBD instancia = null;
    
    private ConexionBD() throws ErrorConexionBD {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ventasdb","root","mysql");
            stmt = conn.createStatement();
        }
        catch(SQLException e) {
            throw new ErrorConexionBD();
        }
    }
    
    public Connection getConnection() {
        return conn;
    }
    
    public Statement getStatement() {
        return stmt;
    }
    
    public static void crearConexion() throws ErrorConexionBD {
        if (instancia == null) {
            instancia = new ConexionBD();
        }
    }
    
    public static ConexionBD instancia() {
        return instancia;
    }
    
    public static void desconectar() {
        if (instancia != null) {
            try {
                instancia.stmt.execute("shutdown");
                instancia.stmt.close();
                instancia.conn.close();
                instancia = null;
            }
            catch(SQLException e) {
            }
        }
    }
}