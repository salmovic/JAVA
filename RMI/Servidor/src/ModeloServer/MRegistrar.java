/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloServer;

import BD.EmpladoServidor;
import DatosServer.DtsEmpleadoServer;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Skynet
 */
public class MRegistrar
{
   EmpladoServidor bdES;
    DtsEmpleadoServer dtsES;
   public MRegistrar()
   {
       bdES = new EmpladoServidor();
       dtsES = new DtsEmpleadoServer();
   }   
   public boolean setEmpleadoServer(DtsEmpleadoServer dts)
   {
       return bdES.setEmpleadoServidor(dts);
   }
   public boolean updateEmpleadoServer(DtsEmpleadoServer dts)
   {
       return bdES.updateEmpleadoServidor(dts);
   }
   public boolean deleteEmpleadoServer(DtsEmpleadoServer dts)
   {
       return bdES.deleteEmpleadoServidor(dts);
   }
   public DefaultTableModel getEmpleado()
   {
       return bdES.getEmpleadoServer("");
   }
   public DefaultTableModel getEmpleadoBy(String txt)
   {
       return bdES.getEmpleadoServerBy(txt);
   }
   
}
