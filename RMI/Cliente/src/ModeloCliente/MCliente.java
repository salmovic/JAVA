/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloCliente;

import ControladorCliente.RMI;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Skynet
 */
public class MCliente {

    public MCliente() {
    }
    //RETIRO DE EFECTIVO
    public boolean retiroEfectivo(String noCuenta,double cantRetiro)
    { 
        try {
            return RMI.obRemoto.retiroEfectivo(noCuenta, cantRetiro);
        } catch (RemoteException ex) {
            return false;
        }        
    }
    //DEPOSITO EFECTIVO
    public boolean depositoEfectivo(String noCuenta,double cantRetiro)
    {
        try {
            return RMI.obRemoto.depositoEfectivo(noCuenta, cantRetiro);
        } catch (RemoteException ex) {
           return false;
        }
    }
    //PAGO DE TIEMPO AIRE
    public boolean tiempoAire(String noCuenta, double cant){
        try {
            return RMI.obRemoto.tiempoAire(noCuenta, cant);
        } catch (RemoteException ex) {
            return false;
        }
    }
    //PAGO DE SERVICIO
    public boolean pagoServicio(String noCuenta, double cant) {
        try {
            return RMI.obRemoto.pagoServicio(noCuenta, cant);
        } catch (RemoteException ex) {
            return false;
        }
    }
    //CONSULTA DE SALDO
    public double getSaldo(String nCuenta)
    {
        try {
            return RMI.obRemoto.getSaldo(nCuenta);
        } catch (RemoteException ex) 
        {
            return -1;
        }
    }
    //OBTIENE TODOS LOS MOVIMIENTOS REALIZADOS POR EL CLIENTE
    public Object getMovimientos(String noC)
    {
        try {
            return RMI.obRemoto.getMovimientos(noC);
        } catch (RemoteException ex) {
            return null;
        }
    }
    
    //SI UN NUMERO ES MULTIPLO DE 100
    public boolean multiploDeCien(double n)
    {
      if (n%100==0) {
            return true;
        }
      return false;
    }
}
