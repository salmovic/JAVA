/*
 * Clase que permite estalecer la comunicion entre el cliente y el servidor
 */
package VistaCliente;

import Acciones.Operaciones;
import Acciones.OperacionesHelper;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;

/**
 *
 * @author Skynet
 */
public class ConServidor
{        
     ORB orb;
     public Operaciones objDist;
     NamingContextExt refNC;
    
     String port="81",host="localhost";
    
//    public Corba(String port,String host) {
//        this.port=port;
//        this.host=host;
//    }    
    
    public void conectarServidor()
    {
        try {
            //Agregamos parametros por defecto {puerto 81, host ip}
            orb = ORB.init(new String[]{"-ORBInitialPort", port, "-ORBInitialHost", host}, null);
            //referencia a la nomenclatura del contexto raiz
            refNC = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));
            //nombre de publicacion de objeto (idOperacion)
            objDist = OperacionesHelper.narrow(refNC.resolve_str("calculadora"));            
        } catch (InvalidName | NotFound | CannotProceed | org.omg.CosNaming.NamingContextPackage.InvalidName ex) {
            System.out.println(ex);
        }
    }
    /**
     * 
     * @param nomUsr
     * @param op
     * @param tipoUsr 
     */
    public void datosSucursal(String nomUsr,String op,String tipoUsr)
    {
         try {
             String equipo=InetAddress.getLocalHost().getHostName();
             String ip = InetAddress.getLocalHost().getHostAddress();
             conectarServidor();
             objDist.mensaje(nomUsr,op,  tipoUsr, equipo, ip);
         } catch (UnknownHostException ex) 
         {}
    }
}
