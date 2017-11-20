/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import Common.DtsMovimiento;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Skynet
 */
public class OperacionesCliente extends Conexion
{    
    int exito;
    public OperacionesCliente(){}
    
    //RETORNSA EL SALDO TOTAOL DEL CLIENTE
    public double getSaldo(String noCuenta)
    {
        double cant=0;
        try {
            sql = "SELECT saldo FROM empleados_cliente WHERE noCuenta='"+noCuenta+"'";
            conectarBD();
            st = con.createStatement();
            rs = st.executeQuery(sql);
            if (rs.next()) {
                cant= rs.getDouble("saldo"); 
                cerrarRS(); 
                setMovimiento(noCuenta, "Consulta Saldo");
                return cant;            
            }
                     
            
        } catch (SQLException ex) 
        {
            System.out.println(ex);
        }
        return 0;
    }
    double getSaldoAux(String noCuenta)
    {
        double cant=0;
        try {
            sql = "SELECT saldo FROM empleados_cliente WHERE noCuenta='"+noCuenta+"'";
            conectarBD();
            st = con.createStatement();
            rs = st.executeQuery(sql);
            if (rs.next()) {
                cant= rs.getDouble("saldo");
            }
            cerrarRS();
            return cant;
        } catch (SQLException ex) 
        {
            System.out.println(ex);
        }
        return 0;
    }
    //FUNCION PARA ESTABLECER UN RETIRO DE EFECTIVO
    public boolean retiroEfectivo(String noCuenta,double cantRetirar)
    {  
        double saldoTotal = getSaldoAux(noCuenta);
        
        if (saldoTotal>0&&saldoTotal>=cantRetirar) {
            saldoTotal-=cantRetirar;
            
            if (actualizarSaldo(noCuenta, saldoTotal)) {                
                return true;
            }
        }
        return false;
        
    }
    //FUNCION PARA ESTABLECER UN DEPOSITO DE EFECTIVO
    public boolean depositoEfectivo(String noCuenta,double cantDepositar)
    {  
        if (cantDepositar<1)return false;
        double saldoTotal = getSaldoAux(noCuenta);
        saldoTotal+=cantDepositar; 
        
          if (actualizarSaldo(noCuenta, saldoTotal)) {
              //setMovimiento(noCuenta, "Deposito Efectivo");
            return true;
        }
        return false;
        }     
    //PAGO DE TIMEPO AIRE  
    public boolean tiempoAire(String noCuenta,double cant)
    {
        double saldoTotal = getSaldoAux(noCuenta);
        
        if (saldoTotal>0&&saldoTotal>=cant) {
            saldoTotal-=cant;            
            if (actualizarSaldo(noCuenta, saldoTotal)) {
               // setMovimiento(noCuenta, "Pago Tiempo Aire");
                return true;
            }
        }
        return false;      
    }
    //PAGO DE SERVICIO
    public boolean pagoServicio(String noCuenta,double cant)
    {
        double saldoTotal = getSaldoAux(noCuenta);
        
        if (saldoTotal>0&&saldoTotal>=cant) {
            saldoTotal-=cant;            
            if (actualizarSaldo(noCuenta, saldoTotal)) {
               // setMovimiento(noCuenta, "Pago Servicio");
                return true;
            }
        }        
        return false;      
    }
    //actualizar saldo auxiliar
    public boolean actualizarSaldo(String noCuenta,double saldoTotal)
    {
        try
            {
                sql = "UPDATE empleados_cliente SET saldo=? WHERE noCuenta=?";
                conectarBD();
                ps = con.prepareStatement(sql);
                
                ps.setDouble(1, saldoTotal);
                ps.setString(2, noCuenta);                
                exito = ps.executeUpdate();
                cerrarPS();
                return exito!=0;
            }catch(SQLException ex)
            {
                cerrarPS();
                return false;
            }
    }
    //GUARDAR TIPO DE OPERACION
    boolean setMovimiento(String noCu,String op)
    {
        try {
            sql="INSERT INTO movimientos(idMov,noCuenta, Operacion, fecha, hora) VALUES (0,?,?,?,?);";
            conectarBD();
            ps=con.prepareStatement(sql);
            ps.setString(1, noCu);
            ps.setString(2, op);
            ps.setString(3, obtenerFechaSistema());
            ps.setString(4, calcularHoraSistema());             
            exito = ps.executeUpdate();
            cerrarPS();
            return exito!=0;
            
        } catch (SQLException ex) 
        {
            System.out.println(ex);
        }
        return false;
    } 
    public Object getMovimientos(String noCuenta)
    {  
        DtsMovimiento dts;
        ArrayList<DtsMovimiento> lis = new ArrayList<>();
        try {
            sql = "SELECT idMov, noCuenta, Operacion, fecha, hora FROM movimientos";
            st = con.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next())
            {    
                dts = new DtsMovimiento();                
                dts.setIdMov(rs.getString("idMov"));
                dts.setNoCuenta(rs.getString("noCuenta"));
                dts.setOperacion(rs.getString("Operacion"));                
                dts.setFecha(rs.getString("fecha"));                
                dts.setHora(rs.getString("hora"));
                lis.add(dts);
            }
            return lis;
        } catch (SQLException ex) 
        {
            return lis;
        }        
    }
        //OBTENER HORA DEL SISTEMA
    public String calcularHoraSistema() {        
        String hora, minutos, segundos, ampm;        
        Calendar calendario = new GregorianCalendar();
        ampm = calendario.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";        
        if (ampm.equals("PM")) {
            int h = calendario.get(Calendar.HOUR_OF_DAY) - 12;
            hora = h > 9 ? "" + h : "0" + h;
        } else {
            hora = calendario.get(Calendar.HOUR_OF_DAY) > 9 ? "" + calendario.get(Calendar.HOUR_OF_DAY) : "0" + calendario.get(Calendar.HOUR_OF_DAY);
        }
        minutos = calendario.get(Calendar.MINUTE) > 9 ? "" + calendario.get(Calendar.MINUTE) : "0" + calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND) > 9 ? "" + calendario.get(Calendar.SECOND) : "0" + calendario.get(Calendar.SECOND);
        String horaFinal = hora + ":" + minutos + ":" + segundos + " " + ampm;
        return horaFinal;
    }
    //OBTENER FECHA DEL SISTEMA 
    private String obtenerFechaSistema() {
        Calendar c1 = Calendar.getInstance();
        int dia = c1.get(Calendar.DATE);
        int mes = c1.get(Calendar.MONTH);
        int anio = c1.get(Calendar.YEAR);
        String fecha = dia + "/" + (mes + 1) + "/" + anio;
        return fecha;
    }
    public String getNombreCompleto(String noCuenta)
    {
        String[] ar =new String[2];
        try {
            sql = "SELECT `nombre`, `apellidos` FROM `empleados_cliente` WHERE noCuenta ='"+noCuenta+"'";
            st= con.createStatement();
            rs = st.executeQuery(sql);
            if (rs.next()) {
                ar[0] = rs.getString("nombre");
                ar[1]=rs.getString("apellidos");
            }
            return ar[0]+" "+ar[1];
        } catch (SQLException ex) {
            return null;
        }
        
    }
    public static void main(String[] args) {
        String ar = new OperacionesCliente().getNombreCompleto("1234");
        System.out.println(ar);
    }
}
