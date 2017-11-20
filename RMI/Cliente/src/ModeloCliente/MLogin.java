/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloCliente;

import Common.DtsEmpleadoCliente;
import ControladorCliente.RMI;
import java.rmi.RemoteException;


/**
 *
 * @author Skynet
 */
public class MLogin 
{    
    /**
     * 
     * @param msj 
     */
    public void mensaje(String msj)
    {
        
        try {
            RMI.obRemoto.mensaje(msj);
        } catch (RemoteException ex) {
            System.out.println(ex.getMessage());
        }      
    }
    /**
     * Logueo del Cliente/Aministrador
     * @return 
     */
    public boolean logingAdmin(String usr,String passwd)
    {
        try {
            return RMI.obRemoto.loginAdmin(usr,passwd);
        } catch (RemoteException ex) 
        {
            return false;
        }
    }
    
    public boolean loginCliente(DtsEmpleadoCliente dts)
    {   
        try {
            return RMI.obRemoto.loginClinete(dts);
        } catch (RemoteException ex) {
            return false;
        }
    }
}
