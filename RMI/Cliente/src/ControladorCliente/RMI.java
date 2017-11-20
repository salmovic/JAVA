/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorCliente;

import Common.Operaciones;
import java.rmi.NoSuchObjectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Skynet
 */
public class RMI implements Runnable
{        
     public static Registry registro;
    public static Operaciones obRemoto;
    static String ip="127.0.0.1";  
     Thread hilo;
    
    public RMI() {
        hilo = new Thread(this);
        hilo.start();
    }
     
    public boolean getConexion(String ip)
    {
        try {
            registro = LocateRegistry.getRegistry(ip, 81);            
            obRemoto=(Operaciones) registro.lookup("rmi"); 
            return true;
            
        } catch (RemoteException|NotBoundException ex) {  
            JOptionPane.showMessageDialog(null, "No se pudo conectar "+ex.getMessage());
            return false;
            
        }catch (Exception err){  
            JOptionPane.showMessageDialog(null, "No se pudo conectar "+err.getMessage());
            return false;            
        }       
    }
    @Override
    public void run() {
        if (getConexion(ip)) {
            JOptionPane.showMessageDialog(null, "Conexion con exito");
        }else
        {
            JOptionPane.showMessageDialog(null, "Imposible conectar");
        }
    }
    public static void cerrarConexion()
    {
         try {
             UnicastRemoteObject.unexportObject(RMI.registro, true);
             JOptionPane.showMessageDialog(null, "Se ha detenido correctamente");
         } catch (NoSuchObjectException ex) {
             JOptionPane.showMessageDialog(null, "No se pudo detener el proceso");
         }
    }
}
