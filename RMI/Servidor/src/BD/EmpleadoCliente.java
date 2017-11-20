/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import Common.DtsEmpleadoCliente;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Skynet
 */
public class EmpleadoCliente extends Conexion{
    DtsEmpleadoCliente ob;
    ArrayList<DtsEmpleadoCliente> lista;
    
    ImageIcon imgIcon;
    private int exito;
    private byte[] img;
    private ByteArrayInputStream bais;  

    Object[] titulo = {"Nombre","Apellidos", "Direccion", "No. Cuenta", "NIP", "Saldo", "Foto"};
    Object[] row = new Object[7];
    DefaultTableModel modelo = new DefaultTableModel(null,titulo){
        @Override
        public Class getColumnClass(int indiceColumna) {
            Object stefany = getValueAt(0, indiceColumna);
            if (stefany == null) {
                return Object.class;
            } else {
                return stefany.getClass();
            }
        }
    };

    public EmpleadoCliente() {
    ob=new DtsEmpleadoCliente();        
    lista= new ArrayList<>();
    }
    
    /**
     * Establecer Cliente
     * @param dts objeto de tipo cliente
     * @param img
     * @return 
     */
    public boolean setEmpleadoCliente(DtsEmpleadoCliente dts)
    {
        try {
            sql = "INSERT INTO empleados_cliente"
                    + "(nombre, apellidos, direccion, noCuenta, nip, saldo, fotografia)"
                    + " VALUES (?,?,?,?,?,?,?);";            
            conectarBD();             
            ps = con.prepareStatement(sql);
            
            ps.setString(1, dts.getNombre());
            ps.setString(2, dts.getApellido());
            ps.setString(3, dts.getDireccion());
            ps.setString(4, dts.getNoCuenta());
            ps.setString(5, dts.getNip());
            ps.setDouble(6, dts.getSaldo());   
            
            bais = new ByteArrayInputStream(dts.getImagen());
            
            ps.setBlob(7, bais,dts.getTamImagen());
            exito = ps.executeUpdate();
            cerrarPS();
            
            return exito!=0;
        } catch (SQLException ex) {
           return false;
        }
    }
    /**
     * Actualizar Cliente
     * @param dts
     * @param img
     * @return 
     */
    public boolean updateEmpleadoCliente(DtsEmpleadoCliente dts)
    {
        try {
            sql = "UPDATE empleados_cliente SET"
                    + " nombre=?,apellidos=?,direccion=?,nip=?,saldo=?,fotografia=? "
                    + "WHERE noCuenta=?;";            
            conectarBD();             
            ps = con.prepareStatement(sql);
            
            ps.setString(1, dts.getNombre());
            ps.setString(2, dts.getApellido());
            ps.setString(3, dts.getDireccion());            
            ps.setString(4, dts.getNip());
            ps.setDouble(5, dts.getSaldo());   
            bais = new ByteArrayInputStream(dts.getImagen());
            ps.setBlob(6, bais,dts.getTamImagen());
            ps.setString(7, dts.getNoCuenta());
            
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
    public boolean deleteEmpleadoCliente(DtsEmpleadoCliente dts)
    {
        try {
            sql = "DELETE FROM empleados_cliente WHERE noCuenta=?;";
            conectarBD();            
            ps = con.prepareStatement(sql);
            ps.setString(1, dts.getNoCuenta());
            
            exito=ps.executeUpdate();
            cerrarPS();
            return exito!=0;
        } catch (SQLException ex) {
           return false;
        }
    } 
    public ArrayList<DtsEmpleadoCliente> getEmpleadoClienteBy(String txt)
    {
        ob= new DtsEmpleadoCliente();        
        lista = new ArrayList<>();
        try {
            sql="SELECT nombre, apellidos, direccion, noCuenta, nip, saldo, fotografia "
                    + "FROM empleados_cliente "
                    + "WHERE noCuenta = '"+txt+"';";
            conectarBD();
            st = con.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next())
            {
                ob.setNombre(rs.getString("nombre"));
                ob.setApellido(rs.getString("apellidos"));
                ob.setDireccion(rs.getString("direccion"));
                ob.setNoCuenta(rs.getString("noCuenta"));
                ob.setNip(rs.getString("nip"));
                ob.setSaldo(rs.getDouble("saldo"));
                //obtenemos la foto
                img = rs.getBytes("fotografia");
                bais = new ByteArrayInputStream(img);
                bais.read(img);
                //tamño de la img
                ob.setTamImagen(img.length);
                //bytes de imagen
                //ob.setImagen(bais);
                //agrefamos a la lista
                lista.add(ob);
            }
            cerrarRS();
            return lista;
        } catch (SQLException | IOException ex) {
            cerrarRS();
           return lista;
        }        
    }
    public boolean getLoginClinete(DtsEmpleadoCliente dts)
    {
        try {
            sql = "SELECT nombre FROM "
                    + "empleados_cliente WHERE"
                    + " noCuenta='"+dts.getNoCuenta()+"' and nip='"+dts.getNip()+"'";
            st= con.createStatement();
            rs = st.executeQuery(sql);
            return rs.next();
        } catch (SQLException ex) {
            return false;
        }
    }
    
    
    //GET ALL CLIENTES
    public Object getEmpleadoCliente()
    {      
        DtsEmpleadoCliente ob;
        ArrayList<DtsEmpleadoCliente> lista = new ArrayList<>();
        try {
            sql="SELECT nombre, apellidos, direccion, noCuenta, nip, saldo, fotografia "
                    + "FROM empleados_cliente";                    
            conectarBD();
            st = con.createStatement();
            rs=st.executeQuery(sql);
            
            while(rs.next())
            {
                ob= new DtsEmpleadoCliente();
                //row[0]=rs.getString("nombre");
                ob.setNombre(rs.getString("nombre"));
                //row[1]=rs.getString("apellidos");
                ob.setApellido(rs.getString("apellidos"));
                //row[2]=rs.getString("direccion");
                ob.setDireccion(rs.getString("direccion"));
                //row[3]=rs.getString("noCuenta");
                ob.setNoCuenta(rs.getString("noCuenta"));
                //row[4]=rs.getString("nip");
                ob.setNip(rs.getString("nip"));
                //row[5]=rs.getDouble("saldo");
                ob.setSaldo(rs.getDouble("saldo"));
                //obtenemos la foto                
                //imgIcon = new ImageIcon(rs.getBytes("fotografia"));
                //row[6]=rs.getBytes("fotografia");
                ob.setImagen(rs.getBytes("fotografia"));
                //row[6]=new ImageIcon(imgIcon.getImage().getScaledInstance(100, 110, 0));                
               //modelo.addRow(row);
                lista.add(ob);
            }
            cerrarRS();
           
            return lista;
        } catch (SQLException ex) 
        {
           return lista;
        }        
    } 

}
