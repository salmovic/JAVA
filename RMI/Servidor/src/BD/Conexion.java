package BD;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Skynet
 */

public class Conexion {
    
    //nombre de la base de datos
    private final String db="cajero_automatico";    
    //ruta del servidor de la bd
    private final String url = "jdbc:mysql://127.0.0.1/" +db;
    //usuario de gbd
    private final String usr = "root";
    //contraseña gbd
    private final String psswd="";

    //Me permite establecer la conexion
    public Connection con = conectar();
    //preparamos la instruccion sql para la consulta    
    public PreparedStatement ps; 
    //Variable que me permite realizar la instruccion sql
    public String sql;
    //variable objeto que nos permite enviar una sentencia sql
    public Statement st;
    //Permite obtener los datos de la bd
    public ResultSet rs;
    
    public Conexion(){}
    //metodo que permite establecer la conexion    
    public Connection conectar()
    {
        Connection link = null;        
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            link = DriverManager.getConnection(this.url, this.usr,this.psswd);            
        } catch (ClassNotFoundException | SQLException e) 
        {
            System.out.println(e.getMessage());
        }
        return link;
    } 
    //establece una conexion con el servidor
    void conectarBD()
    {
        con = conectar();
    }
    void cerrarPS()
    {
        try {
            if (ps!=null)ps.close();        
            if (con!=null)con.close();
        } catch (SQLException ex){}
    }
    void cerrarRS()
    {
        try {
            if(st!=null)st.close();
            if(rs!=null) rs.close();
            if(con!=null) con.close();
        } catch (SQLException ex) {}
    }
}
