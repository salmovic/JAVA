/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Common;


import java.rmi.Remote;
import java.rmi.RemoteException;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Skynet
 */
public interface Operaciones extends Remote
{    
    public boolean setCliente(DtsEmpleadoCliente dts)throws RemoteException;
    public boolean updateCliente(DtsEmpleadoCliente dts)throws RemoteException;
    public DefaultTableModel getClientesBy(String txt)throws RemoteException;
    public Object getCliente()throws RemoteException; 
    public void mensaje(String msj)throws RemoteException;
    //nuevo
    public boolean loginAdmin(String usr, String paswd)throws RemoteException;  
    public boolean loginClinete(DtsEmpleadoCliente dts)throws RemoteException;
    public boolean eliminarCliente(DtsEmpleadoCliente dts)throws RemoteException;    
    //OPERACIONES LOG CLIENTE        
    public double getSaldo(String noCuenta)throws RemoteException;    
    public boolean retiroEfectivo(String noCuenta,double cantRetiro)throws RemoteException;    
    public boolean depositoEfectivo(String noCuenta,double cantDeposito)throws RemoteException;
    public boolean tiempoAire(String noCuenta,double cant)throws RemoteException;
    public boolean pagoServicio(String noCuenta,double cant)throws RemoteException;
    public Object getMovimientos(String noCuenta)throws RemoteException;
}
