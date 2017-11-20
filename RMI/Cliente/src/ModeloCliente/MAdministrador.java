/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloCliente;

import Common.DtsEmpleadoCliente;
import ControladorCliente.RMI;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Skynet
 */
public class MAdministrador{    
    
    public MAdministrador() {
        
    }
    public void mensaje(String msj)
    {
        try {
            RMI.obRemoto.mensaje(msj);
        } catch (RemoteException ex) {
            System.out.println(ex);
        }
    }
    public boolean setCliente(DtsEmpleadoCliente dts)
    {
        try {
            return RMI.obRemoto.setCliente(dts);
        } catch (RemoteException ex) {
            System.out.println(ex.getMessage());
          return false;
        }
    }
    public boolean updateCliente(DtsEmpleadoCliente dts)
    {
        try {
            return RMI.obRemoto.updateCliente(dts);
        } catch (RemoteException ex) {
            
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return false;
        }
    }
    public boolean deleteCliente(DtsEmpleadoCliente dts)
    {
        try {
            return RMI.obRemoto.eliminarCliente(dts);
        } catch (RemoteException ex) 
        {
            return false;
        }
    }
    public Object modeloCliente()
    {
        try {
            return RMI.obRemoto.getCliente();
        } catch (RemoteException ex) 
        {
            JOptionPane.showMessageDialog(null,"RemoteError "+ ex.getMessage());
            return null;
        }
    }
    
}
