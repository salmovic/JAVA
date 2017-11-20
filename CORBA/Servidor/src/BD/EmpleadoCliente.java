/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import Acciones.Cliente;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Skynet
 */
public class EmpleadoCliente extends Conexion{
    private int exito; //confirmar operacion de la bd
    private byte[] img; //alamena los bytes de la imagen
    private ByteArrayInputStream bais; //convierte un arreglo de bytes para alojar a la bd
    /**
     * Establecer Cliente
     * @param dts objeto de tipo cliente
     * @return 
     */
    public boolean setEmpleadoCliente(Cliente dts)
    {
        try {
            sql = "INSERT INTO empleados_cliente"
                    + "(nombre, apellidos, direccion, noCuenta, nip, saldo, fotografia)"
                    + " VALUES (?,?,?,?,?,?,?);";            
            conectarBD();             
            ps = con.prepareStatement(sql);
            
            ps.setString(1, dts.nombre);
            ps.setString(2, dts.apellidos);
            ps.setString(3, dts.direccion);
            ps.setString(4, dts.noCuenta);
            ps.setString(5, dts.nip);
            ps.setDouble(6, dts.saldo);
            bais = new ByteArrayInputStream(dts.foto);
            ps.setBlob(7, bais,dts.lenFoto);
            exito = ps.executeUpdate();
            cerrarPS();            
            return exito!=0;
        } catch (SQLException ex) {
            System.out.println(ex);
           return false;
        }
    }
    /**
     * Actualizar Cliente
     * @param dts
     * @param img
     * @return 
     */
    public boolean updateEmpleadoCliente(Cliente dts)
    {
        try {
            sql = "UPDATE empleados_cliente SET"
                    + " nombre=?,apellidos=?,direccion=?,nip=?,saldo=?,fotografia=? "
                    + "WHERE noCuenta=?;";            
            conectarBD();             
            ps = con.prepareStatement(sql);
            
            ps.setString(1, dts.nombre);
            ps.setString(2, dts.apellidos);
            ps.setString(3, dts.direccion);            
            ps.setString(4, dts.nip);
            ps.setDouble(5, dts.saldo);   
            bais = new ByteArrayInputStream(dts.foto);
            ps.setBlob(6, bais,dts.lenFoto);
            ps.setString(7, dts.noCuenta);
            
            exito = ps.executeUpdate();            
            cerrarPS();            
            return exito!=0;
        } catch (SQLException ex) {
           return false;
        }
    }
    /**
     * Eliminar Cliente
     * @param dts
     * @return 
     */
    public boolean deleteEmpleadoCliente(String dts)
    {
        try {
            sql = "DELETE FROM empleados_cliente WHERE noCuenta=?;";
            conectarBD();            
            ps = con.prepareStatement(sql);
            ps.setString(1, dts);
            
            exito=ps.executeUpdate();
            cerrarPS();
            return exito!=0;
        } catch (SQLException ex) {
           return false;
        }
    } 
    public Cliente[] getEmpleadoClienteBy(String txt)
    {
        Cliente[] cl;
        int len=0,cont = 0;
        try {
             sql="SELECT count(*)as len FROM empleados_cliente WHERE noCuenta = '"+txt+"';";
            conectarBD();
            st = con.createStatement();
            rs=st.executeQuery(sql);
            if(rs.next()) len=rs.getInt("len");  
            
            cl=new Cliente[len];
                    
            sql="SELECT nombre, apellidos, direccion, noCuenta, nip, saldo, fotografia "
                    + "FROM empleados_cliente "
                    + "WHERE noCuenta = '"+txt+"';";
            
            st = con.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next())
            {
                //obtenemos la foto
                img = rs.getBytes("fotografia");
                bais = new ByteArrayInputStream(img);
                bais.read(img);        
                cl[cont] = new Cliente(rs.getString("nombre"), rs.getString("apellidos"),
                        rs.getString("direccion"), rs.getString("noCuenta"),
                        rs.getString("nip"), rs.getDouble("saldo"),
                        img, 0);
                cont++;
            }
            cerrarRS();
            return cl;
        } catch (SQLException | IOException ex) {
            cerrarRS();
           return null;
        }        
    }
    //LOGEO DEL USUARIO
    public boolean getLoginClinete(String noc, String nip)
    {
        try {
            sql = "SELECT nombre FROM "
                    + "empleados_cliente WHERE"
                    + " noCuenta='"+noc+"' and nip='"+nip+"'";
            st= con.createStatement();
            rs = st.executeQuery(sql);
            return rs.next();
        } catch (SQLException ex) {
            return false;
        }
    }
    //GET ALL CLIENTES
    public Cliente[] getEmpleadoCliente()
    {      
        Cliente[] cl;
        int len=0,cont = 0;
        try {
             sql="SELECT count(*)as len FROM empleados_cliente;";
            conectarBD();
            st = con.createStatement();
            rs=st.executeQuery(sql);
            if(rs.next()) len=rs.getInt("len");  
            
            cl=new Cliente[len];
                    
            sql="SELECT nombre, apellidos, direccion, noCuenta, nip, saldo, fotografia "
                    + "FROM empleados_cliente";                    
            
            st = con.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next())
            {
                //obtenemos la foto
                img = rs.getBytes("fotografia");
                bais = new ByteArrayInputStream(img);
                bais.read(img);        
                cl[cont] = new Cliente(rs.getString("nombre"), rs.getString("apellidos"),
                        rs.getString("direccion"), rs.getString("noCuenta"),
                        rs.getString("nip"), rs.getDouble("saldo"),
                        img, 0);
                cont++;
            }
            cerrarRS();
            return cl;
        } catch (SQLException ex) 
        {
           return null;
        } catch (IOException ex) {
            return null;
        }        
    } 
    public boolean getLoginAdm(String usr,String pass)
    {
        try {
            sql = "SELECT * FROM `empleados_servidor` WHERE nombre_usuario='"+usr+"' and contrasena='"+pass+"'";
            conectarBD();
            st = con.createStatement();
            rs = st.executeQuery(sql);
            if(rs.next())
            {
                cerrarRS();
                return true;
            }
            return false;
        } catch (SQLException ex) {
            return false;
        }
    }

}
