/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VistaServer;

import Acciones.Cliente;
import Acciones.Operaciones;
import Acciones.OperacionesHelper;
import Acciones.OperacionesPOA;
import BD.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

/**
 *
 * @author Skynet
 */
public class MServidor extends OperacionesPOA {    
    Object[] titlulo = {"No","Nombre","Operación","Fecha","Tipo/Usuario","Sucursal","IP"};
    Object[] row = new Object[7];
    int cont=1;
    DefaultTableModel modelo = new DefaultTableModel(null,titlulo);
    //OPERACIONES BD    
    EmpleadoCliente opEC = new EmpleadoCliente();        ;
    OperacionesCliente opc = new OperacionesCliente();
    //VISTA SERVIDOR
    VServidor vs;       
    Thread h;
    //escucha las petiones de los clientes y hace las conversiones
    ORB orb;
    //Adaptador de objeto portable permite hace la comunicacion en red
    POA raizPOA;
    //Objeto que referencía a la interfáz
    Operaciones refOB;
    //refencia a la nomeclatura del contexto raiz
    NamingContextExt refNC;
    //vector de nombre de objeto a publicar
    NameComponent nomComp[];
    
    //METODO CONSTRUCTOR
    public MServidor(VServidor vs)
    {   this.vs = vs;        
        opEC = new EmpleadoCliente();        
    }
    public MServidor(){}
    
    public void ejecutarProceso(String port,String host)
    {
        
        try {
            VServidor.lblEstado.setText("Servidor: "+InetAddress.getLocalHost());
            //Agregamos parametros por defecto {puerto 81, host ip}
            orb = ORB.init(new String[]{"-ORBInitialPort",port,"-ORBInitialHost",host},null);
            //obtener referencia raizPOA y activar el POAManager
            raizPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            raizPOA.the_POAManager().activate();
            //referencia a la interface operaciones basicas
            refOB = OperacionesHelper.narrow(raizPOA.servant_to_reference(this));
            //referencia a la nomenclatura del contexto raiz
            refNC = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));
            //nombre de publicacion de objeto (idOperacion)
            nomComp = refNC.to_name("calculadora");
            //publicar el objeto
            refNC.rebind(nomComp, refOB);
            orb.run();//inicia el servidor
            
            
        } catch (InvalidName | AdapterInactive ex) {
            System.out.println(ex);
        } catch (ServantNotActive | WrongPolicy | org.omg.CosNaming.NamingContextPackage.InvalidName | NotFound | CannotProceed | UnknownHostException ex) {
            System.out.println(ex);
        }
    }

    //CARGAR IMAGEN SERVIDOR
    public ImageIcon getImagen(String usr)
    {
        return new EmpladoServidor().getImagen(usr);
    }
    
    
    //OBTENER HORA DEL SISTEMA
    public String getHoraSistema() {
        
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
    public String getFechaSistema() {
        Calendar c1 = Calendar.getInstance();
        int dia = c1.get(Calendar.DATE);
        int mes = c1.get(Calendar.MONTH);
        int anio = c1.get(Calendar.YEAR);
        String fecha = dia + "/" + (mes + 1) + "/" + anio;
        return fecha;
    }
    //INICA SERVIDOR

    @Override
    public boolean setCliente(Cliente dts) {

        return opEC.setEmpleadoCliente(dts);
        
    }

    @Override
    public boolean updateCliente(Cliente dts) {
        return opEC.updateEmpleadoCliente(dts);
    }

    @Override
    public boolean deleteCliente(String id) {
        return opEC.deleteEmpleadoCliente(id);
    }

    @Override
    public Cliente[] getClienteBy(String txt) {
        return opEC.getEmpleadoClienteBy(txt);
    }

    @Override
    public Cliente[] getCliente() {
        return opEC.getEmpleadoCliente();
    }
    
    @Override
    public boolean getLoginAdmin(String usr, String pass) {
        return opEC.getLoginAdm(usr, pass);
    }


    @Override
    public boolean getLoginCliente(String noc, String nip) {
        return opEC.getLoginClinete(noc, nip);
    }

    @Override
    public void mensaje(String nom,String op, String tipUs, String desk, String ip) 
    {   
        if (tipUs.equals("Cliente")) {
            String nombre= opc.getNombreCompleto(nom);
            llenarTabla(nombre, op, tipUs, desk, ip);
        }else
        llenarTabla(nom, op, tipUs, desk, ip);
    }

    @Override
    public double getSaldo(String noCuenta) {
        return opc.getSaldo(noCuenta);
    }

    @Override
    public boolean retiro(String no, double cant) {
        return opc.retiroEfectivo(no, cant);
    }

    @Override
    public boolean depositar(String no, double cant) {
        return opc.depositoEfectivo(no, cant);
    }

    @Override
    public boolean tiempoAire(String no, double cant) {
        return opc.tiempoAire(no, cant);
    }

    @Override
    public boolean pagoServicio(String no, double cant) {
       return opc.pagoServicio(no, cant);
    }

    
   void llenarTabla(String nom,String op, String tipUs, String desk, String ip)
   {
        row[0]=cont;
        row[1]=nom;
        row[2]=op;
        row[3]=getFechaSistema();
        row[4]=tipUs;
        row[5]=desk;
        row[6]=ip;
        modelo.addRow(row);
        cont++;
        VServidor.jtRegistroCliente.setModel(modelo);
   }
    
}
