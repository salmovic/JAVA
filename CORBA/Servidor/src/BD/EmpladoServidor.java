/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import DatosServer.DtsEmpleadoServer;
import java.io.ByteArrayInputStream;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Skynet
 */
public class EmpladoServidor extends Conexion {

    int exito;

    ImageIcon imgIcon;
    ByteArrayInputStream baisImagen;
    //probando

    Object[] titulo = {"ID", "Nombre", "Apellidos", "Direccion", "Telefono", "Usuario", "Contraseña", "Foto"};
    Object[] row = new Object[8];
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

    /**
     * Funcion que permite establcer un nuevo empleado
     *
     * @param dts objecto EmpleadoServidor
     * @return boolean
     */
    public boolean setEmpleadoServidor(DtsEmpleadoServer dts) {
        try {
            sql = "INSERT INTO "
                    + "empleados_servidor(idempleado, nombre, apellidos, direccion, telefono, nombre_usuario,"
                    + " contrasena, fotografia)"
                    + " VALUES (?,?,?,?,?,?,?,?)";
            //establece una conexion con la bd
            conectarBD();
            //preparando la consulta
            ps = con.prepareStatement(sql);

            //enviando los parametros
            ps.setString(1, dts.getIdEmpelado());//idempleado
            ps.setString(2, dts.getNombre());//nombre
            ps.setString(3, dts.getApellidos());//app
            ps.setString(4, dts.getDireccion());//direccion
            ps.setString(5, dts.getTelefono());//telefono
            ps.setString(6, dts.getNomUsuario());//noUsr
            ps.setString(7, dts.getPasswd());//passwd

            //imagen
            ps.setBlob(8, dts.getImagen(), dts.getTamImagen());
            exito = ps.executeUpdate();
            cerrarPS();
            return exito != 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            cerrarPS();
            return false;
        }
    }

    /**
     * Funcion que permite actualizar los datos del empleado Servidor
     *
     * @param dts objeto EmpleadoServidor
     * @return boolean
     */
    public boolean updateEmpleadoServidor(DtsEmpleadoServer dts) {
        try {
            sql = "UPDATE empleados_servidor SET"
                    + " nombre=?,apellidos=?,direccion=?,telefono=?,nombre_usuario=?,contrasena=?,fotografia=?"
                    + " WHERE idempleado=?";
            //establece una conexion con la bd
            conectarBD();
            //preparando la consulta
            ps = con.prepareStatement(sql);
            //enviando los parametros            
            ps.setString(1, dts.getNombre());//nombre
            ps.setString(2, dts.getApellidos());//app
            ps.setString(3, dts.getDireccion());//direccion
            ps.setString(4, dts.getTelefono());//telefono
            ps.setString(5, dts.getNomUsuario());//noUsr
            ps.setString(6, dts.getPasswd());//passwd            
            //imagen
            ps.setBlob(7, dts.getImagen(), dts.getTamImagen());
            ps.setString(8, dts.getIdEmpelado());//idempleado

            exito = ps.executeUpdate();
            cerrarPS();
            return exito != 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            cerrarPS();
            return false;
        }
    }

    public boolean deleteEmpleadoServidor(DtsEmpleadoServer dts) {
        try {
            sql = "DELETE FROM empleados_servidor WHERE idempleado=?";
            //establece una conexion con la bd
            conectarBD();
            //preparando la consulta
            ps = con.prepareStatement(sql);
            //enviando los parametros
            ps.setString(1, dts.getIdEmpelado());//Id Empelado

            exito = ps.executeUpdate();
            cerrarPS();
            return exito != 0;
        } catch (SQLException ex) {
            cerrarPS();
            return false;
        }
    }
    public DefaultTableModel getEmpleadoServerBy(String txt) {

        try {
            sql = "SELECT idempleado, nombre, apellidos, direccion, telefono, nombre_usuario, contrasena, fotografia "
                    + "FROM empleados_servidor WHERE "
                    + "idEmpleado like '%" + txt + "%' or nombre like '%"+txt+"%';";
            conectarBD();
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                row[0] = rs.getString("idEmpleado");
                row[1] = rs.getString("nombre");
                row[2] = rs.getString("apellidos");
                row[3] = rs.getString("direccion");
                row[4] = rs.getString("telefono");
                row[5] = rs.getString("nombre_usuario");
                row[6] = rs.getString("contrasena");
                //foto                
                ImageIcon img = new ImageIcon(rs.getBytes("fotografia"));
                row[7] = new ImageIcon(img.getImage().getScaledInstance(100, 110, 0));
                modelo.addRow(row);
            }
            cerrarRS();
            return modelo;
        } catch (SQLException ex) {
            cerrarRS();
            System.out.println(ex.getMessage());
            return modelo;
        }
    }
    public DefaultTableModel getEmpleadoServer() {
        try {
            sql = "SELECT idempleado, nombre, apellidos, direccion, telefono, nombre_usuario, contrasena, fotografia "
                    + "FROM empleados_servidor;";
            conectarBD();
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                row[0] = rs.getString("idEmpleado");
                row[1] = rs.getString("nombre");
                row[2] = rs.getString("apellidos");
                row[3] = rs.getString("direccion");
                row[4] = rs.getString("telefono");
                row[5] = rs.getString("nombre_usuario");
                row[6] = rs.getString("contrasena");
                //foto                
                ImageIcon img = new ImageIcon(rs.getBytes("fotografia"));
                row[7] = new ImageIcon(img.getImage().getScaledInstance(100, 110, 0));
                modelo.addRow(row);
            }
            cerrarRS();
            return modelo;
        } catch (SQLException ex) {
            cerrarRS();
            System.out.println(ex.getMessage());
            return modelo;
        }
    }

    public boolean getLogin(DtsEmpleadoServer dts) {
        try {
            sql = "SELECT nombre_usuario, contrasena FROM "
                    + "empleados_servidor WHERE "
                    + "nombre_usuario='" + dts.getNomUsuario() + "' and contrasena='" + dts.getPasswd() + "';";
            conectarBD();
            st = con.createStatement();
            rs = st.executeQuery(sql);
            boolean x = rs.next();
            cerrarRS();
            return x;
        } catch (SQLException ex) {
            cerrarRS();
            return false;
        }
    }
    public ImageIcon getImagen(String usr)
    {
        try {
            sql = "SELECT  fotografia FROM empleados_servidor where nombre_usuario='"+usr+"';";
            conectarBD();
            st = con.createStatement();
            rs = st.executeQuery(sql);
            if (rs.next()) {          
                //foto                
                ImageIcon img = new ImageIcon(rs.getBytes("fotografia"));                
               return new ImageIcon(img.getImage().getScaledInstance(100, 110, 0));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }  
    public boolean getLoginAdmin(String usr,String passwd)
    {
        try {
            sql = "SELECT nombre FROM empleados_servidor WHERE"
                    + " nombre_usuario='"+usr+"' and contrasena='"+passwd+"';";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            return rs.next();
        } catch (SQLException ex) {
           return false;
        }
    }
    
}
