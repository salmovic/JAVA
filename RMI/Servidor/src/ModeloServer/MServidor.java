/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloServer;

import BD.*;
import Common.DtsEmpleadoCliente;
import Common.Operaciones;
import VistaServer.VServidor;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Skynet
 */
public class MServidor extends UnicastRemoteObject implements Runnable,Operaciones {

    //OPERACIONES BD
    EmpleadoCliente bdEC; 
    OperacionesCliente bdOC;
    
    //HILO SERVIDOR
    Thread hilo;
    VServidor vs;       
    Registry registro;
    
    public MServidor(VServidor vs)throws RemoteException 
    {
        hilo = new Thread(this);
        hilo.start();
        this.vs = vs;
        bdEC = new EmpleadoCliente(); 
        bdOC = new OperacionesCliente();
    }
    public MServidor()throws RemoteException{}
    //CARGAR IMAGEN SERVIDOR
    public ImageIcon getImagen(String usr)
    {
        return new EmpladoServidor().getImagen(usr);
    }
    //INICAR CONEXION SERVIDOR
     public String getConexion(int port, String id){
        try {
            registro = LocateRegistry.createRegistry(port);            
            registro.rebind(id, this);            
            return "Conexion establecida direccion: "+InetAddress.getLocalHost().toString()+" Puerto: "+port;
        } catch (RemoteException | UnknownHostException ex) {
            return "Imposible Conectar";
        }        
    }
    //DENETER CONEXION
     public boolean getDetenerConRMI()
     {
        try {            
            return UnicastRemoteObject.unexportObject(registro, true);
        } catch (NoSuchObjectException ex) {
            return false;
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
    //INICA SERVIDOR
    @Override
    public void run() {
        Thread ct = Thread.currentThread();
        while (ct == hilo) {
            vs.lblHora.setText(calcularHoraSistema());
            vs.lblFecha.setText(obtenerFechaSistema());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }
    //METODOS DE LAS OPERACIONES DEL CLIENTE
    @Override
    public boolean setCliente(DtsEmpleadoCliente dts) throws RemoteException {
        //ESTABLECER CLIENTE
        return bdEC.setEmpleadoCliente(dts);
    }
    //ACTUALIZAR CLIENTE
    @Override
    public boolean updateCliente(DtsEmpleadoCliente dts) throws RemoteException {        
        return bdEC.updateEmpleadoCliente(dts);
    }
    //OBTENER CLIENTE POR
    @Override
    public DefaultTableModel getClientesBy(String txt) throws RemoteException {        
        return null;
    }
    //OBTENER CLIENTE
    @Override
    public Object getCliente() throws RemoteException {        
        return bdEC.getEmpleadoCliente();
    }
    //MENSAJE
    @Override
    public void mensaje(String msj) throws RemoteException {
        System.out.println("Cliente dice: "+msj);
    }
    //LOGIN ADMINISTRADOR
    @Override
    public boolean loginAdmin(String usr, String paswd) throws RemoteException {
        return new EmpladoServidor().getLoginAdmin(usr, paswd);       
    }
    
    @Override
    public boolean loginClinete(DtsEmpleadoCliente dts) throws RemoteException {
        return bdEC.getLoginClinete(dts);
    }

    @Override
    public boolean eliminarCliente(DtsEmpleadoCliente dts) throws RemoteException {
        return bdEC.deleteEmpleadoCliente(dts);
    }
    //********************OPERACIONES LOGIC CLIENTE********************************
    @Override
    public double getSaldo(String noCuenta) throws RemoteException {
        return bdOC.getSaldo(noCuenta);
    }

    @Override
    public boolean retiroEfectivo(String noCuenta, double cantRetiro) throws RemoteException {
        return bdOC.retiroEfectivo(noCuenta, cantRetiro);
    }

    @Override
    public boolean depositoEfectivo(String noCuenta, double cantDeposito) throws RemoteException {
        return bdOC.depositoEfectivo(noCuenta, cantDeposito);
    }    

    @Override
    public boolean tiempoAire(String noCuenta, double cant) throws RemoteException {
        return bdOC.tiempoAire(noCuenta, cant);
    }

    @Override
    public boolean pagoServicio(String noCuenta, double cant) throws RemoteException {
        return bdOC.pagoServicio(noCuenta, cant);
    }

    @Override
    public Object getMovimientos(String noCuenta) throws RemoteException {
       return bdOC.getMovimientos(noCuenta);
    }
}
